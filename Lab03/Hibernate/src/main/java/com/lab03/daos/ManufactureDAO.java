package com.lab03.daos;

import com.lab03.models.Manufacture;
import com.lab03.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ManufactureDAO {

    public void saveManufacture(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(manufacture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Manufacture getManufactureById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Manufacture.class, id);
        }
    }

    public List<Manufacture> getAllManufactures() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Manufacture", Manufacture.class).list();
        }
    }

    public void updateManufacture(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(manufacture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteManufacture(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, id);
            if (manufacture != null) {
                session.remove(manufacture);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
