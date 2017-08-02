<%--
  Author: Pavel Ravvich.
  Date: 23.07.17.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="">
        <%--<%@include file="" %>--%>
    </style>
    <script type="">
        <%--<%@include file="" %>--%>
    </script>
</head>
<body>

    <form method="post" action="/get_all_proposes">

        <input type="text" name="login">
        <input type="password" name="password">
        <input type="submit" value="Submit">

    </form>

<%-- <c:out value="${requestScope.serverAnswer}"/> 
  <c:forEach var="user" items="${requestScope.allUsers}">
      <li>Id: <c:out value="${user.id}"/></li>
  </c:forEach>--%>
</body>
</html>
