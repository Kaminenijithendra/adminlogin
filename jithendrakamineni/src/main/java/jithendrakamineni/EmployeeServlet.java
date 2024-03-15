package jithendrakamineni;



import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



@WebServlet("/EmployeeServlet")
@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private Jdbc jdbc;
    @Override
    public void init() {
    	jdbc=new Jdbc();
    }
    
   
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String  EMPLOYEE_ID = request.getParameter("EMPLOYEE_ID");
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        String mobileNo = request.getParameter("MobileNo");
        String designation = request.getParameter("Designation");
        System.out.println("degination");
        String gender = request.getParameter("Gender");
        System.out.println("gender");
        String courses = request.getParameter("Course");
        /*InputStream inputStream = null; 
        Part filePart=request.getPart("Photo");
        System.out.println("photo"+filePart);
        if(filePart != null) {
        	System.out.println(filePart.getName());
        	System.out.println(filePart.getSize());
        	System.out.println(filePart.getContentType());
        	
        	inputStream = filePart.getInputStream(); 
        }
        System.out.println("photos: "+inputStream);*/
        int row =jdbc.uploadFile(EMPLOYEE_ID, name, email, mobileNo, designation, gender, courses/*, inputStream*/);
        if (row > 0) {
        	
            out.println("<h3>Data stored successfully!</h3>");
        } else {
            out.println("<h3>Failed to store data!</h3>");
        }
    }
}
