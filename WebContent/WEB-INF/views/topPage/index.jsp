<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>勤怠管理システムへようこそ</h2>




<form method="POST" action="<c:url value='/attendances/new' />">
<button class="start_workbutton" type="submit" name="buttontype" value="start_work" >出勤</button>
</form>
<br /><br />

<form method="POST" action="<c:url value='/attendances/new' />">
<button class="finsh_workbutton" type="submit" name="buttontype" value="finsh_work" >退勤</button>
</form>
<br /><br />


<a href= "<c:url value='/reports/new' />">新規日報の登録</a>


    </c:param>
    </c:import>