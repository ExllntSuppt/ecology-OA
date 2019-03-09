package com.goldgrid;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class iSMSClient2000
{
  private Socket ClientSocket;
  private DataInputStream is;
  private DataOutputStream os;
  private String FError;
  
  public iSMSClient2000()
  {
    FError = "";
  }
  
  public boolean OpenSMS(String paramString, int paramInt) {
    String str = "";
    boolean bool = false;
    try {
      if (paramString == "") {
        paramString = "localhost";
      }
      if (paramInt == 0) {
        paramInt = 8090;
      }
      ClientSocket = new Socket(paramString, paramInt);
      is = new DataInputStream(new BufferedInputStream(ClientSocket.getInputStream()));
      os = new DataOutputStream(new BufferedOutputStream(ClientSocket.getOutputStream()));
      
      str = is.readLine();
      if (str.equalsIgnoreCase("OK")) {
        os.write("SMS\r\n".getBytes());
        bool = true;
      }
    }
    catch (Exception localException)
    {
      bool = false;
    }
    return bool;
  }
  
  public boolean SendSMS(String paramString1, String paramString2, String paramString3)
  {
    boolean bool = false;
    


    try
    {
      byte[] arrayOfByte1 = (paramString1 + "\r\n").getBytes();
      byte[] arrayOfByte2 = (paramString2 + "\r\n").getBytes();
      byte[] arrayOfByte3 = (paramString3 + "\r\n").getBytes();
      byte[] arrayOfByte4 = new byte[3];
      arrayOfByte4[0] = 26;
      arrayOfByte4[1] = 13;
      arrayOfByte4[2] = 10;
      
      os.write(arrayOfByte4);
      os.write(arrayOfByte1);
      os.write(arrayOfByte2);
      os.write(arrayOfByte3);
      os.write(arrayOfByte4);
      os.flush();
      
      FError = is.readLine();
      if (FError.equalsIgnoreCase("OK")) {
        bool = true;
      } else {
        bool = false;
      }
    }
    catch (Exception localException)
    {
      bool = false;
    }
    return bool;
  }
  
  public boolean CloseSMS()
  {
    boolean bool = false;
    try
    {
      os.write("END\r\n".getBytes());
      ClientSocket.close();
      bool = true;
    }
    catch (Exception localException)
    {
      bool = false;
    }
    return bool;
  }
  
  public String getError() { return FError; }
}