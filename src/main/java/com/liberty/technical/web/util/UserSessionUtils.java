package com.liberty.technical.web.util;

import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.Order;
import com.liberty.technical.logic.factory.SessionFactoryInitializer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  @author M-AKI
 */
public class UserSessionUtils {
    public final static String ATTRIBUTE_ORDER = "getOrder";
    public final static String ATTRIBUTE_ALL_ITEMS_LIST = "sessionItemList";

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
      List<Item> items = null;
      if (!session.isNew()) {
          items = session.attribute(ATTRIBUTE_ALL_ITEMS_LIST);
      }
      if (items == null) {
          items = UserSessionUtils.getAllItems();
          session.attribute(ATTRIBUTE_ALL_ITEMS_LIST, items);
      }
      return items;
    }

    public static Order addToCart(spark.Session session, Long itemId)
    {
        Set<Item> items = null;
        Order order = session.attribute(ATTRIBUTE_ORDER);
        if (order != null) {
            items = order.getItems();
        } else {
            order = new Order();
            items = new HashSet<>();
        }
        Item item = getItemById(getItemsBySession(session), itemId);
        items.add(item);
        order.setItems(items);
        order.setAmount(calculateOrderAmount(items));
        session.attribute(ATTRIBUTE_ORDER, order);
        return order;
    }

    private static int calculateOrderAmount (Set<Item> items)
    {
        int sum = 0;
        for (Item item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    private static Item getItemById(List<Item> items, Long id)
    {
        for (Item i : items) {
            if (i.getId() == id.longValue()) {
                return i;
            }
        }
        return null;
    }
}
