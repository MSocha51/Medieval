<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<t:page title="Add your event">
	<div id="add-event" class="form">
		<spring:url value="/event-details-${medievalEvent.id }/edit" var="url" />
		<form:form action="${url}" method="POST" modelAttribute="editEventDto">

			<form:input path="name" placeholder="Event name"  />
			<form:errors path="name" cssClass="error" element="div" />

			<form:textarea path="shortDescription" rows="2"
				placeholder="Short description of your event" />
			<form:errors path="shortDescription" cssClass="error" element="div" />

			<form:textarea path="description" rows="10"
				placeholder="Full description" />
			<form:errors path="description" cssClass="error" element="div" />

			<input type="submit" value="Add Event!" />

		</form:form>
	</div>
</t:page>
