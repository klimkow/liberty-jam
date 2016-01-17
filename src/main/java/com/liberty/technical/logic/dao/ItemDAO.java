package com.liberty.technical.logic.dao;

import com.liberty.technical.logic.entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AKI
 */
public class ItemDAO extends CommonDAOImpl<Item>
{
  public List<Item> filterByCatAndPrice(Integer categoryId, Integer pFrom, Integer pTo)
  {
    List<Item> resultList = new ArrayList<>();
    Session session = factory.openSession();
    Transaction tx = null;
    String categoryCriteria = categoryId == null ? "" : " and chi.name like '" + getCategoryName(categoryId) + "'";
    try {
      tx = session.beginTransaction();
      resultList = session.createQuery(" select itm from Item itm join itm.categories chi " +
          "where itm.price between " + pFrom + " and " + pTo + categoryCriteria).
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


  public List<Item> filterByPrice(Integer pFrom, Integer pTo)
  {
    List<Item> resultList = new ArrayList<>();
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      resultList = session.createQuery(" select itm from Item itm " +
          "where itm.price between " + pFrom + " and " + pTo).
          list();
      tx.commit();

    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return resultList;
  }


  public List<Item> filterByCat(int categoryId)
  {
    List<Item> resultList = new ArrayList<>();
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      resultList = session.createQuery(" select itm from Item itm join itm.categories chi " +
          "where chi.name like '" + getCategoryName(categoryId) + "'").
          list();
      tx.commit();

    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return resultList;
  }


  private String getCategoryName(int i)
  {
    String name = "";
    switch (i) {
      case 1:
        name = "classic";
        break;
      case 2:
        name = "box";
        break;
      case 3:
        name = "vase";
        break;
    }
    return name;
  }

}
