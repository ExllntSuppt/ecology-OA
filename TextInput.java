package com.simplerss.dataobject;

import java.net.URL;









public class TextInput
{
  private String title;
  private String description;
  private String name;
  private URL link;
  
  public TextInput() {}
  
  public String getDescription()
  {
    return description;
  }
  
  public void setDescription(String paramString) { description = paramString; }
  
  public URL getLink() {
    return link;
  }
  
  public void setLink(URL paramURL) { link = paramURL; }
  
  public String getName() {
    return name;
  }
  
  public void setName(String paramString) { name = paramString; }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String paramString) { title = paramString; }
  


  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    
    localStringBuffer.append("Title => " + title + ", ");
    localStringBuffer.append("Description => " + description + ", ");
    localStringBuffer.append("Name => " + name + ", ");
    localStringBuffer.append("Link => " + link + ", ");
    
    return localStringBuffer.toString();
  }
}