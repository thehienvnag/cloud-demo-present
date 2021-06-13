package com.mycompany.java.web.demo.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mycompany.java.web.demo.cart.CartObject;
import com.mycompany.java.web.demo.receipt.ReceiptDAO;
import com.mycompany.java.web.demo.receipt.ReceiptDTO;
import com.mycompany.java.web.demo.receiptdetail.ReceiptDetailDAO;
import com.mycompany.java.web.demo.receiptdetail.ReceiptDetailDTO;

/**
 *
 * @author KhoaPHD
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        String url = "viewCart";
        
        try {
            HttpSession session = request.getSession();
            
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                
                if (cart != null) {
                    Map<String, Integer> items = cart.getItems();
                    
                    if (items != null) {
                        ReceiptDAO receiptDAO = new ReceiptDAO();
                        
                        int receiptId = receiptDAO.generateReceiptId();
                        String username = (String) session.getAttribute("USERNAME");
                        Timestamp shoppingDate = new Timestamp(System.currentTimeMillis());
                        
                        ReceiptDTO receiptDTO = new ReceiptDTO(receiptId, username, shoppingDate);
                        boolean receiptResult = receiptDAO.insertNewReceipt(receiptDTO);
                        
                        if (receiptResult) {
                            ReceiptDetailDAO detailDAO = new ReceiptDetailDAO();
                            boolean detailResult = false;
                            
                            for (String bookTitle : items.keySet()) {
                                int quantity = items.get(bookTitle);
                                
                                ReceiptDetailDTO detailDTO = new ReceiptDetailDTO(receiptId, bookTitle, quantity);
                                detailResult = detailDAO.insertNewReceiptDetail(detailDTO);
                            }
                            
                            if (detailResult) {
                                url = "shopping";
                                session.removeAttribute("CART");
                            }
                        }
                    }
                }
            }
        } catch (NamingException ex) {
            log("CheckoutServlet Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("CheckoutServlet SQL: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
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