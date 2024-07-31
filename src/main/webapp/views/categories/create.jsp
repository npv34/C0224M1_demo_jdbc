<%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 7/31/24
  Time: 8:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Create category</h2>
<form action="/categories/create" method="post">
    Name:
    <input type="text" name="name"><br/>
    <input type="submit" value="Create">
    <a href="/categories">Back to list</a>
</form>
</body>
</html>
