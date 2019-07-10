/*
 *  
 * 
 *  
 *  weaver.general.BaseBean
 */
package com.weaver.integration.datesource;

import weaver.general.BaseBean;

public class SAPInterationBean
extends BaseBean {
    private String id = "";
    private String hpid = "";
    private String poolname = "";
    private String hostname = "";
    private String systemNum = "";
    private String sapRouter = "";
    private String client = "";
    private String language = "";
    private String username = "";
    private String password = "";
    private int maxConnNum = 0;
    private String datasourceDes = "";

    public String getHpid() {
        return this.hpid;
    }

    public void setHpid(String string) {
        this.hpid = string;
    }

    public String getDatasourceDes() {
        return this.datasourceDes;
    }

    public void setDatasourceDes(String string) {
        this.datasourceDes = string;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String string) {
        this.id = string;
    }

    public String getPoolname() {
        return this.poolname;
    }

    public void setPoolname(String string) {
        this.poolname = string;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String string) {
        this.hostname = string;
    }

    public String getSystemNum() {
        return this.systemNum;
    }

    public void setSystemNum(String string) {
        this.systemNum = string;
    }

    public String getSapRouter() {
        return this.sapRouter;
    }

    public void setSapRouter(String string) {
        this.sapRouter = string;
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String string) {
        this.client = string;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String string) {
        this.language = string;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String string) {
        this.username = string;
    }

    public int getMaxConnNum() {
        return this.maxConnNum;
    }

    public void setMaxConnNum(int n) {
        this.maxConnNum = n;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String string) {
        this.password = string;
    }
}