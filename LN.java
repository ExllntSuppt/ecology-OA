 
package ln;

import java.io.File;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Date;
import weaver.conn.RecordSet;
import weaver.file.Prop;
import weaver.general.BaseBean;
import weaver.general.GCONST;
import weaver.general.StaticObj;
import weaver.general.Util;
import weaver.system.GetPhysicalAddress;

public class LN extends BaseBean {
	Date newdate = new Date();
	long datetime = this.newdate.getTime();
	Timestamp timestamp = new Timestamp(this.datetime);
	String currentdate = this.timestamp.toString().substring(0, 4) + "-"
			+ this.timestamp.toString().substring(5, 7) + "-"
			+ this.timestamp.toString().substring(8, 10);
	String currenttime = this.timestamp.toString().substring(11, 13) + ":"
			+ this.timestamp.toString().substring(14, 16) + ":"
			+ this.timestamp.toString().substring(17, 19);
	private String companyname = "system";
	private String license = "";
	private String licensecode = "";
	private String software = "ALL";
	private String hrmnum = "999999";
	//lcs 2016/06/03
	private String expiredate = "9999-99-99";
	//lcs 2016/06/03
	private String message = "1";
	private String licensepass = "";
	//lcs 2016/06/03
	private String concurrentFlag = "1";
	private StaticObj staticobj = null;
	private byte[] licenseFile = null;
	private byte[] licenseKeyAndTypeId = null;
	//lcs 2016/06/03
	private String cid = "588141";
	private String scType = "0";
	private String scCount = "100000";

	public LN() {
		this.staticobj = StaticObj.getInstance();
		OutLicensecode();
	}

	public void setCompanyname(String newValue) {
		//lcs 2016/06/03
		//this.companyname = newValue.trim();
	}

	public String getCompanyname() {
		return this.companyname;
	}

	public void setLicense(String newValue) {
		this.license = newValue.trim();
	}

	public String getLicense() {
		return this.license;
	}

	public void setLicensecode(String newValue) {
		this.licensecode = newValue.trim();
	}

	public String getLicensecode() {
		return this.licensecode;
	}

	public void setSoftware(String newValue) {
		//lcs 2016/06/03
		//this.software = newValue.trim();
	}

	public String getSoftware() {
		return this.software;
	}

	public void setHrmnum(String newValue) {
		//lcs 2016/06/03
		//this.hrmnum = newValue.trim();
	}

	public String getHrmnum() {
		//lcs 2016/06/03
		//return this.hrmnum;
		return "999999";
	}

	public void setExpiredate(String newValue) {
		this.expiredate = newValue.trim();
	}

	public String getExpiredate() {
		return this.expiredate;
	}

	public void setLicensepass(String newValue) {
		this.licensepass = newValue.trim();
	}

	public void setConcurrentFlag(String newValue) {
		//lcs 2016/06/03
		//this.concurrentFlag = newValue.trim();
	}

	public String getConcurrentFlag() {
		
		//lcs 2016/06/03
		//return this.concurrentFlag;
		return "1";
	}

	public String InLicense() {
		if (this.message.equals("0")) {
			return this.message;
		}
		this.message = CkLicense(this.currentdate);
		if ("1".equals(this.message)) {
			RecordSet rs = new RecordSet();
			String sql = "update license set companyname='" + this.companyname
					+ "',software='" + this.software + "',license='"
					+ this.license + "',hrmnum=" + this.hrmnum
					+ ",expiredate='" + this.expiredate + "'";
			boolean r1 = rs.execute(sql);
			sql = "update HrmCompany set companyname='" + this.companyname
					+ "'";
			boolean r2 = rs.execute(sql);
			if ((!r1) || (!r2)) {
				return "6";
			}
		}
		return this.message;
	}

	public void removeLicenseComInfo() {
		this.staticobj.removeObject("LicenseComInfo");
	}

	public String getEcologyBigVersion() {
		String returnStr = "";
		RecordSet rs = new RecordSet();
		String sql = "select cversion  from  license ";
		rs.executeSql(sql);
		if (rs.next()) {
			returnStr = rs.getString(1);
		}
		int pos = returnStr.indexOf(".");
		if (pos != -1) {
			returnStr = returnStr.substring(0, pos);
		}
		return returnStr;
	}

	public void ReadFromFile(String licensefilepath)
  {
    LNParse lnp = new LNParse();
    try
    {
      LNBean lnb = (LNBean)this.staticobj.getRecordFromObj("LicenseComInfo", "LNBean");
      if (lnb == null) {
        throw new Exception("lnb is null from cache!");
      }
    }
    catch (Exception e)
    {
      e = e;
      try
      {
        LNBean lnb = lnp.getLNBean(licensefilepath, "ecology7");
        this.staticobj.putRecordToObj("LicenseComInfo", "LNBean", lnb);
      }
      catch (Exception e1)
      {
        System.out.println(e);
        writeLog(e);
      }
      LNBean lnb = (LNBean)this.staticobj.getRecordFromObj("LicenseComInfo", "LNBean");
      this.companyname = lnb.getCompanyname();
      this.license = lnb.getLicense();
      this.software = "ALL";
      this.hrmnum = lnb.getHrmnum();
      this.expiredate = lnb.getExpiredate();
      this.concurrentFlag = lnb.getConcurrentFlag();
      this.cid = lnb.getCid();
      this.scType = lnb.getScType();
      this.scCount = lnb.getScCount();
      if (!Util.null2String(lnb.getCid()).equals("")) {
        return;
      }
      this.message = "0"; return;
    }
    finally
    {
    	//Exception  localObject = null ;
        LNBean lnb = (LNBean)this.staticobj.getRecordFromObj("LicenseComInfo", "LNBean");
        this.companyname = lnb.getCompanyname();
        this.license = lnb.getLicense();
        this.software = "ALL";this.hrmnum = lnb.getHrmnum();
        this.expiredate = lnb.getExpiredate();
        this.concurrentFlag = lnb.getConcurrentFlag();
        this.cid = lnb.getCid();
        this.scType = lnb.getScType();
        this.scCount = lnb.getScCount();
      if (Util.null2String(lnb.getCid()).equals("")) {
        this.message = "0";
      }
     // throw localObject;
    }
    LNBean lnb = (LNBean)this.staticobj.getRecordFromObj("LicenseComInfo", "LNBean");
    this.companyname = lnb.getCompanyname();
    this.license = lnb.getLicense();
    this.software = "ALL";
    this.hrmnum = lnb.getHrmnum();
    this.expiredate = lnb.getExpiredate();
    this.concurrentFlag = lnb.getConcurrentFlag();
    this.cid = lnb.getCid();
    this.scType = lnb.getScType();
    this.scCount = lnb.getScCount();
    if (Util.null2String(lnb.getCid()).equals("")) {
      this.message = "0";
    }
  }

	public String ReadFromFile2(String licensefilepath) {
		LNParse lnp = new LNParse();
		try {
			LNBean lnb = lnp.getLNBean(licensefilepath, "ecology7");
			return Util.null2String(lnb.getCid());
		} catch (Exception e1) {
			this.message = "4";
			System.out.println(e1);
			writeLog(e1);
		}
		return "";
	}

	public int CKLiCode() {
		int returnInt = 1;
		String src = this.companyname + this.licensecode + this.software
				+ this.hrmnum + this.expiredate + this.concurrentFlag;
		if ((this.license.equals(""))
				|| (!this.license.equals(Util.getEncrypt(src)))) {
			this.message = "0";
		}
		return returnInt;
	}

	public void validateLicense() {
	}

	public String OutLicensecode() {
		String codeStr = Util.null2String((String) this.staticobj
				.getObject("StaticLicenseCode"));
		if (codeStr.equals("")) {
			codeStr = MakeLicensecode();
		}
		this.licensecode = codeStr;
		return this.licensecode;
	}

	public String MakeLicensecode() {
		GetPhysicalAddress PhysicalAddress = new GetPhysicalAddress();
		this.licensecode = Util
				.getEncrypt(PhysicalAddress.getPhysicalAddress());
		this.staticobj.putObject("StaticLicenseCode", this.licensecode);

		return this.licensecode;
	}

	public String CkLicense(String currentdate) {
		String sql = "";
		
		/* lcs 2016/06/03
		String filename = GCONST.getRootPath() + "license" + File.separatorChar
				+ this.licensecode + ".license";
		ReadFromFile(filename);
		if (this.expiredate.compareTo(currentdate) < 0) {
			return "4";
		}
		String temphrmnum = this.hrmnum;

		String src = this.companyname + this.licensecode + this.software
				+ temphrmnum + this.expiredate + this.concurrentFlag;
		if ((!this.license.equals(""))
				&& (this.license.equals(Util.getEncrypt(src)))) {
			this.message = "1";
		} else {
			this.message = "0";
		}
		if (CkHrmnum() >= 1) {
			this.message = "5";
		}
		return this.message;
		*/
		return this.message;
	}

	public int CkHrmnum() {
		int revalue = 0;
		int hrmnum01 = 0;
		int hrmnum02 = 0;
		String sql = "";
		RecordSet rs = new RecordSet();
		
		/* lcs 2016/06/03
		String filename = GCONST.getRootPath() + "license" + File.separatorChar
				+ this.licensecode + ".license";
		ReadFromFile(filename);
		*/
		
		hrmnum01 = Util.getIntValue(this.hrmnum, 0);
		String mode = Prop.getPropValue(GCONST.getConfigFile(), "authentic");
		if ((mode != null) && (mode.equals("ldap"))) {
			if (rs.getDBType().equals("oracle")) {
				sql = "select count(*) from HrmResource where status in (0,1,2,3) and account is not null";
			} else {
				sql = "select count(*) from HrmResource where status in (0,1,2,3) and account is not null and account<>'' ";
			}
		} else if (rs.getDBType().equals("oracle")) {
			sql = "select count(*) from HrmResource where status in (0,1,2,3) and loginid is not null";
		} else {
			sql = "select count(*) from HrmResource where status in (0,1,2,3) and loginid is not null and loginid<>'' ";
		}
		if ("1".equals(this.concurrentFlag)) {
			revalue = -1;
		} else {
			rs.execute(sql);
			if (rs.next()) {
				hrmnum02 = rs.getInt(1);
			}
			revalue = hrmnum02 - hrmnum01;
		}
		return revalue;
	}

	public int CkUnusedHrmnum() {
		int hrmnum01 = 0;
		int hrmnum02 = 0;
		String sql = "";
		RecordSet rs = new RecordSet();
		
		/* lcs 2016/06/03
		String filename = GCONST.getRootPath() + "license" + File.separatorChar
				+ this.licensecode + ".license";
		ReadFromFile(filename);
		*/
		
		hrmnum01 = Util.getIntValue(this.hrmnum, 0);
		String mode = Prop.getPropValue(GCONST.getConfigFile(), "authentic");
		if ((mode != null) && (mode.equals("ldap"))) {
			if (rs.getDBType().equals("oracle")) {
				sql = "select count(*) from HrmResource where status in (0,1,2,3) and account is not null";
			} else {
				sql = "select count(*) from HrmResource where status in (0,1,2,3) and account is not null and account<>'' ";
			}
		} else if (rs.getDBType().equals("oracle")) {
			sql = "select count(*) from HrmResource where status in (0,1,2,3) and loginid is not null";
		} else {
			sql = "select count(*) from HrmResource where status in (0,1,2,3) and loginid is not null and loginid<>'' ";
		}
		rs.execute(sql);
		if (rs.next()) {
			hrmnum02 = rs.getInt(1);
		}
		return hrmnum01 - hrmnum02;
	}

	public int CkUsedHrmnum() {
		int hrmnum01 = 0;
		int hrmnum02 = 0;
		String sql = "";
		RecordSet rs = new RecordSet();
		
		/* lcs 2016/06/03
		String filename = GCONST.getRootPath() + "license" + File.separatorChar
				+ this.licensecode + ".license";
		ReadFromFile(filename);
		*/
		
		hrmnum01 = Util.getIntValue(this.hrmnum, 0);
		String mode = Prop.getPropValue(GCONST.getConfigFile(), "authentic");
		if ((mode != null) && (mode.equals("ldap"))) {
			if (rs.getDBType().equals("oracle")) {
				sql = "select count(*) from HrmResource where status in (0,1,2,3) and account is not null";
			} else {
				sql = "select count(*) from HrmResource where status in (0,1,2,3) and account is not null and account<>'' ";
			}
		} else if (rs.getDBType().equals("oracle")) {
			sql = "select count(*) from HrmResource where status in (0,1,2,3) and loginid is not null";
		} else {
			sql = "select count(*) from HrmResource where status in (0,1,2,3) and loginid is not null and loginid<>'' ";
		}
		rs.execute(sql);
		if (rs.next()) {
			hrmnum02 = rs.getInt(1);
		}
		return hrmnum02;
	}

	public String getCid() {
		/* lcs 2016/06/02
		try {
			MakeLicensecode();
			String filename = GCONST.getRootPath() + "license"
					+ File.separatorChar + this.licensecode + ".license";
			ReadFromFile(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		return this.cid;
	}

	public String getScType() {
		/* lcs 2016/06/03
		try {
			MakeLicensecode();
			String filename = GCONST.getRootPath() + "license"
					+ File.separatorChar + this.licensecode + ".license";
			ReadFromFile(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		return this.scType;
	}

	public String getScCount() {
		try {
			MakeLicensecode();
			String filename = GCONST.getRootPath() + "license"
					+ File.separatorChar + this.licensecode + ".license";
			ReadFromFile(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.scCount;
	}
}