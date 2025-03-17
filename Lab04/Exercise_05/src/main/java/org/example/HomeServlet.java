package org.example;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");

        if (page == null || page.isEmpty()) {
            response.getWriter().write("Welcome to our website");
        } else {
            switch (page) {
                case "about":
                    request.getRequestDispatcher("about.jsp").forward(request, response);
                    break;
                case "contact":
                    request.getRequestDispatcher("contact.jsp").forward(request, response);
                    break;
                case "help":
                    request.getRequestDispatcher("help.jsp").forward(request, response);
                    break;
                default:
                    response.getWriter().write("Welcome to our website");
                    break;
            }
        }
    }
}
