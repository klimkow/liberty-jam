package com.liberty.technical.logic.entity.service;

import javax.persistence.*;
import java.util.Date;

/**
 * @author AKI
 */
@Entity
@Table(name = "RESTRICTED_DATES")
public class DeliveryTimePeriod
{
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "DATE")
  private Date date;

  @Column(name = "TIME_PERIOD")
  private Integer timePeriod;

  public long getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getTimePeriod() {
    return timePeriod;
  }

  public void setTimePeriod(Integer timePeriod) {
    this.timePeriod = timePeriod;
  }
}
