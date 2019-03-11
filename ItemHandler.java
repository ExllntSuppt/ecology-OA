package com.simplerss.handler;

import com.simplerss.dataobject.Enclosure;
import com.simplerss.dataobject.Guid;
import com.simplerss.dataobject.Item;
import com.simplerss.dataobject.Source;
import java.net.MalformedURLException;
import java.net.URL;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class ItemHandler
  extends ChainedHandler
{
  Item item = null;
  EnclosureHandler enclosureHandler = null;
  GuidHandler guidHandler = null;
  SourceHandler sourceHandler = null;
  
  String currentTag = null;
  
  public ItemHandler(ChainedHandler paramChainedHandler) {
    super(paramChainedHandler);
    enclosureHandler = new EnclosureHandler(this);
    guidHandler = new GuidHandler(this);
    sourceHandler = new SourceHandler(this);
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes) throws SAXException
  {
    super.startElement(paramString1, paramString2, paramString3, paramAttributes);
    String str = paramString2.toLowerCase();
    if ("enclosure".equals(str)) {
      enclosureHandler.startHandlingEvents(str, paramAttributes);
    }
    else if ("guid".equals(str)) {
      guidHandler.startHandlingEvents(str, paramAttributes);
    }
    else if ("source".equals(str)) {
      sourceHandler.startHandlingEvents(str, paramAttributes);
    }
  }
  
  public void setAttribute(String paramString, Object paramObject) throws SAXException {
    if ("enclosure".equals(paramString)) {
      item.setEnclosure((Enclosure)paramObject);
    }
    else if ("guid".equals(paramString)) {
      item.setGuid((Guid)paramObject);
    }
    else if ("source".equals(paramString)) {
      item.setSource((Source)paramObject);
    }
  }
  
  public void startHandlingEvents(String paramString, Attributes paramAttributes) throws SAXException
  {
    super.startHandlingEvents(paramString, paramAttributes);
    item = new Item();
    currentTag = paramString;
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3) throws SAXException
  {
    String str = paramString3.toLowerCase();
    if ("author".equals(str))
    {
      item.setAuthor(mText);
    }
    else if ("category".equals(str))
    {
      item.setCategory(mText);
    }
    else if ("comments".equals(str))
    {
      item.setComments(mText);
    }
    else if ("description".equals(str))
    {
      item.setDescription(mText);
    }
    else if ("link".equals(str))
    {
      try
      {
        item.setLink(new URL(mText));
      }
      catch (MalformedURLException localMalformedURLException)
      {
        item.setLink(null);
      }
    }
    else if ("pubdate".equals(str))
    {
      item.setPubDate(mText);
    }
    else if ("title".equals(str))
    {
      item.setTitle(mText);
    }
    else if (currentTag.equals(str))
    {
      mParent.setAttribute(currentTag, item);
      stopHandlingEvents();
    }
  }
}