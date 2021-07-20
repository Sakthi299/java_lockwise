package com.lockwise.services;

import com.lockwise.crypto.randomPassword;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class generatepassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            log("----"+userdata.email+"----"+userdata.password);
            
            request.getRequestDispatcher("generatepassword.jsp").forward(request, response);
                
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("data", "");
        request.setAttribute("msg", "");
        request.getRequestDispatcher("generatepassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int len = Integer.parseInt(request.getParameter("length"));
        String pass = request.getParameter("password");
        String lower = request.getParameter("lower");
        String upper = request.getParameter("upper");
        String number = request.getParameter("number");
        String special = request.getParameter("special");
        log("----"+Integer.toString(len)+"----"+lower+"----"+upper+"----"+number+"----"+special);
        
        randomPassword passwordGenerator = new randomPassword();
        String strongpassword = passwordGenerator.getpass(len, lower, upper, number, special);
        
        request.setAttribute("data", strongpassword); 
        String message = "success";
        request.setAttribute("msg", message);
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
