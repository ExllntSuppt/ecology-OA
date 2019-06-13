/*
 *  
 * 
 *  
 *  weaver.conn.RecordSet
 */
package com.weaver.integration.params;

import com.weaver.integration.params.ServiceParamModeBean;
import weaver.conn.RecordSet;

public class ServiceParamModeUtil {
    public static ServiceParamModeBean getSerParModBeanById(String string, boolean bl) {
        ServiceParamModeBean serviceParamModeBean = null;
        String string2 = "";
        string2 = bl ? "select * from int_serviceParamMode where id=" + string : "select * from int_serviceParamMode where servId=" + string;
        RecordSet recordSet = new RecordSet();
        recordSet.execute(string2);
        if (recordSet.next()) {
            serviceParamModeBean = new ServiceParamModeBean();
            serviceParamModeBean.setId(recordSet.getString("id"));
            serviceParamModeBean.setParamModeName(recordSet.getString("paramModeName"));
            serviceParamModeBean.setServId(recordSet.getString("servId"));
        }
        return serviceParamModeBean;
    }

    public static String insertSerParMode(String string, String string2) {
        String string3 = "";
        try {
            String string4 = "insert into int_serviceParamMode(servId,sid)values(" + string + "," + string2 + ")";
            RecordSet recordSet = new RecordSet();
            recordSet.execute(string4);
            string3 = ServiceParamModeUtil.getMaxId();
            String string5 = "update int_serviceParamMode set paramModeName='paramMode." + string3 + "' where id=" + string3;
            recordSet.execute(string5);
        }
        catch (Exception var3_4) {
            var3_4.printStackTrace();
        }
        return string3;
    }

    public static synchronized String getMaxId() {
        String string = "";
        String string2 = "select max(id) numbs from int_serviceParamMode";
        RecordSet recordSet = new RecordSet();
        recordSet.execute(string2);
        if (recordSet.next()) {
            string = recordSet.getString("numbs");
        }
        return string;
    }
}