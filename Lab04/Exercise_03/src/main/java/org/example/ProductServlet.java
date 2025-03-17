package org.example;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ProductService")
public class ProductServlet extends HttpServlet {
    private List<Product> productList = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void init() throws ServletException {
        productList.add(new Product(nextId++, "Iphone 16", 100000));
        productList.add(new Product(nextId++, "Samsung galaxy", 20000));
        productList.add(new Product(nextId++, "Ipad NoPro", 300000));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        String idParam = request.getParameter("id");
        ResponseMessage responseMessage;

        if (idParam == null || idParam.isEmpty()) {
            responseMessage = new ResponseMessage(0, "Danh sách sản phẩm", productList);
        } else {
            try {
                int id = Integer.parseInt(idParam);
                Product foundProduct = null;

                for (Product product : productList) {
                    if (product.getId() == id) {
                        foundProduct = product;
                        break;
                    }
                }

                if (foundProduct != null) {
                    responseMessage = new ResponseMessage(0, "Đọc sản phẩm thành công", foundProduct);
                } else {
                    responseMessage = new ResponseMessage(1, "Không tìm thấy sản phẩm với ID: " + id, null);
                }
            } catch (NumberFormatException e) {
                responseMessage = new ResponseMessage(1, "ID phải là giá trị số", null);
            }
        }

        out.print(gson.toJson(responseMessage));
        out.flush();
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String priceParam = request.getParameter("price");
        ResponseMessage responseMessage;

        if (idParam == null || name == null || priceParam == null) {
            responseMessage = new ResponseMessage(1, "Thiếu thông tin: id, name, và price là bắt buộc", null);
        } else {
            try {
                int id = Integer.parseInt(idParam);
                double price = Double.parseDouble(priceParam);

                boolean idExists = productList.stream().anyMatch(product -> product.getId() == id);

                if (idExists) {
                    responseMessage = new ResponseMessage(1, "ID đã tồn tại, vui lòng chọn ID khác", null);
                } else {
                    Product newProduct = new Product(id, name, price);
                    productList.add(newProduct);

                    responseMessage = new ResponseMessage(0, "Thêm sản phẩm thành công", newProduct);
                }
            } catch (NumberFormatException e) {
                responseMessage = new ResponseMessage(1, "ID và Price phải là giá trị số", null);
            }
        }

        out.print(gson.toJson(responseMessage));
        out.flush();
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String priceParam = request.getParameter("price");
        ResponseMessage responseMessage;

        if (idParam == null || name == null || priceParam == null) {
            responseMessage = new ResponseMessage(1, "Thiếu thông tin: id, name, và price là bắt buộc", null);
        } else {
            try {
                int id = Integer.parseInt(idParam);
                double price = Double.parseDouble(priceParam);

                Product foundProduct = null;
                for (Product product : productList) {
                    if (product.getId() == id) {
                        foundProduct = product;
                        break;
                    }
                }

                if (foundProduct != null) {
                    foundProduct.setName(name);
                    foundProduct.setPrice(price);

                    responseMessage = new ResponseMessage(0, "Cập nhật sản phẩm thành công", foundProduct);
                } else {
                    responseMessage = new ResponseMessage(1, "Không tìm thấy sản phẩm với ID: " + id, null);
                }
            } catch (NumberFormatException e) {
                responseMessage = new ResponseMessage(1, "ID và Price phải là giá trị số", null);
            }
        }

        out.print(gson.toJson(responseMessage));
        out.flush();
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        String idParam = request.getParameter("id");
        ResponseMessage responseMessage;

        if (idParam == null) {
            responseMessage = new ResponseMessage(1, "Thiếu thông tin: id là bắt buộc", null);
        } else {
            try {
                int id = Integer.parseInt(idParam);

                boolean removed = productList.removeIf(product -> product.getId() == id);

                if (removed) {
                    responseMessage = new ResponseMessage(0, "Xóa sản phẩm thành công", null);
                } else {
                    responseMessage = new ResponseMessage(1, "Không tìm thấy sản phẩm với ID: " + id, null);
                }
            } catch (NumberFormatException e) {
                responseMessage = new ResponseMessage(1, "ID phải là giá trị số", null);
            }
        }

        out.print(gson.toJson(responseMessage));
        out.flush();
    }


    class ResponseMessage {
        private int code;
        private String message;
        private Object data;

        public ResponseMessage(int code, String message, Object data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }
    }
}
