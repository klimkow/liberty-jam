package com.liberty.technical.logic.entity.service;

import com.liberty.technical.logic.entity.Item;
import com.liberty.technical.logic.entity.Order;

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
  @JoinColumn(name="item_id", nullable = false)
  private Item item;

  @Column(name = "item_quantity")
  private Integer itemQuantity;

  @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
  @JoinColumn(name="order_id", referencedColumnName="id", nullable = false)
  private Order order;

  @Column(name = "OPTION_PAPER")
  private boolean withPaper;

  @Column(name = "OPTION_VASE")
  private boolean withVase;


  public ItemQuantity()
  {

  }

  public ItemQuantity(Item item, Integer itemQuantity)
  {
    this.item = item;
    this.itemQuantity = itemQuantity;
  }

  public ItemQuantity(Item item, Integer itemQuantity, Boolean withPaper, Boolean withVase)
  {
    this.item = item;
    this.itemQuantity = itemQuantity;
    this.withPaper = withPaper;
    this.withVase = withVase;
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

  public Order getOrder()
  {
    return order;
  }

  public void setOrder(Order order)
  {
    this.order = order;
  }

  public boolean isWithPaper()
  {
    return withPaper;
  }

  public void setWithPaper(boolean withPaper)
  {
    this.withPaper = withPaper;
  }

  public boolean isWithVase()
  {
    return withVase;
  }

  public void setWithVase(boolean withVase)
  {
    this.withVase = withVase;
  }
}

