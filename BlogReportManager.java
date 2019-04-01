package weaver.blog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.hrm.report.schedulediff.HrmScheduleDiffDetAbsentFromWorkManager;
import weaver.hrm.report.schedulediff.HrmScheduleDiffDetBeLateManager;
import weaver.hrm.resource.ResourceComInfo;
import weaver.systeminfo.SystemEnv;

public class BlogReportManager {
	private User user;

	public BlogReportManager() {
	}

	public Map getBlogReportByUser(String paramString, int paramInt1,
			int paramInt2) {
		HashMap localHashMap1 = new HashMap();
		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		Map localMap = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str1 = (String) localMap.get("startdate");
		String str2 = (String) localMap.get("enddate");
		List localList1 = (List) localMap.get("totaldateList");

		ArrayList localArrayList = new ArrayList();
		BlogDao localBlogDao = new BlogDao();
		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str1, str2);

		List localList2 = localBlogDao.getDiscussWorkdateList(paramString,
				str1, str2);
		Iterator localIterator = localTreeMap.keySet().iterator();

		int i = 0;
		int j = 0;
		while (localIterator.hasNext()) {
			HashMap localHashMap2 = new HashMap();
			String str3 = (String) localIterator.next();
			boolean bool1 = ((Boolean) localTreeMap.get(str3)).booleanValue();

			boolean bool2 = localList2.contains(str3);
			if (bool1) {
				i++;
				if (!bool2) {
					j++;
				}
			}
			localHashMap2.put("workdate", str3);
			localHashMap2.put("isWorkday", new Boolean(bool1));
			localHashMap2.put("isSubmited", new Boolean(bool2));
			localHashMap2.put("userid", paramString);
			localArrayList.add(localHashMap2);
		}
		double d1 = 0.0D;
		if (i != 0) {
			d1 = (i - j) / i * 5.0D;
		}
		DecimalFormat localDecimalFormat = new DecimalFormat("0.0");
		double d2 = Double.parseDouble(localDecimalFormat.format(d1));

		localHashMap1.put("reportList", localArrayList);
		localHashMap1.put("totalWorkday", new Integer(i));
		localHashMap1.put("totalUnsubmit", new Integer(j));
		localHashMap1.put("totaldateList", localList1);
		localHashMap1.put("workIndex", new Double(d2));

		return localHashMap1;
	}

	public Map getMoodReportByUser(String paramString, int paramInt1,
			int paramInt2) {
		HashMap localHashMap1 = new HashMap();
		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		localWorkDayDao.setUser(user);
		Map localMap1 = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str1 = (String) localMap1.get("startdate");
		String str2 = (String) localMap1.get("enddate");
		List localList = (List) localMap1.get("totaldateList");
		ArrayList localArrayList = new ArrayList();
		BlogDao localBlogDao = new BlogDao();
		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str1, str2);

		Map localMap2 = localBlogDao.getMoodvoMap(paramString, str1, str2);
		Iterator localIterator = localTreeMap.keySet().iterator();
		int i = 0;
		int j = 0;
		int k = 0;
		int m = 0;
		int n = 0;
		int i1 = 0;
		Object localObject;
		while (localIterator.hasNext()) {
			HashMap localHashMap2 = new HashMap();
			String str3 = (String) localIterator.next();
			boolean bool1 = ((Boolean) localTreeMap.get(str3)).booleanValue();

			boolean bool2 = localMap2.containsKey(str3);
			localObject = new AppDao();

			if (bool1) {
				i++;
				if (!bool2)
					j++;
			}
			localHashMap2.put("workdate", str3);
			localHashMap2.put("isWorkday", new Boolean(bool1));
			localHashMap2.put("isSubmited", new Boolean(bool2));
			localHashMap2.put("userid", paramString);
			if ((bool1) && (bool2)) {
				AppItemVo localAppItemVo = (AppItemVo) localMap2.get(str3);
				if (localAppItemVo != null) {
					localHashMap2.put("faceImg", localAppItemVo.getFaceImg());
					k += Util.getIntValue(localAppItemVo.getValue(), 0);
					m++;
					if ("4".equals(localAppItemVo.getValue())) {
						n++;
					} else {
						i1++;
					}
				} else {
					localHashMap2.put("faceImg", "");
				}
			} else {
				localHashMap2.put("faceImg", "");
			}
			localArrayList.add(localHashMap2);
		}
		double d1 = 0.0D;
		double d2 = 0.0D;
		if (m != 0) {
			d1 = k / m;
			d1 = d1 * 5.0D / 4.0D;
			localObject = new DecimalFormat("0.0");
			d2 = Double.parseDouble(((DecimalFormat) localObject).format(d1));
		}
		localHashMap1.put("moodIndex", new Double(d2));
		localHashMap1.put("happyDays", new Integer(n));
		localHashMap1.put("unHappyDays", new Integer(i1));
		localHashMap1.put("reportList", localArrayList);
		localHashMap1.put("totalWorkday", new Integer(i));
		localHashMap1.put("totalUnsubmit", new Integer(j));
		localHashMap1.put("totaldateList", localList);

		return localHashMap1;
	}

	public Map getScheduleReportByUser(String paramString, int paramInt1,
			int paramInt2) {
		HashMap localHashMap1 = new HashMap();
		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		Map localMap = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str1 = (String) localMap.get("startdate");
		String str2 = (String) localMap.get("enddate");
		List localList1 = (List) localMap.get("totaldateList");

		ResourceComInfo localResourceComInfo = null;
		try {
			localResourceComInfo = new ResourceComInfo();
		} catch (Exception localException) {
		}
		int i = Util.getIntValue(localResourceComInfo
				.getSubCompanyID(paramString));
		int j = Util.getIntValue(localResourceComInfo
				.getDepartmentID(paramString));

		ArrayList localArrayList1 = new ArrayList();
		BlogDao localBlogDao = new BlogDao();
		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str1, str2);
		Iterator localIterator = localTreeMap.keySet().iterator();

		HrmScheduleDiffDetBeLateManager localHrmScheduleDiffDetBeLateManager = new HrmScheduleDiffDetBeLateManager();
		localHrmScheduleDiffDetBeLateManager.setUser(user);
		List localList2 = localHrmScheduleDiffDetBeLateManager.getScheduleList(
				str1, str2, i, j, Integer.parseInt(paramString));

		ArrayList localArrayList2 = new ArrayList();

		for (int k = 0; k < localList2.size(); k++) {
			Map localObject1;
			localObject1 = (Map) localList2.get(k);
			Object localObject2 = localObject1.get("currentDate");
			localArrayList2.add(localObject2);
		}

		HrmScheduleDiffDetAbsentFromWorkManager localHrmScheduleDiffDetAbsentFromWorkManager = new HrmScheduleDiffDetAbsentFromWorkManager();
		localHrmScheduleDiffDetAbsentFromWorkManager.setUser(user);
		Object localObject1 = localHrmScheduleDiffDetAbsentFromWorkManager
				.getScheduleList(str1, str2, i, j,
						Integer.parseInt(paramString));

		Object localObject2 = new ArrayList();

		for (int m = 0; m < ((List) localObject1).size(); m++) {
			Object localObject3 = new HashMap();
			localObject3 = (Map) ((List) localObject1).get(m);
			String str3 = (String) ((Map) localObject3).get("currentDate");
			((List) localObject2).add(str3);
		}

		int m = 0;
		int n = 0;
		int i1 = 0;
		int i2 = 0;
		while (localIterator.hasNext()) {
			HashMap localHashMap2 = new HashMap();
			String str4 = (String) localIterator.next();
			boolean bool1 = ((Boolean) localTreeMap.get(str4)).booleanValue();
			boolean bool2 = localArrayList2.contains(str4);
			boolean bool3 = ((List) localObject2).contains(str4);

			if (bool2)
				i1++;
			if (bool3) {
				i2++;
			}
			if (bool1) {
				m++;
			}

			localHashMap2.put("workdate", str4);
			localHashMap2.put("isWorkday", new Boolean(bool1));
			localHashMap2.put("isLate", new Boolean(bool2));
			localHashMap2.put("isAbsent", new Boolean(bool3));
			localHashMap2.put("userid", paramString);
			localArrayList1.add(localHashMap2);
		}

		double d1 = 0.0D;
		if (m != 0) {
			d1 = (m * 5 - i2 * 4 - i1 * 3) / (m * 5) * 5.0D;
		}
		DecimalFormat localDecimalFormat = new DecimalFormat("0.0");
		double d2 = Double.parseDouble(localDecimalFormat.format(d1));

		localHashMap1.put("reportList", localArrayList1);
		localHashMap1.put("totalWorkday", new Integer(m));
		localHashMap1.put("totalUnsubmit", new Integer(n));
		localHashMap1.put("totaldateList", localList1);
		localHashMap1.put("totalLate", new Integer(i1));
		localHashMap1.put("totalAbsent", new Integer(i2));
		localHashMap1.put("scheduleIndex", new Double(d2));

		return localHashMap1;
	}

	public double getScheduleIndexByUser(String paramString, int paramInt1,
			int paramInt2) {
		HashMap localHashMap1 = new HashMap();
		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		Map localMap = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str1 = (String) localMap.get("startdate");
		String str2 = (String) localMap.get("enddate");
		List localList1 = (List) localMap.get("totaldateList");

		ArrayList localArrayList1 = new ArrayList();
		BlogDao localBlogDao = new BlogDao();
		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str1, str2);
		Iterator localIterator = localTreeMap.keySet().iterator();

		HrmScheduleDiffDetBeLateManager localHrmScheduleDiffDetBeLateManager = new HrmScheduleDiffDetBeLateManager();
		localHrmScheduleDiffDetBeLateManager.setUser(user);
		List localList2 = localHrmScheduleDiffDetBeLateManager.getScheduleList(
				str1, str2, 0, 0, Integer.parseInt(paramString));

		ArrayList localArrayList2 = new ArrayList();

		for (int i = 0; i < localList2.size(); i++) {
			Map localObject1 = new HashMap();
			localObject1 = (Map) localList2.get(i);
			String localObject2 = (String) ((Map) localObject1)
					.get("currentDate");
			localArrayList2.add(localObject2);
		}

		HrmScheduleDiffDetAbsentFromWorkManager localHrmScheduleDiffDetAbsentFromWorkManager = new HrmScheduleDiffDetAbsentFromWorkManager();
		localHrmScheduleDiffDetAbsentFromWorkManager.setUser(user);
		Object localObject1 = localHrmScheduleDiffDetAbsentFromWorkManager
				.getScheduleList(str1, str2, 0, 0,
						Integer.parseInt(paramString));

		Object localObject2 = new ArrayList();

		for (int j = 0; j < ((List) localObject1).size(); j++) {
			Object localObject3 = new HashMap();
			localObject3 = (Map) ((List) localObject1).get(j);
			String str3 = (String) ((Map) localObject3).get("currentDate");
			((List) localObject2).add(str3);
		}

		int j = 0;
		int k = 0;
		int m = 0;
		int n = 0;
		while (localIterator.hasNext()) {
			HashMap localHashMap2 = new HashMap();
			String str4 = (String) localIterator.next();
			boolean bool1 = ((Boolean) localTreeMap.get(str4)).booleanValue();
			boolean bool2 = localArrayList2.contains(str4);
			boolean bool3 = ((List) localObject2).contains(str4);

			if (bool2)
				m++;
			if (bool3) {
				n++;
			}
			if (bool1) {
				j++;
			}
		}
		double d1 = 0.0D;
		if (j > 0)
			d1 = (j * 5 - n * 4 - m * 3) / (j * 5) * 5.0D;
		DecimalFormat localDecimalFormat = new DecimalFormat("0.0");
		double d2 = Double.parseDouble(localDecimalFormat.format(d1));

		return d2;
	}

	public String getBlogIndexByUser(String paramString, int paramInt1,
			int paramInt2) {
		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		Map localMap1 = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str1 = (String) localMap1.get("startdate");
		String str2 = (String) localMap1.get("enddate");

		ArrayList localArrayList = new ArrayList();
		BlogDao localBlogDao = new BlogDao();
		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str1, str2);
		Map localMap2 = localBlogDao.getDiscussMapByDate(paramString, str1,
				str2);

		int i = 0;
		int j = 0;
		Iterator localIterator = localTreeMap.keySet().iterator();
		while (localIterator.hasNext()) {
			String str3 = (String) localIterator.next();
			boolean bool = ((Boolean) localTreeMap.get(str3)).booleanValue();
			if (bool) {
				i++;
				if (!localMap2.containsKey(str3))
					j++;
			}
		}
		double d = 0.0D;
		if (i > 0)
			d = (i - j) / i * 5.0D;
		DecimalFormat localDecimalFormat = new DecimalFormat("0.0");
		String str4 = localDecimalFormat.format(d);

		return str4;
	}

	public String getMoodIndexByUser(String paramString, int paramInt1,
			int paramInt2) {
		HashMap localHashMap1 = new HashMap();
		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		Map localMap1 = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str1 = (String) localMap1.get("startdate");
		String str2 = (String) localMap1.get("enddate");
		List localList = (List) localMap1.get("totaldateList");
		ArrayList localArrayList = new ArrayList();
		BlogDao localBlogDao = new BlogDao();
		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str1, str2);
		Map localMap2 = localBlogDao.getDiscussMapByDate(paramString, str1,
				str2);
		Iterator localIterator = localTreeMap.keySet().iterator();
		int i = 0;
		int j = 0;
		int k = 0;
		int m = 0;
		while (localIterator.hasNext()) {
			HashMap localHashMap2 = new HashMap();
			String str3 = (String) localIterator.next();
			boolean bool1 = ((Boolean) localTreeMap.get(str3)).booleanValue();
			boolean bool2 = localMap2.containsKey(str3);
			AppDao localAppDao = new AppDao();

			BlogDiscessVo localBlogDiscessVo = (BlogDiscessVo) localMap2
					.get(str3);
			if (bool1) {
				i++;
			}

			localHashMap2.put("workdate", str3);
			localHashMap2.put("isWorkday", new Boolean(bool1));
			localHashMap2.put("isSubmited", new Boolean(bool2));
			localHashMap2.put("userid", paramString);
			if (bool2) {
				AppItemVo localAppItemVo = localAppDao
						.getappItemByDiscussId(localBlogDiscessVo.getId());
				if (localAppItemVo != null) {
					localHashMap2.put("faceImg", localAppItemVo.getFaceImg());
					k += Integer.parseInt(localAppItemVo.getValue());
					m++;
				} else {
					localHashMap2.put("faceImg", "");
				}
			} else {
				localHashMap2.put("faceImg", "");
			}
			localArrayList.add(localHashMap2);
		}
		double d = 0.0D;
		String str4 = "0.0";
		if (m != 0) {
			d = k / m;
			d = d * 5.0D / 4.0D;
			DecimalFormat localDecimalFormat = new DecimalFormat("0.0");
			str4 = localDecimalFormat.format(d);
		}
		return str4;
	}

	public Map getBlogChartReportByUser(String paramString, int paramInt) {
		HashMap localHashMap = new HashMap();
		ArrayList localArrayList1 = new ArrayList();
		ArrayList localArrayList2 = new ArrayList();

		BlogDao localBlogDao = new BlogDao();
		Map localMap = localBlogDao.getCompaerMonth(paramInt);
		int i = ((Integer) localMap.get("startMonth")).intValue();
		int j = ((Integer) localMap.get("endMonth")).intValue();

		for (int k = i; k <= j; k++) {
			String str = getBlogIndexByUser(paramString, paramInt, k);
			localArrayList1.add(k
					+ SystemEnv.getHtmlLabelName(6076, user.getLanguage()));
			localArrayList2.add(new Double(Double.parseDouble(str)));
		}

		localHashMap.put("categoriesList", localArrayList1);
		localHashMap.put("SeriesList", localArrayList2);
		localHashMap.put("SeriesName",
				SystemEnv.getHtmlLabelName(26929, user.getLanguage()));

		return localHashMap;
	}

	public Map getMoodChartReportByUser(String paramString, int paramInt) {
		HashMap localHashMap = new HashMap();
		ArrayList localArrayList1 = new ArrayList();
		ArrayList localArrayList2 = new ArrayList();

		BlogDao localBlogDao = new BlogDao();
		Map localMap = localBlogDao.getCompaerMonth(paramInt);
		int i = ((Integer) localMap.get("startMonth")).intValue();
		int j = ((Integer) localMap.get("endMonth")).intValue();

		for (int k = i; k <= j; k++) {
			String str = getMoodIndexByUser(paramString, paramInt, k);
			localArrayList1.add(k
					+ SystemEnv.getHtmlLabelName(6076, user.getLanguage()));
			if ("".equals(Util.null2String(str))) {
				localArrayList2.add(new Double(0.0D));
			} else {
				localArrayList2.add(new Double(Double.parseDouble(str)));
			}
		}

		localHashMap.put("categoriesList", localArrayList1);
		localHashMap.put("SeriesList", localArrayList2);
		localHashMap.put("SeriesName",
				SystemEnv.getHtmlLabelName(26930, user.getLanguage()));

		return localHashMap;
	}

	public Map getScheduleChartReportByUser(String paramString, int paramInt) {
		HashMap localHashMap = new HashMap();
		ArrayList localArrayList1 = new ArrayList();
		ArrayList localArrayList2 = new ArrayList();

		BlogDao localBlogDao = new BlogDao();
		Map localMap = localBlogDao.getCompaerMonth(paramInt);
		int i = ((Integer) localMap.get("startMonth")).intValue();
		int j = ((Integer) localMap.get("endMonth")).intValue();

		for (int k = i; k <= j; k++) {
			double d = getScheduleIndexByUser(paramString, paramInt, k);
			localArrayList1.add(k
					+ SystemEnv.getHtmlLabelName(6076, user.getLanguage()));
			localArrayList2.add(new Double(d));
		}

		localHashMap.put("categoriesList", localArrayList1);
		localHashMap.put("SeriesList", localArrayList2);
		localHashMap.put("SeriesName",
				SystemEnv.getHtmlLabelName(26931, user.getLanguage()));

		return localHashMap;
	}

	public String getReportIndexStar(double paramDouble) {
		int i = (int) Math.floor(paramDouble);
		double d = paramDouble - i;
		int j = (int) Math.floor(5.0D - paramDouble);

		String str = "";
		for (int k = 0; k < i; k++) {
			str = str
					+ "<img align='absmiddle' src='images/star_full_wev8.png'>";
		}
		if (d > 0.0D) {
			str = str
					+ "<img align='absmiddle' src='images/star_half_wev8.png'>";
		}
		for (int k = 0; k < j; k++) {
			str = str
					+ "<img align='absmiddle' src='images/star_empty_wev8.png'>";
		}
		return str;
	}

	public Map getBlogAttentionReport(String paramString, int paramInt1,
			int paramInt2) {
		HashMap localHashMap1 = new HashMap();
		ArrayList localArrayList1 = new ArrayList();
		ArrayList localArrayList2 = new ArrayList();

		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		BlogManager localBlogManager = new BlogManager(user);
		List localList1 = localBlogManager.getMyAttention(paramString);

		if (localList1.size() == 0) {
			return localHashMap1;
		}
		String str1 = "";
		for (int i = 0; i < localList1.size(); i++) {
			str1 = str1 + "," + localList1.get(i);
		}
		str1 = str1.substring(1);

		Map localMap1 = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str2 = (String) localMap1.get("startdate");
		String str3 = (String) localMap1.get("enddate");
		List localList2 = (List) localMap1.get("totaldateList");

		BlogDao localBlogDao = new BlogDao();
		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str2, str3);
		Iterator localIterator = localTreeMap.keySet().iterator();

		Map localMap2 = localBlogDao.getAttentionDiscussCount(str1, str2, str3);

		ArrayList localArrayList3 = new ArrayList();
		int j = 0;
		int k = 0;
		int m = localList1.size();
		int n = 0;

		while (localIterator.hasNext()) {
			String str4 = (String) localIterator.next();
			boolean bool1 = ((Boolean) localTreeMap.get(str4)).booleanValue();
			if (bool1) {
				j++;

				int i3 = localMap2.get(str4) != null ? ((Integer) localMap2
						.get(str4)).intValue() : 0;
				k = m - i3;
				n += k;
			}
			localArrayList2.add(new Integer(k));
			localArrayList3.add(new Boolean(bool1));
		}

		for (int i1 = 0; i1 < localList1.size(); i1++) {
			ArrayList localArrayList4 = new ArrayList();
			HashMap localHashMap2 = new HashMap();
			String str5 = (String) localList1.get(i1);

			List localObject = localBlogDao.getDiscussWorkdateList(str5, str2,
					str3);
			localIterator = localTreeMap.keySet().iterator();
			int i4 = 0;
			while (localIterator.hasNext()) {
				HashMap localHashMap3 = new HashMap();
				String str6 = (String) localIterator.next();
				boolean bool2 = ((Boolean) localTreeMap.get(str6))
						.booleanValue();

				boolean bool3 = ((List) localObject).contains(str6);
				localArrayList4.add(new Boolean(bool3));
				if ((bool2) && (!bool3)) {
					i4++;
				}
			}
			double d3 = 0.0D;
			if (j > 0)
				d3 = (j - i4) / j * 5.0D;
			DecimalFormat localDecimalFormat = new DecimalFormat("0.0");
			double d4 = Double.parseDouble(localDecimalFormat.format(d3));

			localHashMap2.put("totalUnsubmit", new Integer(i4));
			localHashMap2.put("workIndex", new Double(d4));
			localHashMap2.put("reportList", localArrayList4);
			localHashMap2.put("attentionid", str5);
			localArrayList1.add(localHashMap2);
		}

		BlogReportManager.ComparatorIndex localComparatorIndex = new BlogReportManager.ComparatorIndex();
		Collections.sort(localArrayList1, localComparatorIndex);

		int i2 = m * j;
		double d1 = 0.0D;
		if (i2 > 0)
			d1 = (i2 - n) / i2 * 5.0D;
		Object localObject = new DecimalFormat("0.0");
		double d2 = Double
				.parseDouble(((DecimalFormat) localObject).format(d1));

		localHashMap1.put("resultList", localArrayList1);
		localHashMap1.put("totaldateList", localList2);
		localHashMap1.put("totalWorkday", new Integer(j));
		localHashMap1.put("workdayMap", localTreeMap);
		localHashMap1.put("isWorkdayList", localArrayList3);
		localHashMap1.put("resultCountList", localArrayList2);
		localHashMap1.put("allUnsubmit", new Integer(n));
		localHashMap1.put("allWorkday", new Integer(i2));
		localHashMap1.put("allWorkIndex", new Double(d2));

		return localHashMap1;
	}

	public Map getMoodAttentionReport(String paramString, int paramInt1,
			int paramInt2) {
		HashMap localHashMap1 = new HashMap();
		ArrayList localArrayList1 = new ArrayList();
		Object localObject1 = new ArrayList();

		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		BlogManager localBlogManager = new BlogManager(user);
		List localList1 = localBlogManager.getMyAttention(paramString);

		if (localList1.size() == 0) {
			return localHashMap1;
		}
		String str1 = "";
		for (int i = 0; i < localList1.size(); i++) {
			str1 = str1 + "," + localList1.get(i);
		}
		str1 = str1.substring(1);

		Map localMap = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str2 = (String) localMap.get("startdate");
		String str3 = (String) localMap.get("enddate");
		List localList2 = (List) localMap.get("totaldateList");

		BlogDao localBlogDao = new BlogDao();
		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str2, str3);
		Iterator localIterator = localTreeMap.keySet().iterator();

		ArrayList localArrayList2 = new ArrayList();

		int j = 0;
		int k = 0;
		int m = localList1.size();
		AppDao localAppDao = new AppDao();
		int n = 0;
		int i1 = 0;
		Object localObject2;
		while (localIterator.hasNext()) {
			HashMap localHashMap2 = new HashMap();
			localObject2 = (String) localIterator.next();
			boolean bool1 = ((Boolean) localTreeMap.get(localObject2))
					.booleanValue();
			((List) localObject1).add(new Integer(k));
			localArrayList2.add(new Boolean(bool1));
		}

		localObject1 = localBlogDao.getDiscussMoodCount(str1, str2, str3,
				localTreeMap);
		Object localObject3;
		for (int i2 = 0; i2 < localList1.size(); i2++) {
			localObject2 = new ArrayList();
			HashMap localHashMap3 = new HashMap();
			String str4 = (String) localList1.get(i2);

			localObject3 = localBlogDao.getMoodvoMap(str4, str2, str3);
			localIterator = localTreeMap.keySet().iterator();
			int i5 = 0;
			int i6 = 0;
			int i7 = 0;
			Object localObject4;
			while (localIterator.hasNext()) {
				HashMap localHashMap4 = new HashMap();
				String str5 = (String) localIterator.next();
				boolean bool2 = ((Boolean) localTreeMap.get(str5))
						.booleanValue();

				boolean bool3 = ((Map) localObject3).containsKey(str5);

				if ((bool2) && (!bool3))
					i5++;
				if ((bool2) && (bool3)) {
					localObject4 = (AppItemVo) ((Map) localObject3).get(str5);
					if (localObject4 != null) {
						((List) localObject2).add(((AppItemVo) localObject4)
								.getFaceImg());
						i6 += Integer.parseInt(((AppItemVo) localObject4)
								.getValue());
						i7++;
						if ("4".equals(((AppItemVo) localObject4).getValue())) {
							n++;
						} else if ("2".equals(((AppItemVo) localObject4)
								.getValue())) {
							i1++;
						}
					} else {
						((List) localObject2).add("");
					}
				} else {
					((List) localObject2).add("");
				}
			}

			double d2 = 0.0D;
			double d3 = 0.0D;
			if (i7 != 0) {
				d2 = i6 / i7;
				d2 = d2 * 5.0D / 4.0D;
				localObject4 = new DecimalFormat("0.0");
				d3 = Double.parseDouble(((DecimalFormat) localObject4)
						.format(d2));
			}
			localHashMap3.put("totalUnsubmit", new Integer(i5));
			localHashMap3.put("moodIndex", new Double(d3));
			localHashMap3.put("happyDays", new Integer(n));
			localHashMap3.put("unHappyDays", new Integer(i1));
			localHashMap3.put("reportList", localObject2);
			localHashMap3.put("efdectiveDays", new Integer(i7));
			localHashMap3.put("attentionid", str4);
			localArrayList1.add(localHashMap3);
		}

		int i2 = 0;
		int i3 = 0;
		for (int i4 = 0; i4 < ((List) localObject1).size(); i4++) {
			i2 += ((Integer) ((HashMap) ((List) localObject1).get(i4))
					.get("happy")).intValue();
			i3 += ((Integer) ((HashMap) ((List) localObject1).get(i4))
					.get("unhappy")).intValue();
		}
		double d1 = 0.0D;
		if (i2 + i3 != 0) {
			d1 = (i2 * 4 + i3 * 2) / (i2 + i3);
			d1 = d1 * 5.0D / 4.0D;
			localObject3 = new DecimalFormat("0.0");
			d1 = Double.parseDouble(((DecimalFormat) localObject3).format(d1));
		}

		localHashMap1.put("totalMoodIndex", new Double(d1));
		localHashMap1.put("totalHappyDays", new Integer(i2));
		localHashMap1.put("totalUnHappyDays", new Integer(i3));
		localHashMap1.put("resultList", localArrayList1);
		localHashMap1.put("totaldateList", localList2);
		localHashMap1.put("totalWorkday", new Integer(j));
		localHashMap1.put("workdayMap", localTreeMap);
		localHashMap1.put("isWorkdayList", localArrayList2);
		localHashMap1.put("resultCountList", localObject1);

		return localHashMap1;
	}

	public Map getOrgReportCount(String paramString1, String paramString2,
			String paramString3, int paramInt1, int paramInt2) {
		Map localObject = null;
		ArrayList localArrayList1 = new ArrayList();
		ArrayList localArrayList2 = new ArrayList();

		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		Map localMap1 = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str1 = (String) localMap1.get("startdate");
		String str2 = (String) localMap1.get("enddate");
		List localList1 = (List) localMap1.get("totaldateList");

		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str1, str2);
		Iterator localIterator = localTreeMap.keySet().iterator();

		BlogShareManager localBlogShareManager = new BlogShareManager();
		List localList2 = localBlogShareManager.haveRightView(paramString1);
		List localList3 = localBlogShareManager.getOrgResourceid(paramString2,
				paramString3);

		String str3 = "";
		int i = 0;
		for (int j = 0; j < localList3.size(); j++) {
			String str4 = (String) localList3.get(j);
			if (localList2.contains(str4)) {
				str3 = str3 + "," + str4;
				i++;
			}
		}
		if (i == 0)
			return localObject;
		localObject = new HashMap();
		str3 = str3.substring(1);

		BlogDao localBlogDao = new BlogDao();
		int k = 0;
		int m = 0;
		int n = 0;
		int i1 = 0;
		int i2 = 0;

		Map localMap2 = localBlogDao.getAttentionDiscussCount(str3, str1, str2);
		while (localIterator.hasNext()) {
			HashMap localHashMap = new HashMap();
			String str5 = (String) localIterator.next();
			boolean bool = ((Boolean) localTreeMap.get(str5)).booleanValue();
			if (bool) {
				m++;

				int i3 = localMap2.get(str5) != null ? ((Integer) localMap2
						.get(str5)).intValue() : 0;
				k = i - i3;
				n += k;
			}
			localArrayList1.add(new Integer(k));
			localArrayList2.add(new Boolean(bool));
		}
		double d1 = 0.0D;
		if (m * i > 0)
			d1 = (m * i - n) / (m * i) * 5.0D;
		DecimalFormat localDecimalFormat = new DecimalFormat("0.0");
		double d2 = Double.parseDouble(localDecimalFormat.format(d1));

		((Map) localObject).put("resultCountList", localArrayList1);
		((Map) localObject).put("isWorkdayList", localArrayList2);
		((Map) localObject).put("totaldateList", localList1);
		((Map) localObject).put("totalAttention", new Integer(i));
		((Map) localObject).put("totalUnsubmit", new Integer(n));
		((Map) localObject).put("totalWorkday", new Integer(m));
		((Map) localObject).put("workIndex", new Double(d2));
		((Map) localObject).put("resourceids", str3);

		return localObject;
	}

	public Map getOrgMoodReportCount(String paramString1, String paramString2,
			String paramString3, int paramInt1, int paramInt2) {
		Map localObject1 = null;
		Object localObject2 = new ArrayList();
		ArrayList localArrayList = new ArrayList();

		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		Map localMap = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str1 = (String) localMap.get("startdate");
		String str2 = (String) localMap.get("enddate");
		List localList1 = (List) localMap.get("totaldateList");

		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str1, str2);
		Iterator localIterator = localTreeMap.keySet().iterator();

		BlogShareManager localBlogShareManager = new BlogShareManager();
		List localList2 = localBlogShareManager.haveRightView(paramString1);
		List localList3 = localBlogShareManager.getOrgResourceid(paramString2,
				paramString3);

		String str3 = "";
		int i = 0;
		for (int j = 0; j < localList3.size(); j++) {
			String str4 = (String) localList3.get(j);
			if (localList2.contains(str4)) {
				str3 = str3 + "," + str4;
				i++;
			}
		}

		if (i == 0)
			return localObject1;
		localObject1 = new HashMap();
		str3 = str3.substring(1);
		BlogDao localBlogDao = new BlogDao();

		int k = 0;
		while (localIterator.hasNext()) {
			HashMap localHashMap = new HashMap();
			String str5 = (String) localIterator.next();
			boolean i1 = ((Boolean) localTreeMap.get(str5)).booleanValue();
			localArrayList.add(new Boolean(i1));
		}

		localObject2 = localBlogDao.getDiscussMoodCount(str3, str1, str2,
				localTreeMap);
		int m = 0;
		int n = 0;
		for (int i1 = 0; i1 < ((List) localObject2).size(); i1++) {
			m += ((Integer) ((HashMap) ((List) localObject2).get(i1))
					.get("happy")).intValue();
			n += ((Integer) ((HashMap) ((List) localObject2).get(i1))
					.get("unhappy")).intValue();
		}
		double d = 0.0D;
		if (m + n != 0) {
			d = (m * 4 + n * 2) / (m + n);
			d = d * 5.0D / 4.0D;
			DecimalFormat localDecimalFormat = new DecimalFormat("0.0");
			d = Double.parseDouble(localDecimalFormat.format(d));
		}
		((Map) localObject1).put("totalMoodIndex", new Double(d));
		((Map) localObject1).put("totalHappyDays", new Integer(m));
		((Map) localObject1).put("totalUnHappyDays", new Integer(n));
		((Map) localObject1).put("resultCountList", localObject2);
		((Map) localObject1).put("isWorkdayList", localArrayList);
		((Map) localObject1).put("totaldateList", localList1);
		((Map) localObject1).put("totalAttention", new Integer(i));
		((Map) localObject1).put("resourceids", str3);

		return localObject1;
	}

	public double getBlogIndexByOrg(String paramString, int paramInt1,
			int paramInt2, int paramInt3) {
		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		Map localMap = localWorkDayDao.getStartAndEndOfMonth(paramInt2,
				paramInt3);
		String str1 = (String) localMap.get("startdate");
		String str2 = (String) localMap.get("enddate");
		List localList = (List) localMap.get("totaldateList");

		BlogDao localBlogDao = new BlogDao();

		int i = localWorkDayDao.getWorkDays(str1, str2).size();
		int j = localBlogDao.getTotalSubmited(paramString, str1, str2);

		double d1 = 0.0D;
		if ((i != 0) && (paramInt1 != 0))
			d1 = j / (i * paramInt1) * 5.0D;
		DecimalFormat localDecimalFormat = new DecimalFormat("0.0");
		double d2 = Double.parseDouble(localDecimalFormat.format(d1));

		return d2;
	}

	public double getMoodIndexByOrg(String paramString, int paramInt1,
			int paramInt2) {
		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		Map localMap = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str1 = (String) localMap.get("startdate");
		String str2 = (String) localMap.get("enddate");
		List localList1 = (List) localMap.get("totaldateList");

		BlogDao localBlogDao = new BlogDao();

		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str1, str2);

		List localList2 = localBlogDao.getDiscussMoodCount(paramString, str1,
				str2, localTreeMap);
		int i = 0;
		int j = 0;
		for (int k = 0; k < localList2.size(); k++) {
			i += ((Integer) ((HashMap) localList2.get(k)).get("happy"))
					.intValue();
			j += ((Integer) ((HashMap) localList2.get(k)).get("unhappy"))
					.intValue();
		}
		double d = 0.0D;
		if (i + j != 0) {
			d = (i * 4 + j * 2) / (i + j);
			d = d * 5.0D / 4.0D;
			DecimalFormat localDecimalFormat = new DecimalFormat("0.0");
			d = Double.parseDouble(localDecimalFormat.format(d));
		}

		return d;
	}

	public Map getBlogChartReportByOrg(String paramString1,
			String paramString2, String paramString3, int paramInt) {
		HashMap localHashMap = new HashMap();
		ArrayList localArrayList1 = new ArrayList();
		ArrayList localArrayList2 = new ArrayList();

		BlogShareManager localBlogShareManager = new BlogShareManager();
		List localList1 = localBlogShareManager.haveRightView(paramString1);
		List localList2 = localBlogShareManager.getOrgResourceid(paramString2,
				paramString3);

		String str = "";
		int i = 0;
		for (int j = 0; j < localList2.size(); j++) {
			String localObject = (String) localList2.get(j);
			if (localList1.contains(localObject)) {
				str = str + "," + (String) localObject;
				i++;
			}
		}
		if (i == 0) {
			return localHashMap;
		}
		str = str.substring(1);

		BlogDao localBlogDao = new BlogDao();
		Object localObject = localBlogDao.getCompaerMonth(paramInt);
		int k = ((Integer) ((Map) localObject).get("startMonth")).intValue();
		int m = ((Integer) ((Map) localObject).get("endMonth")).intValue();

		for (int n = k; n <= m; n++) {
			double d = getBlogIndexByOrg(str, i, paramInt, n);
			localArrayList1.add(n
					+ SystemEnv.getHtmlLabelName(6076, user.getLanguage()));
			localArrayList2.add(new Double(d));
		}

		localHashMap.put("categoriesList", localArrayList1);
		localHashMap.put("SeriesList", localArrayList2);
		localHashMap.put("SeriesName",
				SystemEnv.getHtmlLabelName(26929, user.getLanguage()));

		return localHashMap;
	}

	public Map getMoodChartReportByOrg(String paramString1,
			String paramString2, String paramString3, int paramInt) {
		HashMap localHashMap = new HashMap();
		ArrayList localArrayList1 = new ArrayList();
		ArrayList localArrayList2 = new ArrayList();

		BlogShareManager localBlogShareManager = new BlogShareManager();
		List localList1 = localBlogShareManager.haveRightView(paramString1);
		List localList2 = localBlogShareManager.getOrgResourceid(paramString2,
				paramString3);

		String str1 = "";
		int i = 0;
		for (int j = 0; j < localList2.size(); j++) {
			String str2 = (String) localList2.get(j);
			if (localList1.contains(str2)) {
				str1 = str1 + "," + str2;
				i++;
			}
		}
		if (i == 0) {
			return localHashMap;
		}
		str1 = str1.substring(1);

		Calendar localCalendar = Calendar.getInstance();
		int k = localCalendar.get(2) + 1;
		int m = localCalendar.get(1);
		int n;
		double d;
		if (paramInt == m) {
			for (n = 1; n <= k; n++) {
				d = getMoodIndexByOrg(str1, paramInt, n);

				localArrayList1.add(n
						+ SystemEnv.getHtmlLabelName(6076, user.getLanguage()));
				localArrayList2.add(new Double(d));
			}
		} else {
			for (n = 1; n <= 12; n++) {
				d = getBlogIndexByOrg(str1, i, paramInt, n);
				localArrayList1.add(n
						+ SystemEnv.getHtmlLabelName(6076, user.getLanguage()));
				localArrayList2.add(new Double(d));
			}
		}

		localHashMap.put("categoriesList", localArrayList1);
		localHashMap.put("SeriesList", localArrayList2);
		localHashMap.put("SeriesName",
				SystemEnv.getHtmlLabelName(26930, user.getLanguage()));

		return localHashMap;
	}

	public double getBlogIndexByOrg(String paramString1, String paramString2,
			String paramString3, int paramInt1, int paramInt2) {
		HashMap localHashMap = new HashMap();
		ArrayList localArrayList1 = new ArrayList();
		ArrayList localArrayList2 = new ArrayList();

		BlogShareManager localBlogShareManager = new BlogShareManager();
		List localList1 = localBlogShareManager.haveRightView(paramString1);
		List localList2 = localBlogShareManager.getOrgResourceid(paramString2,
				paramString3);

		String str1 = "";
		int i = 0;
		for (int j = 0; j < localList2.size(); j++) {
			String str2 = (String) localList2.get(j);
			if (localList1.contains(str2)) {
				str1 = str1 + "," + str2;
				i++;
			}
		}
		if (i == 0) {
			return 0.0D;
		}
		str1 = str1.substring(1);

		double d = getBlogIndexByOrg(str1, i, paramInt1, paramInt2);

		return d;
	}

	public double getMoodIndexByOrg(String paramString1, String paramString2,
			String paramString3, int paramInt1, int paramInt2) {
		HashMap localHashMap = new HashMap();
		ArrayList localArrayList1 = new ArrayList();
		ArrayList localArrayList2 = new ArrayList();

		BlogShareManager localBlogShareManager = new BlogShareManager();
		List localList1 = localBlogShareManager.haveRightView(paramString1);
		List localList2 = localBlogShareManager.getOrgResourceid(paramString2,
				paramString3);

		String str1 = "";
		int i = 0;
		for (int j = 0; j < localList2.size(); j++) {
			String str2 = (String) localList2.get(j);
			if (localList1.contains(str2)) {
				str1 = str1 + "," + str2;
				i++;
			}
		}
		if (i == 0) {
			return 0.0D;
		}
		str1 = str1.substring(1);

		double d = getMoodIndexByOrg(str1, paramInt1, paramInt2);

		return d;
	}

	public Map getOrgReportRecord(String paramString1, String paramString2,
			String paramString3, int paramInt1, int paramInt2) {
		ArrayList localArrayList1 = new ArrayList();
		HashMap localHashMap1 = new HashMap();

		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		Map localMap1 = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str1 = (String) localMap1.get("startdate");
		String str2 = (String) localMap1.get("enddate");
		List localList1 = (List) localMap1.get("totaldateList");
		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str1, str2);
		Iterator localIterator = localTreeMap.keySet().iterator();

		BlogShareManager localBlogShareManager = new BlogShareManager();
		List localList2 = localBlogShareManager.haveRightView(paramString1);
		List localList3 = localBlogShareManager.getOrgResourceid(paramString2,
				paramString3);

		ArrayList localArrayList2 = new ArrayList();
		for (int i = 0; i < localList3.size(); i++) {
			String localObject1 = (String) localList3.get(i);
			if (localList2.contains(localObject1)) {
				localArrayList2.add(localObject1);
			}
		}
		int i = localArrayList2.size();

		Object localObject1 = new BlogDao();
		ArrayList localArrayList3 = new ArrayList();
		int j = 0;
		Object localObject2;
		while (localIterator.hasNext()) {
			HashMap localHashMap2 = new HashMap();
			localObject2 = (String) localIterator.next();
			boolean bool1 = ((Boolean) localTreeMap.get(localObject2))
					.booleanValue();
			if (bool1) {
				j++;
			}
			localArrayList3.add(new Boolean(bool1));
		}

		for (int k = 0; k < localArrayList2.size(); k++) {
			localObject2 = new ArrayList();
			HashMap localHashMap3 = new HashMap();
			String str3 = (String) localArrayList2.get(k);
			Map localMap2 = ((BlogDao) localObject1).getDiscussMapByDate(str3,
					str1, str2);
			localIterator = localTreeMap.keySet().iterator();
			int m = 0;
			while (localIterator.hasNext()) {
				HashMap localHashMap4 = new HashMap();
				String str4 = (String) localIterator.next();
				boolean bool2 = ((Boolean) localTreeMap.get(str4))
						.booleanValue();
				boolean bool3 = localMap2.containsKey(str4);
				((List) localObject2).add(new Boolean(bool3));

				if ((bool2) && (!bool3))
					m++;
			}
			double d1 = 0.0D;
			if (j > 0)
				d1 = (j - m) / j * 5.0D;
			DecimalFormat localDecimalFormat = new DecimalFormat("0.0");
			double d2 = Double.parseDouble(localDecimalFormat.format(d1));

			localHashMap3.put("totalUnsubmit", new Integer(m));
			localHashMap3.put("workIndex", new Double(d2));
			localHashMap3.put("reportList", localObject2);
			localHashMap3.put("attentionid", str3);
			localArrayList1.add(localHashMap3);
		}

		BlogReportManager.ComparatorIndex localComparatorIndex = new BlogReportManager.ComparatorIndex();
		Collections.sort(localArrayList1, localComparatorIndex);

		localHashMap1.put("resultList", localArrayList1);
		localHashMap1.put("totaldateList", localList1);
		localHashMap1.put("totalWorkday", new Integer(j));
		localHashMap1.put("workdayMap", localTreeMap);
		localHashMap1.put("isWorkdayList", localArrayList3);

		return localHashMap1;
	}

	public Map getOrgMoodReportRecord(String paramString1, String paramString2,
			String paramString3, int paramInt1, int paramInt2) {
		ArrayList localArrayList1 = new ArrayList();
		HashMap localHashMap1 = new HashMap();

		WorkDayDao localWorkDayDao = new WorkDayDao(user);
		Map localMap1 = localWorkDayDao.getStartAndEndOfMonth(paramInt1,
				paramInt2);
		String str1 = (String) localMap1.get("startdate");
		String str2 = (String) localMap1.get("enddate");
		List localList1 = (List) localMap1.get("totaldateList");
		TreeMap localTreeMap = localWorkDayDao.getWorkDaysMap(str1, str2);
		Iterator localIterator = localTreeMap.keySet().iterator();

		BlogShareManager localBlogShareManager = new BlogShareManager();
		List localList2 = localBlogShareManager.haveRightView(paramString1);
		List localList3 = localBlogShareManager.getOrgResourceid(paramString2,
				paramString3);

		ArrayList localArrayList2 = new ArrayList();
		for (int i = 0; i < localList3.size(); i++) {
			String localObject1 = (String) localList3.get(i);
			if (localList2.contains(localObject1)) {
				localArrayList2.add(localObject1);
			}
		}
		int i = localArrayList2.size();

		Object localObject1 = new BlogDao();
		ArrayList localArrayList3 = new ArrayList();
		int j = 0;
		while (localIterator.hasNext()) {
			HashMap localObject2 = new HashMap();
			String str3 = (String) localIterator.next();
			boolean bool1 = ((Boolean) localTreeMap.get(str3)).booleanValue();
			if (bool1) {
				j++;
			}
			localArrayList3.add(new Boolean(bool1));
		}
		Object localObject2 = new AppDao();

		for (int k = 0; k < localArrayList2.size(); k++) {
			ArrayList localArrayList4 = new ArrayList();
			HashMap localHashMap2 = new HashMap();
			String str4 = (String) localArrayList2.get(k);
			Map localMap2 = ((BlogDao) localObject1).getDiscussMapByDate(str4,
					str1, str2);
			localIterator = localTreeMap.keySet().iterator();
			int m = 0;
			int n = 0;
			int i1 = 0;
			int i2 = 0;
			int i3 = 0;
			Object localObject3;
			while (localIterator.hasNext()) {
				HashMap localHashMap3 = new HashMap();
				String str5 = (String) localIterator.next();
				boolean bool2 = ((Boolean) localTreeMap.get(str5))
						.booleanValue();
				boolean bool3 = localMap2.containsKey(str5);
				localObject3 = (BlogDiscessVo) localMap2.get(str5);

				if ((bool2) && (!bool3))
					m++;
				if (bool3) {
					AppItemVo localAppItemVo = ((AppDao) localObject2)
							.getappItemByDiscussId(((BlogDiscessVo) localObject3)
									.getId());
					if (localAppItemVo != null) {
						localArrayList4.add(localAppItemVo.getFaceImg());
						n += Integer.parseInt(localAppItemVo.getValue());
						i1++;
						if ("4".equals(localAppItemVo.getValue()))
							i2++;
						else
							i3++;
					} else {
						localArrayList4.add("");
					}
				} else {
					localArrayList4.add("");
				}
			}

			double d1 = 0.0D;
			double d2 = 0.0D;
			if (i1 != 0) {
				d1 = n / i1;
				d1 = d1 * 5.0D / 4.0D;
				localObject3 = new DecimalFormat("0.0");
				d2 = Double.parseDouble(((DecimalFormat) localObject3)
						.format(d1));
			}
			localHashMap2.put("totalUnsubmit", new Integer(m));
			localHashMap2.put("moodIndex", new Double(d2));
			localHashMap2.put("reportList", localArrayList4);
			localHashMap2.put("attentionid", str4);
			localHashMap2.put("unHappyDays", new Integer(i3));
			localHashMap2.put("happyDays", new Integer(i2));
			localArrayList1.add(localHashMap2);
		}

		BlogReportManager.ComparatorIndex localComparatorIndex = new BlogReportManager.ComparatorIndex();

		localHashMap1.put("resultList", localArrayList1);
		localHashMap1.put("totaldateList", localList1);
		localHashMap1.put("totalWorkday", new Integer(j));
		localHashMap1.put("workdayMap", localTreeMap);
		localHashMap1.put("isWorkdayList", localArrayList3);

		return localHashMap1;
	}

	public Map getUserCompareReport(String paramString1, int paramInt1,
			int paramInt2, String paramString2) {
		HashMap localHashMap = new HashMap();
		ArrayList localArrayList1 = new ArrayList();
		ArrayList localArrayList2 = new ArrayList();
		String[] arrayOfString = paramString1.split(",");
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			for (int i = 0; i < arrayOfString.length; i++) {
				localArrayList1.add(localResourceComInfo
						.getLastname(arrayOfString[i]));
				if ("blog".equals(paramString2)) {
					localArrayList2.add(new Double(Double
							.parseDouble(getBlogIndexByUser(arrayOfString[i],
									paramInt1, paramInt2))));
				} else if ("mood".equals(paramString2))
					localArrayList2.add(new Double(Double
							.parseDouble(getMoodIndexByUser(arrayOfString[i],
									paramInt1, paramInt2))));
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		localHashMap.put("categoriesList", localArrayList1);
		localHashMap.put("seriesDataList", localArrayList2);
		localHashMap.put("totalHrm", new Integer(arrayOfString.length));

		return localHashMap;
	}

	public String addReportTemp(String paramString) {
		String str1 = "0";
		RecordSet localRecordSet = new RecordSet();
		String str2 = "insert into blog_reportTemp(userid,tempName) values("
				+ paramString + ",'自定义报表'" + ")";
		localRecordSet.execute(str2);
		str2 = "select max(id) maxid from blog_reportTemp where userid="
				+ paramString;
		localRecordSet.execute(str2);
		if (localRecordSet.next()) {
			str1 = localRecordSet.getString("maxid");
		}
		return str1;
	}

	public boolean editReportTemp(String paramString1, String paramString2) {
		RecordSet localRecordSet = new RecordSet();
		String str = "update blog_reportTemp set tempName='" + paramString2
				+ "' where id=" + paramString1;

		return localRecordSet.execute(str);
	}

	public boolean delReportTemp(String paramString) {
		RecordSet localRecordSet = new RecordSet();
		String str = "delete from blog_reportTemp where id=" + paramString;
		localRecordSet.execute(str);
		str = "delete from blog_tempCondition where tempid=" + paramString;
		return localRecordSet.execute(str);
	}

	public List getReportTempList(String paramString) {
		ArrayList localArrayList = new ArrayList();
		String str1 = "select * from blog_reportTemp where userid="
				+ paramString + " order by id asc";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str1);
		while (localRecordSet.next()) {
			HashMap localHashMap = new HashMap();
			String str2 = localRecordSet.getString("id");
			String str3 = localRecordSet.getString("tempName");
			localHashMap.put("tempid", str2);
			localHashMap.put("tempName", str3);
			localArrayList.add(localHashMap);
		}

		return localArrayList;
	}

	public String addTempCondition(String paramString1, String paramString2,
			String paramString3) {
		RecordSet localRecordSet = new RecordSet();
		String str1 = "insert into blog_tempCondition(tempid,type,content) values("
				+ paramString1
				+ ","
				+ paramString2
				+ ",'"
				+ paramString3
				+ "')";
		localRecordSet.execute(str1);
		str1 = "select max(id) as maxid from blog_tempCondition where tempid="
				+ paramString1;
		localRecordSet.execute(str1);
		localRecordSet.next();
		String str2 = localRecordSet.getString("maxid");

		return str2;
	}

	public boolean delTempCondition(String paramString) {
		RecordSet localRecordSet = new RecordSet();
		String str = "delete from blog_tempCondition where id=" + paramString;
		return localRecordSet.execute(str);
	}

	public List getConditionList(String paramString) {
		ArrayList localArrayList = new ArrayList();
		RecordSet localRecordSet = new RecordSet();
		String str1 = "select * from blog_tempCondition where tempid="
				+ paramString + " order by id asc";
		localRecordSet.execute(str1);

		while (localRecordSet.next()) {
			HashMap localHashMap = new HashMap();
			String str2 = localRecordSet.getString("id");
			String str3 = localRecordSet.getString("type");
			String str4 = localRecordSet.getString("content");

			localHashMap.put("id", str2);
			localHashMap.put("type", str3);
			localHashMap.put("content", str4);

			localArrayList.add(localHashMap);
		}
		return localArrayList;
	}

	public void setUser(User paramUser) {
		user = paramUser;
	}

	class ComparatorIndex implements Comparator {
		ComparatorIndex() {
		}

		public int compare(Object object, Object object2) {
			double d;
			Map map = (Map) object;
			Map map2 = (Map) object2;
			double d2 = (Double) map.get("workIndex");
			if (d2 > (d = ((Double) map2.get("workIndex")).doubleValue())) {
				return 1;
			}
			if (d2 == d) {
				return 0;
			}
			return -1;
		}
	}
}
