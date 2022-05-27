<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="sidebar">
            <!-- Brand Logo -->
            <a href="<%=request.getContextPath() %>/admin/index" class="brand-link">
                <img src="<%=request.getContextPath() %>/resources/image/Favicon6.svg" alt="Rundering Logo"
                    class="brand-image img-circle elevation-3" style="opacity: .8">
                <span class="brand-text"><strong>Rundering</strong></span>
            </a>

            <!-- Sidebar -->
            <div class="sidebar">
                <!-- Sidebar Menu -->
                <nav class="mt-2">
                    <ul class="nav nav-pills nav-sidebar flex-column subMenuList" data-widget="treeview" role="menu" style="font-size: 1.18rem;">
                    <c:forEach items="${key}" var="dataKey" >
						<c:set var="menuList" value="${dataMap[dataKey] }" />
						<c:if test="${menuList[0].menuCode ne 'A000000' }">	
						<li class="nav-item">
                            <a href="javascript:goPage('<%=request.getContextPath()%>${menuList[0].menuUrl}','${menuList[0].menuCode}');" class="nav-link">
                                <i class="nav-icon fas fa-bars"></i>
                              
                                <p> 
                             		      ${menuList[0].menuName }
                             		<c:if test="${fn:length(menuList) > 1}">      
                                     	<i class="right fas fa-angle-left"></i>
                                     </c:if>
                                </p>
                            </a>
                            <c:if test="${fn:length(menuList) > 1}">
	                            <ul class="nav nav-treeview">
	                            		 <c:forEach var="menu" items="${menuList }" begin="1">
											<li class="nav-item">
												<a	href="javascript:goPage('<%=request.getContextPath()%>${menu.menuUrl}','${menu.menuCode}');"	class="nav-link">
													<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menu.menuName}</p>
												</a>
											</li>
										</c:forEach>  
								</ul>
								</c:if>
                        </li> 
                        </c:if>
					</c:forEach>
                    </ul>
                </nav>
                <!-- /.sidebar-menu -->
            </div>
            <!-- /.sidebar -->
        </aside>