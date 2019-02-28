package com.simplerss.dataobject;

import java.net.URL;
import java.util.Calendar;

public class Channel
{
  private String title;
  private URL link;
  private String description;
  private String language;
  private String copyright;
  private String managingEditor;
  private String webMaster;
  private String pubDate;
  private Calendar lastBuildDate;
  private String category;
  private String generator;
  private URL docs;
  private Cloud cloud;
  private int ttl;
  private Image image;
  private String rating;
  private TextInput textInput;
  private SkipHours skipHours;
  private SkipDays skipDays;
  private Item[] item;
  
  public Channel() {}
  
  public Channel(String paramString1, URL paramURL1, String paramString2, 
  String paramString3, String paramString4, String paramString5, 
  String paramString6, String paramString7, Calendar paramCalendar,
  String paramString8, String paramString9, URL paramURL2, Cloud paramCloud, 
  int paramInt, Image paramImage, String paramString10, TextInput paramTextInput, SkipHours paramSkipHours, 
  SkipDays paramSkipDays, Item[] paramArrayOfItem)
  {
    title = paramString1;
    link = paramURL1;
    description = paramString2;
    language = paramString3;
    copyright = paramString4;
    managingEditor = paramString5;
    webMaster = paramString6;
    pubDate = paramString7;
    lastBuildDate = paramCalendar;
    category = paramString8;
    generator = paramString9;
    docs = paramURL2;
    cloud = paramCloud;
    ttl = paramInt;
    image = paramImage;
    rating = paramString10;
    textInput = paramTextInput;
    skipHours = paramSkipHours;
    skipDays = paramSkipDays;
    item = paramArrayOfItem;
  }
  
  public String getCategory() {
    return category;
  }
  
  public void setCategory(String paramString) { category = paramString; }
  
  public Cloud getCloud() {
    return cloud;
  }
  
  public void setCloud(Cloud paramCloud) { cloud = paramCloud; }
  
  public String getCopyright() {
    return copyright;
  }
  
  public void setCopyright(String paramString) { copyright = paramString; }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String paramString) { description = paramString; }
  
  public URL getDocs() {
    return docs;
  }
  
  public void setDocs(URL paramURL) { docs = paramURL; }
  
  public String getGenerator() {
    return generator;
  }
  
  public void setGenerator(String paramString) { generator = paramString; }
  
  public Image getImage() {
    return image;
  }
  
  public void setImage(Image paramImage) { image = paramImage; }
  
  public Item[] getItem() {
    return item;
  }
  
  public void setItem(Item[] paramArrayOfItem) { item = paramArrayOfItem; }
  
  public String getLanguage() {
    return language;
  }
  
  public void setLanguage(String paramString) { language = paramString; }
  
  public Calendar getLastBuildDate() {
    return lastBuildDate;
  }
  
  public void setLastBuildDate(Calendar paramCalendar) { lastBuildDate = paramCalendar; }
  
  public URL getLink() {
    return link;
  }
  
  public void setLink(URL paramURL) { link = paramURL; }
  
  public String getManagingEditor() {
    return managingEditor;
  }
  
  public void setManagingEditor(String paramString) { managingEditor = paramString; }
  
  public String getPubDate() {
    return pubDate;
  }
  
  public void setPubDate(String paramString) { pubDate = paramString; }
  
  public String getRating() {
    return rating;
  }
  
  public void setRating(String paramString) { rating = paramString; }
  
  public SkipDays getSkipDays() {
    return skipDays;
  }
  
  public void setSkipDays(SkipDays paramSkipDays) { skipDays = paramSkipDays; }
  
  public SkipHours getSkipHours() {
    return skipHours;
  }
  
  public void setSkipHours(SkipHours paramSkipHours) { skipHours = paramSkipHours; }
  
  public TextInput getTextInput() {
    return textInput;
  }
  
  public void setTextInput(TextInput paramTextInput) { textInput = paramTextInput; }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String paramString) { title = paramString; }
  
  public int getTtl() {
    return ttl;
  }
  
  public void setTtl(int paramInt) { ttl = paramInt; }
  
  public String getWebMaster() {
    return webMaster;
  }
  
  public void setWebMaster(String paramString) { webMaster = paramString; }
  


  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("Title => " + title + "\n");
    localStringBuffer.append("Link => " + link + "\n");
    localStringBuffer.append("Description => " + description + "\n");
    localStringBuffer.append("Language => " + language + "\n");
    localStringBuffer.append("Copyright => " + copyright + "\n");
    localStringBuffer.append("ManagingEditor => " + managingEditor + "\n");
    localStringBuffer.append("WebMaster => " + webMaster + "\n");
    localStringBuffer.append("pubDate => " + pubDate + "\n");
    localStringBuffer.append("LastBuildDate => " + lastBuildDate + "\n");
    localStringBuffer.append("Category => " + category + "\n");
    localStringBuffer.append("Generator => " + generator + "\n");
    localStringBuffer.append("Docs => " + docs + "\n");
    if (cloud != null)
    {
      localStringBuffer.append("Cloud => " + cloud + "\n");
    }
    localStringBuffer.append("TTL => " + ttl + "\n");
    if (image != null)
    {
      localStringBuffer.append("Image => " + image + "\n");
    }
    localStringBuffer.append("Rating => " + rating + "\n");
    if (textInput != null)
    {
      localStringBuffer.append("TextInput => " + textInput + "\n");
    }
    if (skipHours != null)
    {
      localStringBuffer.append("SkipHours => " + skipHours + "\n");
    }
    if (skipDays != null)
    {
      localStringBuffer.append("SkipDays => " + skipDays + "\n");
    }
    if (item != null)
    {
      localStringBuffer.append("Items =>\n");
      for (int i = 0; i < item.length; i++)
        localStringBuffer.append("{\n" + item[i] + "}\n");
    }
    return localStringBuffer.toString();
  }
}
/*
package com.simplerss.dataobject;

import com.simplerss.dataobject.Cloud;
import com.simplerss.dataobject.Image;
import com.simplerss.dataobject.Item;
import com.simplerss.dataobject.SkipDays;
import com.simplerss.dataobject.SkipHours;
import com.simplerss.dataobject.TextInput;
import java.net.URL;
import java.util.Calendar;

public class Channel {
    private String title;
    private URL link;
    private String description;
    private String language;
    private String copyright;
    private String managingEditor;
    private String webMaster;
    private String pubDate;
    private Calendar lastBuildDate;
    private String category;
    private String generator;
    private URL docs;
    private Cloud cloud;
    private int ttl;
    private Image image;
    private String rating;
    private TextInput textInput;
    private SkipHours skipHours;
    private SkipDays skipDays;
    private Item[] item;

    public Channel() {
    }

    public Channel(String string, URL uRL, String string2, String string3, String string4, String string5, String string6, String string7, Calendar calendar, String string8, String string9, URL uRL2, Cloud cloud, int n, Image image, String string10, TextInput textInput, SkipHours skipHours, SkipDays skipDays, Item[] arritem) {
        this.title = string;
        this.link = uRL;
        this.description = string2;
        this.language = string3;
        this.copyright = string4;
        this.managingEditor = string5;
        this.webMaster = string6;
        this.pubDate = string7;
        this.lastBuildDate = calendar;
        this.category = string8;
        this.generator = string9;
        this.docs = uRL2;
        this.cloud = cloud;
        this.ttl = n;
        this.image = image;
        this.rating = string10;
        this.textInput = textInput;
        this.skipHours = skipHours;
        this.skipDays = skipDays;
        this.item = arritem;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String string) {
        this.category = string;
    }

    public Cloud getCloud() {
        return this.cloud;
    }

    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }

    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String string) {
        this.copyright = string;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String string) {
        this.description = string;
    }

    public URL getDocs() {
        return this.docs;
    }

    public void setDocs(URL uRL) {
        this.docs = uRL;
    }

    public String getGenerator() {
        return this.generator;
    }

    public void setGenerator(String string) {
        this.generator = string;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Item[] getItem() {
        return this.item;
    }

    public void setItem(Item[] arritem) {
        this.item = arritem;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String string) {
        this.language = string;
    }

    public Calendar getLastBuildDate() {
        return this.lastBuildDate;
    }

    public void setLastBuildDate(Calendar calendar) {
        this.lastBuildDate = calendar;
    }

    public URL getLink() {
        return this.link;
    }

    public void setLink(URL uRL) {
        this.link = uRL;
    }

    public String getManagingEditor() {
        return this.managingEditor;
    }

    public void setManagingEditor(String string) {
        this.managingEditor = string;
    }

    public String getPubDate() {
        return this.pubDate;
    }

    public void setPubDate(String string) {
        this.pubDate = string;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String string) {
        this.rating = string;
    }

    public SkipDays getSkipDays() {
        return this.skipDays;
    }

    public void setSkipDays(SkipDays skipDays) {
        this.skipDays = skipDays;
    }

    public SkipHours getSkipHours() {
        return this.skipHours;
    }

    public void setSkipHours(SkipHours skipHours) {
        this.skipHours = skipHours;
    }

    public TextInput getTextInput() {
        return this.textInput;
    }

    public void setTextInput(TextInput textInput) {
        this.textInput = textInput;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String string) {
        this.title = string;
    }

    public int getTtl() {
        return this.ttl;
    }

    public void setTtl(int n) {
        this.ttl = n;
    }

    public String getWebMaster() {
        return this.webMaster;
    }

    public void setWebMaster(String string) {
        this.webMaster = string;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Title => " + this.title + "\n");
        stringBuffer.append("Link => " + this.link + "\n");
        stringBuffer.append("Description => " + this.description + "\n");
        stringBuffer.append("Language => " + this.language + "\n");
        stringBuffer.append("Copyright => " + this.copyright + "\n");
        stringBuffer.append("ManagingEditor => " + this.managingEditor + "\n");
        stringBuffer.append("WebMaster => " + this.webMaster + "\n");
        stringBuffer.append("pubDate => " + this.pubDate + "\n");
        stringBuffer.append("LastBuildDate => " + this.lastBuildDate + "\n");
        stringBuffer.append("Category => " + this.category + "\n");
        stringBuffer.append("Generator => " + this.generator + "\n");
        stringBuffer.append("Docs => " + this.docs + "\n");
        if (this.cloud != null) {
            stringBuffer.append("Cloud => " + (Object)this.cloud + "\n");
        }
        stringBuffer.append("TTL => " + this.ttl + "\n");
        if (this.image != null) {
            stringBuffer.append("Image => " + (Object)this.image + "\n");
        }
        stringBuffer.append("Rating => " + this.rating + "\n");
        if (this.textInput != null) {
            stringBuffer.append("TextInput => " + (Object)this.textInput + "\n");
        }
        if (this.skipHours != null) {
            stringBuffer.append("SkipHours => " + (Object)this.skipHours + "\n");
        }
        if (this.skipDays != null) {
            stringBuffer.append("SkipDays => " + (Object)this.skipDays + "\n");
        }
        if (this.item != null) {
            stringBuffer.append("Items =>\n");
            for (int i = 0; i < this.item.length; ++i) {
                stringBuffer.append("{\n" + (Object)this.item[i] + "}\n");
            }
        }
        return stringBuffer.toString();
    }
}
*/