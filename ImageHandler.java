package com.simplerss.handler;

import com.simplerss.dataobject.Image;
import java.net.MalformedURLException;
import java.net.URL;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ImageHandler
  extends ChainedHandler
{
  Image image = null;
  String currentTag = null;
  
  public ImageHandler(ChainedHandler paramChainedHandler)
  {
    super(paramChainedHandler);
  }
  
  public void startHandlingEvents(String paramString, Attributes paramAttributes) throws SAXException
  {
    super.startHandlingEvents(paramString, paramAttributes);
    image = new Image();
    currentTag = paramString;
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3) throws SAXException
  {
    String str = paramString3.toLowerCase();
    if ("description".equals(str))
    {
      image.setDescription(mText);
    }
    else if ("height".equals(str))
    {
      image.setHeight(mText);
    }
    else if ("title".equals(str))
    {
      image.setTitle(mText);
    }
    else if ("width".equals(str))
    {
      image.setWidth(mText);
    }
    else if ("url".equals(str))
    {
      try
      {
        image.setUrl(new URL(mText));
      }
      catch (MalformedURLException localMalformedURLException1)
      {
        image.setUrl(null);
      }
    }
    else if ("link".equals(str))
    {
      try
      {
        image.setLink(new URL(mText));
      }
      catch (MalformedURLException localMalformedURLException2)
      {
        image.setLink(null);
      }
    }
    else if (currentTag.equals(str))
    {
      mParent.setAttribute(currentTag, image);
      stopHandlingEvents();
    }
  }
}