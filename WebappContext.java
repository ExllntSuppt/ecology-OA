 
package com.converter.system;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import weaver.file.Prop;
import weaver.general.GCONST;
import weaver.general.Util;

public class WebappContext {
	private static final String KEY = WebappContext.class.getName();
	private final OfficeManager officeManager;
	private final OfficeDocumentConverter documentConverter;

	public WebappContext(ServletContext paramServletContext) {
		String str1 = paramServletContext.getRealPath("/");
		if (!str1.endsWith("" + File.separatorChar)) {
			str1 = str1 + File.separatorChar;
		}
		GCONST.setRootPath(str1);
		String str2 = str1 + "openoffice/";
		String str3 = Prop.getPropValue("convert", "office.port");
		String str4 = Prop.getPropValue("convert", "office.home");
		String str5 = Prop.getPropValue("convert", "office.thread");
		String str6 = Prop.getPropValue("convert", "office.portmin");
		String str7 = Prop.getPropValue("convert", "office.portmax");

		DefaultOfficeManagerConfiguration localDefaultOfficeManagerConfiguration = new DefaultOfficeManagerConfiguration();
		if ((str3 == null) ||

		(str4 != null)) {
			localDefaultOfficeManagerConfiguration
					.setOfficeHome(new File(str4));
		}
		if (str2 != null) {
			localDefaultOfficeManagerConfiguration
					.setTemplateProfileDir(new File(str2));
		}
		int i = Util.getIntValue(Util.null2String(str5), 5);
		int j = Util.getIntValue(Util.null2String(str6), 2001);
		int k = Util.getIntValue(Util.null2String(str7), 2010);
		List localList = getPort(i, j, k);
		int[] arrayOfInt = new int[localList.size()];
		for (int m = 0; m < localList.size(); m++) {
			arrayOfInt[m] = ((Integer) localList.get(m)).intValue();
		}
		localDefaultOfficeManagerConfiguration.setPortNumbers(arrayOfInt);

		this.officeManager = localDefaultOfficeManagerConfiguration
				.buildOfficeManager();
		this.documentConverter = new OfficeDocumentConverter(this.officeManager);
	}

	protected static void init(ServletContext paramServletContext) {
		WebappContext localWebappContext = new WebappContext(
				paramServletContext);
		paramServletContext.setAttribute(KEY, localWebappContext);
		localWebappContext.officeManager.start();
	}

	protected static void destroy(ServletContext paramServletContext) {
		WebappContext localWebappContext = get(paramServletContext);
		localWebappContext.officeManager.stop();
	}

	public static WebappContext get(ServletContext paramServletContext) {
		return (WebappContext) paramServletContext.getAttribute(KEY);
	}

	public OfficeManager getOfficeManager() {
		return this.officeManager;
	}

	public OfficeDocumentConverter getDocumentConverter() {
		return this.documentConverter;
	}

	private List getPort(int paramInt1, int paramInt2, int paramInt3) {
		ArrayList localArrayList = new ArrayList();
		int i = 0;
		DatagramSocket localDatagramSocket = null;
		for (; paramInt2 <= paramInt3; paramInt2++) {
			if (i == paramInt1) {
				break;
			}
			try {
				localDatagramSocket = new DatagramSocket(paramInt2,
						InetAddress.getLocalHost());
				localDatagramSocket.close();
				localArrayList.add(Integer.valueOf(paramInt2));
				i++;
			} catch (IOException localIOException) {
			}
		}
		return localArrayList;
	}
}