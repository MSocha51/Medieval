<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="title" required="true"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<title>${title }</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href='<c:url value="./resources/css/style.css"/>'>
<link
	href="https://fonts.googleapis.com/css?family=Cormorant+Garamond:500,700&subset=latin-ext"
	rel="stylesheet">
</head>
<t:wrapper>
	<jsp:attribute name="topPage">
		<div id="header">Your first medieval calendar</div>

		<div id="menu">
			<div class="menu-item">
				<a href='<c:url value="./list"/>'>List of events</a>
			</div>			
			<div class="menu-item">
				<a href='<c:url value="./calendar"/>'>Calendar</a>
			</div>
			<div class="menu-item">
				<a href='<c:url value="./about-me"/>'>About project</a>
			</div>
			<sec:authorize access="isAuthenticated()">
				<div class="menu-item">
					<a href='<c:url value="./add"/>'>Add new event</a>
				</div>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('ROLE_MOD','ROLE_ADMIN')">
				<div class="menu-item">
					<a href='<c:url value="./notAcceptedEvent"/>'>Add new event</a>
				</div>
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<div class="menu-item" id="register-item">
					<a href='<c:url value="./register"/>'>Sing in</a>
				</div>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<div class="menu-item" id="hello-item">
					Hello ${user.nick}
				</div>
			</sec:authorize>
			
			<div style="clear: both"></div>
			
		</div>
		
		<div id="topbar">
			<div id="topbar-image">
				<img alt="Logo" src='<c:url value="./resources/img/logo.png"/>'>
			</div>
			<div id="topbar-info"></div>
		</div>
	</jsp:attribute>
	<jsp:attribute name="downPage">
		<div id="footer">Copyright by Mateusz Socha "Sumarlidi" 2016</div>	
	</jsp:attribute>
	<jsp:body>
		<div id="list-and-content">
			<div id="list">
				<c:if test="${empty listOfEvents }">There are no incoming events</c:if>
				<c:forEach varStatus="status" var="event" items="${listOfEvents }"
					end="25">
					<a href='<c:url value="event-details-${event.id}"/>'>${status.index + 1}. ${event.name }</a>
					<br />  
				</c:forEach>			
			</div>
			<div id="content">
				<jsp:doBody />
			</div>
			<div style="clear: both"></div>
		</div>
	</jsp:body>
</t:wrapper>
</html>
