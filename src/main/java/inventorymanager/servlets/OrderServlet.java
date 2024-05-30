package inventorymanager.servlets;

import inventorymanager.models.Order;
import inventorymanager.models.Product;
import inventorymanager.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        Date orderDate = new Date();

        List<Order> orders = JsonUtils.getOrders();
        Order order = new Order(orders.size() + 1, productId, customerId, quantity, orderDate);
        orders.add(order);
        JsonUtils.saveOrders(orders);

        // Update product quantity
        List<Product> products = JsonUtils.getProducts();
        for (Product product : products) {
            if (product.getId() == productId) {
                product.setQuantity(product.getQuantity() - quantity);
                break;
            }
        }
        JsonUtils.saveProducts(products);

        resp.sendRedirect("orderConfirmation.jsp");
    }
}
