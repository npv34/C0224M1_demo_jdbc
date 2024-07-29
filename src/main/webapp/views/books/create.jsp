<%@ page import="org.codegym.library.entity.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 7/29/24
  Time: 8:16 PM
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
<h2>Page create book</h2>
<form action="/books/create" method="post">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="description">Description:</label><br>
    <textarea id="description" name="description" rows="4" cols="50"></textarea><br>
    <label for="price">Price:</label><br>
    <input type="number" id="price" name="price"><br>
    <label for="category_id">Category:</label>
    <select id="category_id" name="category_id">
        <c:forEach var="category" items="${categories}">
        <option value="<c:out value="${category.id}"/>"><c:out value="${category.name}"/></option>
        </c:forEach>
    </select>
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
