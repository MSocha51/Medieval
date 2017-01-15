<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:page title="List of users">
	<c:if test="${empty listOfUsers }"><h1>There are no users events</h1></c:if>
	<c:forEach items="${listOfUsers}" var="userFor" varStatus="index">
	<a href='<c:url value="profile-${userFor.id }"/>'><c:out value="${userFor.nick }-${userFor.email }" /></a
	> <a  href='<c:url value="profile-${userFor.id }/delete"/>'>Delete</a
	> <c:if test='${userFor.role.role == "ROLE_USER" }'><a  href='<c:url value="profile-${userFor.id }/promote"/>'>Promote</a></c:if><br />
	</c:forEach>
</t:page>
