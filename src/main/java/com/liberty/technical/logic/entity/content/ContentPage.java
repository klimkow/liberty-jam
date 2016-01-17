package com.liberty.technical.logic.entity.content;

import javax.persistence.*;


/**
 * @author M-AKI.
 */
@Entity
@Table(name = "content_pages")
public class ContentPage
{
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "menu_id")
  private Integer menuId;


  public String getTitle()
  {
    return title;
  }


  public void setTitle(String title)
  {
    this.title = title;
  }


  public String getContent()
  {
    return content;
  }


  public void setContent(String content)
  {
    this.content = content;
  }


  public Integer getMenuId()
  {
    return menuId;
  }


  public void setMenuId(Integer menuId)
  {
    this.menuId = menuId;
  }
}
