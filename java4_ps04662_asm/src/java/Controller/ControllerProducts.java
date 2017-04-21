/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entity.Product;
import Model.Products;
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
public class ControllerProducts extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String[] selected = null; //chua codesp da select
    List<Product> listCart = new ArrayList<Product>();  //dung de chua sp

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String action = request.getParameter("action");
            if (action.equals("Search")) {
                String tensp = request.getParameter("txtTenSP");
                Products sp = new Products();
                List<Product> list = new ArrayList<Product>();
                list = sp.showProduct(tensp);
                request.setAttribute("listSP", list);
                RequestDispatcher rd = request.getRequestDispatcher("products.jsp");
                rd.forward(request, response);
            } else if (action.equals("Delete")) {
                String code = request.getParameter("txtCode");
                String search = request.getParameter("txtSearch");
                Products sp = new Products();
                sp.delete(code);
                String url = "ControllerProducts?action=Search&txtTenSP=" + search;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (action.equals("Edit")) {
                double price = Double.valueOf(request.getParameter("txtPrice"));
                String code = request.getParameter("txtCode");
                String name = request.getParameter("txtName");
                String image = request.getParameter("EditImage");
                String search = request.getParameter("txtSearch");

                Products sp = new Products();
                sp.update(code, name, price, image);

                String url = "ControllerProducts?action=Search&txtTenSP=" + search;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (action.equals("Insert")) {
                double giasp = Double.valueOf(request.getParameter("txtNewPrice"));
                String masp = request.getParameter("txtNewCode");
                String tensp = request.getParameter("txtNewName");
                String anhsp = request.getParameter("NewImage");

                Products sp = new Products();
                sp.insert(masp, tensp, giasp, anhsp);

                String url = "ControllerProducts?action=Search&txtTenSP=";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (action.equals("Add to cart")) {
                selected = request.getParameterValues("ckbSP");
                
                Products a = new Products();
                listCart = a.addCart(selected);
                HttpSession session = request.getSession(true);
                session.setAttribute("listCart", listCart);
//                request.setAttribute("listCart", listCart);
                RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
                rd.forward(request, response);
            } else if (action.equals("DelCart")) {
                String code = request.getParameter("txtCode");
                Products a = new Products();
                listCart.remove(a.getObProduct(code));
                String url = "ControllerProducts?";
                for (int i = 0; i < selected.length; i++) {
                    if (code.equals(selected[i]) == false) {
                        String subUrl = "ckbSP=" + selected[i] + "&";
                        url += subUrl;
                        
                    }
                }
                url += "action=Add+to+cart";

                //String url = "ControllerProducts?ckbSP=SP002&ckbSP=SP003&ckbSP=SP004&action=Add+to+cart";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                
                HttpSession session = request.getSession(true);
                session.setAttribute("listCart", listCart);
//                for(int i =0; i <listCart.size(); i++){
//                    System.out.println(listCart.get(i).getCode());
//                }
            } else if (action.equals("Buy")){
                
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
