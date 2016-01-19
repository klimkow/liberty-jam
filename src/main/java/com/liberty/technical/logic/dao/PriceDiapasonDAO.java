package com.liberty.technical.logic.dao;

import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.service.PriceDiapason;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AKI
 */
public class PriceDiapasonDAO extends CommonDAOImpl<PriceDiapason>
  {
    public List<PriceDiapason> getItemFlexPrices(Long itemId)
    {
      List<PriceDiapason> priceDiapason = new ArrayList<>();
      Session session = factory.openSession();
      Transaction tx = null;
      try {
        tx = session.beginTransaction();
        priceDiapason = session.createQuery(" select p from PriceDiapason p " +
            "where p.item.id = '" + itemId + "'").list();
        tx.commit();

      } catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace();
      } finally {
        session.close();
      }
      return priceDiapason;
    }
  }