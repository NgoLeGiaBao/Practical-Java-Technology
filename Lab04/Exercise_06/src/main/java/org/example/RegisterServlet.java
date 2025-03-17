package org.example;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String birthtime = request.getParameter("birthtime");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String[] favoriteIDE = request.getParameterValues("favorite_ide[]");
        String toeic = request.getParameter("toeic");
        String message = request.getParameter("message");

        System.out.println(name);
        System.out.println(email);
        System.out.println(birthday);
        System.out.println(birthtime);
        System.out.println(gender);
        System.out.println(country);
        System.out.println(favoriteIDE);
        System.out.println(toeic);
        System.out.println(message);


        if (name == null || name.isEmpty() || email == null || email.isEmpty() || birthday == null || birthday.isEmpty() ||
                birthtime == null || birthtime.isEmpty() || gender == null || gender.isEmpty() || country == null || country.isEmpty() ||
                favoriteIDE == null || favoriteIDE.length == 0 || toeic == null || toeic.isEmpty() || message == null || message.isEmpty()) {

            out.println("<h1>Missing Information</h1>");
            out.println("<p>Please fill in all required fields.</p>");
        } else {
            out.println("<h1>User Information</h1>");
            out.println("<table border='1'>");
            out.println("<tr><td>Name</td><td>" + name + "</td></tr>");
            out.println("<tr><td>Email</td><td>" + email + "</td></tr>");
            out.println("<tr><td>Birthday</td><td>" + birthday + "</td></tr>");
            out.println("<tr><td>Birthtime</td><td>" + birthtime + "</td></tr>");
            out.println("<tr><td>Gender</td><td>" + gender + "</td></tr>");
            out.println("<tr><td>Country</td><td>" + country + "</td></tr>");
            out.println("<tr><td>Favorite IDE</td><td>");

            for (String ide : favoriteIDE) {
                out.println(ide + "<br>");
            }

            out.println("</td></tr>");
            out.println("<tr><td>TOEIC Score</td><td>" + toeic + "</td></tr>");
            out.println("<tr><td>Message</td><td>" + message + "</td></tr>");
            out.println("</table>");
        }

        out.close();
    }
}

