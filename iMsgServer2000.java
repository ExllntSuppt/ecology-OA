/*
 *  
 * 
 *  
 *  javax.servlet.ServletInputStream
 *  javax.servlet.ServletOutputStream
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package DBstep;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class iMsgServer2000 {
    private String _$901 = "DBSTEP V3.0";
    private String _$902 = "8.5.0.0";
    private String _$903 = "FxcYg3UZvtEz50Na8G476=mLDI/jVfC9dsoMAiBhJSu2qPKe+QRbXry1TnkWHlOpw";
    private byte[] _$904;
    private byte[] _$905;
    private String _$906 = "";
    private String _$907 = "";
    private String _$908 = "DBSTEP V3.0";
    private int _$909 = 0;
    private boolean _$910 = false;
    public String Charset = "GB2312";
    public String Md5Value = "";
    public boolean FDebug = false;
    static final int S11 = 7;
    static final int S12 = 12;
    static final int S13 = 17;
    static final int S14 = 22;
    static final int S21 = 5;
    static final int S22 = 9;
    static final int S23 = 14;
    static final int S24 = 20;
    static final int S31 = 4;
    static final int S32 = 11;
    static final int S33 = 16;
    static final int S34 = 23;
    static final int S41 = 6;
    static final int S42 = 10;
    static final int S43 = 15;
    static final int S44 = 21;
    static final byte[] PADDING = new byte[]{-128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private long[] _$931 = new long[4];
    private long[] _$932 = new long[2];
    private byte[] _$933 = new byte[64];
    private String _$934;
    private byte[] _$935 = new byte[16];

    private String _$936(String inbuf) {
        this._$938();
        this._$939(inbuf.getBytes(), inbuf.length());
        this._$941();
        this._$934 = "";
        for (int i = 0; i < 16; ++i) {
            this._$934 = this._$934 + iMsgServer2000._$943(this._$935[i]);
        }
        return this._$934;
    }

    private void _$938() {
        this._$932[0] = 0;
        this._$932[1] = 0;
        this._$931[0] = 1732584193;
        this._$931[1] = 4023233417L;
        this._$931[2] = 2562383102L;
        this._$931[3] = 271733878;
    }

    private long _$944(long x, long y, long z) {
        return x & y | (x ^ -1) & z;
    }

    private long _$948(long x, long y, long z) {
        return x & z | y & (z ^ -1);
    }

    private long _$949(long x, long y, long z) {
        return x ^ y ^ z;
    }

    private long _$950(long x, long y, long z) {
        return y ^ (x | z ^ -1);
    }

    private long _$951(long a, long b, long c, long d, long x, long s, long ac) {
        a += this._$944(b, c, d) + x + ac;
        a = (int)a << (int)s | (int)a >>> (int)(32 - s);
        return a += b;
    }

    private long _$958(long a, long b, long c, long d, long x, long s, long ac) {
        a += this._$948(b, c, d) + x + ac;
        a = (int)a << (int)s | (int)a >>> (int)(32 - s);
        return a += b;
    }

    private long _$959(long a, long b, long c, long d, long x, long s, long ac) {
        a += this._$949(b, c, d) + x + ac;
        a = (int)a << (int)s | (int)a >>> (int)(32 - s);
        return a += b;
    }

    private long _$960(long a, long b, long c, long d, long x, long s, long ac) {
        a += this._$950(b, c, d) + x + ac;
        a = (int)a << (int)s | (int)a >>> (int)(32 - s);
        return a += b;
    }

    private void _$939(byte[] inbuf, int inputLen) {
        int i;
        byte[] block = new byte[64];
        int index = (int)(this._$932[0] >>> 3) & 63;
        this._$932[0] = this._$932[0] + (long)(inputLen << 3);
        if (this._$932[0] < (long)(inputLen << 3)) {
            long[] arrl = this._$932;
            arrl[1] = arrl[1] + 1;
        }
        long[] arrl = this._$932;
        arrl[1] = arrl[1] + (long)(inputLen >>> 29);
        int partLen = 64 - index;
        if (inputLen >= partLen) {
            this._$965(this._$933, inbuf, index, 0, partLen);
            this._$966(this._$933);
            i = partLen;
            while (i + 63 < inputLen) {
                this._$965(block, inbuf, 0, i, 64);
                this._$966(block);
                i += 64;
            }
            index = 0;
        } else {
            i = 0;
        }
        this._$965(this._$933, inbuf, index, i, inputLen - i);
    }

    private void _$941() {
        byte[] bits = new byte[8];
        this._$969(bits, this._$932, 8);
        int index = (int)(this._$932[0] >>> 3) & 63;
        int padLen = index < 56 ? 56 - index : 120 - index;
        this._$939(PADDING, padLen);
        this._$939(bits, 8);
        this._$969(this._$935, this._$931, 16);
    }

    private void _$965(byte[] output, byte[] input, int outpos, int inpos, int len) {
        for (int i = 0; i < len; ++i) {
            output[outpos + i] = input[inpos + i];
        }
    }

    private void _$966(byte[] block) {
        long a = this._$931[0];
        long b = this._$931[1];
        long c = this._$931[2];
        long d = this._$931[3];
        long[] x = new long[16];
        this._$975(x, block, 64);
        a = this._$951(a, b, c, d, x[0], 7, 3614090360L);
        d = this._$951(d, a, b, c, x[1], 12, 3905402710L);
        c = this._$951(c, d, a, b, x[2], 17, 606105819);
        b = this._$951(b, c, d, a, x[3], 22, 3250441966L);
        a = this._$951(a, b, c, d, x[4], 7, 4118548399L);
        d = this._$951(d, a, b, c, x[5], 12, 1200080426);
        c = this._$951(c, d, a, b, x[6], 17, 2821735955L);
        b = this._$951(b, c, d, a, x[7], 22, 4249261313L);
        a = this._$951(a, b, c, d, x[8], 7, 1770035416);
        d = this._$951(d, a, b, c, x[9], 12, 2336552879L);
        c = this._$951(c, d, a, b, x[10], 17, 4294925233L);
        b = this._$951(b, c, d, a, x[11], 22, 2304563134L);
        a = this._$951(a, b, c, d, x[12], 7, 1804603682);
        d = this._$951(d, a, b, c, x[13], 12, 4254626195L);
        c = this._$951(c, d, a, b, x[14], 17, 2792965006L);
        b = this._$951(b, c, d, a, x[15], 22, 1236535329);
        a = this._$958(a, b, c, d, x[1], 5, 4129170786L);
        d = this._$958(d, a, b, c, x[6], 9, 3225465664L);
        c = this._$958(c, d, a, b, x[11], 14, 643717713);
        b = this._$958(b, c, d, a, x[0], 20, 3921069994L);
        a = this._$958(a, b, c, d, x[5], 5, 3593408605L);
        d = this._$958(d, a, b, c, x[10], 9, 38016083);
        c = this._$958(c, d, a, b, x[15], 14, 3634488961L);
        b = this._$958(b, c, d, a, x[4], 20, 3889429448L);
        a = this._$958(a, b, c, d, x[9], 5, 568446438);
        d = this._$958(d, a, b, c, x[14], 9, 3275163606L);
        c = this._$958(c, d, a, b, x[3], 14, 4107603335L);
        b = this._$958(b, c, d, a, x[8], 20, 1163531501);
        a = this._$958(a, b, c, d, x[13], 5, 2850285829L);
        d = this._$958(d, a, b, c, x[2], 9, 4243563512L);
        c = this._$958(c, d, a, b, x[7], 14, 1735328473);
        b = this._$958(b, c, d, a, x[12], 20, 2368359562L);
        a = this._$959(a, b, c, d, x[5], 4, 4294588738L);
        d = this._$959(d, a, b, c, x[8], 11, 2272392833L);
        c = this._$959(c, d, a, b, x[11], 16, 1839030562);
        b = this._$959(b, c, d, a, x[14], 23, 4259657740L);
        a = this._$959(a, b, c, d, x[1], 4, 2763975236L);
        d = this._$959(d, a, b, c, x[4], 11, 1272893353);
        c = this._$959(c, d, a, b, x[7], 16, 4139469664L);
        b = this._$959(b, c, d, a, x[10], 23, 3200236656L);
        a = this._$959(a, b, c, d, x[13], 4, 681279174);
        d = this._$959(d, a, b, c, x[0], 11, 3936430074L);
        c = this._$959(c, d, a, b, x[3], 16, 3572445317L);
        b = this._$959(b, c, d, a, x[6], 23, 76029189);
        a = this._$959(a, b, c, d, x[9], 4, 3654602809L);
        d = this._$959(d, a, b, c, x[12], 11, 3873151461L);
        c = this._$959(c, d, a, b, x[15], 16, 530742520);
        b = this._$959(b, c, d, a, x[2], 23, 3299628645L);
        a = this._$960(a, b, c, d, x[0], 6, 4096336452L);
        d = this._$960(d, a, b, c, x[7], 10, 1126891415);
        c = this._$960(c, d, a, b, x[14], 15, 2878612391L);
        b = this._$960(b, c, d, a, x[5], 21, 4237533241L);
        a = this._$960(a, b, c, d, x[12], 6, 1700485571);
        d = this._$960(d, a, b, c, x[3], 10, 2399980690L);
        c = this._$960(c, d, a, b, x[10], 15, 4293915773L);
        b = this._$960(b, c, d, a, x[1], 21, 2240044497L);
        a = this._$960(a, b, c, d, x[8], 6, 1873313359);
        d = this._$960(d, a, b, c, x[15], 10, 4264355552L);
        c = this._$960(c, d, a, b, x[6], 15, 2734768916L);
        b = this._$960(b, c, d, a, x[13], 21, 1309151649);
        a = this._$960(a, b, c, d, x[4], 6, 4149444226L);
        d = this._$960(d, a, b, c, x[11], 10, 3174756917L);
        c = this._$960(c, d, a, b, x[2], 15, 718787259);
        b = this._$960(b, c, d, a, x[9], 21, 3951481745L);
        long[] arrl = this._$931;
        arrl[0] = arrl[0] + a;
        long[] arrl2 = this._$931;
        arrl2[1] = arrl2[1] + b;
        long[] arrl3 = this._$931;
        arrl3[2] = arrl3[2] + c;
        long[] arrl4 = this._$931;
        arrl4[3] = arrl4[3] + d;
    }

    private void _$969(byte[] output, long[] input, int len) {
        int i = 0;
        for (int j = 0; j < len; j += 4) {
            output[j] = (byte)(input[i] & 255);
            output[j + 1] = (byte)(input[i] >>> 8 & 255);
            output[j + 2] = (byte)(input[i] >>> 16 & 255);
            output[j + 3] = (byte)(input[i] >>> 24 & 255);
            ++i;
        }
    }

    private void _$975(long[] output, byte[] input, int len) {
        int i = 0;
        for (int j = 0; j < len; j += 4) {
            output[i] = iMsgServer2000._$977(input[j]) | iMsgServer2000._$977(input[j + 1]) << 8 | iMsgServer2000._$977(input[j + 2]) << 16 | iMsgServer2000._$977(input[j + 3]) << 24;
            ++i;
        }
    }

    private static long _$977(byte b) {
        return b < 0 ? (long)(b & 255) : (long)b;
    }

    private static String _$943(byte ib) {
        char[] Digit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] ob = new char[]{Digit[ib >>> 4 & 15], Digit[ib & 15]};
        String s = new String(ob);
        return s;
    }

    public String MD5Stream(byte[] Value) {
        this._$938();
        this._$939(Value, Value.length);
        this._$941();
        this._$934 = "";
        for (int i = 0; i < 16; ++i) {
            this._$934 = this._$934 + iMsgServer2000._$943(this._$935[i]);
        }
        return this._$934;
    }

    public iMsgServer2000() {
        if (this.FDebug) {
            System.out.println("Run iMsgServer2000 Version " + this._$902 + "...");
        }
    }

    protected String FormatHead(String vString) {
        if (vString.length() > 16) {
            return vString.substring(0, 16);
        }
        for (int i = vString.length() + 1; i < 17; ++i) {
            vString = vString.concat(" ");
        }
        return vString;
    }

    private boolean _$988() {
        int HeadSize = 64;
        int BodySize = 0;
        int ErrorSize = 0;
        int FileSize = 0;
        int Position = 0;
        try {
            Position = 0;
            BodySize = this._$906.getBytes(this.Charset).length;
            ErrorSize = this._$907.getBytes(this.Charset).length;
            FileSize = this._$909;
            ByteArrayOutputStream mBuffer = new ByteArrayOutputStream(HeadSize + BodySize + ErrorSize + FileSize);
            String HeadString = this.FormatHead(this._$908) + this.FormatHead(String.valueOf(BodySize)) + this.FormatHead(String.valueOf(ErrorSize)) + this.FormatHead(String.valueOf(FileSize));
            mBuffer.write(HeadString.getBytes(), Position, HeadSize);
            Position += HeadSize;
            if (BodySize > 0) {
                mBuffer.write(this._$906.getBytes());
            }
            Position += BodySize;
            if (ErrorSize > 0) {
                mBuffer.write(this._$907.getBytes(this.Charset));
            }
            Position += ErrorSize;
            if (FileSize > 0) {
                mBuffer.write(this._$905);
            }
            Position += FileSize;
            mBuffer.close();
            this._$904 = mBuffer.toByteArray();
            return true;
        }
        catch (Exception e) {
            this._$907 = this._$907 + e.toString();
            System.out.println(e.toString());
            return false;
        }
    }

    public byte[] MsgVariant() {
        this._$988();
        return this._$904;
    }

    private static int _$1001(byte[] b) {
        int s = 0;
        for (int i = 3; i > 0; --i) {
            s = b[i] >= 0 ? (s += b[i]) : s + 256 + b[i];
            s *= 256;
        }
        s = b[0] >= 0 ? (s += b[0]) : s + 256 + b[0];
        return s;
    }

    public byte[] ToDocument(byte[] Value) {
        byte[] mIntBuf = new byte[]{0, 0, 0, 0};
        byte[] mFlagBuf = new byte[]{68, 73, 82, 71};
        byte[] mGZipBuf = new byte[]{80, 75, 3, 4};
        byte[] mOutBuf = null;
        boolean HeadFlag = false;
        int Signature = 0;
        int WordSize = 0;
        int PageSize = 0;
        int FlagSize = 0;
        try {
            ByteArrayInputStream mStream = new ByteArrayInputStream(Value);
            mStream.read(mIntBuf, 0, 4);
            Signature = iMsgServer2000._$1001(mIntBuf);
            mStream.read(mIntBuf, 0, 4);
            WordSize = iMsgServer2000._$1001(mIntBuf);
            mStream.read(mIntBuf, 0, 4);
            PageSize = iMsgServer2000._$1001(mIntBuf);
            mStream.read(mIntBuf, 0, 4);
            FlagSize = iMsgServer2000._$1001(mIntBuf);
            if (Signature != iMsgServer2000._$1001(mFlagBuf)) {
                mStream.reset();
                WordSize = mStream.available();
            }
            mOutBuf = new byte[WordSize];
            mStream.read(mOutBuf, 0, WordSize);
            return mOutBuf;
        }
        catch (Exception e) {
            this._$907 = this._$907 + e.toString();
            System.out.println(e.toString());
            return mOutBuf;
        }
    }

    private boolean _$1015() {
        int HeadSize = 64;
        int BodySize = 0;
        int ErrorSize = 0;
        int FileSize = 0;
        int Position = 0;
        String Md5Calcu = "";
        this._$910 = false;
        this.Md5Value = "";
        try {
            Position = 0;
            String HeadString = new String(this._$904, Position, HeadSize);
            this._$908 = HeadString.substring(0, 15);
            BodySize = Integer.parseInt(HeadString.substring(16, 31).trim());
            ErrorSize = Integer.parseInt(HeadString.substring(32, 47).trim());
            this._$909 = FileSize = Integer.parseInt(HeadString.substring(48, 63).trim());
            Position += HeadSize;
            if (BodySize > 0) {
                this._$906 = new String(this._$904, Position, BodySize);
            }
            Position += BodySize;
            if (ErrorSize > 0) {
                this._$907 = new String(this._$904, Position, ErrorSize);
            }
            Position += ErrorSize;
            this._$905 = new byte[FileSize];
            if (FileSize > 0) {
                for (int i = 0; i < FileSize; ++i) {
                    this._$905[i] = this._$904[i + Position];
                }
                if (this._$904.length >= (Position += FileSize) + 32) {
                    this.Md5Value = new String(this._$904, Position, 32);
                    if (this.FDebug) {
                        System.out.println("From Client:" + this.Md5Value);
                    }
                }
            }
            return true;
        }
        catch (Exception e) {
            this._$907 = this._$907 + e.toString();
            System.out.println(e.toString());
            return false;
        }
    }

    public void MsgVariant(byte[] mStream) {
        this._$904 = mStream;
        if (this._$907 == "") {
            this._$1015();
        }
    }

    public boolean SavePackage(String FileName) {
        try {
            FileOutputStream mFile = new FileOutputStream(FileName);
            mFile.write(this._$904);
            mFile.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean MsgFileSave(String FileName) {
        try {
            FileOutputStream mFile = new FileOutputStream(FileName);
            mFile.write(this._$905);
            mFile.close();
            return true;
        }
        catch (Exception e) {
            this._$907 = this._$907 + e.toString();
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean MsgFileLoad(String FileName) {
        try {
            File mFile = new File(FileName);
            int mSize = (int)mFile.length();
            this._$905 = new byte[mSize];
            FileInputStream mStream = new FileInputStream(mFile);
            for (int mRead = 0; mRead < mSize; mRead += mStream.read((byte[])this._$905, (int)mRead, (int)(mSize - mRead))) {
            }
            mStream.close();
            this._$909 = mSize;
            return true;
        }
        catch (Exception e) {
            this._$907 = this._$907 + e.toString();
            System.out.println(e.toString());
            return false;
        }
    }

    public String MsgTextBody() {
        return this._$906;
    }

    public byte[] MsgFileBody() {
        return this._$905;
    }

    public String MsgError() {
        return this._$907;
    }

    public String MsgVersion() {
        return this._$908;
    }

    public void MsgTextBody(String Value) {
        this._$906 = Value;
    }

    public void MsgFileBody(byte[] Value) {
        this._$905 = Value;
        this._$909 = this._$905.length;
    }

    public void MsgError(String Value) {
        this._$907 = this._$910 ? "[01]" + Value : Value;
    }

    public int MsgFileSize() {
        return this._$909;
    }

    public void MsgFileSize(int value) {
        this._$909 = value;
    }

    public void MsgFileClear() {
        this._$909 = 0;
        this._$905 = null;
    }

    public void MsgTextClear() {
        this._$906 = "";
    }

    public void MsgErrorClear() {
        this._$907 = "";
    }

    public String DecodeBase64(String Value) {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        String m = "";
        byte[] d = new byte[4];
        try {
            int count = 0;
            byte[] x = Value.getBytes();
            while (count < x.length) {
                for (int n = 0; n <= 3; ++n) {
                    if (count >= x.length) {
                        d[n] = 64;
                    } else {
                        int y = this._$903.indexOf(x[count]);
                        if (y < 0) {
                            y = 65;
                        }
                        d[n] = (byte)y;
                    }
                    ++count;
                }
                o.write((byte)(((d[0] & 63) << 2) + ((d[1] & 48) >> 4)));
                if (d[2] == 64) continue;
                o.write((byte)(((d[1] & 15) << 4) + ((d[2] & 60) >> 2)));
                if (d[3] == 64) continue;
                o.write((byte)(((d[2] & 3) << 6) + (d[3] & 63)));
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            this._$907 = this._$907 + e.toString();
            System.out.println(e.toString());
        }
        try {
            m = o.toString(this.Charset);
        }
        catch (UnsupportedEncodingException ea) {
            System.out.println(ea.toString());
        }
        return m;
    }

    public String EncodeBase64(String Value) {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        byte[] d = new byte[4];
        try {
            int count = 0;
            byte[] x = Value.getBytes(this.Charset);
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
                for (int n = 0; n <= 3; ++n) {
                    o.write(this._$903.charAt(d[n]));
                }
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            this._$907 = this._$907 + e.toString();
            System.out.println(e.toString());
        }
        catch (UnsupportedEncodingException ea) {
            System.out.println(ea.toString());
        }
        return o.toString();
    }

    public int GetFieldCount() {
        int i = 0;
        int j = 0;
        i = this._$906.indexOf("\r\n", i + 1);
        while (i != -1) {
            ++j;
            i = this._$906.indexOf("\r\n", i + 1);
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
            if ((i = this._$906.indexOf("\r\n", i + 1)) == -1) continue;
            ++j;
        }
        k = this._$906.indexOf("\r\n", i + 1);
        if (i != -1 && k != -1) {
            mFieldString = i == 0 ? this._$906.substring(i, k) : this._$906.substring(i + 2, k);
            n = mFieldString.indexOf("=", 0);
            if (n != -1) {
                mReturn = mFieldName = mFieldString.substring(0, n);
            }
        }
        return mReturn;
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
            if ((i = this._$906.indexOf("\r\n", i + 1)) == -1) continue;
            ++j;
        }
        k = this._$906.indexOf("\r\n", i + 1);
        if (i != -1 && k != -1) {
            mFieldString = i == 0 ? this._$906.substring(i, k) : this._$906.substring(i + 2, k);
            n = mFieldString.indexOf("=", 0);
            if (n != -1) {
                mFieldValue = mFieldString.substring(n + 1, mFieldString.length());
                mReturn = this.DecodeBase64(mFieldValue);
            }
        }
        return mReturn;
    }

    public String GetFieldText() {
        return this._$906.toString();
    }

    public String GetMsgByName(String FieldName) {
        int i = 0;
        int j = 0;
        String mReturn = "";
        String mFieldName = FieldName.trim().concat("=");
        i = this._$906.indexOf(mFieldName);
        if (i != -1) {
            j = this._$906.indexOf("\r\n", i + 1);
            i += mFieldName.length();
            if (j != -1) {
                String mFieldValue = this._$906.substring(i, j);
                mReturn = this.DecodeBase64(mFieldValue);
                return mReturn;
            }
            return mReturn;
        }
        return mReturn;
    }

    public void SetMsgByName(String FieldName, String FieldValue) {
        String mFieldText = "";
        String mFieldHead = "";
        String mFieldNill = "";
        int i = 0;
        int j = 0;
        boolean f = false;
        if (FieldName.compareToIgnoreCase("STATUS") == 0 && this._$910) {
            FieldValue = "[01]" + FieldValue;
        }
        String mFieldName = FieldName.trim().concat("=");
        String mFieldValue = this.EncodeBase64(FieldValue);
        mFieldText = mFieldName + mFieldValue + "\r\n";
        i = this._$906.indexOf(mFieldName);
        if (i != -1 && (j = this._$906.indexOf("\r\n", i + 1)) != -1) {
            mFieldHead = this._$906.substring(0, i);
            mFieldNill = this._$906.substring(j + 2);
            f = true;
        }
        this._$906 = f ? new StringBuffer().append(mFieldHead).append(mFieldText).append(mFieldNill).toString() : this._$906.concat(mFieldText);
    }

    public boolean MakeDirectory(String FilePath) {
        File mFile = new File(FilePath);
        mFile.mkdirs();
        return mFile.isDirectory();
    }

    public boolean MKDirectory(String FilePath) {
        File mFile = new File(FilePath);
        mFile.mkdirs();
        return mFile.isDirectory();
    }

    public boolean RMDirectory(String FilePath) {
        File mFile = new File(FilePath);
        if (mFile.isDirectory()) {
            mFile.delete();
        }
        return true;
    }

    public boolean DelFile(String FileName) {
        File mFile = new File(FileName);
        if (mFile.exists()) {
            mFile.delete();
        }
        return true;
    }

    public boolean DelTree(String FilePath) {
        File mFile = new File(FilePath);
        if (mFile.isDirectory()) {
            mFile.delete();
        }
        return true;
    }

    public int LoadFilePoint(String FileName) {
        int i = 0;
        int j = 0;
        int mSize = 0;
        String mText = "";
        String mReturn = "-1";
        String mFieldName = "INDEX=";
        try {
            File mFile = new File(FileName + ".fp");
            mSize = (int)mFile.length();
            byte[] mBuffer = new byte[mSize];
            FileInputStream mStream = new FileInputStream(mFile);
            mStream.read(mBuffer, 0, mSize);
            mStream.close();
            mText = new String(mBuffer);
        }
        catch (Exception e) {
            this._$907 = this._$907 + e.toString();
            return Integer.parseInt(mReturn);
        }
        i = mText.indexOf(mFieldName);
        if (i != -1) {
            j = mText.indexOf("\r\n", i + 1);
            i += mFieldName.length();
            if (j != -1) {
                mReturn = mText.substring(i, j - i);
                return Integer.parseInt(mReturn);
            }
            return Integer.parseInt(mReturn);
        }
        return Integer.parseInt(mReturn);
    }

    public boolean SaveFilePoint(String FileName, int FCount) {
        boolean i = false;
        boolean j = false;
        int mSize = 0;
        String mFieldName = "INDEX=";
        String mCount = "";
        try {
            FileOutputStream mFile = new FileOutputStream(FileName);
            mCount = mFieldName + FCount + "\r\n";
            byte[] mBuffer = mCount.getBytes();
            mSize = mBuffer.length;
            mFile.write(mBuffer, 0, mSize);
            mFile.close();
            return true;
        }
        catch (Exception e) {
            this._$907 = this._$907 + e.toString();
            System.out.println("SaveFilePoint:" + this._$907);
            return false;
        }
    }

    public boolean SaveFromStream(String FileName, int Index) {
        String mPkName = "";
        mPkName = FileName + ".fp";
        this.DelFile(mPkName);
        if (Index == 0) {
            this.DelFile(FileName);
        }
        try {
            RandomAccessFile mFile = new RandomAccessFile(FileName, "rw");
            mFile.seek(mFile.length());
            mFile.write(this._$905);
            mFile.close();
        }
        catch (Exception e) {
            this._$907 = this._$907 + e.toString();
            System.out.println("SaveFromStream:" + this._$907);
            return false;
        }
        return true;
    }

    public boolean DecodeBase64ToFile(String Value, String FileName) {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        boolean mResult = false;
        byte[] d = new byte[4];
        try {
            int count = 0;
            byte[] x = Value.getBytes();
            while (count < x.length) {
                for (int n = 0; n <= 3; ++n) {
                    if (count >= x.length) {
                        d[n] = 64;
                    } else {
                        int y = this._$903.indexOf(x[count]);
                        if (y < 0) {
                            y = 65;
                        }
                        d[n] = (byte)y;
                    }
                    ++count;
                }
                o.write((byte)(((d[0] & 63) << 2) + ((d[1] & 48) >> 4)));
                if (d[2] == 64) continue;
                o.write((byte)(((d[1] & 15) << 4) + ((d[2] & 60) >> 2)));
                if (d[3] == 64) continue;
                o.write((byte)(((d[2] & 3) << 6) + (d[3] & 63)));
            }
            FileOutputStream mFile = new FileOutputStream(FileName);
            byte[] mBuffer = o.toByteArray();
            int mSize = mBuffer.length;
            mFile.write(mBuffer, 0, mSize);
            mFile.close();
            mResult = true;
        }
        catch (Exception e) {
            this._$907 = this._$907 + e.toString();
            mResult = false;
            System.out.println(e.toString());
        }
        return mResult;
    }

    public boolean SaveFromFile(String FileName, int FileCount) {
        int mIndex = 0;
        String mPkName = "";
        mPkName = FileName + ".fp";
        this.DelFile(mPkName);
        try {
            FileOutputStream mFile = new FileOutputStream(FileName);
            for (mIndex = 0; mIndex <= FileCount; ++mIndex) {
                mPkName = FileName + "." + mIndex;
                File nTemp = new File(mPkName);
                FileInputStream mTemp = new FileInputStream(nTemp);
                byte[] mBuffer = new byte[(int)nTemp.length()];
                mTemp.read(mBuffer, 0, (int)nTemp.length());
                mFile.write(mBuffer, 0, (int)nTemp.length());
                mTemp.close();
                nTemp.delete();
            }
            mFile.close();
        }
        catch (Exception e) {
            this._$907 = this._$907 + e.toString();
            System.out.println("SaveFromFile:" + this._$907);
            return false;
        }
        return true;
    }

    public byte[] ReadPackage(HttpServletRequest request) {
        int totalRead = 0;
        int readBytes = 0;
        int totalBytes = 0;
        this.Charset = request.getCharacterEncoding();
        if (this.Charset == null) {
            this.Charset = request.getHeader("charset");
        }
        if (this.Charset == null) {
            this.Charset = "GB2312";
        }
        if (this.FDebug) {
            System.out.println("Charset :" + this.Charset);
        }
        try {
            totalBytes = request.getContentLength();
            this._$904 = new byte[totalBytes];
            while (totalRead < totalBytes) {
                request.getInputStream();
                readBytes = request.getInputStream().read(this._$904, totalRead, totalBytes - totalRead);
                totalRead += readBytes;
            }
            if (this._$907 == "") {
                this._$1015();
            }
        }
        catch (Exception e) {
            System.out.println("ReadPackage:" + e.toString());
        }
        return this._$904;
    }

    public void Load(HttpServletRequest request) {
        int totalRead = 0;
        int readBytes = 0;
        int totalBytes = 0;
        if (this.FDebug) {
            System.out.println("Load request ...");
        }
        this.Charset = request.getCharacterEncoding();
        if (this.Charset == null) {
            this.Charset = request.getHeader("charset");
        }
        if (this.Charset == null) {
            this.Charset = "GB2312";
        }
        if (this.FDebug) {
            System.out.println("Charset :" + this.Charset);
        }
        try {
            totalBytes = request.getContentLength();
            this._$904 = new byte[totalBytes];
            while (totalRead < totalBytes) {
                request.getInputStream();
                readBytes = request.getInputStream().read(this._$904, totalRead, totalBytes - totalRead);
                totalRead += readBytes;
            }
            if (this._$907 == "") {
                this._$1015();
            }
        }
        catch (Exception e) {
            System.out.println("Load:" + e.toString());
        }
    }

    public void SendPackage(HttpServletResponse response) {
        try {
            ServletOutputStream OutBinarry = response.getOutputStream();
            OutBinarry.write(this.MsgVariant());
            OutBinarry.flush();
            OutBinarry.close();
        }
        catch (Exception e) {
            System.out.println("SendPackage:" + e.toString());
        }
    }

    public void Send(HttpServletResponse response) {
        if (this.FDebug) {
            System.out.println("Send response ...");
        }
        try {
            ServletOutputStream OutBinarry = response.getOutputStream();
            OutBinarry.write(this.MsgVariant());
            OutBinarry.flush();
            OutBinarry.close();
        }
        catch (Exception e) {
            System.out.println("Send:" + e.toString());
        }
    }

    public static String Version() {
        return "8,5,0,0";
    }

    public static String Version(String SoftwareName) {
        String mVersion = "0,0,0,0";
        if (SoftwareName.equalsIgnoreCase("HandWrite") || SoftwareName.equalsIgnoreCase("")) {
            mVersion = "4,3,44,0";
        }
        if (SoftwareName.equalsIgnoreCase("iWebSignature") || SoftwareName.equalsIgnoreCase("iWebRevision")) {
            mVersion = "6,4,0,198";
        }
        if (SoftwareName.equalsIgnoreCase("iWebPDF")) {
            mVersion = "7,2,0,338";
        }
        return mVersion;
    }

    public static String VersionEx() {
        return "\u5ba2\u6237\u7248\u672c";
    }

    public static String VersionEx(String SoftwareName) {
        String mVersionEx = "\u9519\u8bef\u7248\u672c";
        if (SoftwareName.equalsIgnoreCase("HandWrite") || SoftwareName.equalsIgnoreCase("")) {
            mVersionEx = "\u5ba2\u6237\u7248\u672c";
        }
        if (SoftwareName.equalsIgnoreCase("iWebSignature") || SoftwareName.equalsIgnoreCase("iWebRevision")) {
            mVersionEx = "\u6807\u51c6\u7248\u672c";
        }
        if (SoftwareName.equalsIgnoreCase("iWebPDF")) {
            mVersionEx = "\u6807\u51c6\u7248\u672c";
        }
        return mVersionEx;
    }
}