package org.example.tourplanner.DAL.repositories;

import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.DAL.HibernateUtil;
import org.example.tourplanner.Injectable;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Slf4j
public abstract class BaseDAO<T> implements Injectable {
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
            log.error("Unable to save entity of Type " + entity.getClass() + ", an exception occurred: " + e);
        }
    }

    protected void update(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Unable to update entity of Type " + entity.getClass() + ", an exception occurred: " + e);
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
            log.error("Unable to delete entity of Type " + entity.getClass() + ", an exception occurred: " + e);
        }
    }
}
