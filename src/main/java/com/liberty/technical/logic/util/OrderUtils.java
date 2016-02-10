package com.liberty.technical.logic.util;

import com.liberty.technical.logic.entity.DeliveryInformation;
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

    public static void updateOrderDeliveryPrice(Order order, DeliveryInformation info)
    {
        int curOpt = info.getDeliveryPrice();
        if (order.getDeliveryInformation() == null) {
            order.setAmount(order.getAmount() + getPriceByZone(curOpt));
        } else {
            int prevOpt = order.getDeliveryInformation().getDeliveryPrice();
            boolean needUpdate = prevOpt != curOpt;
            if (needUpdate) {
                int dif = getPriceByZone(curOpt) - getPriceByZone(prevOpt);
                order.setAmount(order.getAmount() + dif);
            }
        }
    }


    public static int getPriceByZone(int zone)
    {
        switch (zone) {
            case 2:
                return 100;
            case 3:
                return 150;
            default:
                return 0;
        }
    }

}
