package com.liberty.technical.logic.entity.images;

import com.liberty.technical.logic.entity.Item;

import javax.persistence.*;


/**
 * @author M-AKI.
 */
@Entity
@Table(name = "item_images")
public class ItemImages
{
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "image")
  private String imageUrl;

  @ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
  @JoinColumn(name="item_id", referencedColumnName="id")
  private Item item;


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
}
