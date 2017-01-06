<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:page title="List of events">
	<c:if test="${empty listOfEvents }"><h1>There are no incoming events</h1></c:if>
	<c:forEach items="${events}" var="event" varStatus="index">
	<a href='<c:url value="event-details-${event.id}"/>'>
		<div class="event-item">
			<fmt:formatDate value="${event.startDate }" type="date" pattern="dd-MM-yyyy"
				var="eventDate"/>
				<h2>${event.name }</h2>
				
				<p>${event.shortDescription }</p>
				
				Start:${eventDate}	
		</div>
	</a>
	</c:forEach>
</t:page>
