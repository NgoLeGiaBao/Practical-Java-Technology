package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String speedParam = request.getParameter("speed");

        if (fileName == null || fileName.isEmpty()) {
            response.getWriter().write("File not found");
            return;
        }

        File file = new File(getServletContext().getRealPath("/WEB-INF/classes/" + fileName));
        System.out.println(file.getAbsolutePath());

        if (file.exists()) {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            response.setContentType("application/octet-stream");

            int speed = speedParam != null ? Integer.parseInt(speedParam) : Integer.MAX_VALUE;

            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                long startTime = System.currentTimeMillis();

                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    long targetTime = (bytesRead * 1000L) / speed;
                    if (elapsedTime < targetTime) {
                        try {
                            Thread.sleep(targetTime - elapsedTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            response.getWriter().write("File not found");
        }
    }
}
