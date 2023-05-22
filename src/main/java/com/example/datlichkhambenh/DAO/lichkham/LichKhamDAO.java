package com.example.datlichkhambenh.DAO.lichkham;

import com.example.datlichkhambenh.models.LichKham;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class LichKhamDAO implements ILichKhamDAO {
    private SessionFactory sessionFactory;

    public LichKhamDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Override
    public List<LichKham> findAll() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<LichKham> query = session.createQuery("select a from LichKham a ", LichKham.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void save(LichKham lichKham) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(lichKham);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LichKham findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<LichKham> query = session.createQuery("select a from LichKham a where a.id = :id", LichKham.class);
            query.setParameter("id",id);
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
            LichKham lichKham = new LichKham();
            lichKham.setId(id);
            session.delete(lichKham);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, LichKham lichKham) {
        lichKham.setId(id);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(lichKham);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LichKham> layDanhSachLichKhamCuaBacSi(int maBS) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<LichKham> query = session.createQuery("select a from LichKham a where a.bacSi.id = :idBS", LichKham.class);
            query.setParameter("idBS",maBS);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<LichKham> layDanhSachLichKhamCuaBenhNhan(int maBN) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<LichKham> query = session.createQuery("select a from LichKham a where a.benhNhan.id = :idBN", LichKham.class);
            query.setParameter("idBN",maBN);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<LichKham> layDanhSachLichKhamTrongKhoang(String timeStart, String timeEnd) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<LichKham> query = session.createQuery("SELECT l FROM LichKham l WHERE ngaykham BETWEEN :timeStart AND :timeEnd", LichKham.class);
            query.setParameter("timeStart",timeStart);
            query.setParameter("timeEnd",timeEnd);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}