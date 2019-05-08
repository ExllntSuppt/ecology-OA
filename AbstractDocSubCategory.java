package com.weaver.ecology.search.model.base;

public abstract class AbstractDocSubCategory extends AbstractBaseBean
{
  private Integer id;
  private Integer maincategoryid;
  private String categoryname;
  
  public AbstractDocSubCategory() {}
  
  public String getCategoryname() {
    return categoryname;
  }
  
  public void setCategoryname(String paramString) { categoryname = paramString; }
  
  private Integer subcategoryid;
  public String getCoder() { return coder; }
  
  private String seccategoryids;
  public void setCoder(String paramString) { coder = paramString; }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer paramInteger) { id = paramInteger; }
  
  public Integer getMaincategoryid() {
    return maincategoryid;
  }
  
  public void setMaincategoryid(Integer paramInteger) { maincategoryid = paramInteger; }
  

  public Integer getNorepeatedname() { return norepeatedname; }
  private String coder;
  
  public void setNorepeatedname(Integer paramInteger) { norepeatedname = paramInteger; }
  

  public String getSeccategoryids() { return seccategoryids; }
  
  private Integer norepeatedname;
  public void setSeccategoryids(String paramString) { seccategoryids = paramString; }
  
  public Integer getSubcategoryid() {
    return subcategoryid;
  }
  
  public void setSubcategoryid(Integer paramInteger) { subcategoryid = paramInteger; }
}