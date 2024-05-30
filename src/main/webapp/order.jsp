<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Place Order</title>
</head>
<body>
<h1>Place Order</h1>
<form action="order" method="post">
    <label for="productId">Product ID:</label>
    <input type="text" id="productId" name="productId"><br>
    <label for="customerId">Customer ID:</label>
    <input type="text" id="customerId" name="customerId"><br>
    <label for="quantity">Quantity:</label>
    <input type="text" id="quantity" name="quantity"><br>
    <input type="submit" value="Place Order">
</form>
<a href="index.jsp">Home</a>
</body>
</html>
