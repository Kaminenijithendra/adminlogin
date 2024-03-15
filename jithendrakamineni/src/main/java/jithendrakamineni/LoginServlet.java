package jithendrakamineni;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("login");

        // JDBC URL, username, and password of Oracle database
        String jdbcURL = "jdbc:oracle:thin:@localhost:1521:orcl";
        String dbUser = "jithendra";
        String dbPassword = "jithu";

        // Fetching username and password from request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;

        try {
            // Load the JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Create a connection to the database
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Create a SQL statement to query the database
            String sql = "SELECT * FROM t_login WHERE f_userName = ? AND f_Pwd = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the SQL statement and get the result set
            ResultSet result = statement.executeQuery();

            if (result.next()) {
            	System.out.println("loginsucessfully loaded");
                // If the user is found in the database, redirect to a success HTML page
            	response.sendRedirect("success.html");
            } else {
                // If the user is not found in the database, redirect back to the login page
            	response.sendRedirect("loginfaild.html");
            	
            }
        } catch (SQLException ex) {
            // Handle any SQL exceptions
            ex.printStackTrace();
            throw new ServletException(ex);
        } catch (ClassNotFoundException ex) {
            // Handle any ClassNotFoundExceptions
            ex.printStackTrace();
            throw new ServletException(ex);
        } finally {
            // Close the database connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new ServletException(ex);
            }
        }
    }
}
