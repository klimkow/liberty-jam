package com.liberty.technical.logic.service;

import com.liberty.technical.logic.dao.CommonDAO;
import com.liberty.technical.logic.dao.ItemDAO;
import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.Order;
import com.liberty.technical.logic.entity.service.ItemQuantity;
import com.liberty.technical.logic.factory.DaoFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author AKI
 */
public class GenericeCartService {
  private ItemDAO itemDAO;

  public GenericeCartService()
  {
    itemDAO = DaoFactory.getInstance().createItemDAO();
  }

  public void addToCart(Order order, Long itemId)
  {
    if (order == null || itemId == null) {
      // log exception
      return;
    }
    Set<Item> items = order.getItems();
    Set<ItemQuantity> quantities = new HashSet<>();
    if (items == null) {
      items = new HashSet<>();
    }
    Item item = itemDAO.readObject(Item.class, itemId);
    items.add(item);
    ItemQuantity itemQuantity = new ItemQuantity(item, 1);
    quantities.add(itemQuantity);
    order.setItemQuantity(quantities);
    order.setItems(items);
    order.setAmount(calculateOrderAmount(order));
  }


  public void setItemQuantity(Order order,
                              Long itemId, Integer quantity)
  {
    Item item = itemDAO.readObject(Item.class, itemId);
    ItemQuantity itemQuantity = new ItemQuantity(item, quantity);
//    order.setItemQuantity(itemQuantity);
  }


  private Integer calculateOrderAmount(Order order)
  {
    int sum = 0;
    if (order != null && order.getItems() != null) {
      Iterator<Item> it = order.getItems().iterator();
      while (it.hasNext()) {
        Item item = it.next();
        sum += item.getPrice();
      }
    }
    return sum;
  }


  public void removeItemFromOrder(Order order, Long itemId)
  {
    if (order == null || itemId == null) {
      // log exception
      return;
    }
    Set<Item> items = order.getItems();
    items.removeIf(item1 -> item1.getId() == itemId);
  }

}
