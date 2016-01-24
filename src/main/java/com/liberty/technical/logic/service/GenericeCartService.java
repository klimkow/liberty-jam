package com.liberty.technical.logic.service;

import com.liberty.technical.logic.dao.CommonDAO;
import com.liberty.technical.logic.dao.ItemDAO;
import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.Order;
import com.liberty.technical.logic.entity.service.ItemQuantity;
import com.liberty.technical.logic.factory.DaoFactory;

import java.util.*;


/**
 * @author AKI
 */
public class GenericeCartService {
  private ItemDAO itemDAO;

  public GenericeCartService()
  {
    itemDAO = DaoFactory.getInstance().createItemDAO();
  }

  public void addToCart(Order order, Long itemId, Boolean withPaper, Boolean withVase, Integer amount)
  {
    if (order == null || itemId == null) {
      // log exception
      return;
    }
    Item item = itemDAO.readObject(Item.class, itemId);
    if (amount == null) {
      amount = item.getMinAmount();
    }
    ItemQuantity itemQuantity = new ItemQuantity(item, amount, withPaper, withVase);
    ItemQuantity oldIQ = order.getIQWithItem(item);
    order.getItemQuantity().remove(oldIQ);
    order.addItemQuantity(itemQuantity);
    order.setAmount(calculateOrderAmount(order));
  }


  public void setItemQuantity(Order order,
                              Long itemId, Integer quantity)
  {
    Item item = itemDAO.readObject(Item.class, itemId);

    ItemQuantity itemQuantity = order.getIQWithItem(item);
    if (itemQuantity != null) {
      itemQuantity.setItemQuantity(quantity);
    } else {
      itemQuantity = new ItemQuantity(item, quantity);
      order.addItemQuantity(itemQuantity);
    }
    order.setAmount(calculateOrderAmount(order));
  }


  private Integer calculateOrderAmount(Order order)
  {
    int sum = 0;
    for (ItemQuantity q : order.getItemQuantity()) {
      int count = q.getItemQuantity();
      sum += q.getItem().getPriceIncludingDiapasons(count) * count;
      if (q.isWithPaper()) {
        // TODO: get amount from db
        sum += 25;
      }
      if (q.isWithVase()) {
        // TODO: get amount from db
        sum += 70;
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
    Set<ItemQuantity> items = order.getItemQuantity();
    items.removeIf(item1 -> item1.getItem().getId() == itemId);
    order.setAmount(calculateOrderAmount(order));
  }

}
