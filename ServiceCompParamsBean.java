/*
 *  
 * 
 *  
 *  weaver.general.BaseBean
 */
package com.weaver.integration.params;

import weaver.general.BaseBean;

public class ServiceCompParamsBean
extends BaseBean {
    private String id = "";
    private String paramId = "";
    private String paramName = "";
    private String paramDesc = "";

    public String getId() {
        return this.id;
    }

    public void setId(String string) {
        this.id = string;
    }

    public String getParamId() {
        return this.paramId;
    }

    public void setParamId(String string) {
        this.paramId = string;
    }

    public String getParamName() {
        return this.paramName;
    }

    public void setParamName(String string) {
        this.paramName = string;
    }

    public String getParamDesc() {
        return this.paramDesc;
    }

    public void setParamDesc(String string) {
        this.paramDesc = string;
    }
}