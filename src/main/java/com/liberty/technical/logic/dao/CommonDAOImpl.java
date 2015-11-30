package com.liberty.technical.logic.dao;

import com.liberty.technical.logic.factory.SessionFactoryInitializer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author AKI
 */
public class CommonDAOImpl<T> implements CommonDAO<T> {

  protected SessionFactory factory = SessionFactoryInitializer.getInstance().getSessionFacroty();

  @Override
  public void persistObject(T object) {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      session.save(object);
      tx.commit();

    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }


  public T readObject(Class<T> className, Long uid) {
    Session session = factory.openSession();
    T object = null;
    try {
      object = session.get(className, uid);
    } catch (HibernateException e) {
      e.printStackTrace();
    } finally {
      session.close();
    }
    return object;
  }


  public List<T> readAllObjects(Class<T> className) {
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


  public void deleteObject(T object) {

  }


  public void updateObject(T object) {

  }
}
