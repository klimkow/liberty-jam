package com.liberty.technical.logic.entity.images;

import com.liberty.technical.logic.entity.Category;

import javax.persistence.*;


/**
 * @author M-AKI.
 */
@Entity
@Table(name = "category_images")
public class CategoryImages
{
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "image")
  private String imageUrl;

  @ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
  @JoinColumn(name="category_id", referencedColumnName="id")
  private com.liberty.technical.logic.entity.Category Category;


  public long getId()
  {
    return id;
  }


  public void setId(long id)
  {
    this.id = id;
  }


  public String getImageUrl()
  {
    return imageUrl;
  }


  public void setImageUrl(String imageUrl)
  {
    this.imageUrl = imageUrl;
  }


  public Category getCategory()
  {
    return Category;
  }


  public void setCategory(Category Category)
  {
    this.Category = Category;
  }
}
