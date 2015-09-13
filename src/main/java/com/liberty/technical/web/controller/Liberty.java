package com.liberty.technical.web.controller;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.Order;
import com.liberty.technical.logic.entity.OrderVO;
import com.liberty.technical.logic.localization.LocalizationUtil;
import com.liberty.technical.web.util.UserSessionUtils;
import freemarker.template.Configuration;
import spark.*;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;


/**
 * @author M-AKI.
 */
public class Liberty implements SparkApplication
{

    @Override
    public void init() {
        Configuration config = new Configuration();
        config.setClassForTemplateLoading(Liberty.class, "/freemarker/templates");

        FreeMarkerEngine engine = new FreeMarkerEngine();
        engine.setConfiguration(config);


        SparkBase.staticFileLocation("/public");

        get("/", (request, response) -> {
            Session session = request.session();
            Map<String, Object> attributes = new HashMap<>();
            if (!session.isNew()) {
                Order order = session.attribute(UserSessionUtils.ATTRIBUTE_ORDER);
                if (order != null) {
                    attributes.put("order", order);
                    attributes.put("itemCount", order.getItemCount());
                }
            }
            Locale locale = session.attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
            attributes.put("translator", LocalizationUtil.getInstance(locale));

            return new ModelAndView(attributes, "index.ftl");
        }, engine);

//        get("/", new Route() {
//            @Override
//            public Object handle(Request request, Response response)  {
//                halt();
//                return null;
//            }
//        }
//        , null);





        post("/getItem", (request, response) -> {
            List<Item> items = UserSessionUtils.getItemsBySession(request.session());
            if (items == null) {
                // TODO: throw exception view
            }

            Map<String, Object> attributes = new HashMap<>();
            Long id = new Long( request.queryParams("itemId"));
            Item item = UserSessionUtils.getItemIndexById(items, id);
            attributes.put("selectedItem", item);
            Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
            if (order != null) {
                attributes.put("order", order);
            }
            Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
            attributes.put("translator", LocalizationUtil.getInstance(locale));

            return new ModelAndView(attributes, "common/item-description.ftl");
        }, engine);

        post("/cart", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
            if (order != null) {
                attributes.put("order", order);
                attributes.put("itemCount", order.getItemCount());
                attributes.put("cartItems", order.getItems());
            }
            Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
            attributes.put("translator", LocalizationUtil.getInstance(locale));
            return new ModelAndView(attributes, "common/cart/item-list.ftl");
        }, engine);


        post("/getAllItems", (request, response) -> {
            List<Item> items = UserSessionUtils.getItemsBySession(request.session());
            if (items == null) {
                // TODO: throw exception view
            }

            Double itemWidth = new Double(request.queryParams("itemWidth"));
            Double totalWidth = itemWidth * items.size();
            request.session().attribute("itemWidth", itemWidth);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("items", items);
            attributes.put("itemsSize", items.size());
            attributes.put("itemWidth", itemWidth.toString());
            attributes.put("galleryWidth", totalWidth.toString());
            attributes.put("x", 0);
            Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
            if (order != null) {
                attributes.put("order", order);
            }
            Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
            attributes.put("translator", LocalizationUtil.getInstance(locale));

            return new ModelAndView(attributes, "common/marketing.ftl");
        }, engine);

        Gson gson = new Gson();
        post("/addToCart", (request, response) -> {
            Map<String, Order> attributes = new HashMap<>();
            List<Item> items = UserSessionUtils.getItemsBySession(request.session());
            if (items == null) {
                // TODO: throw exception view
            }
            Long id = new Long( request.queryParams("itemId"));
            Order order = UserSessionUtils.addToCart(request.session(), id);
            String bouquets;
            int count = order.getItems().size();
            if (count == 1) {
                bouquets = LocalizationUtil.getString("bouquet1");
            } else if (count >1 && count <5) {
                bouquets = LocalizationUtil.getString("bouquet24");
            } else {
                bouquets = LocalizationUtil.getString("bouquet5");
            }

            attributes.put("order", order);
            return new OrderVO(order.getAmount(),
                    count,
                    LocalizationUtil.getString("you_have"),
                    bouquets,
                    LocalizationUtil.getString("total_amount"),
                    LocalizationUtil.getString("currency"),
                    LocalizationUtil.getString("item_int_the_cart"));
        }, gson::toJson);

        post("/delivery_info", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();

            Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
            attributes.put("translator", LocalizationUtil.getInstance(locale));
            return new ModelAndView(attributes, "common/cart/delivery.ftl");
        }, engine);


        post("/filter", (request, response) -> {
            Integer categoryId = Integer.parseInt(request.queryParams("filterOption"));
            List<Item> items = UserSessionUtils.filterByCat(categoryId);
            if (items == null) {
                // TODO: throw exception view
            }

            Double itemWidth = request.session().attribute("itemWidth");
            Double totalWidth = itemWidth * items.size();

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("items", items);
            attributes.put("itemsSize", items.size());
            attributes.put("itemWidth", itemWidth.toString());
            attributes.put("galleryWidth", totalWidth.toString());
            attributes.put("x", 0);
            Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
            if (order != null) {
                attributes.put("order", order);
            }
            Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
            attributes.put("translator", LocalizationUtil.getInstance(locale));
            return new ModelAndView(attributes, "common/marketing.ftl");
        }, engine);
    }

    public static void main(String[] args)
    {
//        Configuration config = new Configuration();
//        config.setClassForTemplateLoading(Liberty.class, "/freemarker/templates");
//
//        FreeMarkerEngine engine = new FreeMarkerEngine();
//        engine.setConfiguration(config);
//
//
//        SparkBase.staticFileLocation("/public");
//
//        get("/", (request, response) -> {
//            Session session = request.session();
//            Map<String, Object> attributes = new HashMap<>();
//            if (!session.isNew()) {
//                Order order = session.attribute(UserSessionUtils.ATTRIBUTE_ORDER);
//                if (order != null) {
//                    attributes.put("order", order);
//                    attributes.put("itemCount", order.getItemCount());
//                }
//            }
//            Locale locale = session.attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//            attributes.put("translator", LocalizationUtil.getInstance(locale));
//
//            return new ModelAndView(attributes, "index.ftl");
//        }, engine);
//
////        get("/", new Route() {
////            @Override
////            public Object handle(Request request, Response response)  {
////                halt();
////                return null;
////            }
////        }
////        , null);
//
//
//
//
//
//        post("/getItem", (request, response) -> {
//            List<Item> items = UserSessionUtils.getItemsBySession(request.session());
//            if (items == null) {
//                // TODO: throw exception view
//            }
//
//            Map<String, Object> attributes = new HashMap<>();
//            Long id = new Long( request.queryParams("itemId"));
//            Item item = UserSessionUtils.getItemIndexById(items, id);
//            attributes.put("selectedItem", item);
//            Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
//            if (order != null) {
//                attributes.put("order", order);
//            }
//            Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//            attributes.put("translator", LocalizationUtil.getInstance(locale));
//
//            return new ModelAndView(attributes, "common/item-description.ftl");
//        }, engine);
//
//        post("/cart", (request, response) -> {
//            Map<String, Object> attributes = new HashMap<>();
//            Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
//            if (order != null) {
//                attributes.put("order", order);
//                attributes.put("itemCount", order.getItemCount());
//                attributes.put("cartItems", order.getItems());
//            }
//            Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//            attributes.put("translator", LocalizationUtil.getInstance(locale));
//            return new ModelAndView(attributes, "common/cart/item-list.ftl");
//        }, engine);
//
//
//        post("/getAllItems", (request, response) -> {
//            List<Item> items = UserSessionUtils.getItemsBySession(request.session());
//            if (items == null) {
//                // TODO: throw exception view
//            }
//
//            Double itemWidth = new Double(request.queryParams("itemWidth"));
//            Double totalWidth = itemWidth * items.size();
//            request.session().attribute("itemWidth", itemWidth);
//
//            Map<String, Object> attributes = new HashMap<>();
//            attributes.put("items", items);
//            attributes.put("itemsSize", items.size());
//            attributes.put("itemWidth", itemWidth.toString());
//            attributes.put("galleryWidth", totalWidth.toString());
//            attributes.put("x", 0);
//            Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
//            if (order != null) {
//                attributes.put("order", order);
//            }
//            Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//            attributes.put("translator", LocalizationUtil.getInstance(locale));
//
//            return new ModelAndView(attributes, "common/marketing.ftl");
//        }, engine);
//
//        Gson gson = new Gson();
//        post("/addToCart", (request, response) -> {
//            Map<String, Order> attributes = new HashMap<>();
//            List<Item> items = UserSessionUtils.getItemsBySession(request.session());
//            if (items == null) {
//                // TODO: throw exception view
//            }
//            Long id = new Long( request.queryParams("itemId"));
//            Order order = UserSessionUtils.addToCart(request.session(), id);
//            String bouquets;
//            int count = order.getItems().size();
//            if (count == 1) {
//                bouquets = LocalizationUtil.getString("bouquet1");
//            } else if (count >1 && count <5) {
//                bouquets = LocalizationUtil.getString("bouquet24");
//            } else {
//                bouquets = LocalizationUtil.getString("bouquet5");
//            }
//
//            attributes.put("order", order);
//            return new OrderVO(order.getAmount(),
//                count,
//                LocalizationUtil.getString("you_have"),
//                bouquets,
//                LocalizationUtil.getString("total_amount"),
//                LocalizationUtil.getString("currency"),
//                LocalizationUtil.getString("item_int_the_cart"));
//        }, gson::toJson);
//
//        post("/delivery_info", (request, response) -> {
//            Map<String, Object> attributes = new HashMap<>();
//
//            Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//            attributes.put("translator", LocalizationUtil.getInstance(locale));
//            return new ModelAndView(attributes, "common/cart/delivery.ftl");
//        }, engine);
//
//
//        post("/filter", (request, response) -> {
//            Integer categoryId = Integer.parseInt(request.queryParams("filterOption"));
//            List<Item> items = UserSessionUtils.filterByCat(categoryId);
//            if (items == null) {
//                // TODO: throw exception view
//            }
//
//            Double itemWidth = request.session().attribute("itemWidth");
//            Double totalWidth = itemWidth * items.size();
//
//            Map<String, Object> attributes = new HashMap<>();
//            attributes.put("items", items);
//            attributes.put("itemsSize", items.size());
//            attributes.put("itemWidth", itemWidth.toString());
//            attributes.put("galleryWidth", totalWidth.toString());
//            attributes.put("x", 0);
//            Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
//            if (order != null) {
//                attributes.put("order", order);
//            }
//            Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//            attributes.put("translator", LocalizationUtil.getInstance(locale));
//            return new ModelAndView(attributes, "common/marketing.ftl");
//        }, engine);



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
