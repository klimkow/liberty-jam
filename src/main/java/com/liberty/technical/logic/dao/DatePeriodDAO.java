package com.liberty.technical.logic.dao;

import com.liberty.technical.logic.entity.service.DeliveryTimePeriod;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author M-AKI.
 */
public class DatePeriodDAO extends CommonDAOImpl<DeliveryTimePeriod>
{
  public List<DeliveryTimePeriod> readAllPeriodsForDate(String date)
  {
    List<DeliveryTimePeriod> resultList = new ArrayList<>();
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      resultList = session.createQuery(" select dt from DeliveryTimePeriod dt where dt.date = '" + date + "'").
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
