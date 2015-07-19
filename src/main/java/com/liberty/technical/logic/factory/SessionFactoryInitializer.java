package com.liberty.technical.logic.factory;

import com.liberty.technical.logic.entity.*;
import com.liberty.technical.logic.entity.images.CategoryImages;
import com.liberty.technical.logic.entity.images.ItemImages;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * @author M-AKI.
 */
final public class SessionFactoryInitializer
{
  private SessionFactory sessionFactory;


  private SessionFactoryInitializer()
  {

  }


  public static SessionFactoryInitializer getInstance()
  {
    return new SessionFactoryInitializer();
  }


  public SessionFactory getSessionFacroty()
  {
    return new Configuration().configure()
        .addAnnotatedClass(Category.class)
        .addAnnotatedClass(DeliveryInformation.class)
        .addAnnotatedClass(Item.class)
        .addAnnotatedClass(Order.class)
        .addAnnotatedClass(User.class)
        .addAnnotatedClass(ItemImages.class)
        .addAnnotatedClass(CategoryImages.class)
        .buildSessionFactory();
  }
}
