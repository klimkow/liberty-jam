package com.liberty.technical.logic.dao;

import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.factory.SessionFactoryInitializer;
import org.hibernate.*;

import java.util.List;

/**
 * @author AKI
 */
public class ItemDAO {
    SessionFactory factory = SessionFactoryInitializer.getInstance().getSessionFacroty();

    public Item readItem(Long uid)
    {
        Session session = factory.openSession();
        Item item = null;
        try {
            item = session.get(Item.class, uid);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return item;
    }


    public List<Item> readAllItems()
    {
        List<Item> resultList = null;
        Session session = factory.openSession();
        try {
            resultList = session.createCriteria(Item.class).list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return resultList;
    }

}
