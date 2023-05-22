package com.example.datlichkhambenh.DAO.bacsi;

import com.example.datlichkhambenh.models.BacSi;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class BacSiDAO implements IBacSiDAO {
    private SessionFactory sessionFactory;

    public BacSiDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Override
    public List<BacSi> findAll() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<BacSi> query = session.createQuery("select a from BacSi a ", BacSi.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void save(BacSi bacSi) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(bacSi);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BacSi findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<BacSi> query = session.createQuery("select a from BacSi a where a.id = :id", BacSi.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            BacSi bacSi = new BacSi();
            bacSi.setMabs(id);
            session.delete(bacSi);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, BacSi bacSi) {
        bacSi.setMabs(id);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(bacSi);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BacSi> layBacSiTheoKhoa(int maKhoa) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<BacSi> query = session.createQuery("select a from BacSi a where a.khoa.id = :idBS", BacSi.class);
            query.setParameter("idBS", maKhoa);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}