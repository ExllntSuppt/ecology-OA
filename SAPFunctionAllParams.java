/*
 *  
 * 
 *  
 *  com.weaver.integration.datesource.SAPFunctionExportParams
 *  com.weaver.integration.datesource.SAPFunctionImportParams
 *  weaver.general.BaseBean
 */
package com.weaver.integration.datesource;

import com.weaver.integration.datesource.SAPFunctionExportParams;
import com.weaver.integration.datesource.SAPFunctionImportParams;
import weaver.general.BaseBean;

public class SAPFunctionAllParams
extends BaseBean {
    private SAPFunctionImportParams sip = null;
    private SAPFunctionExportParams sep = null;

    public SAPFunctionImportParams getSip() {
        return this.sip;
    }

    public void setSip(SAPFunctionImportParams sAPFunctionImportParams) {
        this.sip = sAPFunctionImportParams;
    }

    public SAPFunctionExportParams getSep() {
        return this.sep;
    }

    public void setSep(SAPFunctionExportParams sAPFunctionExportParams) {
        this.sep = sAPFunctionExportParams;
    }
}