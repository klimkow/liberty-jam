package com.liberty.technical.logic.service;

import com.liberty.technical.logic.dao.CommonDAO;
import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.images.ItemImages;
import com.liberty.technical.logic.factory.DaoFactory;

/**
 * @author AKI
 */
public class ImageService
{
  private CommonDAO<ItemImages> imageDAO = DaoFactory.getInstance().createCommonDAO(ItemImages.class);
  private static final String IMAGES_PATH = "img/bouquets/";
  private static final String LOGO_IMAGE = "KLOGO_";

  public ItemImages setItemImage(Item item, String url, int order)
  {
    ItemImages images = new ItemImages();
    images.setImageUrl(IMAGES_PATH + url);
    images.setItem(item);
    images.setSeqenceOrder(order);
    imageDAO.persistObject(images);
    return images;
  }


  public void deleteItemImages(Item item)
  {
    if (item.getImages() != null && !item.getImages().isEmpty()) {
      item.getImages().forEach(imageDAO::deleteObject);
    }
  }

}
