<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/header.jsp" %>

<div><input name="exception" value="${exception}" type="hidden"/></div>
<div><input name="numberOfPages" value="${numberOfPages}" type="hidden"/></div>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h4 style="color: red">${exception}</h4>
            </br>
        </div>
    </div>
</div>
</body>
</html>

