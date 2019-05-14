package com.weaver.ecology.search.model.base;

public abstract class AbstractHrmOrgGroupRelated extends AbstractBaseBean {
  private Integer id;
  private Integer orgGroupId;
  private Integer type;
  
  public AbstractHrmOrgGroupRelated() {}
  
  public Integer getContent() {
    return content;
  }
  
  public void setContent(Integer paramInteger) { content = paramInteger; }
  
  private Integer content;
  public Integer getId() { return id; }
  
  public void setId(Integer paramInteger) {
    id = paramInteger;
  }
  
  public Integer getOrgGroupId() { return orgGroupId; }
  
  public void setOrgGroupId(Integer paramInteger) {
    orgGroupId = paramInteger;
  }
  
  public Integer getSecLevelFrom() { return secLevelFrom; }
  
  public void setSecLevelFrom(Integer paramInteger) {
    secLevelFrom = paramInteger;
  }
  
  public Integer getSecLevelTo() { return secLevelTo; }
  

  public void setSecLevelTo(Integer paramInteger) { secLevelTo = paramInteger; }
  private Integer secLevelFrom;
  
  public Integer getType() { return type; }
  
  private Integer secLevelTo;
  public void setType(Integer paramInteger) { type = paramInteger; }
}
