/*
 *  
 * 
 *  
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  weaver.WorkPlan.WorkPlanViewer
 *  weaver.apply.ApplyUserVO
 *  weaver.conn.RecordSet
 *  weaver.crm.CrmViewer
 *  weaver.crm.Maint.CustomerInfoComInfo
 *  weaver.crm.data.CustomerModifyLog
 *  weaver.general.Util
 *  weaver.hrm.User
 *  weaver.hrm.company.DepartmentComInfo
 *  weaver.hrm.resource.ResourceComInfo
 */
package weaver.apply;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import weaver.WorkPlan.WorkPlanViewer;
import weaver.apply.ApplyUser;
import weaver.apply.ApplyUserVO;
import weaver.conn.RecordSet;
import weaver.crm.CrmViewer;
import weaver.crm.Maint.CustomerInfoComInfo;
import weaver.crm.data.CustomerModifyLog;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.resource.ResourceComInfo;

public class ApplyUserDAO {
    RecordSet rs = new RecordSet();
    User user = new User();
    WorkPlanViewer workPlanViewer = new WorkPlanViewer();
    CrmViewer crmViewer = new CrmViewer();
    HttpServletRequest request = null;
    HttpServletResponse response = null;

    public ApplyUserDAO(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.request = httpServletRequest;
        this.response = httpServletResponse;
    }

    public synchronized void insertApplyUser(String[] arrstring) {
        for (int i = 0; i < arrstring.length; ++i) {
            Date date = new Date();
            long l = date.getTime();
            Timestamp timestamp = new Timestamp(l);
            String string = timestamp.toString().substring(0, 4) + "-" + timestamp.toString().substring(5, 7) + "-" + timestamp.toString().substring(8, 10);
            String string2 = timestamp.toString().substring(11, 13) + ":" + timestamp.toString().substring(14, 16) + ":" + timestamp.toString().substring(17, 19);
            boolean bl = false;
            ApplyUser applyUser = new ApplyUser();
            ApplyUserVO applyUserVO = new ApplyUserVO();
            applyUserVO = applyUser.getApplyUser(arrstring[i]);
            String string3 = applyUserVO.getName();
            String string4 = applyUserVO.getAreacode();
            String string5 = applyUserVO.getTelephone();
            String string6 = applyUserVO.getFax();
            String string7 = applyUserVO.getEmail();
            String string8 = applyUserVO.getWeb();
            String string9 = applyUserVO.getCompany();
            String string10 = applyUserVO.getTitle();
            String string11 = applyUserVO.getIndustry_sector();
            String string12 = applyUserVO.getAddress();
            String string13 = applyUserVO.getType();
            String string14 = applyUserVO.getProvince();
            String string15 = applyUserVO.getCity();
            String string16 = applyUserVO.getCompanyEn();
            String string17 = applyUserVO.getZipcode();
            String string18 = applyUserVO.getCustomerDesc();
            String string19 = applyUserVO.getCustomerSize();
            String string20 = applyUserVO.getStartDate();
            String string21 = applyUserVO.getNameTitle();
            String string22 = applyUserVO.getMobile();
            String string23 = applyUserVO.getEmail2();
            String string24 = applyUserVO.getRemark();
            String string25 = Util.fromScreen2((String)this.request.getParameter("resourceid"), (int)this.user.getLanguage());
            ResourceComInfo resourceComInfo = null;
            try {
                resourceComInfo = new ResourceComInfo();
            }
            catch (Exception var36_36) {
                var36_36.printStackTrace();
            }
            String string26 = resourceComInfo.getDepartmentID(string25);
            DepartmentComInfo departmentComInfo = null;
            try {
                departmentComInfo = new DepartmentComInfo();
            }
            catch (Exception var38_39) {
                var38_39.printStackTrace();
            }
            String string27 = departmentComInfo.getSubcompanyid1(string26);
            if (string13.equals("0")) {
                string10 = string10.equals("GeneralAdmin") ? "\u603b\u7ecf\u7406\u3001\u8463\u4e8b\u957f\uff0cCxO" : (string10.equals("MarketManager") ? "\u5e02\u573a\u90e8\u7ecf\u7406" : (string10.equals("SalesManager") ? "\u9500\u552e\u90e8\u7ecf\u7406" : (string10.equals("ExecutiveManager") ? "\u884c\u653f\u4e3b\u7ba1" : (string10.equals("HRManager") ? "\u4eba\u4e8b\u4e3b\u7ba1" : (string10.equals("FinanceManager") ? "\u8d22\u52a1\u4e3b\u7ba1" : (string10.equals("TechnicalManager") ? "\u6280\u672f\u4e3b\u7ba1" : (string10.equals("Engineer") ? "\u5de5\u7a0b\u5e08" : (string10.equals("Clerk") ? "\u666e\u901a\u804c\u5458" : (string10.equals("Retired") ? "\u9000\u4f11" : "\u5176\u4ed6")))))))));
            }
            if (string13.equals("0")) {
                string5 = string4 + "-" + string5;
            }
            if (string12.equals("")) {
                string12 = "---";
            }
            String string28 = "select id from HrmProvince where provincename like '%" + string14 + "%'";
            string14 = "0";
            this.rs.executeSql(string28);
            if (this.rs.next()) {
                string14 = this.rs.getString("id");
            }
            string28 = "select id from HrmCity where cityname like '%" + string15 + "%'";
            string15 = "0";
            this.rs.executeSql(string28);
            if (this.rs.next()) {
                string15 = this.rs.getString("id");
            }
            if (!(bl = this.rs.executeSql(string28 = "insert into crm_customerinfo (name,language,engname,address1,zipcode,city,province,phone,fax,email,website,source,sector,description,size_n,manager,department,subcompanyid1,type,status,deleted,createdate) values('" + string9 + "',7,'" + string16 + "','" + string12 + "','" + string17 + "'," + string15 + "," + string14 + ",'" + string5 + "','" + string6 + "','" + string7 + "','" + string8 + "',8,16," + string18 + "," + string19 + "," + string25 + "," + string26 + "," + string27 + ",1,1,0,'" + string + "')"))) {
                try {
                    this.response.sendRedirect("applyerror.jsp?sql=" + string28);
                }
                catch (IOException var40_42) {
                    var40_42.printStackTrace();
                }
                return;
            }
            string28 = "select max(id) from crm_customerinfo";
            this.rs.executeSql(string28);
            this.rs.next();
            String string29 = this.rs.getString(1);
            CustomerModifyLog customerModifyLog = new CustomerModifyLog();
            customerModifyLog.modify(string29, "-1", string25);
            string3 = Util.fromScreen2((String)string3, (int)this.user.getLanguage());
            if (string3.equals("")) {
                string3 = "Sir";
            }
            if (!(bl = this.rs.executeSql(string28 = "insert into crm_customercontacter (customerid,title,fullname,lastname,firstname,jobtitle,email,phoneoffice,manager,main,language,mobilephone) values(" + string29 + "," + string21 + ",'" + string3 + "','" + string3 + "','" + string3 + "','" + string10 + "','" + string23 + "','" + string5 + "'," + string25 + ",1,7,'" + string22 + "')"))) {
                this.rs.executeSql("delete from crm_customerinfo where id = " + string29);
                try {
                    this.response.sendRedirect("applyerror.jsp?sql=" + string28);
                }
                catch (IOException var42_45) {
                    var42_45.printStackTrace();
                }
                return;
            }
            CustomerInfoComInfo customerInfoComInfo = null;
            try {
                customerInfoComInfo = new CustomerInfoComInfo();
            }
            catch (Exception var43_47) {
                var43_47.printStackTrace();
            }
            customerInfoComInfo.addCustomerInfoCache(string29);
            String string30 = "" + this.user.getUID() + "";
            String string31 = this.request.getRemoteAddr();
            String string32 = "";
            char c = '\u0002';
            string32 = string29;
            string32 = string32 + c + "n";
            string32 = string32 + c + "0";
            string32 = string32 + c + "";
            string32 = string32 + c + string;
            string32 = string32 + c + string2;
            string32 = string32 + c + string30;
            string32 = string32 + c + "1";
            string32 = string32 + c + string31;
            this.rs.executeProc("CRM_Log_Insert", string32);
            String string33 = string9 + "-\u5ba2\u6237\u8054\u7cfb";
            char c2 = Util.getSeparator();
            String string34 = "3" + c2 + string9 + "-\u5ba2\u6237\u8054\u7cfb" + c2 + string25 + c2 + string + c2 + "" + c2 + "" + c2 + "" + c2 + string24 + c2 + "0" + c2 + "0" + c2 + string29 + c2 + "0" + c2 + "0" + c2 + "2" + c2 + "1" + c2 + "0" + c2 + string25 + c2 + string + c2 + string2 + c2 + "0" + c2 + "0" + c2 + "0" + c2 + "0";
            this.rs.executeProc("WorkPlan_Insert", string34);
            if (this.rs.next()) {
                try {
                    this.workPlanViewer.setWorkPlanShareById(this.rs.getString(1));
                }
                catch (Exception var50_55) {
                    var50_55.printStackTrace();
                }
            }
            string32 = string29;
            string32 = string32 + c + "1";
            string32 = string32 + c + "30";
            string32 = string32 + c + "0";
            this.rs.executeProc("CRM_ContacterLog_R_Insert", string32);
            string28 = "delete from applyuser where id=" + arrstring[i];
            this.rs.executeSql(string28);
            try {
                this.crmViewer.setCrmShareByCrm("" + string29);
                continue;
            }
            catch (Exception var50_56) {
                var50_56.printStackTrace();
            }
        }
    }
}