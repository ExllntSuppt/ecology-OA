/*
 *  
 * 
 *  
 *  weaver.general.BaseBean
 */
package com.weaver.integration.params;

import weaver.general.BaseBean;

public class ServiceParamModeDisBean
extends BaseBean {
    private String id = "";
    private String servId = "";
    private String paramModeId = "";
    private String paramType = "";
    private String paramName = "";
    private String paramDesc = "";
    private boolean isCompSty = false;
    private String compStyTypeName = "";
    private String compstyname = "";
    private boolean paramIsMust = false;
    private String paramCons = "";
    private String paramOrderNum = "";

    public String getId() {
        return this.id;
    }

    public void setId(String string) {
        this.id = string;
    }

    public String getServId() {
        return this.servId;
    }

    public void setServId(String string) {
        this.servId = string;
    }

    public String getParamModeId() {
        return this.paramModeId;
    }

    public void setParamModeId(String string) {
        this.paramModeId = string;
    }

    public String getParamType() {
        return this.paramType;
    }

    public void setParamType(String string) {
        this.paramType = string;
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

    public boolean isCompSty() {
        return this.isCompSty;
    }

    public void setCompSty(boolean bl) {
        this.isCompSty = bl;
    }

    public String getCompStyTypeName() {
        return this.compStyTypeName;
    }

    public void setCompStyTypeName(String string) {
        this.compStyTypeName = string;
    }

    public String getCompstyname() {
        return this.compstyname;
    }

    public void setCompstyname(String string) {
        this.compstyname = string;
    }

    public boolean isParamIsMust() {
        return this.paramIsMust;
    }

    public void setParamIsMust(boolean bl) {
        this.paramIsMust = bl;
    }

    public String getParamCons() {
        return this.paramCons;
    }

    public void setParamCons(String string) {
        this.paramCons = string;
    }

    public String getParamOrderNum() {
        return this.paramOrderNum;
    }

    public void setParamOrderNum(String string) {
        this.paramOrderNum = string;
    }
}