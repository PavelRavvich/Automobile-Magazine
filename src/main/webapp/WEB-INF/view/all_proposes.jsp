<%--
  Author: Pavel Ravvich.
  Date: 24.07.17.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
    <script type="text/javascript">
        <%@include file="/WEB-INF/js/propose_filter.js" %>
    </script>

    <%--Jquery connection--%>

</head>
<body>

<h2>All Proposes: </h2><br>

<form method="get" action="get_all_proposes/add_propose">
    <input type="submit" value="Add propose">
</form>
<hr>

<form method="get" action="get_all_proposes/select_propose_by_markmodel">

    <div>
        <select id="mark" name="mark" data-url="get_all_proposes/get_all_marks">
            <option value="">Select mark</option>
        </select>
    </div>

    <div>
        <select id="model" name="model" data-url="get_all_proposes/get_model_by_mark">
            <option value="">Select model</option>
        </select>
    </div>

    <button type="submit">Select</button>

</form>

<hr>

    <c:forEach var="propose" items="${requestScope.allProposes}">

        <ul>

            <li>Model: <c:out value="${propose.model}"/></li>
            <li>Mark: <c:out value="${propose.mark}"/></li>
            <li>Sold: <c:out value="${propose.sold}"/></li>
            <input name="pId" hidden type="number" value="${propose.id}">
            <a href="/get_all_proposes/get_propose_by_id?pId=${propose.id}">More</a>
            <hr>

        </ul>

    </c:forEach>

</body>
</html>
