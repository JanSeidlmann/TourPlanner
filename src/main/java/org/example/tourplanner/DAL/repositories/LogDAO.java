package org.example.tourplanner.DAL.repositories;

import org.example.tourplanner.BL.models.LogModel;
import org.hibernate.Session;

import java.util.List;

public class LogDAO extends BaseDAO<LogModel> {
    public List<LogModel> findALl(){
        try(Session session = getSession()){
            return session.createQuery("FROM LogModel", LogModel.class).list();
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
