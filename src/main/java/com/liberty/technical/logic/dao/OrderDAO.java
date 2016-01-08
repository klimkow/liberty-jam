package com.liberty.technical.logic.dao;

import com.liberty.technical.logic.entity.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


/**
 * @author M-AKI.
 */
public class OrderDAO extends CommonDAOImpl<Order>
{
  public List<Order> readAllOrders()
  {
    List<Order> resultList = new ArrayList<>();
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      resultList = session.createQuery(" select ord from Order ord ").
          list();
      tx.commit();

    } catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return resultList;
  }
}
