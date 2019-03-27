 
package weaver.blog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import weaver.conn.RecordSet;
import weaver.hrm.User;
import weaver.hrm.company.SubCompanyComInfo;
import weaver.hrm.resource.ResourceComInfo;

public class BlogManager {
	ResourceComInfo resourceComInfo;
	SubCompanyComInfo subCompanyComInfo;
	private User user;

	public BlogManager(User paramUser) {
		try {
			this.resourceComInfo = new ResourceComInfo();
			this.subCompanyComInfo = new SubCompanyComInfo();
			this.user = paramUser;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public BlogManager() {
		try {
			this.resourceComInfo = new ResourceComInfo();
			this.subCompanyComInfo = new SubCompanyComInfo();
			this.user = this.user;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public void addAttention(String paramString1, String paramString2,
			String paramString3) {
		String str = "";
		if (paramString3.equals("1")) {
			str = "delete from blog_cancelAttention where userid="
					+ paramString1 + " and attentionid=" + paramString2;
		} else {
			str = "insert into blog_attention(userid,attentionid) values("
					+ paramString1 + "," + paramString2 + ")";
		}
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
	}

	public void cancelAttention(String paramString1, String paramString2,
			String paramString3) {
		String str = "";
		if (paramString3.equals("1")) {
			str = "insert into blog_cancelAttention(userid,attentionid) values("
					+ paramString1 + "," + paramString2 + ")";
		} else {
			str = "delete from blog_attention where userid=" + paramString1
					+ " and attentionid=" + paramString2;
		}
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);

		str = "delete from blog_remind where remindType=6 and remindid="
				+ paramString1 + " and relatedid=" + paramString2;
		localRecordSet.execute(str);
	}

	public void requestAttention(String paramString1, String paramString2,
			String paramString3) {
		SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd");
		SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat(
				"HH:mm:ss");

		Date localDate = new Date();
		String str1 = localSimpleDateFormat1.format(localDate);
		String str2 = localSimpleDateFormat2.format(localDate);

		String str3 = "select id from blog_remind where (status=0 or status=1) and remindType=1 and remindid="
				+ paramString2 + " and relatedid=" + paramString1;

		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str3);
		if (!localRecordSet.next()) {
			str3 = "insert into blog_remind(remindid,relatedid,remindType,remindValue,status,createdate,createtime) values("
					+ paramString2
					+ ","
					+ paramString1
					+ ",1,'"
					+ str1
					+ "',0,'" + str1 + "','" + str2 + "')";
			localRecordSet.execute(str3);
		}
	}

	public String getMyAttentionByGroup(String paramString1, String paramString2) {
		if ((paramString2 == null) || ("".equals(paramString2))) {
			paramString2 = "all";
		}
		BlogManager localBlogManager = new BlogManager();
		List localList = localBlogManager.getMyAttention(paramString1,
				paramString2);
		String str = "";
		for (int i = 0; i < localList.size(); i++) {
			str = str + "," + localList.get(i);
		}
		if (paramString2.equals("all")) {
			str = str + "," + paramString1;
		}
		str = str.length() > 0 ? str.substring(1) : str;
		return str;
	}

	public List getMyAttention(String paramString) {
		return getMyAttention(paramString, "all");
	}

	public List getMyAttention(String paramString1, String paramString2) {
		RecordSet localRecordSet = new RecordSet();
		ArrayList localArrayList = new ArrayList();
		BlogShareManager localBlogShareManager = new BlogShareManager();

		String str1 = this.resourceComInfo.getDepartmentID(paramString1);
		String str2 = this.resourceComInfo.getSubCompanyID(paramString1);
		String str3 = this.resourceComInfo.getSeclevel(paramString1);

		String str4 = "','+t.managerstr+','";
		if (localRecordSet.getDBType().equals("oracle")) {
			str4 = "','||t.managerstr||','";
		}
		String str5 = " select * from (select id,status,managerid,managerstr,case when t1.blogid is not null or "
				+ str4
				+ " like '%,"
				+ paramString1
				+ ",%' then 1 else 0 end as isshare,case when t2.attentionid is not null or t.managerid="
				+ paramString1
				+ " then 1 else 0 end as isattention,case when t4.attentionid is not null then 1 else 0 end as iscancel,case when t5.specifiedid is not null then 1 else 0 end as isSpecified "
				+ (paramString2.equals("all") ? "" : ",groupid ")
				+ " from HrmResource t "
				+ " left join ("
				+ localBlogShareManager.getBlogShareSql(paramString1)
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
				+ (paramString2.equals("all") ? ""
						: new StringBuilder()
								.append(" left join (select distinct userid,groupid  from blog_userGroup where groupid=")
								.append(paramString2)
								.append(")  t6 on t.id=t6.userid ").toString())
				+ " left join ("
				+ localBlogShareManager.getSpecifiedShareSql(paramString1)
				+ ") t5"
				+ " on t.id=t5.specifiedid "
				+ " ) t0 where ((isshare=1 or isSpecified=1) and isattention=1) and iscancel=0 and (status=0 or status=1 or status=2 or status=3)";
		if (!paramString2.equals("all")) {
			str5 = str5 + " and groupid=" + paramString2;
		}
		localRecordSet.execute(str5);
		while (localRecordSet.next()) {
			localArrayList.add(localRecordSet.getString("id"));
		}
		return localArrayList;
	}

	public List getDiscussList(String paramString1, String paramString2,
			String paramString3) {
		ArrayList localArrayList = new ArrayList();

		WorkDayDao localWorkDayDao = new WorkDayDao(this.user);
		List localList = localWorkDayDao
				.getWorkDays(paramString2, paramString3);

		BlogDao localBlogDao = new BlogDao();
		Map localMap = localBlogDao.getDiscussMapByDate(paramString1,
				paramString2, paramString3);
		for (int i = localList.size() - 1; i >= 0; i--) {
			String str = (String) localList.get(i);
			BlogDiscessVo localBlogDiscessVo = (BlogDiscessVo) localMap
					.get(str);
			if (localBlogDiscessVo == null) {
				localBlogDiscessVo = new BlogDiscessVo();
				localBlogDiscessVo.setWorkdate(str);
			}
			localArrayList.add(localBlogDiscessVo);
		}
		return localArrayList;
	}

	public List getBlogDiscussVOList(String paramString, int paramInt1,
			int paramInt2) {
		BlogDao localBlogDao = new BlogDao();
		List localList = localBlogDao.getBlogDiscussVOList(paramString,
				paramInt1, paramInt2, "all");
		return localList;
	}

	public List getBlogDiscussVOList(String paramString, int paramInt1,
			int paramInt2, int paramInt3, Map paramMap) {
		BlogDao localBlogDao = new BlogDao();
		List localList = localBlogDao.getBlogDiscussVOList(paramString,
				paramInt1, paramInt2, paramInt3, paramMap);
		return localList;
	}

	public Map getAttentionDiscussMap(String paramString1, String paramString2) {
		ArrayList localArrayList1 = new ArrayList();
		List localList = getMyAttention(paramString1, "all");
		BlogDao localBlogDao = new BlogDao();
		Map localMap = localBlogDao.getAttentionDicussByDate(localList,
				paramString2);
		HashMap localHashMap1 = new HashMap();

		ArrayList localArrayList2 = new ArrayList();
		Object localObject;
		for (int i = 0; i < localList.size(); i++) {
			localObject = (String) localList.get(i);
			BlogDiscessVo localBlogDiscessVo = (BlogDiscessVo) localMap
					.get(localObject);
			if (localBlogDiscessVo != null) {
				localHashMap1.put(localObject, localMap.get(localObject));
				localArrayList1.add(localObject);
			} else {
				localArrayList2.add(localObject);
			}
		}
		for (int i = 0; i < localArrayList2.size(); i++) {
			localObject = new BlogDiscessVo();
			((BlogDiscessVo) localObject).setUserid((String) localArrayList2
					.get(i));
			((BlogDiscessVo) localObject).setWorkdate(paramString2);
			localHashMap1.put((String) localArrayList2.get(i), localObject);
			localArrayList1.add((String) localArrayList2.get(i));
		}
		HashMap localHashMap2 = new HashMap();
		localHashMap2.put("resultList", localArrayList1);
		localHashMap2.put("discussMap", localHashMap1);

		return localHashMap2;
	}

	public void dealRequest(String paramString1, String paramString2,
			String paramString3, String paramString4) {
		BlogShareManager localBlogShareManager = new BlogShareManager();
		String str1 = "delete from blog_remind where id=" + paramString3;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str1);
		if (paramString4.equals("1")) {
			str1 = "select id,content from blog_share where blogid="
					+ paramString1 + " and type=7";
			localRecordSet.execute(str1);
			if (localRecordSet.next()) {
				String str2 = localRecordSet.getString("id");
				String str3 = localRecordSet.getString("content");
				str3 = str3 + paramString2 + ",";
				str1 = "update blog_share set content='" + str3 + "' where id="
						+ str2;
				localRecordSet.execute(str1);
				localBlogShareManager.deleteCache(paramString1);
				str1 = "insert into blog_attention(userid,attentionid) values("
						+ paramString2 + "," + paramString1 + ")";
				localRecordSet.execute(str1);
			} else {
				str1 = "insert into blog_share (blogid,type,content,seclevel,sharelevel) values ("
						+ paramString1 + ",7,'," + paramString2 + ",',0,0)";
				localRecordSet.execute(str1);
				localBlogShareManager.deleteCache(paramString1);
				str1 = "insert into blog_attention(userid,attentionid) values("
						+ paramString2 + "," + paramString1 + ")";
				localRecordSet.execute(str1);
			}
		}
	}

	public List getBlogReportByUser(String paramString1, String paramString2,
			String paramString3) {
		ArrayList localArrayList = new ArrayList();
		WorkDayDao localWorkDayDao = new WorkDayDao(this.user);
		BlogDao localBlogDao = new BlogDao();
		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(paramString2,
				paramString3);
		Map localMap = localBlogDao.getDiscussMapByDate(paramString1,
				paramString2, paramString3);

		Iterator localIterator = localTreeMap.keySet().iterator();
		while (localIterator.hasNext()) {
			HashMap localHashMap = new HashMap();
			String str = (String) localIterator.next();
			boolean bool = ((Boolean) localTreeMap.get(str)).booleanValue();
			localHashMap.put("workdate", str);
			localHashMap.put("isWorkday", new Boolean(bool));
			localHashMap.put("isSubmited",
					new Boolean(localMap.containsKey(str)));
			localHashMap.put("userid", paramString1);
			localArrayList.add(localHashMap);
		}
		return localArrayList;
	}

	public List getAttentionDiscussCount(String paramString1,
			String paramString2, String paramString3) {
		ArrayList localArrayList = new ArrayList();
		HashMap localHashMap = null;
		WorkDayDao localWorkDayDao = new WorkDayDao(this.user);
		BlogDao localBlogDao = new BlogDao();

		List localList1 = localWorkDayDao.getWorkDays(paramString2,
				paramString3);
		List localList2 = getMyAttention(paramString1, "all");
		if (localList2.size() == 0) {
			return localArrayList;
		}
		String str1 = "";
		for (int i = 0; i < localList2.size(); i++) {
			str1 = str1 + "," + localList2.get(i);
		}
		str1 = str1.substring(1);
		Map localMap = localBlogDao.getAttentionDiscussCount(str1,
				paramString2, paramString3);
		int j = localList2.size();
		for (int k = 0; k < localList1.size(); k++) {
			localHashMap = new HashMap();
			String str2 = (String) localList1.get(k);
			int m = localMap.get(str2) != null ? ((Integer) localMap.get(str2))
					.intValue() : 0;
			int n = j - m;
			localHashMap.put("workdate", str2);
			localHashMap.put("submited", new Integer(m));
			localHashMap.put("unsubmit", new Integer(n));
			localArrayList.add(localHashMap);
		}
		return localArrayList;
	}

	public List getDiscussListAll(String paramString1, String paramString2,
			String paramString3) {
		ArrayList localArrayList = new ArrayList();
		try {
			WorkDayDao localWorkDayDao = new WorkDayDao(this.user);
			List localList = localWorkDayDao.getWorkDays(paramString2,
					paramString3);

			BlogDao localBlogDao = new BlogDao();
			Map localMap = localBlogDao.getDiscussMapByDate(paramString1,
					paramString2, paramString3);

			SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			Date localDate1 = localSimpleDateFormat.parse(paramString2);
			Date localDate2 = localSimpleDateFormat.parse(paramString3);

			Calendar localCalendar = Calendar.getInstance();
			localCalendar.setTime(localDate2);
			localCalendar.add(5, 1);
			String str = "";
			while (localDate1.getTime() < localCalendar.getTime().getTime()) {
				localCalendar.add(5, -1);
				str = localSimpleDateFormat.format(localCalendar.getTime());
				boolean bool = localList.contains(str);
				BlogDiscessVo localBlogDiscessVo = (BlogDiscessVo) localMap
						.get(str);
				if ((localBlogDiscessVo == null) && (bool)) {
					localBlogDiscessVo = new BlogDiscessVo();
					localBlogDiscessVo.setWorkdate(str);
					localBlogDiscessVo.setUserid(paramString1);
					localArrayList.add(localBlogDiscessVo);
				} else if (localBlogDiscessVo != null) {
					localArrayList.add(localBlogDiscessVo);
				}
			}
		} catch (ParseException localParseException) {
			localParseException.printStackTrace();
		}
		return localArrayList;
	}
}