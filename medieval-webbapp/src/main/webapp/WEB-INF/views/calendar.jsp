<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:page title="Calendar">
	<c:forEach items="${eventsDates}" var="eventDate" varStatus="index">
		<fmt:formatDate value="${eventDate }" type="date" pattern="dd-MM-yyyy" var="date"/>
		<strong><a href='<c:url value="events-list-date-${date}"/>'>${date }</a><br/></strong>
	</c:forEach>
</t:page>

