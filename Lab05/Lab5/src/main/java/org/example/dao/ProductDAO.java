package org.example.dao;


import org.example.model.Product;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ProductDAO {
    HibernateUtils hibernateUtils;

    public ProductDAO() {

    }

    public ProductDAO(HibernateUtils hibernateUtils) {
        this.hibernateUtils = hibernateUtils;
    }

    public void save(Product product) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void add(Product product) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Product> getAllProducts() {
        Transaction transaction = null;
        List<Product> productList = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
            criteriaQuery.from(Product.class);
            productList = session.createQuery(criteriaQuery).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return productList;
    }


    public Product getProductByID(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Product product = session.find(Product.class, id);
            session.getTransaction().commit();
            return product;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Product getProductByProductName(String productName) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Product product = session.find(Product.class, productName);
            session.getTransaction().commit();
            return product;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void update(Product product) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteByID(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Product product = session.find(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Product product) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteProductById(int productId) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, productId);
            if (product != null) {
                session.delete(product);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
