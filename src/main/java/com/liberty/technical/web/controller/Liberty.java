package com.liberty.technical.web.controller;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.liberty.technical.logic.dao.CommonDAO;
import com.liberty.technical.logic.dao.ItemDAO;
import com.liberty.technical.logic.entity.*;
import com.liberty.technical.logic.entity.system.SystemUser;
import com.liberty.technical.logic.factory.DaoFactory;
import com.liberty.technical.logic.factory.ServiceFactory;
import com.liberty.technical.logic.localization.LocalizationUtil;
import com.liberty.technical.logic.service.AuthenticationService;
import com.liberty.technical.logic.service.GenericeCartService;
import com.liberty.technical.logic.util.OrderUtils;
import com.liberty.technical.web.SharedConstants;
import com.liberty.technical.web.util.UserSessionUtils;
import freemarker.template.Configuration;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author M-AKI.
 */
public class Liberty {

//    @Override
//    public void init()
//    {
//      Configuration config = new Configuration();
//      config.setClassForTemplateLoading(Liberty.class, "/freemarker/templates");
//
//      FreeMarkerEngine engine = new FreeMarkerEngine();
//      engine.setConfiguration(config);
//
//
//      SparkBase.staticFileLocation("/public");
//
//      get("/administrator", (request, response) -> {
//
//        return new ModelAndView(null, "admin/index.ftl");
//
//      }, engine);
//
//      get("/tables", (request, response) -> {
//        List<Order> orderList = DaoFactory.getInstance().
//            createCommonDAO().readAllObjects(Order.class);
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("orders", orderList);
//        return new ModelAndView(attributes, "admin/orders.ftl");
//
//      }, engine);
//
//      get("/", (request, response) -> {
//        Session session = request.session();
//        Map<String, Object> attributes = new HashMap<>();
//        if (!session.isNew()) {
//          Order order = session.attribute(UserSessionUtils.ATTRIBUTE_ORDER);
//          if (order != null) {
//            attributes.put("order", order);
//            attributes.put("itemCount", order.getItemCount());
//          }
//        }
//        Locale locale = session.attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//        attributes.put("translator", LocalizationUtil.getInstance(locale));
//
//        return new ModelAndView(attributes, "index.ftl");
//      }, engine);
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
//      post("/getItem", (request, response) -> {
//        List<Item> items = UserSessionUtils.getItemsBySession(request.session());
//        if (items == null) {
//          // TODO: throw exception view
//        }
//
//        Map<String, Object> attributes = new HashMap<>();
//        Long id = new Long(request.queryParams("itemId"));
//        Item item = UserSessionUtils.getItemIndexById(items, id);
//        attributes.put("selectedItem", item);
//        Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
//        if (order != null) {
//          attributes.put("order", order);
//        }
//        Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//        attributes.put("translator", LocalizationUtil.getInstance(locale));
//
//        return new ModelAndView(attributes, "common/item-description.ftl");
//      }, engine);
//
//      post("/cart", (request, response) -> {
//        Map<String, Object> attributes = new HashMap<>();
//        Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
//        if (order != null) {
//          attributes.put("order", order);
//          attributes.put("itemCount", order.getItemCount());
//          attributes.put("cartItems", order.getItems());
//        }
//        Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//        attributes.put("translator", LocalizationUtil.getInstance(locale));
//        return new ModelAndView(attributes, "common/cart/item-list.ftl");
//      }, engine);
//
//
//      post("/getAllItems", (request, response) -> {
//        List<Item> items = UserSessionUtils.getItemsBySession(request.session());
//        if (items == null) {
//          // TODO: throw exception view
//        }
//
//        Double itemWidth = new Double(request.queryParams("itemWidth"));
//        Double totalWidth = itemWidth * items.size();
//        request.session().attribute("itemWidth", itemWidth);
//
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("items", items);
//        attributes.put("itemsSize", items.size());
//        attributes.put("itemWidth", itemWidth.toString());
//        attributes.put("galleryWidth", totalWidth.toString());
//        attributes.put("x", 0);
//        Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
//        if (order != null) {
//          attributes.put("order", order);
//        }
//        Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//        attributes.put("translator", LocalizationUtil.getInstance(locale));
//
//        return new ModelAndView(attributes, "common/marketing.ftl");
//      }, engine);
//
//      Gson gson = new Gson();
//      post("/addToCart", (request, response) -> {
//        Map<String, Order> attributes = new HashMap<>();
//        List<Item> items = UserSessionUtils.getItemsBySession(request.session());
//        if (items == null) {
//          // TODO: throw exception view
//        }
//        Long id = new Long(request.queryParams("itemId"));
//        Order order = UserSessionUtils.addToCart(request.session(), id);
//        String bouquets;
//        int count = order.getItems().size();
//        if (count == 1) {
//          bouquets = LocalizationUtil.getString("bouquet1");
//        }
//        else if (count > 1 && count < 5) {
//          bouquets = LocalizationUtil.getString("bouquet24");
//        }
//        else {
//          bouquets = LocalizationUtil.getString("bouquet5");
//        }
//
//        attributes.put("order", order);
//        return new OrderVO(order.getAmount(),
//            count,
//            LocalizationUtil.getString("you_have"),
//            bouquets,
//            LocalizationUtil.getString("total_amount"),
//            LocalizationUtil.getString("currency"),
//            LocalizationUtil.getString("item_int_the_cart"));
//      }, gson::toJson);
//
//      post("/delivery_info", (request, response) -> {
//        Map<String, Object> attributes = new HashMap<>();
//
//        Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//        attributes.put("translator", LocalizationUtil.getInstance(locale));
//        return new ModelAndView(attributes, "common/cart/delivery.ftl");
//      }, engine);
//
//
//      post("/payment_step", (request, response) -> {
//        Map<String, Object> attributes = new HashMap<>();
//
//        DeliveryInformation info = new DeliveryInformation();
//        info.setName(request.queryParams(SharedConstants.DELIVERY_NAME_TO));
//        info.setPhone(request.queryParams(SharedConstants.DELIVERY_PHONE_TO));
//        info.setMessage(request.queryParams(SharedConstants.DELIVERY_MESSAGE));
//        request.session().attribute(UserSessionUtils.ATTRIBUTE_DELIVERY_INFO, info);
//
//        User user = new User();
//        user.setName(request.queryParams(SharedConstants.DELIVERY_NAME_FROM));
//        user.setPhone(request.queryParams(SharedConstants.DELIVERY_PHONE_FROM));
//        user.setEmail(request.queryParams(SharedConstants.DELIVERY_EMAIL_FROM));
//        request.session().attribute(UserSessionUtils.ATTRIBUTE_USER, user);
//
//        Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
//        order.setDeliveryInformation(info);
//        order.setUser(user);
//
//        Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//        attributes.put("translator", LocalizationUtil.getInstance(locale));
//        return new ModelAndView(attributes, "common/cart/payment.ftl");
//      }, engine);
//
//
//      post("/filter", (request, response) -> {
//        String catId = request.queryParams("filterOption");
//        Integer categoryId = catId == null ? null : Integer.parseInt(catId);
//
//        if (categoryId != null) {
//          request.session().attribute("categoryId", categoryId);
//        }
//
//        String priceFrom = request.queryParams("price_from") == null ?
//            null : request.queryParams("price_from").replaceAll("\\.", "").replaceFirst("000", "");
//        String priceTo = request.queryParams("price_to") == null ?
//            null : request.queryParams("price_to").replaceAll("\\.", "").replaceFirst("000", "");
//        Integer pFrom = Integer.parseInt(priceFrom);
//        Integer pTo = Integer.parseInt(priceTo);
//
//        List<Item> items = UserSessionUtils.filterByCatAndPrice(request.session().attribute("categoryId"), pFrom, pTo);
//        ;
//
////            List<Item> items = UserSessionUtils.filterByCat(categoryId);
//
//
//        if (items == null) {
//          // TODO: throw exception view
//        }
//
//        Double itemWidth = new Double(request.queryParams("itemWidth"));
//        Double totalWidth = itemWidth * items.size();
//        request.session().attribute("itemWidth", itemWidth);
//
//
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("items", items);
//        attributes.put("itemsSize", items.size());
//        attributes.put("itemWidth", itemWidth.toString());
//        attributes.put("galleryWidth", totalWidth.toString());
//        attributes.put("x", 0);
//        Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
//        if (order != null) {
//          attributes.put("order", order);
//        }
//        Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//        attributes.put("translator", LocalizationUtil.getInstance(locale));
//        String viewName = items.size() > 0 ?
//            "common/marketing.ftl" : "common/marketing-empty-view.ftl";
//        return new ModelAndView(attributes, viewName);
//      }, engine);
//
//
//      post("/getCategoryItems", (request, response) -> {
//        Integer categoryId = Integer.parseInt(request.queryParams("categoryId"));
//        List<Item> items = UserSessionUtils.filterByCat(categoryId);
//        if (items == null) {
//          // TODO: throw exception view
//        }
//
//        Double itemWidth = new Double(request.queryParams("itemWidth"));
//        Double totalWidth = itemWidth * items.size();
//        request.session().attribute("itemWidth", itemWidth);
//
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("items", items);
//        attributes.put("itemsSize", items.size());
//        attributes.put("itemWidth", itemWidth.toString());
//        attributes.put("galleryWidth", totalWidth.toString());
//        attributes.put("x", 0);
//        Order order = request.session().attribute(UserSessionUtils.ATTRIBUTE_ORDER);
//        if (order != null) {
//          attributes.put("order", order);
//        }
//        Locale locale = request.session().attribute(UserSessionUtils.ATTRIBUTE_LOCALE);
//        attributes.put("translator", LocalizationUtil.getInstance(locale));
//        String viewName = items.size() > 0 ?
//            "common/marketing.ftl" : "common/marketing-empty-view.ftl";
//        return new ModelAndView(attributes, viewName);
//      }, engine);
//    }

  public static void main(String[] args) {
    Configuration config = new Configuration();
    config.setClassForTemplateLoading(Liberty.class, "/freemarker/templates");

    FreeMarkerEngine engine = new FreeMarkerEngine();
    engine.setConfiguration(config);


    SparkBase.staticFileLocation("/public");



    get("/", (request, response) -> {
      Session session = request.session();
      Map<String, Object> attributes = new HashMap<>();
      Order order = session.attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order != null) {
        attributes.put("order", order);
        attributes.put("itemCount", order.getItemCount());
      }
      Locale locale = session.attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));

      return new ModelAndView(attributes, "index.ftl");
    }, engine);


    post("/openPage", (request, response) -> {
      Map<String, Object> attributes = new HashMap<>();
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      Integer pageNumber = new Integer(request.queryParams("page"));
      return new ModelAndView(attributes, "common/pages/about.ftl");
    }, engine);


    post("/changeLang", (request, response) -> {
      String lang = request.queryParams("lang");
      request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE, new Locale(lang));
      Session session = request.session();
      Map<String, Object> attributes = new HashMap<>();
      Order order = session.attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order != null) {
        attributes.put("order", order);
        attributes.put("itemCount", order.getItemCount());
      }
      Locale locale = session.attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      response.redirect("/");
      return new ModelAndView(attributes, "index.ftl");
    }, engine);


    post("/getItem", (request, response) -> {
      CommonDAO<Item> itemCommonDAO = DaoFactory.getInstance().createCommonDAO(Item.class);

      Map<String, Object> attributes = new HashMap<>();
      Long id = new Long(request.queryParams("itemId"));
      Item item = itemCommonDAO.readObject(Item.class, id);
      attributes.put("selectedItem", item);
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order != null) {
        attributes.put("order", order);
      }
      attributes.put("itemId", id);
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));

      return new ModelAndView(attributes, "common/item-description.ftl");
    }, engine);


    get("/cart", (request, response) -> {
      Map<String, Object> attributes = new HashMap<>();
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order != null) {
        attributes.put("order", order);
        attributes.put("itemCount", order.getItemCount());
        attributes.put("cartItems", order.getItems());
      }
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      return new ModelAndView(attributes, "common/cart/index-cart.ftl");
    }, engine);


    post("/cart", (request, response) -> {
      Map<String, Object> attributes = new HashMap<>();
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order != null) {
        attributes.put("order", order);
        attributes.put("itemCount", order.getItemCount());
        attributes.put("cartItems", order.getItems());
      }
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      return new ModelAndView(attributes, "common/cart/item-list.ftl");
    }, engine);


    post("/removeItem", (request, response) -> {
      GenericeCartService cartService = ServiceFactory.getInstanse().createCartService();
      Map<String, Object> attributes = new HashMap<>();
      Long id = new Long(request.queryParams("id"));
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order != null) {
        cartService.removeItemFromOrder(order, id);
        attributes.put("order", order);
        attributes.put("itemCount", order.getItemCount());
        attributes.put("cartItems", order.getItems());
      }
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      String path = order != null && order.getItemCount() > 0 ? "item-list.ftl" : "empty-stub.ftl";
      return new ModelAndView(attributes, "common/cart/" + path);
    }, engine);


    post("/getAllItems", (request, response) -> {
      CommonDAO<Item> itemCommonDAO = DaoFactory.getInstance().createCommonDAO(Item.class);
      List<Item> items = itemCommonDAO.readAllObjects(Item.class);
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
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order != null) {
        attributes.put("order", order);
      }
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));

      return new ModelAndView(attributes, "common/marketing.ftl");
    }, engine);


    Gson gson = new Gson();
    post("/addToCart", (request, response) -> {
      GenericeCartService cartService = ServiceFactory.getInstanse().createCartService();
      Map<String, Order> attributes = new HashMap<>();
      CommonDAO<Item> itemCommonDAO = DaoFactory.getInstance().createCommonDAO(Item.class);
      List<Item> items = itemCommonDAO.readAllObjects(Item.class);
      if (items == null) {
        // TODO: throw exception view
      }
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order == null) {
        order = new Order();
      }
      Long id = new Long(request.queryParams("itemId"));
      cartService.addToCart(order, id);
      request.session().attribute(SharedConstants.ATTRIBUTE_ORDER, order);

      String bouquets;
      int count = order.getItems().size();
      if (count == 1) {
        bouquets = LocalizationUtil.getString("bouquet1");
      } else if (count > 1 && count < 5) {
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


    post("/updateNavbar", (request, response) -> {
      Order order = request.session()
          .attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order == null) {
        // throw exception view
      }

      int count = order.getItems().size();
      String bouquets;
      if (count == 1) {
        bouquets = LocalizationUtil.getString("bouquet1");
      } else if (count > 1 && count < 5) {
        bouquets = LocalizationUtil.getString("bouquet24");
      } else {
        bouquets = LocalizationUtil.getString("bouquet5");
      }

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

      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order == null) {
        // return exception view
      }

      if (order.getUser() != null) {
        attributes.put("user", order.getUser());
      }
      if (order.getDeliveryInformation() != null) {
        attributes.put("delivery", order.getDeliveryInformation());
      }

      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      return new ModelAndView(attributes, "common/cart/delivery.ftl");
    }, engine);


    post("/payment_step", (request, response) -> {
      Map<String, Object> attributes = new HashMap<>();

      DeliveryInformation info = new DeliveryInformation();
      info.setName(request.queryParams(SharedConstants.DELIVERY_NAME_TO));
      info.setPhone(request.queryParams(SharedConstants.DELIVERY_PHONE_TO));
      info.setMessage(request.queryParams(SharedConstants.DELIVERY_MESSAGE));
      StringBuilder address = new StringBuilder();
      address.append(request.queryParams(SharedConstants.DELIVERY_ADDRESS)).
          append("-").
          append(request.queryParams(SharedConstants.DELIVERY_ADDRESS_HOUSE)).
          append("-").
          append(request.queryParams(SharedConstants.DELIVERY_ADDRESS_FLOR));
      info.setAddress(address.toString());
      String dateParam = request.queryParams(SharedConstants.DELIVERY_DATE);
      if (dateParam != null && !dateParam.isEmpty()) {
        Date date = new Date(dateParam);
        info.setDeliveryDate(date);
      }
//        request.session().attribute(UserSessionUtils.ATTRIBUTE_DELIVERY_INFO, info);

      User user = new User();
      user.setName(request.queryParams(SharedConstants.DELIVERY_NAME_FROM));
      user.setPhone(request.queryParams(SharedConstants.DELIVERY_PHONE_FROM));
      user.setEmail(request.queryParams(SharedConstants.DELIVERY_EMAIL_FROM));
//        request.session().attribute(UserSessionUtils.ATTRIBUTE_USER, user);

      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      order.setDeliveryInformation(info);
      order.setUser(user);

      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      return new ModelAndView(attributes, "common/cart/payment.ftl");
    }, engine);


    post("/filter", (request, response) -> {
      ItemDAO itemDAO = DaoFactory.getInstance().createItemDAO();
      String catId = request.queryParams("filterOption");
      Integer categoryId = catId == null ? null : Integer.parseInt(catId);

      if (categoryId != null) {
        request.session().attribute("categoryId", categoryId);
      } else if (request.session().attribute("categoryId") != null) {
        categoryId = request.session().attribute("categoryId");
      }

      String priceFrom = request.queryParams("price_from") == null ?
          null : request.queryParams("price_from").replaceAll("\\.", "").replaceFirst("000", "");
      String priceTo = request.queryParams("price_to") == null ?
          null : request.queryParams("price_to").replaceAll("\\.", "").replaceFirst("000", "");

      List<Item> items = new ArrayList<>();
      if (categoryId == null && priceFrom != null && priceTo != null) {
        Integer pFrom = Integer.parseInt(priceFrom);
        Integer pTo = Integer.parseInt(priceTo);
        items = itemDAO.filterByPrice(pFrom, pTo);
      } else if (priceFrom == null && priceTo == null && categoryId != null) {
        items = itemDAO.filterByCat(categoryId);
      } else if (categoryId != null && priceFrom != null && priceTo != null) {
        Integer pFrom = Integer.parseInt(priceFrom);
        Integer pTo = Integer.parseInt(priceTo);
        items = itemDAO.filterByCatAndPrice(categoryId, pFrom, pTo);
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
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order != null) {
        attributes.put("order", order);
      }
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      String viewName = items.size() > 0 ?
          "common/marketing.ftl" : "common/marketing-empty-view.ftl";
      return new ModelAndView(attributes, viewName);
    }, engine);


    post("/getCategoryItems", (request, response) -> {
      ItemDAO itemDAO = DaoFactory.getInstance().createItemDAO();
      Integer categoryId = Integer.parseInt(request.queryParams("categoryId"));
      List<Item> items = itemDAO.filterByCat(categoryId);
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
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order != null) {
        attributes.put("order", order);
      }
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      String viewName = items.size() > 0 ?
          "common/marketing.ftl" : "common/marketing-empty-view.ftl";
      return new ModelAndView(attributes, viewName);
    }, engine);


    // administration
    before("/administrator", (request, response) -> {
      if (request.session().attribute(SharedConstants.SYSTEM_USER) == null) {
        response.redirect("/signin");
        halt();
      }
    });

    before("/administrator/*", (request, response) -> {
      if (request.session().attribute(SharedConstants.SYSTEM_USER) == null) {
        response.redirect("/signin");
        halt();
      }
    });

    get("/administrator", (request, response) -> {
      return new ModelAndView(null, "admin/index.ftl");
    }, engine);

    get("/signin", (request, response) -> {
      Map<String, Object> attributes = new HashMap<>();
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      return new ModelAndView(null, "admin/signin.ftl");
    }, engine);


    post("/signin", (request, response) -> {
      AuthenticationService service = ServiceFactory.getInstanse().createAuthenticationService();
      String name = request.queryParams(SharedConstants.USERNAME);
      String password = request.queryParams(SharedConstants.PASSWORD);
      SystemUser user = null;
      if (name != null && password != null) {
        user = service.isValidUser(name, password);
      }
      if (user != null) {
        request.session().attribute(SharedConstants.SYSTEM_USER, user);
        response.redirect("/administrator");
      }
      Map<String, Object> attributes = new HashMap<>();
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      attributes.put("show_error_message", true);
      return new ModelAndView(attributes, "admin/signin.ftl");
    }, engine);

    get("/administrator/orders", (request, response) -> {
      List<Order> orderList = DaoFactory.getInstance().
          createCommonDAO(Order.class).readAllObjects(Order.class);
      Map<String, Object> attributes = new HashMap<>();
      attributes.put("orders", orderList);
      return new ModelAndView(attributes, "admin/orders.ftl");

    }, engine);


    get("/administrator/logoff", (request, response) -> {
      request.session().removeAttribute(SharedConstants.SYSTEM_USER);
      response.redirect("/administrator");
      return null;
    });


    get("/administrator/orders/order", (request, response) -> {
      String stringIdParam = request.queryParams("id");
      if(stringIdParam == null) {
        // TODO: throw exception
      }
      CommonDAO<Order> orderDAO = DaoFactory.getInstance().createCommonDAO(Order.class);
      Long id = new Long(stringIdParam);
      Order order = orderDAO.readObject(Order.class, id);
      Map<String, Object> attributes = new HashMap<>();
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      attributes.put("order", order);
      attributes.put("user", order.getUser());
      return new ModelAndView(attributes, "admin/order-detail.ftl");
    }, engine);


  }
//
//
//
////    SessionFactory factory = SessionFactoryInitializer.getInstance().getSessionFacroty();
////      org.hibernate.Session session = factory.openSession();
////    Transaction tx = null;
////    long id = 2;
////    Category cat2 = null;
////    try {
////      tx = session.beginTransaction();
////
////      Item item = new Item();
////      item.setName("Летняя сказка");
////      item.setDescription("It’s the timeless beauty and elegant simplicity of this arrangement that makes it special. Exquisite large-headed roses, graceful lilies and a beautiful selection of germinis, lisianthus and spray chrysanthemums make up this chic display, and the looped steel grass adds a contemporary twist.");
////      item.setAvailableAmount(10);
////        item.setPrice(700);
////
////        Set<ItemImages> images = new HashSet<>();
////        ItemImages image = new ItemImages();
////        image.setImageUrl("img/b3.jpg");
////        image.setItem(item);
////        images.add(image);
////
////        item.setImages(images);
////        session.save(item);
////        session.save(image);
////
////      Item item2 = new Item();
////      item2.setName("Лунный сонет");
////      item2.setDescription("It’s the timeless beauty and elegant simplicity of this arrangement that makes it special. Exquisite large-headed roses, graceful lilies and a beautiful selection of germinis, lisianthus and spray chrysanthemums make up this chic display, and the looped steel grass adds a contemporary twist.");
////        item2.setAvailableAmount(9);
////        item2.setPrice(1100);
////
////        ItemImages image2 = new ItemImages();
////        image2.setImageUrl("img/b2.jpg");
////        image2.setItem(item2);
////        images.clear();
////        images.add(image2);
////
////         item2.setImages(images);
////
////        session.save(item2);
////        session.save(image2);
//
//
//
////      session.save(item2);
//
////      Set<Item> items = new HashSet<>();
////      items.add(item);
////      items.add(item2);
//
////      User us = new User();
////      us.setName("Nigga");
////      session.save(us);
//
////      DeliveryInformation dv = new DeliveryInformation();
////      dv.setName("dv");
////
////      Order order = new Order();
////      order.setUser(us);
////      order.setAmount(100500);
////      order.setItems(items);
////      order.setDeliveryInformation(dv);
////      session.save(order);
//
//
//// This works
////      User us = new User();
////      us.setName("Nigga");
////      session.save(us);
////      DeliveryInformation dv = new DeliveryInformation();
////      dv.setName("dv");
////      Order order = new Order();
////      order.setAmount(150);
////      order.setUser(us);
////      order.setDeliveryInformation(dv);
////
////      id = (long) session.save(order);
////      tx.commit();
//
////      tx = session.beginTransaction();
////
////      cat2 = (Category) session.get(Category.class, id);
////      tx.commit();
////
////    }catch (HibernateException e) {
////      if (tx!=null) tx.rollback();
////      e.printStackTrace();
////    }finally {
////      session.close();
////    }
////
////    System.out.println(cat2 == null ? "null" : cat2.getDescription());
//  }
}
