<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:page title="${userProfile.nick } profile!">
		<div class="article">
				<c:out value="<h3>${userProfile.nick }</h3>" />
				<c:out value="Team:${userProfile.team }<br />" />
				<c:out value="Email:${userProfile.email }<br />" />
				
			Owned Events:<br />	
			<c:forEach items="${userProfile.ownEvents}" var="event" varStatus="index">
				<a href="./event-${event.id}">${index.index + 1}. <c:out value="${event.name}" /></a><br />
			</c:forEach>
			<c:if test="${!(empty userProfile.signedEvents)}" >
			Incoming Event:<br />
				<c:forEach items="${userProfile.signedEvents}" var="event" varStatus="index">
					<a href="./event-${event.id}">${index.index + 1}. <c:out value="${event.name}" /></a><br />
				</c:forEach>
			</c:if>
		</div>
</t:page>	