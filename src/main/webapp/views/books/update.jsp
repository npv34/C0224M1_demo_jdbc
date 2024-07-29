<%@ page import="org.codegym.library.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="org.codegym.library.entity.Book" %><%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 7/29/24
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<% List<Category> categories = (List<Category>) request.getAttribute("categories"); %>
<% Book book = (Book) request.getAttribute("book"); %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Page update book</h2>
<form action="/books/update?id=${book.id}" method="post">
    <label for="name">Name:</label><br>
    <input type="text" id="name" value="<c:out value="${book.name}"/>" name="name"><br>
    <label for="description">Description:</label><br>
    <textarea id="description" name="description" rows="4" cols="50">
        <c:out value="${book.description}"/>
    </textarea><br>
    <label for="price">Price:</label><br>
    <input type="number" value="<c:out value="${book.price}"/>" id="price" name="price"><br>
    <label for="category_id">Category:</label>
    <select id="category_id" name="category_id">
        <c:forEach var="category" items="${categories}">
            <option <c:out value="${book.category_id == category.id ? 'selected' : ''}"/> value="<c:out value="${category.id}"/>"><c:out value="${category.name}"/></option>

<%--            <c:if test="${book.category_id == category.id}">--%>
<%--                <option selected value="<c:out value="${category.id}"/>"><c:out value="${category.name}"/></option>--%>
<%--            </c:if>--%>
<%--            <c:if test="${book.category_id != category.id}">--%>
<%--                <option value="<c:out value="${category.id}"/>"><c:out value="${category.name}"/></option>--%>
<%--            </c:if>--%>
        </c:forEach>
    </select>
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
