package com.liberty.technical.web.session;

import spark.Session;

import java.util.HashMap;
import java.util.Map;


/**
 * @author M-AKI.
 */
public class SessionManager
{
  private static SessionManager instance;
  private static Map<String, Session> sessionMap = new HashMap<>();

  private SessionManager()
  {

  }

  public static SessionManager getInstance()
  {
    if (instance == null) {
      instance = new SessionManager();
    }
    return instance;
  }

//  public static Session getSession()
//  {
//
//  }


}
