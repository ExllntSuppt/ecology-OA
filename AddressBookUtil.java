 
package weaver.addressbook;

import weaver.general.BaseBean;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.company.SubCompanyComInfo;
import weaver.hrm.resource.ResourceComInfo;

public class AddressBookUtil extends BaseBean {
	public AddressBookUtil() {
	}

	public String getAddressBookUserInfo(String paramString) {
		String str = "";
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			SubCompanyComInfo localSubCompanyComInfo = new SubCompanyComInfo();
			DepartmentComInfo localDepartmentComInfo = new DepartmentComInfo();

			str = "<table><tr><td rowspan=\"2\"><img src=\""
					+ localResourceComInfo.getMessagerUrls(paramString)
					+ "\"></td>";
			str = str + "<td>";
			str = str
					+ localResourceComInfo.getLastname(paramString)
					+ "&nbsp;"
					+ localSubCompanyComInfo
							.getSubCompanyname(localResourceComInfo
									.getSubCompanyID(paramString)) + "&nbsp;";
			str = str + "</td></tr>";
			str = str + "<tr><td>";
			str = str
					+ localDepartmentComInfo
							.getDepartmentname(localResourceComInfo
									.getDepartmentID(paramString)) + "&nbsp;";
			str = str + localResourceComInfo.getMobile(paramString) + "&nbsp;";
			str = str + localResourceComInfo.getEmail(paramString) + "&nbsp;";
			str = str + "</td></tr></table>";
		} catch (Exception localException) {
			localException.printStackTrace();
			writeLog(localException);
		}
		return str;
	}
}