package com.weaver.cssRenderHandler;

import java.util.Map;

public abstract interface CssRenderHandler
{
  public abstract CssDescriber getCssDescriber(Map<String, String> paramMap, String paramString);
}