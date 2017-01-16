<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:page title="Calendar">
	<c:if test="${empty eventsDates }"><h1>There are no events</h1></c:if>
	<c:forEach items="${eventsDates}" var="eventDate" varStatus="index">
		<fmt:formatDate value="${eventDate }" type="date" pattern="dd-MM-yyyy" var="date"/>
		<a href='<c:url value="events-list-date-${date}"/>'>${date }</a>
	</c:forEach>
</t:page>

