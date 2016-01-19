package com.liberty.technical.logic.entity.images;

import com.liberty.technical.logic.entity.Item;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author M-AKI.
 */
@Entity
@Table(name = "item_images")
public class ItemImages
{
  public static final int IMAGE_LOGO = 0;
  public static final int SECOND_IMAGE = 1;
  public static final int THIRD_IMAGE = 2;

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "image")
  private String imageUrl;

  @ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
  @JoinColumn(name="item_id", referencedColumnName="id")
  private Item item;

  @Column(name = "seqence_order")
  private Integer seqenceOrder;


  public long getId()
  {
    return id;
  }


  public void setId(long id)
  {
    this.id = id;
  }


  public String getImageUrl()
  {
    return imageUrl;
  }


  public void setImageUrl(String imageUrl)
  {
    this.imageUrl = imageUrl;
  }


  public Item getItem()
  {
    return item;
  }


  public void setItem(Item item)
  {
    this.item = item;
  }


  public Integer getSeqenceOrder() {
    return seqenceOrder;
  }


  public void setSeqenceOrder(Integer seqenceOrder) {
    this.seqenceOrder = seqenceOrder;
  }
}
