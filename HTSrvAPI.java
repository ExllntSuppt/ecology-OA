/*
 *  
 */
package HT;

import java.security.DigestException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class HTSrvAPI {
    public String HTSrvSHA1(String string, int n) {
        String string2 = "";
        try {
            byte[] arrby = new byte[20];
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(string.getBytes(), 0, n);
            messageDigest.digest(arrby, 0, 20);
            string2 = this.ByteToString(arrby, 20);
        }
        catch (NoSuchAlgorithmException var4_5) {
            var4_5.printStackTrace();
        }
        catch (DigestException var4_6) {
            var4_6.printStackTrace();
        }
        return string2;
    }

    public String HTSrvCrypt(int n, String string, int n2, String string2) {
        byte[] arrby = new byte[24];
        String string3 = string + string.substring(0, 16);
        byte[] arrby2 = new byte[24];
        arrby2 = this.StrToByte(string3);
        byte[] arrby3 = this.StrToByte(string2);
        try {
            int n3 = 1;
            DESedeKeySpec dESedeKeySpec = new DESedeKeySpec(arrby2);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey secretKey = secretKeyFactory.generateSecret(dESedeKeySpec);
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(n3, secretKey);
            arrby = cipher.doFinal(arrby3);
        }
        catch (Exception var9_10) {
            var9_10.printStackTrace();
        }
        String string4 = this.ByteToString(arrby, 24);
        return string4;
    }

    public String ByteToString(byte[] arrby, int n) {
        if (arrby == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        byte[] arrby2 = arrby;
        for (int i = 0; i < n; ++i) {
            int n2 = arrby2[i] >> 4 & 15;
            stringBuffer.append("0123456789ABCDEF00000".charAt(n2));
            n2 = arrby2[i] & 15;
            stringBuffer.append("0123456789ABCDEF00000".charAt(n2));
        }
        return stringBuffer.toString();
    }

    public byte[] StrToByte(String string) {
        int n = string.length();
        byte[] arrby = new byte[n / 2];
        char[] arrc = new char[n];
        for (int i = 0; i < n; ++i) {
            arrc[i] = string.charAt(i);
        }
        for (int j = 0; j < n; ++j) {
            byte by = (byte)arrc[j];
            if (j % 2 == 0) {
                if (by >= 48 && by <= 57) {
                    arrby[j / 2] = (byte)(by - 48 << 4);
                    continue;
                }
                if (by >= 97 && by <= 102) {
                    arrby[j / 2] = (byte)(by - 87 << 4);
                    continue;
                }
                if (by < 65 || by > 70) continue;
                arrby[j / 2] = (byte)(by - 55 << 4);
                continue;
            }
            if (by >= 48 && by <= 57) {
                byte[] arrby2 = arrby;
                int n2 = j / 2;
                arrby2[n2] = (byte)(arrby2[n2] | by - 48);
                continue;
            }
            if (by >= 97 && by <= 102) {
                byte[] arrby3 = arrby;
                int n3 = j / 2;
                arrby3[n3] = (byte)(arrby3[n3] | by - 87);
                continue;
            }
            if (by < 65 || by > 70) continue;
            byte[] arrby4 = arrby;
            int n4 = j / 2;
            arrby4[n4] = (byte)(arrby4[n4] | by - 55);
        }
        return arrby;
    }

    public static String HexToStr(byte[] arrby, int n) {
        char[] arrc = new char[2 * n + 1];
        for (int i = 0; i < n; ++i) {
            if ((arrby[i] & 240) >> 4 >= 0 && (arrby[i] & 240) >> 4 <= 9) {
                arrc[2 * i] = (char)(((arrby[i] & 240) >> 4) + 48);
            } else if ((arrby[i] & 240) >> 4 >= 10 && (arrby[i] & 240) >> 4 <= 16) {
                arrc[2 * i] = (char)(((arrby[i] & 240) >> 4) + 55);
            }
            if ((arrby[i] & 15) >= 0 && (arrby[i] & 15) <= 9) {
                arrc[2 * i + 1] = (char)((arrby[i] & 15) + 48);
                continue;
            }
            if ((arrby[i] & 15) < 10 || (arrby[i] & 15) > 16) continue;
            arrc[2 * i + 1] = (char)((arrby[i] & 15) + 55);
        }
        String string = new String(arrc);
        return string;
    }
}