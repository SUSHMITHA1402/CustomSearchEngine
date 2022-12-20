package com.Custom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// Hit this url link to get this response from home web link
@WebServlet("/Servlet")
// This a Servlet (Mini Server)
public class MyServlet extends HttpServlet {
    // This will handle our requests to the application
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // Set the content type of the Servlet response
        response.setContentType("text/html");
        // Get writer to write content in response
        PrintWriter out = response.getWriter();
        // Write content
        out.println("<h3>This is My custom Servlet</h3>");
    }
}
