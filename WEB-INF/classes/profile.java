import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class profile extends HttpServlet{
    public void service(HttpServletRequest req,HttpServletResponse res){
        PrintWriter out=null;
        try{
            out=res.getWriter();
            res.setContentType("text/html");
            out.print("<html>");
            out.print("<head>");
            out.println("<link rel=\"stylesheet\" href=\"style_profile.css\">");
            out.println("</head>");
            out.println("</head><body>");
            out.print("<div class='main'>");
            out.println("<ul>");
            out.println("<li><a href=\"index.html\">Home</a></li>");
            out.println("<li><a href=\"about.html\">About</a></li>");
            out.println("<li><a href=\"services.html\">Services</a></li>");
            out.println("<li><a href=\"booking.html\">Book</a></li>");
            out.println("<li><a href=\"login.html\">Login</a></li>");
            out.println("<li><a href=\"contact.html\">Contact Us</a></li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("<div class='title'>");
            out.println("<h1>Customize your profile</h1>");
            out.println("</div>");
            out.println("<hr style=\" width:90%;text-align:left;margin-left: 20px;color: #696969;\">");
            out.println("<div class='boom'>");
            out.println("<br>");
            String u = req.getParameter("user");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ARYAN", "oracle@3142");
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(
                "select * from Creations where username='"+u+"' "
            );
            if(rs.next()){
                String e=req.getParameter("email");
                String n=req.getParameter("name");
                String ph=req.getParameter("phone");
                String p=req.getParameter("pass");
                out.println("<b>Welcome !</b>"+"  "+n);
                out.println("<div class='btn'><a href='index.html' style=\"color: #000000; text-decoration: none;\">Logout</a></div><br><br>");
                out.println("<b>Email:</b>"+"  "+e+"<br><br>");
                out.println("<b>User name:</b>"+"  "+u+ "<br><br>");
                out.println("<b>Your Contact number:</b>"+"  "+ph+"<br><br></div>");
                out.println("<div class='btn1'><a href='booking.html' style=\"color: #000000; text-decoration: none;\">Book</a></div>");
                out.println("</body></html>");
            }
            else{
                res.sendRedirect("login.html");
            }
        }catch(Exception ex){
            out.print(ex);
            out.close();
        }
    }
}