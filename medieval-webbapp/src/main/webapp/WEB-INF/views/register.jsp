<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<t:page title="Login">
	<div id="login" class="form">
		<form:form action="login" method="post">
			<input type="text" name="nick" placeholder="Nick" /><br/>
   			<input type="password" name="password" placeholder="Password"/><br/>
			<input type="submit" value="Login!" />
		</form:form>
	</div>
	<div id="register" class="form">
		<form:form action="register" method="POST" modelAttribute="registerDTO">
			<form:input  path="nick" placeholder="Nick"/>
			<form:errors path="nick" cssClass="error" element="div"/>
			<br/>			
			<form:input  path="email" placeholder="E-mail"/>
			<form:errors path="email" cssClass="error" element="div"/>
			<br/>			
			<form:input  path="team" placeholder="Your Team"/>
			<form:errors path="team" cssClass="error" element="div"/>
			<br/>							
			<form:password path="password" placeholder="Password"/>
			<form:errors path="password" cssClass="error" element="div"/>
			<br/>			
			<form:password path="passwordRepetition" placeholder="Repeat your password"/>
			<form:errors path="passwordRepetition" cssClass="error" element="div"/>
			<br/>	
			
			<input type="submit" value="Register!" />
		</form:form>
	</div>
</t:page>
		 