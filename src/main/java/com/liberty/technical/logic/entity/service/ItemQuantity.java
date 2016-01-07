package com.liberty.technical.logic.entity.service;

import com.liberty.technical.logic.entity.Item;

import javax.persistence.*;


/**
 * @author AKI
 */
@Entity
@Table(name = "order_item_quantity")
public class ItemQuantity {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name="item_id")
  private Item item;

  @Column(name = "item_quantity")
  private Integer itemQuantity;

  public ItemQuantity(Item item, Integer itemQuantity)
  {
    this.item = item;
    this.itemQuantity = itemQuantity;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public Integer getItemQuantity() {
    return itemQuantity;
  }

  public void setItemQuantity(Integer itemQuantity) {
    this.itemQuantity = itemQuantity;
  }
}
