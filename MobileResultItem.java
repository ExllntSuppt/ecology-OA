package com.weaver.ecology.search.model;

import com.weaver.ecology.search.model.base.AbstractBaseBean;




public class MobileResultItem
  extends AbstractBaseBean
{
  private static final long serialVersionUID = 9003128171352713409L;
  private String id;
  private String schema;
  private String title;
  private String content;
  private String description;
  private String date;
  private String other;
  private String url;
  
  public MobileResultItem() {}
  
  public String getId()
  {
    return id;
  }
  
  public void setId(String paramString) { id = paramString; }
  
  public String getSchema() {
    return schema;
  }
  
  public void setSchema(String paramString) { schema = paramString; }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String paramString) { title = paramString; }
  
  public String getContent() {
    return content;
  }
  
  public void setContent(String paramString) { content = paramString; }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String paramString) { description = paramString; }
  
  public String getOther() {
    return other;
  }
  
  public void setOther(String paramString) { other = paramString; }
  
  public String getDate() {
    return date;
  }
  
  public void setDate(String paramString) { date = paramString; }
  
  public String getUrl() {
    return url;
  }
  
  public void setUrl(String paramString) { url = paramString; }
}