package com.liberty.technical.logic.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author M-AKI.
 */
@Entity
@Table(name = "delivery_information")
public class DeliveryInformation
{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "city")
  private String city;

  @Column(name = "address")
  private String address;

  @Column(name = "message")
  private String message;

  @Column(name = "delivery_time")
  private Date deliveryDate;

  @Column(name = "delivery_price")
  private Integer deliveryPrice;


  public long getId()
  {
    return id;
  }


  public void setId(long id)
  {
    this.id = id;
  }


  public String getName()
  {
    return name;
  }


  public void setName(String name)
  {
    this.name = name;
  }


  public String getEmail()
  {
    return email;
  }


  public void setEmail(String email)
  {
    this.email = email;
  }


  public String getPhone()
  {
    return phone;
  }


  public void setPhone(String phone)
  {
    this.phone = phone;
  }


  public String getCity()
  {
    return city;
  }


  public void setCity(String city)
  {
    this.city = city;
  }


  public String getAddress()
  {
    return address;
  }


  public void setAddress(String address)
  {
    this.address = address;
  }


  public String getMessage()
  {
    return message;
  }


  public void setMessage(String message)
  {
    this.message = message;
  }


  @Temporal(TemporalType.DATE)
  public Date getDeliveryDate()
  {
    return deliveryDate;
  }


  public void setDeliveryDate(Date deliveryDate)
  {
    this.deliveryDate = deliveryDate;
  }


  public Integer getDeliveryPrice()
  {
    return deliveryPrice;
  }


  public void setDeliveryPrice(Integer deliveryPrice)
  {
    this.deliveryPrice = deliveryPrice;
  }
}
