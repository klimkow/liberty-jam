package com.liberty.technical.logic.entity;

import com.liberty.technical.logic.entity.service.ItemQuantity;
import com.liberty.technical.web.SharedConstants;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author M-AKI.
 */
@Entity
@Table(name = "order_detail")
public class Order
{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;

  @Column(name = "amount")
  private Integer amount;

  @Column(name = "date_created")
  private Date dateCreated;

  @Column(name = "confirmation_number")
  private String confirmationNumber;

  @Column(name = "status")
  private Integer status;

  @Column(name = "last_updated")
  private Date lastUpdated;

  @Column(name = "payment_type")
  private Integer paymentType;

  @ManyToMany(targetEntity=com.liberty.technical.logic.entity.Item.class,
          cascade = {CascadeType.PERSIST, CascadeType.MERGE}
      , fetch = FetchType.EAGER)
  @JoinTable(name = "order_has_item",
      joinColumns = {@JoinColumn(name = "order_id")},
      inverseJoinColumns = {@JoinColumn(name = "item_id")})
  private Set items;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name="delivery_information_id")
  private DeliveryInformation deliveryInformation;

  @OneToMany(targetEntity = ItemQuantity.class,
      cascade=CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name="order_id", referencedColumnName="id")
  private Set<ItemQuantity> itemQuantity = new HashSet<>();

  @ManyToOne(targetEntity=User.class,
          cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
  @JoinColumn(name="user_id", referencedColumnName="id")
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
    Set<Item> items = itemQuantity.stream().map(ItemQuantity::getItem).collect(Collectors.toSet());
    return items;
  }


  public Integer getAmountOfItem(Item item)
  {
    for (ItemQuantity q : itemQuantity) {
      if (q.getItem().equals(item)) {
        return q.getItemQuantity();
      }
    }
    return item.getMinAmount();
  }


  public Integer getSumItemCount()
  {
    int count = 0;
    for (ItemQuantity q : itemQuantity) {
      if (q.getItem().getCategoryName().equals(SharedConstants.CATEGORY_CLASSIC)) {
        count++;
      } else {
        count += q.getItemQuantity();
      }
    }
    return count;
  }


//  public Integer getSumItemPrice()
//  {
//    int price = 0;
//    for (ItemQuantity q : itemQuantity) {
//      price += q.getItem().getPrice() * q.getItemQuantity();
//    }
//    return price;
//  }



  public ItemQuantity getIQWithItem(Item item)
  {
    for (ItemQuantity q : itemQuantity) {
      if (q.getItem().equals(item)) {
        return q;
      }
    }
    return null;
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


  public Set<ItemQuantity> getItemQuantity() {
    return itemQuantity;
  }


  public void setItemQuantity(Set<ItemQuantity> itemQuantity) {
    this.itemQuantity = itemQuantity;
  }


  public void addItemQuantity(ItemQuantity item)
  {
    item.setOrder(this);
    itemQuantity.add(item);
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


  public String getConfirmationNumber()
  {
    return confirmationNumber;
  }


  public void setConfirmationNumber(String confirmationNumber)
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


  public Integer getPaymentType()
  {
    return paymentType;
  }


  public void setPaymentType(Integer paymentType)
  {
    this.paymentType = paymentType;
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

  public String getDateCreatedView()
  {
    if (getDateCreated() != null) {
      SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy' 'HH:mm");
      return sdf.format(getDateCreated());
    }
    return "";
  }

  public String getPayTypeView()
  {
    if (getPaymentType() != null && getPaymentType() == 1) {
      return "Онлайн";
    }
    return "Наличные";
  }

}
