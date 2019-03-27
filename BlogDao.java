package weaver.blog;

import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.sql.CLOB;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.docs.docs.DocImageManager;
import weaver.file.FileUpload;
import weaver.general.BaseBean;
import weaver.general.SplitPageParaBean;
import weaver.general.SplitPageUtil;
import weaver.general.StaticObj;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.company.SubCompanyComInfo;
import weaver.hrm.job.JobTitlesComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.hrm.roles.RolesComInfo;
import weaver.hrm.schedule.HrmKqSystemComInfo;
import weaver.systeminfo.SystemEnv;

public class BlogDao extends BaseBean {
	ResourceComInfo resourceComInfo;
	DepartmentComInfo departmentComInfo;
	JobTitlesComInfo jobTitlesComInfo;
	SubCompanyComInfo subCompanyComInfo;
	RolesComInfo rolesComInfo;
	BlogShareManager shareManager;

	public BlogDao() {
		try {
			resourceComInfo = new ResourceComInfo();
			departmentComInfo = new DepartmentComInfo();
			jobTitlesComInfo = new JobTitlesComInfo();
			subCompanyComInfo = new SubCompanyComInfo();
			rolesComInfo = new RolesComInfo();
			shareManager = new BlogShareManager();
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public BlogDiscessVo getDiscussVo(String paramString) {
		BlogDiscessVo localBlogDiscessVo = null;
		RecordSet localRecordSet = new RecordSet();
		String str1 = "select * from blog_discuss where id=" + paramString;
		localRecordSet.execute(str1);
		if (localRecordSet.next()) {
			localBlogDiscessVo = new BlogDiscessVo();
			String str2 = localRecordSet.getString("userid");
			String str3 = localRecordSet.getString("workdate");
			String str4 = localRecordSet.getString("createdate");
			String str5 = localRecordSet.getString("createtime");
			String str6 = localRecordSet.getString("content");
			String str7 = localRecordSet.getString("lastUpdatetime");
			String str8 = localRecordSet.getString("isReplenish");
			String str9 = localRecordSet.getString("score");
			String str10 = localRecordSet.getString("comefrom");
			localBlogDiscessVo.setId(paramString);
			localBlogDiscessVo.setUserid(str2);
			localBlogDiscessVo.setWorkdate(str3);
			localBlogDiscessVo.setCreatedate(str4);
			localBlogDiscessVo.setCreatetime(str5);
			localBlogDiscessVo.setContent(str6);
			localBlogDiscessVo.setLastUpdatetime(str7);
			localBlogDiscessVo.setIsReplenish(str8);
			localBlogDiscessVo.setScore(str9);
			localBlogDiscessVo.setComefrom(str10);
		}
		return localBlogDiscessVo;
	}

	public BlogDiscessVo getDiscussVoByDate(String paramString1,
			String paramString2) {
		BlogDiscessVo localBlogDiscessVo = null;
		RecordSet localRecordSet = new RecordSet();
		String str1 = "select * from blog_discuss where userid=" + paramString1
				+ " and workdate='" + paramString2 + "'";
		localRecordSet.execute(str1);
		if (localRecordSet.next()) {
			localBlogDiscessVo = new BlogDiscessVo();
			String str2 = localRecordSet.getString("id");
			String str3 = localRecordSet.getString("createdate");
			String str4 = localRecordSet.getString("createtime");
			String str5 = localRecordSet.getString("content");
			String str6 = localRecordSet.getString("lastUpdatetime");
			String str7 = localRecordSet.getString("isReplenish");
			String str8 = localRecordSet.getString("score");
			String str9 = localRecordSet.getString("comefrom");

			localBlogDiscessVo.setId(str2);
			localBlogDiscessVo.setUserid(paramString1);
			localBlogDiscessVo.setWorkdate(paramString2);
			localBlogDiscessVo.setCreatedate(str3);
			localBlogDiscessVo.setCreatetime(str4);
			localBlogDiscessVo.setContent(str5);
			localBlogDiscessVo.setLastUpdatetime(str6);
			localBlogDiscessVo.setIsReplenish(str7);
			localBlogDiscessVo.setScore(str8);
			localBlogDiscessVo.setComefrom(str9);
			localBlogDiscessVo
					.setIsHasLocation(isHasLocation(str2) ? "1" : "0");
		}
		return localBlogDiscessVo;
	}

	public BlogDiscessVo getLatestDiscuss(String paramString) {
		BlogDiscessVo localBlogDiscessVo = null;
		RecordSet localRecordSet = new RecordSet();
		String str1 = "select * from blog_discuss where workdate=(select max(workdate) from blog_discuss where userid="
				+ paramString + ") and userid=" + paramString;
		localRecordSet.execute(str1);
		if (localRecordSet.next()) {
			localBlogDiscessVo = new BlogDiscessVo();
			String str2 = localRecordSet.getString("id");
			String str3 = localRecordSet.getString("createdate");
			String str4 = localRecordSet.getString("createtime");
			String str5 = localRecordSet.getString("content");
			String str6 = localRecordSet.getString("lastUpdatetime");
			String str7 = localRecordSet.getString("workdate");
			String str8 = localRecordSet.getString("isReplenish");
			String str9 = localRecordSet.getString("score");

			localBlogDiscessVo.setId(str2);
			localBlogDiscessVo.setUserid(paramString);
			localBlogDiscessVo.setWorkdate(str7);
			localBlogDiscessVo.setCreatedate(str3);
			localBlogDiscessVo.setCreatetime(str4);
			localBlogDiscessVo.setContent(str5);
			localBlogDiscessVo.setLastUpdatetime(str6);
			localBlogDiscessVo.setIsReplenish(str8);
			localBlogDiscessVo.setScore(str9);
		}
		return localBlogDiscessVo;
	}

	public Map getDiscussMapByDate(String paramString1, String paramString2,
			String paramString3) {
		HashMap localHashMap = new HashMap();
		String str1 = "select * from blog_discuss where userid=" + paramString1
				+ " and workdate>='" + paramString2 + "' and workdate<='"
				+ paramString3 + "'";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str1);
		while (localRecordSet.next()) {
			BlogDiscessVo localBlogDiscessVo = new BlogDiscessVo();
			String str2 = localRecordSet.getString("id");
			String str3 = localRecordSet.getString("workdate");
			String str4 = localRecordSet.getString("createdate");
			String str5 = localRecordSet.getString("createtime");
			String str6 = localRecordSet.getString("content");
			String str7 = localRecordSet.getString("lastUpdatetime");
			String str8 = localRecordSet.getString("isReplenish");
			String str9 = localRecordSet.getString("score");
			String str10 = localRecordSet.getString("comefrom");
			localBlogDiscessVo.setId(str2);
			localBlogDiscessVo.setUserid(paramString1);
			localBlogDiscessVo.setWorkdate(str3);
			localBlogDiscessVo.setCreatedate(str4);
			localBlogDiscessVo.setCreatetime(str5);
			localBlogDiscessVo.setContent(str6);
			localBlogDiscessVo.setLastUpdatetime(str7);
			localBlogDiscessVo.setIsReplenish(str8);
			localBlogDiscessVo.setScore(str9);
			localBlogDiscessVo.setComefrom(str10);
			localBlogDiscessVo
					.setIsHasLocation(isHasLocation(str2) ? "1" : "0");
			localHashMap.put(str3, localBlogDiscessVo);
		}
		return localHashMap;
	}

	public List getDiscussWorkdateList(String paramString1,
			String paramString2, String paramString3) {
		HashMap localHashMap = new HashMap();
		ArrayList localArrayList = new ArrayList();
		String str1 = "select workdate from blog_discuss where userid="
				+ paramString1 + " and workdate>='" + paramString2
				+ "' and workdate<='" + paramString3 + "'";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str1);
		while (localRecordSet.next()) {
			String str2 = localRecordSet.getString("workdate");
			localArrayList.add(str2);
		}
		return localArrayList;
	}

	public Map getMoodvoMap(String paramString1, String paramString2,
			String paramString3) {
		HashMap localHashMap = new HashMap();
		ArrayList localArrayList = new ArrayList();
		String str1 = "select t0.workDate,t1.* from blog_appDatas t0 LEFT JOIN blog_AppItem t1 ON t0.appItemId=t1.id where userid="
				+ paramString1
				+ " and workDate>='"
				+ paramString2
				+ "' and workDate<='" + paramString3 + "'";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str1);
		while (localRecordSet.next()) {
			AppItemVo localAppItemVo = new AppItemVo();
			String str2 = localRecordSet.getString("workDate");
			localAppItemVo.setId(localRecordSet.getString("id"));
			localAppItemVo.setValue(localRecordSet.getString("value"));
			localAppItemVo.setType(localRecordSet.getString("type"));
			localAppItemVo.setFaceImg(localRecordSet.getString("face"));

			localHashMap.put(str2, localAppItemVo);
		}
		return localHashMap;
	}

	public int getTotalSubmited(String paramString1, String paramString2,
			String paramString3) {
		String str = "select count(id) as total from blog_discuss where userid in("
				+ paramString1
				+ ")"
				+ " and workdate>='"
				+ paramString2
				+ "' and workdate<='" + paramString3 + "'";

		int i = 0;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		if (localRecordSet.next())
			i = localRecordSet.getInt("total");
		if (i == -1)
			i = 0;
		return i;
	}

	public Map getAttentionDicussByDate(List paramList, String paramString) {
		HashMap localHashMap = new HashMap();
		if ((paramList == null) || (paramList.size() == 0))
			return localHashMap;
		String str1 = "";
		for (int i = 0; i < paramList.size(); i++) {
			str1 = str1 + "," + paramList.get(i);
		}
		str1 = str1.substring(1);
		String str2 = "select * from blog_discuss where userid in(" + str1
				+ ") and workdate='" + paramString + "'";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str2);

		while (localRecordSet.next()) {
			BlogDiscessVo localBlogDiscessVo = new BlogDiscessVo();
			String str3 = localRecordSet.getString("id");
			String str4 = localRecordSet.getString("userid");
			String str5 = localRecordSet.getString("createdate");
			String str6 = localRecordSet.getString("createtime");
			String str7 = localRecordSet.getString("content");
			String str8 = localRecordSet.getString("lastUpdatetime");
			String str9 = localRecordSet.getString("isReplenish");
			String str10 = localRecordSet.getString("score");
			String str11 = localRecordSet.getString("comefrom");
			localBlogDiscessVo.setId(str3);
			localBlogDiscessVo.setUserid(str4);
			localBlogDiscessVo.setWorkdate(paramString);
			localBlogDiscessVo.setCreatedate(str5);
			localBlogDiscessVo.setCreatetime(str6);
			localBlogDiscessVo.setContent(str7);
			localBlogDiscessVo.setLastUpdatetime(str8);
			localBlogDiscessVo.setIsReplenish(str9);
			localBlogDiscessVo.setScore(str10);
			localBlogDiscessVo.setComefrom(str11);
			localHashMap.put(str4, localBlogDiscessVo);
		}
		return localHashMap;
	}

	public Map getAttentionDiscussCount(String paramString1,
			String paramString2, String paramString3) {
		HashMap localHashMap = new HashMap();
		String str1 = "select workdate,count(id) as total from blog_discuss where  userid in("
				+ paramString1
				+ ") and workdate>='"
				+ paramString2
				+ "' and workdate<='" + paramString3 + "' group by workdate";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str1);
		while (localRecordSet.next()) {
			String str2 = localRecordSet.getString("workdate");
			int i = localRecordSet.getInt("total");
			localHashMap.put(str2, new Integer(i));
		}

		return localHashMap;
	}

	public List getDiscussMoodCount(String paramString1, String paramString2,
			String paramString3, Map paramMap) {
		ArrayList localArrayList1 = new ArrayList();
		ArrayList localArrayList2 = new ArrayList();
		ArrayList localArrayList3 = new ArrayList();
		String str1 = "select workdate,count(CASE  WHEN appItemid=1 THEN 1  END ) happy,count(CASE  WHEN appItemid=2 THEN 1  END )unhappy from blog_appDatas where userid in("
				+ paramString1
				+ ") and workdate>='"
				+ paramString2
				+ "' and workdate<='" + paramString3 + "' group by workdate";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str1);
		while (localRecordSet.next()) {
			HashMap localObject1 = new HashMap();
			((Map) localObject1).put("happy",
					new Integer(localRecordSet.getInt("happy")));
			((Map) localObject1).put("unhappy",
					new Integer(localRecordSet.getInt("unhappy")));

			localArrayList2.add(localRecordSet.getString("workdate"));
			localArrayList3.add(localObject1);
		}

		Object localObject1 = paramMap.keySet().iterator();
		while (((Iterator) localObject1).hasNext()) {
			String str2 = (String) ((Iterator) localObject1).next();
			Object localObject2 = new HashMap();
			if (localArrayList2.contains(str2)) {
				localObject2 = (Map) localArrayList3.get(localArrayList2
						.indexOf(str2));
			} else {
				((Map) localObject2).put("happy", new Integer(0));
				((Map) localObject2).put("unhappy", new Integer(0));
			}
			localArrayList1.add(localObject2);
		}
		return localArrayList1;
	}

	public List getReplyList(String paramString1, String paramString2,
			String paramString3) {
		ArrayList localArrayList = new ArrayList();

		String str1 = "select * from blog_reply where bediscussantid="
				+ paramString1 + " and workdate='" + paramString2
				+ "' and  (commentType=0 or (commentType=1 and (userid="
				+ paramString3 + " or bediscussantid=" + paramString3
				+ " or relatedid=" + paramString3 + ")))  order by id asc";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str1);
		while (localRecordSet.next()) {
			BlogReplyVo localBlogReplyVo = new BlogReplyVo();
			String str2 = localRecordSet.getString("id");
			String str3 = localRecordSet.getString("userid");
			String str4 = localRecordSet.getString("createdate");
			String str5 = localRecordSet.getString("createtime");
			String str6 = localRecordSet.getString("content");
			String str7 = localRecordSet.getString("comefrom");
			String str8 = localRecordSet.getString("commentType");

			localBlogReplyVo.setId(str2);
			localBlogReplyVo.setUserid(str3);
			localBlogReplyVo.setCreatedate(str4);
			localBlogReplyVo.setCreatetime(str5);
			localBlogReplyVo.setContent(str6);
			localBlogReplyVo.setComefrom(str7);
			localBlogReplyVo.setWorkdate(paramString2);
			localBlogReplyVo.setBediscussantid(paramString1);
			localBlogReplyVo.setCommentType(str8);
			localArrayList.add(localBlogReplyVo);
		}
		return localArrayList;
	}

	public BlogReplyVo getReplyById(String paramString) {
		String str = "select * from blog_reply where id=" + paramString;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		if (localRecordSet.next()) {
			BlogReplyVo localBlogReplyVo = new BlogReplyVo();
			localBlogReplyVo.setId(localRecordSet.getString("id"));
			localBlogReplyVo.setContent(localRecordSet.getString("content"));
			localBlogReplyVo.setCreatedate(localRecordSet
					.getString("createdate"));
			localBlogReplyVo.setCreatetime(localRecordSet
					.getString("createtime"));
			localBlogReplyVo
					.setDiscussid(localRecordSet.getString("discussid"));
			localBlogReplyVo.setUserid(localRecordSet.getString("userid"));
			localBlogReplyVo.setComefrom(localRecordSet.getString("comefrom"));

			localBlogReplyVo.setWorkdate(localRecordSet.getString("workdate"));
			localBlogReplyVo.setBediscussantid(localRecordSet
					.getString("bediscussantid"));
			localBlogReplyVo.setCommentType(localRecordSet
					.getString("commentType"));

			return localBlogReplyVo;
		}
		return null;
	}

	public List getCommentList(String paramString) {
		ArrayList localArrayList = new ArrayList();

		String str1 = "SELECT  bediscussantid,workdate,max(id) AS replayid FROM blog_reply WHERE bediscussantid="
				+ paramString
				+ " or relatedid="
				+ paramString
				+ " GROUP BY bediscussantid,workdate ORDER BY replayid desc";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str1);
		while (localRecordSet.next()) {
			String str2 = localRecordSet.getString("bediscussantid");
			String str3 = localRecordSet.getString("workdate");
			BlogDiscessVo localBlogDiscessVo = getDiscussVoByDate(str2, str3);
			if (localBlogDiscessVo == null) {
				localBlogDiscessVo = new BlogDiscessVo();
				localBlogDiscessVo.setWorkdate(str3);
				localBlogDiscessVo.setUserid(str2);
			}
			localArrayList.add(localBlogDiscessVo);
		}
		return localArrayList;
	}

	public int getCommentTotal(String paramString) {
		int i = 0;
		String str = "SELECT count(*) as total FROM (SELECT  bediscussantid,workdate from  blog_reply WHERE bediscussantid="
				+ paramString
				+ " or relatedid="
				+ paramString
				+ " GROUP BY bediscussantid,workdate) t";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		if (localRecordSet.next()) {
			i = localRecordSet.getInt("total");
		}
		return i;
	}

	public List getCommentDiscussVOList(String paramString, int paramInt1,
			int paramInt2, int paramInt3) {
		ArrayList localArrayList = new ArrayList();
		RecordSet localRecordSet = new RecordSet();
		String str1 = "";

		int i = paramInt3;
		int j = paramInt1 * paramInt2;
		int k = paramInt2;
		if (i - j + paramInt2 < paramInt2)
			k = i - j + paramInt2;
		if (i < paramInt2)
			k = i;
		if (localRecordSet.getDBType().equals("oracle")) {
			str1 = "select bediscussantid,workdate,max(id) AS replayid FROM blog_reply WHERE bediscussantid="
					+ paramString
					+ " or relatedid="
					+ paramString
					+ " GROUP BY bediscussantid,workdate ORDER BY replayid desc";
			str1 = "select t1.*,rownum rn from (" + str1
					+ ") t1 where rownum <= " + j;
			str1 = "select t2.* from (" + str1 + ") t2 where rn > "
					+ (j - paramInt2);
		} else {
			str1 = "select top "
					+ j
					+ " bediscussantid,workdate,max(id) AS replayid FROM blog_reply WHERE bediscussantid="
					+ paramString
					+ " or relatedid="
					+ paramString
					+ " GROUP BY bediscussantid,workdate ORDER BY replayid desc";
			str1 = "select top " + k + " t1.* from (" + str1
					+ ") t1  order by replayid asc";
			str1 = "select top " + k + " t2.* from (" + str1
					+ ") t2  order by replayid desc";
		}
		localRecordSet.executeSql(str1);
		while (localRecordSet.next()) {
			String str2 = localRecordSet.getString("bediscussantid");
			String str3 = localRecordSet.getString("workdate");
			BlogDiscessVo localBlogDiscessVo = getDiscussVoByDate(str2, str3);
			if (localBlogDiscessVo == null) {
				localBlogDiscessVo = new BlogDiscessVo();
				localBlogDiscessVo.setWorkdate(str3);
				localBlogDiscessVo.setUserid(str2);
			}
			localArrayList.add(localBlogDiscessVo);
		}
		return localArrayList;
	}

	public void addReadRecord(String paramString1, String paramString2) {
		String str = "select * from blog_read where userid=" + paramString1
				+ " and blogid=" + paramString2;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		if (!localRecordSet.next()) {
			str = "insert into blog_read(userid,blogid) values(" + paramString1
					+ "," + paramString2 + ")";
			localRecordSet.execute(str);
		}
	}

	public void addVisitRecord(String paramString1, String paramString2) {
		if (paramString1.equals(paramString2))
			return;
		Date localDate = new Date();
		SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd");
		SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat(
				"HH:mm:ss");

		String str1 = localSimpleDateFormat1.format(localDate);
		String str2 = localSimpleDateFormat2.format(localDate);

		String str3 = "select id from blog_visit where userid=" + paramString1
				+ " and blogid=" + paramString2;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str3);
		if (!localRecordSet.next()) {
			str3 = "insert into blog_visit(userid,blogid,visitdate,visittime) values("
					+ paramString1
					+ ","
					+ paramString2
					+ ",'"
					+ str1
					+ "','"
					+ str2 + "')";
			localRecordSet.execute(str3);
		} else {
			String str4 = localRecordSet.getString("id");
			str3 = "update blog_visit set visitdate='" + str1 + "',visittime='"
					+ str2 + "' where id=" + str4;
			localRecordSet.execute(str3);
		}
	}

	public String getSysSetting(String paramString) {
		String str = "select * from blog_sysSetting";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		localRecordSet.next();

		return localRecordSet.getString(paramString);
	}

	public Map getEnableDate() {
		HashMap localHashMap = new HashMap();
		String str = getSysSetting("enableDate");

		Calendar localCalendar = Calendar.getInstance();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		try {
			Date localDate = localSimpleDateFormat.parse(str);
			localCalendar.setTime(localDate);
			int i = localCalendar.get(1);
			int j = localCalendar.get(2) + 1;
			int k = localCalendar.get(5);

			localHashMap.put("year", new Integer(i));
			localHashMap.put("month", new Integer(j));
			localHashMap.put("day", new Integer(k));
		} catch (ParseException localParseException) {
			localParseException.printStackTrace();
		}

		return localHashMap;
	}

	public Map getCompaerMonth(int paramInt) {
		HashMap localHashMap = new HashMap();

		Calendar localCalendar = Calendar.getInstance();
		int i = localCalendar.get(2) + 1;
		int j = localCalendar.get(1);
		int k = localCalendar.get(5);

		Map localMap = getEnableDate();
		int m = ((Integer) localMap.get("year")).intValue();
		int n = ((Integer) localMap.get("month")).intValue();

		int i1 = 0;
		int i2 = 0;
		if (paramInt < j) {
			if (paramInt == m) {
				i1 = n;
				i2 = 12;
			} else {
				i1 = 1;
				i2 = 12;
			}
		} else if (paramInt == j) {
			if (paramInt == m) {
				i1 = n;
				i2 = i;
			} else {
				i1 = 1;
				i2 = i;
			}
			if (k == 1) {
				i2 = i - 1;
			}
		}
		localHashMap.put("startMonth", new Integer(i1));
		localHashMap.put("endMonth", new Integer(i2));
		return localHashMap;
	}

	public List getVisitorList(String paramString, int paramInt1,
			int paramInt2, int paramInt3) {
		String str1 = "";
		RecordSet localRecordSet = new RecordSet();

		int i = paramInt3;
		int j = paramInt1 * paramInt2;
		int k = paramInt2;
		if (i - j + paramInt2 < paramInt2)
			k = i - j + paramInt2;
		if (i < paramInt2)
			k = i;
		if (localRecordSet.getDBType().equals("oracle")) {
			str1 = "SELECT  id,userid,visitdate,visittime FROM blog_visit where  blogid="
					+ paramString + "  ORDER BY visitdate desc,visittime desc ";
			str1 = "select t1.*,rownum rn from (" + str1
					+ ") t1 where rownum <= " + j;
			str1 = "select t2.* from (" + str1 + ") t2 where rn > "
					+ (j - paramInt2);
		} else {
			str1 = "select top "
					+ j
					+ " id,userid,visitdate,visittime FROM blog_visit WHERE blogid="
					+ paramString + "  ORDER BY visitdate desc,visittime desc";
			str1 = "select top " + k + " t1.* from (" + str1
					+ ") t1  order by visitdate asc,visittime asc";
			str1 = "select top " + k + " t2.* from (" + str1
					+ ") t2  order by visitdate desc,visittime desc";
		}

		localRecordSet.executeSql(str1);
		ArrayList localArrayList = new ArrayList();
		while (localRecordSet.next()) {
			HashMap localHashMap = new HashMap();
			String str2 = localRecordSet.getString("userid");
			localHashMap.put("userid", str2);
			localHashMap.put("username", resourceComInfo.getLastname(str2));
			localHashMap.put("deptName", departmentComInfo
					.getDepartmentname(resourceComInfo.getDepartmentID(str2)));
			localHashMap.put("subName", subCompanyComInfo
					.getSubCompanyname(resourceComInfo.getSubCompanyID(str2)));
			localHashMap.put("imageUrl", resourceComInfo.getMessagerUrls(str2));
			localHashMap
					.put("visitdate", localRecordSet.getString("visitdate"));
			localHashMap
					.put("visittime", localRecordSet.getString("visittime"));
			localArrayList.add(localHashMap);
		}
		return localArrayList;
	}

	public int getVisitTotal(String paramString) {
		int i = 0;
		String str = "SELECT count(1) total FROM blog_visit WHERE blogid="
				+ paramString;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		if (localRecordSet.next())
			i = localRecordSet.getInt("total");
		return i;
	}

	public List getAccessList(String paramString, int paramInt1, int paramInt2,
			int paramInt3) {
		String str = "";
		RecordSet localRecordSet = new RecordSet();

		int i = paramInt3;
		int j = paramInt1 * paramInt2;
		int k = paramInt2;
		if (i - j + paramInt2 < paramInt2)
			k = i - j + paramInt2;
		if (i < paramInt2)
			k = i;
		if (localRecordSet.getDBType().equals("oracle")) {
			str = "SELECT  id,blogid,visitdate,visittime FROM blog_visit where  userid="
					+ paramString + "  ORDER BY visitdate desc,visittime desc ";
			str = "select t1.*,rownum rn from (" + str
					+ ") t1 where rownum <= " + j;
			str = "select t2.* from (" + str + ") t2 where rn > "
					+ (j - paramInt2);
		} else {
			str = "select top "
					+ j
					+ " id,blogid,visitdate,visittime FROM blog_visit WHERE userid="
					+ paramString + "  ORDER BY visitdate desc,visittime desc";
			str = "select top " + k + " t1.* from (" + str
					+ ") t1  order by visitdate asc,visittime asc";
			str = "select top " + k + " t2.* from (" + str
					+ ") t2  order by visitdate desc,visittime desc";
		}

		localRecordSet.executeSql(str);
		ArrayList localArrayList = new ArrayList();
		while (localRecordSet.next()) {
			HashMap localHashMap = new HashMap();
			localHashMap.put("userid", localRecordSet.getString("blogid"));
			localHashMap
					.put("visitdate", localRecordSet.getString("visitdate"));
			localHashMap
					.put("visittime", localRecordSet.getString("visittime"));
			localArrayList.add(localHashMap);
		}
		return localArrayList;
	}

	public int getAccessTotal(String paramString) {
		int i = 0;
		String str = "SELECT count(1) total FROM blog_visit WHERE userid="
				+ paramString;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		if (localRecordSet.next())
			i = localRecordSet.getInt("total");
		return i;
	}

	public List getMsgRemidList(User paramUser, String paramString1,
			String paramString2) {
		BlogDao localBlogDao = new BlogDao();
		SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd");
		SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat(
				"HH:mm:ss");
		RecordSet localRecordSet = new RecordSet();
		String str1 = "" + paramUser.getUID();

		ArrayList localArrayList = new ArrayList();
		String str2 = "";
		try {
			if (paramString1.equals("comment")) {
				str2 = "select * from blog_remind where remindid="
						+ str1
						+ " and remindType=9 and status=0 order by createdate desc,createtime desc";
			} else if (paramString1.equals("remind")) {
				str2 = "select * from blog_remind where remindid="
						+ str1
						+ " and (remindType=1 or remindType=2 or remindType=3 or remindType=7 or remindType=8) and (status=0 or status=1) order by createdate desc,createtime desc";
			} else if (paramString1.equals("myAttention")) {
				str2 = "select * from blog_remind where remindid="
						+ str1
						+ " and remindType=4 and status=0 order by createdate desc,createtime desc";
			} else if (paramString1.equals("attentionMe")) {
				str2 = "select * from blog_remind where remindid="
						+ str1
						+ " and remindType=5 and status=0 order by createdate desc,createtime desc";
			} else if (paramString1.equals("update")) {
				str2 = "select * from blog_remind where remindid="
						+ str1
						+ " and remindType=6 and status=0 order by createdate desc,createtime desc";
			} else if (paramString1.equals("all")) {
				if (localRecordSet.getDBType().equals("oracle")) {
					str2 = "select * from (SELECT  * FROM blog_remind where  remindid="
							+ str1
							+ " and status=0 ORDER BY createdate desc,createtime desc ) where rownum<="
							+ paramString2;
				} else {
					str2 = "SELECT top "
							+ paramString2
							+ " * FROM blog_remind where  remindid="
							+ str1
							+ " and status=0  ORDER BY createdate desc,createtime desc ";
				}
			}
			localRecordSet.execute(str2);
			while (localRecordSet.next()) {
				HashMap localHashMap = new HashMap();
				localHashMap.put("id", localRecordSet.getString("id"));
				localHashMap.put("remindid",
						localRecordSet.getString("remindid"));
				localHashMap.put("relatedid",
						localRecordSet.getString("relatedid"));
				localHashMap.put("remindType",
						localRecordSet.getString("remindType"));
				localHashMap.put("createdate",
						localRecordSet.getString("createdate"));
				localHashMap.put("createtime",
						localRecordSet.getString("createtime"));
				localHashMap.put("remindValue",
						localRecordSet.getString("remindValue"));

				localArrayList.add(localHashMap);
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}

		return localArrayList;
	}

	public List getBlogStatusRemidList(User paramUser, String paramString1,
			String paramString2) {
		BlogDao localBlogDao = new BlogDao();
		SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd");
		SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat(
				"HH:mm:ss");
		RecordSet localRecordSet = new RecordSet();
		String str1 = "" + paramUser.getUID();

		ArrayList localArrayList = new ArrayList();
		String str2 = "";
		try {
			if (localRecordSet.getDBType().equals("oracle")) {
				str2 = "select * from (SELECT  * FROM blog_remind where  remindid="
						+ str1;
			} else {
				str2 = "SELECT top " + paramString2
						+ " * FROM blog_remind where  remindid=" + str1;
			}
			if (paramString1.equals("comment")) {
				str2 = str2
						+ " and remindType=9 and  status=0 order by createdate desc,createtime desc";
			} else if (paramString1.equals("remind")) {
				str2 = str2
						+ " and (remindType=1 or remindType=2 or remindType=3 or remindType=7 or remindType=8) and status=0 order by createdate desc,createtime desc";

			} else if (paramString1.equals("update")) {
				str2 = str2
						+ " and remindType=6 and  status=0 order by createdate desc,createtime desc";
			}
			if (localRecordSet.getDBType().equals("oracle")) {
				str2 = str2 + ") where rownum<=" + paramString2;
			}
			localRecordSet.execute(str2);
			while (localRecordSet.next()) {
				HashMap localHashMap = new HashMap();
				localHashMap.put("id", localRecordSet.getString("id"));
				localHashMap.put("remindid",
						localRecordSet.getString("remindid"));
				localHashMap.put("relatedid",
						localRecordSet.getString("relatedid"));
				localHashMap.put("remindType",
						localRecordSet.getString("remindType"));
				localHashMap.put("createdate",
						localRecordSet.getString("createdate"));
				localHashMap.put("createtime",
						localRecordSet.getString("createtime"));
				localHashMap.put("remindValue",
						localRecordSet.getString("remindValue"));
				localHashMap.put("status", localRecordSet.getString("status"));

				localArrayList.add(localHashMap);
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}

		return localArrayList;
	}

	public Map getReindCount(User paramUser) {
		int i = 0;
		int j = 0;
		int k = 0;
		int m = 0;
		int n = 0;

		checkUnsubmitRemind(paramUser);
		String str1 = "" + paramUser.getUID();

		delInvalidRemind(str1);

		String str2 = "select remindType,count(*) as total from blog_remind where remindid="
				+ str1 + " and status=0 group by remindType";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str2);

		while (localRecordSet.next()) {
			int i1 = localRecordSet.getInt("remindType");
			int i2 = localRecordSet.getInt("total");

			if ((i1 == 1) || (i1 == 2) || (i1 == 3) || (i1 == 7) || (i1 == 8)) {
				j += i2;
			} else if (i1 == 5) {
				m += i2;
			} else if (i1 == 6) {
				n += i2;
			} else if (i1 == 9) {
				k += i2;
			}
		}
		HashMap localHashMap = new HashMap();
		localHashMap.put("requestCount", new Integer(i));
		localHashMap.put("remindCount", new Integer(j));
		localHashMap.put("commentCount", new Integer(k));
		localHashMap.put("attentionMeCount", new Integer(m));
		localHashMap.put("updateCount", new Integer(n));

		return localHashMap;
	}

	public String getRemindUnReadCount(String paramString1, String paramString2) {
		String str1 = "";
		if (paramString2.equals("commment")) {
			str1 = "select count(1) as total from blog_remind where remindid="
					+ paramString1 + " and remindType=9 and status=0";
		} else if (paramString2.equals("update"))
			str1 = "select count(1) as total from blog_remind where remindid="
					+ paramString1 + " and remindType=6 and status=0";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str1);
		String str2 = "0";
		if (localRecordSet.next())
			str2 = localRecordSet.getString("total");
		return str2;
	}

	public void checkUnsubmitRemind(User paramUser) {
		BlogDao localBlogDao = new BlogDao();
		SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd");
		SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat(
				"HH:mm:ss");
		RecordSet localRecordSet = new RecordSet();
		String str1 = "";
		String str2 = "" + paramUser.getUID();

		Date localDate = new Date();
		BlogDiscessVo localBlogDiscessVo = localBlogDao.getLatestDiscuss(str2);
		String str3 = "";
		if (localBlogDiscessVo != null) {
			str3 = localBlogDiscessVo.getWorkdate();
		} else {
			str3 = getUserBlogStartdate(str2);
		}
		String str4 = localSimpleDateFormat1.format(localDate);
		String str5 = localSimpleDateFormat2.format(localDate);

		WorkDayDao localWorkDayDao = new WorkDayDao(paramUser);
		List localList = localWorkDayDao.getWorkDays(str3, str4);

		int i = localList.size() - 1;

		if (i > 3) {
			str1 = "select * from blog_remind where remindid=" + str2
					+ " and remindType=8";
			localRecordSet.execute(str1);
			if (!localRecordSet.next()) {
				str1 = "insert into blog_remind(remindid,relatedid,remindType,remindValue,createdate,createtime,status) values("
						+ str2
						+ ",0,8,'"
						+ i
						+ "','"
						+ str4
						+ "','"
						+ str5
						+ "',0)";

				localRecordSet.execute(str1);
			} else {
				String str6 = localRecordSet.getString("id");
				String str7 = localRecordSet.getString("createdate");
				if (!str4.equals(str7)) {
					str1 = "update blog_remind set createdate='" + str4
							+ "',createtime='" + str5 + "',remindValue='" + i
							+ "',status=0 where id=" + str6;
					localRecordSet.execute(str1);
				}
			}
		}
	}

	public void addRemind(String paramString1, String paramString2,
			String paramString3, String paramString4, String paramString5) {
		SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd");
		SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat(
				"HH:mm:ss");
		Date localDate = new Date();
		String str1 = localSimpleDateFormat1.format(localDate);
		String str2 = localSimpleDateFormat2.format(localDate);

		String str3 = "insert into blog_remind(remindid,relatedid,remindType,remindValue,createdate,createtime,status) values("
				+ paramString1
				+ ","
				+ paramString2
				+ ","
				+ paramString3
				+ ",'"
				+ paramString4
				+ "','"
				+ str1
				+ "','"
				+ str2
				+ "',"
				+ paramString5 + ")";

		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str3);
	}

	public boolean chkDiscussExits(String paramString1, String paramString2) {
		String str = "select * from blog_discuss where userid=" + paramString1
				+ " and workdate='" + paramString2 + "'";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		return localRecordSet.next();
	}

	public List getBlogDiscussVOList(String paramString, int paramInt1,
			int paramInt2) {
		return getBlogDiscussVOList(paramString, paramInt1, paramInt2, "all");
	}

	public List getBlogDiscussVOList(String paramString1, int paramInt1,
			int paramInt2, String paramString2) {
		ArrayList localArrayList = new ArrayList();
		RecordSet localRecordSet = new RecordSet();
		String str1 = "";

		BlogManager localBlogManager = new BlogManager();
		String str2 = localBlogManager.getMyAttentionByGroup(paramString1,
				paramString2);

		int i = getBlogDiscussCount(str2);
		int j = paramInt1 * paramInt2;
		int k = paramInt2;
		if (i - j + paramInt2 < paramInt2)
			k = i - j + paramInt2;
		if (i < paramInt2)
			k = i;
		if (localRecordSet.getDBType().equals("oracle")) {
			str1 = "select * from blog_discuss where userid in(" + str2
					+ ") order by id desc";
			str1 = "select t1.*,rownum rn from (" + str1
					+ ") t1 where rownum <= " + j;
			str1 = "select t2.* from (" + str1 + ") t2 where rn > "
					+ (j - paramInt2);
		} else {
			str1 = "select top "
					+ j
					+ " * from blog_discuss with(INDEX=blog_discuss_index) where userid in("
					+ str2 + ") order by id desc";
			str1 = "select top " + k + " t1.* from (" + str1
					+ ") t1  order by id asc";
			str1 = "select top " + k + " t2.* from (" + str1
					+ ") t2  order by id desc";
		}
		localRecordSet.executeSql(str1);
		while (localRecordSet.next()) {
			BlogDiscessVo localBlogDiscessVo = new BlogDiscessVo();

			String str3 = localRecordSet.getString("id");
			paramString1 = localRecordSet.getString("userid");
			String str4 = localRecordSet.getString("createdate");
			String str5 = localRecordSet.getString("createtime");
			String str6 = localRecordSet.getString("content");
			String str7 = localRecordSet.getString("lastUpdatetime");
			String str8 = localRecordSet.getString("isReplenish");
			String str9 = localRecordSet.getString("score");
			String str10 = localRecordSet.getString("workdate");
			String str11 = localRecordSet.getString("comefrom");

			localBlogDiscessVo.setId(str3);
			localBlogDiscessVo.setUserid(paramString1);
			localBlogDiscessVo.setWorkdate(str10);
			localBlogDiscessVo.setCreatedate(str4);
			localBlogDiscessVo.setCreatetime(str5);
			localBlogDiscessVo.setContent(str6);
			localBlogDiscessVo.setLastUpdatetime(str7);
			localBlogDiscessVo.setIsReplenish(str8);
			localBlogDiscessVo.setScore(str9);
			localBlogDiscessVo.setComefrom(str11);
			localArrayList.add(localBlogDiscessVo);
		}
		return localArrayList;
	}

	public List getBlogDiscussVOList(String paramString, int paramInt1,
			int paramInt2, int paramInt3, Map paramMap) {
		ArrayList localArrayList = new ArrayList();
		RecordSet localRecordSet = new RecordSet();
		String str1 = "";

		String str2 = "";
		if (paramMap != null) {
			str2 = str2 + "  userid in("
					+ (String) paramMap.get("attentionids") + ")";
			str2 = str2 + " and createdate>='"
					+ (String) paramMap.get("startDate") + "'";
			str2 = str2 + " and createdate<='"
					+ (String) paramMap.get("endDate") + "'";
			str2 = str2 + " and content like '%"
					+ (String) paramMap.get("content") + "%'";
		}

		int i = paramInt3;
		int j = paramInt1 * paramInt2;
		int k = paramInt2;
		if (i - j + paramInt2 < paramInt2)
			k = i - j + paramInt2;
		if (i < paramInt2)
			k = i;
		if (localRecordSet.getDBType().equals("oracle")) {
			str1 = "select * from blog_discuss where " + str2
					+ " order by id desc";
			str1 = "select t1.*,rownum rn from (" + str1
					+ ") t1 where rownum <= " + j;
			str1 = "select t2.* from (" + str1 + ") t2 where rn > "
					+ (j - paramInt2);
		} else {
			str1 = "select top "
					+ j
					+ " * from blog_discuss with(INDEX=blog_discuss_index) where "
					+ str2 + " order by id desc";
			str1 = "select top " + k + " t1.* from (" + str1
					+ ") t1  order by id asc";
			str1 = "select top " + k + " t2.* from (" + str1
					+ ") t2  order by id desc";
		}
		localRecordSet.executeSql(str1);
		while (localRecordSet.next()) {
			BlogDiscessVo localBlogDiscessVo = new BlogDiscessVo();

			String str3 = localRecordSet.getString("id");
			paramString = localRecordSet.getString("userid");
			String str4 = localRecordSet.getString("createdate");
			String str5 = localRecordSet.getString("createtime");
			String str6 = localRecordSet.getString("content");
			String str7 = localRecordSet.getString("lastUpdatetime");
			String str8 = localRecordSet.getString("isReplenish");
			String str9 = localRecordSet.getString("score");
			String str10 = localRecordSet.getString("workdate");
			String str11 = localRecordSet.getString("comefrom");

			localBlogDiscessVo.setId(str3);
			localBlogDiscessVo.setUserid(paramString);
			localBlogDiscessVo.setWorkdate(str10);
			localBlogDiscessVo.setCreatedate(str4);
			localBlogDiscessVo.setCreatetime(str5);
			localBlogDiscessVo.setContent(str6);
			localBlogDiscessVo.setLastUpdatetime(str7);
			localBlogDiscessVo.setIsReplenish(str8);
			localBlogDiscessVo.setScore(str9);
			localBlogDiscessVo.setComefrom(str11);
			localBlogDiscessVo
					.setIsHasLocation(isHasLocation(str3) ? "1" : "0");
			localArrayList.add(localBlogDiscessVo);
		}
		return localArrayList;
	}

	public int getBlogDiscussCount(String paramString) {
		RecordSet localRecordSet = new RecordSet();
		String str = "select count(id) as total from blog_discuss where userid in("
				+ paramString + ")";
		localRecordSet.execute(str);
		localRecordSet.next();
		int i = localRecordSet.getInt(1);
		return i;
	}

	public int getBlogDiscussCount(Map paramMap) {
		RecordSet localRecordSet = new RecordSet();
		String str1 = "";
		if (paramMap != null) {
			str1 = str1 + "  userid in("
					+ (String) paramMap.get("attentionids") + ")";
			str1 = str1 + " and createdate>='"
					+ (String) paramMap.get("startDate") + "'";
			str1 = str1 + " and createdate<='"
					+ (String) paramMap.get("endDate") + "'";
			str1 = str1 + " and content like '%"
					+ (String) paramMap.get("content") + "%'";
		}
		String str2 = "select count(id) as total from blog_discuss where "
				+ str1;
		localRecordSet.execute(str2);
		localRecordSet.next();
		int i = localRecordSet.getInt(1);
		return i;
	}

	public List getUpdateDiscussidList(String paramString) {
		String str = "SELECT remindValue FROM blog_remind WHERE  remindid="
				+ paramString + " AND remindType=6 order by id desc";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);

		ArrayList localArrayList = new ArrayList();
		while (localRecordSet.next()) {
			localArrayList.add(localRecordSet.getString("remindValue"));
		}
		return localArrayList;
	}

	public int getUpdateDiscussTotal(String paramString) {
		int i = 0;
		String str = "SELECT count(id) as total FROM blog_remind WHERE  remindid="
				+ paramString + " AND remindType=6";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		if (localRecordSet.next())
			i = localRecordSet.getInt("total");
		return i;
	}

	public int getUpdateMinRemindid(String paramString) {
		int i = 0;
		String str = "SELECT min(id) as minid FROM blog_remind WHERE  remindid="
				+ paramString + " AND remindType=6";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		if (localRecordSet.next()) {
			i = localRecordSet.getInt("minid");
			i = i == -1 ? 0 : i;
		}
		return i;
	}

	public int getUpdateMaxRemindid(String paramString) {
		int i = 0;
		String str = "SELECT max(id) as maxid FROM blog_remind WHERE  remindid="
				+ paramString + " AND remindType=6";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		if (localRecordSet.next())
			i = localRecordSet.getInt("maxid");
		return i;
	}

	public Map getUpdateDiscussVOList(String paramString, int paramInt1,
			int paramInt2) {
		HashMap localHashMap = new HashMap();
		ArrayList localArrayList = new ArrayList();
		RecordSet localRecordSet = new RecordSet();
		String str1 = "";

		BlogManager localBlogManager = new BlogManager();
		List localList = localBlogManager.getMyAttention(paramString, "all");
		String str2 = paramString;
		for (int i = 0; i < localList.size(); i++) {
			str2 = str2 + "," + localList.get(i);
		}
		if (localRecordSet.getDBType().equals("oracle")) {
			str1 = "select * from (select t1.*,t0.id AS remindid FROM (SELECT id,remindValue FROM blog_remind WHERE  remindid="
					+ paramString
					+ " AND remindType=6 and id<"
					+ paramInt2
					+ ") t0 LEFT JOIN blog_discuss t1 ON to_number(t0.remindValue)=t1.id ORDER BY remindid desc) where rownum<="
					+ paramInt1;
		} else {
			str1 = "select top "
					+ paramInt1
					+ " t1.*,t0.id AS remindid FROM (SELECT id,remindValue FROM blog_remind WHERE  remindid="
					+ paramString
					+ " AND remindType=6 and id<"
					+ paramInt2
					+ ") t0 LEFT JOIN blog_discuss t1 ON convert(INT ,t0.remindValue)=t1.id ORDER BY remindid desc";
		}

		localRecordSet.executeSql(str1);
		while (localRecordSet.next()) {
			BlogDiscessVo localBlogDiscessVo = new BlogDiscessVo();

			String str3 = localRecordSet.getString("id");
			paramString = localRecordSet.getString("userid");
			String str4 = localRecordSet.getString("createdate");
			String str5 = localRecordSet.getString("createtime");
			String str6 = localRecordSet.getString("content");
			String str7 = localRecordSet.getString("lastUpdatetime");
			String str8 = localRecordSet.getString("isReplenish");
			String str9 = localRecordSet.getString("score");
			String str10 = localRecordSet.getString("workdate");
			String str11 = localRecordSet.getString("comefrom");

			if (!str3.equals("")) {
				localBlogDiscessVo.setId(str3);
				localBlogDiscessVo.setUserid(paramString);
				localBlogDiscessVo.setWorkdate(str10);
				localBlogDiscessVo.setCreatedate(str4);
				localBlogDiscessVo.setCreatetime(str5);
				localBlogDiscessVo.setContent(str6);
				localBlogDiscessVo.setLastUpdatetime(str7);
				localBlogDiscessVo.setIsReplenish(str8);
				localBlogDiscessVo.setScore(str9);
				localBlogDiscessVo.setComefrom(str11);
				localBlogDiscessVo.setIsHasLocation(isHasLocation(str3) ? "1"
						: "0");

				localArrayList.add(localBlogDiscessVo);
				paramInt2 = localRecordSet.getInt("remindid");
			}
		}
		localHashMap.put("discussList", localArrayList);
		localHashMap.put("maxUpdateid", new Integer(paramInt2));

		return localHashMap;
	}

	public List getAttentionMapList(String paramString) {
		ArrayList localArrayList = new ArrayList();
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			DepartmentComInfo localDepartmentComInfo = new DepartmentComInfo();
			JobTitlesComInfo localJobTitlesComInfo = new JobTitlesComInfo();
			String str1 = localResourceComInfo.getDepartmentID(paramString);
			String str2 = localResourceComInfo.getSubCompanyID(paramString);
			String str3 = localResourceComInfo.getSeclevel(paramString);

			int i = getMyAttentionCount(paramString, "all");
			RecordSet localRecordSet = new RecordSet();
			String str4 = "','+t.managerstr+','";
			if (localRecordSet.getDBType().equals("oracle")) {
				str4 = "','||t.managerstr||','";
			}
			String str5 = " select * from (select id,lastname,status,managerid,managerstr,case when t1.blogid is not null or "
					+ str4
					+ " like '%,"
					+ paramString
					+ ",%' then 1 else 0 end as isshare,case when t2.attentionid is not null then 1 else 0 end as isattention,case when t4.attentionid is not null then 1 else 0 end as iscancel,case when t3.blogid is not null then 0 else 1 end as isnew from HrmResource t "
					+ " left join ("
					+ shareManager.getBlogShareSql(paramString)
					+ ") t1"
					+ " on t.id=t1.blogid"
					+ " left join (select distinct attentionid from blog_attention where userid="
					+ paramString
					+ ") t2"
					+ " on t.id=t2.attentionid"
					+ " left join (select distinct blogid from blog_read where userid="
					+ paramString
					+ ")  t3"
					+ " on t.id=t3.blogid"
					+ " left join (select distinct attentionid from blog_cancelAttention where userid="
					+ paramString
					+ ")  t4"
					+ " on t.id=t4.attentionid"
					+ " ) t0 where (status=0 or status=1 or status=2 or status=3) and ((isshare=1 and isattention=1) or managerid="
					+ paramString
					+ ") and iscancel=0 ORDER BY isnew desc,lastname asc";

			localRecordSet.execute(str5);

			while (localRecordSet.next()) {
				String str6 = localRecordSet.getString("id");
				String str7 = localRecordSet.getString("isnew");
				String str8 = localResourceComInfo.getLastname(str6);
				String str9 = localDepartmentComInfo
						.getDepartmentname(localResourceComInfo
								.getDepartmentID(str6));
				String str10 = localJobTitlesComInfo
						.getJobTitlesname(localResourceComInfo
								.getJobTitle(str6));
				localJobTitlesComInfo.getJobTitlesname(str10);

				HashMap localHashMap = new HashMap();
				localHashMap.put("attentionid", str6);
				localHashMap.put("isnew", str7);
				localHashMap.put("username", str8);
				localHashMap.put("deptName", str9);

				localArrayList.add(localHashMap);
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}

		return localArrayList;
	}

	public int getMyAttentionCount(String paramString) {
		return getMyAttentionCount(paramString, "all");
	}

	public int getMyAttentionCount(String paramString1, String paramString2) {
		return getMyAttentionCount(paramString1, paramString2, null);
	}

	public int getMyAttentionCount(String paramString1, String paramString2,
			Map paramMap) {
		if ((paramString2 == null) || (paramString2.equals(""))) {
			paramString2 = "all";
		}
		int i = 0;
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			String str1 = localResourceComInfo.getDepartmentID(paramString1);
			String str2 = localResourceComInfo.getSubCompanyID(paramString1);
			String str3 = localResourceComInfo.getSeclevel(paramString1);

			RecordSet localRecordSet = new RecordSet();
			String str4 = "','+t.managerstr+','";

			if (localRecordSet.getDBType().equals("oracle")) {
				str4 = "','||t.managerstr||','";
			}

			String str5 = " select count(1) as total from (select id,lastname,pinyinlastname,status,managerid,case when t1.blogid is not null or "
					+ str4
					+ " like '%,"
					+ paramString1
					+ ",%' then 1 else 0 end as isshare,case when t2.attentionid is not null or t.managerid="
					+ paramString1
					+ " then 1 else 0 end as isattention,case when t4.attentionid is not null then 1 else 0 end as iscancel,case when t5.specifiedid is not null then 1 else 0 end as isSpecified "
					+ (paramString2.equals("all") ? "" : ",groupid ")
					+ "from HrmResource t "
					+ " left join ("
					+ shareManager.getBlogShareSql(paramString1)
					+ ") t1"
					+ " on t.id=t1.blogid"
					+ " left join (select distinct attentionid from blog_attention where userid="
					+ paramString1
					+ ") t2"
					+ " on t.id=t2.attentionid"
					+ " left join (select distinct attentionid from blog_cancelAttention where userid="
					+ paramString1
					+ ")  t4"
					+ " on t.id=t4.attentionid"
					+ " left join ("
					+ shareManager.getSpecifiedShareSql(paramString1)
					+ ") t5"
					+ " on t.id=t5.specifiedid "
					+ (paramString2.equals("all") ? ""
							: new StringBuilder()
									.append(" left join (select a.userid,a.groupid from blog_userGroup a,blog_Group b where b.userid=")
									.append(paramString1)
									.append(" and a.groupid=b.id ) t6 on t.id=t6.userid ")
									.toString())
					+ " ) t0 where ((isshare=1 or isSpecified=1) and isattention=1) and iscancel=0 and (status=0 or status=1 or status=2 or status=3) ";

			if (paramString2.equals("nogroup")) {
				str5 = str5 + " and groupid is null ";
			} else if (!paramString2.equals("all")) {
				str5 = str5 + " and groupid=" + paramString2;
			}
			if (paramMap != null) {
				String str6 = Util.null2String((String) paramMap
						.get("userName"));
				if (!"".equals(str6)) {
					str5 = str5 + " and (lastname like '%" + str6
							+ "%' or pinyinlastname like '%" + str6 + "%')";
				}
			}
			localRecordSet.execute(str5);

			if (localRecordSet.next()) {
				i = localRecordSet.getInt("total");
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return i;
	}

	public List getAttentionMapList(String paramString, int paramInt1,
			int paramInt2, int paramInt3) {
		return getAttentionMapList(paramString, paramInt1, paramInt2,
				paramInt3, "all");
	}

	public List getAttentionMapList(String paramString1, int paramInt1,
			int paramInt2, int paramInt3, String paramString2) {
		return getAttentionMapList(paramString1, paramInt1, paramInt2,
				paramInt3, "all", null);
	}

	public List getAttentionMapList(String paramString1, int paramInt1,
			int paramInt2, int paramInt3, String paramString2, Map paramMap) {
		if ((paramString2 == null) || (paramString2.equals(""))) {
			paramString2 = "all";
		}
		ArrayList localArrayList = new ArrayList();
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			DepartmentComInfo localDepartmentComInfo = new DepartmentComInfo();
			JobTitlesComInfo localJobTitlesComInfo = new JobTitlesComInfo();
			String str1 = localResourceComInfo.getDepartmentID(paramString1);
			String str2 = localResourceComInfo.getSubCompanyID(paramString1);
			String str3 = localResourceComInfo.getSeclevel(paramString1);

			RecordSet localRecordSet = new RecordSet();
			String str4 = "','+t.managerstr+','";
			if (localRecordSet.getDBType().equals("oracle")) {
				str4 = "','||t.managerstr||','";
			}

			String str5 = " from (select id,lastname,pinyinlastname,status,managerid,managerstr,case when t.managerid="
					+ paramString1
					+ " then 1 else 0 end as islower,case when t1.blogid is not null or "
					+ str4
					+ " like '%,"
					+ paramString1
					+ ",%' then 1 else 0 end as isshare,case when t2.attentionid is not null or t.managerid="
					+ paramString1
					+ " then 1 else 0 end as isattention,case when t4.attentionid is not null then 1 else 0 end as iscancel,case when t3.blogid is not null then 0 else 1 end as isnew,case when t5.specifiedid is not null then 1 else 0 end as isSpecified"
					+ (paramString2.equals("all") ? "" : ",groupid ")
					+ " from HrmResource t "
					+ " left join ("
					+ shareManager.getBlogShareSql(paramString1)
					+ ") t1"
					+ " on t.id=t1.blogid"
					+ " left join (select distinct attentionid from blog_attention where userid="
					+ paramString1
					+ ") t2"
					+ " on t.id=t2.attentionid"
					+ " left join (select distinct blogid from blog_read where userid="
					+ paramString1
					+ ")  t3"
					+ " on t.id=t3.blogid"
					+ " left join (select distinct attentionid from blog_cancelAttention where userid="
					+ paramString1
					+ ")  t4"
					+ " on t.id=t4.attentionid"
					+ " left join ("
					+ shareManager.getSpecifiedShareSql(paramString1)
					+ ") t5"
					+ " on t.id=t5.specifiedid "
					+ (paramString2.equals("all") ? ""
							: " left join (select userid,groupid from blog_userGroup ) t6 on t.id=t6.userid ")
					+ " ) t0 where ((isSpecified=1 OR isshare=1) and isattention=1) and iscancel=0 and (status=0 or status=1 or status=2 or status=3) ";

			if (paramString2.equals("nogroup")) {
				str5 = str5 + " and groupid is null ";
			} else if (!paramString2.equals("all")) {
				str5 = str5 + " and groupid=" + paramString2;
			}
			if (paramMap != null) {
				String str6 = Util.null2String((String) paramMap
						.get("userName"));
				if (!"".equals(str6)) {
					str5 = str5 + " and (lastname like '%" + str6
							+ "%' or pinyinlastname like '%" + str6 + "%')";
				}
			}
			str5 = str5 + "ORDER BY isnew desc,lastname asc";

			int i = paramInt3;
			int j = paramInt1 * paramInt2;
			int k = paramInt2;
			if (i - j + paramInt2 < paramInt2)
				k = i - j + paramInt2;
			if (i < paramInt2) {
				k = i;
			}
			if (localRecordSet.getDBType().equals("oracle")) {
				str5 = "select * " + str5;
				str5 = "select t1.*,rownum rn from (" + str5
						+ ") t1 where rownum <= " + j;
				str5 = "select t2.* from (" + str5 + ") t2 where rn > "
						+ (j - paramInt2);
			} else {
				str5 = "select top " + j + " * " + str5;
				str5 = "select top " + k + " t1.* from (" + str5
						+ ") t1 order by t1.isnew asc,t1.lastname desc";
				str5 = "select top " + k + " t2.* from (" + str5
						+ ") t2 order by t2.isnew desc,t2.lastname asc";
			}
			localRecordSet.execute(str5);
			while (localRecordSet.next()) {
				String str7 = localRecordSet.getString("id");
				String str8 = localRecordSet.getString("isnew");
				String str9 = localResourceComInfo.getLastname(str7);
				String str10 = localDepartmentComInfo
						.getDepartmentname(localResourceComInfo
								.getDepartmentID(str7));
				String str11 = localJobTitlesComInfo
						.getJobTitlesname(localResourceComInfo
								.getJobTitle(str7));
				String str12 = localRecordSet.getString("isshare");
				String str13 = localRecordSet.getString("isSpecified");
				String str14 = localRecordSet.getString("isattention");
				String str15 = localRecordSet.getString("islower");
				String str16 = localRecordSet.getString("iscancel");

				HashMap localHashMap = new HashMap();
				localHashMap.put("attentionid", str7);
				localHashMap.put("isnew", str8);
				localHashMap.put("username", str9);
				localHashMap.put("deptName", str10);
				localHashMap.put("jobtitle", str11);
				localHashMap.put("isshare", str12);
				localHashMap.put("isSpecified", str13);
				localHashMap.put("isattention", str14);
				localHashMap.put("islower", str15);
				localHashMap.put("iscancel", str16);

				localArrayList.add(localHashMap);
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}

		return localArrayList;
	}

	public List getBlogMapList(String paramString1, String paramString2,
			Map paramMap) {
		return getBlogMapList(paramString1, paramString2, paramMap, "all");
	}

	public List getBlogMapList(String paramString1, String paramString2,
			Map paramMap, String paramString3) {
		String str1 = resourceComInfo.getDepartmentID(paramString1);
		String str2 = resourceComInfo.getSubCompanyID(paramString1);
		String str3 = resourceComInfo.getSeclevel(paramString1);

		ArrayList localArrayList = new ArrayList();
		String str4 = getSysSetting("allowRequest");

		String str5 = "','+t.managerstr+','";
		RecordSet localRecordSet = new RecordSet();

		if (localRecordSet.getDBType().equals("oracle")) {
			str5 = "','||t.managerstr||','";
		}

		String str6 = "select * from ( select distinct id,lastname,pinyinlastname,case when t.managerid="
				+ paramString1
				+ " then 1 else 0 end as islower,case when t1.blogid is not null or "
				+ str5
				+ " like '%,"
				+ paramString1
				+ ",%' then 1 else 0 end as isshare,case when t2.attentionid is not null or t.managerid="
				+ paramString1
				+ " then 1 else 0 end as isattention,case when t4.attentionid is not null then 1 else 0 end as iscancel,case when t3.blogid is not null then 0 else 1 end as isnew,case when t5.specifiedid is not null then 1 else 0 end as isSpecified"
				+ (paramString3.equals("all") ? "" : ",groupid ")
				+ " from HrmResource t "
				+ " left join ("
				+ shareManager.getBlogShareSql(paramString1)
				+ ") t1"
				+ " on t.id=t1.blogid"
				+ " left join (select distinct attentionid from blog_attention where userid="
				+ paramString1
				+ ") t2"
				+ " on t.id=t2.attentionid"
				+ " left join (select distinct blogid from blog_read where userid="
				+ paramString1
				+ ")  t3"
				+ " on t.id=t3.blogid"
				+ " left join (select distinct attentionid from blog_cancelAttention where userid="
				+ paramString1
				+ ")  t4"
				+ " on t.id=t4.attentionid"
				+ " left join ("
				+ shareManager.getSpecifiedShareSql(paramString1)
				+ ") t5"
				+ " on t.id=t5.specifiedid "
				+ (paramString3.equals("all") ? ""
						: new StringBuilder()
								.append(" left join (select a.userid,a.groupid from blog_userGroup a,blog_Group b where b.userid=")
								.append(paramString1)
								.append(" and a.groupid=b.id ) t6 on t.id=t6.userid ")
								.toString())
				+ " where (status=0 or status=1 or status=2 or status=3)) t0 where ";

		String str7;

		if (paramString2.equals("searchList")) {
			if (paramMap != null) {
				str7 = (String) paramMap.get("keyworkd");
				str6 = str6 + " (lastname like '%" + str7
						+ "%' or pinyinlastname like '%" + str7 + "%')";
			}
		} else if (paramString2.equals("attention")) {
			str6 = str6
					+ " ((isshare=1 or isSpecified=1) and isattention=1) and iscancel=0 ";
			if (paramString3.equals("nogroup")) {
				str6 = str6 + " and groupid is null ";
			} else if (!paramString3.equals("all"))
				str6 = str6 + " and groupid=" + paramString3;
		} else if (paramString2.equals("canview")) {
			str6 = str6 + " (isshare=1 or isSpecified=1)";
			if (paramMap != null) {
				str7 = (String) paramMap.get("keyworkd");
				str6 = str6 + " and (lastname like '%" + str7
						+ "%' or pinyinlastname like '%" + str7 + "%')";
			}
		}

		str6 = str6
				+ " order by isnew desc ,isattention desc,isshare desc,lastname asc";

		localRecordSet.execute(str6);
		while (localRecordSet.next()) {
			str7 = localRecordSet.getString("id");
			String str8 = localRecordSet.getString("isnew");
			String str9 = localRecordSet.getString("isshare");
			String str10 = localRecordSet.getString("isSpecified");
			String str11 = localRecordSet.getString("isattention");
			String str12 = localRecordSet.getString("islower");
			String str13 = localRecordSet.getString("iscancel");

			if (!str7.equals(paramString1)) {
				String str14 = resourceComInfo.getLastname(str7);
				String str15 = departmentComInfo
						.getDepartmentname(resourceComInfo
								.getDepartmentID(str7));
				String str16 = jobTitlesComInfo
						.getJobTitlesname(resourceComInfo.getJobTitle(str7));

				HashMap localHashMap = new HashMap();
				localHashMap.put("attentionid", str7);
				localHashMap.put("isnew", str8);
				localHashMap.put("isshare", str9);
				localHashMap.put("isSpecified", str10);
				localHashMap.put("isattention", str11);
				localHashMap.put("islower", str12);
				localHashMap.put("iscancel", str13);
				localHashMap.put("deptName", str15);
				localHashMap.put("jobtitle", str16);

				localArrayList.add(localHashMap);
			}
		}
		return localArrayList;
	}

	public Map getBlogMapList(String paramString1, String paramString2,
			Map paramMap, int paramInt1, int paramInt2) {
		HashMap localHashMap1 = new HashMap();
		String str1 = resourceComInfo.getDepartmentID(paramString1);
		String str2 = resourceComInfo.getSubCompanyID(paramString1);
		String str3 = resourceComInfo.getSeclevel(paramString1);

		ArrayList localArrayList = new ArrayList();
		String str4 = getSysSetting("allowRequest");

		String str5 = "','+t.managerstr+','";
		RecordSet localRecordSet = new RecordSet();

		if (localRecordSet.getDBType().equals("oracle")) {
			str5 = "','||t.managerstr||','";
		}

		String str6 = "( select id,lastname,case when t.managerid="
				+ paramString1
				+ " then 1 else 0 end as islower,case when t1.blogid is not null or "
				+ str5
				+ " like '%,"
				+ paramString1
				+ ",%' then 1 else 0 end as isshare,case when t2.attentionid is not null or t.managerid="
				+ paramString1
				+ " then 1 else 0 end as isattention,case when t4.attentionid is not null then 1 else 0 end as iscancel,case when t3.blogid is not null then 0 else 1 end as isnew,case when t5.specifiedid is not null then 1 else 0 end as isSpecified from HrmResource t "
				+ " left join ("
				+ shareManager.getBlogShareSql(paramString1)
				+ ") t1"
				+ " on t.id=t1.blogid"
				+ " left join (select distinct attentionid from blog_attention where userid="
				+ paramString1
				+ ") t2"
				+ " on t.id=t2.attentionid"
				+ " left join (select distinct blogid from blog_read where userid="
				+ paramString1
				+ ")  t3"
				+ " on t.id=t3.blogid"
				+ " left join (select distinct attentionid from blog_cancelAttention where userid="
				+ paramString1 + ")  t4" + " on t.id=t4.attentionid"
				+ " left join ("
				+ shareManager.getSpecifiedShareSql(paramString1) + ") t5"
				+ " on t.id=t5.specifiedid "
				+ " where (status=0 or status=1 or status=2 or status=3)) t0 ";

		String str7 = "";

		if (paramString2.equals("searchList")) {
			String str8 = (String) paramMap.get("keyworkd");
			str7 = str7 + " lastname like '%" + str8 + "%'";
		} else if (paramString2.equals("attention")) {
			str7 = str7
					+ " ((isshare=1 or isSpecified=1) and isattention=1) and iscancel=0 ";
		} else {
			str7 = str7 + " isshare=1 or isSpecified=1";
		}
		String str8 = "id";

		String str9 = "id,lastname,islower,isshare,isattention,iscancel,isnew,isSpecified";
		SplitPageParaBean localSplitPageParaBean = new SplitPageParaBean();
		SplitPageUtil localSplitPageUtil = new SplitPageUtil();

		localSplitPageParaBean.setBackFields(str9);
		localSplitPageParaBean.setSqlFrom(str6);
		localSplitPageParaBean.setSqlWhere(str7);
		localSplitPageParaBean.setPrimaryKey(str8);

		LinkedHashMap localLinkedHashMap = new LinkedHashMap();
		localSplitPageParaBean.getClass();
		localLinkedHashMap.put("isnew", Integer.valueOf(1));
		localSplitPageParaBean.getClass();
		localLinkedHashMap.put("isshare", Integer.valueOf(1));
		localSplitPageParaBean.getClass();
		localLinkedHashMap.put("lastname", Integer.valueOf(0));

		localSplitPageParaBean.setOrderByMap(localLinkedHashMap);

		localSplitPageParaBean.getClass();
		localSplitPageParaBean.setSortWay(0);

		localSplitPageUtil.setSpp(localSplitPageParaBean);

		int i = localSplitPageUtil.getRecordCount();

		localRecordSet = localSplitPageUtil.getCurrentPageRsNew(paramInt1,
				paramInt2);
		while (localRecordSet.next()) {
			String str10 = localRecordSet.getString("id");
			String str11 = localRecordSet.getString("isnew");
			String str12 = localRecordSet.getString("isshare");
			String str13 = localRecordSet.getString("isSpecified");
			String str14 = localRecordSet.getString("isattention");
			String str15 = localRecordSet.getString("islower");
			String str16 = localRecordSet.getString("iscancel");

			if (!str10.equals(paramString1)) {
				String str17 = resourceComInfo.getLastname(str10);
				String str18 = departmentComInfo
						.getDepartmentname(resourceComInfo
								.getDepartmentID(str10));
				String str19 = jobTitlesComInfo
						.getJobTitlesname(resourceComInfo.getJobTitle(str10));
				jobTitlesComInfo.getJobTitlesname(str19);

				HashMap localHashMap2 = new HashMap();
				localHashMap2.put("attentionid", str10);
				localHashMap2.put("isnew", str11);
				localHashMap2.put("isshare", str12);
				localHashMap2.put("isSpecified", str13);
				localHashMap2.put("isattention", str14);
				localHashMap2.put("islower", str15);
				localHashMap2.put("iscancel", str16);

				localArrayList.add(localHashMap2);
			}
		}
		localHashMap1.put("list", localArrayList);
		localHashMap1.put("total", Integer.valueOf(i));

		return localHashMap1;
	}

	public int isNeedSubmit(User paramUser, String paramString) {
		int i = 0;
		String str1 = getSysSetting("enableDate");
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		try {
			Date localDate1 = localSimpleDateFormat.parse(str1);
			Date localDate2 = localSimpleDateFormat.parse(paramString);
			if (localDate1.getTime() <= localDate2.getTime()) {
				String str2 = "" + paramUser.getUID();

				BlogDiscessVo localBlogDiscessVo = getDiscussVoByDate(str2,
						paramString);
				if (localBlogDiscessVo != null) {
					i = 1;
				} else if (getReplyList(str2, paramString, str2).size() > 0) {
					i = 4;
				}

			} else {
				i = 3;
			}
		} catch (ParseException localParseException) {
			localParseException.printStackTrace();
			writeLog(localParseException);
		}

		return i;
	}

	public int getMyBlogCount(String paramString) {
		String str = "select count(1) as total from blog_discuss where userid="
				+ paramString;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		int i = 0;
		if (localRecordSet.next())
			i = localRecordSet.getInt("total");
		return i;
	}

	public int getMyBlogCount(String paramString, Map paramMap) {
		String str1 = "";
		if (paramMap != null) {
			str1 = str1 + " and workdate>="
					+ (String) paramMap.get("startDate");
			str1 = str1 + " and workdate<=" + (String) paramMap.get("endDate");
			str1 = str1 + " and content like '%"
					+ (String) paramMap.get("content") + "%'";
		}

		String str2 = "select count(1) as total from blog_discuss where userid="
				+ paramString;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str2);
		int i = 0;
		if (localRecordSet.next())
			i = localRecordSet.getInt("total");
		return i;
	}

	public List getAttentionMe(String paramString) {
		RecordSet localRecordSet = new RecordSet();
		BlogShareManager localBlogShareManager = new BlogShareManager();
		List localList = localBlogShareManager.getShareList(paramString);
		String str1 = "select distinct userid from blog_attention where attentionid="
				+ paramString;
		localRecordSet.execute(str1);

		ArrayList localArrayList = new ArrayList();
		Object localObject;
		while (localRecordSet.next()) {
			localObject = localRecordSet.getString("userid");
			if (localList.contains(localObject)) {
				localArrayList.add(localObject);
			}
		}
		try {
			localObject = new ResourceComInfo();
			String str2 = ((ResourceComInfo) localObject)
					.getManagerID(paramString);

			str1 = "select userid from blog_cancelAttention where userid="
					+ str2 + " and attentionid=" + paramString;
			localRecordSet.execute(str1);

			if ((!localRecordSet.next()) && (!localArrayList.contains(str2))
					&& (!"0".equals(str2)) && (!paramString.equals(str2))) {
				localArrayList.add(str2);
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}

		return localArrayList;
	}

	public String getBlogStatus() {
		String str = "0";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute("select id from LeftMenuInfo where id=392");
		if (localRecordSet.next())
			str = "1";
		return str;
	}

	public boolean blogAppRightCheck(String paramString,
			Map<String, String> paramMap) {
		String str = (String) paramMap.get("linkType");
		int i = Util.getIntValue((String) paramMap.get("linkid"), 0);
		int j = Util.getIntValue((String) paramMap.get("discussid"), 0);

		return appViewRight(str, paramString, i, j);
	}

	public boolean appViewRight(String paramString1, String paramString2,
			int paramInt1, int paramInt2) {
		boolean bool = false;
		BlogDiscessVo localBlogDiscessVo = getDiscussVo("" + paramInt2);
		if (localBlogDiscessVo != null) {
			String str1 = localBlogDiscessVo.getUserid();
			BlogShareManager localBlogShareManager = new BlogShareManager();
			List localList = localBlogShareManager.getShareList(str1);
			String str2 = localBlogDiscessVo.getContent();
			if ((localList.contains(paramString2))
					&& (str2.indexOf("linkid=\"" + paramInt1 + "\"") != -1))
				bool = true;
		}
		return bool;
	}

	public String appRightFilter(String paramString1, String paramString2,
			String paramString3) {
		if (paramString2.equals("crm")) {
			return weaver.crm.customer.CustomerShareUtil.customerRightFilter(
					paramString1, paramString3);
		}
		return paramString3;
	}

	public String getAttachmentLink(int paramInt, User paramUser)
			throws Exception {
		RecordSet localRecordSet = new RecordSet();
		DocImageManager localDocImageManager = new DocImageManager();
		String str1 = "";
		localRecordSet
				.executeSql("select id,docsubject,accessorycount from docdetail where id="
						+ paramInt);
		int i = -1;
		if (localRecordSet.next()) {
			i++;
			String str2 = Util.null2String(localRecordSet.getString(1));
			String str3 = Util.toScreen(localRecordSet.getString(2),
					paramUser.getLanguage());
			int j = localRecordSet.getInt(3);

			localDocImageManager.resetParameter();
			localDocImageManager.setDocid(Integer.parseInt(str2));
			localDocImageManager.selectDocImageInfo();

			String str4 = "";
			long l = 0L;
			String str5 = "";
			String str6 = "";
			int k = 0;

			if (localDocImageManager.next()) {
				str4 = localDocImageManager.getImagefileid();
				l = localDocImageManager.getImageFileSize(Util
						.getIntValue(str4));
				str5 = localDocImageManager.getImagefilename();
				str6 = str5.substring(str5.lastIndexOf(".") + 1).toLowerCase();
				k = localDocImageManager.getVersionId();
			}
			if (j > 1) {
				str6 = "htm";
			}

			if ((j == 1)
					&& ((str6.equalsIgnoreCase("xls"))
							|| (str6.equalsIgnoreCase("doc"))
							|| (str6.equalsIgnoreCase("xlsx")) || (str6
								.equalsIgnoreCase("docx")))) {
				str1 = str1
						+ "<a href='javascript:void(0)' unselectable='off' contenteditable='false' href='javascript:void(0)' linkid="
						+ str2
						+ " onclick=\"try{opendoc('"
						+ str2
						+ "','"
						+ k
						+ "','"
						+ str4
						+ "',this);return false;}catch(e){}\" unselectable='off' contenteditable='false' style='cursor:pointer;text-decoration:underline !important;margin-right:8px'>"
						+ str5 + "</a>";
			} else
				str1 = str1
						+ "<a href='javascript:void(0)' unselectable='off' contenteditable='false' href='javascript:void(0)' linkid="
						+ str2
						+ " onclick=\"try{opendoc1('"
						+ str2
						+ "',this);return false;}catch(e){}\"  style='cursor:pointer;text-decoration:underline !important;margin-right:8px'>"
						+ str3 + "." + str6 + "</a>";
			if (j == 1) {
				str1 = str1
						+ "&nbsp;<a href='javascript:void(0)' unselectable='off' contenteditable='false' href='javascript:void(0)' linkid="
						+ str2
						+ " onclick=\"try{downloads('"
						+ str4
						+ "',this,'"
						+ str3
						+ "."
						+ str6
						+ "');return false;}catch(e){}\"  style='cursor:pointer;text-decoration:underline !important;margin-right:8px'>"
						+ SystemEnv.getHtmlLabelName(258,
								paramUser.getLanguage()) + "(" + l / 1000L
						+ "K)</a></br>";
			}
		}
		return str1;
	}

	public void delUpdateRemind(String paramString) {
		BlogShareManager localBlogShareManager = new BlogShareManager();
		List localList = localBlogShareManager.getShareList(paramString);
		String str = "";
		for (int i = 0; i < localList.size(); i++) {
			str = str + "," + localList.get(i);
		}
		if (str.length() > 0) {
			str = str.substring(1);
		} else
			return;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute("delete from blog_remind where relatedid="
				+ paramString + " and remindType=6 and remindid not in(" + str
				+ ")");
	}

	public void delInvalidRemind(String paramString) {
		BlogManager localBlogManager = new BlogManager();
		List localList = localBlogManager.getMyAttention(paramString, "all");
		String str = "";
		for (int i = 0; i < localList.size(); i++) {
			str = str + "," + localList.get(i);
		}

		if (str.length() > 0) {
			str = str.substring(1);
			RecordSet localRecordSet = new RecordSet();
			localRecordSet.execute("delete from blog_remind where remindid ="
					+ paramString + " and remindType=6 and relatedid not in("
					+ str + ")");
		}
	}

	public void saveNotes(String paramString1, String paramString2,
			String paramString3) {
		ConnStatement localConnStatement = new ConnStatement();
		try {
			SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			String str1 = localSimpleDateFormat.format(new Date());
			RecordSet localRecordSet = new RecordSet();
			String str2 = "select id,updateDate,content from blog_notes where userid="
					+ paramString1;
			localRecordSet.execute(str2);
			if (localRecordSet.next()) {
				String str3 = localRecordSet.getString("updateDate");
				String str4 = localRecordSet.getString("content");
				if (paramString3.equals("append")) {
					if ((!str1.equals(str3)) || (str4.equals(""))) {
						paramString2 = str4
								+ "<br><span style=\"font-weight:bold\">"
								+ str1
								+ ":</span><br><br>&nbsp;&nbsp;&nbsp;&nbsp;"
								+ paramString2;
					} else
						paramString2 = str4
								+ "<br><br>&nbsp;&nbsp;&nbsp;&nbsp;"
								+ paramString2;
				}
				str2 = "update blog_notes set content=?,updateDate=? where userid=?";
				localConnStatement.setStatementSql(str2);
				localConnStatement.setString(1, "" + paramString2);
				localConnStatement.setString(2, "" + str1);
				localConnStatement.setString(3, "" + paramString1);
			} else {
				paramString2 = "<span style=\"font-weight:bold\">" + str1
						+ ":</span><br><br>&nbsp;&nbsp;&nbsp;&nbsp;"
						+ paramString2;
				str2 = "insert into blog_notes(userid,content,updateDate) values(?,?,?)";
				localConnStatement.setStatementSql(str2);
				localConnStatement.setString(1, "" + paramString1);
				localConnStatement.setString(2, "" + paramString2);
				localConnStatement.setString(3, "" + str1);
			}
			localConnStatement.executeUpdate();
		} catch (Exception localException) {
			localException.printStackTrace();
		} finally {
			localConnStatement.close();
		}
	}

	public Map getNotes(String paramString) {
		HashMap localHashMap = new HashMap();
		String str1 = "select * from blog_notes where userid=" + paramString;
		String str2 = "";
		String str3 = "";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str1);
		if (localRecordSet.next()) {
			str2 = localRecordSet.getString("content");
			str3 = localRecordSet.getString("updateDate");
		}
		localHashMap.put("content", str2);
		localHashMap.put("updateDate", str3);
		return localHashMap;
	}

	public boolean addTemp(String paramString1, String paramString2,
			String paramString3, String paramString4, String paramString5,
			String paramString6) {
		RecordSet localRecordSet = new RecordSet();
		boolean bool = true;
		Object localObject1;
		Object localObject2;
		if (localRecordSet.getDBType().equals("oracle")) {
			localObject1 = "insert into blog_template(tempName,tempDesc,isSystem,isUsed,tempContent,userId) values('"
					+ paramString1
					+ "','"
					+ paramString2
					+ "',"
					+ paramString3
					+ ","
					+ paramString4
					+ ",empty_clob(),"
					+ paramString6
					+ ")";
			bool = localRecordSet.execute((String) localObject1);
			localObject1 = "select tempContent from  blog_template where id = (select max(id) from blog_template) for update ";
			localObject2 = new ConnStatement();
			try {
				((ConnStatement) localObject2)
						.setStatementSql((String) localObject1);
				((ConnStatement) localObject2).executeQuery();
				((ConnStatement) localObject2).next();
				CLOB localCLOB = ((ConnStatement) localObject2).getClob(1);

				char[] arrayOfChar = paramString5.toCharArray();
				Writer localWriter = localCLOB.getCharacterOutputStream();
				localWriter.write(arrayOfChar);
				localWriter.flush();
				localWriter.close();
			} catch (Exception localException2) {
				writeLog(localException2);
			} finally {
				((ConnStatement) localObject2).close();
			}
		} else {
			localObject1 = new ConnStatement();
			try {
				localObject2 = "insert into blog_template(tempName,tempDesc,isSystem,isUsed,tempContent,userId) values(?,?,?,?,?,?)";
				((ConnStatement) localObject1)
						.setStatementSql((String) localObject2);
				((ConnStatement) localObject1).setString(1, "" + paramString1);
				((ConnStatement) localObject1).setString(2, "" + paramString2);
				((ConnStatement) localObject1).setString(3, "" + paramString3);
				((ConnStatement) localObject1).setString(4, "" + paramString4);
				((ConnStatement) localObject1).setString(5, "" + paramString5);
				((ConnStatement) localObject1).setString(6, paramString6);
				((ConnStatement) localObject1).executeUpdate();
			} catch (Exception localException1) {
				writeLog(localException1);
			} finally {
				((ConnStatement) localObject1).close();
			}
		}

		return bool;
	}

	public boolean updateTemp(String paramString1, String paramString2,
			String paramString3, String paramString4, String paramString5,
			String paramString6) {
		RecordSet localRecordSet = new RecordSet();
		boolean bool = true;
		Object localObject1;
		Object localObject2;
		if (localRecordSet.getDBType().equals("oracle")) {
			localObject1 = "update blog_template set tempName='" + paramString2
					+ "', tempDesc = '" + paramString3 + "' ,isUsed="
					+ paramString4 + ",tempContent=empty_clob() , userId = "
					+ paramString6 + " where id=" + paramString1;
			bool = localRecordSet.execute((String) localObject1);
			localObject1 = "select tempContent from  blog_template where id ="
					+ paramString1 + " for update ";
			localObject2 = new ConnStatement();
			try {
				((ConnStatement) localObject2)
						.setStatementSql((String) localObject1);
				((ConnStatement) localObject2).executeQuery();
				((ConnStatement) localObject2).next();
				CLOB localCLOB = ((ConnStatement) localObject2).getClob(1);

				char[] arrayOfChar = paramString5.toCharArray();
				Writer localWriter = localCLOB.getCharacterOutputStream();
				localWriter.write(arrayOfChar);
				localWriter.flush();
				localWriter.close();
			} catch (Exception localException2) {
				writeLog(localException2);
			} finally {
				((ConnStatement) localObject2).close();
			}
		} else {
			localObject1 = new ConnStatement();
			try {
				localObject2 = "update blog_template set tempName=?,tempDesc =?,isUsed=?,tempContent=? , userId = "
						+ paramString6 + " where id=" + paramString1;
				((ConnStatement) localObject1)
						.setStatementSql((String) localObject2);
				((ConnStatement) localObject1).setString(1, "" + paramString2);
				((ConnStatement) localObject1).setString(2, "" + paramString3);
				((ConnStatement) localObject1).setString(3, "" + paramString4);
				((ConnStatement) localObject1).setString(4, "" + paramString5);
				((ConnStatement) localObject1).executeUpdate();
			} catch (Exception localException1) {
				writeLog(localException1);
			} finally {
				((ConnStatement) localObject1).close();
			}
		}

		return bool;
	}

	public void addTempShare(String paramString, FileUpload paramFileUpload) {
		String str1 = paramFileUpload.getParameter("shareOperation");
		String[] arrayOfString1 = paramFileUpload.getParameterValues("shareid");

		String[] arrayOfString2 = paramFileUpload
				.getParameterValues("sharetype");
		String[] arrayOfString3 = paramFileUpload
				.getParameterValues("relatedshareid");
		String[] arrayOfString4 = paramFileUpload
				.getParameterValues("secLevel");
		String[] arrayOfString5 = paramFileUpload
				.getParameterValues("secLevelMax");
		RecordSet localRecordSet = new RecordSet();
		String str2 = "";

		if ((arrayOfString2 != null) && (arrayOfString2.length > 0)) {
			for (int i = 0; i < arrayOfString2.length; i++) {
				String str3 = Util.null2String(arrayOfString1[i]);
				String str4 = Util.null2String(arrayOfString2[i]);
				String str5 = Util.null2String(arrayOfString3[i]);
				String str6 = Math.min(Util.getIntValue(arrayOfString4[i]),
						Util.getIntValue(arrayOfString5[i])) + "";
				String str7 = Math.max(Util.getIntValue(arrayOfString4[i]),
						Util.getIntValue(arrayOfString5[i])) + "";

				if (("6".equals(str4)) || (!"".equals(str5))
						|| (!str3.equals("0"))) {
					if (str3.equals("0")) {
						str2 = "insert into blog_tempShare (tempid,type,content,seclevel,seclevelMax) values ("
								+ paramString
								+ ","
								+ str4
								+ ",'"
								+ str5
								+ "',"
								+ str6 + "," + str7 + ")";
					} else if ((str4.equals("1")) || (str4.equals("2"))
							|| (str4.equals("3")) || (str4.equals("4"))
							|| (str4.equals("5"))) {
						str2 = "update blog_tempShare set content='" + str5
								+ "' where id=" + str3;
					}
					localRecordSet.executeSql(str2);
				}
			}
		}
	}

	public ArrayList getTempShareList(String paramString) {
		ArrayList localArrayList = new ArrayList();
		HashMap localHashMap = new HashMap();

		RecordSet localRecordSet = new RecordSet();
		String str1 = "select * from blog_tempshare where tempid="
				+ paramString;
		localRecordSet.execute(str1);

		while (localRecordSet.next()) {
			localHashMap = new HashMap();
			String str2 = Util.null2String(localRecordSet.getString("id"));
			String str3 = Util.null2String(localRecordSet.getString("type"));
			String str4 = Util.null2String(localRecordSet.getString("content"));
			String str5 = Util
					.null2String(localRecordSet.getString("seclevel"));
			String str6 = Util.null2String(localRecordSet
					.getString("sharelevel"));
			String str7 = Util.null2String(localRecordSet
					.getString("seclevelMax"));
			localHashMap.put("shareid", str2);
			if ("1".equals(str3)) {
				localHashMap.put("typeName", "1867");
				str5 = "";
				localHashMap.put("contentName",
						resourceComInfo.getLastnames(str4));
			} else if ("2".equals(str3)) {
				localHashMap.put("typeName", "141");
				localHashMap.put("contentName",
						subCompanyComInfo.getSubcompanynames(str4));
			} else if ("3".equals(str3)) {
				localHashMap.put("typeName", "124");
				localHashMap.put("contentName",
						departmentComInfo.getDeptnames(str4));
			} else {
				String[] arrayOfString;
				String str8;
				int i;
				if ("4".equals(str3)) {
					localHashMap.put("typeName", "122");
					arrayOfString = str4.split(",");
					str8 = "";

					for (i = 0; i < arrayOfString.length; i++) {
						if (!"".equals(arrayOfString[i]))
							str8 = str8
									+ ","
									+ Util.toHtml(rolesComInfo
											.getRolesRemark(arrayOfString[i]));
					}
					localHashMap.put("contentName", str8.substring(1));
				} else if ("5".equals(str3)) {
					localHashMap.put("typeName", "6086");
					arrayOfString = str4.split(",");
					str8 = "";

					for (i = 0; i < arrayOfString.length; i++) {
						if (!"".equals(arrayOfString[i]))
							str8 = str8
									+ ","
									+ Util.toHtml(jobTitlesComInfo
											.getJobTitlesname(arrayOfString[i]));
					}
					localHashMap.put("contentName", str8.substring(1));
				} else if ("6".equals(str3)) {
					localHashMap.put("typeName", "1340");
					localHashMap.put("contentName", "");
				}
			}
			localHashMap.put("seclevel", str5);
			localHashMap.put("seclevelMax", str7);

			localHashMap.put("content", str4);

			localHashMap.put("type", "" + str3);
			localArrayList.add(localHashMap);
		}
		return localArrayList;
	}

	public boolean deleteTempShare(String paramString) {
		String str = "delete from blog_tempShare where id=" + paramString;
		RecordSet localRecordSet = new RecordSet();
		boolean bool = localRecordSet.execute(str);
		return bool;
	}

	public boolean deleteTemp(String paramString) {
		String str = "delete from blog_template where id in (" + paramString
				+ ")";
		RecordSet localRecordSet = new RecordSet();
		boolean bool = localRecordSet.execute(str);
		str = "delete from blog_tempShare where tempid in (" + paramString
				+ ")";
		localRecordSet.execute(str);
		return bool;
	}

	public String getTemplateTable(String paramString) {
		ArrayList localArrayList = new ArrayList();
		String str1 = resourceComInfo.getDepartmentID(paramString);
		String str2 = resourceComInfo.getSubCompanyID(paramString);
		String str3 = resourceComInfo.getSeclevel(paramString);
		String str4 = resourceComInfo.getJobTitle(paramString);

		RecordSet localRecordSet = new RecordSet();
		String str5 = " content like '%,'+CAST (roleid AS VARCHAR(100))+',%' ";
		if (localRecordSet.getDBType().equals("oracle")) {
			str5 = " nvl(content,0) like '%,'||CAST (roleid AS VARCHAR(100))||',%' ";
		}

		String str6 = "(select distinct tempid from blog_tempShare where (type=1 and  content like '%,"
				+ paramString
				+ ",%' ) "
				+ "or (type=2 and content like '%,"
				+ str2
				+ ",%'  and "
				+ str3
				+ ">=seclevel and "
				+ str3
				+ " <= seclevelMax)  "
				+ "or (type=3 and content like '%,"
				+ str1
				+ ",%' and "
				+ str3
				+ ">=seclevel and "
				+ str3
				+ " <= seclevelMax)"
				+ " or (type=4 and exists (select roleid from hrmrolemembers  where resourceid="
				+ paramString
				+ "  and "
				+ str5
				+ ") and "
				+ str3
				+ ">=seclevel and "
				+ str3
				+ " <= seclevelMax)"
				+ " or (type=5 and content like '%,"
				+ str4
				+ ",%' and "
				+ str3
				+ ">=seclevel and "
				+ str3
				+ " <= seclevelMax)"
				+ " or (type=6 and "
				+ str3
				+ ">=seclevel and "
				+ str3
				+ " <= seclevelMax))";

		return str6;
	}

	public List getTemplate(String paramString) {
		ArrayList localArrayList = new ArrayList();

		String str1 = resourceComInfo.getDepartmentID(paramString);
		String str2 = resourceComInfo.getSubCompanyID(paramString);
		String str3 = resourceComInfo.getSeclevel(paramString);
		String str4 = resourceComInfo.getJobTitle(paramString);
		RecordSet localRecordSet = new RecordSet();

		String str5 = "select * from blog_template where isUsed = 1 and id = (SELECT templateId FROM blog_templateUser where userId= "
				+ paramString + ") " + " union all ";

		if (localRecordSet.getDBType().equals("oracle")) {
			str5 = str5 + "select * from  (select t.* from blog_template t ";
		} else {
			str5 = str5
					+ "select * from (select TOP (SELECT count(*) FROM blog_template) t.* from blog_template t ";
		}

		str5 = str5
				+ " left join (select distinct tempid from blog_tempShare where (type=1 and  content like '%,"
				+ paramString
				+ ",%' ) or (type=2 and content like '%,"
				+ str2
				+ ",%'  and "
				+ str3
				+ ">=seclevel and "
				+ str3
				+ " <= seclevelMax)  or (type=3 and content like '%,"
				+ str1
				+ ",%' and "
				+ str3
				+ ">=seclevel and "
				+ str3
				+ " <= seclevelMax) or (type=4 and exists (select roleid from hrmrolemembers  where resourceid="
				+ paramString
				+ "  and Cast(roleid as varchar(100)) in(content)) and "
				+ str3
				+ ">=seclevel and "
				+ str3
				+ " <= seclevelMax) or (type=5 and content like '%,"
				+ str4
				+ ",%' and "
				+ str3
				+ ">=seclevel and "
				+ str3
				+ " <= seclevelMax) or (type=6 and "
				+ str3
				+ ">=seclevel and "
				+ str3
				+ " <= seclevelMax)) t1"
				+ " on t.id=t1.tempid WHERE isUsed = 1 and (t.id = t1.tempid or (isSystem = 0 and userId = '"
				+ paramString + "')) order by id desc) t10";

		boolean bool = localRecordSet.execute(str5);
		while (localRecordSet.next()) {
			String str6 = localRecordSet.getString("id");
			String str7 = localRecordSet.getString("tempName");
			String str8 = localRecordSet.getString("tempContent");

			HashMap localHashMap = new HashMap();
			localHashMap.put("tempid", str6);
			localHashMap.put("tempName", str7);
			localHashMap.put("tempContent", str8);

			localArrayList.add(localHashMap);
		}
		return localArrayList;
	}

	public void addSpecifiedShare(String paramString, FileUpload paramFileUpload) {
		String str1 = paramFileUpload.getParameter("shareOperation");
		String[] arrayOfString1 = paramFileUpload.getParameterValues("shareid");

		String[] arrayOfString2 = paramFileUpload
				.getParameterValues("sharetype");
		String[] arrayOfString3 = paramFileUpload
				.getParameterValues("relatedshareid");
		String[] arrayOfString4 = paramFileUpload
				.getParameterValues("seclevel");
		String[] arrayOfString5 = paramFileUpload
				.getParameterValues("seclevelMax");

		RecordSet localRecordSet = new RecordSet();
		String str2 = "";

		if ((arrayOfString2 != null) && (arrayOfString2.length > 0))
			for (int i = 0; i < arrayOfString2.length; i++) {
				String str3 = Util.null2String(arrayOfString1[i]);
				String str4 = Util.null2String(arrayOfString2[i]);
				String str5 = Util.null2String(arrayOfString3[i]);
				String str6 = Math.min(Util.getIntValue(arrayOfString4[i]),
						Util.getIntValue(arrayOfString5[i])) + "";
				String str7 = Math.max(Util.getIntValue(arrayOfString4[i]),
						Util.getIntValue(arrayOfString5[i])) + "";

				if (("6".equals(str4)) || (!"".equals(str5))
						|| (!str3.equals("0"))) {
					if (str3.equals("0")) {
						str2 = "insert into blog_specifiedShare (specifiedid,type,content,seclevel,secLevelMax) values ("
								+ paramString
								+ ","
								+ str4
								+ ",'"
								+ str5
								+ "',"
								+ str6 + "," + str7 + ")";
					} else if ((str4.equals("1")) || (str4.equals("2"))
							|| (str4.equals("3")) || (str4.equals("4"))
							|| (str4.equals("5"))) {
						str2 = "update blog_specifiedShare set content='"
								+ str5 + "',specifiedid=" + paramString
								+ " where id=" + str3;
					}
					localRecordSet.executeSql(str2);
				}
			}
		deleteSpecifiedShareCache(paramString);
	}

	public ArrayList getSpecifiedShareList(String paramString) {
		ArrayList localArrayList = new ArrayList();
		HashMap localHashMap = new HashMap();

		if ("".equals(paramString)) {
			return localArrayList;
		}

		RecordSet localRecordSet = new RecordSet();
		String str1 = "select * from blog_specifiedShare where specifiedid="
				+ paramString;
		localRecordSet.execute(str1);

		while (localRecordSet.next()) {
			localHashMap = new HashMap();
			String str2 = Util.null2String(localRecordSet.getString("id"));
			String str3 = Util.null2String(localRecordSet.getString("type"));
			String str4 = Util.null2String(localRecordSet.getString("content"));
			String str5 = Util
					.null2String(localRecordSet.getString("seclevel"));
			String str6 = Util.null2String(localRecordSet
					.getString("sharelevel"));

			localHashMap.put("shareid", str2);
			if ("1".equals(str3)) {
				localHashMap.put("typeName", "1867");
				str5 = "";
				localHashMap.put("contentName",
						resourceComInfo.getLastnames(str4));
			} else if ("2".equals(str3)) {
				localHashMap.put("typeName", "141");
				localHashMap.put("contentName",
						subCompanyComInfo.getSubcompanynames(str4));
			} else if ("3".equals(str3)) {
				localHashMap.put("typeName", "124");
				localHashMap.put("contentName",
						departmentComInfo.getDeptnames(str4));
			} else {
				String[] arrayOfString;
				String str7;
				int i;
				if ("4".equals(str3)) {
					localHashMap.put("typeName", "122");
					arrayOfString = str4.split(",");
					str7 = "";

					for (i = 0; i < arrayOfString.length; i++) {
						if (!"".equals(arrayOfString[i]))
							str7 = str7
									+ ","
									+ Util.toHtml(rolesComInfo
											.getRolesRemark(arrayOfString[i]));
					}
					localHashMap.put("contentName", str7.substring(1));
				} else if ("5".equals(str3)) {
					localHashMap.put("typeName", "6086");
					arrayOfString = str4.split(",");
					str7 = "";

					for (i = 0; i < arrayOfString.length; i++) {
						if (!"".equals(arrayOfString[i]))
							str7 = str7
									+ ","
									+ Util.toHtml(jobTitlesComInfo
											.getJobTitlesname(arrayOfString[i]));
					}
					localHashMap.put("contentName", str7.substring(1));
				} else if ("6".equals(str3)) {
					localHashMap.put("typeName", "1340");
					localHashMap.put("contentName", "");
				}
			}
			localHashMap.put("seclevel", str5);
			localHashMap.put("seclevelMax",
					Util.null2String(localRecordSet.getString("seclevelMax")));

			localHashMap.put("content", str4);

			localHashMap.put("type", "" + str3);
			localArrayList.add(localHashMap);
		}
		return localArrayList;
	}

	public boolean deleteSpecifiedShare(String paramString1, String paramString2) {
		deleteSpecifiedShareCache(paramString1);

		String str = "delete from blog_specifiedShare where id=" + paramString2;
		RecordSet localRecordSet = new RecordSet();
		boolean bool = localRecordSet.execute(str);
		return bool;
	}

	public boolean deleteSpecified(String paramString) {
		deleteSpecifiedShareCache(paramString);

		String str = "delete from blog_specifiedShare where specifiedid in ("
				+ paramString + ")";
		RecordSet localRecordSet = new RecordSet();
		boolean bool = localRecordSet.execute(str);

		return bool;
	}

	public void deleteSpecifiedShareCache(String paramString) {
		RecordSet localRecordSet = new RecordSet();
		BlogShareManager localBlogShareManager = new BlogShareManager();
		String str1 = localBlogShareManager.getSpecifiedShareSql(paramString);

		localRecordSet.execute(str1);
		StaticObj localStaticObj = StaticObj.getInstance();
		while (localRecordSet.next()) {
			String str2 = localRecordSet.getString("specifiedid");
			String str3 = "blogShareInfo_AllUserId_" + str2;
			String str4 = "blogShareInfo_Time_" + str2;

			localStaticObj.removeObject(str3);
			localStaticObj.removeObject(str4);
		}
	}

	public boolean isHasLocation(String paramString) {
		String str = "select id from blog_location where discussid="
				+ paramString;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		if (localRecordSet.next()) {
			return true;
		}
		return false;
	}

	public void addScore(String paramString1, String paramString2) {
		String str = "update blog_discuss set score='" + paramString2
				+ "' where id=" + paramString1;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
	}

	public List getLocationList(String paramString) {
		if ((paramString == null) || (paramString.equals("")))
			return null;
		String str = "select * from blog_location where discussid="
				+ paramString + " order by id asc";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
		ArrayList localArrayList = new ArrayList();
		while (localRecordSet.next()) {
			HashMap localHashMap = new HashMap();
			localHashMap.put("location",
					Util.null2String(localRecordSet.getString("location")));
			localHashMap.put("locationName",
					Util.null2String(localRecordSet.getString("locationName")));
			localHashMap.put("createtime",
					Util.null2String(localRecordSet.getString("createtime")));
			localArrayList.add(localHashMap);
		}
		return localArrayList;
	}

	public String getIsSignInOrSignOut() {
		String str = "0";
		try {
			HrmKqSystemComInfo localHrmKqSystemComInfo = new HrmKqSystemComInfo();
			str = localHrmKqSystemComInfo.getNeedsign();
		} catch (Exception localException) {
		}
		return str;
	}

	public String getIsSignInOrSignOut(User paramUser) {
		String str = "0";
		try {
			boolean bool = weaver.hrm.schedule.HrmScheduleKqUtil
					.hasHrmSchedule(paramUser);
			HrmKqSystemComInfo localHrmKqSystemComInfo = null;
			if (bool) {
				localHrmKqSystemComInfo = new HrmKqSystemComInfo(paramUser);
			} else {
				localHrmKqSystemComInfo = new HrmKqSystemComInfo();
			}
			str = localHrmKqSystemComInfo.getNeedsign();
		} catch (Exception localException) {
		}
		return str;
	}

	public String delComment(String paramString1, String paramString2,
			String paramString3) {
		String str1 = "select * from blog_reply where id='" + paramString1
				+ "' and discussid=" + paramString2;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str1);
		localRecordSet.next();
		String str2 = localRecordSet.getString("userid");
		String str3 = localRecordSet.getString("createdate");
		String str4 = localRecordSet.getString("createtime");

		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date localDate = new Date();
		long l = 0L;
		try {
			l = (localDate.getTime() - localSimpleDateFormat.parse(
					str3 + " " + str4).getTime()) / 60000L;
		} catch (Exception localException) {
		}
		str1 = "select max(id) maxid from blog_reply where discussid="
				+ paramString2;
		localRecordSet.execute(str1);
		String str5 = "0";
		if (localRecordSet.next()) {
			str5 = localRecordSet.getString("maxid");
		}
		int i = 1;
		String str6 = "1";
		if (!paramString3.equals(str2)) {
			i = 0;
		} else if (!paramString1.equals(str5)) {
			str6 = "-1";
			i = 0;
		} else if (l > 10L) {
			str6 = "-2";
			i = 0;
		}

		if (i != 0) {
			str1 = "delete from blog_reply where id=" + paramString1;
			localRecordSet.execute(str1);

			str1 = "delete from blog_remind where relatedid=" + paramString3
					+ " and remindType=9 and remindValue like '" + paramString2
					+ "|%%|" + paramString1 + "'";
			localRecordSet.execute(str1);
		}
		return str6;
	}

	public String getAttachmentDir() {
		String str = getSysSetting("attachmentDir");
		if (str.indexOf("|") != -1) {
			str = Util.TokenizerString2(str, "|")[2];
		}
		return str;
	}

	public boolean isSetWorkday(User paramUser, String paramString) {
		WorkDayDao localWorkDayDao = new WorkDayDao(paramUser);
		return localWorkDayDao.isSetWorkday(paramString);
	}

	public String getUserBlogStartdate(String paramString) {
		String localObject = "";
		String str1 = getSysSetting("enableDate");
		RecordSet localRecordSet = new RecordSet();
		localRecordSet
				.execute("select createdate from HrmResource where id = '"
						+ paramString + "'");
		localRecordSet.next();
		String str2 = localRecordSet.getString(1);
		if (TimeUtil.dateInterval(str1, str2) > 0) {
			localObject = str2;
		} else
			localObject = str1;
		return localObject;
	}
}