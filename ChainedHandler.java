/*
 *  
 * 
 *  
 *  weaver.general.BaseBean
 */
package com.simplerss.handler;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;
import weaver.general.BaseBean;

public class ChainedHandler
extends DefaultHandler {
    public ChainedHandler mParent = null;
    public XMLReader mReader = null;
    public Attributes mAttrs = null;
    public String mHandling = "";
    public String mTag = "";
    public String mText = "";

    public ChainedHandler() {
    }

    public ChainedHandler(XMLReader xMLReader) {
        this();
        this.mReader = xMLReader;
    }

    public ChainedHandler(ChainedHandler chainedHandler) {
        this.mParent = chainedHandler;
        this.setReader(chainedHandler.getReader());
    }

    public void startHandlingEvents() {
        this.mReader.setContentHandler(this);
        this.mReader.setErrorHandler(this);
    }

    public void startHandlingEvents(String string, Attributes attributes) throws SAXException {
        this.mHandling = string;
        this.mTag = string;
        this.mAttrs = new AttributesImpl(attributes);
        this.mText = "";
        this.startHandlingEvents();
    }

    public void stopHandlingEvents() {
        this.mReader.setContentHandler(this.mParent);
        this.mReader.setErrorHandler(this.mParent);
    }

    public void setAttribute(String string, Object object) throws SAXException {
        new BaseBean().writeLog((Object)(this.getClass().getName() + ".setAttribute(" + string + ") must be overridden!"));
    }

    public void setAttribute(String string, Object[] arrobject) throws SAXException {
        new BaseBean().writeLog((Object)(this.getClass().getName() + ".setAttribute(" + string + ") must be overridden!"));
    }

    public String getTag() {
        return this.mTag;
    }

    public String getText() {
        return this.mText;
    }

    public Attributes getAttributes() {
        return this.mAttrs;
    }

    public XMLReader getReader() {
        return this.mReader;
    }

    public void setReader(XMLReader xMLReader) {
        this.mReader = xMLReader;
    }

    public void setParent(ChainedHandler chainedHandler) {
        this.mParent = chainedHandler;
        this.setReader(chainedHandler.getReader());
    }

    @Override
    public void startElement(String string, String string2, String string3, Attributes attributes) throws SAXException {
        this.mTag = string2;
        this.mAttrs = new AttributesImpl(attributes);
        this.mText = "";
    }

    @Override
    public void characters(char[] arrc, int n, int n2) throws SAXException {
        String string = "\n\t\r";
        for (int i = n; i < n + n2 - 1; ++i) {
            if (string.indexOf(arrc[i]) < 0) continue;
            arrc[i] = 32;
        }
        this.mText = this.mText + new String(arrc, n, n2);
    }
}