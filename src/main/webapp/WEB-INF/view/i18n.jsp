<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: antro
  Date: 06.05.2018
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<c:set var="language" scope="session" value="${empty sessionScope.lang ? 'en' : sessionScope.lang}" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="/i18n/messages" var="bundle" scope="session"/>
