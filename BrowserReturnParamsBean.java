/*
 *  
 * 
 *  
 *  weaver.general.BaseBean
 */
package com.weaver.integration.params;

import java.util.HashMap;
import java.util.Map;
import weaver.general.BaseBean;

public class BrowserReturnParamsBean
extends BaseBean {
    private Map strMap = new HashMap();
    private Map structMap = new HashMap();
    private Map tableMap = new HashMap();
    private int maxlogid;

    public Map getStrMap() {
        return this.strMap;
    }

    public void setStrMap(Map map) {
        this.strMap = map;
    }

    public Map getStructMap() {
        return this.structMap;
    }

    public void setStructMap(Map map) {
        this.structMap = map;
    }

    public Map getTableMap() {
        return this.tableMap;
    }

    public void setTableMap(Map map) {
        this.tableMap = map;
    }

    public int getMaxlogid() {
        return this.maxlogid;
    }

    public void setMaxlogid(int n) {
        this.maxlogid = n;
    }
}