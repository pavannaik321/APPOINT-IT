package servlet1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;

public class AddAppoint extends HttpServlet {
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display the form for adding a new appointment
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add New Appointment</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Add New Appointment</h1>");
        out.println("<form method=\"post\" action=\"AddAppoint\">");
        out.println("    <input type=\"hidden\" name=\"txtnum\" value=\"" + id + "\">");
        out.println("Title: <input type=\"text\" name=\"title\"><br>");
        out.println("Date: <input type=\"text\" name=\"date\"><br>");
        out.println("Time: <input type=\"text\" name=\"time\"><br>");
        out.println("Notes: <textarea name=\"notes\"></textarea><br>");
        out.println("<input type=\"submit\" value=\"Add Appointment\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Process the form submission and add the new appointment to the database
        String title = request.getParameter("title");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String notes = request.getParameter("notes");
        String id  = request.getParameter("txtnum");

        // Use the same database connection configuration as in your original servlet
        String dbUrl = "jdbc:derby://localhost:1527/fix";
        String dbUser = "root";
        String dbPassword = "root";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "INSERT INTO appoint (upnone, aitle, date, time, notes) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                // You need to obtain the 'upnone' value from the session or wherever it is stored
                String upnone = id;

                preparedStatement.setString(1, upnone);
                preparedStatement.setString(2, title);
                preparedStatement.setString(3, date);
                preparedStatement.setString(4, time);
                preparedStatement.setString(5, notes);

                int rowsAffected = preparedStatement.executeUpdate();
                

                // Check if the appointment was added successfully
                if (rowsAffected > 0) {
                   out.println("Data inserted successfully");
                } else {
                     out.println("Failed to upload data");
                }
            }
        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
