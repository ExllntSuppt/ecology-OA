 
package weaver.admincenter.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import weaver.general.GCONST;

public class FileUtil {
	private static String codeModel = null;
	public static String filePath = null;

	public FileUtil() {
	}

	public static boolean dispatch(String paramString1, String paramString2) {
		return dispatch(paramString1, paramString2, null, "80");
	}

	public static boolean dispatch(String paramString1, String paramString2,
			String paramString3) {
		return dispatch(paramString1, paramString2, paramString3, "80");
	}

	public static boolean dispatch(String paramString1, String paramString2,
			String paramString3, String paramString4) {
		return false;
	}

	public static boolean deploy(String paramString1, String paramString2,
			String paramString3) {
		try {
			return copyFile(new File(paramString2 + "/" + paramString1
					+ ".class"), new File(paramString3 + "/" + paramString1
					+ ".class"));
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return false;
	}

	public static boolean copyFile(File paramFile1, File paramFile2) {
		BufferedInputStream localBufferedInputStream = null;
		BufferedOutputStream localBufferedOutputStream = null;
		try {
			localBufferedInputStream = new BufferedInputStream(
					new FileInputStream(paramFile1));

			localBufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(paramFile2));

			byte[] arrayOfByte = new byte['?'];
			while (( localBufferedInputStream.read(arrayOfByte)) != -1) {
				int i = localBufferedInputStream.read(arrayOfByte);
				localBufferedOutputStream.write(arrayOfByte, 0, i);
			}
			localBufferedOutputStream.flush();
			return true;
		} catch (Exception localException1) {
			int i;
			return false;
		} finally {
			if (localBufferedInputStream != null) {
				try {
					localBufferedInputStream.close();
				} catch (Exception localException6) {
				}
			}
			if (localBufferedOutputStream != null) {
				try {
					localBufferedOutputStream.close();
				} catch (Exception localException7) {
				}
			}
		}
	}

	public static boolean createCode(String paramString) {
		if (codeModel == null) {
			boolean bool = loadBaseAction();
			if (!bool) {
				return false;
			}
		}
		String str = codeModel.replaceAll("BaseAction", paramString);
		try {
			BufferedWriter localBufferedWriter = new BufferedWriter(
					new FileWriter(new File(filePath + "/" + paramString
							+ ".java")));
			localBufferedWriter.write(str);
			localBufferedWriter.close();
			return true;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return false;
	}

	public static boolean addOrUpdateXml(String paramString1,
			String paramString2) {
		try {
			XMLWriter localXMLWriter = null;
			SAXReader localSAXReader = new SAXReader();
			File localFile = new File(paramString2
					+ "WEB-INF/service/action.xml");
			OutputFormat localOutputFormat = OutputFormat.createPrettyPrint();
			localOutputFormat.setEncoding(GCONST.XML_UTF8);
			Document localDocument;
			Element localElement1;
			Object localObject;
			Element localElement2;
			Element localElement3;
			if (localFile.exists()) {
				localDocument = localSAXReader.read(localFile);
				localElement1 = localDocument.getRootElement();
				for (localObject = localElement1
						.elementIterator("service-point"); ((Iterator) localObject)
						.hasNext();) {
					localElement2 = (Element) ((Iterator) localObject).next();
					if (localElement2.attributeValue("id").equals(paramString1)) {
						return true;
					}
				}
				localObject = localElement1.addElement("service-point");
				((Element) localObject).addAttribute("id", paramString1);
				((Element) localObject).addAttribute("interface",
						"weaver.interfaces.workflow.action.Action");
				localElement2 = ((Element) localObject)
						.addElement("invoke-factory");
				localElement3 = localElement2.addElement("construct");
				localElement3.addAttribute("class",
						"weaver.interfaces.workflow.action." + paramString1);

				localXMLWriter = new XMLWriter(new FileWriter(localFile),
						localOutputFormat);
				localXMLWriter.write(localDocument);
				localXMLWriter.close();
			} else {
				localDocument = DocumentHelper.createDocument();
				localElement1 = localDocument.addElement("module");
				localElement1.addAttribute("id", "action");
				localElement1.addAttribute("version", "1.0.0");
				localObject = localElement1.addElement("service-point");
				((Element) localObject).addAttribute("id", paramString1);
				((Element) localObject).addAttribute("interface",
						"weaver.interfaces.workflow.action.Action");
				localElement2 = ((Element) localObject)
						.addElement("invoke-factory");
				localElement3 = localElement2.addElement("construct");
				localElement3.addAttribute("class",
						"weaver.interfaces.workflow.action." + paramString1);

				localXMLWriter = new XMLWriter(new FileWriter(localFile),
						localOutputFormat);
				localXMLWriter.write(localDocument);
				localXMLWriter.close();
			}
		} catch (Exception localException) {
			localException.printStackTrace();
			return false;
		}
		return true;
	}

	private static boolean loadBaseAction() {
		try {
			String str1 = "UTF-8";
			codeModel = "";
			File localFile = new File(filePath + "/BaseAction.java");
			if ((localFile.isFile()) && (localFile.exists())) {
				InputStreamReader localInputStreamReader = new InputStreamReader(
						new FileInputStream(localFile), str1);

				BufferedReader localBufferedReader = new BufferedReader(
						localInputStreamReader);
				String str2 = null;
				while ((str2 = localBufferedReader.readLine()) != null) {
					codeModel = codeModel + "\n" + str2;
				}
				localInputStreamReader.close();
				return true;
			}
			return false;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return false;
	}
}