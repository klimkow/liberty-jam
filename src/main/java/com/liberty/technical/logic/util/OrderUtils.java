package com.liberty.technical.logic.util;

import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.Order;
import com.liberty.technical.logic.entity.OrderVO;

/**
 * @author AKI
 */
public class OrderUtils {

    public static void removeItemFromOrder(Order order, Long itemId)
    {
        Item item = getItemFromOrder(order, itemId);
        if (item != null) {
            order.setAmount(order.getAmount() - item.getPrice());
            order.getItems().remove(item);
        }

    }

    public static Item getItemFromOrder(Order order, Long uid)
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
