package com.weaver.ecology.search.model.base;


public abstract class AbstractImageFile
  extends AbstractBaseBean
{
  private Integer imagefileid;
  private String imagefilename;
  private String imagefiletype;
  private byte[] imagefile;
  private Integer imagefileused;
  private String filerealpath;
  
  public AbstractImageFile() {}
  
  public Integer getDownloads()
  {
    return downloads;
  }
  
  public void setDownloads(Integer paramInteger) { downloads = paramInteger; }
  
  private String iszip;
  public String getFilerealpath() { return filerealpath; }
  
  private String isencrypt;
  public void setFilerealpath(String paramString) { filerealpath = paramString; }
  
  private String fileSize;
  public String getFileSize() { return fileSize; }
  
  private Integer downloads;
  public void setFileSize(String paramString) { fileSize = paramString; }
  
  private String miniimgpath;
  public byte[] getImagefile() { return imagefile; }
  private String isaesencrypt;
  
  public void setImagefile(byte[] paramArrayOfByte) { imagefile = paramArrayOfByte; }
  
  private String aescode;
  public Integer getImagefileid() { return imagefileid; }
  
  public void setImagefileid(Integer paramInteger) {
    imagefileid = paramInteger;
  }
  
  public String getImagefilename() { return imagefilename; }
  
  public void setImagefilename(String paramString) {
    imagefilename = paramString;
  }
  
  public String getImagefiletype() { return imagefiletype; }
  
  public void setImagefiletype(String paramString) {
    imagefiletype = paramString;
  }
  
  public Integer getImagefileused() { return imagefileused; }
  
  public void setImagefileused(Integer paramInteger) {
    imagefileused = paramInteger;
  }
  
  public String getIsencrypt() { return isencrypt; }
  
  public void setIsencrypt(String paramString) {
    isencrypt = paramString;
  }
  
  public String getIszip() { return iszip; }
  
  public void setIszip(String paramString) {
    iszip = paramString;
  }
  
  public String getMiniimgpath() { return miniimgpath; }
  
  public void setMiniimgpath(String paramString) {
    miniimgpath = paramString;
  }
  
  public String getIsaesencrypt() { return isaesencrypt; }
  
  public void setIsaesencrypt(String paramString) {
    isaesencrypt = paramString;
  }
  
  public String getAescode() { return aescode; }
  
  public void setAescode(String paramString) {
    aescode = paramString;
  }
}