package com.liberty.technical.logic.entity.service;


import com.liberty.technical.logic.entity.Item;

import javax.persistence.*;

/**
 * @author AKI
 */
@Entity
@Table(name = "PRICE_DIAPASON")
public class PriceDiapason
{
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @ManyToOne(targetEntity=Item.class,
      cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
  @JoinColumn(name="item_id", referencedColumnName="id")
  private Item item;

  @Column(name = "count_from")
  private Integer countFrom;

  @Column(name = "count_to")
  private Integer countTo;

  @Column(name = "price")
  private Integer Price;


  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public Integer getCountFrom() {
    return countFrom;
  }

  public void setCountFrom(Integer countFrom) {
    this.countFrom = countFrom;
  }

  public Integer getCountTo() {
    return countTo;
  }

  public void setCountTo(Integer countTo) {
    this.countTo = countTo;
  }

  public Integer getPrice() {
    return Price;
  }

  public void setPrice(Integer price) {
    Price = price;
  }
}
