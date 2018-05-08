<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/header.jsp" %>

<div class="container">
    <form name="input" action="/" method="POST">
        <input type="submit" value="<fmt:message key="sort" bundle="${bundle}"/>">
    </form>
    <div class="row">
        <div class="col-sm-12">
        </div>
    </div>
</div>
</body>
</html>
