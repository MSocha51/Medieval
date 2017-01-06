<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:page title="${medievalEvent.name }">
		<div class="article">
			<fmt:formatDate value="${medievalEvent.startDate }" type="date" pattern="dd-MM-yyyy"
				var="eventDate"/>
				<h3>${medievalEvent.name }</h3>
				
				<p>${medievalEvent.description }</p>
				
				Start:${eventDate}
				<br />
				<h2>${vacanices}</h2>
				<div id="sing" class="form">
					<sec:authorize access="isAuthenticated()">
						<form:form action="event-details-${medievalEvent.id}/sing" method="post">
					 		<input type="submit" value="Sing for this Event"/>
						</form:form>	
					</sec:authorize>
				</div>	
			<c:forEach items="${medievalEvent.participants }" var="participant" varStatus="index">
				<p>${index.index + 1}. ${participant.nick} from ${participant.team}</p>
			</c:forEach>
		</div>
</t:page>	