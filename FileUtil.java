package com.converter.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;

public class FileUtil
{
  public FileUtil() {}
  
  public static String getFilePrefix(String paramString)
  {
    int i = paramString.lastIndexOf(".");
    return paramString.substring(0, i);
  }
  
  public static String getFileSufix(String paramString) {
    int i = paramString.lastIndexOf(".");
    return paramString.substring(i + 1);
  }
  
  public static void copyFile(String paramString1, String paramString2) throws java.io.FileNotFoundException {
    File localFile1 = new File(paramString1);
    File localFile2 = new File(paramString2);
    FileInputStream localFileInputStream = new FileInputStream(localFile1);
    FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
    int i = 0;
    try {
      while ((i = localFileInputStream.read()) != -1)
        localFileOutputStream.write(i);
      return;
    } catch (IOException localIOException2) {
      localIOException2.printStackTrace();
    } finally {
      try {
        localFileInputStream.close();
        localFileOutputStream.close();
      } catch (IOException localIOException4) {
        localIOException4.printStackTrace();
      }
    }
  }
  
  public static boolean deleteDir(File paramFile)
  {
    if (paramFile.isDirectory()) {
      String[] arrayOfString = paramFile.list();
      for (int i = 0; i < arrayOfString.length; i++) {
        boolean bool = deleteDir(new File(paramFile, arrayOfString[i]));
        if (!bool) {
          return false;
        }
      }
    }
    
    return paramFile.delete();
  }
  

  public static String getSHA1(String paramString)
    throws OutOfMemoryError, IOException
  {
    File localFile = new File(paramString);
    FileInputStream localFileInputStream = new FileInputStream(localFile);
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      
      byte[] arrayOfByte = new byte[10485760];
      int i = 0;
      
      while ((i = localFileInputStream.read(arrayOfByte)) > 0)
      {
        localMessageDigest.update(arrayOfByte, 0, i);
      }
      


      SHA1Util localSHA1Util = new SHA1Util();
      return localSHA1Util.getDigestOfString(localMessageDigest.digest());
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
      localNoSuchAlgorithmException.printStackTrace();
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      throw localOutOfMemoryError;
    } finally {
      localFileInputStream.close();
    }
    return null;
  }
  
  public static int getPdfPage(String paramString) {
    int i = 0;
    try {
      PDDocument localPDDocument = PDDocument.load(paramString);
      List localList = localPDDocument.getDocumentCatalog().getAllPages();
      i = localList.size();
    }
    catch (Exception localException) {
      localException.printStackTrace();
    }
    return i;
  }
  
  public static void main(String[] paramArrayOfString) {}
}