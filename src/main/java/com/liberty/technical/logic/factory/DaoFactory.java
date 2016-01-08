package com.liberty.technical.logic.factory;


import com.liberty.technical.logic.dao.*;


/**
 * @author AKI
 */
public class DaoFactory {
  private static DaoFactory instanse;


  private DaoFactory() {

  }


  public static DaoFactory getInstance() {
    if (instanse == null) {
      instanse = new DaoFactory();
    }
    return instanse;
  }


  public ItemDAO createItemDAO() {
    return new ItemDAO();
  }


  public <T> CommonDAO<T> createCommonDAO(Class<T> tClass) {
    return new CommonDAOImpl<>();
  }

  public SystemUserDAO createSystemUserDAO()
  {
    return new SystemUserDAO();
  }


  public CategoryDAO createCategoryDAO()
  {
    return new CategoryDAO();
  }


  public OrderDAO createOrderDAO()
  {
    return new OrderDAO();
  }


}
