package com.weaver.cssRenderHandler;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonUtils {
	public JsonUtils() {
	}

	public static String object2json(Object paramObject) {
		StringBuilder localStringBuilder = new StringBuilder();
		if (paramObject == null) {
			localStringBuilder.append("\"\"");
		} else if (((paramObject instanceof String))
				|| ((paramObject instanceof Integer))
				|| ((paramObject instanceof Float))
				|| ((paramObject instanceof Boolean))
				|| ((paramObject instanceof Short))
				|| ((paramObject instanceof Double))
				|| ((paramObject instanceof Long))
				|| ((paramObject instanceof BigDecimal))
				|| ((paramObject instanceof BigInteger))
				|| ((paramObject instanceof Byte))) {

			localStringBuilder.append("\"")
					.append(string2json(paramObject.toString())).append("\"");
		} else if ((paramObject instanceof Object[])) {
			localStringBuilder.append(array2json((Object[]) paramObject));
		} else if ((paramObject instanceof List)) {
			localStringBuilder.append(list2json((List) paramObject));
		} else if ((paramObject instanceof Map)) {
			localStringBuilder.append(map2json((Map) paramObject));
		} else if ((paramObject instanceof Set)) {
			localStringBuilder.append(set2json((Set) paramObject));
		} else {
			localStringBuilder.append(bean2json(paramObject));
		}
		return localStringBuilder.toString();
	}

	public static String bean2json(Object paramObject) {
		StringBuilder localStringBuilder = new StringBuilder();
		localStringBuilder.append("{");
		PropertyDescriptor[] arrayOfPropertyDescriptor = null;
		try {
			arrayOfPropertyDescriptor = Introspector.getBeanInfo(
					paramObject.getClass(), Object.class)
					.getPropertyDescriptors();
		} catch (IntrospectionException localIntrospectionException) {
			localIntrospectionException.printStackTrace();
		}
		if (arrayOfPropertyDescriptor != null) {
			for (int i = 0; i < arrayOfPropertyDescriptor.length; i++) {
				try {
					String str1 = object2json(arrayOfPropertyDescriptor[i]
							.getName());
					String str2 = object2json(arrayOfPropertyDescriptor[i]
							.getReadMethod().invoke(paramObject, new Object[0]));
					localStringBuilder.append(str1);
					localStringBuilder.append(":");
					localStringBuilder.append(str2);
					localStringBuilder.append(",");
				} catch (Exception localException) {
					localException.printStackTrace();
				}
			}
			localStringBuilder.setCharAt(localStringBuilder.length() - 1, '}');
		} else {
			localStringBuilder.append("}");
		}
		return localStringBuilder.toString();
	}

	public static String list2json(List<?> paramList) {
		StringBuilder localStringBuilder = new StringBuilder();
		localStringBuilder.append("[");
		if ((paramList != null) && (paramList.size() > 0)) {
			for (Object localObject : paramList) {
				localStringBuilder.append(object2json(localObject));
				localStringBuilder.append(",");
			}
			localStringBuilder.setCharAt(localStringBuilder.length() - 1, ']');
		} else {
			localStringBuilder.append("]");
		}
		return localStringBuilder.toString();
	}

	public static String array2json(Object[] paramArrayOfObject) {
		StringBuilder localStringBuilder = new StringBuilder();
		localStringBuilder.append("[");
		if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0)) {
			for (Object localObject : paramArrayOfObject) {
				localStringBuilder.append(object2json(localObject));
				localStringBuilder.append(",");
			}
			localStringBuilder.setCharAt(localStringBuilder.length() - 1, ']');
		} else {
			localStringBuilder.append("]");
		}
		return localStringBuilder.toString();
	}

	public static String map2json(Map<?, ?> paramMap) {
		StringBuilder localStringBuilder = new StringBuilder();
		localStringBuilder.append("{");
		if ((paramMap != null) && (paramMap.size() > 0)) {
			for (Object localObject : paramMap.keySet()) {
				localStringBuilder.append(object2json(localObject));
				localStringBuilder.append(":");
				localStringBuilder
						.append(object2json(paramMap.get(localObject)));
				localStringBuilder.append(",");
			}
			localStringBuilder.setCharAt(localStringBuilder.length() - 1, '}');
		} else {
			localStringBuilder.append("}");
		}
		return localStringBuilder.toString();
	}

	public static String set2json(Set<?> paramSet) {
		StringBuilder localStringBuilder = new StringBuilder();
		localStringBuilder.append("[");
		if ((paramSet != null) && (paramSet.size() > 0)) {
			for (Object localObject : paramSet) {
				localStringBuilder.append(object2json(localObject));
				localStringBuilder.append(",");
			}
			localStringBuilder.setCharAt(localStringBuilder.length() - 1, ']');
		} else {
			localStringBuilder.append("]");
		}
		return localStringBuilder.toString();
	}

	public static String string2json(String paramString) {
		if (null == paramString) {
			return "";
		}
		StringBuilder localStringBuilder = new StringBuilder();
		for (int i = 0; i < paramString.length(); i++) {
			char c = paramString.charAt(i);
			switch (c) {
			case '"':
				localStringBuilder.append("\\\"");
				break;
			case '\\':
				localStringBuilder.append("\\\\");
				break;
			case '\b':
				localStringBuilder.append("\\b");
				break;
			case '\f':
				localStringBuilder.append("\\f");
				break;
			case '\n':
				localStringBuilder.append("\\n");
				break;
			case '\r':
				localStringBuilder.append("\\r");
				break;
			case '\t':
				localStringBuilder.append("\\t");
				break;
			case '/':
				localStringBuilder.append("\\/");
				break;
			default:
				if ((c >= 0) && (c <= '\037')) {
					String str = Integer.toHexString(c);
					localStringBuilder.append("\\u");
					for (int j = 0; j < 4 - str.length(); j++) {
						localStringBuilder.append('0');
					}
					localStringBuilder.append(str.toUpperCase());
				} else {
					localStringBuilder.append(c);
				}
				break;
			}
		}
		return localStringBuilder.toString();
	}
}