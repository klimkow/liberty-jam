package com.liberty.technical.logic.service;

import com.liberty.technical.logic.dao.CommonDAO;
import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.Order;
import com.liberty.technical.logic.entity.User;
import com.liberty.technical.logic.entity.service.ItemQuantity;
import com.liberty.technical.logic.factory.DaoFactory;

import java.util.Date;

/**
 * @author AKI
 */
public class OrderService
{
  private CommonDAO<Order> orderDAO;
  private CommonDAO<User> userDAO;

  public OrderService()
  {
    orderDAO = DaoFactory.getInstance().createCommonDAO(Order.class);
    userDAO = DaoFactory.getInstance().createCommonDAO(User.class);
  }




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


  public void saveOrder(Order order)
  {
    User user = order.getUser();
    StringBuilder username = new StringBuilder();
    username.append(user.getName()).
        append(" ").
        append(user.getSurname());
    user.setName(username.toString());
    userDAO.persistObject(order.getUser());
    orderDAO.persistObject(order);
  }

}
