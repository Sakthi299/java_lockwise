package com.lockwise.services;

import static com.lockwise.crypto.Blowfish.callencrypt;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addnotes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            List<String> info=new ArrayList<String>(3);  
            List<String> ciphers=new ArrayList<String>(3);
            info.add(request.getParameter("tag"));
            info.add(request.getParameter("name"));
            info.add(request.getParameter("notes"));
            String userid = userdata.email;

            for (int i = 2; i < info.size(); i++)   
            {
               ciphers.add(callencrypt(userid, info.get(i)));
            }
             
            try (Connection conn = dbconnect.initializeDatabase()) {
                String query= "INSERT INTO notes(userid, tag, name, notes) VALUES (?, ?, ?, ?)" ;
                PreparedStatement stat = conn.prepareStatement(query);
                    stat.setString(1, userid);
                    stat.setString(2, info.get(0));
                    stat.setString(3, info.get(1));
                    stat.setString(4, ciphers.get(0));
                    
                    int result = stat.executeUpdate();
                    if(result > 0)
                    {
                        String msg="success";
                        request.setAttribute("msg", msg);
                        RequestDispatcher rd = request.getRequestDispatcher("fetchnotes"); 
                        rd.forward(request, response); 
                    }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }       
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(addnotes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(addnotes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
