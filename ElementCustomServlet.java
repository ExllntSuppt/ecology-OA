package weaver.admincenter.homepage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import weaver.admincenter.file.UploadFile;
import weaver.conn.RecordSet;
import weaver.filter.XssUtil;
import weaver.general.StaticObj;
import weaver.general.Util;
import weaver.homepage.cominfo.HomepageElementFieldCominfo;
import wscheck.ZipUtils;

public class ElementCustomServlet extends HttpServlet {
	private Map<String, String> kv = null;
	private String customPath = "page/elementCustom/temp/";
	private StaticObj staticObj = StaticObj.getInstance();

	public ElementCustomServlet() {
	}

	protected void doGet(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		doPost(paramHttpServletRequest, paramHttpServletResponse);
	}

	protected void doPost(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) {
		try {
			paramHttpServletRequest.setCharacterEncoding("UTF-8");
			String str1 = Util.null2String(paramHttpServletRequest
					.getParameter("operate"));
			if ("edit".equals(str1)) {
				String str2 = editElementTemplate(paramHttpServletRequest,
						paramHttpServletResponse);
				paramHttpServletResponse
						.sendRedirect("/admincenter/portalEngine/ElementCustomEdit.jsp?colseDialog=close&msg="
								+ str2
								+ "&ebaseid="
								+ Util.null2String(paramHttpServletRequest
										.getParameter("e_id")));
				return;
			}
			kv = uploadFile(paramHttpServletRequest);
			if ("false".equals(kv.get("exists"))) {
				createElementTemplate(paramHttpServletRequest,
						paramHttpServletResponse);
			} else {
				paramHttpServletResponse
						.sendRedirect("/admincenter/portalEngine/ElementCustom.jsp?msg=1");
			}
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
	}

	private String editElementTemplate(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws IOException {
		String str1 = Util.null2String(paramHttpServletRequest
				.getParameter("e_id"));
		String str2 = "page/elementCustom/" + str1 + "/conf.xml";
		RecordSet localRecordSet = new RecordSet();
		try {
			File localFile = new File(getServletContext().getRealPath("/")
					+ str2);
			if (!localFile.exists())
				return "1";
			XMLConfiguration localXMLConfiguration = new XMLConfiguration(
					localFile);
			localXMLConfiguration.setEncoding("UTF-8");
			String str3 = Util.null2String(paramHttpServletRequest
					.getParameter("e_title"));
			String str4 = Util.null2String(paramHttpServletRequest
					.getParameter("e_titleEN"));
			String str5 = Util.null2String(paramHttpServletRequest
					.getParameter("e_titleTHK"));
			String str6 = Util.null2String(paramHttpServletRequest
					.getParameter("e_desc"));
			String str7 = Util.null2String(paramHttpServletRequest
					.getParameter("e_linkMode"));
			String str8 = Util.null2String(paramHttpServletRequest
					.getParameter("e_loginview"));
			localXMLConfiguration.setProperty("title", str3);
			localXMLConfiguration.setProperty("titleEN", str4);
			localXMLConfiguration.setProperty("desc", str6);
			localXMLConfiguration.setProperty("linkMode", str7);
			localXMLConfiguration.setProperty("loginview", str8);

			localXMLConfiguration.setProperty("private.jsList", " ");
			localXMLConfiguration.setProperty("public.jsIdList", " ");
			localXMLConfiguration.setProperty("public.cssIdList", " ");
			localXMLConfiguration.setProperty("view.url", " ");
			localXMLConfiguration.setProperty("view.method", " ");

			localXMLConfiguration.setProperty("setting.url", " ");
			localXMLConfiguration.setProperty("setting.method", " ");
			localXMLConfiguration.setProperty("more.url", " ");
			localXMLConfiguration.setProperty("more.method", " ");
			localXMLConfiguration.setProperty("operation", " ");

			localRecordSet.executeSql("UPDATE hpBaseElement set title='" + str3
					+ "',titleEN='" + str4 + "',titleTHK='" + str5 + "',"
					+ "elementdesc='" + str6 + "',loginview=" + str8
					+ ",linkMode=" + str7 + " where id='" + str1 + "'");

			int i = Util.getIntValue(paramHttpServletRequest
					.getParameter("showrownum"));
			int k;
			if (i > 0) {
				localXMLConfiguration.clearTree("displayfield.item");
				localRecordSet
						.executeSql("DELETE from hpFieldElement where elementid='"
								+ str1 + "'");
				localRecordSet.executeSql("select max(id) from hpFieldElement");
				int j = 100;
				if (localRecordSet.next())
					j = localRecordSet.getInt(1);
				for (k = 0; k < i; k++) {
					j++;
					String localObject = Util.null2String(paramHttpServletRequest
							.getParameter("showdatalength_" + k));

					localRecordSet
							.executeSql("INSERT INTO hpFieldElement( id,elementid,fieldname,fieldColumn,isDate,transMethod,fieldwidth,linkurl,valuecolumn,isLimitLength,ordernum) VALUES ( "
									+ j
									+ ",'"
									+ str1
									+ "','"
									+ Util.null2String(paramHttpServletRequest
											.getParameter(new StringBuilder()
													.append("showtitle_")
													.append(k).toString()))
									+ "','"
									+ Util.null2String(paramHttpServletRequest
											.getParameter(new StringBuilder()
													.append("showname_")
													.append(k).toString()))
									+ "','0','','*','','','"
									+ Util.null2String(paramHttpServletRequest
											.getParameter(new StringBuilder()
													.append("showdatatype_")
													.append(k).toString()))
									+ "',"
									+ ("".equals(localObject) ? Integer
											.valueOf(k + 1) : localObject)
									+ ")");

					localXMLConfiguration.addProperty("displayfield.item(" + k
							+ ").showtitle", Util
							.null2String(paramHttpServletRequest
									.getParameter("showtitle_" + k)));
					localXMLConfiguration.addProperty("displayfield.item(" + k
							+ ").showtype", Util
							.null2String(paramHttpServletRequest
									.getParameter("showtype_" + k)));
					localXMLConfiguration.addProperty("displayfield.item(" + k
							+ ").showname", Util
							.null2String(paramHttpServletRequest
									.getParameter("showname_" + k)));
					localXMLConfiguration.addProperty("displayfield.item(" + k
							+ ").showdatatype", Util
							.null2String(paramHttpServletRequest
									.getParameter("showdatatype_" + k)));
					localXMLConfiguration.addProperty("displayfield.item(" + k
							+ ").showdatalength",
							"".equals(localObject) ? Integer.valueOf(k + 1)
									: localObject);
				}
			}
			int j = Util.getIntValue(paramHttpServletRequest
					.getParameter("settingrownum"));
			if (j > 0) {
				for (k = 0; k < j; k++) {
					localXMLConfiguration.setProperty("settingfield.item(" + k
							+ ").settingtitle", Util
							.null2String(paramHttpServletRequest
									.getParameter("settingtitle_" + k)));
					localXMLConfiguration.setProperty("settingfield.item(" + k
							+ ").settingtype", Util
							.null2String(paramHttpServletRequest
									.getParameter("settingtype_" + k)));
					localXMLConfiguration.setProperty("settingfield.item(" + k
							+ ").settingname", Util
							.null2String(paramHttpServletRequest
									.getParameter("settingname_" + k)));
					localXMLConfiguration.setProperty("settingfield.item(" + k
							+ ").settingdatatype", Util
							.null2String(paramHttpServletRequest
									.getParameter("settingdatatype_" + k)));
					localXMLConfiguration.setProperty("settingfield.item(" + k
							+ ").settingdatalength", Util
							.null2String(paramHttpServletRequest
									.getParameter("settingdatalength_" + k)));
				}
			}
			localXMLConfiguration.save();
			WeaverBaseElementCominfo localWeaverBaseElementCominfo = new WeaverBaseElementCominfo();
			localWeaverBaseElementCominfo.updateBaseElementCache(str1);
			Object localObject = new HomepageElementFieldCominfo();
			((HomepageElementFieldCominfo) localObject).reloadHpCache();
			staticObj.removeObject("ElementCustomCominfo");
		} catch (Exception localException) {
			localException.printStackTrace();
			return "1";
		}
		return "";
	}

	private String createElementTemplate(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws IOException {
		String str1 = "";
		String str2 = Util.null2String((String) kv.get("e_id"));
		String str3 = Util.null2String((String) kv.get("e_icon_imgpath"));
		if (!"".equals(str3)) {
			kv.put("e_icon",
					"resource/image" + str3.substring(str3.lastIndexOf("/")));
		} else if ("".equals(Util.null2String((String) kv.get("e_icon")))) {
			kv.put("e_icon", "resource/image/default_wev8.gif");
		}
		StringBuffer localStringBuffer = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		localStringBuffer.append("\n<root>");
		localStringBuffer.append("\n\t<id>").append(str2).append("</id>");
		localStringBuffer.append("\n\t<title>")
				.append(Util.null2String((String) kv.get("e_title")))
				.append("</title>");
		localStringBuffer.append("\n\t<titleEN>")
				.append(Util.null2String((String) kv.get("e_titleEN")))
				.append("</titleEN>");
		localStringBuffer.append("\n\t<titleTHK>")
				.append(Util.null2String((String) kv.get("e_titleTHK")))
				.append("</titleTHK>");
		localStringBuffer.append("\n\t<desc>")
				.append(Util.null2String((String) kv.get("e_desc")))
				.append("</desc>");
		localStringBuffer.append("\n\t<icon>")
				.append(Util.null2String((String) kv.get("e_icon")))
				.append("</icon>");
		localStringBuffer.append("\n\t<linkMode>")
				.append(Util.null2String((String) kv.get("e_linkMode")))
				.append("</linkMode>");
		localStringBuffer.append("\n\t<loginview>")
				.append(Util.null2String((String) kv.get("e_loginview")))
				.append("</loginview>");
		localStringBuffer.append("\n\t<type>")
				.append(Util.null2String((String) kv.get("e_type")))
				.append("</type>");
		localStringBuffer.append("\n\t<perpage>")
				.append(Util.null2String((String) kv.get("e_perpage")))
				.append("</perpage>");
		localStringBuffer.append("\n\t<private>");
		localStringBuffer.append("\n\t\t<jsList>")
				.append(Util.null2String((String) kv.get("e_privateJSList")))
				.append("</jsList>");
		localStringBuffer.append("\n\t</private>");
		localStringBuffer.append("\n\t<public>");
		localStringBuffer.append("\n\t\t<jsIdList>")
				.append(Util.null2String((String) kv.get("e_publicJSList")))
				.append("</jsIdList>");
		localStringBuffer.append("\n\t\t<cssIdList>")
				.append(Util.null2String((String) kv.get("e_publicCSSList")))
				.append("</cssIdList>");
		localStringBuffer.append("\n\t</public>");
		localStringBuffer.append("\n\t<view>");
		localStringBuffer.append("\n\t\t<url>")
				.append(Util.null2String((String) kv.get("e_viewUrl")))
				.append("</url>");
		localStringBuffer.append("\n\t\t<method>")
				.append(Util.null2String((String) kv.get("e_viewMethod")))
				.append("</method>");
		localStringBuffer.append("\n\t</view>");
		localStringBuffer.append("\n\t<setting>");
		localStringBuffer.append("\n\t\t<url>")
				.append(Util.null2String((String) kv.get("e_settingUrl")))
				.append("</url>");
		localStringBuffer.append("\n\t\t<method>")
				.append(Util.null2String((String) kv.get("e_settingMethod")))
				.append("</method>");
		localStringBuffer.append("\n\t</setting>");
		localStringBuffer.append("\n\t<more>");
		localStringBuffer.append("\n\t\t<url>")
				.append(Util.null2String((String) kv.get("e_moreUrl")))
				.append("</url>");
		localStringBuffer.append("\n\t\t<method>")
				.append(Util.null2String((String) kv.get("e_moreMethod")))
				.append("</method>");
		localStringBuffer.append("\n\t</more>");
		localStringBuffer.append("\n\t<operation>")
				.append(Util.null2String((String) kv.get("e_operation")))
				.append("</operation>");
		localStringBuffer.append("\n\t<displayfield>");
		int i = Util.getIntValue((String) kv.get("showrownum"));
		for (int j = 0; j < i; j++) {
			localStringBuffer.append("\n\t\t<item>");
			localStringBuffer
					.append("\n\t\t\t<showtitle>")
					.append(Util.null2String((String) kv.get("showtitle_" + j)))
					.append("</showtitle>");
			localStringBuffer.append("\n\t\t\t<showtype>")
					.append(Util.null2String((String) kv.get("showtype_" + j)))
					.append("</showtype>");
			localStringBuffer.append("\n\t\t\t<showname>")
					.append(Util.null2String((String) kv.get("showname_" + j)))
					.append("</showname>");
			localStringBuffer
					.append("\n\t\t\t<showdatatype>")
					.append(Util.null2String((String) kv.get("showdatatype_"
							+ j))).append("</showdatatype>");
			localStringBuffer
					.append("\n\t\t\t<showdatalength>")
					.append(Util.null2String((String) kv.get("showdatalength_"
							+ j))).append("</showdatalength>");
			localStringBuffer.append("\n\t\t</item>");
		}
		localStringBuffer.append("\n\t</displayfield>");
		localStringBuffer.append("\n\t<settingfield>");
		int j = Util.getIntValue((String) kv.get("settingrownum"));
		for (int k = 0; k < j; k++) {
			localStringBuffer.append("\n\t\t<item>");
			localStringBuffer
					.append("\n\t\t\t<settingtitle>")
					.append(Util.null2String((String) kv.get("settingtitle_"
							+ k))).append("</settingtitle>");
			localStringBuffer
					.append("\n\t\t\t<settingtype>")
					.append(Util.null2String((String) kv
							.get("settingtype_" + k))).append("</settingtype>");
			localStringBuffer
					.append("\n\t\t\t<settingname>")
					.append(Util.null2String((String) kv
							.get("settingname_" + k))).append("</settingname>");
			localStringBuffer
					.append("\n\t\t\t<settingdatatype>")
					.append(Util.null2String((String) kv.get("settingdatatype_"
							+ k))).append("</settingdatatype>");
			localStringBuffer
					.append("\n\t\t\t<settingdatalength>")
					.append(Util.null2String((String) kv
							.get("settingdatalength_" + k)))
					.append("</settingdatalength>");
			localStringBuffer.append("\n\t\t</item>");
		}
		localStringBuffer.append("\n\t</settingfield>");
		localStringBuffer.append("\n</root>");
		String str4 = getServletContext().getRealPath("/") + customPath;
		String str5 = str4 + str2;

		File localFile = new File(str5);
		if (!localFile.exists()) {
			localFile.mkdir();
		}
		PrintWriter localPrintWriter = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(new File(str5
						+ "/conf.xml")), "UTF-8")));

		localPrintWriter.write(localStringBuffer.toString());
		localPrintWriter.flush();
		localPrintWriter.close();

		copyDirectiory(str4 + "resource", str5 + "/resource");
		if (!"".equals(str3)) {
			File localObject1 = new File(getServletContext().getRealPath("/")
					+ (String) kv.get("e_icon_imgpath"));
			File localObject2 = new File(getServletContext().getRealPath("/")
					+ customPath + "/" + str2 + "/resource/image/"
					+ ((File) localObject1).getName());
			copyFile((File) localObject1, (File) localObject2);
		}
		ZipUtils.execute(str5, str5 + ".zip");

		localFile = new File(str5 + ".zip");

		paramHttpServletResponse.setHeader("Content-Disposition",
				"attachment;filename=" + str2 + ".zip");
		paramHttpServletResponse.setContentType("application/x-msdownload");
		paramHttpServletResponse.setContentLength((int) localFile.length());

		Object localObject1 = new FileInputStream(localFile);
		Object localObject2 = new BufferedInputStream(
				(InputStream) localObject1);
		byte[] arrayOfByte = new byte['ࠀ'];
		long l = 0L;
		ServletOutputStream localServletOutputStream = paramHttpServletResponse
				.getOutputStream();
		while (l < localFile.length()) {
			int m = ((BufferedInputStream) localObject2).read(arrayOfByte, 0,
					2048);
			l += m;
			localServletOutputStream.write(arrayOfByte, 0, m);
		}
		localServletOutputStream.flush();
		localServletOutputStream.close();

		return str1;
	}

	public void copyFile(File paramFile1, File paramFile2) throws IOException {
		BufferedInputStream localBufferedInputStream = null;
		BufferedOutputStream localBufferedOutputStream = null;
		try {
			localBufferedInputStream = new BufferedInputStream(
					new FileInputStream(paramFile1));

			localBufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(paramFile2));

			byte[] arrayOfByte = new byte['᐀'];
			int i;
			while ((i = localBufferedInputStream.read(arrayOfByte)) != -1) {
				localBufferedOutputStream.write(arrayOfByte, 0, i);
			}

			localBufferedOutputStream.flush();
		} finally {
			if (localBufferedInputStream != null)
				localBufferedInputStream.close();
			if (localBufferedOutputStream != null) {
				localBufferedOutputStream.close();
			}
		}
	}

	public void copyDirectiory(String paramString1, String paramString2)
			throws IOException {
		new File(paramString2).mkdirs();

		File[] arrayOfFile = new File(paramString1).listFiles();

		for (int i = 0; i < arrayOfFile.length; i++) {
			Object localObject1;
			Object localObject2;
			if (arrayOfFile[i].isFile()) {
				localObject1 = arrayOfFile[i];

				localObject2 = new File(
						new File(paramString2).getAbsolutePath()
								+ File.separator + arrayOfFile[i].getName());

				copyFile((File) localObject1, (File) localObject2);
			}

			if (arrayOfFile[i].isDirectory()) {
				localObject1 = paramString1 + "/" + arrayOfFile[i].getName();

				localObject2 = paramString2 + "/" + arrayOfFile[i].getName();

				copyDirectiory((String) localObject1, (String) localObject2);
			}
		}
	}

	private boolean validateFileExt(String paramString) {
		if (paramString == null)
			return false;
		if (paramString.indexOf(".") != paramString.lastIndexOf(".")) {
			return false;
		}
		String[] arrayOfString = { ".jpg", ".jpeg", ".gif", ".ico", ".bmp",
				".png", ".js", ".css" };
		if ((paramString != null) && (arrayOfString != null)) {
			for (int i = 0; i < arrayOfString.length; i++) {
				if (paramString.toLowerCase().endsWith(
						arrayOfString[i].toLowerCase())) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public Map uploadFile(HttpServletRequest paramHttpServletRequest) {
		kv = new HashMap();
		DiskFileUpload localDiskFileUpload = new DiskFileUpload();
		RecordSet localRecordSet = new RecordSet();
		String str1 = "";
		String str2 = "";
		try {
			List localList = localDiskFileUpload
					.parseRequest(paramHttpServletRequest);
			UploadFile localUploadFile = new UploadFile();
			Iterator localIterator;
			FileItem localFileItem;
			for (localIterator = localList.iterator(); localIterator.hasNext();) {
				localFileItem = (FileItem) localIterator.next();
				if (localFileItem.isFormField()) {
					kv.put(localFileItem.getFieldName(),
							localFileItem.getString());
					if ("e_id".equals(localFileItem.getFieldName())) {
						str1 = localFileItem.getString();
					}
				}
			}
			if (!"".equals(str1)) {
				localRecordSet
						.executeSql("select id from hpBaseElement where id = '"
								+ Util.null2String(str1) + "'");
				if (!localRecordSet.next())
					kv.put("exists", "false");
				else
					return kv;
			}
			for (localIterator = localList.iterator(); localIterator.hasNext();) {
				localFileItem = (FileItem) localIterator.next();
				if (!localFileItem.isFormField()) {
					String str3 = localFileItem.getName();
					if (!"".equals(str3)) {
						if ("e_icon".equals(localFileItem.getFieldName()))
							str2 = customPath + str1 + "/resource/image";
						if ("e_privateJSList".equals(localFileItem
								.getFieldName())) {
							str2 = customPath + str1 + "/resource/js";
						}
						File localFile1 = new File(str3);

						File localFile2 = new File(getServletContext()
								.getRealPath(str2), localFile1.getName());
						str3 = str3.replaceAll("%00", "").replaceAll("%", "");
						if (!validateFileExt(str3)) {
							new XssUtil().writeLog(str3 + " is not valid!",
									true);
						} else {
							boolean bool = localUploadFile.upload(
									localFileItem, localFile2);
							if (bool)
								kv.put(localFileItem.getFieldName(),
										str2.substring(str2
												.lastIndexOf("resource"))
												+ "/"
												+ localFile1.getName());
						}
					}
				}
			}
		} catch (Exception localException) {
			UploadFile localUploadFile;
			Iterator localIterator;
			FileItem localFileItem;
			localException.printStackTrace();
		}
		return kv;
	}
}
