/*
 *  
 * 
 *  
 *  weaver.general.BaseBean
 */
package com.weaver.integration.params;

import weaver.general.BaseBean;

public class ServiceParamModeBean
extends BaseBean {
    private String id = "";
    private String paramModeName = "";
    private String servId = "";

    public String getId() {
        return this.id;
    }

    public void setId(String string) {
        this.id = string;
    }

    public String getParamModeName() {
        return this.paramModeName;
    }

    public void setParamModeName(String string) {
        this.paramModeName = string;
    }

    public String getServId() {
        return this.servId;
    }

    public void setServId(String string) {
        this.servId = string;
    }
}