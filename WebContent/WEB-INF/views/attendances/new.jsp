<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">

<h2>出退勤登録</h2>


 <c:import url="_form.jsp" />
<p><a href="<c:url value='/attendances/index' />">出退勤一覧</a></p>
<br />
<p><a href="<c:url value='/attendances/index' />">トップに戻る</a></p>

</c:param>
</c:import>