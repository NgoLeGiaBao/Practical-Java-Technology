package org.example.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.dao.UserDAO;
import org.example.model.User;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;
    public LoginServlet() {
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String remember = req.getParameter("remember");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            User user = userDAO.findByEmail(email);
            if (user == null) {
                handleUserNotFound(req, resp);
            } else if (isValidPassword(user, password)) {
                handleSuccessfulLogin(req, resp, user, remember);
            } else {
                handleInvalidPassword(req, resp);
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
            req.setAttribute("error", "An error occurred, please try again.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    private boolean isValidPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    private void handleUserNotFound(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", "User not found, please register.");
        RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
        dispatcher.forward(req, resp);
    }

    private void handleInvalidPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", "Incorrect password, please try again.");
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    private void handleSuccessfulLogin(HttpServletRequest req, HttpServletResponse resp, User user, String remember) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("USERNAME", user.getUsername());
        session.setAttribute("ID", user.getID());
        req.setAttribute("username", user.getUsername());

        if (remember != null) {
            addRememberMeCookie(resp, user);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }

    private void addRememberMeCookie(HttpServletResponse resp, User user) {
        Cookie cookie = new Cookie("ID", String.valueOf(user.getID()));
        cookie.setMaxAge(30 * 60 * 60 * 24);
        resp.addCookie(cookie);
    }


    @Override
    public void init() throws ServletException {
        super.init();
        this.userDAO=new UserDAO();
    }
}
