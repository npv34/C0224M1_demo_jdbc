<%@ page import="java.util.List" %>

<%@ page import="org.codegym.library.entity.Book" %>
<%@ page import="org.codegym.library.entity.Category" %><%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 7/26/24
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<% List<Book> books = (List<Book>) request.getAttribute("books");
    int limit = request.getParameter("limit") != null ? Integer.parseInt(request.getParameter("limit")) : 2;
    int totalBook = (int) request.getAttribute("totalBooks");
    int totalPage = (int) (double) (totalBook / limit);
    int currentPage = request.getParameter("page")!= null? Integer.parseInt(request.getParameter("page")) : 1;
%>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            width: 700px;
            border-collapse: collapse;
        }

        tr, td {
            border: 1px solid black;
            padding: 8px;
        }
        .btn-delete{
            color: red;
            text-decoration: none;
            cursor: pointer;
        }

        .btn-create {
            background-color: green;
            color: white;
            text-align: center;
        }
    </style>
</head>
<body>
<h2>Book list</h2>
<a href="/books/create" class="btn-create">Create</a>

<form action="/books/search" method="get">
    <input type="text" name="keyword">
    <input type="submit" value="Search">
</form>
<% for (int i = 1; i <= totalPage ; i++) { %>
<a href="/books?page=<%= i %>&limit=<%= limit%>"><%= i %></a>
<% } %>
<a href="/books?page=<%= currentPage + 1 %>&limit=<%= limit%>">Next</a>
<table>
    <tr>
        <td>#</td>
        <td>Name</td>
        <td>Description</td>
        <td>Price</td>
        <td>Category</td>
        <td></td>
    </tr>
    <c:set var="index" value="1"/>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${index}</td>
            <td><c:out value="${book.name}"/></td>
            <td><c:out value="${book.description}"/></td>
            <td><c:out value="${book.price}"/></td>
            <td><c:out value="${book.category.name}"/></td>
            <td><a class="btn-delete" onclick="return confirm('Are you want delete?')" href="/books/delete?id=<c:out value="${book.id}"/>">Delete</a></td>
            <td><a class="btn-update" href="/books/update?id=<c:out value="${book.id}"/>">Update</a></td>
        </tr>
        <c:set var="index" value="${index + 1}"/>
    </c:forEach>
</table>
<h3>Total books: <c:out value="${books.size()}"/></h3>
</body>
</html>
