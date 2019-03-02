/*
 *  
 * 
 *  
 *  weaver.general.GCONST
 */
package com.wellcom;

import java.io.File;
import weaver.general.GCONST;

public class GfpVerify {
    private static GfpVerify gfpVerify;

    private GfpVerify() {
    }

    public static GfpVerify getGfpVerify() {
        if (gfpVerify == null) {
            gfpVerify = new GfpVerify();
        }
        return gfpVerify;
    }

    private native int execute(byte[] var1, byte[] var2, int var3);

    public boolean verifySimple(String string, String string2) {
        byte[] arrby;
        int n;
        if (string2 == null || string2 == "") {
            return false;
        }
        byte[] arrby2 = string2.getBytes();
        if (string == null && string == "") {
            string = "";
        }
        if ((n = this.execute(arrby = string.getBytes(), arrby2, 3)) == 0) {
            return true;
        }
        return false;
    }

    static {
        String string = GCONST.getRootPath() + File.separatorChar + "login" + File.separatorChar + "gfpv20_v3_0_5_for_win_20081023.dll";
        System.load(string);
    }
}