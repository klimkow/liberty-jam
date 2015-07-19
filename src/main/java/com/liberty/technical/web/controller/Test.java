package com.liberty.technical.web.controller;

import static spark.Spark.*;
import com.liberty.technical.logic.entity.*;
import com.liberty.technical.logic.factory.SessionFactoryInitializer;
import freemarker.template.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author M-AKI.
 */
public class Test
{

  public static void main(String[] args)
  {
    Configuration config = new Configuration();
    config.setClassForTemplateLoading(Test.class, "/freemarker/templates");

    FreeMarkerEngine engine = new FreeMarkerEngine();
    engine.setConfiguration(config);

    get("/hello/:words", (request, response) -> {
      Map<String, Object> attributes = new HashMap<String, Object>();
      attributes.put("message", request.params(":words"));

      return new ModelAndView(attributes, "index.ftl");
    }, engine);

//    SessionFactory factory = SessionFactoryInitializer.getInstance().getSessionFacroty();
//    Session session = factory.openSession();
//    Transaction tx = null;
//    long id = 2;
//    Category cat2 = null;
//    try{
//      tx = session.beginTransaction();
//
//      Item item = new Item();
//      item.setName("item1");
//      item.setDescription("desc");
//
//      Item item2 = new Item();
//      item.setName("item2");
//      item.setDescription("desc");
//      session.save(item);
//      session.save(item2);
//
//      Set<Item> items = new HashSet<>();
//      items.add(item);
//      items.add(item2);
//
//      User us = new User();
//      us.setName("Nigga");
//      session.save(us);
//
//      DeliveryInformation dv = new DeliveryInformation();
//      dv.setName("dv");
//
//      Order order = new Order();
//      order.setUser(us);
//      order.setAmount(100500);
//      order.setItems(items);
//      order.setDeliveryInformation(dv);
//      session.save(order);


// This works
//      User us = new User();
//      us.setName("Nigga");
//      session.save(us);
//      DeliveryInformation dv = new DeliveryInformation();
//      dv.setName("dv");
//      Order order = new Order();
//      order.setAmount(150);
//      order.setUser(us);
//      order.setDeliveryInformation(dv);
//
//      id = (long) session.save(order);
//      tx.commit();

//      tx = session.beginTransaction();
//
//      cat2 = (Category) session.get(Category.class, id);
//      tx.commit();

//    }catch (HibernateException e) {
//      if (tx!=null) tx.rollback();
//      e.printStackTrace();
//    }finally {
//      session.close();
//    }
//
//    System.out.println(cat2 == null ? "null" : cat2.getDescription());
  }
}
