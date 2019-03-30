package weaver.blog;

import weaver.general.Util;














public class SSO
{
  private String api_secret;
  private String api_key;
  private long auth_timestamp;
  
  public SSO()
  {
    api_secret = "eYou185HH";
    api_key = "api@citicpacific.com.cn";
    auth_timestamp = getTimestamp();
  }
  







  public String getSSOURL(String paramString1, String paramString2, String paramString3)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    
    localStringBuffer.append("http://" + paramString1 + "/api/sso/login?");
    
    localStringBuffer.append("auth_type=simple");
    localStringBuffer.append("&auth_key=api%40citicpacific.com.cn");
    localStringBuffer.append("&auth_timestamp=" + auth_timestamp);
    localStringBuffer.append("&email=" + getEmail(paramString2, paramString3));
    localStringBuffer.append("&auth_signature=" + getAuth_signature(paramString2, paramString3));
    return localStringBuffer.toString();
  }
  




  private long getTimestamp()
  {
    long l = System.currentTimeMillis();
    l /= 1000L;
    return l;
  }
  






  private String getEmail(String paramString1, String paramString2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramString1);
    localStringBuffer.append("@");
    localStringBuffer.append(paramString2);
    return localStringBuffer.toString();
  }
  






  private String getAuth_signature(String paramString1, String paramString2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    

    localStringBuffer.append(getEncrypt(api_secret));
    localStringBuffer.append(api_key);
    localStringBuffer.append(auth_timestamp);
    
    localStringBuffer.append(getEmail(paramString1, paramString2));
    


    return getEncrypt(localStringBuffer.toString());
  }
  
  private String getEncrypt(String paramString) {
    return Util.getEncrypt(paramString).toLowerCase();
  }
  
  public static void main(String[] paramArrayOfString)
  {
    SSO localSSO = new SSO();
  }
}