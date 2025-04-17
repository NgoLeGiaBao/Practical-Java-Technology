package com.example.Ex1;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Integer errorCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String errorMsg;

        switch (errorCode) {
            case 400:
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            case 401:
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            case 404:
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            case 500:
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            default:
                errorMsg = "Http Error Code: " + errorCode + ". Unexpected Error";
                break;
        }

        model.addAttribute("errorMsg", errorMsg);
        return "error";
    }
}
