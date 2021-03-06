
<%--
  Created by IntelliJ IDEA.
  User: antro
  Date: 06.05.2018
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h4 style="color: red">${exception}</h4>
            <form action="/" method="POST">
                <div><input name="command" value="registration" type="hidden"/></div>
                <div class="form-group">
                    <label for="firstName"><fmt:message key="firstName" bundle="${bundle}"/></label>
                    <input type="text" class="form-control" id="firstName" name="firstName" required>
                </div>
                <div class="form-group">
                    <label for="lastName"><fmt:message key="lastName" bundle="${bundle}" /></label>
                    <input type="text" class="form-control" id="lastName" name="lastName" required>
                </div>
                <div class="form-group">
                    <label for="email"><fmt:message key="email" bundle="${bundle}" /></label>
                    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" required>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="password" bundle="${bundle}" /></label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="signUp" bundle="${bundle}" /></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
