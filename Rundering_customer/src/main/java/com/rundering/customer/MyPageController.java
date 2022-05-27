package com.rundering.customer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rundering.command.FAQModifyCommand;
import com.rundering.command.MemberAddCommand;
import com.rundering.command.MyOrderCriteria;
import com.rundering.dto.AttachVO;
import com.rundering.dto.LaundryOrderVO;
import com.rundering.dto.MemberAddressVO;
import com.rundering.dto.MemberVO;
import com.rundering.dto.ReplyVO;
import com.rundering.service.AttachService;
import com.rundering.service.FAQService;
import com.rundering.service.LaundryOrderService;
import com.rundering.service.MemberAddressService;
import com.rundering.service.MemberService;
import com.rundering.service.PaymentService;
import com.rundering.service.ReplyService;
import com.rundering.util.FileUtil;
import com.rundering.util.GetAttachesByMultipartFileAdapter;
import com.rundering.util.UserSha256;


@RequestMapping("/mypage")
@Controller
public class MyPageController {
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="memberAddressService")
	private MemberAddressService memberAddressService;

	@Resource(name="attachService")
	private AttachService attachService;
	
	@Resource(name="paymentService")
	private PaymentService paymentService;
	
	@Resource(name = "boardPath")
	private String boardPath;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private LaundryOrderService laundryOrderService; 
	
	@Autowired
	private FAQService faqService;
	
	// 비밀번호 체크 폼
	@RequestMapping("")
	public String mypage() {
		String url = "/mypage/my_privacy_check";
		return url;
	}	
	
	// 비밀번호 체크
	@RequestMapping("/pwCheck")
	public ResponseEntity<String> pwCheck(String password,HttpSession session) throws Exception {
		ResponseEntity<String> entity = null;
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		password = UserSha256.encrypt(password);
		
		try {
			String pw = memberService.checkPw(loginUser.getId());
			if (password.equals(pw)) {
				entity = new ResponseEntity<String>("OK", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("", HttpStatus.OK);
			}
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


		return entity;
	}
	
	// 회원정보 수정 폼
	@RequestMapping("/memberModifyform")
	public ModelAndView memberModifyForm(HttpServletRequest request, ModelAndView mnv) throws Exception {
		String url = "/mypage/my_privacy";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		//loginUser.setPassword(UserSha256.encrypt(loginUser.getPassword()));
		MemberAddressVO memberAddress = memberAddressService.getDefaultMemberAddress(loginUser.getMemberNo());
		mnv.addObject("memberAddress",memberAddress);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	// 회원정보 수정 폼
	@RequestMapping("/memberModify")
	public ResponseEntity<String> memberModify(MemberAddCommand mac) {
		
		ResponseEntity<String> entity = null;
		
		try {
			memberService.modifyMember(mac);
			
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entity;
	}
	
	// 비밀번호 비동기 변경
	@RequestMapping("/pwModify")
	public ResponseEntity<String> pwModify(HttpServletRequest request,String password) throws Exception {
		ResponseEntity<String> entity = null;
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	
		String pw = UserSha256.encrypt(password);
		loginUser.setPassword(pw);

		try {
			
			memberService.updatePassword(loginUser);
			
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);
			
		} catch (SQLException e) {
			
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

	return entity;
	}
	
	
	//문의내역
	@RequestMapping("/myinquiry/list")
	private ModelAndView myInquiryList(HttpServletRequest request,MyOrderCriteria cri, ModelAndView mnv) throws Exception {
		String url = "mypage/my_inquiry_list";
		
		HttpSession session = request.getSession();
		MemberVO mv = (MemberVO) session.getAttribute("loginUser");
		cri.setWriter(mv.getMemberNo());
		
		Map<String, Object> dataMap = faqService.getMyFAQList(cri);
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);

		return mnv;
	}
	
	// 문의내역 상세보기
	@RequestMapping(value = "/myinquiry/detail")
	private ModelAndView faqDetail(int faqno, HttpServletRequest request, ModelAndView mnv, HttpSession session) throws Exception {

		String url = "question/question_detail";
		
		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap = faqService.getFAQModify(faqno);

		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);

		return mnv;
	}
	
	// 문의사항 수정1
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(MyOrderCriteria cri, Model model, HttpSession session, int faqno, ModelAndView mnv) throws Exception {

		String url = "mypage/my_inquiry_modify";

		Map<String, Object> dataMap = faqService.getFAQModify(faqno);

		mnv.addAllObjects(dataMap);

		mnv.setViewName(url);

		return mnv;
	}
	
	// 문의사항 수정2
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPost(FAQModifyCommand faqcmd, HttpServletRequest request, RedirectAttributes rttr) throws Exception {

		String url = "redirect:/mypage/my_inquiry_detail";

		List<AttachVO> attachList = GetAttachesByMultipartFileAdapter.save(faqcmd.getUploadFile(), this.boardPath,"고객문의");
		
		faqService.modify(faqcmd, attachList);

		rttr.addAttribute("faqno", faqcmd.getFaqno());
		rttr.addFlashAttribute("from", "modify");

		return url;
	}

	// 문의사항 삭제
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(int faqno, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/mypage/myinquiry/list";

		faqService.remove(faqno);

		rttr.addFlashAttribute("from", "remove");
		rttr.addAttribute("faqno", faqno);

		return url;
	}
	
	
	//회원탈퇴폼
	@RequestMapping("/secedeform")
	private String secedeform() {
		String url = "mypage/my_secede";
		return url;
	}
	
	
	//회원탈퇴
	@RequestMapping("/secede")
	@ResponseBody
	private ResponseEntity<String> secede(HttpServletRequest request,String password,MyOrderCriteria cri) throws Exception {
		ResponseEntity<String> entity = null;
		
			HttpSession session = request.getSession();
			MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
			cri.setMemberNo(loginUser.getMemberNo());
			String pw = UserSha256.encrypt(password);
		try {
			
			String upw = memberService.checkPw(loginUser.getId());
			
			int count = laundryOrderService.ingCount(cri);
			if (upw.equals(pw) ) {
				
				if(count == 0) {
					memberService.deleteMember(loginUser.getId());
					entity = new ResponseEntity<String>("OK", HttpStatus.OK);
				}
				
				entity = new ResponseEntity<String>("NO", HttpStatus.OK);
				
			} else {
				entity = new ResponseEntity<String>("", HttpStatus.OK);
			}
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	// 이메일 중복 체크 
	@RequestMapping("/emailCheck")
	@ResponseBody
	public ResponseEntity<String> emailCheck(String email) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			MemberAddCommand mac = memberService.checkEmail(email);
			if (mac != null) {
				entity = new ResponseEntity<String>("OK", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("", HttpStatus.OK);
			}
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	// 내 주소 관리
	@RequestMapping("/myaddress")
	public ModelAndView myaddress(HttpServletRequest request, ModelAndView mnv) throws Exception {
		String url = "/mypage/my_address";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		MemberAddressVO defaultMemberAddress = memberAddressService.getDefaultMemberAddress(loginUser.getMemberNo());
		mnv.addObject("defaultMemberAddress",defaultMemberAddress);
		
		List<MemberAddressVO> memberAddressList = memberAddressService.getMemberAddressList(loginUser.getMemberNo());
		mnv.addObject("memberAddressList",memberAddressList);
		
		mnv.setViewName(url);
		
		return mnv;
	}	
	
	
	// 내 주소 디테일
	@RequestMapping("/myaddress/detail")
	@ResponseBody
	public ResponseEntity<MemberAddressVO> addrDetail(String addressNo){
		
		ResponseEntity<MemberAddressVO> entity = null;
		
		
		try {
		
			MemberAddressVO mv = memberAddressService.getMemberAddress(addressNo);
			
			String addrNo = mv.getAddressNo()+"";
			
			mv.setAddrNo(addrNo);
			
			if (mv != null) {
				entity = new ResponseEntity<MemberAddressVO>(mv, HttpStatus.OK);
			} 
		} catch (SQLException e) {
			entity = new ResponseEntity<MemberAddressVO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	// 추가 등록된 주소 삭제(기본주소를 제외한 주소 삭제)
	@RequestMapping("/myaddress/remove")
	@ResponseBody
	public ResponseEntity<String> remove(String addressNo){
		
		ResponseEntity<String> entity = null;
		
		try {
			memberAddressService.removeByAddressNo(addressNo);
			
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);
			
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	// 내 주소 기본 주소지로 변경 및 수정
	@RequestMapping("/myaddress/defaultmodify")
	@ResponseBody
	public ResponseEntity<String> modifyDefaultAdd(HttpServletRequest request, MemberAddressVO memberAdd){
		ResponseEntity<String> rs = null;
		
		HttpSession session = request.getSession();
		MemberVO mv = (MemberVO) session.getAttribute("loginUser");
		
		int addressNo =  Integer.parseInt(memberAdd.getAddrNo());
		
		memberAdd.setAddressNo(addressNo);
		memberAdd.setMemberNo(mv.getMemberNo());
		try {
			memberAddressService.modifyDefaultAddress(memberAdd);
			rs = new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			rs = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return rs;
	}
		
	// 내 주소지 수정
	@RequestMapping("/myaddress/modify")
	@ResponseBody
	public ResponseEntity<String> modifyAdd(MemberAddressVO memberAdd){
		ResponseEntity<String> rs = null;
		
		int addressNo =  Integer.parseInt(memberAdd.getAddrNo());
		
		memberAdd.setAddressNo(addressNo);
		memberAdd.setAddressNo(addressNo);
		
		try {
			memberAddressService.modifyAddress(memberAdd);
			rs = new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			rs = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return rs;
	}
	
	// 내 주소지 기본 주소지로 변경 및 등록
	@RequestMapping("/myaddress/defaultregist")
	@ResponseBody
	public ResponseEntity<String> defaultAddressRegist(HttpServletRequest request,MemberAddressVO memberAdd){
		ResponseEntity<String> rs = null;
		
		HttpSession session = request.getSession();
		MemberVO mv = (MemberVO) session.getAttribute("loginUser");
		
		memberAdd.setMemberNo(mv.getMemberNo());
		
		try {
			memberAddressService.defaultAddressRegist(memberAdd);
			rs = new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			rs = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return rs;
	}
	
	// 내 주소지 추가 등록
	@RequestMapping("/myaddress/regist")
	@ResponseBody
	public ResponseEntity<String> addAddressRegist(HttpServletRequest request,MemberAddressVO memberAdd){
		ResponseEntity<String> rs = null;
		
		HttpSession session = request.getSession();
		MemberVO mv = (MemberVO) session.getAttribute("loginUser");
		
		memberAdd.setMemberNo(mv.getMemberNo());
		
		try {
			memberAddressService.addAddressRegist(memberAdd);
			rs = new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			rs = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return rs;
	}
	
	
	/* ***********************************  여기서 부터 주문 내역  *********************************** */
	@RequestMapping("/myorder/histroy/main")
	public ModelAndView myorder(HttpServletRequest request, ModelAndView mnv,MyOrderCriteria cri) throws Exception {
		String url = "/mypage/order_history";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		LaundryOrderVO laundryOrder = new LaundryOrderVO();
		
		// 세션을 통해 고객번호 받아오기!
		laundryOrder.setMemberNo(loginUser.getMemberNo());
		cri.setMemberNo(loginUser.getMemberNo());
		
		Map<String, Object> dataMap = laundryOrderService.getMyOrderList(cri);
		
		
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	// 주문내역 디테일
	@RequestMapping("/order_detail")
	public ModelAndView orderDetail(HttpServletRequest request,ModelAndView mnv,AttachVO attach,String orderNo) throws Exception {
		String url = "/mypage/order_detail";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		Map<String, Object> dataMap = laundryOrderService.getDetail(orderNo);
		
		mnv.addObject("loginUser",loginUser);
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		return mnv;
	}
	
	// 댓글 수정
	@RequestMapping("/modifyReply")
	public ResponseEntity<String> modifyReply(ReplyVO rv) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			replyService.modifyReply(rv);
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);

		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	// 요청사항 수정
	@RequestMapping("/modifyReq")
	public ResponseEntity<String> modifyReq(LaundryOrderVO laundryOrder) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			laundryOrderService.modifyReq(laundryOrder);
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);
			
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	// 댓글 삭제(삭제여부 상태변경)
	@RequestMapping("/removeReply")
	public ResponseEntity<String> removeReply(ReplyVO rv) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			replyService.removeReply(rv);
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);

		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	// 댓글 삭제(삭제여부 상태변경)
	@RequestMapping("/reqRemove")
	public ResponseEntity<String> reqRemove(String orderNo) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			laundryOrderService.removeReq(orderNo);
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);

		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	// 진행중인 주문내역
	@RequestMapping("/myorder/histroy/ingList")
	public ModelAndView myorderIng(HttpServletRequest request, ModelAndView mnv,MyOrderCriteria cri) throws Exception {
		String url = "/mypage/order_history_ing";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		LaundryOrderVO laundryOrder = new LaundryOrderVO();
		
		// 세션을 통해 고객번호 받아오기!
		laundryOrder.setMemberNo(loginUser.getMemberNo());
		cri.setMemberNo(loginUser.getMemberNo());
		
		Map<String, Object> dataMap = laundryOrderService.getMyOrderIngList(cri);
		
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	// 배송완료된 주문내역
	@RequestMapping("/myorder/histroy/complete")
	public ModelAndView myCompleteorder(HttpServletRequest request, ModelAndView mnv,MyOrderCriteria cri) throws Exception {
		String url = "/mypage/order_Complete_history";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		LaundryOrderVO laundryOrder = new LaundryOrderVO();
		
		// 세션을 통해 고객번호 받아오기!
		laundryOrder.setMemberNo(loginUser.getMemberNo());
		cri.setMemberNo(loginUser.getMemberNo());
		
		Map<String, Object> dataMap = laundryOrderService.getMyCompleteOrderList(cri);
		
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	// 결제내역
	@RequestMapping("/myorder/histroy/comlist")
	public ModelAndView myComOrder(HttpServletRequest request, ModelAndView mnv,MyOrderCriteria cri) throws Exception {
		String url = "/mypage/order_complete_list";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		// 세션을 통해 고객번호 받아오기!
		cri.setMemberNo(loginUser.getMemberNo());
		
		Map<String, Object> dataMap = paymentService.getComList(cri);
		
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	// 취소내역
	@RequestMapping("/myorder/histroy/cnacellist")
	public ModelAndView myCancelOrder(HttpServletRequest request, ModelAndView mnv,MyOrderCriteria cri) throws Exception {
		String url = "/mypage/order_cancel_list";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		// 세션을 통해 고객번호 받아오기!
		cri.setMemberNo(loginUser.getMemberNo());
		
		Map<String, Object> dataMap = paymentService.getCancelList(cri);
		
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	// 요청사항 댓글 달기
	@RequestMapping("/reply")
	public ResponseEntity<String> reply(ReplyVO rv,String no,HttpSession session) throws Exception {
		ResponseEntity<String> entity = null;
		
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		rv.setMemberno(loginUser.getMemberNo());
		rv.setReplyno(Integer.parseInt(no));
		try {
			replyService.insertMypageRe(rv);
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);

		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


		return entity;
	}
	
	// 주문취소
	@RequestMapping("/cancelOrder")
	public ResponseEntity<String> cancelOrder(LaundryOrderVO laundryOrder) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			laundryOrderService.cancelOrder(laundryOrder);
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);

		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	// 사진가져오기
	@RequestMapping(value = "/getPicture", produces = "text/plain;charset=utf-8")
	public ResponseEntity<byte[]> getPicture(AttachVO atch) throws Exception {
		
		FileUtil fileUtil = new FileUtil();
		ResponseEntity<List<byte[]>> en = fileUtil.getPicture(atch, attachService);
		List<byte[]> bs =en.getBody();
		byte[] file = bs.get(0);
		
		ResponseEntity<byte[]> entity = null;
		entity = new ResponseEntity<byte[]>(file, HttpStatus.CREATED);
		return entity;
	}
	

}
