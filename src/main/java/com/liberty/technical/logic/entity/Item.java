package com.liberty.technical.logic.entity;


import com.liberty.technical.logic.entity.images.ItemImages;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;


/**
 * @author M-AKI.
 */
@Entity
@Table(name = "item")
public class Item
{
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "available_amount")
  private Integer availableAmount;

  @Column(name = "price")
  private Integer price;

  @ManyToMany(
          targetEntity=com.liberty.technical.logic.entity.Category.class,
          cascade = {CascadeType.PERSIST, CascadeType.MERGE}
      , fetch = FetchType.EAGER)
  @JoinTable(name = "category_has_item",
      joinColumns = {@JoinColumn(name = "item_id")},
      inverseJoinColumns = {@JoinColumn(name = "category_id")})
  private Set<Category> categories;

  @OneToMany(targetEntity = com.liberty.technical.logic.entity.images.ItemImages.class,
          cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name="item_id", referencedColumnName="id")
  private Set<ItemImages> images;

  public long getId()
  {
    return id;
  }


  public void setId(long id)
  {
    this.id = id;
  }


  public Set<Category> getCategories()
  {
    return categories;
  }


  public void setCategories(Set categories)
  {
    this.categories = categories;
  }


  public String getName()
  {
    return name;
  }


  public void setName(String name)
  {
    this.name = name;
  }


  public String getDescription()
  {
    return description;
  }


  public void setDescription(String description)
  {
    this.description = description;
  }


  public Integer getAvailableAmount()
  {
    return availableAmount;
  }


  public void setAvailableAmount(Integer availableAmount)
  {
    this.availableAmount = availableAmount;
  }


  public Set<ItemImages> getImages()
  {
    return images;
  }


  public void setImages(Set<ItemImages> images)
  {
    this.images = images;
  }


  public Integer getPrice()
  {
    return price;
  }


  public void setPrice(Integer price)
  {
    this.price = price;
  }

  public String getLogo()
  {
    StringBuilder result = new StringBuilder();
    if (!(getImages() == null || getImages().isEmpty())) {
      Optional<ItemImages> image = getImages().stream().
          filter(im -> im.getSeqenceOrder() == ItemImages.IMAGE_LOGO).findFirst();
      if (image.isPresent()) {
        result.append(image.get().getImageUrl());
      }
    }
    return result.toString();
  }

  public String getSecondImageUrl()
  {
    StringBuilder result = new StringBuilder();
    if (!(getImages() == null || getImages().isEmpty())) {
      Optional<ItemImages> image = getImages().stream().
          filter(im -> im.getSeqenceOrder() == ItemImages.SECOND_IMAGE).findFirst();
      if (image.isPresent()) {
        result.append(image.get().getImageUrl());
      }
    }
    return result.toString();
  }

  public String getThirdImageUrl()
  {
    StringBuilder result = new StringBuilder();
    if (!(getImages() == null || getImages().isEmpty())) {
      Optional<ItemImages> image = getImages().stream().
          filter(im -> im.getSeqenceOrder() == ItemImages.THIRD_IMAGE).findFirst();
      if (image.isPresent()) {
        result.append(image.get().getImageUrl());
      }
    }
    return result.toString();
  }

  public String getCategoryName() {
    return getCategories() == null || getCategories().isEmpty() ?
        "" : getCategories().iterator().next().getName();
  }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            return id == ((Item) obj).getId();
        }
        return false;
    }
}
