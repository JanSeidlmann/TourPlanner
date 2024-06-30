package org.example.tourplanner.DAL.repositories;

import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.models.LogModel;
import org.example.tourplanner.BL.models.TourModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

@Slf4j
public class LogDAO extends BaseDAO<LogModel> {
    public List<LogModel> findALl() {
        try(Session session = getSession()){
            return session.createQuery("FROM LogModel", LogModel.class).list();
        } catch (Exception e) {
            log.error("Unable to get session for query, exception occurred: " + e);
            return null;
        }
    }

    public List<LogModel> findByTour(TourModel tour) {
        try(Session session = getSession()) {
            Query<LogModel> query = session.createQuery("FROM LogModel WHERE tour = :tour", LogModel.class);
            query.setParameter("tour", tour);
            return query.list();
        } catch (Exception e) {
            log.error("Unable to get session for query, exception occurred: " + e);
            return null;
        }
    }

    @Override
    public void save(LogModel log){
        super.save(log);
    }

    @Override
    public void delete(LogModel log){
        super.delete(log);
    }
}
