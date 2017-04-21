/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import entity.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class Customers {

    Session session;

    public Customers() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public boolean checkLogin(String user, String pass) {
        Transaction trans = null;
        try {
            trans = session.getTransaction();
            trans.begin();
            String sql = "from Customer where username=? and password=?";
            Query query = session.createQuery(sql);
            query.setString(0, user);
            query.setString(1, pass);
            boolean exist = query.list().isEmpty();
            if (exist == false) {
                return true;
            }
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public List<Customer> showCustomer(String tenkh) {
        Transaction trans = null;
        try {
            List<Customer> list = new ArrayList<Customer>();
            trans = session.getTransaction();
            trans.begin();
            
            String sql = "from Customer";
            if (tenkh.length() > 0) {
                sql += " where hoten like '%" + tenkh + "%'";
            }
            Query query = session.createQuery(sql);

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
    
    public void insert(String user, String pass, String hoten, boolean gioitinh, String email, boolean role, String image) {
        Transaction trans = null;
        try {
            trans = session.getTransaction();
            trans.begin();
            Customer kh = new Customer(user, pass, hoten, gioitinh, email, role, image);
            session.save(kh);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public void update(String user, String pass, String hoten, boolean gioitinh, String email, boolean role, String image) {
        Transaction trans = null;
        try {
            trans = session.getTransaction();
            trans.begin();
            Customer kh = (Customer) session.get(Customer.class, user);
            kh.setUsername(user);
            kh.setPassword(pass);
            kh.setHoten(hoten);
            kh.setGioitinh(gioitinh);
            kh.setEmail(email);
            kh.setRole(role);
            kh.setImage(image);
            session.save(kh);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public void delete(String user) {
        Transaction trans = null;
        try {
            trans = session.getTransaction();
            trans.begin();
            Customer kh = (Customer) session.get(Customer.class, user);
            session.delete(kh);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }
    }
}
