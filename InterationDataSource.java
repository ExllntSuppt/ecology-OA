/*
 *  
 * 
 *  
 *  com.weaver.integration.log.LogInfo
 */
package com.weaver.integration.datesource;

import com.weaver.integration.log.LogInfo;

public interface InterationDataSource {
    public static final int SAPMAXCOUNTCONN = 99;
    public static final String SAPNOCONN = "00";
    public static final String SAPIMPORTPARAMS = "import";
    public static final String SAPEXPORTPARAMS = "export";
    public static final String SAPSTRUCTSTYLE = "struct";
    public static final String SAPSTRSTYLE = "str";
    public static final String SAPTABLESTYLE = "table";

    public Object getConnection(String var1, LogInfo var2);
}