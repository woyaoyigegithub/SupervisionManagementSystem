<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 配置分页标签   -->
<%@ taglib prefix="pager" uri="/pager-tags" %>

<!-- 设置一个项目路径的变量  -->
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:set var="jsp" value="WEB-INF/jsp"></c:set>

