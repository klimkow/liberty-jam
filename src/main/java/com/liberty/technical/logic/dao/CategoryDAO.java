package com.liberty.technical.logic.dao;

import com.liberty.technical.logic.entity.Category;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author AKI
 */
public class CategoryDAO extends CommonDAOImpl<Category>
{
  public Category getCategoryByName(String name)
  {
    Category category = null;
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      category = (Category) session.createQuery(" select cat from Category cat " +
          "where cat.name = '" + name + "'").uniqueResult();
      tx.commit();

    } catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return category;
  }
}
