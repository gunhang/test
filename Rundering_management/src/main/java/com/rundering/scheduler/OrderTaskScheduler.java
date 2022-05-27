
package com.rundering.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rundering.dao.BranchDAO;
import com.rundering.dao.ComCodeDAO;
import com.rundering.dao.EmployeesDAO;
import com.rundering.dao.ItemOrderDAO;
import com.rundering.dao.LaundryArticlesDAO;
import com.rundering.dao.LaundryGoodsStockDAO;
import com.rundering.dao.LaundryOrderDAO;
import com.rundering.dao.NotificationDAO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.ComCodeVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.ItemOrderDetailVO;
import com.rundering.dto.ItemOrderVO;
import com.rundering.dto.LaundryArticlesVO;
import com.rundering.dto.LaundryGoodsStockVO;
import com.rundering.dto.LaundryOrderVO;
import com.rundering.dto.NotificationVO;
import com.rundering.util.SensSms;

public class OrderTaskScheduler {

	private BranchDAO branchDAO;

	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}

	private LaundryOrderDAO laundryOrderDAO;

	public void setLaundryOrderDAO(LaundryOrderDAO laundryOrderDAO) {
		this.laundryOrderDAO = laundryOrderDAO;
	}

	private ComCodeDAO comCodeDAO;

	public void setComCodeDAO(ComCodeDAO comCodeDAO) {
		this.comCodeDAO = comCodeDAO;
	}

	private EmployeesDAO employeesDAO;

	public void setEmployeesDAO(EmployeesDAO employeesDAO) {
		this.employeesDAO = employeesDAO;
	}

	private SensSms sensSms;

	public void setSensSms(SensSms sensSms) {
		this.sensSms = sensSms;
	}

	private NotificationDAO notificationDAO;

	public void setNotificationDAO(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}

	private LaundryGoodsStockDAO laundryGoodsStockDAO;

	public void setLaundryGoodsStockDAO(LaundryGoodsStockDAO laundryGoodsStockDAO) {
		this.laundryGoodsStockDAO = laundryGoodsStockDAO;
	}

	private ItemOrderDAO itemOrderDAO;

	public void setItemOrderDAO(ItemOrderDAO itemOrderDAO) {
		this.itemOrderDAO = itemOrderDAO;
	}

	private LaundryArticlesDAO laundryArticlesDAO;

	public void setLaundryArticlesDAO(LaundryArticlesDAO laundryArticlesDAO) {
		this.laundryArticlesDAO = laundryArticlesDAO;
	}

	private static final Logger logger = LoggerFactory.getLogger(OrderTaskScheduler.class);

	// 세탁주문 지점에 할당
	public Map<String,Object> assignLaundryOrderToBranch() throws Exception {
		
		Map<String,Object> dataMap = new HashMap<String, Object>();
		
		List<ComCodeVO> areaCodeList = comCodeDAO.selectComCodeByComCodeGrp("AREA");
		int assignedOrderCnt = 0;
		for (ComCodeVO comCodeVO : areaCodeList) {
			List<BranchVO> branchListByArea = branchDAO.selectBranchListByArea(comCodeVO.getComCode());
			List<LaundryOrderVO> orderListByArea = laundryOrderDAO
					.selectLaundryOrderListNotAssignedToBranchByArea(comCodeVO.getComCode());

			if (orderListByArea.size() == 0)
				continue;

			int totalOrderQuantityInArea = orderListByArea.size();
			int remainingOrderQuantity = orderListByArea.size();
			int cnt = 0;
			int totalProcessableOrderQuantity = 0;
			for (BranchVO branchVO : branchListByArea) {
				totalProcessableOrderQuantity += branchVO.getBranchLndrpcrymslmcoqy();
			}
			// 지역의 전체주문수가 지역내 지점들의 세탁가능수량보다 적을 때 (지역 내에서 감당가능할 때)
			if (totalProcessableOrderQuantity > totalOrderQuantityInArea) {
				BranchVO mostThroughputBranch = null;
				LaundryOrderVO orderVO = new LaundryOrderVO();
				for (BranchVO branchVO : branchListByArea) {
					// 지점 주문할당할 개수 = 지역내전체주문수 * (해당지점의 세탁가능수량 / 지역내모든지점의 세탁가능수량 * 100) / 100
					int quantity = (int) (totalOrderQuantityInArea
							* ((double) branchVO.getBranchLndrpcrymslmcoqy() / totalProcessableOrderQuantity * 100)
							/ 100);
					logger.info(comCodeVO.getComCodeNm() + " 지역의 전체주문수 : " + totalOrderQuantityInArea
							+ ", 지역내모든지점의 총세탁가능량 : " + totalProcessableOrderQuantity);
					logger.info(branchVO.getBranchName() + "의 세탁가능량 : " + branchVO.getBranchLndrpcrymslmcoqy() + ", "
							+ branchVO.getBranchName() + "의 할당 퍼센트"
							+ ((double) branchVO.getBranchLndrpcrymslmcoqy() / totalProcessableOrderQuantity * 100));
					logger.info(branchVO.getBranchName() + "에 할당될 주문수" + quantity);
					if (mostThroughputBranch == null || (mostThroughputBranch.getBranchLndrpcrymslmcoqy() < branchVO
							.getBranchLndrpcrymslmcoqy())) {
						mostThroughputBranch = branchVO;
					}
					for (int i = cnt + 0; i < cnt + quantity; i++) {
						orderVO = orderListByArea.get(i);
						orderVO.setBranchCode(branchVO.getBranchCode());
						laundryOrderDAO.updateLaundryOrderbranchCode(orderVO);
						assignedOrderCnt++;
					}
					cnt += quantity;
					remainingOrderQuantity -= quantity;
				}
				if (remainingOrderQuantity > 0) {
					for (int i = cnt + 0; i < cnt + remainingOrderQuantity; i++) {
						orderVO = orderListByArea.get(i);
						orderVO.setBranchCode(mostThroughputBranch.getBranchCode());
						laundryOrderDAO.updateLaundryOrderbranchCode(orderVO);
						remainingOrderQuantity -= 1;
						assignedOrderCnt++;
					}
				}
				logger.info(comCodeVO.getComCodeNm() + " 지역의 담당지점 미할당 주문수 : " + remainingOrderQuantity);

			} else { // 지역의 전체주문수가 지역내 지점들의 세탁가능수량보다 많을 때 (지역 내 지점들로 감당하지 못할 때)

				for (BranchVO branchVO : branchListByArea) {
					logger.info(comCodeVO.getComCodeNm() + " 지역의 전체주문수 : " + totalOrderQuantityInArea
							+ ", 지역내모든지점의 총세탁가능량 : " + totalProcessableOrderQuantity);
					logger.info(branchVO.getBranchName() + "의 세탁가능량 : " + branchVO.getBranchLndrpcrymslmcoqy());
					LaundryOrderVO orderVO = new LaundryOrderVO();
					for (int i = cnt + 0; i < cnt + branchVO.getBranchLndrpcrymslmcoqy(); i++) {
						orderVO = orderListByArea.get(i);
						orderVO.setBranchCode(branchVO.getBranchCode());
						laundryOrderDAO.updateLaundryOrderbranchCode(orderVO);
						assignedOrderCnt++;
					}
					cnt += branchVO.getBranchLndrpcrymslmcoqy();
					remainingOrderQuantity -= cnt;
				}
				logger.info(comCodeVO.getComCodeNm() + " 지역의 담당지점 미할당 주문수 : " + remainingOrderQuantity);
			}
		}

		// 초과 주문 타 지역지점에 넘기기
		for (ComCodeVO comCodeVO : areaCodeList) {
			List<LaundryOrderVO> orderListByArea = laundryOrderDAO
					.selectLaundryOrderListNotAssignedToBranchByArea(comCodeVO.getComCode());

			if (orderListByArea.size() == 0)
				continue;

			int remainingOrderQuantity = orderListByArea.size();
			int cnt = 0;
			int totalProcessableOrderQuantity = 0;
			if (remainingOrderQuantity > 0) {
				List<String> areaList = getAffordableArea(comCodeVO.getComCode(), remainingOrderQuantity);
				if (areaList.size() == 0) {

					logger.info(comCodeVO.getComCodeNm() + "지역의 초과주문을 이관할 적당한 지역을 찾지 못했습니다. 확인바랍니다.");

				} else if (areaList.size() == 1) { // 타지역 1곳에 할당

					int totalExcessCapacity = branchDAO.selectExcessCapacityOfTomorrowLaundryByArea(areaList.get(0));

					List<BranchVO> branchListOfOtherArea = branchDAO.selectBranchListByArea(areaList.get(0));
					int totalRemainingOrderQuantity = remainingOrderQuantity;
					totalProcessableOrderQuantity = 0;
					for (BranchVO branchVO : branchListOfOtherArea) {
						totalProcessableOrderQuantity += branchVO.getBranchLndrpcrymslmcoqy();
					}
					BranchVO mostThroughputBranch = null;
					LaundryOrderVO orderVO = new LaundryOrderVO();
					for (BranchVO branchVO : branchListOfOtherArea) {
						// 지점 주문할당할 개수 = 다른지역의 남은주문수 * (해당지점의 남은세탁가능수량 / 지역내모든지점의 남은세탁가능수량 * 100) / 100
						int quantity = (int) (totalRemainingOrderQuantity * ((double) branchDAO
								.selectExcessCapacityOfTomorrowLaundryByBranchCode(branchVO.getBranchCode())
								/ totalExcessCapacity * 100) / 100);
						logger.info(comCodeVO.getComCodeNm() + " 지역의 초과주문수 : " + totalRemainingOrderQuantity);
						logger.info(branchVO.getBranchName() + "의 세탁가능량 : "
								+ branchDAO.selectExcessCapacityOfTomorrowLaundryByBranchCode(branchVO.getBranchCode())
								+ ", " + branchVO.getBranchName() + "의 할당 퍼센트"
								+ ((double) branchDAO
										.selectExcessCapacityOfTomorrowLaundryByBranchCode(branchVO.getBranchCode())
										/ totalExcessCapacity * 100));
						logger.info(
								branchVO.getBranchName() + "에 할당될 " + comCodeVO.getComCodeNm() + " 지역의 주문수" + quantity);
						if (mostThroughputBranch == null || (mostThroughputBranch.getBranchLndrpcrymslmcoqy() < branchVO
								.getBranchLndrpcrymslmcoqy())) {
							mostThroughputBranch = branchVO;
						}
						for (int i = cnt + 0; i < cnt + quantity; i++) {
							orderVO = orderListByArea.get(i);
							orderVO.setBranchCode(branchVO.getBranchCode());
							laundryOrderDAO.updateLaundryOrderbranchCode(orderVO);
							assignedOrderCnt++;
						}
						cnt += quantity;
						remainingOrderQuantity -= quantity;
					}
					if (remainingOrderQuantity > 0) {
						for (int i = cnt + 0; i < cnt + remainingOrderQuantity; i++) {
							orderVO = orderListByArea.get(i);
							orderVO.setBranchCode(mostThroughputBranch.getBranchCode());
							laundryOrderDAO.updateLaundryOrderbranchCode(orderVO);
							remainingOrderQuantity -= 1;
							assignedOrderCnt++;
						}
					}
					logger.info(comCodeVO.getComCodeNm() + " 지역의 담당지점 미할당 주문수 : " + remainingOrderQuantity);

				} else if (areaList.size() == 2) { // 타지역 2곳에 할당

					BranchVO mostThroughputBranch = null;

					for (String area : areaList) {
						int totalExcessCapacity = branchDAO.selectExcessCapacityOfTomorrowLaundryByArea(area);
						int excessCapacity = branchDAO.selectExcessCapacityOfTomorrowLaundryByArea(area);
						if (excessCapacity >= remainingOrderQuantity)
							excessCapacity = remainingOrderQuantity;
						List<BranchVO> branchListOfOtherArea = branchDAO.selectBranchListByArea(area);
						int totalRemainingOrderQuantity = remainingOrderQuantity;
						LaundryOrderVO orderVO = new LaundryOrderVO();
						for (BranchVO branchVO : branchListOfOtherArea) {
							// 지점 주문할당할 개수 = 할당받을다른지역의세탁처리초과량 * (해당지점의 남은세탁가능수량 / 지역의남은세탁처리가능수량 * 100) / 100
							int quantity = (int) (excessCapacity * ((double) branchDAO
									.selectExcessCapacityOfTomorrowLaundryByBranchCode(branchVO.getBranchCode())
									/ totalExcessCapacity * 100) / 100);
							logger.info(comCodeVO.getComCodeNm() + " 지역의 초과주문수 : " + totalRemainingOrderQuantity);
							logger.info(branchVO.getBranchName() + "에 할당될 " + comCodeVO.getComCodeNm() + " 지역의 주문수"
									+ quantity);
							if (mostThroughputBranch == null
									|| (branchDAO.selectExcessCapacityOfTomorrowLaundryByBranchCode(
											mostThroughputBranch.getBranchCode()) < branchDAO
													.selectExcessCapacityOfTomorrowLaundryByBranchCode(
															branchVO.getBranchCode()))) {
								mostThroughputBranch = branchVO;
							}
							for (int i = cnt + 0; i < cnt + quantity; i++) {
								orderVO = orderListByArea.get(i);
								orderVO.setBranchCode(branchVO.getBranchCode());
								laundryOrderDAO.updateLaundryOrderbranchCode(orderVO);
								assignedOrderCnt++;
							}
							cnt += quantity;
							remainingOrderQuantity -= quantity;
						}
					}
					if (remainingOrderQuantity > 0) {
						LaundryOrderVO orderVO = new LaundryOrderVO();
						for (int i = cnt + 0; i < cnt + remainingOrderQuantity; i++) {
							orderVO = orderListByArea.get(i);
							orderVO.setBranchCode(mostThroughputBranch.getBranchCode());
							laundryOrderDAO.updateLaundryOrderbranchCode(orderVO);
							remainingOrderQuantity -= 1;
							assignedOrderCnt++;
						}
					}
					logger.info(comCodeVO.getComCodeNm() + " 지역의 담당지점 미할당 주문수 : " + remainingOrderQuantity);
				}

			}
		}

		int remainAllAreaOrder = 0;
		for (ComCodeVO comCodeVO : areaCodeList) {
			List<LaundryOrderVO> orderListByArea = laundryOrderDAO
					.selectLaundryOrderListNotAssignedToBranchByArea(comCodeVO.getComCode());
			remainAllAreaOrder += orderListByArea.size();
		}
		
		//할당된 주문이 있을 경우에만 알림
		if(assignedOrderCnt > 0) {
			// 본사 직원들에게 알림
			List<EmployeesVO> employeesList = employeesDAO.selectEmployeesByBranchCode("000000");
			NotificationVO notificationVO = new NotificationVO();
			for (EmployeesVO employeesVO : employeesList) {
				int sequence = notificationDAO.selectNotificationSequenceNextValue();
				notificationVO.setNtcnId(String.valueOf(sequence));
				notificationVO.setEmployeeId(employeesVO.getEmployeeId());
				notificationVO.setNtcnknd("PC"); // 알림종류 공통코드 - 할당완료
				notificationVO.setNtcncn("세탁주문, 미할당 " + remainAllAreaOrder + "건");
				notificationVO.setNtcnclickhourUrl("'/runderingmanage/admin/laundryorder/list','A010100'");
				notificationDAO.insertNotification(notificationVO);
			}
			
			// 주문이 할당된 모든 지점의 사원에게 공지 알림 - 배송사원 제외
			List<BranchVO> branchList = branchDAO.selectBranchList();
			for (BranchVO branch : branchList) {
				if (branch.getBranchCode().equals("000000"))
					continue;
				List<LaundryOrderVO> orderlist = laundryOrderDAO
						.selectLaundryOrderListPickUpRequestDateTodayByBranchCode(branch.getBranchCode());
				if (orderlist.size() < 1)
					continue;
				
				List<EmployeesVO> employeesli = employeesDAO.selectEmployeesByBranchCode(branch.getBranchCode());
				for (EmployeesVO employees2 : employeesli) {
					if (employees2.getDepartment().equals("DE"))
						continue;
					int sequence = notificationDAO.selectNotificationSequenceNextValue();
					notificationVO.setNtcnId(String.valueOf(sequence));
					notificationVO.setEmployeeId(employees2.getEmployeeId());
					notificationVO.setNtcnknd("PC"); // 알림종류 공통코드 - 할당완료
					notificationVO.setNtcncn("세탁주문 " + orderlist.size() + "건 할당됨");
					notificationVO.setNtcnclickhourUrl("'/runderingmanage/branch/laundrysituatuion/list','B010100'");
					notificationDAO.insertNotification(notificationVO);
				}
			}
		}

		dataMap.put("assignedOrderCnt",assignedOrderCnt);
		dataMap.put("remainAllAreaOrder",remainAllAreaOrder);
		return dataMap;

	}

	private List<String> getAffordableArea(String area, int remainQuantity) throws Exception {
		List<String> AffordableArea = new ArrayList<String>();
		int excessCapacityOfFirstArea = 0;
		int excessCapacityOfSecondArea = 0;
		String firstArea = "";
		String secondArea = "";
		switch (area) {
		case "0601":
			firstArea = "0602";
			secondArea = "0605";
			break;
		case "0602":
			firstArea = "0601";
			secondArea = "0603";
			break;
		case "0603":
			firstArea = "0602";
			secondArea = "0604";
			break;
		case "0604":
			firstArea = "0603";
			secondArea = "0605";
			break;
		case "0605":
			firstArea = "0604";
			secondArea = "0601";
			break;
		default:
			return AffordableArea;
		}
		excessCapacityOfFirstArea = branchDAO.selectExcessCapacityOfTomorrowLaundryByArea(firstArea);
		excessCapacityOfSecondArea = branchDAO.selectExcessCapacityOfTomorrowLaundryByArea(secondArea);
		if (remainQuantity > (excessCapacityOfFirstArea + excessCapacityOfSecondArea)) {
			return AffordableArea;
		}
		if (remainQuantity <= excessCapacityOfFirstArea && excessCapacityOfFirstArea >= excessCapacityOfSecondArea) {
			AffordableArea.add(firstArea);
		} else if (remainQuantity <= excessCapacityOfSecondArea
				&& excessCapacityOfFirstArea < excessCapacityOfSecondArea) {
			AffordableArea.add(secondArea);
		} else {
			AffordableArea.add(firstArea);
			AffordableArea.add(secondArea);
		}
		return AffordableArea;
	}

	// 수거기사 배정
	public void assignPickupEmployee() throws Exception {
		List<ComCodeVO> areaCodeList = comCodeDAO.selectComCodeByComCodeGrp("AREA");

		for (ComCodeVO comCodeVO : areaCodeList) {
			List<BranchVO> branchListByArea = branchDAO.selectBranchListByArea(comCodeVO.getComCode());
			for (BranchVO branchVO : branchListByArea) {
				List<LaundryOrderVO> orderListByBranchCode = laundryOrderDAO
						.selectLaundryOrderListPickUpRequestDateTodayByBranchCode(branchVO.getBranchCode());
				List<EmployeesVO> deliveryDepartmentEmployees = employeesDAO
						.selectDeliveryDepartmentEmployeesByBranchCode(branchVO.getBranchCode());

				if (orderListByBranchCode.size() == 0)
					continue;
				if (deliveryDepartmentEmployees.size() == 0) {
					logger.info(branchVO.getBranchName() + "의 배송사원이 없습니다.");
					continue;
				}

				int numberOfEmployees = deliveryDepartmentEmployees.size();
				int numberOfOrder = orderListByBranchCode.size();

				int cnt = 0;

				int quantity = (int) ((double) numberOfOrder / numberOfEmployees);
				for (EmployeesVO employeesVO : deliveryDepartmentEmployees) {
					LaundryOrderVO orderVO = new LaundryOrderVO();
					for (int i = cnt + 0; i < cnt + quantity; i++) {
						orderVO = orderListByBranchCode.get(i);
						orderVO.setPickupEmployeeId(employeesVO.getEmployeeId());
						laundryOrderDAO.updateLaundryOrderPickupEmployeeId(orderVO);
						orderVO.setOrderStatus("02");
						laundryOrderDAO.updateLaundryOrderStatusByOrderNo(orderVO);

					}
					cnt += quantity;
					logger.info(branchVO.getBranchName() + "의 " + employeesVO.getEmployeeId() + " 사원에게 전체 수거건 "
							+ numberOfOrder + "개 중 " + quantity + "개 할당됨");
				}
				if ((numberOfOrder - cnt) > 0) {
					LaundryOrderVO orderVO = new LaundryOrderVO();
					for (int i = cnt + 0; i < cnt + (numberOfOrder - cnt); i++) {
						orderVO = orderListByBranchCode.get(i);
						orderVO.setPickupEmployeeId(deliveryDepartmentEmployees.get(0).getEmployeeId());
						laundryOrderDAO.updateLaundryOrderPickupEmployeeId(orderVO);
						orderVO.setOrderStatus("02");
						laundryOrderDAO.updateLaundryOrderStatusByOrderNo(orderVO);
					}
					logger.info(branchVO.getBranchName() + "의 " + deliveryDepartmentEmployees.get(0).getEmployeeId()
							+ " 사원에게 " + (numberOfOrder - cnt) + "개 추가 할당됨");
				}
			}
		}
	}

	// 배송기사 배정
	public void assignDeliveryEmployee() throws Exception {
		List<ComCodeVO> areaCodeList = comCodeDAO.selectComCodeByComCodeGrp("AREA");

		for (ComCodeVO comCodeVO : areaCodeList) {
			List<BranchVO> branchListByArea = branchDAO.selectBranchListByArea(comCodeVO.getComCode());
			for (BranchVO branchVO : branchListByArea) {
				List<LaundryOrderVO> orderListByBranchCode = laundryOrderDAO
						.selectCompletedLaundryOrderListByBranchCode(branchVO.getBranchCode());
				List<EmployeesVO> deliveryDepartmentEmployees = employeesDAO
						.selectDeliveryDepartmentEmployeesByBranchCode(branchVO.getBranchCode());

				if (orderListByBranchCode.size() == 0)
					continue;
				if (deliveryDepartmentEmployees.size() == 0) {
					logger.info(branchVO.getBranchName() + "의 배송사원이 없습니다.");
					continue;
				}

				int numberOfEmployees = deliveryDepartmentEmployees.size();
				int numberOfOrder = orderListByBranchCode.size();

				int cnt = 0;

				int quantity = (int) ((double) numberOfOrder / numberOfEmployees);
				for (EmployeesVO employeesVO : deliveryDepartmentEmployees) {
					LaundryOrderVO orderVO = new LaundryOrderVO();
					for (int i = cnt + 0; i < cnt + quantity; i++) {
						orderVO = orderListByBranchCode.get(i);
						orderVO.setDeliveryEmployeeId(employeesVO.getEmployeeId());
						laundryOrderDAO.updateLaundryOrderDeliveryEmployeeId(orderVO);
						orderVO.setOrderStatus("07");
						laundryOrderDAO.updateLaundryOrderStatusByOrderNo(orderVO);
						// 문자발송
						try {
							sensSms.sendSMS(orderVO.getContactNumber().trim(), "[Rundering]\n고객님의 세탁이 완료되어 내일 오전 7시 전에 도착할 예정입니다.");
						} catch (Exception e) {
							logger.warn("주문번호 "+orderVO.getOrderNo()+" 배송안내 문자 발송실패");
						}

					}
					cnt += quantity;
					logger.info(branchVO.getBranchName() + "의 " + employeesVO.getEmployeeId() + " 사원에게 전체 배송건 "
							+ numberOfOrder + "개 중 " + quantity + "개 할당됨");
				}
				if ((numberOfOrder - cnt) > 0) {
					LaundryOrderVO orderVO = new LaundryOrderVO();
					for (int i = cnt + 0; i < cnt + (numberOfOrder - cnt); i++) {
						orderVO = orderListByBranchCode.get(i);
						orderVO.setDeliveryEmployeeId(deliveryDepartmentEmployees.get(0).getEmployeeId());
						laundryOrderDAO.updateLaundryOrderDeliveryEmployeeId(orderVO);
						orderVO.setOrderStatus("07");
						laundryOrderDAO.updateLaundryOrderStatusByOrderNo(orderVO);
					}
					logger.info(branchVO.getBranchName() + "의 " + deliveryDepartmentEmployees.get(0).getEmployeeId()
							+ " 사원에게 " + (numberOfOrder - cnt) + "개 추가 할당됨");
				}

			}
		}
	}
	
	//지점에서의 버튼을 통한 세탁완료주문 배송기사 배정
	public Map<String,Object> assignLaundryOrderToBranchDeliveryEmployee(String branchCode) throws Exception{
		Map<String,Object> dataMap = new HashMap<String, Object>();
		
		int assignedOrderCnt = 0;
		int numberOfDeliveryEmployees = 0;
		
		BranchVO branchVO = branchDAO.getBranchByCode(branchCode);
		
		List<LaundryOrderVO> orderListByBranchCode = laundryOrderDAO.selectCompletedLaundryOrderListByBranchCode(branchCode);
		List<EmployeesVO> deliveryDepartmentEmployees = employeesDAO.selectDeliveryDepartmentEmployeesByBranchCode(branchCode);
		numberOfDeliveryEmployees = deliveryDepartmentEmployees.size();
		
		if (orderListByBranchCode.size() == 0) {
			dataMap.put("assignedOrderCnt",assignedOrderCnt);
			dataMap.put("numberOfDeliveryEmployees",numberOfDeliveryEmployees);
			return dataMap;
		}
		if (deliveryDepartmentEmployees.size() == 0) {
			dataMap.put("assignedOrderCnt",assignedOrderCnt);
			dataMap.put("numberOfDeliveryEmployees",numberOfDeliveryEmployees);
			return dataMap;
		}

		int numberOfEmployees = deliveryDepartmentEmployees.size();
		int numberOfOrder = orderListByBranchCode.size();

		int cnt = 0;

		int quantity = (int) ((double) numberOfOrder / numberOfEmployees);
		for (EmployeesVO employeesVO : deliveryDepartmentEmployees) {
			LaundryOrderVO orderVO = new LaundryOrderVO();
			for (int i = cnt + 0; i < cnt + quantity; i++) {
				orderVO = orderListByBranchCode.get(i);
				orderVO.setDeliveryEmployeeId(employeesVO.getEmployeeId());
				laundryOrderDAO.updateLaundryOrderDeliveryEmployeeId(orderVO);
				orderVO.setOrderStatus("07");
				laundryOrderDAO.updateLaundryOrderStatusByOrderNo(orderVO);
				assignedOrderCnt++;
				// 문자발송 주석처리
				try {
					sensSms.sendSMS(orderVO.getContactNumber().trim(), "[Rundering]\n고객님의 세탁이 완료되어 내일 오전 7시 전에 도착할 예정입니다.");
				} catch (Exception e) {
					logger.warn("주문번호 "+orderVO.getOrderNo()+" 배송안내 문자 발송실패");
				}

			}
			cnt += quantity;
			logger.info(branchVO.getBranchName() + "의 " + employeesVO.getEmployeeId() + " 사원에게 전체 배송건 "
					+ numberOfOrder + "개 중 " + quantity + "개 할당됨");
		}
		if ((numberOfOrder - cnt) > 0) {
			LaundryOrderVO orderVO = new LaundryOrderVO();
			for (int i = cnt + 0; i < cnt + (numberOfOrder - cnt); i++) {
				orderVO = orderListByBranchCode.get(i);
				orderVO.setDeliveryEmployeeId(deliveryDepartmentEmployees.get(0).getEmployeeId());
				laundryOrderDAO.updateLaundryOrderDeliveryEmployeeId(orderVO);
				orderVO.setOrderStatus("07");
				laundryOrderDAO.updateLaundryOrderStatusByOrderNo(orderVO);
				assignedOrderCnt++;
			}
			logger.info(branchVO.getBranchName() + "의 " + deliveryDepartmentEmployees.get(0).getEmployeeId()
					+ " 사원에게 " + (numberOfOrder - cnt) + "개 추가 할당됨");
		}
		
		dataMap.put("assignedOrderCnt",assignedOrderCnt);
		dataMap.put("numberOfDeliveryEmployees",numberOfDeliveryEmployees);
		return dataMap;
	}

	public void autoOrder() throws Exception {
		List<BranchVO> branchList = branchDAO.selectBranchList();
		
		
		/* 키 : 아티클 코드 벨류 아티클 이름 */
		Map<String, Integer> articlesPrice = new HashMap<String, Integer>();
		// 지점 가져오기
		

		List<LaundryArticlesVO> laundrArticlesList = laundryArticlesDAO.selectLandryArticlesStock();
		// 물품가격을 합함
		for (LaundryArticlesVO laundryArticles : laundrArticlesList) {

			articlesPrice.put(laundryArticles.getArticlesCode(), laundryArticles.getPrice());

		}
		for (BranchVO branch : branchList) {
			autoOrderMethod(branch.getBranchCode(),articlesPrice,laundryGoodsStockDAO,itemOrderDAO);
		}
	}
	
	public void autoOrderMethod(String branchCode,Map<String,Integer> articlesPrice,LaundryGoodsStockDAO laundryGoodsStockDAO, ItemOrderDAO itemOrderDAO) throws Exception {
		
		
		List<LaundryGoodsStockVO> goodList = laundryGoodsStockDAO.selectLaundryGoodsStockByBranchCode(branchCode);

		int sum = 0;
		for (LaundryGoodsStockVO laundryGoodsStock : goodList) {

			int supplyCount = laundryGoodsStock.getSupplyCount();
			int point = laundryGoodsStock.getAutoOrderPoint();
			Date autoLastDay = laundryGoodsStock.getAutoOrderLastDate();
			Date sysdate = new Date();

			// 14일 비교
			if (supplyCount < point) {
				if ((sysdate.getTime() - autoLastDay.getTime()) / (1000 * 60 * 60 * 24) >= 14) {
					// 제품 * 개수
					sum += articlesPrice.get(laundryGoodsStock.getArticlesCode())
							* laundryGoodsStock.getAutoOrderCount();
					// 총합 가격을 가져옴
				}
			}

		}
		// SEQ 가져오기
		
		// 주문 넣기
		if (sum > 0) {
			String seq = itemOrderDAO.seq();
			ItemOrderVO itemOrder = new ItemOrderVO();
			itemOrder.setBranchCode(branchCode);
			itemOrder.setOrdercode(seq);
			itemOrder.setItemOrderPaymentPrice(sum);
			itemOrderDAO.insertItemOrderByItmeOrder(itemOrder);

			// 주문 상세 순번
			int detailSeq = 1;

			for (LaundryGoodsStockVO laundryGoodsStock : goodList) {
				if(laundryGoodsStock.getBranchCode().equals("000000")) {
					continue;
				}
				
				int supplyCount = laundryGoodsStock.getSupplyCount();
				int point = laundryGoodsStock.getAutoOrderPoint();
				Date autoLastDay = laundryGoodsStock.getAutoOrderLastDate();
				Date sysdate = new Date();

				// 14일 비교
				if (supplyCount < point) {
					if ((sysdate.getTime() - autoLastDay.getTime()) / (1000 * 60 * 60 * 24) >= 14) {
						ItemOrderDetailVO detail = new ItemOrderDetailVO();
						// 가격 뽑기
						detail.setPrice(articlesPrice.get(laundryGoodsStock.getArticlesCode())
								* laundryGoodsStock.getAutoOrderCount());
						detail.setOrdercode(seq);
						detail.setArticlesCode(laundryGoodsStock.getArticlesCode());
						detail.setSeq(detailSeq);
						detail.setOrderCount(laundryGoodsStock.getAutoOrderCount());
						itemOrderDAO.insertItemOrderDetailByItmeOrderDetail(detail);
						// 날짜업데이틍
						laundryGoodsStockDAO.updateLaundryGoodsStockLastDateAuto(laundryGoodsStock);

						detailSeq++;

					}
				}
			}
		}
	}

}
