<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:page title="Add your event">
	<div id="add-event" class="form">
		<form:form action="add" method="POST" modelAttribute="addEventsDTO">

			<form:input path="name" placeholder="Event name" />
			<form:errors path="name" cssClass="error" element="div" />

			<form:input path="promoter" placeholder="Promoter name" />
			<form:errors path="promoter" cssClass="error" element="div" />

			<form:textarea path="shortDescription" rows="2"
				placeholder="Short description of your event" />
			<form:errors path="shortDescription" cssClass="error" element="div" />

			<form:textarea path="description" rows="10"
				placeholder="Full description" />
			<form:errors path="description" cssClass="error" element="div" />
			<br />
			<div class="input-conteiner">
				Event start date:<br />
				<form:input path="startDate" type="date" style="width:250px;" />
				<form:errors path="startDate" cssClass="error" element="div" />
			</div>
			<div class="input-conteiner">
				Number of participants:<br />
				<form:input path="maxParticipants" style="width:50px;" />
				<form:errors path="maxParticipants" cssClass="error" element="div" />
			</div>
			<div style="clear: both"></div>
			<input type="submit" value="Add Event!" />

		</form:form>
	</div>
</t:page>
