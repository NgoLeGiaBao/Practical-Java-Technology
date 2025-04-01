<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product list</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #f8f8f8">

<div class="container-fluid p-5">
    <div class="row mb-5">
        <div class="col-md-6">
            <h3>Product Management</h3>
        </div>
        <div class="col-md-6 text-right">
            Xin chào <span class="text-danger"> <%=session.getAttribute("USERNAME") %></span>
            <form class="mt-2" method="get" action="logout">
                <button type="submit" class="btn btn-info">Logout</button>
            </form>
        </div>
    </div>
    <div class="row rounded border p-3">
        <div class="col-md-4">
            <h4 class="text-success">Thêm sản phẩm mới</h4>
            <form action="product" class="mt-3" method="post">
                <div class="form-group">
                    <label for="productName">Tên sản phẩm</label>
                    <input class="form-control" type="text" placeholder="Nhập tên sản phẩm" id="productName"
                           name="productName">
                </div>
                <div class="form-group">
                    <label for="priceProduct">Giá sản phẩm</label>
                    <input class="form-control" type="number" placeholder="Nhập giá bán" id="priceProduct"
                           name="priceProduct">
                </div>
                <div class="form-group">
                    <button value="Submit" type="submit" class="btn btn-success mr-2">Thêm sản phẩm</button>
                </div>
                <div class="alert alert-danger">
                    <% if (request.getAttribute("error") != null) { %>
                    <div style="text-align: center; color: green" class="error">
                        <%= request.getAttribute("error") %>
                    </div>
                    <% } %></div>
            </form>
        </div>
        <div class="col-md-8">
            <h4 class="text-success">Danh sách sản phẩm</h4>
            <p>Chọn một sản phẩm cụ thể để xem chi tiết</p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Product> products = (List<Product>)request.getAttribute("products");
                    int stt = 1;
                    if (products != null) {
                        for (Product product : products) {
                %>
                <tr>
                    <td><%= stt++ %></td>
                    <td><%= product.getName() %></td>
                    <td><%= product.getPrice() %></td>
                    <td>
                        <a href="deleteById?id=<%= product.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này?')">Xóa</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function () {
        const fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>
</body>
</html>
