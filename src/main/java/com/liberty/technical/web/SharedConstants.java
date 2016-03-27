package com.liberty.technical.web;

/**
 * @author AKI
 */
public interface SharedConstants {
  public final static String ATTRIBUTE_ORDER = "getOrder";
  public final static String ATTRIBUTE_LOCALE = "locale";
  public final static String SYSTEM_USER = "system-user";

  public static final String BOUQUET_AMOUNT = "amount";
  public static final String DELIVERY_NAME_FROM = "name-from";
  public static final String DELIVERY_SURNAME_FROM = "surname-from";
  public static final String DELIVERY_EMAIL_FROM = "email-from";
  public static final String DELIVERY_PHONE_FROM = "phone-from";

  public static final String DELIVERY_NAME_TO = "name-to";
  public static final String DELIVERY_PHONE_TO = "phone-to";
  public static final String DELIVERY_MESSAGE = "message";

  public static final String DELIVERY_ADDRESS = "address";
  public static final String DELIVERY_ADDRESS_HOUSE = "address-house";
  public static final String DELIVERY_ADDRESS_DOOR = "address-door";
  public static final String DELIVERY_ADDRESS_FLOOR = "address-flor";
  public static final String DELIVERY_ZONE = "dlv_zone_radio";
  public static final String DELIVERY_ORDER_COMMENT = "dlv_comment";

  public static final String DELIVERY_DATE = "date";
  public static final String DELIVERY_TIME = "time";
  public static final String DELIVERY_OPTION = "option";

  public static final String USERNAME = "j_username";
  public static final String PASSWORD = "j_password";

  public static final String OPTION_PAPER = "option_paper";
  public static final String OPTION_VASE = "option_vase";
  public static final String ITEM_QUANTITY = "item_quantity";

  public static final String ADMN_DELETE_ORDERS_IDS = "orders_ids";

  public static final String CATEGORY_CLASSIC = "classic";
  public static final Integer CATEGORY_CLASSIC_ID = 1;

  public static final int PAYMENT_CASH = 1;
  public static final int PAYMENT_ONLINE = 2;
}
