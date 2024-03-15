package jithendrakamineni;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Jdbc {

    private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String user = "jithendra";
    private static final String password = "jithu";
    private static final String sql = "INSERT INTO employees1 (EMPLOYEE_ID, name, email, mobile_no, designation, gender, courses) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public int uploadFile(String EMPLOYEE_ID, String name, String email, String mobile_no, String designation, String gender, String courses) {
        int row = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, EMPLOYEE_ID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, mobile_no);
            preparedStatement.setString(5, designation);
            preparedStatement.setString(6, gender);
            preparedStatement.setString(7, courses);
           /* if (photo != null) {
                preparedStatement.setBinaryStream(8, photo);
            }*/

            row = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return row;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
