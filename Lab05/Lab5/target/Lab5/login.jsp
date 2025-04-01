
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<% Cookie[] cookies=request.getCookies();
boolean login= session.getAttribute("ID") != null;
    if(!login && cookies!=null){
    for(Cookie cookie:cookies){
        if(cookie.getName().equals("ID")){
            login=true;
            break;
        }
    }
}
if(login){
    response.sendRedirect("/product");
}
%>


<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <h3 class="text-center text-secondary mt-5 mb-3">User Login</h3>
            <div class="form-group text-center ">
<%--                <div class="alert-dismissible fade show">--%>
<%--                    <% if (request.getAttribute("error") != null) { %>--%>
<%--                    <div style="text-align: center; color: green"  class="error">--%>
<%--                        <%= request.getAttribute("error") %>--%>
<%--                    </div>--%>
<%--                    <% } %></div>--%>
            </div>
            <form action="login" method="post" class="border rounded w-100 mb-5 mx-auto px-3 pt-3 bg-light">
                <div class="form-group">
                    <label for="username">Email</label>
                    <input id="username" name="email" type="text" class="form-control" placeholder="email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input id="password" name="password" type="password" class="form-control" placeholder="Password">
                </div>
                <!-- Checkbox for remember me -->
                <div class="form-check mb-3">
                    <input class="form-check-input" type="checkbox" id="remember" name="remember">
                    <label class="form-check-label" for="remember">
                        Remember me
                    </label>
                </div>
                <div class="form-group">
                    <button type="submit" value="Submit" class="btn btn-success px-5">Login</button>
                </div>

                <div class="form-group">
                    <p>Forgot password? <a href="register">Click here</a></p>
                </div>
                <div class="form-group">
                    <p>Don't have account? <a href="register">Click here</a></p>
                </div>

            </form>

        </div>
    </div>
</div>

</body>
</html>
