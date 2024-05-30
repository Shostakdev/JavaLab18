<%@ page import="inventorymanager.models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="inventorymanager.models.Order" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Page</title>
</head>
<body>
<h1>Employee Management</h1>

<h2>Products</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
    </tr>
    <% List<Product> products = (List<Product>) request.getAttribute("products"); %>
    <% for (Product product : products) { %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getPrice() %></td>
            <td><%= product.getQuantity() %></td>
        </tr>
    <% } %>
</table>
<a href="addProduct.jsp">Add New Product</a>

<h2>Orders</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Product ID</th>
        <th>Customer ID</th>
        <th>Quantity</th>
        <th>Order Date</th>
        <th>Action</th>
    </tr>
    <% List<Order> orders = (List<Order>) request.getAttribute("orders"); %>
    <% for (Order order : orders) { %>
        <tr>
            <td><%= order.getId() %></td>
            <td><%= order.getProductId() %></td>
            <td><%= order.getCustomerId() %></td>
            <td><%= order.getQuantity() %></td>
            <td><%= order.getOrderDate() %></td>
            <td>
                <form action="employee" method="post">
                    <input type="hidden" name="action" value="shipOrder">
                    <input type="hidden" name="orderId" value="${order.id}">
                    <input type="submit" value="Ship Order">
                </form>
            </td>
        </tr>
    <% } %>
</table>
<a href="index.jsp">Home</a>
</body>
</html>
