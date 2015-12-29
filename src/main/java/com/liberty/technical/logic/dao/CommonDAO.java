package com.liberty.technical.logic.dao;

import java.util.List;

/**
 * @author AKI
 */
public interface CommonDAO<T> {

  void persistObject(T object);

  T readObject(Class<T> className, Long uid);

  List<T> readAllObjects(Class<T> className);

  void updateObject(T object);

  void deleteObject(T object);

}
