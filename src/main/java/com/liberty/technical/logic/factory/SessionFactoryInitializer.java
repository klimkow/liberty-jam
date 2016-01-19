package com.liberty.technical.logic.factory;

import com.liberty.technical.logic.entity.*;
import com.liberty.technical.logic.entity.content.ContentPage;
import com.liberty.technical.logic.entity.images.CategoryImages;
import com.liberty.technical.logic.entity.images.ItemImages;
import com.liberty.technical.logic.entity.service.ItemQuantity;
import com.liberty.technical.logic.entity.service.PriceDiapason;
import com.liberty.technical.logic.entity.system.SystemUser;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * @author M-AKI.
 */
public class SessionFactoryInitializer {

  private static SessionFactoryInitializer instanse;
  private SessionFactory factory;


  private SessionFactoryInitializer() {
    Configuration config = new Configuration();
    config.configure()
        .addAnnotatedClass(Category.class)
        .addAnnotatedClass(DeliveryInformation.class)
        .addAnnotatedClass(Item.class)
        .addAnnotatedClass(Order.class)
        .addAnnotatedClass(User.class)
        .addAnnotatedClass(ItemImages.class)
        .addAnnotatedClass(CategoryImages.class)
        .addAnnotatedClass(SystemUser.class)
        .addAnnotatedClass(ItemQuantity.class)
        .addAnnotatedClass(ContentPage.class)
        .addAnnotatedClass(PriceDiapason.class)
        .buildSessionFactory();
    factory = config.buildSessionFactory();
  }


  public static SessionFactoryInitializer getInstance() {
    if (instanse == null) {
      instanse = new SessionFactoryInitializer();
    }
    return instanse;
  }


  public SessionFactory getSessionFacroty() {
    return factory;
  }
}
