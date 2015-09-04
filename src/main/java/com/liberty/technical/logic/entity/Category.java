package com.liberty.technical.logic.entity;

import com.liberty.technical.logic.entity.images.CategoryImages;

import javax.persistence.*;
import java.util.Set;


/**
 * @author M-AKI.
 */
@Entity
@Table(name = "category")
public class Category
{
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @OneToMany(targetEntity=com.liberty.technical.logic.entity.images.CategoryImages.class,
          cascade=CascadeType.PERSIST)
  @JoinColumn(name="category_id", referencedColumnName="id")
  private Set images;


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


  public Set getImages()
  {
    return images;
  }


  public void setImages(Set images)
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
