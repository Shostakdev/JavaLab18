package inventorymanager.servlets;

import inventorymanager.models.Customer;
import inventorymanager.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        List<Customer> customers = JsonUtils.getCustomers();
        Customer customer = new Customer(customers.size() + 1, name, email);
        customers.add(customer);
        JsonUtils.saveCustomers(customers);

        resp.sendRedirect("index.jsp");
    }
}
