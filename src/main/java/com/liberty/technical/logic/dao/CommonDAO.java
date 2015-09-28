package com.liberty.technical.logic.dao;

import com.liberty.technical.logic.factory.SessionFactoryInitializer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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


    public <T> void deleteObject(T object)
    {

    }


    public <T> void updateObject(T object)
    {

    }
}
