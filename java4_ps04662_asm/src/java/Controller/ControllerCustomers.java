/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customers;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nhatl
 */
public class ControllerCustomers extends HttpServlet {

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
        try {
            String action = request.getParameter("action");
            if (action.equals("Login")) {
                String user = request.getParameter("txtUser");
                String pass = request.getParameter("txtPass");
                Customers cus = new Customers();
                boolean exist = cus.checkLogin(user, pass);
                String url = "error.jsp";
                if (exist) {
                    url = "index.jsp";
                    if (user.equals("admin")) {
                        url = "choose.jsp";
                    }
                    HttpSession sesson = request.getSession(true);
                    sesson.setAttribute("USER", user);
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (action.equals("Search")) {
                String tenkh = request.getParameter("txtTenKH");
                Customers kh = new Customers();
                List<Customer> list = new ArrayList<Customer>();
                list = kh.showCustomer(tenkh);
                request.setAttribute("listKH", list);
                RequestDispatcher rd = request.getRequestDispatcher("customer.jsp");
                rd.forward(request, response);
            } else if (action.equals("Insert")) {
                String user = request.getParameter("txtNewUser");
                String pass = request.getParameter("txtNewPass");
                String hoten = request.getParameter("txtNewHoTen");
                boolean gioitinh = Boolean.valueOf(request.getParameter("NewGioitinh"));
                String email = request.getParameter("txtNewEmail");
                boolean vaitro = Boolean.valueOf(request.getParameter("NewVaitro"));
                String anhkh = request.getParameter("NewImage");

                Customers kh = new Customers();
                kh.insert(user, pass, hoten, gioitinh, email, vaitro, anhkh);

                String url = "ControllerCustomers?txtTenKH=&action=Search";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (action.equals("Edit")) {
                String user = request.getParameter("txtUser");
                String pass = request.getParameter("txtPass");
                String hoten = request.getParameter("txtHoTen");
                String gt = request.getParameter("Gioitinh");
                boolean gioitinh = true;
                if (gt.equals("0")) {
                    gioitinh = false;
                }
                String email = request.getParameter("txtEmail");
                String vt = request.getParameter("Vaitro");
                boolean vaitro = true;
                if (vt.equals("0")) {
                    vaitro = false;
                }
                String anhkh = request.getParameter("EditImage");
                String search = request.getParameter("txtSearch");

                Customers kh = new Customers();
                kh.update(user, pass, hoten, gioitinh, email, vaitro, anhkh);

                String url = "ControllerCustomers?action=Search&txtTenKH=" + search;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (action.equals("Delete")) {
                String user = request.getParameter("txtDelUser");
                String search = request.getParameter("txtSearch");
                Customers kh = new Customers();
                kh.delete(user);
                String url = "ControllerCustomers?action=Search&txtTenKH=" + search;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
