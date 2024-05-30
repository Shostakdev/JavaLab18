<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Product</title>
</head>
<body>
<h1>Add New Product</h1>
<form action="employee" method="post">
    <input type="hidden" name="action" value="addProduct">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name"><br>
    <label for="price">Price:</label>
    <input type="text" id="price" name="price"><br>
    <label for="quantity">Quantity:</label>
    <input type="text" id="quantity" name="quantity"><br>
    <input type="submit" value="Add Product">
</form>
<a href="employee">Back to Employee Management</a>
</body>
</html>
