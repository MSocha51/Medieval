<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:page title="Login">
	<div id="login" class="form">
		<form:form action="login" method="post">
			${error }<br/>
			${message }<br/>
			<input type="text" name="nick" /><br/>
   			<input type="password" name="password" /><br/>
			<input type="submit" value="Login!" />
		</form:form>
	</div>
</t:page>
