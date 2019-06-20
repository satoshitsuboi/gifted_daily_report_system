<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>出退勤登録一覧</h2>
<c:if test="${errors != null}">
 <div id="flush_errors">
 <c:forEach var="errors" items="${errors}">
            ・<c:out value="${errors}" /><br />
        </c:forEach>

    </div>
</c:if>
<c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>





<p><a href="<c:url value='/attendances/index' />">勤怠一覧を見る</a></p>
<br />
<p><a href="<c:url value='/index.html' />">トップに戻る</a></p>
