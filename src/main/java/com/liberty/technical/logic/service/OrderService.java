package com.liberty.technical.logic.service;

import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.Order;

/**
 * @author AKI
 */
public class OrderService
{
  public void removeItemFromOrder(Order order, Long itemId)
  {
    Item item = getItemFromOrder(order, itemId);
    if (item != null) {
      order.setAmount(order.getAmount() - item.getPrice());
      order.getItems().remove(item);
    }

  }

  public Item getItemFromOrder(Order order, Long uid)
  {
    Item item = null;
    for (Item i : order.getItems()) {
      if (i.getId() == uid) {
        item = i;
      }
    }
    return item;
  }
}
