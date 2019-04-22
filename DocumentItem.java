package com.weaver.ecology.search.model;

import com.weaver.ecology.search.model.base.AbstractBaseBean;
import com.weaver.ecology.search.util.CommonUtils;
import java.util.Date;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.Field.TermVector;
import org.apache.lucene.document.NumberTools;

public class DocumentItem
  extends AbstractBaseBean
{
  private static final long serialVersionUID = -2671004821663909321L;
  public static final String FIELD_ID = "id";
  public static final String FIELD_TITLE = "title";
  public static final String FIELD_FIRST_DIRECTORY = "firstDirectory";
  public static final String FIELD_SECOND_DIRECTORY = "secondDirectory";
  public static final String FIELD_THIRD_DIRECTORY = "thirdDirectory";
  public static final String FIELD_CREATOR_ID = "creatorId";
  public static final String FIELD_CREATOR_NAME = "creatorName";
  public static final String FIELD_USER_TYPE = "userType";
  public static final String FIELD_DOC_SECURITY = "docSecurity";
  public static final String FIELD_DOC_TYPE = "docType";
  public static final String FIELD_CONTENTS = "contents";
  public static final String FIELD_DOC_SIZE = "docSize";
  public static final String FIELD_IS_ACCESSORIES = "isAccessories";
  public static final String FIELD_IS_REPLY = "isReply";
  public static final String FIELD_CREATE_DATE = "createDate";
  private int id;
  private String title;
  private String firstDirectory;
  private String secondDirectory;
  private String thirdDirectory;
  private int creatorId;
  private String createDate;
  private String userType;
  private String docSecurity;
  private int docType;
  private String contents;
  private int docSize;
  private boolean isAccessories;
  private boolean isReply;
  
  public DocumentItem() {}
  
  public boolean isReply()
  {
    return isReply;
  }
  
  public void setReply(boolean paramBoolean) {
    isReply = paramBoolean;
  }
  
  public String getContents() {
    return contents;
  }
  
  public void setContents(String paramString) {
    contents = paramString;
  }
  
  public String getDocSecurity() {
    return docSecurity;
  }
  
  public void setDocSecurity(String paramString) {
    docSecurity = paramString;
  }
  
  public int getDocSize() {
    return docSize;
  }
  
  public void setDocSize(int paramInt) {
    docSize = paramInt;
  }
  
  public int getDocType() {
    return docType;
  }
  
  public void setDocType(int paramInt) {
    docType = paramInt;
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int paramInt) {
    id = paramInt;
  }
  
  public String getSecondDirectory() {
    return secondDirectory;
  }
  
  public void setSecondDirectory(String paramString) {
    secondDirectory = paramString;
  }
  
  public String getThirdDirectory() {
    return thirdDirectory;
  }
  
  public void setThirdDirectory(String paramString) {
    thirdDirectory = paramString;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String paramString) {
    title = paramString;
  }
  
  public String getUserType() {
    return userType;
  }
  
  public void setUserType(String paramString) {
    userType = paramString;
  }
  
  public int getCreatorId() {
    return creatorId;
  }
  
  public void setCreatorId(int paramInt) {
    creatorId = paramInt;
  }
  
  public boolean isAccessories() {
    return isAccessories;
  }
  
  public void setAccessories(boolean paramBoolean) {
    isAccessories = paramBoolean;
  }
  



  public Document getIndexDoc()
  {
    Document localDocument = new Document();
    
    localDocument.add(new Field("id", NumberTools.longToString(id), Field.Store.YES, Field.Index.UN_TOKENIZED));
    
    localDocument.add(new Field("title", title, Field.Store.YES, Field.Index.NO));
    
    localDocument.add(new Field("firstDirectory", firstDirectory, Field.Store.YES, Field.Index.NO));
    
    localDocument.add(new Field("secondDirectory", secondDirectory, Field.Store.YES, Field.Index.NO));
    
    localDocument.add(new Field("thirdDirectory", thirdDirectory, Field.Store.YES, Field.Index.NO));
    
    localDocument.add(new Field("creatorId", NumberTools.longToString(creatorId), Field.Store.YES, Field.Index.NO));
    


    userType = ((userType == null) || (userType.equalsIgnoreCase("")) ? "1" : userType);
    localDocument.add(new Field("userType", userType, Field.Store.YES, Field.Index.NO));
    
    Date localDate = CommonUtils.getString2Date(createDate);
    localDate.setTime(localDate.getTime() + 86400000L);
    localDocument.add(new Field("createDate", DateTools.dateToString(localDate, DateTools.Resolution.DAY), Field.Store.YES, Field.Index.NO));
    
    localDocument.add(new Field("docSecurity", docSecurity, Field.Store.YES, Field.Index.NO));
    
    localDocument.add(new Field("docType", NumberTools.longToString(docType), Field.Store.YES, Field.Index.NO));
    
    localDocument.add(new Field("contents", title + contents, Field.Store.COMPRESS, Field.Index.TOKENIZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
    

    localDocument.add(new Field("docSize", NumberTools.longToString(docSize), Field.Store.YES, Field.Index.NO));
    
    localDocument.add(new Field("isAccessories", isAccessories() ? "1" : "0", Field.Store.YES, Field.Index.NO));
    
    localDocument.add(new Field("isReply", isReply() ? "1" : "0", Field.Store.YES, Field.Index.NO));
    
    return localDocument;
  }
  
  public String getFirstDirectory() {
    return firstDirectory;
  }
  
  public void setFirstDirectory(String paramString) {
    firstDirectory = paramString;
  }
  







  public String getCreateDate()
  {
    return createDate;
  }
  
  public void setCreateDate(String paramString) {
    createDate = paramString;
  }
}