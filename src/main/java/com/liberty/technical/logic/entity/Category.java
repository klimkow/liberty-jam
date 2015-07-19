package com.liberty.technical.logic.entity;

import com.liberty.technical.logic.entity.images.CategoryImages;

import javax.persistence.*;
import java.util.Set;


/**
 * @author M-AKI.
 */
@Entity
@Table(name = "CATEGORY")
public class Category
{
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @OneToMany(cascade=CascadeType.PERSIST)
  @JoinColumn(name="CATEGORY_ID", referencedColumnName="ID")
  private Set<CategoryImages> images;


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


  public Set<CategoryImages> getImages()
  {
    return images;
  }


  public void setImages(Set<CategoryImages> images)
  {
    this.images = images;
  }


  public String getDescription()
  {
    return description;
  }


  public void setDescription(String description)
  {
    this.description = description;
  }
}
