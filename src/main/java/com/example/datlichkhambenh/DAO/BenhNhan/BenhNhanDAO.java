package com.example.datlichkhambenh.DAO.BenhNhan;

import com.example.datlichkhambenh.models.BenhNhan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class BenhNhanDAO implements IBenhNhanDAO{
    private SessionFactory sessionFactory;

    public BenhNhanDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }
    @Override
    public List<BenhNhan> findAll() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<BenhNhan> query = session.createQuery("select a from BenhNhan a ", BenhNhan.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @Override
    public void save(BenhNhan benhNhan) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(benhNhan);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BenhNhan findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<BenhNhan> query = session.createQuery("select a from BenhNhan a where a.id = :id", BenhNhan.class);
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
            BenhNhan benhNhan = new BenhNhan();
            benhNhan.setMabn(id);
            session.delete(benhNhan);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, BenhNhan benhNhan) {
        benhNhan.setMabn(id);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(benhNhan);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
