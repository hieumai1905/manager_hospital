package com.example.datlichkhambenh.DAO.khoa;

import com.example.datlichkhambenh.models.Khoa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class KhoaDAO implements IKhoaDAO {

    private SessionFactory sessionFactory;

    public KhoaDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }
    @Override
    public List<Khoa> findAll() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Khoa> query = session.createQuery("select a from Khoa a ", Khoa.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Khoa khoa) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(khoa);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Khoa findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Khoa> query = session.createQuery("select a from Khoa a where a.id = :id", Khoa.class);
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
            Khoa khoa = new Khoa();
            khoa.setMaK(id);
            session.delete(khoa);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Khoa khoa) {
        khoa.setMaK(id);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(khoa);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}