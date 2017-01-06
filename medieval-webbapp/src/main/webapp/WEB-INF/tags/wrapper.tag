<%@ tag description="Top structure layout" language="java"
	pageEncoding="UTF-8"%>
<%@attribute name="topPage" fragment="true"%>
<%@attribute name="downPage" fragment="true"%>
<body>
	<div id="conteiner">
		<jsp:invoke fragment="topPage" />

		<jsp:doBody />

		<jsp:invoke fragment="downPage" />
	</div>
</body>
