package com.lockwise.services;

import static com.lockwise.crypto.Blowfish.calldecrypt;
import static com.lockwise.crypto.baseencode.argonifiedSalt;
import static com.lockwise.crypto.baseencode.replacesalt;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class fetchpaymentcards extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            try (Connection conn = dbconnect.initializeDatabase()) {
                String query= "SELECT * from paymentcards where userid = ?";
                PreparedStatement stat = conn.prepareStatement(query);  
                stat.setString(1, userdata.email);
                ResultSet result = stat.executeQuery();
                ArrayList<util> pass = new ArrayList<util>();
                ArrayList<util> decrypted = new ArrayList<util>();
                while(result.next())
                {
                    pass.add(new util(result.getInt("payid"),result.getString("tag"),result.getString("name"),result.getString("namecard"),result.getString("type"),result.getString("cardno"),result.getString("expirymonth"),result.getString("expirydate"),result.getString("cvv"),result.getString("notes")));             
                }
                stat.close();
                conn.close();
                
                String argonifiedsalt = argonifiedSalt(userdata.email);
                String f_salt = argonifiedsalt.substring(0, argonifiedsalt.length()/2);
                String l_salt = argonifiedsalt.substring(argonifiedsalt.length()/2);
                for(int i=0; i<pass.size(); i++)
                {
                 decrypted.add(new util(
                        pass.get(i).id,
                        pass.get(i).tag,
                        pass.get(i).name,
                        replacesalt(f_salt, calldecrypt(pass.get(i).cardname), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).type), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).cardno), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).exmonth), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).exyear), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).cvv), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).notes), l_salt)
                 ));
                }
                
                request.setAttribute("data", decrypted); 
                log("----"+userdata.email+"----"+userdata.password);
                String message = (String)request.getAttribute("msg");
                request.setAttribute("msg", message);
                request.getRequestDispatcher("paymentcards.jsp").forward(request, response);
            }
            catch(Exception e)
            {
                System.out.println(e);
            } 
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
