/*
 *  
 * 
 *  
 *  org.apache.commons.configuration.Configuration
 *  org.apache.commons.configuration.XMLConfiguration
 *  weaver.general.StaticObj
 *  weaver.page.PageCominfo
 *  weaver.page.PageManager
 */
package weaver.admincenter.homepage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.XMLConfiguration;
import weaver.general.StaticObj;
import weaver.page.PageCominfo;
import weaver.page.PageManager;

public class ElementCustomCominfo {
    private PageCominfo pc = new PageCominfo();
    private PageManager pm = new PageManager();
    private StaticObj staticObj = null;
    private String elementCustom = "";
    private int array_size = 0;
    private int current_index = -1;
    private ArrayList idList = null;
    private ArrayList titleList = null;
    private ArrayList titleENList = null;
    private ArrayList descList = null;
    private ArrayList iconList = null;
    private ArrayList linkModeList = null;
    private ArrayList loginViewList = null;
    private ArrayList perpageList = null;
    private ArrayList typeList = null;
    private ArrayList viewUrlList = null;
    private ArrayList viewMethodList = null;
    private ArrayList settingUrlList = null;
    private ArrayList settingMethodList = null;
    private ArrayList operationList = null;
    private ArrayList moreUrlList = null;
    private ArrayList moreMethodList = null;
    private ArrayList privateJsList = null;
    private ArrayList displayList = null;
    private ArrayList settingList = null;
    private ArrayList pathList = null;

    public ElementCustomCominfo() {
        if (this.pc.getConfig() != null) {
            this.elementCustom = this.pc.getConfig().getString("element.customPath");
        }
        this.staticObj = StaticObj.getInstance();
        this.getElementCustomCominfo();
        this.array_size = this.idList.size();
    }

    public int getCount() {
        return this.array_size;
    }

    public void setTofirstRow() {
        this.current_index = -1;
    }

    public boolean next() {
        if (this.current_index + 1 < this.array_size) {
            ++this.current_index;
            return true;
        }
        this.current_index = -1;
        return false;
    }

    public String getId() {
        return (String)this.idList.get(this.current_index);
    }

    public String getTitle() {
        return (String)this.titleList.get(this.current_index);
    }

    public String getTitle(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.titleList.get(n);
        }
        return "";
    }

    public String getTitleEN() {
        return (String)this.titleENList.get(this.current_index);
    }

    public String getTitleEN(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.titleENList.get(n);
        }
        return "";
    }

    public String getDesc() {
        return (String)this.descList.get(this.current_index);
    }

    public String getDesc(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.descList.get(n);
        }
        return "";
    }

    public String getIcon() {
        return (String)this.iconList.get(this.current_index);
    }

    public String getIcon(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.iconList.get(n);
        }
        return "";
    }

    public String getLinkMode() {
        return (String)this.linkModeList.get(this.current_index);
    }

    public String getLinkMode(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.linkModeList.get(n);
        }
        return "";
    }

    public String getLoginView() {
        return (String)this.loginViewList.get(this.current_index);
    }

    public String getLoginView(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.loginViewList.get(n);
        }
        return "";
    }

    public String getType() {
        return (String)this.typeList.get(this.current_index);
    }

    public String getType(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.typeList.get(n);
        }
        return "";
    }

    public String getPerpage() {
        return (String)this.perpageList.get(this.current_index);
    }

    public String getPerpage(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.perpageList.get(n);
        }
        return "";
    }

    public String getMoreUrl() {
        return (String)this.moreUrlList.get(this.current_index);
    }

    public String getMoreUrl(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.moreUrlList.get(n);
        }
        return "";
    }

    public String getMoreMethod() {
        return (String)this.moreMethodList.get(this.current_index);
    }

    public String getMoreMethod(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.moreMethodList.get(n);
        }
        return "";
    }

    public String getView() {
        return (String)this.viewUrlList.get(this.current_index);
    }

    public String getView(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.viewUrlList.get(n);
        }
        return "";
    }

    public String getViewMethod() {
        return (String)this.viewMethodList.get(this.current_index);
    }

    public String getViewMethod(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.viewMethodList.get(n);
        }
        return "";
    }

    public String getSetting() {
        return (String)this.settingList.get(this.current_index);
    }

    public String getSetting(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.settingList.get(n);
        }
        return "";
    }

    public String getSettingUrl() {
        return (String)this.settingUrlList.get(this.current_index);
    }

    public String getSettingUrl(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.settingUrlList.get(n);
        }
        return "";
    }

    public String getSettingMethod() {
        return (String)this.settingMethodList.get(this.current_index);
    }

    public String getSettingMethod(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.settingMethodList.get(n);
        }
        return "";
    }

    public String getOperation() {
        return (String)this.operationList.get(this.current_index);
    }

    public String getOperation(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.operationList.get(n);
        }
        return "";
    }

    public List getPrivateJsList() {
        return (List)this.privateJsList.get(this.current_index);
    }

    public List getPrivateJsList(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (List)this.privateJsList.get(n);
        }
        return new ArrayList();
    }

    public List getDisplayList() {
        return (List)this.displayList.get(this.current_index);
    }

    public List getDisplayList(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (List)this.displayList.get(n);
        }
        return null;
    }

    public List getSettingList() {
        return (List)this.settingList.get(this.current_index);
    }

    public List getSettingList(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (List)this.settingList.get(n);
        }
        return null;
    }

    public String getPath() {
        return (String)this.pathList.get(this.current_index);
    }

    public String getPath(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            return (String)this.pathList.get(n);
        }
        return "";
    }

    private void getElementCustomCominfo() {
        if (this.staticObj.getObject("ElementCustomCominfo") == null || this.pc.isDebug()) {
            this.setElementBaseCominfo();
        } else {
            this.idList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "idList");
            this.titleList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "titleList");
            this.titleENList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "titleENList");
            this.descList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "descList");
            this.iconList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "iconList");
            this.linkModeList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "linkModeList");
            this.loginViewList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "loginViewList");
            this.typeList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "typeList");
            this.perpageList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "perpageList");
            this.viewUrlList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "viewUrlList");
            this.viewMethodList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "viewMethodList");
            this.settingUrlList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "settingUrlList");
            this.settingMethodList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "settingMethodList");
            this.operationList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "operationList");
            this.moreUrlList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "moreUrlList");
            this.moreMethodList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "moreMethodList");
            this.privateJsList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "privateJsList");
            this.displayList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "displayList");
            this.settingList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "settingList");
            this.pathList = (ArrayList)this.staticObj.getRecordFromObj("ElementCustomCominfo", "pathList");
        }
    }

    public void setElementBaseCominfo() {
        this.init();
        ArrayList arrayList = this.pm.getElementConfList(this.elementCustom);
        for (int i = 0; i < arrayList.size(); ++i) {
            XMLConfiguration xMLConfiguration = (XMLConfiguration)arrayList.get(i);
            this.addCache(xMLConfiguration);
        }
        this.staticObj.putRecordToObj("ElementCustomCominfo", "idList", (Object)this.idList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "titleList", (Object)this.titleList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "titleENList", (Object)this.titleENList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "descList", (Object)this.descList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "iconList", (Object)this.iconList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "linkModeList", (Object)this.linkModeList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "loginViewList", (Object)this.loginViewList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "typeList", (Object)this.typeList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "perpageList", (Object)this.perpageList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "viewUrlList", (Object)this.viewUrlList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "viewMethodList", (Object)this.viewMethodList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "settingUrlList", (Object)this.settingUrlList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "settingMethodList", (Object)this.settingMethodList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "operationList", (Object)this.operationList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "moreUrlList", (Object)this.moreUrlList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "moreMethodList", (Object)this.moreMethodList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "privateJsList", (Object)this.privateJsList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "displayList", (Object)this.displayList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "settingList", (Object)this.settingList);
        this.staticObj.putRecordToObj("ElementCustomCominfo", "pathList", (Object)this.pathList);
    }

    private void addCache(XMLConfiguration xMLConfiguration) {
        String string = this.elementCustom + xMLConfiguration.getFile().getParentFile().getName() + "/";
        this.idList.add(xMLConfiguration.getString("id"));
        this.titleList.add(xMLConfiguration.getString("title"));
        this.titleENList.add(xMLConfiguration.getString("titleEN"));
        this.descList.add(xMLConfiguration.getString("desc"));
        this.iconList.add(string + xMLConfiguration.getString("icon"));
        this.linkModeList.add(xMLConfiguration.getString("linkMode"));
        this.loginViewList.add(xMLConfiguration.getString("loginView"));
        this.typeList.add(xMLConfiguration.getString("type"));
        this.perpageList.add(xMLConfiguration.getString("perpage"));
        this.viewUrlList.add(this.getWebPath(string, xMLConfiguration.getString("view.url")));
        this.viewMethodList.add(xMLConfiguration.getString("view.method"));
        this.settingUrlList.add(this.getWebPath(string, xMLConfiguration.getString("setting.url")));
        this.settingMethodList.add(xMLConfiguration.getString("setting.method"));
        this.operationList.add(this.getWebPath(string, xMLConfiguration.getString("operation")));
        this.moreUrlList.add(this.getWebPath(string, xMLConfiguration.getString("more.url")));
        this.moreMethodList.add(xMLConfiguration.getString("more.method"));
        List list = xMLConfiguration.getList("private.jsList");
        for (int i = 0; i < list.size(); ++i) {
            list.set(i, string + list.get(i));
        }
        this.privateJsList.add(list);
        List list2 = xMLConfiguration.getList("displayfield.item.showtitle");
        for (int j = 0; j < list2.size(); ++j) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("showtitle", xMLConfiguration.getString("displayfield.item(" + j + ").showtitle"));
            hashMap.put("showtype", xMLConfiguration.getString("displayfield.item(" + j + ").showtype"));
            hashMap.put("showname", xMLConfiguration.getString("displayfield.item(" + j + ").showname"));
            hashMap.put("showdatatype", xMLConfiguration.getString("displayfield.item(" + j + ").showdatatype"));
            hashMap.put("showdatalength", xMLConfiguration.getString("displayfield.item(" + j + ").showdatalength"));
            list2.set(j, hashMap);
        }
        this.displayList.add(list2);
        List list3 = xMLConfiguration.getList("settingfield.item.settingtitle");
        for (int k = 0; k < list3.size(); ++k) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("settingtitle", xMLConfiguration.getString("settingfield.item(" + k + ").settingtitle"));
            hashMap.put("settingtype", xMLConfiguration.getString("settingfield.item(" + k + ").settingtype"));
            hashMap.put("settingname", xMLConfiguration.getString("settingfield.item(" + k + ").settingname"));
            hashMap.put("settingdatatype", xMLConfiguration.getString("settingfield.item(" + k + ").settingdatatype"));
            hashMap.put("settingdatalength", xMLConfiguration.getString("settingfield.item(" + k + ").settingdatalength"));
            list3.set(k, hashMap);
        }
        this.settingList.add(list3);
        this.pathList.add(string);
    }

    private String getWebPath(String string, String string2) {
        if (!(string2.startsWith("/") || string2.equals("") || string2.equals("#"))) {
            return string + string2;
        }
        return string2;
    }

    private void init() {
        if (this.idList != null) {
            this.idList.clear();
        } else {
            this.idList = new ArrayList();
        }
        if (this.titleList != null) {
            this.titleList.clear();
        } else {
            this.titleList = new ArrayList();
        }
        if (this.titleENList != null) {
            this.titleENList.clear();
        } else {
            this.titleENList = new ArrayList();
        }
        if (this.descList != null) {
            this.descList.clear();
        } else {
            this.descList = new ArrayList();
        }
        if (this.iconList != null) {
            this.iconList.clear();
        } else {
            this.iconList = new ArrayList();
        }
        if (this.linkModeList != null) {
            this.linkModeList.clear();
        } else {
            this.linkModeList = new ArrayList();
        }
        if (this.loginViewList != null) {
            this.loginViewList.clear();
        } else {
            this.loginViewList = new ArrayList();
        }
        if (this.typeList != null) {
            this.typeList.clear();
        } else {
            this.typeList = new ArrayList();
        }
        if (this.perpageList != null) {
            this.perpageList.clear();
        } else {
            this.perpageList = new ArrayList();
        }
        if (this.viewUrlList != null) {
            this.viewUrlList.clear();
        } else {
            this.viewUrlList = new ArrayList();
        }
        if (this.viewMethodList != null) {
            this.viewMethodList.clear();
        } else {
            this.viewMethodList = new ArrayList();
        }
        if (this.settingUrlList != null) {
            this.settingUrlList.clear();
        } else {
            this.settingUrlList = new ArrayList();
        }
        if (this.settingMethodList != null) {
            this.settingMethodList.clear();
        } else {
            this.settingMethodList = new ArrayList();
        }
        if (this.operationList != null) {
            this.operationList.clear();
        } else {
            this.operationList = new ArrayList();
        }
        if (this.moreUrlList != null) {
            this.moreUrlList.clear();
        } else {
            this.moreUrlList = new ArrayList();
        }
        if (this.moreMethodList != null) {
            this.moreMethodList.clear();
        } else {
            this.moreMethodList = new ArrayList();
        }
        if (this.privateJsList != null) {
            this.privateJsList.clear();
        } else {
            this.privateJsList = new ArrayList();
        }
        if (this.pathList != null) {
            this.pathList.clear();
        } else {
            this.pathList = new ArrayList();
        }
        if (this.displayList != null) {
            this.displayList.clear();
        } else {
            this.displayList = new ArrayList();
        }
        if (this.settingList != null) {
            this.settingList.clear();
        } else {
            this.settingList = new ArrayList();
        }
    }

    public void removeCache(String string) {
        int n = this.idList.indexOf(string);
        if (n != -1) {
            this.idList.remove(n);
            this.titleList.remove(n);
            this.titleENList.remove(n);
            this.descList.remove(n);
            this.iconList.remove(n);
            this.linkModeList.remove(n);
            this.loginViewList.remove(n);
            this.typeList.remove(n);
            this.perpageList.remove(n);
            this.viewUrlList.remove(n);
            this.viewMethodList.remove(n);
            this.settingList.remove(n);
            this.settingMethodList.remove(n);
            this.operationList.remove(n);
            this.moreUrlList.remove(n);
            this.moreMethodList.remove(n);
            this.privateJsList.remove(n);
            this.displayList.remove(n);
            this.settingList.remove(n);
            this.pathList.remove(n);
        }
    }
}