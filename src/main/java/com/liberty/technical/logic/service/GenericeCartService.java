package com.liberty.technical.logic.service;

import com.liberty.technical.logic.dao.CommonDAO;
import com.liberty.technical.logic.dao.ItemDAO;
import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.Order;
import com.liberty.technical.logic.factory.DaoFactory;

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
    if (items == null) {
      items = new HashSet<>();
    }
    Item item = itemDAO.readObject(Item.class, itemId);
    items.add(item);
    order.setItems(items);
    order.setAmount(calculateOrderAmount(order));
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
