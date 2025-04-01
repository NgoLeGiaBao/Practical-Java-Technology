package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.utils.HibernateUtils;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();
    private HibernateUtils hibernateUtils;

    public RegisterServlet() {
    }

    public RegisterServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String userName = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        try {
            if (isEmailRegistered(email, req, resp)) {
                return;
            }

            if (validateInputs(userName, email, password, confirmPassword, req, resp)) {
                return;
            }

            User newUser = new User(userName, password, email);
            userDAO.save(newUser);

            HttpSession session = req.getSession();
            session.setAttribute("USERNAME", userName);
            req.setAttribute("message", "Register successfully");
            req.getRequestDispatcher("login.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("error", "An error occurred during registration. Please try again.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }

    private boolean isEmailRegistered(String email, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userDAO.findByEmail(email);
        if (user != null) {
            req.setAttribute("error", "This email has been registered, please try another.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return true;
        }
        return false;
    }

    private boolean validateInputs(String userName, String email, String password, String confirmPassword, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (userName == null || userName.trim().isEmpty()) {
            req.setAttribute("error", "Please fill in this field: username");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return true;
        }

        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("error", "Please fill in this field: email");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return true;
        }

        if (password == null || password.trim().isEmpty()) {
            req.setAttribute("error", "Please fill in this field: password");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return true;
        }

        if (password.length() < 6) {
            req.setAttribute("error", "Password must have at least 6 characters.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return true;
        }

        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Passwords do not match.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return true;
        }

        return false;
    }



    @Override
    public void init() throws ServletException {
        super.init();
    }
}
