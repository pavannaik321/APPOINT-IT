/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author Dell
 */
public class InsertData extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertData</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertData at " + request.getContextPath() + "</h1>");
           // out.println("<h1>Servlet InsertData at "   "</h1>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null ,rs2 = null;
    String n = "", p = "", num;
    long num2;

    n = request.getParameter("txtuname");
    p = request.getParameter("txtupass");
    num = request.getParameter("txtnum");

    try {
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/fix", "root", "root");
       
            
        stmt = con.createStatement();

        rs = stmt.executeQuery("SELECT * FROM users WHERE upnone = '" + num + "' AND uname = '" + n + "'");
        
        if (rs.next()) {
             out.println("<header style=\"background-color: #333; padding: 10px; color: white; text-align: center;\">");
            out.println("<span style=\"float: left; margin-left: 10px;\">APPOINT IT</span>");
            out.println("<span style=\"float: right; margin-right: 10px;\">"+n+"</span>");
            out.println("<div style=\"clear: both;\"></div>");
            out.println("</header>");
            
            out.println("<h2 style=\"color: #333; margin: 30px;\">Appointments</h2>");
            out.println("<div style=\"margin: 20px; display:flex; justify-content:center; flex-wrap : wrap; \">");
           // Assuming rs2 is your ResultSet
rs2 = stmt.executeQuery("SELECT * FROM appoint WHERE upnone = '" + num + "' ");

// Check if the result set is not empty
if (rs2.next()) {
    int count=1;
    // Iterate over the result set and print appointment cards
    do {
        count++;
        out.println("<div style=\" width: 25%; background-color: #f4f4f4; border-radius: 10px; padding: 15px; margin: 10px; float: left;\">");
        out.println("<li style=\"border-bottom: 1px solid #ddd; padding: 10px;\">");
        out.println("<strong style=\"color: #0066cc;\">Title:</strong> " + rs2.getString("aitle") + "<br>");
        out.println("</li>");
        out.println("<li style=\"border-bottom: 1px solid #ddd; padding: 10px;\">");
        out.println("<strong style=\"color: #0066cc;\">Time:</strong> " + rs2.getString("Time") + "<br>");
        out.println("</li>");
        out.println("<li style=\"padding: 10px;\">");
        out.println("<strong style=\"color: #0066cc;\">Date:</strong> " + rs2.getString("date") + "<br>");
        out.println("</li>");
        out.println("<li style=\"padding: 10px;\">");
        out.println("<strong style=\"color: #0066cc;\">Notes:</strong> " + rs2.getString("notes") + "<br>");
        out.println("</li>");
        out.println("</ul>");
        out.println("</div>");
    } while (rs2.next());
} else {
    // Print a message if there are no appointments
    out.println("<p>No appointments found.</p>");
}


            out.println("<div style=\"clear: both;\"></div>"); 
            out.println("</div>");
            
            out.println("<div style=\"margin: 20px; display:flex; flex-wrap:wrap; justify-content:center \">");
            out.println("<div style=\"margin-top: 20px;\">");
        
            out.println("<button style=\" cursor:pointer; background-color: #ff3300; color: white; padding: 10px 20px; border: none; border-radius: 5px; margin-right: 10px;\">Delete</button>");
            out.println("<button style=\" cursor:pointer; background-color: #0066cc; color: white; padding: 10px 20px; border: none; border-radius: 5px; margin-right: 10px;\">Modify</button>");
            out.println("<button onclick=\"location.href='AddAppoint?id="+num+"'\" style=\"cursor:pointer; background-color: #ff9900; color: white; padding: 10px 20px; border: none; border-radius: 5px;\">Add New</button>");
            out.println("</div>");
            out.println("</div>");
            
        } else {
            stmt.executeUpdate("INSERT INTO users VALUES('" + num + "', '" + n + "', '" + p + "')");
             out.println("<header style=\"background-color: #333; padding: 10px; color: white; text-align: center;\">");
            out.println("<span style=\"float: left; margin-left: 10px;\">APPOINT IT</span>");
            out.println("<span style=\"float: right; margin-right: 10px;\">"+n+"</span>");
            out.println("<div style=\"clear: both;\"></div>");
            out.println("</header>");
            
             
                out.println("<h3 style=\"color: #0066cc;\">No Appointment</h3>");
                
            out.println("<div style=\"margin: 20px; display:flex; justify-content:center \">");
            out.println("<div style=\"margin-top: 20px;\">");
            out.println("<button style=\"cursor:pointer; background-color: #ff3300; color: white; padding: 10px 20px; border: none; border-radius: 5px; margin-right: 10px;\">Delete</button>");
            out.println("<button style=\"cursor:pointer; background-color: #0066cc; color: white; padding: 10px 20px; border: none; border-radius: 5px; margin-right: 10px;\">Modify</button>");
            out.println("<button style=\"cursor:pointer; background-color: #ff9900; color: white; padding: 10px 20px; border: none; border-radius: 5px;\">Add New</button>");
            out.println("</div>");
            out.println("</div>");

       
            
        }

    } catch (Exception e) {
        out.println(e);
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            out.println(e);
        }
    }
}


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
