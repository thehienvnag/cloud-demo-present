package com.mycompany.java.web.demo.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mycompany.java.web.demo.book.BookCheckAvailableErrors;
import com.mycompany.java.web.demo.book.BookDAO;
import com.mycompany.java.web.demo.cart.CartObject;

/**
 *
 * @author KhoaPHD
 */
@WebServlet(name = "CheckAvailableBookServlet", urlPatterns = {"/CheckAvailableBookServlet"})
public class CheckAvailableBookServlet extends HttpServlet {

    private final String ERROR_PAGE = "viewCart.jsp";
    private final String CHECKOUT_SERVLET = "CheckoutServlet";
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
        
        String url = ERROR_PAGE;
        BookCheckAvailableErrors errors = new BookCheckAvailableErrors();
        boolean foundErr = false;
        
        try {
            HttpSession session = request.getSession();
            
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                
                if (cart != null) {
                    Map<String, Integer> items = cart.getItems();
                    
                    if (items != null) {
                        BookDAO dao = new BookDAO();
                        List<String> notAvailableBooksList =
                                errors.getNotAvailableBooksList();
                        
                        for (String bookTitle : items.keySet()) {
                            if (!dao.isAvailable(bookTitle)) {
                                if (notAvailableBooksList == null) {
                                    notAvailableBooksList = new ArrayList<>();
                                    foundErr = true;
                                }
                                
                                notAvailableBooksList.add(bookTitle);
                            }
                        }
                        
                        if (foundErr) {
                            request.setAttribute("NOT_AVAILABLE_BOOK_LIST",
                                    notAvailableBooksList);
                        } else {
                            url = CHECKOUT_SERVLET;
                        }
                    }
                }
            }
        } catch (NamingException ex) {
            log("CheckAvailableBookServlet Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("CheckAvailableBookServlet SQL: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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