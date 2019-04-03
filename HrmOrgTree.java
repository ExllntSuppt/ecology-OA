package weaver.blog;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.email.MailSend;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.hrm.appdetach.AppDetachComInfo;
import weaver.hrm.company.CompanyComInfo;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.company.SubCompanyComInfo;
import weaver.hrm.resource.ResourceComInfo;

public class HrmOrgTree {
	CompanyComInfo companyInfo = null;
	SubCompanyComInfo subCompanyInfo = null;
	SubCompanyComInfo subCompanyInfo2 = null;
	DepartmentComInfo departComInfo = null;
	DepartmentComInfo departComInfo2 = null;
	ResourceComInfo resourceComInfo = null;
	ResourceComInfo resourceComInfo2 = null;
	String treeType = "";
	String ids = "";
	boolean isShowAccount = false;

	private int[] subcomids = null;
	private int[] subcomids1 = null;
	private boolean isUseAppDetach = false;
	private User user = null;

	public HrmOrgTree(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) {
		try {
			user = ((paramHttpServletRequest.getAttribute("user") instanceof User) ? (User) paramHttpServletRequest
					.getAttribute("user") : null);
			String str = (String) paramHttpServletRequest
					.getAttribute("isaccount");
			if ("1".equals(str)) {
				isShowAccount = true;
			}
			AppDetachComInfo localAppDetachComInfo = new AppDetachComInfo();
			isUseAppDetach = localAppDetachComInfo.isUseAppDetach();
			ids = localAppDetachComInfo.getScopeIds(user, "resource");
			if ((ids == null) || ("".equals(ids)))
				isUseAppDetach = false;
			ids = ("," + ids + ",");
			companyInfo = new CompanyComInfo();
			subCompanyInfo = new SubCompanyComInfo();
			subCompanyInfo2 = new SubCompanyComInfo();
			departComInfo = new DepartmentComInfo();
			departComInfo2 = new DepartmentComInfo();
			resourceComInfo = new ResourceComInfo();
			resourceComInfo2 = new ResourceComInfo();
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public JSONArray getTreeData2(String paramString1, String paramString2)
			throws Exception {
		JSONArray localJSONArray1 = new JSONArray();
		String str = "";
		RecordSet localRecordSet = new RecordSet();
		JSONObject localJSONObject1;
		if ("source".equals(paramString1)) {
			localJSONObject1 = new JSONObject(
					"{text:'私人组','id':'0','expanded':true,'classes':'company'}");

			str = "select id,name , (SELECT count(*) FROM HrmGroupMembers WHERE groupid = t1.id AND userid IN (SELECT id FROM HrmResource)) userNum  from HrmGroup t1 where owner="
					+ paramString2 + " and type=0 order by sn asc";

			localRecordSet.executeSql(str);
			JSONArray localJSONArray2 = new JSONArray();
			while (localRecordSet.next()) {
				JSONObject localJSONObject2 = new JSONObject();
				localJSONObject2.put("text", localRecordSet.getString("name"));
				localJSONObject2.put("id", localRecordSet.getString("id"));
				localJSONObject2.put("classes", "department");
				localJSONObject2.put("onclick",
						"openBlog(0,'" + localRecordSet.getString("id") + "','"
								+ localRecordSet.getString("name") + "',this)");
				localJSONObject2.put("ondblclick", "dblclickTree(0,'"
						+ localRecordSet.getString("id") + "','"
						+ localRecordSet.getString("name") + "',this)");
				localJSONObject2.put("hasChildren",
						!localRecordSet.getString("userNum").equals("0"));
				localJSONArray2.put(localJSONObject2);
			}
			localJSONObject1.put("children", localJSONArray2);
			localJSONArray1.put(localJSONObject1);
		} else {
			str = "select userid,(select dsporder from HrmResource where id = t1.userid) order_num from HrmGroupMembers t1 where groupid="
					+ paramString1
					+ " and userid in (select id from HrmResource)"
					+ " order by order_num asc , userid asc";

			localRecordSet.executeSql(str);
			while (localRecordSet.next()) {
				localJSONObject1 = new JSONObject();
				localJSONObject1.put("text", resourceComInfo
						.getLastname(localRecordSet.getString("userid")));
				localJSONObject1.put(
						"id",
						localRecordSet.getString("userid")
								+ "|"
								+ resourceComInfo
										.getDepartmentID(localRecordSet
												.getString("userid")));
				localJSONObject1.put("dpid", resourceComInfo
						.getDepartmentID(localRecordSet.getString("userid")));
				localJSONObject1.put("classes", "person");
				localJSONObject1.put(
						"onclick",
						"dblclickTree(1,'"
								+ localRecordSet.getString("userid")
								+ "|"
								+ resourceComInfo
										.getDepartmentID(localRecordSet
												.getString("userid"))
								+ "','"
								+ resourceComInfo.getLastname(localRecordSet
										.getString("userid")) + "',this)");
				localJSONObject1.put("hasChildren", false);
				localJSONArray1.put(localJSONObject1);
			}
		}

		return localJSONArray1;
	}

	public int getHrmCount(String paramString1, String paramString2)
  {
    int i = 0;
    i += getHrmAllCount(paramString1, paramString2);
    String[] arrayOfString1; String str; if (paramString1.equals("2"))
    {
      arrayOfString1 = getAllSub(paramString2).split(",");
      for (String str1 : arrayOfString1) {
        if ((str1 != null) && (!"".equals(str1))) {
          i += getHrmAllCount("2", str1);
        }
      }
    } else if (paramString1.equals("3"))
    {
      arrayOfString1 = getAllSubDepart(paramString2).split(",");
      for (String str1 : arrayOfString1) {
        if ((str1 != null) && (!"".equals(str1))) {
          i += getHrmAllCount("3", str1);
        }
      }
    }
    

    return i;
  }

	private String getAllSubDepart(String paramString) {
		String str1 = "";
		ArrayList localArrayList = getSubDepartList(paramString);
		if (localArrayList.size() > 0) {
			for (int i = 0; i < localArrayList.size(); i++) {
				String[] arrayOfString = (String[]) localArrayList.get(i);
				String str2 = arrayOfString[0].toString();
				if ((str2 != null) && (!str2.equals("")))
					str1 = str1 + str2 + ",";
				if ("true".equals(arrayOfString[2].toString())) {
					str1 = str1 + getAllSubDepart(str2) + ",";
				}
			}
		}
		return MailSend.trim(str1);
	}

	private String getAllSub(String paramString) {
		String str1 = "";

		ArrayList localArrayList = getSubList(paramString);
		if (localArrayList.size() > 0) {
			for (int i = 0; i < localArrayList.size(); i++) {
				String[] arrayOfString = (String[]) localArrayList.get(i);
				String str2 = arrayOfString[0].toString();
				if ((str2 != null) && (!str2.equals("")))
					str1 = str1 + str2 + ",";
				if ("true".equals(arrayOfString[2].toString())) {
					str1 = str1 + getAllSub(str2) + ",";
				}
			}
		}
		return MailSend.trim(str1);
	}

	public int getHrmAllCount(String paramString1, String paramString2) {
		int i = 0;
		resourceComInfo.setTofirstRow();
		while (resourceComInfo.next()) {
			if (isAccount(resourceComInfo.getLoginID())) {
				String str1 = resourceComInfo.getStatus();
				if ((str1.equals("0")) || (str1.equals("1"))
						|| (str1.equals("2")) || (str1.equals("3")))
					if (isSocpe(resourceComInfo.getResourceid())) {
						String str2 = resourceComInfo.getSubCompanyID();
						String str3 = resourceComInfo.getDepartmentID();
						if ((paramString1.equals("all"))
								|| ((paramString1.equals("2")) && (str2
										.equals(paramString2)))
								|| ((paramString1.equals("3")) && (str3
										.equals(paramString2))))
							i++;
					}
			}
		}
		return i;
	}

	public String getHrmCountStr(String paramString1, String paramString2) {
		if (treeType.equals("email")) {
			return "&nbsp;<span style=\"color:blue\" _orgType=\""
					+ paramString1 + "\" _objid=\"" + paramString2
					+ "\" onclick=\"addResourceMail(this,event)\"  ></span>";
		}
		return "";
	}

	public JSONArray getTreeData(String paramString) throws Exception {
		JSONArray localJSONArray = new JSONArray();
		Object localObject;
		if ("source".equals(paramString)) {
			localObject = new JSONObject("{text:'"
					+ companyInfo.getCompanyname("1")
					+ getHrmCountStr("all", "")
					+ "','id':'0','expanded':true,'classes':'company'}");

			((JSONObject) localObject).put("children", getSubArray("0"));
			localJSONArray.put(localObject);
		} else {
			String[] localObject1 = Util.TokenizerString2(paramString, "|");

			if ("subcompany".equals(localObject1[0])) {
				localJSONArray = getSubArray(localObject1[1]);
			} else if ("department".equals(localObject1[0])) {
				localJSONArray = getSubDepartmentArray(localObject1[1]);
			} else if ("hrm".equals(localObject1[0])) {
				localJSONArray = getSubHrmArray(localObject1[1]);
			}
		}
		return localJSONArray;
	}

	public JSONArray getSubArray(String paramString) throws JSONException {
		JSONArray localJSONArray = new JSONArray();
		AppDetachComInfo localAppDetachComInfo = new AppDetachComInfo(user);

		ArrayList localArrayList1 = getDepartList(paramString);
		Object localObject;
		for (int i = 0; i < localArrayList1.size(); i++) {
			String[] arrayOfString = (String[]) localArrayList1.get(i);
			if ((!localAppDetachComInfo.isUseAppDetach())
					|| (localAppDetachComInfo.checkUserAppDetach(
							arrayOfString[0], "3") != 0)) {
				localObject = new JSONObject();
				((JSONObject) localObject).put("text", arrayOfString[1]
						+ getHrmCountStr("3", arrayOfString[0]));
				((JSONObject) localObject).put("id", "department|"
						+ arrayOfString[0]);
				((JSONObject) localObject).put("classes", "department");

				((JSONObject) localObject).put("onclick", "openBlog("
						+ arrayOfString[0] + ",3)");
				((JSONObject) localObject).put("ondblclick", "dblclickTree("
						+ arrayOfString[0] + ",3)");

				((JSONObject) localObject).put("hasChildren",
						arrayOfString[2].equals("true"));

				localJSONArray.put(localObject);
			}
		}

		ArrayList localArrayList2 = getSubList(paramString);
		for (int j = 0; j < localArrayList2.size(); j++) {
			String[] localObject1 = (String[]) localArrayList2.get(j);
			if ((!localAppDetachComInfo.isUseAppDetach())
					|| (localAppDetachComInfo.checkUserAppDetach(
							localObject1[0], "2") != 0)) {
				JSONObject localJSONObject = new JSONObject();
				localJSONObject.put("text",
						localObject1[1] + getHrmCountStr("2", localObject1[0]));
				localJSONObject.put("classes", "subcompany");
				localJSONObject.put("expanded", false);
				localJSONObject.put("id", "subcompany|" + localObject1[0]);

				localJSONObject.put("onclick", "openBlog(" + localObject1[0]
						+ ",2)");
				localJSONObject.put("ondblclick", "dblclickTree("
						+ localObject1[0] + ",2)");

				localJSONObject.put("hasChildren",
						localObject1[2].equals("true"));

				localJSONArray.put(localJSONObject);
			}
		}
		return localJSONArray;
	}

	public JSONArray getSubDepartmentArray(String paramString)
			throws JSONException {
		JSONArray localJSONArray = new JSONArray();

		ArrayList localArrayList = getSubDepartList(paramString);
		for (int i = 0; i < localArrayList.size(); i++) {
			String[] arrayOfString = (String[]) localArrayList.get(i);
			JSONObject localJSONObject2 = new JSONObject();
			localJSONObject2.put("text",
					arrayOfString[1] + getHrmCountStr("3", arrayOfString[0]));
			localJSONObject2.put("id", "department|" + arrayOfString[0]);
			localJSONObject2.put("classes", "department");

			localJSONObject2.put("onclick", "openBlog(" + arrayOfString[0]
					+ ",3)");
			localJSONObject2.put("ondblclick", "dblclickTree("
					+ arrayOfString[0] + ",3)");

			localJSONObject2
					.put("hasChildren", arrayOfString[2].equals("true"));

			localJSONArray.put(localJSONObject2);
		}

		resourceComInfo.setTofirstRow();
		while (resourceComInfo.next()) {
			if ((isAccount(resourceComInfo.getLoginID()))
					&& (paramString.equals(resourceComInfo.getDepartmentID()))
					&& ((resourceComInfo.getStatus().equals("0"))
							|| (resourceComInfo.getStatus().equals("1"))
							|| (resourceComInfo.getStatus().equals("2")) || (resourceComInfo
								.getStatus().equals("3")))) {
				JSONObject localJSONObject1 = new JSONObject();
				localJSONObject1.put("text", resourceComInfo.getLastname());
				localJSONObject1.put("id",
						"department|" + resourceComInfo.getResourceid());

				localJSONObject1.put("classes", "person");
				localJSONObject1.put("onclick", "dblclickTree("
						+ resourceComInfo.getResourceid() + ",1,this)");
				localJSONObject1.put("hasChildren", false);
				localJSONArray.put(localJSONObject1);
			}
		}

		return localJSONArray;
	}

	public ArrayList getSubList(String paramString) {
		ArrayList localArrayList = new ArrayList();

		subCompanyInfo.setTofirstRow();
		while (subCompanyInfo.next()) {
			String str1 = subCompanyInfo.getSupsubcomid();
			if (str1.equals(""))
				str1 = "0";
			if ((str1.equals(paramString))
					&& (!subCompanyInfo.getCompanyiscanceled().equals("1"))) {
				String str2 = subCompanyInfo.getSubCompanyid();
				String str3 = subCompanyInfo.getSubCompanyname();

				String[] arrayOfString = { str2, str3,
						isHavaSubChildren(str2, subcomids) ? "true" : "false" };
				localArrayList.add(arrayOfString);
			}
		}
		return localArrayList;
	}

	public boolean isHavaSubChildren(String paramString, int[] paramArrayOfInt) {
		boolean bool = false;
		subCompanyInfo2.setTofirstRow();
		String str;
		while (subCompanyInfo2.next()) {
			str = subCompanyInfo2.getSupsubcomid();

			if ((str.equals(paramString))
					&& (!subCompanyInfo2.getCompanyiscanceled().equals("1"))) {
				bool = true;
				break;
			}
		}

		departComInfo2.setTofirstRow();
		while (departComInfo2.next()) {
			str = departComInfo2.getSubcompanyid1();
			if ((str.equals(paramString))
					&& (!departComInfo2.getDeparmentcanceled().equals("1"))) {
				bool = true;
				break;
			}
		}

		return bool;
	}

	public boolean isHavaDepartChildren(String paramString) {
		boolean bool = false;
		departComInfo2.setTofirstRow();
		while (departComInfo2.next()) {
			String str = departComInfo2.getDepartmentsupdepid();
			if ((paramString.equals(str))
					&& (!departComInfo2.getDeparmentcanceled().equals("1"))) {
				bool = true;
				break;
			}
		}
		return bool;
	}

	public ArrayList getDepartList(String paramString) {
		ArrayList localArrayList = new ArrayList();
		departComInfo.setTofirstRow();
		while (departComInfo.next()) {
			String str1 = departComInfo.getSubcompanyid1();
			String str2 = Util.null2String(
					departComInfo.getDepartmentsupdepid(), "0");
			str2 = str2.equals("") ? "0" : str2;

			if ((str1.equals(paramString))
					&& (!departComInfo.getDeparmentcanceled().equals("1"))
					&& (str2.equals("0"))) {

				String str3 = departComInfo.getDepartmentid();
				String str4 = departComInfo.getDepartmentname();

				String str5 = isHavaDepartChildren(str3) ? "true" : "false";
				if (str5.equals("false")) {
					resourceComInfo.setTofirstRow();
					while (resourceComInfo.next()) {
						if (((resourceComInfo.getStatus().equals("0"))
								|| (resourceComInfo.getStatus().equals("1"))
								|| (resourceComInfo.getStatus().equals("2")) || (resourceComInfo
									.getStatus().equals("3")))
								&& (str3.equals(resourceComInfo
										.getDepartmentID()))) {
							str5 = "true";
						}
					}
				}

				String[] arrayOfString = { str3, str4, str5 };
				localArrayList.add(arrayOfString);
			}
		}
		return localArrayList;
	}

	public ArrayList getSubDepartList(String paramString) {
		ArrayList localArrayList = new ArrayList();
		departComInfo.setTofirstRow();
		while (departComInfo.next()) {
			String str1 = departComInfo.getDepartmentsupdepid();
			if ((str1.equals(paramString))
					&& (!departComInfo.getDeparmentcanceled().equals("1"))) {
				String str2 = departComInfo.getDepartmentid();
				String str3 = departComInfo.getDepartmentname();

				String str4 = isHavaDepartChildren(str2) ? "true" : "false";

				if (str4.equals("false")) {
					resourceComInfo.setTofirstRow();
					while (resourceComInfo.next()) {
						if (((resourceComInfo.getStatus().equals("0"))
								|| (resourceComInfo.getStatus().equals("1"))
								|| (resourceComInfo.getStatus().equals("2")) || (resourceComInfo
									.getStatus().equals("3")))
								&& (str2.equals(resourceComInfo
										.getDepartmentID()))) {
							str4 = "true";
						}
					}
				}

				String[] arrayOfString = { str2, str3, str4 };
				localArrayList.add(arrayOfString);
			}
		}

		return localArrayList;
	}

	public boolean isInArray(String paramString, int[] paramArrayOfInt) {
		boolean bool = false;
		for (int i = 0; i < paramArrayOfInt.length; i++) {
			if (paramString.equals(String.valueOf(paramArrayOfInt[i])))
				bool = true;
		}
		return bool;
	}

	public JSONArray getSubHrmArray(String paramString) throws JSONException {
		JSONArray localJSONArray = new JSONArray();

		ArrayList localArrayList = getSubHrmList(paramString);
		for (int i = 0; i < localArrayList.size(); i++) {
			String[] arrayOfString = (String[]) localArrayList.get(i);
			JSONObject localJSONObject = new JSONObject();
			localJSONObject.put("text", arrayOfString[1]);
			localJSONObject.put("id", "hrm|" + arrayOfString[0]);
			localJSONObject.put("classes", "person");

			localJSONObject.put("onclick", "doClick(" + arrayOfString[0]
					+ ",4,this,'" + arrayOfString[1] + "')");

			localJSONObject.put("hasChildren", arrayOfString[2].equals("true"));

			localJSONArray.put(localJSONObject);
		}

		return localJSONArray;
	}

	public ArrayList getSubHrmList(String paramString) {
		ArrayList localArrayList = new ArrayList();
		resourceComInfo.setTofirstRow();
		while (resourceComInfo.next()) {
			String str1 = resourceComInfo.getManagerID();
			if (isAccount(resourceComInfo.getLoginID())) {
				if ((str1.equals(paramString))
						&& (!resourceComInfo.getResourceid()
								.equals(paramString))
						&& ((resourceComInfo.getStatus().equals("0"))
								|| (resourceComInfo.getStatus().equals("1"))
								|| (resourceComInfo.getStatus().equals("2")) || (resourceComInfo
									.getStatus().equals("3")))) {
					String str2 = resourceComInfo.getResourceid();
					String str3 = resourceComInfo.getLastname();

					String str4 = isHavaHrmChildren(str2) ? "true" : "false";

					String[] arrayOfString = { str2, str3, str4 };
					localArrayList.add(arrayOfString);
				}
			}
		}
		return localArrayList;
	}

	public boolean isHavaHrmChildren(String paramString) {
		boolean bool = false;
		resourceComInfo2.setTofirstRow();
		while (resourceComInfo2.next()) {
			String str = resourceComInfo2.getManagerID();
			if ((str.equals(paramString))
					&& (!resourceComInfo2.getResourceid().equals(paramString))
					&& ((resourceComInfo2.getStatus().equals("0"))
							|| (resourceComInfo2.getStatus().equals("1"))
							|| (resourceComInfo2.getStatus().equals("2")) || (resourceComInfo2
								.getStatus().equals("3")))) {
				bool = true;
				break;
			}
		}
		return bool;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String paramString) {
		treeType = paramString;
	}

	public boolean isSocpe(String paramString) {
		paramString = "," + paramString + ",";
		if (isUseAppDetach) {
			if (ids.indexOf(paramString) != -1)
				return true;
		} else {
			return true;
		}
		return false;
	}

	public boolean isAccount(String paramString) {
		if (isShowAccount)
			return true;
		if (("".equals(paramString)) || (paramString == null)) {
			return false;
		}
		return true;
	}
}
