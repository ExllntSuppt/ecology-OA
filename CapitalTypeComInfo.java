/*
 *  
 * 
 *  
 *  weaver.conn.RecordSet
 *  weaver.general.BaseBean
 *  weaver.general.StaticObj
 */
package weaver.cpt.maintenance;

import java.util.ArrayList;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.StaticObj;

public class CapitalTypeComInfo
extends BaseBean {
    private static Object lock = new Object();
    private ArrayList ids = null;
    private ArrayList names = null;
    private StaticObj staticobj = StaticObj.getInstance();
    private int current_index = -1;
    private int array_size = 0;

    public CapitalTypeComInfo() throws Exception {
        this.getCapitalTypeInfo();
        this.array_size = this.ids.size();
    }

    private void getCapitalTypeInfo() throws Exception {
        Object object = lock;
        synchronized (object) {
            if (this.staticobj.getObject("CapitalTypeInfo") == null) {
                this.setCapitalTypeInfo();
            }
            this.ids = (ArrayList)this.staticobj.getRecordFromObj("CapitalTypeInfo", "ids");
            this.names = (ArrayList)this.staticobj.getRecordFromObj("CapitalTypeInfo", "names");
            if (this.ids == null) {
                this.setCapitalTypeInfo();
            }
        }
    }

    private void setCapitalTypeInfo() throws Exception {
        if (this.ids != null) {
            this.ids.clear();
        } else {
            this.ids = new ArrayList();
        }
        if (this.names != null) {
            this.names.clear();
        } else {
            this.names = new ArrayList();
        }
        RecordSet recordSet = new RecordSet();
        recordSet.executeProc("CptCapitalType_Select", "");
        try {
            while (recordSet.next()) {
                this.ids.add(recordSet.getString(1));
                this.names.add(recordSet.getString("name"));
            }
        }
        catch (Exception var2_2) {
            this.writeLog((Object)var2_2);
            throw var2_2;
        }
        this.staticobj.putRecordToObj("CapitalTypeInfo", "ids", (Object)this.ids);
        this.staticobj.putRecordToObj("CapitalTypeInfo", "names", (Object)this.names);
    }

    public int getCapitalTypeNum() {
        return this.array_size;
    }

    public boolean next() {
        if (this.current_index + 1 < this.array_size) {
            ++this.current_index;
            return true;
        }
        this.current_index = -1;
        return false;
    }

    public boolean next(String string) {
        while (this.current_index + 1 < this.array_size) {
            ++this.current_index;
        }
        if (this.current_index + 1 >= this.array_size) {
            this.current_index = -1;
            return false;
        }
        ++this.current_index;
        return true;
    }

    public void setTofirstRow() {
        this.current_index = -1;
    }

    public String getCapitalTypeid() {
        return (String)this.ids.get(this.current_index);
    }

    public String getCapitalTypename() {
        return (String)this.names.get(this.current_index);
    }

    public String getCapitalTypename(String string) {
        int n = this.ids.indexOf(string);
        if (n != -1) {
            return (String)this.names.get(n);
        }
        return "";
    }

    public void removeCapitalTypeCache() {
        this.staticobj.removeObject("CapitalTypeInfo");
    }
}