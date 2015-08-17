package com.liberty.technical.logic.util;

import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.factory.SessionFactoryInitializer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: klimkou
 * Date: 8/17/15
 * Time: 1:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class LibertyBaseUtils {

    public static List<Item> getAllItems()
    {
        List<Item> resultList = null;
        SessionFactory factory = SessionFactoryInitializer.getInstance().getSessionFacroty();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
          tx = session.beginTransaction();
          resultList = session.createCriteria(Item.class).list();
          tx.commit();

        } catch (HibernateException e) {
          if (tx!=null) tx.rollback();
          e.printStackTrace();
        } finally {
          session.close();
        }
        return resultList;
    }

    public static Item getItemIndexById(List<Item> items, Long id)
    {
      for (Item item : items) {
        if (item.getId() == id) {
          return item;
        }
      }
      return null;
    }

    public static List<Item> getItemsBySession(spark.Session session)
    {
      List<Item> items;
      if(session.isNew()) {
        items = LibertyBaseUtils.getAllItems();
        session.attribute("sessionItemList", items);
      } else {
        items = session.attribute("sessionItemList");
      }
      return items;
    }
}
