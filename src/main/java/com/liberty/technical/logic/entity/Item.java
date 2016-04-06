package com.liberty.technical.logic.entity;


import com.liberty.technical.logic.entity.images.ItemImages;
import com.liberty.technical.logic.entity.service.PriceDiapason;
import org.hibernate.annotations.Formula;

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

  @Column(name = "min_amount")
  private Integer minAmount;

  @Column(name = "size")
  private String size;

  @OneToMany(targetEntity = com.liberty.technical.logic.entity.service.PriceDiapason.class,
      cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name="item_id", referencedColumnName="id")
  private Set<PriceDiapason> priceDiapasonList;

  // is used in ItemDAO
  @Formula("price*min_amount")
  private Integer minPrice;

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


  public Set<PriceDiapason> getPriceDiapasonList()
  {
    return priceDiapasonList;
  }


  public void setPriceDiapasonList(Set<PriceDiapason> priceDiapasonList)
  {
    this.priceDiapasonList = priceDiapasonList;
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


  public Integer getMinAmount()
  {
    return minAmount;
  }


  public void setMinAmount(Integer minAmount)
  {
    this.minAmount = minAmount;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
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

  public int getPriceIncludingDiapasons(int count)
  {
    for (PriceDiapason diapason : getPriceDiapasonList()) {
      if (count >= diapason.getCountFrom() && count <= diapason.getCountTo()) {
        return diapason.getPrice();
      }
    }
    return getPrice();
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
