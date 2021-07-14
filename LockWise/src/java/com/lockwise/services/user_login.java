package com.lockwise.services;

import static com.lockwise.crypto.baseencode.gettingfullsalt;
import static com.lockwise.crypto.baseencode.hextostr;
import static com.lockwise.crypto.baseencode.readconfig;
import com.lockwise.crypto.tripleDES;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="user_login", urlPatterns={"/user_login"})
public class user_login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String name = request.getParameter("usermail");
            String pass = request.getParameter("password"); 
            
           try(Connection conn = dbconnect.initializeDatabase())
           {
            String query= "SELECT * FROM users WHERE email= ?";
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, name);
            ResultSet result= stat.executeQuery();
            while(result.next())
            {
             userdata.email = result.getString("email");
             userdata.password = result.getString("password");
            }
            log("---?????---"+userdata.email+"---????---"+userdata.password);
           
            stat.close();
            conn.close();
           }
           
           // Decrypt MASTER password
            tripleDES td= new tripleDES();
            String salt = gettingfullsalt(name);
            String decoded = td.decrypt(hextostr(userdata.password));
            log("user_login : ---------------"+decoded+"---------------");
            String base_pass = salt+pass+readconfig("pepper");
            log("user_login : ---------------"+base_pass+"---------------");
            
            
            // System.out.println(td.decrypt(td.encrypt(strtohex(baseencode("sai@gmail.com"))+"sai@gmail.com"+readconfig("pepper"))));

           
           if( name.equals(userdata.email) && base_pass.equals(decoded))
            {
            Cookie loginCookie = new Cookie("user",userdata.email);
	    //setting cookie to expiry in 30 mins
	    loginCookie.setMaxAge(120*60);
	    response.addCookie(loginCookie);
            response.sendRedirect("view_dashboard");
            }
          
           else
           {
                String msg="logfailed";
                log("login failed");
                request.setAttribute("msg", msg); 
                log("----"+userdata.email+"----"+userdata.password);
                RequestDispatcher rd = request.getRequestDispatcher("authenticate_user.jsp"); 
                rd.forward(request, response); 
            }
        }
           catch(Exception e)
           {
            System.out.println(e);
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
        processRequest(request, response);
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
