/*
 *  
 * 
 *  
 *  com.weaver.integration.params.ServiceParamModeStatusBean
 *  weaver.conn.RecordSet
 */
package com.weaver.integration.params;

import com.weaver.integration.params.ServiceParamModeDisBean;
import com.weaver.integration.params.ServiceParamModeStatusBean;
import java.util.ArrayList;
import java.util.List;
import weaver.conn.RecordSet;

public class ServiceParamModeDisUtil {
    private static final String PARAMIMPORT = "import";
    private static final String PARAMEXPORT = "export";
    private static final String PARAMSTRUCT = "struct";
    private static final String PARAMTABLE = "table";

    public static boolean insOrUpdBySerParModeDisBean(ServiceParamModeDisBean serviceParamModeDisBean, boolean bl) {
        boolean bl2 = false;
        if (serviceParamModeDisBean == null) {
            return bl2;
        }
        String string = serviceParamModeDisBean.getServId();
        String string2 = serviceParamModeDisBean.getParamModeId();
        String string3 = serviceParamModeDisBean.getParamType();
        String string4 = serviceParamModeDisBean.getParamName();
        String string5 = serviceParamModeDisBean.getParamDesc();
        boolean bl3 = serviceParamModeDisBean.isCompSty();
        String string6 = bl3 ? "1" : "0";
        String string7 = serviceParamModeDisBean.getCompStyTypeName();
        String string8 = serviceParamModeDisBean.getCompstyname();
        boolean bl4 = false;
        String string9 = bl4 ? "1" : "0";
        String string10 = serviceParamModeDisBean.getParamCons();
        String string11 = "";
        if ("".equals(string) || "".equals(string2) || "".equals(string4) || !"import".equals(string3) && !"export".equals(string3)) {
            return bl2;
        }
        String string12 = "";
        if (bl) {
            string12 = "insert into int_servParamModeDis(servid,parammodeid,paramtype,paramdesc,paramname,iscompsty,compstytypename,compstyname,paramismust,paramcons,paramordernum)values('";
            string12 = string12 + string + "','" + string2 + "','" + string3 + "','" + string5 + "','" + string4 + "','" + string6 + "','" + string7 + "','" + string8 + "','" + string9 + "','";
            string12 = string12 + string10 + "','" + string11 + "')";
        }
        RecordSet recordSet = new RecordSet();
        if ("".equals(string12)) {
            return bl2;
        }
        bl2 = recordSet.execute(string12);
        return bl2;
    }

    public static List getParamsModeDisById(String string, String string2, String string3, boolean bl, String string4, String string5) {
        String string6;
        ArrayList<ServiceParamModeDisBean> arrayList = null;
        String string7 = "";
        String string8 = string6 = bl ? "1" : "0";
        string7 = "".equals(string5) ? ("".equals(string4) ? "select * from int_servParamModeDis where (compstyname is null or compstyname = '') and  paramtype='" + string3 + "' and iscompsty=" + string6 + " and servid=" + string + " and parammodeid=" + string2 : "select * from int_servParamModeDis where (compstyname is null or compstyname = '') and  paramtype='" + string3 + "' and iscompsty=" + string6 + " and servid=" + string + " and parammodeid=" + string2 + " and compstytypename='" + string4 + "'") : ("".equals(string4) ? "select * from int_servParamModeDis where paramtype='" + string3 + "' and iscompsty=" + string6 + " and servid=" + string + " and parammodeid=" + string2 + " and compstyname='" + string5 + "'" : "select * from int_servParamModeDis where paramtype='" + string3 + "' and iscompsty=" + string6 + " and servid=" + string + " and parammodeid=" + string2 + " and compstytypename='" + string4 + "' and compstyname='" + string5 + "'");
        RecordSet recordSet = new RecordSet();
        recordSet.executeSql(string7);
        while (recordSet.next()) {
            if (arrayList == null) {
                arrayList = new ArrayList<ServiceParamModeDisBean>();
            }
            ServiceParamModeDisBean serviceParamModeDisBean = new ServiceParamModeDisBean();
            String string9 = recordSet.getString("paramname");
            String string10 = recordSet.getString("paramdesc");
            String string11 = recordSet.getString("paramcons");
            serviceParamModeDisBean.setCompSty(bl);
            serviceParamModeDisBean.setCompstyname(string5);
            serviceParamModeDisBean.setCompStyTypeName(string4);
            serviceParamModeDisBean.setParamCons(string11);
            serviceParamModeDisBean.setParamDesc(string10);
            serviceParamModeDisBean.setParamModeId(string2);
            serviceParamModeDisBean.setParamName(string9);
            serviceParamModeDisBean.setParamType(string3);
            serviceParamModeDisBean.setServId(string);
            arrayList.add(serviceParamModeDisBean);
        }
        return arrayList;
    }

    public static ServiceParamModeStatusBean getServParModStaBeanById(String string, String string2) {
        ServiceParamModeStatusBean serviceParamModeStatusBean = new ServiceParamModeStatusBean();
        String string3 = "select A.impStrCount, B.expStrCount,c.impStructCount,d.expStructCount,e.imptableCount,f.exptableCount from ";
        String string4 = "(select count(*) impStrCount from int_servParamModeDis where  iscompsty=0 and paramtype= 'import' and servid=" + string + " and parammodeid=" + string2 + " and (compstyname is null or compstyname = '')) A,";
        String string5 = "(select count(*) expStrCount from int_servParamModeDis where  iscompsty=0 and paramtype= 'export' and servid=" + string + " and parammodeid=" + string2 + " and (compstyname is null or compstyname = '')) B,";
        String string6 = "(select count(*) impStructCount from int_servParamModeDis where iscompsty=1 and paramtype= 'import' and servid=" + string + " and parammodeid=" + string2 + " and compstytypename='struct') c,";
        String string7 = "(select count(*) expStructCount from int_servParamModeDis where iscompsty=1 and paramtype= 'export' and servid=" + string + " and parammodeid=" + string2 + " and compstytypename='struct') d,";
        String string8 = "(select count(*) imptableCount from int_servParamModeDis where iscompsty=1 and paramtype= 'import' and compstytypename='table' and servid=" + string + " and parammodeid=" + string2 + ") e,";
        String string9 = "(select count(*) exptableCount from int_servParamModeDis where iscompsty=1 and paramtype= 'export' and compstytypename='table' and servid=" + string + " and parammodeid=" + string2 + ") f";
        string3 = string3 + string4 + string5 + string6 + string7 + string8 + string9;
        RecordSet recordSet = new RecordSet();
        recordSet.executeSql(string3);
        if (recordSet.next()) {
            serviceParamModeStatusBean.setImpstrcount(recordSet.getInt("impStrCount"));
            serviceParamModeStatusBean.setImpstructcount(recordSet.getInt("impStructCount"));
            serviceParamModeStatusBean.setImptablecount(recordSet.getInt("imptableCount"));
            serviceParamModeStatusBean.setExpstrcount(recordSet.getInt("expStrCount"));
            serviceParamModeStatusBean.setExpstructcount(recordSet.getInt("expStructCount"));
            serviceParamModeStatusBean.setExptablecount(recordSet.getInt("exptableCount"));
        }
        return serviceParamModeStatusBean;
    }

    public static int getCompFieldCountByName(String string, String string2, String string3, String string4) {
        int n = 0;
        String string5 = "select count(*) numbs from int_servParamModeDis where  iscompsty=0 and paramtype= '" + string3 + "' and servid=" + string + " and parammodeid=" + string2 + " and compstyname='" + string4 + "'";
        RecordSet recordSet = new RecordSet();
        recordSet.executeSql(string5);
        if (recordSet.next()) {
            n = recordSet.getInt("numbs");
        }
        return n;
    }

    public static boolean deleteAllParamsMode(String string, String string2) {
        boolean bl = false;
        String string3 = "delete from int_servParamModeDis where servid=" + string + " and parammodeid=" + string2;
        RecordSet recordSet = new RecordSet();
        bl = recordSet.executeSql(string3);
        return bl;
    }
}