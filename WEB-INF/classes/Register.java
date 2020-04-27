import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class Register extends HttpServlet{
    public void service(HttpServletRequest req,HttpServletResponse res){
        PrintWriter out=null;
        try{
            out=res.getWriter();
            res.setContentType("text/html");
            String u=req.getParameter("user");
            String n=req.getParameter("name");
            String ph=req.getParameter("phone");
            String e=req.getParameter("email");
            String p=req.getParameter("pass");
            //first step : Loading the driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // second step : initializing a connection sys as sysdba
            Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ARYAN","oracle@3142");
            // CREATING A STATEMENT
            Statement st = c.createStatement();
            //executing the query
            int x = st.executeUpdate(
                "insert into Creations values('"+u+"' , '"+n+"' , '"+ph+"' , '"+e+"' , '"+p+"' ) "
            );
            if(x==1){
                RequestDispatcher rd=req.getRequestDispatcher("profile");
				rd.forward(req,res);
            }else{
                res.sendRedirect("registerError.html");
            }

        }
        catch(SQLIntegrityConstraintViolationException ex){
            try{
                res.sendRedirect("registerError2.html");
            }
            catch(Exception u){
                out.print(u);
                out.close();
            }
        }catch(Exception ex){
            out.print(ex);
            out.close();
        }
    }
}