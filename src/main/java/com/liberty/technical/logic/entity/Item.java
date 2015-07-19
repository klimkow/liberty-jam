package com.liberty.technical.logic.entity;


import com.liberty.technical.logic.entity.images.ItemImages;

import javax.persistence.*;
import java.util.Set;


/**
 * @author M-AKI.
 */
@Entity
@Table(name = "Item")
public class Item
{
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "AVAILABLE_AMOUNT")
  private Integer availableAmount;

  @Column(name = "PRICE")
  private Integer price;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}
      , fetch = FetchType.EAGER)
  @JoinTable(name = "CATEGORY_HAS_ITEM",
      joinColumns = {@JoinColumn(name = "ITEM_ID")},
      inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID")})
  private Set<Category> categories;

  @OneToMany(cascade=CascadeType.PERSIST)
  @JoinColumn(name="ITEM_ID", referencedColumnName="ID")
  private Set<ItemImages> images;


  public long getId()
  {
    return id;
  }


  public void setId(long id)
  {
    this.id = id;
  }


  public Set<Category> getCategories()
  {
    return categories;
  }


  public void setCategories(Set<Category> categories)
  {
    this.categories = categories;
  }


  public String getName()
  {
    return name;
  }


  public void setName(String name)
  {
    this.name = name;
  }


  public String getDescription()
  {
    return description;
  }


  public void setDescription(String description)
  {
    this.description = description;
  }


  public Integer getAvailableAmount()
  {
    return availableAmount;
  }


  public void setAvailableAmount(Integer availableAmount)
  {
    this.availableAmount = availableAmount;
  }


  public Set<ItemImages> getImages()
  {
    return images;
  }


  public void setImages(Set<ItemImages> images)
  {
    this.images = images;
  }


  public Integer getPrice()
  {
    return price;
  }


  public void setPrice(Integer price)
  {
    this.price = price;
  }
}
