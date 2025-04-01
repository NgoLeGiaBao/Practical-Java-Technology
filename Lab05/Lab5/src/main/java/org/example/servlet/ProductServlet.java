package org.example.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.ProductDAO;
import org.example.model.Product;
import org.example.utils.HibernateUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;
    private HibernateUtils hibernateUtils;

    public ProductServlet() {
    }

    public ProductServlet(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("delete")) {
            handleDeleteProduct(req, resp);
        } else {
            List<Product> productList = productDAO.getAllProducts();
            req.setAttribute("products", productList);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    private void handleDeleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdStr = req.getParameter("id");
        if (productIdStr != null && !productIdStr.isEmpty()) {
            int productId = Integer.parseInt(productIdStr);
            productDAO.deleteProductById(productId); // Gọi phương thức xóa từ DAO
            req.setAttribute("message", "Product deleted successfully.");
        } else {
            req.setAttribute("error", "Invalid product ID.");
        }
        resp.sendRedirect("product"); // Sau khi xóa xong, chuyển hướng về trang danh sách sản phẩm
    }


    @Override
    public void init() throws ServletException {
        this.productDAO = new ProductDAO();
        super.init();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String productName = req.getParameter("productName");
        String priceString = req.getParameter("priceProduct");
        int price = parsePrice(priceString, req, resp);

        if (isInputInvalid(productName, price, req, resp)) {
            return;
        }

        if (isProductExist(productName, req, resp)) {
            return;
        }

        addNewProduct(productName, price, req, resp);
    }

    private int parsePrice(String priceString, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int price = 0;
        if (priceString != null && !priceString.isEmpty()) {
            try {
                price = Integer.parseInt(priceString);
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Invalid price value.");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "Price is required.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        return price;
    }

    private boolean isInputInvalid(String productName, int price, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (productName == null || productName.trim().isEmpty() || price == 0) {
            req.setAttribute("error", "Product name and price are required.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return true;
        }
        return false;
    }

    private boolean isProductExist(String productName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product existingProduct = productDAO.getProductByProductName(productName);
        if (existingProduct != null) {
            req.setAttribute("error", "This product name already exists, please choose another.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return true;
        }
        return false;
    }

    private void addNewProduct(String productName, int price, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setName(productName);
        product.setPrice(price);
        productDAO.add(product);

        req.setAttribute("message", "Product added successfully.");
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }

}
