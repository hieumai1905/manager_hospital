package com.example.datlichkhambenh.DAO.account;

import com.example.datlichkhambenh.models.Account;
import com.example.datlichkhambenh.models.BenhNhan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.sql.*;

public class AccountDAO implements IAccountDAO {
    private SessionFactory sessionFactory;

    public AccountDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Override
    public Account login(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Account> query = session.createQuery("select a from Account a where a.username = :username and a.password = :password", Account.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean register(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Date date = new Date(2000, 2, 2);
            BenhNhan benhNhan = new BenhNhan();
            benhNhan.setNgaysinh(date);
            benhNhan.setTenbn(username);
            Serializable benhNhanId = session.save(benhNhan);
            benhNhan.setMabn((Integer) benhNhanId);
            Account account1 = new Account(username,password,0, benhNhan);
            session.saveOrUpdate(account1);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
