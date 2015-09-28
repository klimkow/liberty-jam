package com.liberty.technical.logic.dao.util;

import com.liberty.technical.logic.dao.CommonDAO;
import com.liberty.technical.logic.entity.DeliveryInformation;
import com.liberty.technical.logic.entity.Order;
import com.liberty.technical.logic.entity.User;
import com.liberty.technical.logic.factory.DaoFactory;

/**
 * @author AKI
 */
public class EntityDaoUtils {

    public void storeOrder(Order order, User user, DeliveryInformation deliveryInformation)
    {
        CommonDAO commonDAO = DaoFactory.getInstance().createCommonDAO();
        commonDAO.writeObject(deliveryInformation);
        commonDAO.writeObject(user);
        commonDAO.writeObject(order);
    }

}
