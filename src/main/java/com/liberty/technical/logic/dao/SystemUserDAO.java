package com.liberty.technical.logic.dao;

import com.liberty.technical.logic.entity.system.SystemUser;
import com.liberty.technical.logic.util.SecurityUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * @author M-AKI.
 */
public class SystemUserDAO extends CommonDAOImpl
{
  public SystemUser getUser(String name, String password)
  {
    SystemUser user = null;
    Session session = factory.openSession();
    Transaction tx = null;
    String hashedPass = SecurityUtil.getStringMD5Hash(password);
    try {
      tx = session.beginTransaction();
      Query query = session.createQuery(" select usr from SystemUser usr where usr.name = '" + name + "' and usr.password = '" + hashedPass + "'");
      user = (SystemUser) query.uniqueResult();
      tx.commit();

    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return user;
  }

}
