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

public class fetchbankaccounts extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           try (Connection conn = dbconnect.initializeDatabase()) {
                String query= "SELECT * from bankaccounts where userid = ?";
                PreparedStatement stat = conn.prepareStatement(query);  
                stat.setString(1, userdata.email);
                ResultSet result = stat.executeQuery();
                ArrayList<util> pass = new ArrayList<util>();
                ArrayList<util> decrypted = new ArrayList<util>();
                while(result.next())
                {
                    pass.add(new util(result.getInt("accid"),result.getString("tag"),result.getString("name"),result.getString("bankname"),result.getString("ifsc"),result.getString("actype"),result.getString("acholder"),result.getString("acnumber"),result.getString("brname"),result.getString("braddr"),result.getString("brphone"),result.getString("acdate"),result.getString("notes")));             
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
                        replacesalt(f_salt, calldecrypt(pass.get(i).bankname), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).ifsc), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).type), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).acholder), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).acnumber), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).brname), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).braddr), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).brphone), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).acdate), l_salt),
                        replacesalt(f_salt, calldecrypt(pass.get(i).notes), l_salt)
                 ));
                }
                
                request.setAttribute("data", decrypted); 
                log("----"+userdata.email+"----"+userdata.password);
                String message = (String)request.getAttribute("msg");
                request.setAttribute("msg", message);
                request.getRequestDispatcher("bankaccounts.jsp").forward(request, response);
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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
