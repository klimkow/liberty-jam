package com.liberty.technical.logic.dao;

import com.liberty.technical.logic.entity.content.ContentPage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * @author AKI
 */
public class PageDAO extends CommonDAOImpl<ContentPage>
{
  public ContentPage getContentPage(Integer menuId)
  {
    ContentPage page = null;
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      page = (ContentPage) session.createQuery(" select p from ContentPage p " +
          "where p.menuId = '" + menuId + "'").uniqueResult();
      tx.commit();

    } catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return page;
  }
}
