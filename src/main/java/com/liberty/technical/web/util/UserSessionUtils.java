package com.liberty.technical.web.util;

import com.liberty.technical.logic.dao.ItemDAO;
import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.Order;
import com.liberty.technical.logic.factory.DaoFactory;
import com.liberty.technical.logic.factory.SessionFactoryInitializer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.*;

/**
 *  @author M-AKI
 */
public class UserSessionUtils {
    public final static String ATTRIBUTE_ORDER = "getOrder";
    public final static String ATTRIBUTE_USER = "getUser";
    public final static String ATTRIBUTE_DELIVERY_INFO = "getDelvInfo";
    public final static String ATTRIBUTE_ALL_ITEMS_LIST = "sessionItemList";
    public final static String ATTRIBUTE_LOCALE = "locale";

    public static List<Item> getAllItems()
    {
        ItemDAO itemDAO = DaoFactory.getInstance().createItemDAO();
        return itemDAO.readAllItems();
    }

    public static synchronized void storeOrder()
    {

    }


    public static Item getItemIndexById(List items, Long id)
    {
      for (int i = 0; i < items.size(); i++) {
        Item item = (Item)items.get(i);
        if (item.getId() == id) {
          return item;
        }
      }
      return null;
    }

    public static List getItemsBySession(spark.Session session)
    {
      List items = null;
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
        Set items = null;
        Order order = session.attribute(ATTRIBUTE_ORDER);
        if (order != null) {
            items = order.getItems();
        } else {
            order = new Order();
            items = new HashSet();
        }
        Item item = getItemById(getItemsBySession(session), itemId);
        items.add(item);
        order.setItems(items);
        order.setAmount(calculateOrderAmount(items));
        session.attribute(ATTRIBUTE_ORDER, order);
        return order;
    }

    private static int calculateOrderAmount (Set items)
    {
        int sum = 0;
        Iterator it = items.iterator();
        while (it.hasNext()) {
            Item item = (Item)it.next();
            sum += item.getPrice();
        }
        return sum;
    }

    private static Item getItemById(List items, Long id)
    {
        for (int i = 0; i < items.size(); i++) {
            Item item = (Item)items.get(i);
            if (item.getId() == id.longValue()) {
                return item;
            }
        }
        return null;
    }

    public static List<Item> filterByCat(int categoryId)
    {
        List resultList = null;
        SessionFactory factory = SessionFactoryInitializer.getInstance().getSessionFacroty();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            resultList = session.createQuery(" select itm from Item itm join itm.categories chi " +
                    "where chi.id = " + categoryId).
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
