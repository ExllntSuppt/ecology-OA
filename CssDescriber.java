package com.weaver.cssRenderHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CssDescriber {
	private List<String> classnames = new ArrayList(0);

	private Map<String, String> map = new HashMap();

	public CssDescriber() {
	}

	public void addCss(String paramString1, String paramString2) {
		map.put(paramString1, paramString2);
	}

	public void addClass(String paramString) {
		classnames.add(paramString);
	}

	public String toString() {
		return JsonUtils.object2json(this);
	}

	public List<String> getClassnames() {
		return classnames;
	}

	public Map<String, String> getMap() {
		return map;
	}
}