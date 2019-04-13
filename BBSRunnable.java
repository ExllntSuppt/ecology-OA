package weaver.bbs;

public class BBSRunnable implements Runnable
{
  public String userid;
  public String passwordnew;
  
  public BBSRunnable(String paramString1, String paramString2) {
    userid = paramString1;
    passwordnew = paramString2;
  }
  
  public void run() {
    UserOAToBBS localUserOAToBBS = new UserOAToBBS();
    localUserOAToBBS.changBBSUser(userid, passwordnew);
  }
}