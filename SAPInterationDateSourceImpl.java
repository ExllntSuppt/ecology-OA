/*
 *  
 * 
 *  
 *  com.sap.mw.jco.JCO
 *  com.sap.mw.jco.JCO$Client
 *  com.sap.mw.jco.JCO$Pool
 *  com.weaver.integration.datesource.SAPInterationDateSourceUtil
 *  com.weaver.integration.log.LogInfo
 *  weaver.general.BaseBean
 */
package com.weaver.integration.datesource;

import com.sap.mw.jco.JCO;
import com.weaver.integration.datesource.InterationDataSource;
import com.weaver.integration.datesource.SAPInterationBean;
import com.weaver.integration.datesource.SAPInterationDateSourceUtil;
import com.weaver.integration.log.LogInfo;
import java.util.Properties;
import weaver.general.BaseBean;

public class SAPInterationDateSourceImpl
extends BaseBean
implements InterationDataSource {
    @Override
    public Object getConnection(String string, LogInfo logInfo) {
        JCO.Client client = null;
        boolean bl = false;
        SAPInterationBean sAPInterationBean = SAPInterationDateSourceUtil.getSAPBean((String)string);
        JCO.Pool pool = this.getPoolById(sAPInterationBean.getId());
        bl = pool == null ? this.createPoolByBean(sAPInterationBean) : true;
        if (!bl) {
            logInfo.setClientMessage("0");
        } else {
            logInfo.setClientMessage("1");
        }
        client = JCO.getClient((String)sAPInterationBean.getId());
        return client;
    }

    public void releaseC(JCO.Client client) {
        if (client != null) {
            JCO.releaseClient((JCO.Client)client);
        }
    }

    public String getNumberUsered(String string) {
        JCO.Pool pool = this.getPoolById(string);
        if (pool != null) {
            String string2 = "" + pool.getNumUsed() + "";
            return string2;
        }
        return "00";
    }

    public String getCurrentWaitThreads(String string) {
        JCO.Pool pool = this.getPoolById(string);
        if (pool != null) {
            String string2 = "" + pool.getNumWaitingThreads() + "";
            return string2;
        }
        return "00";
    }

    public JCO.Pool getPoolById(String string) {
        JCO.Pool pool = JCO.getClientPoolManager().getPool(string);
        return pool;
    }

    public boolean createPoolByBean(SAPInterationBean sAPInterationBean) {
        boolean bl = false;
        try {
            String string = sAPInterationBean.getId();
            String string2 = sAPInterationBean.getHostname();
            String string3 = sAPInterationBean.getSystemNum();
            String string4 = sAPInterationBean.getSapRouter();
            String string5 = sAPInterationBean.getClient();
            String string6 = sAPInterationBean.getLanguage();
            String string7 = sAPInterationBean.getUsername();
            String string8 = sAPInterationBean.getPassword();
            Properties properties = new Properties();
            properties.setProperty("jco.client.lang", string6);
            properties.setProperty("jco.client.client", string5);
            properties.setProperty("jco.client.passwd", string8);
            properties.setProperty("jco.client.sysnr", string3);
            if (!"".equals(string4)) {
                string2 = string4 + string2;
            }
            properties.setProperty("jco.client.ashost", string2);
            properties.setProperty("jco.client.user", string7);
            JCO.addClientPool((String)string, (int)99, (Properties)properties);
            bl = true;
        }
        catch (Exception var3_4) {
            // empty catch block
        }
        return bl;
    }
}