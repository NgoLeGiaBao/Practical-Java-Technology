package org.example.dao;


import org.example.model.User;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDAO {


    public UserDAO() {
    }



    public void save(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public User findByEmail(String email) {
        Transaction transaction = null;
        User user = null;

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // Dùng HQL để tìm user theo email
            String hql = "FROM User U WHERE U.email = :email";
            user = session.createQuery(hql, User.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            System.err.println("Error fetching user by email: " + e.getMessage());
        }

        return user;
    }



    public void add(User user) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAll() {
        Transaction transaction = null;
        List<User> userList = null;

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            criteriaQuery.from(User.class);
            userList = session.createQuery(criteriaQuery).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return userList;
    }


    public User getUserByID(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, id);
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void update(User user) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteByID(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(User user) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
