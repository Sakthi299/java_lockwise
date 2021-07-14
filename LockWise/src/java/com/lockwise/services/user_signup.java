package com.lockwise.services;

import static com.lockwise.crypto.baseencode.baseencode;
import static com.lockwise.crypto.baseencode.readconfig;
import static com.lockwise.crypto.baseencode.spliting;
import static com.lockwise.crypto.baseencode.strtohex;
import com.lockwise.crypto.tripleDES;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="user_signup", urlPatterns={"/user_signup"})
public class user_signup extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String name = request.getParameter("username");
            String pass = request.getParameter("password");
            String repass = request.getParameter("repassword");
            String mail = request.getParameter("email");
            log(name+pass+mail);
            Connection conn= null;
            int flag = 1;
            String msg;
            try 
            {
            conn = dbconnect.initializeDatabase();
            } 
            catch(Exception e)
            {
            System.out.println(e);
            }
            String q = "SELECT email FROM users" ;
            PreparedStatement s = conn.prepareStatement(q);
            ResultSet res= s.executeQuery();
            while(res.next())
            {
            String fetchedmail = res.getString("email");
            if(fetchedmail.equals(mail))
            {
                flag= 0;
                break;
            }
            }
            if(pass.equals(repass))
            {    
             if(flag == 1)
             {
                // Encrypt MASTER password
                tripleDES td= new tripleDES();
                String fn = baseencode(mail);
                log("user_SALT : ---------------"+fn+"---------------");
                spliting(mail, mail , fn);
                String hash = strtohex(td.encrypt(fn+pass+readconfig("pepper")));
                log("user_signup : ---------------"+hash+"---------------");
                String query= "INSERT INTO users(username, email, password) VALUES (?, ?, ?)" ;
                PreparedStatement stat = conn.prepareStatement(query);
                    stat.setString(1, name);
                    stat.setString(2, mail);
                    stat.setString(3, hash);
                    int result = stat.executeUpdate();
                    if(result > 0)
                    {
                        msg="regsuccess";
                        String data="";
                        request.setAttribute("msg", data);
                        request.setAttribute("regmsg", msg);
                        RequestDispatcher rd = request.getRequestDispatcher("authenticate_user.jsp"); 
                        rd.forward(request, response); 
                    }
            }
             else
             {
                msg="regmail";
                log("signup mail error");
                request.setAttribute("regmsg", msg); 
                RequestDispatcher rd = request.getRequestDispatcher("authenticate_user.jsp"); 
                rd.forward(request, response); 
             }
            }
            else
            {
                msg="regpass";
                log("signup pass error");
                request.setAttribute("regmsg", msg); 
                RequestDispatcher rd = request.getRequestDispatcher("authenticate_user.jsp"); 
                rd.forward(request, response); 
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(user_signup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(user_signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(user_signup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(user_signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
