package com.Custom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Create a Search servlet
@WebServlet("/Search")
public class Search extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        // Get the keyword searched in the search engine
        String keyword = request.getParameter("keyword");
        System.out.println(keyword);
        try{
            Connection connection = DatabaseConnection.getConnection();
            // Add keyword and link into the history table
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into history values(?,?)");
            preparedStatement.setString(1,keyword);
            preparedStatement.setString(2,"http://localhost:8080/SearchEngineCustom/Search?keyword"+keyword);
            preparedStatement.executeUpdate();

            // Store the result table in result set which similar to linked list
            ResultSet resultSet = connection.createStatement().executeQuery("select pageTitle, pageLink, (length(pageData)-length(replace(lower(pageData),'"+keyword+"',\"\")))/length('"+keyword+"') as keyOccurances \n" +"from pages\n" + "Order by keyOccurances desc");
            // New ArrayList to store the result set
            ArrayList<SearchResult> result = new ArrayList<SearchResult>();
            while(resultSet.next()){
                // Create Search Result to store the pageTitle and Link
                SearchResult searchResult = new SearchResult();
                searchResult.setPageTitle(resultSet.getString("pageTitle"));
                searchResult.setPageLink(resultSet.getString("PageLink"));
                // Add the searchResult to final result List
                result.add(searchResult);
            }
            // Set the Attribute of the request for results
            request.setAttribute("results", result);
            // Forwarding the request and response after calculating to the search.jsp that is frontend
            request.getRequestDispatcher("/search.jsp").forward(request,response);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        catch (ServletException servletException){
            servletException.printStackTrace();
        }

    }

}
