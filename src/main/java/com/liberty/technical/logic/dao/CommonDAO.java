package com.liberty.technical.logic.dao;

import java.util.List;

/**
 * @author AKI
 */
public interface CommonDAO<T> {

  public void persistObject(T object);

  public T readObject(Class<T> className, Long uid);

  public List<T> readAllObjects(Class<T> className);

}
