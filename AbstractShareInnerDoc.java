package com.weaver.ecology.search.model.base;

public abstract class AbstractShareInnerDoc extends AbstractBaseBean
{
  private Integer id;
  private Integer sourceid;
  private Integer type;
  private Integer content;
  
  public AbstractShareInnerDoc() {}
  
  public Integer getContent()
  {
    return content;
  }
  
  public void setContent(Integer paramInteger) { content = paramInteger; }
  
  private Integer seclevel;
  public Integer getId() { return id; }
  
  private Integer sharelevel;
  public void setId(Integer paramInteger) { id = paramInteger; }
  
  private Integer srcfrom;
  public Integer getOpuser() { return opuser; }
  
  public void setOpuser(Integer paramInteger) {
    opuser = paramInteger;
  }
  
  public Integer getSeclevel() { return seclevel; }
  
  public void setSeclevel(Integer paramInteger) {
    seclevel = paramInteger;
  }
  
  public Integer getSharelevel() { return sharelevel; }
  private Integer opuser;
  
  public void setSharelevel(Integer paramInteger) { sharelevel = paramInteger; }
  
  private Integer sharesource;
  public Integer getSharesource() { return sharesource; }
  
  public void setSharesource(Integer paramInteger) {
    sharesource = paramInteger;
  }
  
  public Integer getSourceid() { return sourceid; }
  
  public void setSourceid(Integer paramInteger) {
    sourceid = paramInteger;
  }
  
  public Integer getSrcfrom() { return srcfrom; }
  
  public void setSrcfrom(Integer paramInteger) {
    srcfrom = paramInteger;
  }
  
  public Integer getType() { return type; }
  
  public void setType(Integer paramInteger) {
    type = paramInteger;
  }
}