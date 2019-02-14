/*
 *  
 */
package DBstep;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class iMsgServer2000_iWebRevision {
    private String FError = "";
    private int FFileSize = 0;
    private byte[] FMsgFile;
    private String FMsgText = "";
    private byte[] FStream;
    private String FVersion = "DBSTEP V3.0";
    private String TableBase64 = "FxcYg3UZvtEz50Na8G476=mLDI/jVfC9dsoMAiBhJSu2qPKe+QRbXry1TnkWHlOpw";
    private String VERSION = "DBSTEP V3.0";

    public String DecodeBase64(String Value) {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        byte[] d = new byte[4];
        try {
            int count = 0;
            byte[] x = Value.getBytes();
            while (count < x.length) {
                int n = 0;
                while (n <= 3) {
                    if (count >= x.length) {
                        d[n] = 64;
                    } else {
                        int y = this.TableBase64.indexOf(x[count]);
                        if (y < 0) {
                            y = 65;
                        }
                        d[n] = (byte)y;
                    }
                    ++count;
                    ++n;
                }
                o.write((byte)(((d[0] & 63) << 2) + ((d[1] & 48) >> 4)));
                if (d[2] == 64) continue;
                o.write((byte)(((d[1] & 15) << 4) + ((d[2] & 60) >> 2)));
                if (d[3] == 64) continue;
                o.write((byte)(((d[2] & 3) << 6) + (d[3] & 63)));
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            this.FError = String.valueOf(this.FError) + String.valueOf(e.toString());
            System.out.println(e.toString());
        }
        return o.toString();
    }

    public String EncodeBase64(String Value) {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        byte[] d = new byte[4];
        try {
            int count = 0;
            byte[] x = Value.getBytes();
            while (count < x.length) {
                byte c = x[count];
                d[0] = (byte)((c & 252) >> 2);
                d[1] = (byte)((c & 3) << 4);
                if (++count < x.length) {
                    c = x[count];
                    d[1] = (byte)(d[1] + (byte)((c & 240) >> 4));
                    d[2] = (byte)((c & 15) << 2);
                    if (++count < x.length) {
                        c = x[count];
                        ++count;
                        d[2] = (byte)(d[2] + ((c & 192) >> 6));
                        d[3] = (byte)(c & 63);
                    } else {
                        d[3] = 64;
                    }
                } else {
                    d[2] = 64;
                    d[3] = 64;
                }
                int n = 0;
                while (n <= 3) {
                    o.write(this.TableBase64.charAt(d[n]));
                    ++n;
                }
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            this.FError = String.valueOf(this.FError) + String.valueOf(e.toString());
            System.out.println(e.toString());
        }
        return o.toString();
    }

    protected String FormatHead(String vString) {
        if (vString.length() > 16) {
            return vString.substring(0, 16);
        }
        int i = vString.length() + 1;
        while (i < 17) {
            vString = vString.concat(" ");
            ++i;
        }
        return vString;
    }

    public int GetFieldCount() {
        int i = 0;
        int j = 0;
        i = this.FMsgText.indexOf("\r\n", i + 1);
        while (i != -1) {
            ++j;
            i = this.FMsgText.indexOf("\r\n", i + 1);
        }
        return j;
    }

    public String GetFieldName(int Index) {
        int i = 0;
        int j = 0;
        int k = 0;
        int n = 0;
        String mFieldString = "";
        String mFieldName = "";
        String mReturn = "";
        while (i != -1 && j < Index) {
            if ((i = this.FMsgText.indexOf("\r\n", i + 1)) == -1) continue;
            ++j;
        }
        k = this.FMsgText.indexOf("\r\n", i + 1);
        if (i != -1 && k != -1) {
            mFieldString = i == 0 ? this.FMsgText.substring(i, k) : this.FMsgText.substring(i + 2, k);
            n = mFieldString.indexOf("=", 0);
            if (n != -1) {
                mReturn = mFieldName = mFieldString.substring(0, n);
            }
        }
        return mReturn;
    }

    public String GetFieldText() {
        return this.FMsgText.toString();
    }

    public String GetFieldValue(int Index) {
        int i = 0;
        int j = 0;
        int k = 0;
        int n = 0;
        String mFieldString = "";
        String mFieldValue = "";
        String mReturn = "";
        while (i != -1 && j < Index) {
            if ((i = this.FMsgText.indexOf("\r\n", i + 1)) == -1) continue;
            ++j;
        }
        k = this.FMsgText.indexOf("\r\n", i + 1);
        if (i != -1 && k != -1) {
            mFieldString = i == 0 ? this.FMsgText.substring(i, k) : this.FMsgText.substring(i + 2, k);
            n = mFieldString.indexOf("=", 0);
            if (n != -1) {
                mFieldValue = mFieldString.substring(n + 1, mFieldString.length());
                mReturn = this.DecodeBase64(mFieldValue);
            }
        }
        return mReturn;
    }

    public String GetMsgByName(String FieldName) {
        int i = 0;
        int j = 0;
        String mReturn = "";
        String mFieldName = FieldName.trim().concat("=");
        i = this.FMsgText.indexOf(mFieldName);
        if (i != -1) {
            j = this.FMsgText.indexOf("\r\n", i + 1);
            i += mFieldName.length();
            if (j != -1) {
                String mFieldValue = this.FMsgText.substring(i, j);
                mReturn = this.DecodeBase64(mFieldValue);
                return mReturn;
            }
            return mReturn;
        }
        return mReturn;
    }

    public boolean MakeDirectory(String FilePath) {
        File mFile = new File(FilePath);
        mFile.mkdirs();
        return mFile.isDirectory();
    }

    public void MsgError(String Value) {
        this.FError = Value;
    }

    public String MsgError() {
        return this.FError;
    }

    public void MsgErrorClear() {
        this.FError = "";
    }

    public void MsgFileBody(byte[] Value) {
        this.FMsgFile = Value;
        this.FFileSize = this.FMsgFile.length;
    }

    public byte[] MsgFileBody() {
        return this.FMsgFile;
    }

    public void MsgFileClear() {
        this.FFileSize = 0;
        this.FMsgFile = null;
    }

    public boolean MsgFileLoad(String FileName) {
        try {
            File mFile = new File(FileName);
            int mSize = (int)mFile.length();
            int mRead = 0;
            this.FMsgFile = new byte[mSize];
            FileInputStream mStream = new FileInputStream(mFile);
            while (mRead < mSize) {
                mRead += mStream.read(this.FMsgFile, mRead, mSize - mRead);
            }
            mStream.close();
            this.FFileSize = mSize;
            boolean flag1 = true;
            return flag1;
        }
        catch (Exception e) {
            this.FError = String.valueOf(this.FError) + String.valueOf(e.toString());
            System.out.println(e.toString());
            boolean flag = false;
            return flag;
        }
    }

    public boolean MsgFileSave(String FileName) {
        try {
            FileOutputStream mFile = new FileOutputStream(FileName);
            mFile.write(this.FMsgFile);
            mFile.close();
            boolean flag = true;
            return flag;
        }
        catch (Exception e) {
            this.FError = String.valueOf(this.FError) + String.valueOf(e.toString());
            System.out.println(e.toString());
            boolean flag1 = false;
            return flag1;
        }
    }

    public void MsgFileSize(int value) {
        this.FFileSize = value;
    }

    public int MsgFileSize() {
        return this.FFileSize;
    }

    public void MsgTextBody(String Value) {
        this.FMsgText = Value;
    }

    public String MsgTextBody() {
        return this.FMsgText;
    }

    public void MsgTextClear() {
        this.FMsgText = "";
    }

    private boolean MsgToStream() {
        int HeadSize = 64;
        int BodySize = 0;
        int ErrorSize = 0;
        int FileSize = 0;
        int Position = 0;
        try {
            Position = 0;
            BodySize = this.FMsgText.getBytes().length;
            ErrorSize = this.FError.getBytes().length;
            FileSize = this.FFileSize;
            ByteArrayOutputStream mBuffer = new ByteArrayOutputStream(HeadSize + BodySize + ErrorSize + FileSize);
            String HeadString = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.FormatHead(this.FVersion)))).append(this.FormatHead(String.valueOf(BodySize))).append(this.FormatHead(String.valueOf(ErrorSize))).append(this.FormatHead(String.valueOf(FileSize)))));
            mBuffer.write(HeadString.getBytes(), Position, HeadSize);
            Position += HeadSize;
            if (BodySize > 0) {
                mBuffer.write(this.FMsgText.getBytes());
            }
            Position += BodySize;
            if (ErrorSize > 0) {
                mBuffer.write(this.FError.getBytes());
            }
            Position += ErrorSize;
            if (FileSize > 0) {
                mBuffer.write(this.FMsgFile);
            }
            Position += FileSize;
            mBuffer.close();
            this.FStream = mBuffer.toByteArray();
            boolean flag = true;
            return flag;
        }
        catch (IOException e) {
            this.FError = String.valueOf(this.FError) + String.valueOf(e.toString());
            System.out.println(e.toString());
            boolean flag1 = false;
            return flag1;
        }
    }

    public void MsgVariant(byte[] mStream) {
        this.FStream = mStream;
        if (this.FError == "") {
            this.StreamToMsg();
        }
    }

    public byte[] MsgVariant() {
        this.MsgToStream();
        return this.FStream;
    }

    public String MsgVersion() {
        return this.FVersion;
    }

    public boolean SavePackage(String FileName) {
        try {
            FileOutputStream mFile = new FileOutputStream(FileName);
            mFile.write(this.FStream);
            mFile.close();
            boolean flag = true;
            return flag;
        }
        catch (Exception e) {
            e.printStackTrace();
            boolean flag1 = false;
            return flag1;
        }
    }

    public void SetMsgByName(String FieldName, String FieldValue) {
        String mFieldText = "";
        String mFieldHead = "";
        String mFieldNill = "";
        int i = 0;
        int j = 0;
        boolean f = false;
        String mFieldName = FieldName.trim().concat("=");
        String mFieldValue = this.EncodeBase64(FieldValue);
        mFieldText = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(mFieldName))).append(mFieldValue).append("\r\n")));
        i = this.FMsgText.indexOf(mFieldName);
        if (i != -1 && (j = this.FMsgText.indexOf("\r\n", i + 1)) != -1) {
            mFieldHead = this.FMsgText.substring(0, i);
            mFieldNill = this.FMsgText.substring(j + 2);
            f = true;
        }
        this.FMsgText = f ? String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(mFieldHead))).append(mFieldText).append(mFieldNill))) : this.FMsgText.concat(mFieldText);
    }

    private boolean StreamToMsg() {
        int HeadSize = 64;
        int BodySize = 0;
        int ErrorSize = 0;
        int FileSize = 0;
        int Position = 0;
        try {
            Position = 0;
            String HeadString = new String(this.FStream, Position, HeadSize);
            this.FVersion = HeadString.substring(0, 15);
            BodySize = Integer.parseInt(HeadString.substring(16, 31).trim());
            ErrorSize = Integer.parseInt(HeadString.substring(32, 47).trim());
            this.FFileSize = FileSize = Integer.parseInt(HeadString.substring(48, 63).trim());
            Position += HeadSize;
            if (BodySize > 0) {
                this.FMsgText = new String(this.FStream, Position, BodySize);
            }
            Position += BodySize;
            if (ErrorSize > 0) {
                this.FError = new String(this.FStream, Position, ErrorSize);
            }
            Position += ErrorSize;
            this.FMsgFile = new byte[FileSize];
            if (FileSize > 0) {
                int i = 0;
                while (i < FileSize) {
                    this.FMsgFile[i] = this.FStream[i + Position];
                    ++i;
                }
            }
            boolean flag = true;
            return flag;
        }
        catch (Exception e) {
            this.FError = String.valueOf(this.FError) + String.valueOf(e.toString());
            System.out.println(e.toString());
            boolean flag1 = false;
            return flag1;
        }
    }
}