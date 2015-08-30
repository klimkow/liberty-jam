package com.liberty.technical.logic.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * @author M-AKI.
 */
@Entity
@Table(name = "ORDER")
public class Order
{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;

  @Column(name = "AMOUNT")
  private Integer amount;

  @Column(name = "DATE_CREATED")
  private Date dateCreated;

  @Column(name = "CONFIRMATION_NUMBER")
  private Integer confirmationNumber;

  @Column(name = "STATUS")
  private Integer status;

  @Column(name = "LAST_UPDATED")
  private Date lastUpdated;

  @Column(name = "PAYMENT_STATUS")
  private Integer paymentStatus;

  @ManyToMany(targetEntity=com.liberty.technical.logic.entity.Item.class,
          cascade = {CascadeType.PERSIST, CascadeType.MERGE}
      , fetch = FetchType.EAGER)
  @JoinTable(name = "ORDER_HAS_ITEM",
      joinColumns = {@JoinColumn(name = "ORDER_ID")},
      inverseJoinColumns = {@JoinColumn(name = "ITEM_ID")})
  private Set items;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name="DELIVERY_INFORMATION_ID")
  private DeliveryInformation deliveryInformation;


  @ManyToOne(targetEntity=User.class,
          cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
  @JoinColumn(name="USER_ID", referencedColumnName="ID")
  private User user;

  public long getId()
  {
    return id;
  }


  public void setId(long id)
  {
    this.id = id;
  }


  public Set<Item> getItems()
  {
    return items;
  }


  public void setItems(Set<Item> items)
  {
    this.items = items;
  }


  public Integer getAmount()
  {
    return amount;
  }


  public void setAmount(Integer amount)
  {
    this.amount = amount;
  }


  public User getUser()
  {
    return user;
  }


  public void setUser(User user)
  {
    this.user = user;
  }


  public DeliveryInformation getDeliveryInformation()
  {
    return deliveryInformation;
  }


  public void setDeliveryInformation(DeliveryInformation deliveryInformation)
  {
    this.deliveryInformation = deliveryInformation;
  }


  @Temporal(TemporalType.DATE)
  public Date getDateCreated()
  {
    return dateCreated;
  }


  public void setDateCreated(Date dateCreated)
  {
    this.dateCreated = dateCreated;
  }


  public Integer getConfirmationNumber()
  {
    return confirmationNumber;
  }


  public void setConfirmationNumber(Integer confirmationNumber)
  {
    this.confirmationNumber = confirmationNumber;
  }


  public Integer getStatus()
  {
    return status;
  }


  public void setStatus(Integer status)
  {
    this.status = status;
  }


  @Temporal(TemporalType.TIMESTAMP)
  public Date getLastUpdated()
  {
    return lastUpdated;
  }


  public void setLastUpdated(Date lastUpdated)
  {
    this.lastUpdated = lastUpdated;
  }


  public Integer getPaymentStatus()
  {
    return paymentStatus;
  }


  public void setPaymentStatus(Integer paymentStatus)
  {
    this.paymentStatus = paymentStatus;
  }

  // TODO: replace to ContentManager

  public int getItemCount()
  {
      return getItems() == null ? 0 : getItems().size();
  }

  public boolean isSelected(Item item)
  {
      return getItems() != null && getItems().contains(item);
  }

}
