package com.liberty.technical.logic.entity;

import javax.persistence.*;
import java.util.Set;


/**
 * @author M-AKI.
 */
@Entity
@Table(name= "USER")
public class User
{
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "PHONE")
  private String phone;

  @Column(name = "ADDRESS")
  private String address;

  @Column(name = "CITY")
  private String city;

  @OneToMany(cascade=CascadeType.PERSIST)
  @JoinColumn(name="USER_ID", referencedColumnName="ID")
  private Set<Order> orders;


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


  public Set<Order> getOrders()
  {
    return orders;
  }


  public void setOrders(Set<Order> orders)
  {
    this.orders = orders;
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


  public String getAddress()
  {
    return address;
  }


  public void setAddress(String address)
  {
    this.address = address;
  }


  public String getCity()
  {
    return city;
  }


  public void setCity(String city)
  {
    this.city = city;
  }
}
