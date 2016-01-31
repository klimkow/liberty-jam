package com.liberty.technical.web.controller;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.liberty.technical.logic.dao.*;
import com.liberty.technical.logic.entity.*;
import com.liberty.technical.logic.entity.content.ContentPage;
import com.liberty.technical.logic.entity.images.ItemImages;
import com.liberty.technical.logic.entity.service.ItemQuantity;
import com.liberty.technical.logic.entity.service.PriceDiapason;
import com.liberty.technical.logic.entity.system.SystemUser;
import com.liberty.technical.logic.factory.DaoFactory;
import com.liberty.technical.logic.factory.ServiceFactory;
import com.liberty.technical.logic.localization.LocalizationUtil;
import com.liberty.technical.logic.service.AuthenticationService;
import com.liberty.technical.logic.service.GenericeCartService;
import com.liberty.technical.logic.service.ImageService;
import com.liberty.technical.web.SharedConstants;
import com.liberty.technical.web.util.UserSessionUtils;
import freemarker.template.Configuration;
import org.eclipse.jetty.util.MultiPartInputStreamParser;
import spark.*;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;
import sun.security.provider.SHA;

import javax.imageio.ImageIO;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * @author M-AKI.
 */
public class Liberty implements SparkApplication {

    @Override
    public void init()
    {
      execute();
    }

//  public static void main(String[] args) {
//    execute();
//  }

  private static void execute()
  {
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
        attributes.put("itemCount", order.getSumItemCount());
      }
      Locale locale = session.attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));

      return new ModelAndView(attributes, "index.ftl");
    }, engine);


    post("/openPage", (request, response) -> {
      Map<String, Object> attributes = new HashMap<>();
      PageDAO contentPageDAO = DaoFactory.getInstance().createPageDAO();
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      Integer pageNumber = new Integer(request.queryParams("page"));
      ContentPage page = contentPageDAO.getContentPage(pageNumber);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      attributes.put("title", page.getTitle());
      attributes.put("content", page.getContent());
      return new ModelAndView(attributes, "common/pages/content_page_template.ftl");
    }, engine);


    post("/changeLang", (request, response) -> {
      String lang = request.queryParams("lang");
      request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE, new Locale(lang));
      Session session = request.session();
      Map<String, Object> attributes = new HashMap<>();
      Order order = session.attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order != null) {
        attributes.put("order", order);
        attributes.put("itemCount", order.getSumItemCount());
      }
      Locale locale = session.attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      response.redirect("/");
      return new ModelAndView(attributes, "index.ftl");
    }, engine);


    post("/getItem", (request, response) -> {
      CommonDAO<Item> itemCommonDAO = DaoFactory.getInstance().createCommonDAO(Item.class);
      PriceDiapasonDAO priceDAO = DaoFactory.getInstance().createPriceDiapasonDAO();

      Map<String, Object> attributes = new HashMap<>();
      Long id = new Long(request.queryParams("itemId"));
      Item item = itemCommonDAO.readObject(Item.class, id);
      boolean isClassic = item.getCategoryName().equals("classic");
      attributes.put("selectedItem", item);
      attributes.put("isClassic", isClassic);
      attributes.put("minAmount", item.getMinAmount());
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      int count = item.getMinAmount();
      if (order != null) {
        attributes.put("order", order);
        attributes.put("itemQuantity", order.getIQWithItem(item));
        count = order.getAmountOfItem(item);
      }
      List<PriceDiapason> prices = priceDAO.getItemFlexPrices(id);
      if (!prices.isEmpty()) {
        attributes.put("diapasons", prices);
      }
      attributes.put("itemId", id);
      attributes.put("count", count);
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));

      return new ModelAndView(attributes, "common/item-description.ftl");
    }, engine);


    get("/cart", (request, response) -> {
      Map<String, Object> attributes = new HashMap<>();
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order != null) {
        attributes.put("order", order);
        attributes.put("itemCount", order.getSumItemCount());
        attributes.put("sumAmount", order.getAmount());
        attributes.put("cartItems", order.getItems());

        for (Item item : order.getItems()) {
          attributes.put("minAmount" + item.getId(), item.getMinAmount());
        }
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
        attributes.put("itemCount", order.getSumItemCount());
        attributes.put("sumAmount", order.getAmount());
        attributes.put("cartItems", order.getItems());

        for (Item item : order.getItems()) {
          attributes.put("minAmount" + item.getId(), item.getMinAmount());
        }
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
        attributes.put("itemCount", order.getSumItemCount());
        attributes.put("sumAmount", order.getAmount());
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
      Boolean withPaper = request.queryParams(SharedConstants.OPTION_PAPER) != null;
      Boolean withVase = request.queryParams(SharedConstants.OPTION_VASE) != null;
      String quantity_string = request.queryParams(SharedConstants.ITEM_QUANTITY);
      Integer item_quantity = quantity_string == null || quantity_string.isEmpty() ? null : new Integer(quantity_string);

      cartService.addToCart(order, id, withPaper, withVase, item_quantity);
      request.session().attribute(SharedConstants.ATTRIBUTE_ORDER, order);

      String bouquets;
      int count = order.getSumItemCount();
      int amount = order.getAmount();
      if (count == 1) {
        bouquets = LocalizationUtil.getString("bouquet1");
      } else if (count > 1 && count < 5) {
        bouquets = LocalizationUtil.getString("bouquet24");
      } else {
        bouquets = LocalizationUtil.getString("bouquet5");
      }
      attributes.put("order", order);
      return new OrderVO(amount,
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
      int count = order == null ? 1 : order.getSumItemCount();
      int amount = order == null ? 0 : order.getAmount();


      String bouquets;
      if (count == 1) {
        bouquets = LocalizationUtil.getString("bouquet1");
      } else if (count > 1 && count < 5) {
        bouquets = LocalizationUtil.getString("bouquet24");
      } else {
        bouquets = LocalizationUtil.getString("bouquet5");
      }

      return new OrderVO(amount,
          count,
          LocalizationUtil.getString("you_have"),
          bouquets,
          LocalizationUtil.getString("total_amount"),
          LocalizationUtil.getString("currency"),
          LocalizationUtil.getString("item_int_the_cart"));
    }, gson::toJson);


    post("/delivery_info", (request, response) -> {
      Map<String, Object> attributes = new HashMap<>();
      GenericeCartService cartService = ServiceFactory.getInstanse().createCartService();
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);

      if (order != null) {
        for(Item item : order.getItems()) {
          String itemId = String.valueOf(item.getId());
          String amountString = request.queryParams(itemId);
          if (amountString != null) {
            Integer amount = new Integer(amountString);
            cartService.setItemQuantity(order, item.getId(), amount);
          }
        }

        if (order.getUser() != null) {
          attributes.put("user", order.getUser());
        }
        if (order.getDeliveryInformation() != null) {
          attributes.put("delivery", order.getDeliveryInformation());
        }
      }

      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      return new ModelAndView(attributes, "common/cart/delivery.ftl");
    }, engine);


    get("/payment_result", (request, response) -> {
      Map<String, Object> attributes = new HashMap<>();
      OrderDAO orderDAO = DaoFactory.getInstance().createOrderDAO();
      Long id = new Long(request.queryParams("ordernumber"));
      String confirmNumber = request.queryParams("billnumber");
      Order order = orderDAO.readObject(Order.class, id);

      if (order != null) {
        order.setConfirmationNumber(confirmNumber);
        attributes.put("id", order.getId());
        attributes.put("confirmNumber", confirmNumber);
        orderDAO.updateObject(order);
      }

      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      return new ModelAndView(attributes, "common/cart/order-finished.ftl");
    }, engine);


    post("/payment_denied", (request, response) -> {
      Map<String, Object> attributes = new HashMap<>();
      OrderDAO orderDAO = DaoFactory.getInstance().createOrderDAO();
      Long id = new Long(request.queryParams("ordernumber"));
//      Integer confirmNumber = new Integer(request.queryParams("billnumber"));
      Order order = orderDAO.readObject(Order.class, id);

      if (order != null) {
        orderDAO.deleteObject(order);
      }

      attributes.put("paymentFailed", true);
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      return new ModelAndView(attributes, "common/cart/order-finished.ftl");
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
      Integer timeParam = new Integer(request.queryParams(SharedConstants.DELIVERY_TIME));
      if (dateParam != null && !dateParam.isEmpty()) {
        Date date = new Date(dateParam);
        info.setDeliveryDate(date);
        info.setTimeRange(timeParam);
      }

      User user = new User();
      user.setName(request.queryParams(SharedConstants.DELIVERY_NAME_FROM));
      user.setSurname(request.queryParams(SharedConstants.DELIVERY_SURNAME_FROM));
      user.setPhone(request.queryParams(SharedConstants.DELIVERY_PHONE_FROM));
      user.setEmail(request.queryParams(SharedConstants.DELIVERY_EMAIL_FROM));
//        request.session().attribute(UserSessionUtils.ATTRIBUTE_USER, user);

      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      order.setDeliveryInformation(info);
      order.setUser(user);

      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      attributes.put("sumAmount", order.getAmount());
      return new ModelAndView(attributes, "common/cart/payment.ftl");
    }, engine);


    post("/payCash", (request, response) -> {
      Map<String, Object> attributes = new HashMap<>();
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order == null) {
        // TODO: throw exception
      }
      attributes.put("order", order);
      ServiceFactory.getInstanse().createOrderService().saveOrder(order, SharedConstants.PAYMENT_CASH);
      request.session().invalidate();
      return new ModelAndView(attributes, "common/cart/well-done.ftl");
    }, engine);


    Gson payOnlineJson = new Gson();
    post("/payOnline", (request, response) -> {
      Order order = request.session().attribute(SharedConstants.ATTRIBUTE_ORDER);
      if (order == null) {
        // TODO: throw exception
      }
      ServiceFactory.getInstanse().createOrderService().saveOrder(order, SharedConstants.PAYMENT_ONLINE);
      return new OrderVO(order.getId(), order.getAmount() * 1000, order.getUser().getName(),
          order.getUser().getSurname(), order.getUser().getEmail(), order.getUser().getPhone());
    }, payOnlineJson::toJson);


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
      attributes.put("isClassic", categoryId.equals(SharedConstants.CATEGORY_CLASSIC_ID));
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
      attributes.put("isClassic", categoryId.equals(SharedConstants.CATEGORY_CLASSIC_ID));
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
      request.raw().setCharacterEncoding("UTF-8");
      response.raw().setContentType("text/html;charset=UTF-8");
      response.raw().setCharacterEncoding("UTF-8");
      if (request.session().attribute(SharedConstants.SYSTEM_USER) == null) {
        response.redirect("/signin");
        halt();
      }
    });

    before("/administrator/*", (request, response) -> {
      request.raw().setCharacterEncoding("UTF-8");
      response.raw().setContentType("text/html;charset=UTF-8");
      response.raw().setCharacterEncoding("UTF-8");
      if (request.session().attribute(SharedConstants.SYSTEM_USER) == null) {
        response.redirect("/signin");
        halt();
      }
    });

    get("/administrator", (request, response) -> new ModelAndView(null, "admin/index.ftl"), engine);

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
          createOrderDAO().readAllOrders();
      Map<String, Object> attributes = new HashMap<>();
      attributes.put("orders", orderList);
      return new ModelAndView(attributes, "admin/orders.ftl");

    }, engine);

    get("/administrator/items", (request, response) -> {
      List<Item> itemList = DaoFactory.getInstance().
          createItemDAO().getAllItems();
      Map<String, Object> attributes = new HashMap<>();
      attributes.put("items", itemList);
      return new ModelAndView(attributes, "admin/items.ftl");

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
      attributes.put("info", order.getDeliveryInformation());
//      attributes.put("items", order.getUser().get);
      return new ModelAndView(attributes, "admin/order-detail.ftl");
    }, engine);

    get("/administrator/items/item", (request, response) -> {
      String stringIdParam = request.queryParams("id");
      if(stringIdParam == null) {
        // TODO: throw exception
      }
      CommonDAO<Item> itemDAO = DaoFactory.getInstance().createCommonDAO(Item.class);
      Long id = new Long(stringIdParam);
      Item item = itemDAO.readObject(Item.class, id);
      Map<String, Object> attributes = new HashMap<>();
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      attributes.put("item", item);
//      attributes.put("items", order.getUser().get);
      return new ModelAndView(attributes, "admin/item-detail.ftl");
    }, engine);

    post("/administrator/items/save_item", (request, response) -> {
      String stringIdParam = request.queryParams("id");
      if (stringIdParam == null) {
        // TODO: throw exception
      }
      Long id = new Long(request.queryParams("id"));
      String name = request.queryParams("b_name");
      String description = request.queryParams("b_desc");
      Integer price = new Integer(request.queryParams("b_price"));
      String categoryName = request.queryParams("b_cat");
      CommonDAO<Item> itemDAO = DaoFactory.getInstance().createCommonDAO(Item.class);
      CategoryDAO categoryDAO = DaoFactory.getInstance().createCategoryDAO();
      Item item = itemDAO.readObject(Item.class, id);
      Category category = categoryDAO.getCategoryByName(categoryName);
      Set<Category> categories = new HashSet<Category>();
      categories.add(category);

      String image_main = request.queryParams("image0");
      String image_add1 = request.queryParams("image1");
      String image_add2 = request.queryParams("image2");
      if (image_main != null && !image_main.isEmpty()) {
        Set<ItemImages> images = new HashSet<>();
        ImageService imageService = ServiceFactory.getInstanse().createImageService();
        imageService.deleteItemImages(item);
        ItemImages image = imageService.setItemImage(item, image_main, 0);
        images.add(image);

        if (Objects.nonNull(image_add1)) {
          images.add(imageService.setItemImage(item, image_add1, 1));
        }
        if (Objects.nonNull(image_add2)) {
          images.add(imageService.setItemImage(item, image_add2, 2));
        }

        item.setImages(images);
      }

      item.setName(name);
      item.setDescription(description);
      item.setPrice(price);
      item.setCategories(categories);
      itemDAO.updateObject(item);
      Map<String, Object> attributes = new HashMap<>();
      Locale locale = request.session().attribute(SharedConstants.ATTRIBUTE_LOCALE);
      attributes.put("translator", LocalizationUtil.getInstance(locale));
      attributes.put("item", item);
//      attributes.put("items", order.getUser().get);
      response.redirect("/administrator/items");
      return new ModelAndView(attributes, "admin/items.ftl");
    }, engine);

    post("/administrator/items/b_image_upload", (request, response) -> {
      MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
      request.raw().setAttribute("org.eclipse.multipartConfig", multipartConfigElement);

      try {
        Part file = request.raw().getPart("file");
        if (file != null) {
          BufferedImage img = ImageIO.read(file.getInputStream());
          File outputfile = new File(request.raw().getSession().getServletContext().getRealPath("/") +
              "/WEB-INF/classes/public/img/bouquets",
              ((MultiPartInputStreamParser.MultiPart) file)
              .getContentDispositionFilename());
          ImageIO.write(img, "jpg", outputfile);
        }
      } catch (IOException | ServletException e) {

      }
      return new ModelAndView(null, "admin/items.ftl");
    }, engine);

  }
}
