package com.liberty.technical.web.controller;

import static spark.Spark.*;
import com.liberty.technical.logic.entity.*;
import com.liberty.technical.logic.entity.images.ItemImages;
import com.liberty.technical.logic.factory.SessionFactoryInitializer;
import freemarker.template.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import spark.*;
import spark.servlet.SparkFilter;
import spark.template.freemarker.FreeMarkerEngine;
import com.liberty.technical.logic.localization.LocalizationUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;


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


    SparkBase.staticFileLocation("/public");

    get("/", (request, response) -> {
      Map<String, Object> attributes = new HashMap<String, Object>();
      attributes.put("message", "Alex");

      return new ModelAndView(attributes, "index.ftl");
    }, engine);

    post("/getItem", (request, response) -> {
      Map<String, Object> attributes = new HashMap<String, Object>();
      attributes.put("message", "Alex");

      return new ModelAndView(attributes, "common/item-description.ftl");
    }, engine);





//    SessionFactory factory = SessionFactoryInitializer.getInstance().getSessionFacroty();
//    Session session = factory.openSession();
//    Transaction tx = null;
//    long id = 2;
//    Category cat2 = null;
//    try {
//      tx = session.beginTransaction();
//
//      Item item = new Item();
//      item.setName("Ласковый рассвет");
//      item.setDescription("With its fresh, zingy shades of lime, hot pink and vibrant yellow, this hand-tied bouquet is perfect for a celebration or simply to make someone you care about feel great. Sunflowers always prompt a smile and are definitely a summer favourite too. Featuring blue agapanthus, sunflowers, cerise gerbera, yellow spray chrysanthemums and green alchemilla mollis with pittosporum and salal, trimmed with a lime ribbon.");
//      item.setAvailableAmount(10);
//        item.setPrice(1500);
//
//        Set<ItemImages> images = new HashSet<>();
//        ItemImages image = new ItemImages();
//        image.setImageUrl("img/b1.jpg");
//        image.setItem(item);
//        images.add(image);
//
//        item.setImages(images);


//      Item item2 = new Item();
//      item.setName("item2");
//      item.setDescription("desc");
//         session.save(item);
//        session.save(image);


//      session.save(item2);

//      Set<Item> items = new HashSet<>();
//      items.add(item);
//      items.add(item2);

//      User us = new User();
//      us.setName("Nigga");
//      session.save(us);

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
