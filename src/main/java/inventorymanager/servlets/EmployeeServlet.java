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
import java.util.List;

public class EmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = JsonUtils.getProducts();
        List<Order> orders = JsonUtils.getOrders();
        req.setAttribute("products", products);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/employee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("addProduct".equals(action)) {
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            List<Product> products = JsonUtils.getProducts();
            Product product = new Product(products.size() + 1, name, price, quantity);
            products.add(product);
            JsonUtils.saveProducts(products);

            resp.sendRedirect("employee");
        } else if ("shipOrder".equals(action)) {
            int orderId = Integer.parseInt(req.getParameter("orderId"));

            List<Order> orders = JsonUtils.getOrders();
            orders.removeIf(order -> order.getId() == orderId);
            JsonUtils.saveOrders(orders);

            resp.sendRedirect("employee");
        }
    }
}
