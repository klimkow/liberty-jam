package com.liberty.technical.logic.entity.system;

import javax.persistence.*;


/**
 * @author M-AKI.
 */
@Entity
@Table(name = "system_user")
public class SystemUser
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;


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


  public String getPassword()
  {
    return password;
  }


  public void setPassword(String password)
  {
    this.password = password;
  }
}
