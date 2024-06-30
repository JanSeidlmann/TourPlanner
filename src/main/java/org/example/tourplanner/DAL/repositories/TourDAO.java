package org.example.tourplanner.DAL.repositories;
import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.models.TourModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

@Slf4j
public class TourDAO extends BaseDAO<TourModel> {
    public TourModel findByName(String name){
        try(Session session = getSession()){
            Query<TourModel> query = session.createQuery("FROM TourModel WHERE name = :name",
                    TourModel.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
            log.error("Unable to get session for query, exception occurred: " + e);
            return null;
        }
    }

    public List<TourModel> findAll(){
        try(Session session = getSession()){
            return session.createQuery("FROM TourModel", TourModel.class).list();
        } catch (Exception e) {
            log.error("Unable to get session for query, exception occurred: " + e);
            return null;
        }
    }

    @Override
    public void save(TourModel tour){
        super.save(tour);
    }

    @Override
    public void delete(TourModel tour){
        super.delete(tour);
    }
}
