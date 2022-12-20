<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Custom.HistorySearch"%>
<html>
<head>
<link rel = "stylesheet" type = "text/css" href = "style.css">
</head>
    <body>
        <form action ="History">
            <input type ="text" name = "keyword">
            <button id ="search" type = "submit">Search</button>
        </form>
        <div class="resultTable">
        <table border =2>
            <tr>
                <td>Keyword</td>
                <td>Link</td>
            </tr>
            <%
                //Get results from History servlet
                ArrayList<HistorySearch> results = (ArrayList<HistorySearch>) request.getAttribute("results");
                for(HistorySearch result: results){
            %>
                <tr>
                    <td><% out.println(result.getKeyword());%></td>
                    <td><a href="<% out.println(result.getLink());%>"><% out.println(result.getLink());%></a></td>
                </tr>
            <%
                }
            %>
        </table>
        </div>
    </body>
</html>