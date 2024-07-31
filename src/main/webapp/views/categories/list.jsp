<%@ page import="java.util.List" %>
<%@ page import="org.codegym.library.entity.Category" %><%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 7/31/24
  Time: 8:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<% List<Category> categories = (List<Category>) request.getAttribute("categories"); %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>List Category</h2>
<a href="/categories/create">Create</a>
<table>
    <tr>
        <td>#</td>
        <td>Name</td>
        <td></td>
    </tr>
    <c:set var="index" value="1"/>
    <c:forEach items="${categories}" var="category">
        <tr>
            <td><c:out value="${index}"/></td>
            <td><c:out value="${category.name}"/></td>
            <td><a href="/categories/update?id=<c:out value="${category.id}"/>">Update</a> | <a href="/categories/delete?id=<c:out value="${category.id}"/>">Delete</a></td>
        </tr>
        <c:set var="index" value="${index + 1}"/>
    </c:forEach>
</table>
</body>
</html>
