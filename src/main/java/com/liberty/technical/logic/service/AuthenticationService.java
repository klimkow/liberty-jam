package com.liberty.technical.logic.service;

import com.liberty.technical.logic.dao.SystemUserDAO;
import com.liberty.technical.logic.entity.system.SystemUser;
import com.liberty.technical.logic.factory.DaoFactory;


/**
 * @author M-AKI.
 */
public class AuthenticationService
{
  private SystemUserDAO userDAO;
  private static final String SALT = "c010ed8c54f25d7e486ec62273b0def0";


  public AuthenticationService()
  {
    userDAO = DaoFactory.getInstance().createSystemUserDAO();
  }

  public boolean isValidUser(String name, String password)
  {
    SystemUser user  = userDAO.getUser(name, password);
    if (user != null) {
      return true;
    }
    return false;
  }

}
