import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class Login extends HttpServlet{
    public void service(HttpServletRequest req,HttpServletResponse res){
        PrintWriter out=null;
        try{
            out=res.getWriter();
            res.setContentType("text/html");
            String u=req.getParameter("user");
            String p=req.getParameter("pass");
            //first step : loading the driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //second step : Initializing the connection
            Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ARYAN" , "oracle@3142");
            //third step : cretaing a statement
            Statement st=c.createStatement();
            //fourth step : executing the query
            ResultSet rs = st.executeQuery(
                "select * from Creations where USERNAME='"+u+"' and PASSWORD = '"+p+"'"
            );
            if(rs.next()){
                //fifth step : closing the connection
                c.close();
                RequestDispatcher rd=req.getRequestDispatcher("profile");
				rd.forward(req,res);
            } else{
                c.close();
                res.sendRedirect("loginError.html");
            }
        }
        catch(Exception ex){
            out.print(ex);
            out.close();
        }   
    }
}