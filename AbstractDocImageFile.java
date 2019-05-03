/*
 *  
 * 
 *  
 *  com.weaver.ecology.search.model.base.AbstractBaseBean
 */
package com.weaver.ecology.search.model.base;

import com.weaver.ecology.search.model.base.AbstractBaseBean;

public abstract class AbstractDocImageFile
extends AbstractBaseBean {
    private Integer id;
    private Integer docid;
    private Integer imagefileid;
    private String imagefilename;
    private String imagefiledesc;
    private Short imagefilewidth;
    private Short imagefileheight;
    private Short imagefielsize;
    private String docfiletype;
    private Integer versionId;
    private String versionDetail;

    public String getDocfiletype() {
        return this.docfiletype;
    }

    public void setDocfiletype(String string) {
        this.docfiletype = string;
    }

    public Integer getDocid() {
        return this.docid;
    }

    public void setDocid(Integer n) {
        this.docid = n;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer n) {
        this.id = n;
    }

    public Short getImagefielsize() {
        return this.imagefielsize;
    }

    public void setImagefielsize(Short s) {
        this.imagefielsize = s;
    }

    public String getImagefiledesc() {
        return this.imagefiledesc;
    }

    public void setImagefiledesc(String string) {
        this.imagefiledesc = string;
    }

    public Short getImagefileheight() {
        return this.imagefileheight;
    }

    public void setImagefileheight(Short s) {
        this.imagefileheight = s;
    }

    public Integer getImagefileid() {
        return this.imagefileid;
    }

    public void setImagefileid(Integer n) {
        this.imagefileid = n;
    }

    public String getImagefilename() {
        return this.imagefilename;
    }

    public void setImagefilename(String string) {
        this.imagefilename = string;
    }

    public Short getImagefilewidth() {
        return this.imagefilewidth;
    }

    public void setImagefilewidth(Short s) {
        this.imagefilewidth = s;
    }

    public String getVersionDetail() {
        return this.versionDetail;
    }

    public void setVersionDetail(String string) {
        this.versionDetail = string;
    }

    public Integer getVersionId() {
        return this.versionId;
    }

    public void setVersionId(Integer n) {
        this.versionId = n;
    }
}