package com.liberty.technical.logic.factory;

import com.liberty.technical.logic.service.GenericeCartService;
import com.liberty.technical.logic.service.AuthenticationService;


/**
 * @author AKI
 */
public class ServiceFactory {
  private static ServiceFactory instanse;

  private ServiceFactory()
  {

  }


  public static ServiceFactory getInstanse()
  {
    if (instanse == null) {
      instanse = new ServiceFactory();
    }
    return instanse;
  }


  public GenericeCartService createCartService()
  {
    return new GenericeCartService();
  }


  public AuthenticationService createAuthenticationService()
  {
    return new AuthenticationService();
  }

}
