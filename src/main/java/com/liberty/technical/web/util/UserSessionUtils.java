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
 * @author M-AKI
 */
public class UserSessionUtils {

  public final static String ATTRIBUTE_USER = "getUser";
  public final static String ATTRIBUTE_DELIVERY_INFO = "getDelvInfo";
  public final static String ATTRIBUTE_ALL_ITEMS_LIST = "sessionItemList";



  public static synchronized void storeOrder() {

  }


  public static Item getItemIndexById(List items, Long id) {
    for (int i = 0; i < items.size(); i++) {
      Item item = (Item) items.get(i);
      if (item.getId() == id) {
        return item;
      }
    }
    return null;
  }

//  public static List getItemsBySession(spark.Session session) {
//    List items = null;
//    if (!session.isNew()) {
//      items = session.attribute(ATTRIBUTE_ALL_ITEMS_LIST);
//    }
//    if (items == null) {
//      items = UserSessionUtils.getAllItems();
//      session.attribute(ATTRIBUTE_ALL_ITEMS_LIST, items);
//    }
//    return items;
//  }


}
