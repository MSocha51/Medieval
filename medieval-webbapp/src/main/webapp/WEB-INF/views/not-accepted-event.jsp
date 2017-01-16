<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:page title="List of unnacepted events">
	<c:if test="${empty notAcceptedListOfEvents}"><h1>There are no unaccepted events</h1></c:if>
	<c:forEach items="${notAcceptedListOfEvents}" var="event" varStatus="index">
	<a href='<c:url value="event-details-${event.id}"/>'>
		<div class="event-item">
			<fmt:formatDate value="${event.startDate }" type="date" pattern="dd-MM-yyyy"
				var="eventDate"/>
				<h2><c:out value="${event.name }"/></h2>
				
				<p><c:out value="${event.shortDescription }"/></p>
				
				<c:out value="Start:${eventDate}"/>
				<br />
				<input class="button" type="submit" value="Accept Event" form="accept-${event.id}" />
		</div>
		</a>
		<form:form id="accept-${event.id}" action="./accept-${event.id}" method="POST" />		
	</c:forEach>
</t:page>
