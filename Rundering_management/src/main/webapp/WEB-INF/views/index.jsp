<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
	if('${loginEmployee.employeeId}'){
		if('${loginEmployee.branchCode}'=='000000'){
			location.href="<%=request.getContextPath() %>/admin/index";
		}else{
			location.href="<%=request.getContextPath() %>/branch/index";
			if('${fn:substring(loginEmployee.employeeId, 0, 2)}' == 'DE'){
				location.href="<%=request.getContextPath() %>/fordelivery/main"
			}
		}
	}else{
		location.href="<%=request.getContextPath() %>/common/loginform.do";
	}
</script>