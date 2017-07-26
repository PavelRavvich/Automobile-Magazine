<%--
  Author: Pavel Ravvich.
  Date: 24.07.17.
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
<h2>All Proposes: </h2><br>

    <form method="get" action="get_all_proposes/get_propose_by_id">
        <c:forEach var="propose" items="${requestScope.allProposes}">

            <ul>

                <li>Model: <c:out value="${propose.model}"/></li>
                <li>Mark: <c:out value="${propose.mark}"/></li>
                <li>Sold: <c:out value="${propose.sold}"/></li>
                <input name="pId" hidden type="number" value="${propose.id}">
                <input type="submit" value="More">
                <hr>

            </ul>

        </c:forEach>
    </form>

</body>
</html>
