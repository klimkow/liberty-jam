package com.liberty.technical.logic.dao;

import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.factory.SessionFactoryInitializer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author AKI
 */
public class CommonDAO {
    SessionFactory factory = SessionFactoryInitializer.getInstance().getSessionFacroty();

    public <T> void writeObject(T object)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(object);
            tx.commit();

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public <T> Object readObject(Class<T> className)
    {
        return new Object();
    }


    public <T> List<T> readAllObjects(Class<T> className)
    {
        List<T> resultList = null;
        Session session = factory.openSession();
        try {
            resultList = session.createCriteria(className).list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return resultList;
    }


    public <T> void deleteObject(T object)
    {

    }


    public <T> void updateObject(T object)
    {

    }
}
