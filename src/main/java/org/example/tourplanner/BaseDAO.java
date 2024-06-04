package org.example.tourplanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class BaseDAO<T> {
    protected Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    protected void save(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                ;
            }
            e.printStackTrace();
        }
    }

    protected void delete(T entity){
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                ;
            }
            e.printStackTrace();
        }
    }
}
