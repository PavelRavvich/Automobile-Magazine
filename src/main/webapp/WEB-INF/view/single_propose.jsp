<%--
  Author: Pavel Ravvich.
  Date: 26.07.17.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--<style type="">--%>
        <%--<%@include file="" %>--%>
    <%--</style>--%>
    <%--<script type="">--%>
        <%--<%@include file="" %>--%>
    <%--</script>--%>
    <jsp:useBean id="propose" class="ru.pravvich.model.Propose" scope="page" />
</head>
<body>
<h2>Propose: </h2><br>

        <c:set var="propose" value="${requestScope.propose}"/>

        <img alt="img" src="data:image/png;base64,${propose.base64ImageFile}"/>
        <li>Author: <c:out value="${propose.auhtor.login}"/></li>
        <li>Mark: <c:out value="${propose.mark}"/></li>
        <li>Model: <c:out value="${propose.model}"/></li>
        <li>Description: <c:out value="${propose.description}"/></li>
        <form method="get" action="../">
            <input type="submit"  value="All proposes">
        </form>

</body>
</html>
