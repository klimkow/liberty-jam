package com.liberty.technical.web.controller;

import static spark.Spark.*;

import com.liberty.technical.logic.entity.Category;
import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.images.ItemImages;
import com.liberty.technical.logic.factory.SessionFactoryInitializer;
import com.liberty.technical.logic.util.LibertyBaseUtils;
import freemarker.template.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;


/**
 * @author M-AKI.
 */
public class Liberty
{

  public static void main(String[] args)
  {
    Configuration config = new Configuration();
    config.setClassForTemplateLoading(Liberty.class, "/freemarker/templates");

    FreeMarkerEngine engine = new FreeMarkerEngine();
    engine.setConfiguration(config);


    SparkBase.staticFileLocation("/public");
    List<Item> items = LibertyBaseUtils.getAllItems();

    get("/", (request, response) -> {

      Map<String, Object> attributes = new HashMap<String, Object>();
      attributes.put("items", items);

      return new ModelAndView(attributes, "index.ftl");
    }, engine);


    post("/getItem", (request, response) -> {
      Map<String, Item> attributes = new HashMap<>();
      Long id = new Long( request.queryParams("itemId"));
      Item item = LibertyBaseUtils.getItemIndexById(items, id);
      attributes.put("selectedItem", item);

      return new ModelAndView(attributes, "common/item-description.ftl");
    }, engine);


    post("/getAllItem", (request, response) -> {
      Map<String, Object> attributes = new HashMap<String, Object>();
      attributes.put("message", "Alex");

      return new ModelAndView(attributes, "common/item-description.ftl");
    }, engine);





//    SessionFactory factory = SessionFactoryInitializer.getInstance().getSessionFacroty();
//      org.hibernate.Session session = factory.openSession();
//    Transaction tx = null;
//    long id = 2;
//    Category cat2 = null;
//    try {
//      tx = session.beginTransaction();
//
//      Item item = new Item();
//      item.setName("Летняя сказка");
//      item.setDescription("It’s the timeless beauty and elegant simplicity of this arrangement that makes it special. Exquisite large-headed roses, graceful lilies and a beautiful selection of germinis, lisianthus and spray chrysanthemums make up this chic display, and the looped steel grass adds a contemporary twist.");
//      item.setAvailableAmount(10);
//        item.setPrice(700);
//
//        Set<ItemImages> images = new HashSet<>();
//        ItemImages image = new ItemImages();
//        image.setImageUrl("img/b3.jpg");
//        image.setItem(item);
//        images.add(image);
//
//        item.setImages(images);
//        session.save(item);
//        session.save(image);
//
//      Item item2 = new Item();
//      item2.setName("Лунный сонет");
//      item2.setDescription("It’s the timeless beauty and elegant simplicity of this arrangement that makes it special. Exquisite large-headed roses, graceful lilies and a beautiful selection of germinis, lisianthus and spray chrysanthemums make up this chic display, and the looped steel grass adds a contemporary twist.");
//        item2.setAvailableAmount(9);
//        item2.setPrice(1100);
//
//        ItemImages image2 = new ItemImages();
//        image2.setImageUrl("img/b2.jpg");
//        image2.setItem(item2);
//        images.clear();
//        images.add(image2);
//
//         item2.setImages(images);
//
//        session.save(item2);
//        session.save(image2);



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
//
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
