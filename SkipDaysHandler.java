package com.simplerss.handler;

import com.simplerss.dataobject.SkipDays;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class SkipDaysHandler
  extends ChainedHandler
{
  SkipDays skipDays = null;
  ArrayList days = null;
  String currentTag = null;
  
  public SkipDaysHandler(ChainedHandler paramChainedHandler)
  {
    super(paramChainedHandler);
    days = new ArrayList();
  }
  
  public void startHandlingEvents(String paramString, Attributes paramAttributes) throws SAXException
  {
    super.startHandlingEvents(paramString, paramAttributes);
    skipDays = new SkipDays();
    currentTag = paramString;
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3) throws SAXException
  {
    String str = paramString3.toLowerCase();
    if ("day".equals(str))
    {
      days.add(mText);
    }
    else if (currentTag.equals(str))
    {
      skipDays.setDay((String[])days.toArray(new String[days.size()]));
      mParent.setAttribute(currentTag, skipDays);
      stopHandlingEvents();
    }
  }
}