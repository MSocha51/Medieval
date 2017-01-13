<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<t:page title="${medievalEvent.name }">
	<div class="article">
		<fmt:formatDate value="${medievalEvent.startDate }" type="date"
			pattern="dd-MM-yyyy" var="eventDate" />
		<h3>${medievalEvent.name }</h3>

		<p>${medievalEvent.description }</p>

		Start:${eventDate} <br />
		<h2>${eventFullMsg}</h2>
		<div id="sing" class="form">
			<spring:url value="/event-details-${medievalEvent.id }" var="url" />
			<sec:authorize access="isAuthenticated()">
				<form:form action="${url}/sing" method="post">
					<input type="submit" value="Sing for this Event" />
				</form:form>
			</sec:authorize>
			<c:set var="status" value="${medievalEvent.accepted}" />
			<sec:authorize access="hasAnyRole('ROLE_MOD','ROLE_ADMIN')">
				<c:if test="${false == status}">				
					<form:form id="accept-${medievalEvent.id}"
						action="./accept-${medievalEvent.id}" method="POST" />
					<input class="button" type="submit" value="Accept Event"
						form="accept-${medievalEvent.id}" />				
				</c:if>		
			</sec:authorize>
			<c:if test="${ifOwnerOrMod}">
				<form:form id="edit-${medievalEvent.id}" action="${url }/edit"  method = "GET" />
				<input class="button" type="submit" value="Edit Event"
					form="edit-${medievalEvent.id}" />
				<form:form id="${url }/delete"
						action="${url }/delete" method="POST" />
				<input class="button" type="submit" value="Delete event"
					form="${url }/delete" />
			</c:if>
		</div>
		<c:forEach items="${medievalEvent.participants }" var="participant"
			varStatus="index">
			<p>${index.index + 1}. ${participant.nick} from ${participant.team}</p>
		</c:forEach>

	</div>
</t:page>
