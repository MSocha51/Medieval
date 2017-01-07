<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:page title="${medievalEvent.name }">
		<div class="article">
			<fmt:formatDate value="${medievalEvent.startDate }" type="date" pattern="dd-MM-yyyy"
				var="eventDate"/>
				<h3>${medievalEvent.name }</h3>
				
				<p>${medievalEvent.description }</p>
				
				Start:${eventDate}
				<br />
				<h2>${eventFullMsg}</h2>
				<div id="sing" class="form">
					<sec:authorize access="isAuthenticated()">
						<spring:url value="/event-details-${medievalEvent.id }/sing" var="url" />
						<form:form action="${url}" method="post">
					 		<input type="submit" value="Sing for this Event"/>
						</form:form>
					</sec:authorize>
					<c:set var="status" value="${medievalEvent.accepted}"/>
					<c:if test="${false == status}">
						<sec:authorize access="hasAnyRole('ROLE_MOD','ROLE_ADMIN')">									
							<form:form id="accept-${medievalEvent.id}" action="./accept-${medievalEvent.id}" method="POST" />
								<input class="button" type="submit" value="Accept Event" form="accept-${medievalEvent.id}" />						
						</sec:authorize>
					</c:if>
				</div>	
			<c:forEach items="${medievalEvent.participants }" var="participant" varStatus="index">
				<p>${index.index + 1}. ${participant.nick} from ${participant.team}</p>
			</c:forEach>
			
		</div>
</t:page>	