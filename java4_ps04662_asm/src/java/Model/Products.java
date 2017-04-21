/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import entity.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author nhatl
 */
public class Products {

    Session session;

    public Products() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void insert(String masp, String tensp, double giasp, String anhsp) {
        Transaction trans = null;
        try {
            trans = session.getTransaction();
            trans.begin();
            Product sp = new Product(masp, tensp, giasp, anhsp);
            session.save(sp);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(String masp, String tensp, double giasp, String anhsp) {
        Transaction trans = null;
        try {
            trans = session.getTransaction();
            trans.begin();
            Product sp = (Product) session.get(Product.class, masp);
            sp.setName(tensp);
            sp.setPrice(giasp);
            sp.setImage(anhsp);
            session.save(sp);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(String masp) {
        Transaction trans = null;
        try {
            trans = session.getTransaction();
            trans.begin();
            Product sp = (Product) session.get(Product.class, masp);
            session.delete(sp);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Product> showProduct(String tensp) {
        Transaction trans = null;
        try {
            trans = session.getTransaction();
            trans.begin();
            String sql = "from Product";
            if (tensp.length() > 0) {
                sql += " where Name like '%" + tensp + "%'";
            }
            Query query = session.createQuery(sql);

            List<Product> list = new ArrayList<Product>();
            list = query.list();
            trans.commit();
            return list;
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    //dua vao 1 mang String chua masp -> tim va tra ve SP -> add SP vao listCart
    public List<Product> addCart(String[] codeArr) {
        Transaction trans = null;
        try {
            trans = session.getTransaction();
            trans.begin();
            List<Product> listCart = new ArrayList<Product>();
            for (int i = 0; i < codeArr.length; i++) {
                String sql = "from Product where code = '" + codeArr[i] + "'";
                Query query = session.createQuery(sql);
                listCart.add((Product) query.list().get(0));
            }
            trans.commit();
            return listCart;
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public Product getObProduct(String code) {
        Transaction trans = null;
        try {
            trans = session.getTransaction();
            trans.begin();
            String sql = "from Product where code = '" + code + "'";
            Query query = session.createQuery(sql);
            return (Product) query.list().get(0);
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
    
    public void saveCart(){
        
    }
}
