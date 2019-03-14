/*
 *  
 * 
 *  
 *  com.simplerss.dataobject.Cloud
 */
package com.simplerss.handler;

import com.simplerss.dataobject.Cloud;
import com.simplerss.handler.ChainedHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class CloudHandler
extends ChainedHandler {
    Cloud cloud = null;
    String currentTag = null;

    public CloudHandler(ChainedHandler chainedHandler) {
        super(chainedHandler);
    }

    @Override
    public void startHandlingEvents(String string, Attributes attributes) throws SAXException {
        super.startHandlingEvents(string, attributes);
        this.cloud = new Cloud();
        this.currentTag = string;
    }

    @Override
    public void endElement(String string, String string2, String string3) throws SAXException {
        String string4 = string3.toLowerCase();
        if ("domain".equals(string4)) {
            this.cloud.setDomain(this.mText);
        } else if ("path".equals(string4)) {
            this.cloud.setPath(this.mText);
        } else if ("port".equals(string4)) {
            this.cloud.setPort(this.mText);
        } else if ("protocol".equals(string4)) {
            this.cloud.setProtocol(this.mText);
        } else if ("registerprocedure".equals(string4)) {
            this.cloud.setRegisterProcedure(this.mText);
        } else if (this.currentTag.equals(string4)) {
            this.mParent.setAttribute(this.currentTag, (Object)this.cloud);
            this.stopHandlingEvents();
        }
    }
}