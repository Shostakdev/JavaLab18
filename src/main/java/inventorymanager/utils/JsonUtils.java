package inventorymanager.utils;

import inventorymanager.models.Customer;
import inventorymanager.models.Order;
import inventorymanager.models.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String PRODUCTS_FILE = "products.json";
    private static final String ORDERS_FILE = "orders.json";
    private static final String CUSTOMERS_FILE = "customers.json";

    public static List<Product> getProducts() throws IOException {
        return getListFromFile(PRODUCTS_FILE, Product.class);
    }

    public static List<Order> getOrders() throws IOException {
        return getListFromFile(ORDERS_FILE, Order.class);
    }

    public static List<Customer> getCustomers() throws IOException {
        return getListFromFile(CUSTOMERS_FILE, Customer.class);
    }

    public static void saveProducts(List<Product> products) throws IOException {
        saveListToFile(PRODUCTS_FILE, products);
    }

    public static void saveOrders(List<Order> orders) throws IOException {
        saveListToFile(ORDERS_FILE, orders);
    }

    public static void saveCustomers(List<Customer> customers) throws IOException {
        saveListToFile(CUSTOMERS_FILE, customers);
    }

    private static <T> List<T> getListFromFile(String filename, Class<T> clazz) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
            saveListToFile(filename, new ArrayList<>());
        }
        String content = new String(Files.readAllBytes(file.toPath()));
        JSONArray jsonArray = new JSONArray(content);
        List<T> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (clazz == Product.class) {
                list.add(clazz.cast(new Product(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getDouble("price"),
                        jsonObject.getInt("quantity")
                )));
            } else if (clazz == Order.class) {
                list.add(clazz.cast(new Order(
                        jsonObject.getInt("id"),
                        jsonObject.getInt("productId"),
                        jsonObject.getInt("customerId"),
                        jsonObject.getInt("quantity"),
                        new java.util.Date(jsonObject.getLong("orderDate"))
                )));
            } else if (clazz == Customer.class) {
                list.add(clazz.cast(new Customer(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getString("email")
                )));
            }
        }
        return list;
    }

    private static <T> void saveListToFile(String filename, List<T> list) throws IOException {
        JSONArray jsonArray = new JSONArray();
        for (T item : list) {
            if (item instanceof Product) {
                Product product = (Product) item;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", product.getId());
                jsonObject.put("name", product.getName());
                jsonObject.put("price", product.getPrice());
                jsonObject.put("quantity", product.getQuantity());
                jsonArray.put(jsonObject);
            } else if (item instanceof Order) {
                Order order = (Order) item;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", order.getId());
                jsonObject.put("productId", order.getProductId());
                jsonObject.put("customerId", order.getCustomerId());
                jsonObject.put("quantity", order.getQuantity());
                jsonObject.put("orderDate", order.getOrderDate().getTime());
                jsonArray.put(jsonObject);
            } else if (item instanceof Customer) {
                Customer customer = (Customer) item;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", customer.getId());
                jsonObject.put("name", customer.getName());
                jsonObject.put("email", customer.getEmail());
                jsonArray.put(jsonObject);
            }
        }
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(jsonArray.toString());
        }
    }
}
