package org.example;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private HashMap<String, String> userDatabase;

    @Override
    public void init() throws ServletException {
        userDatabase = new HashMap<>();
        userDatabase.put("admin", "admin123");
        userDatabase.put("user", "user123");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            out.println("<html><body>");
            out.println("<h2>Name/Password match</h2>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h2>Name/Password does not match</h2>");
            out.println("</body></html>");
        }
    }
}
