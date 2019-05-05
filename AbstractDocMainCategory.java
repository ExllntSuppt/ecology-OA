/*
 *  
 * 
 *  
 *  com.weaver.ecology.search.model.base.AbstractBaseBean
 */
package com.weaver.ecology.search.model.base;

import com.weaver.ecology.search.model.base.AbstractBaseBean;

public abstract class AbstractDocMainCategory
extends AbstractBaseBean {
    private Integer id;
    private String categoryname;
    private Integer categoryiconid;
    private Integer categoryorder;
    private String coder;
    private Integer norepeatedname;

    public Integer getCategoryiconid() {
        return this.categoryiconid;
    }

    public void setCategoryiconid(Integer n) {
        this.categoryiconid = n;
    }

    public String getCategoryname() {
        return this.categoryname;
    }

    public void setCategoryname(String string) {
        this.categoryname = string;
    }

    public Integer getCategoryorder() {
        return this.categoryorder;
    }

    public void setCategoryorder(Integer n) {
        this.categoryorder = n;
    }

    public String getCoder() {
        return this.coder;
    }

    public void setCoder(String string) {
        this.coder = string;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer n) {
        this.id = n;
    }

    public Integer getNorepeatedname() {
        return this.norepeatedname;
    }

    public void setNorepeatedname(Integer n) {
        this.norepeatedname = n;
    }
}