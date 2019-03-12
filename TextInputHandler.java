package com.simplerss.handler;

import com.simplerss.dataobject.TextInput;
import java.net.MalformedURLException;
import java.net.URL;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class TextInputHandler
  extends ChainedHandler
{
  TextInput textInput = null;
  String currentTag = null;
  
  public TextInputHandler(ChainedHandler paramChainedHandler)
  {
    super(paramChainedHandler);
  }
  
  public void startHandlingEvents(String paramString, Attributes paramAttributes) throws SAXException
  {
    super.startHandlingEvents(paramString, paramAttributes);
    textInput = new TextInput();
    currentTag = paramString;
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3) throws SAXException
  {
    String str = paramString3.toLowerCase();
    if ("description".equals(str))
    {
      textInput.setDescription(mText);
    }
    else if ("link".equals(str))
    {
      try
      {
        textInput.setLink(new URL(mText));
      }
      catch (MalformedURLException localMalformedURLException)
      {
        textInput.setLink(null);
      }
    }
    else if ("name".equals(str))
    {
      textInput.setName(mText);
    }
    else if ("title".equals(str))
    {
      textInput.setTitle(mText);
    }
    else if (currentTag.equals(str))
    {
      mParent.setAttribute(currentTag, textInput);
      stopHandlingEvents();
    }
  }
}