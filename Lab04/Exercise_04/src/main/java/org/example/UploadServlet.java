package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";
    private static final String[] SUPPORTED_EXTENSIONS = {"txt", "doc", "docx", "jpg", "jpeg", "png", "gif", "pdf", "rar", "zip"};

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("name");
        Part filePart = request.getPart("document");

        String fileExtension = getFileExtension(filePart.getSubmittedFileName());
        if (!isSupportedFileExtension(fileExtension)) {
            response.getWriter().write("Unsupported file extension");
            return;
        }

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String fullFileName = fileName + "." + fileExtension;
        File uploadedFile = new File(uploadDir, fullFileName);
        boolean override = request.getParameter("override") != null;
        if (uploadedFile.exists() && !override) {
            response.getWriter().write("File already exists");
            return;
        }

        filePart.write(uploadedFile.getAbsolutePath());

        String message = override ? "File has been overridden" : "File has been uploaded";
        String downloadLink = "<br><a href=\"/uploads/" + fullFileName + "\">here</a>";
        response.setContentType("text/html");
        response.getWriter().write(message + ". Click " + downloadLink + " to visit the file.");
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    private boolean isSupportedFileExtension(String extension) {
        for (String supported : SUPPORTED_EXTENSIONS) {
            if (supported.equals(extension)) {
                return true;
            }
        }
        return false;
    }
}
