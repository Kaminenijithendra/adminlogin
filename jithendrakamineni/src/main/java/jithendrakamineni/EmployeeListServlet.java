package jithendrakamineni;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeListServlet")
public class EmployeeListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "jithendra";
    private static final String DB_PASSWORD = "jithu";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees1");

            List<Employee> employeesList = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEMPLOYEE_ID(resultSet.getInt("EMPLOYEE_ID"));
                employee.setNAME(resultSet.getString("NAME"));
                employee.setEMAIL(resultSet.getString("EMAIL"));
                employee.setMOBILE_NO(resultSet.getString("MOBILE_NO"));
                employee.setDESIGNATION(resultSet.getString("DESIGNATION"));
                employee.setGENDER(resultSet.getString("GENDER"));
				 String COURSESString = resultSet.getString("COURSES"); 
				 String[] COURSESArray = COURSESString.split(","); 
				 employee.setCourses(COURSESArray);
                employeesList.add(employee);
            }

            resultSet.close();
            statement.close();
            connection.close();

            Gson gson = new Gson();
            String json = gson.toJson(employeesList);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            int employeeId = Integer.parseInt(request.getParameter("employeeId"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String mobileNo = request.getParameter("mobileNo");
            String designation = request.getParameter("designation");
            String gender = request.getParameter("gender");
            //String courses = request.getParameter("courses");

            PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE employees1 SET NAME=?, EMAIL=?, MOBILE_NO=?, DESIGNATION=?, GENDER=?, COURSES=? WHERE EMPLOYEE_ID=?");
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, mobileNo);
            preparedStatement.setString(5, designation);
            preparedStatement.setString(6, gender);
           // preparedStatement.setString(7, courses);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            int employeeId = Integer.parseInt(request.getParameter("employeeId"));

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employees1 WHERE EMPLOYEE_ID=?");
            preparedStatement.setInt(1, employeeId);
            preparedStatement.executeUpdate();
            System.out.println("jithendra");
            preparedStatement.close();
            connection.close();
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/delete_success.html");
            System.out.println("kamineni");
        //dispatcher.forward(request,response);
        } catch (SQLException e) {
        }
    }
}
