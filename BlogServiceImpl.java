package weaver.blog.webservices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;

import weaver.blog.AppDao;
import weaver.blog.AppItemVo;
import weaver.blog.AppVo;
import weaver.blog.BlogDao;
import weaver.blog.BlogDiscessVo;
import weaver.blog.BlogManager;
import weaver.blog.BlogReplyVo;
import weaver.blog.BlogShareManager;
import weaver.blog.service.BlogGroupService;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.company.SubCompanyComInfo;
import weaver.hrm.job.JobTitlesComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.mobile.webservices.common.HtmlUtil;
import weaver.systeminfo.SystemEnv;

public class BlogServiceImpl extends BaseBean implements BlogService {
	public BlogServiceImpl() {
	}

	public BlogUserInfo[] getMyAttentionList(String paramString) {
		BlogUserInfo[] arrayOfBlogUserInfo = null;
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			DepartmentComInfo localDepartmentComInfo = new DepartmentComInfo();
			JobTitlesComInfo localJobTitlesComInfo = new JobTitlesComInfo();
			SubCompanyComInfo localSubCompanyComInfo = new SubCompanyComInfo();

			BlogDao localBlogDao = new BlogDao();
			List localList = localBlogDao.getAttentionMapList(paramString);
			Map[] arrayOfMap = new Map[localList.size()];
			arrayOfBlogUserInfo = new BlogUserInfo[localList.size()];
			for (int i = 0; i < localList.size(); i++) {
				Map localMap = (Map) localList.get(i);
				String str1 = (String) localMap.get("attentionid");
				String str2 = (String) localMap.get("username");
				String str3 = (String) localMap.get("isnew");
				String str4 = (String) localMap.get("deptName");
				String str5 = localJobTitlesComInfo
						.getJobTitlesname(localResourceComInfo
								.getJobTitle(str1));
				String str6 = localSubCompanyComInfo.getSubCompanyname(str1);
				String str7 = localResourceComInfo.getMessagerUrls(str1);

				BlogUserInfo localBlogUserInfo = new BlogUserInfo();
				localBlogUserInfo.setUserid(str1);
				localBlogUserInfo.setDeptName(str4);
				localBlogUserInfo.setIsnew(str3);
				localBlogUserInfo.setJobtitle(str5);
				localBlogUserInfo.setUsername(str2);
				localBlogUserInfo.setSubName(str6);
				localBlogUserInfo.setImageUrl(str7);

				arrayOfBlogUserInfo[i] = localBlogUserInfo;
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}

		return arrayOfBlogUserInfo;
	}

	public BlogDiscessVo[] getBlogDiscussListByTime(String paramString,
			int paramInt1, int paramInt2) {
		return getBlogDiscussListByTime(paramString, paramInt1, paramInt2,
				"all");
	}

	public BlogDiscessVo[] getBlogDiscussListByTime(String paramString1,
			int paramInt1, int paramInt2, String paramString2) {
		BlogDao localBlogDao = new BlogDao();
		List localList1 = localBlogDao.getBlogDiscussVOList(paramString1,
				paramInt1, paramInt2, paramString2);
		List localList2 = localBlogDao.getUpdateDiscussidList(paramString1);

		BlogDiscessVo[] arrayOfBlogDiscessVo = new BlogDiscessVo[localList1
				.size()];
		AppDao localAppDao = new AppDao();
		boolean bool = localAppDao.getAppVoByType("mood").isActive();
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			JobTitlesComInfo localJobTitlesComInfo = new JobTitlesComInfo();
			for (int i = 0; i < localList1.size(); i++) {
				BlogDiscessVo localBlogDiscessVo = (BlogDiscessVo) localList1
						.get(i);
				String str1 = localBlogDiscessVo.getId();
				String str2 = localBlogDiscessVo.getUserid();
				String str3 = localList2.contains(str1) ? "0" : "1";
				List localList3 = localBlogDao.getReplyList(str2,
						localBlogDiscessVo.getWorkdate(), paramString1);
				BlogReplyVo[] arrayOfBlogReplyVo = new BlogReplyVo[localList3
						.size()];
				for (int j = 0; j < localList3.size(); j++) {
					BlogReplyVo localObject = (BlogReplyVo) localList3.get(j);
					String str4 = ((BlogReplyVo) localObject).getUserid();
					String str5 = localResourceComInfo.getLastname(str4);

					((BlogReplyVo) localObject).setUsername(str5);
					((BlogReplyVo) localObject)
							.setImageurl(localResourceComInfo
									.getMessagerUrls(str4));

					arrayOfBlogReplyVo[j] = localObject;
				}

				List localList4 = localBlogDao.getLocationList(str1);
				localBlogDiscessVo.setLocationList(localList4);

				localBlogDiscessVo.setIsnew(str3);
				localBlogDiscessVo.setReplyVoArray(arrayOfBlogReplyVo);
				localBlogDiscessVo.setUsername(localResourceComInfo
						.getLastname(str2));
				localBlogDiscessVo.setImageurl(localResourceComInfo
						.getMessagerUrls(str2));
				localBlogDiscessVo.setJobtitle(localJobTitlesComInfo
						.getJobTitlesname(localResourceComInfo
								.getJobTitle(str2)));
				localBlogDiscessVo.setManagerid(localResourceComInfo
						.getManagerID(str2));
				localBlogDiscessVo.setIsHasLocation(localBlogDao
						.isHasLocation(str1) ? "1" : "0");
				localBlogDiscessVo
						.setContents(blogHtmlFormate(localBlogDiscessVo
								.getContent()));

				Object localObject = null;
				if ((bool) && (str1 != null)) {
					localObject = localAppDao.getappItemByDiscussId(str1);
					if (localObject != null) {
						((AppItemVo) localObject).setItemName(SystemEnv
								.getHtmlLabelName(Util
										.getIntValue(((AppItemVo) localObject)
												.getItemName()), 7));
					}
				}
				localBlogDiscessVo.setAppItemVo((AppItemVo) localObject);

				arrayOfBlogDiscessVo[i] = localBlogDiscessVo;
			}
		} catch (Exception localException) {
		}

		return arrayOfBlogDiscessVo;
	}

	public BlogDiscessVo[] getDiscussListByDate(String paramString1,
			String paramString2, String paramString3, String paramString4) {
		User localUser = getUser(paramString2);
		BlogManager localBlogManager = new BlogManager(localUser);
		List localList1 = localBlogManager.getDiscussListAll(paramString2,
				paramString3, paramString4);
		BlogDiscessVo[] arrayOfBlogDiscessVo = new BlogDiscessVo[localList1
				.size()];
		BlogDao localBlogDao = new BlogDao();
		AppDao localAppDao = new AppDao();
		boolean bool = localAppDao.getAppVoByType("mood").isActive();

		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			JobTitlesComInfo localJobTitlesComInfo = new JobTitlesComInfo();
			for (int i = 0; i < localList1.size(); i++) {
				BlogDiscessVo localBlogDiscessVo = (BlogDiscessVo) localList1
						.get(i);
				String str1 = localBlogDiscessVo.getId();
				localBlogDiscessVo.setUserid(paramString2);
				localBlogDiscessVo.setUsername(localResourceComInfo
						.getLastname(paramString2));

				List localList2 = localBlogDao.getReplyList(paramString2,
						localBlogDiscessVo.getWorkdate(), paramString1);
				BlogReplyVo[] arrayOfBlogReplyVo = new BlogReplyVo[localList2
						.size()];
				for (int j = 0; j < localList2.size(); j++) {
					BlogReplyVo localObject = (BlogReplyVo) localList2.get(j);
					String str2 = ((BlogReplyVo) localObject).getUserid();
					String str3 = localResourceComInfo.getLastname(str2);

					((BlogReplyVo) localObject).setUsername(str3);
					((BlogReplyVo) localObject)
							.setImageurl(localResourceComInfo
									.getMessagerUrls(str2));

					arrayOfBlogReplyVo[j] = localObject;
				}

				List localList3 = localBlogDao.getLocationList(str1);
				localBlogDiscessVo.setLocationList(localList3);

				localBlogDiscessVo.setReplyVoArray(arrayOfBlogReplyVo);
				localBlogDiscessVo.setImageurl(localResourceComInfo
						.getMessagerUrls(paramString2));
				localBlogDiscessVo.setJobtitle(localJobTitlesComInfo
						.getJobTitlesname(localResourceComInfo
								.getJobTitle(paramString2)));
				localBlogDiscessVo.setManagerid(localResourceComInfo
						.getManagerID(paramString2));
				localBlogDiscessVo.setIsHasLocation(localBlogDao
						.isHasLocation(str1) ? "1" : "0");
				localBlogDiscessVo
						.setContents(blogHtmlFormate(localBlogDiscessVo
								.getContent()));

				Object localObject = null;
				if ((bool) && (str1 != null)) {
					localObject = localAppDao.getappItemByDiscussId(str1);
					if (localObject != null) {
						((AppItemVo) localObject).setItemName(SystemEnv
								.getHtmlLabelName(Util
										.getIntValue(((AppItemVo) localObject)
												.getItemName()), 7));
					}
				}
				localBlogDiscessVo.setAppItemVo((AppItemVo) localObject);

				arrayOfBlogDiscessVo[i] = localBlogDiscessVo;
			}
		} catch (Exception localException) {
			localException.printStackTrace();
			writeLog(localException);
		}

		return arrayOfBlogDiscessVo;
	}

	public BlogUserInfo getUserInfo(String paramString) {
		BlogUserInfo localBlogUserInfo = new BlogUserInfo();
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			DepartmentComInfo localDepartmentComInfo = new DepartmentComInfo();
			SubCompanyComInfo localSubCompanyComInfo = new SubCompanyComInfo();
			JobTitlesComInfo localJobTitlesComInfo = new JobTitlesComInfo();

			String str1 = localResourceComInfo.getLastname(paramString);
			String str2 = localSubCompanyComInfo
					.getSubCompanyname(localResourceComInfo
							.getSubCompanyID(paramString));
			String str3 = localDepartmentComInfo
					.getDepartmentname(localResourceComInfo
							.getDepartmentID(paramString));
			String str4 = localJobTitlesComInfo
					.getJobTitlesname(localResourceComInfo
							.getJobTitle(paramString));
			String str5 = localResourceComInfo.getMessagerUrls(paramString);
			String str6 = localResourceComInfo.getSexs(paramString);

			BlogDao localBlogDao = new BlogDao();

			String str7 = "" + localBlogDao.getMyBlogCount(paramString);
			String str8 = ""
					+ localBlogDao.getMyAttentionCount(paramString, "all");
			String str9 = "" + localBlogDao.getAttentionMe(paramString).size();

			localBlogUserInfo.setAttentionCount(str8);
			localBlogUserInfo.setAttentionMeCount(str9);
			localBlogUserInfo.setBlogCount(str7);
			localBlogUserInfo.setSubName(str2);
			localBlogUserInfo.setDeptName(str3);
			localBlogUserInfo.setJobtitle(str4);
			localBlogUserInfo.setUserid(paramString);
			localBlogUserInfo.setUsername(str1);
			localBlogUserInfo.setImageUrl(str5);
			localBlogUserInfo.setSex(str6);
		} catch (Exception localException) {
			localException.printStackTrace();
			writeLog(localException);
		}

		return localBlogUserInfo;
	}

	public BlogDiscessVo saveBlogDiscuss(String paramString1,
			String paramString2, String paramString3, String paramString4,
			String paramString5) {
		return saveBlogDiscuss(paramString1, paramString2, paramString3,
				paramString4, paramString5, "", "1", "");
	}

	public BlogDiscessVo saveBlogDiscuss(String paramString1,
			String paramString2, String paramString3, String paramString4,
			String paramString5, String paramString6, String paramString7,
			String paramString8) {
		ConnStatement localConnStatement = new ConnStatement();
		int i = 0;
		String str1 = "";
		String str2 = "";
		paramString7 = "2".equals(paramString7) ? "2" : "1";
		BlogDiscessVo localBlogDiscessVo = new BlogDiscessVo();
		String localObject4;
		try {
			Date localDate = new Date();
			SimpleDateFormat localObject1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat localObject2 = new SimpleDateFormat("HH:mm:ss");

			str1 = ((SimpleDateFormat) localObject1).format(localDate);
			str2 = ((SimpleDateFormat) localObject2).format(localDate);

			paramString4 = str1.equals(paramString3) ? "0" : "1";

			BlogDao localBlogDao = new BlogDao();
			localBlogDiscessVo = localBlogDao.getDiscussVoByDate(paramString1,
					paramString3);
			if (localBlogDiscessVo != null) {
				localBlogDiscessVo = getBlogDiscessVo(paramString1,
						paramString3, localBlogDiscessVo.getId());
			}
			String[] localObject3 = Util.TokenizerString2(paramString8, ",");
			for (int j = 0; j < localObject3.length; j++) {
				if (!"".equals(localObject3[j])) {
					paramString2 = paramString2
							+ "<img src='/weaver/weaver.file.FileDownload?fileid="
							+ localObject3[j] + "'/>";
				}
			}
			if (localBlogDiscessVo == null) {
				localObject4 = "insert into blog_discuss (userid, createdate, createtime,content,lastUpdatetime,isReplenish,workdate,score,comefrom)values (?, ?,?,?,?,?,?,?,?)";

				localConnStatement.setStatementSql((String) localObject4);
				localConnStatement.setString(1, paramString1);
				localConnStatement.setString(2, str1);
				localConnStatement.setString(3, str2);
				localConnStatement.setString(4, paramString2);
				localConnStatement.setString(5, "");
				localConnStatement.setString(6, paramString4);
				localConnStatement.setString(7, paramString3);
				localConnStatement.setString(8, "0");
				localConnStatement.setString(9, paramString5);
				i = localConnStatement.executeUpdate();
			} else {
				paramString2 = localBlogDiscessVo.getContent() + paramString2;
				updateBlogDiscuss(localBlogDiscessVo.getId(), paramString2,
						paramString7, "");
			}
		} catch (Exception localException) {
			writeLog(localException);
		} finally {
			localConnStatement.close();
		}
		RecordSet localRecordSet = new RecordSet();
		Object localObject1 = "";
		Object localObject2 = "";
		BlogDao localBlogDao = new BlogDao();
		Object localObject3 = new AppDao();
		if (localBlogDiscessVo == null) {
			localObject1 = "select max(id) as maxid from blog_discuss where userid="
					+ paramString1;
			localRecordSet.execute((String) localObject1);

			if (localRecordSet.next()) {
				localObject2 = localRecordSet.getString("maxid");
				localBlogDiscessVo = getBlogDiscessVo(paramString1,
						paramString3, (String) localObject2);
			}

			localObject1 = "DELETE FROM blog_read WHERE blogid='"
					+ paramString1 + "'";
			localRecordSet.executeSql((String) localObject1);

			List localObject41 = localBlogDao.getAttentionMe(paramString1);
			for (int k = 0; k < ((List) localObject41).size(); k++) {
				localBlogDao.addRemind((String) ((List) localObject41).get(k),
						paramString1, "6", "" + (String) localObject2, "0");
			}

			if (((AppDao) localObject3).getAppVoByType("mood").isActive()) {
				localObject1 = "INSERT INTO blog_appDatas(userid,workDate,createDate,createTime,discussid,appitemId)VALUES('"
						+ paramString1
						+ "','"
						+ paramString3
						+ "','"
						+ str1
						+ "','"
						+ str2
						+ "','"
						+ (String) localObject2
						+ "','"
						+ paramString7 + "')";

				localRecordSet.executeSql((String) localObject1);
			}
		} else {
			localObject2 = localBlogDiscessVo.getId();

			if (((AppDao) localObject3).getAppVoByType("mood").isActive()) {
				localObject1 = "update blog_appDatas set  appitemId='"
						+ paramString7 + "' where discussid='"
						+ (String) localObject2 + "'";
				localRecordSet.executeSql((String) localObject1);
			}
		}
		if ((!paramString6.equals("")) && (paramString4.equals("0"))) {
			String[] localObject42 = Util.TokenizerString2(paramString6, ",");
			String str3 = "";
			if (localObject42.length == 3) {
				paramString6 = localObject42[0] + "," + localObject42[1];
				str3 = localObject42[2];
			} else if (localObject42.length == 2) {
				paramString6 = localObject42[0] + "," + localObject42[1];
			}
			localObject1 = "select id from blog_location where discussid="
					+ (String) localObject2 + " and (location='" + paramString6
					+ "' or locationName='" + str3 + "')";
			localRecordSet.executeSql((String) localObject1);
			if ((!localRecordSet.next()) && (!"".equals(paramString6))
					&& ("0".equals(paramString4))) {
				localObject1 = "insert into blog_location(discussid,location,locationName,createtime) values("
						+ (String) localObject2
						+ ",'"
						+ paramString6
						+ "','"
						+ str3 + "','" + str2 + "')";
				localRecordSet.executeSql((String) localObject1);
			}
		}
		localBlogDiscessVo = getBlogDiscessVo(paramString1, paramString3,
				(String) localObject2);
		localBlogDiscessVo.setIsHasLocation(localBlogDao
				.isHasLocation((String) localObject2) ? "1" : "0");
		return localBlogDiscessVo;
	}

	public void updateBlogDiscuss(String paramString1, String paramString2) {
		updateBlogDiscuss(paramString1, paramString2, "1", "");
	}

	public void updateBlogDiscuss(String paramString1, String paramString2,
			String paramString3, String paramString4) {
		ConnStatement localConnStatement = new ConnStatement();
		RecordSet localRecordSet = new RecordSet();

		String[] arrayOfString = Util.TokenizerString2(paramString4, ",");
		for (int i = 0; i < arrayOfString.length; i++) {
			if (!"".equals(arrayOfString[i])) {
				paramString2 = paramString2
						+ "<img src='/weaver/weaver.file.FileDownload?fileid="
						+ arrayOfString[i] + "'/>";
			}
		}
		try {
			String str = "update blog_discuss set content=? where id=?";
			localConnStatement.setStatementSql(str);

			localConnStatement.setString(1, "" + paramString2);
			localConnStatement.setString(2, "" + paramString1);
			localConnStatement.executeUpdate();

			str = "update blog_appDatas set  appitemId='" + paramString3
					+ "' where discussid='" + paramString1 + "'";
			localRecordSet.executeSql(str);
		} catch (Exception localException) {
			localException.printStackTrace();
			writeLog(localException);
		} finally {
			localConnStatement.close();
		}
	}

	public BlogDiscessVo getBlogDiscessVo(String paramString1,
			String paramString2, String paramString3) {
		BlogDao localBlogDao = new BlogDao();
		BlogDiscessVo localBlogDiscessVo = localBlogDao
				.getDiscussVo(paramString3);
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			JobTitlesComInfo localJobTitlesComInfo = new JobTitlesComInfo();

			String str1 = localBlogDiscessVo.getUserid();

			List localList1 = localBlogDao.getReplyList(paramString1,
					paramString2, paramString1);
			BlogReplyVo[] arrayOfBlogReplyVo = new BlogReplyVo[localList1
					.size()];
			for (int i = 0; i < localList1.size(); i++) {
				BlogReplyVo localObject1 = (BlogReplyVo) localList1.get(i);
				String str2 = ((BlogReplyVo) localObject1).getUserid();
				String localObject2 = localResourceComInfo.getLastname(str2);

				((BlogReplyVo) localObject1).setUsername((String) localObject2);
				((BlogReplyVo) localObject1).setImageurl(localResourceComInfo
						.getMessagerUrls(str2));

				arrayOfBlogReplyVo[i] = localObject1;
			}

			List localList2 = localBlogDao.getLocationList(paramString3);
			localBlogDiscessVo.setLocationList(localList2);

			localBlogDiscessVo.setReplyVoArray(arrayOfBlogReplyVo);
			localBlogDiscessVo.setUsername(localResourceComInfo
					.getLastname(str1));
			localBlogDiscessVo.setImageurl(localResourceComInfo
					.getMessagerUrls(str1));
			localBlogDiscessVo.setJobtitle(localJobTitlesComInfo
					.getJobTitlesname(localResourceComInfo.getJobTitle(str1)));
			localBlogDiscessVo.setManagerid(localResourceComInfo
					.getManagerID(str1));
			localBlogDiscessVo.setIsHasLocation(localBlogDao
					.isHasLocation(localBlogDiscessVo.getId()) ? "1" : "0");
			localBlogDiscessVo.setContents(blogHtmlFormate(localBlogDiscessVo
					.getContent()));

			Object localObject1 = new AppDao();
			boolean bool = ((AppDao) localObject1).getAppVoByType("mood")
					.isActive();
			Object localObject2 = null;
			if ((bool) && (paramString3 != null)) {
				localObject2 = ((AppDao) localObject1)
						.getappItemByDiscussId(paramString3);
				if (localObject2 != null) {
					((AppItemVo) localObject2).setItemName(SystemEnv
							.getHtmlLabelName(Util
									.getIntValue(((AppItemVo) localObject2)
											.getItemName()), 7));
				}
			}
			localBlogDiscessVo.setAppItemVo((AppItemVo) localObject2);
		} catch (Exception localException) {
			localException.printStackTrace();
		}

		return localBlogDiscessVo;
	}

	public BlogReplyVo saveBlogReply(String paramString1, String paramString2,
			String paramString3, String paramString4, String paramString5,
			String paramString6, String paramString7, String paramString8) {
		ConnStatement localConnStatement = new ConnStatement();
		int i = 0;
		ResourceComInfo localResourceComInfo = null;
		String str2;
		try {
			localResourceComInfo = new ResourceComInfo();
			Date localDate = new Date();
			SimpleDateFormat localObject1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat localObject2 = new SimpleDateFormat("HH:mm:ss");

			String localObject3 = ((SimpleDateFormat) localObject1).format(localDate);
			String str1 = ((SimpleDateFormat) localObject2).format(localDate);

			str2 = "insert into blog_reply (userid, discussid, createdate, createtime, content,comefrom,workdate,bediscussantid,commentType,relatedid)VALUES (?, ?,?,?,?,?,?,?,?,?)";

			localConnStatement.setStatementSql(str2);
			localConnStatement.setString(1, paramString1);
			localConnStatement.setString(2, paramString3);
			localConnStatement.setString(3, (String) localObject3);
			localConnStatement.setString(4, str1);
			localConnStatement.setString(5, paramString4);
			localConnStatement.setString(6, paramString5);
			localConnStatement.setString(7, paramString6);
			localConnStatement.setString(8, paramString2);
			localConnStatement.setString(9, paramString7);
			localConnStatement.setString(10, paramString8);
			i = localConnStatement.executeUpdate();
		} catch (Exception localException) {
			writeLog(localException);
		} finally {
			localConnStatement.close();
		}

		BlogReplyVo localBlogReplyVo = new BlogReplyVo();

		Object localObject1 = new RecordSet();
		Object localObject2 = "select max(id) as maxid from blog_reply where userid="
				+ paramString1;
		((RecordSet) localObject1).execute((String) localObject2);

		Object localObject3 = new BlogDao();
		String str1 = "";
		if (((RecordSet) localObject1).next()) {
			str1 = ((RecordSet) localObject1).getString("maxid");
			localBlogReplyVo = ((BlogDao) localObject3).getReplyById(str1);

			str2 = localResourceComInfo.getLastname(paramString1);
			localBlogReplyVo.setUsername(str2);
			localBlogReplyVo.setImageurl(localResourceComInfo
					.getMessagerUrls(paramString1));
		}

		if (!paramString1.equals(paramString2)) {
			((BlogDao) localObject3).addRemind(paramString2, "" + paramString1,
					"9", paramString3 + "|0|" + str1, "0");
		}

		return localBlogReplyVo;
	}

	public void writeBlogReadFlag(String paramString1, String paramString2,
			String paramString3) {
		String str = "";
		RecordSet localRecordSet = new RecordSet();

		if (!"".equals(paramString3)) {
			str = "delete from blog_remind where remindType=6 and remindid="
					+ paramString1 + " and remindValue='" + paramString3 + "'";
			localRecordSet.execute(str);
		} else {
			str = "delete from blog_remind where remindType=6 and remindid="
					+ paramString1 + " and relatedid=" + paramString2;
			localRecordSet.execute(str);
		}
		BlogDao localBlogDao = new BlogDao();
		localBlogDao.addReadRecord(paramString1, paramString2);
		localBlogDao.addVisitRecord(paramString1, paramString2);
	}

	public String[] getMenuItemCount(String paramString) {
		BlogDao localBlogDao = new BlogDao();

		String str1 = localBlogDao.getRemindUnReadCount(paramString, "update");

		String str2 = localBlogDao
				.getRemindUnReadCount(paramString, "commment");

		List localList = localBlogDao.getAttentionMapList(paramString);

		String str3 = "" + localList.size();

		String[] arrayOfString = new String[2];
		arrayOfString[0] = str1;
		arrayOfString[1] = str2;

		return arrayOfString;
	}

	public int getBlogDiscussListByTimeCount(String paramString) {
		return getBlogDiscussListByTimeCount(paramString, "all");
	}

	public int getBlogDiscussListByTimeCount(String paramString1,
			String paramString2) {
		BlogManager localBlogManager = new BlogManager();
		String str = localBlogManager.getMyAttentionByGroup(paramString1,
				paramString2);

		BlogDao localBlogDao = new BlogDao();
		int i = localBlogDao.getBlogDiscussCount(str);

		return i;
	}

	public int isSubmit(String paramString1, String paramString2) {
		int i = 0;
		User localUser = getUser(paramString1);
		BlogDao localBlogDao = new BlogDao();
		i = localBlogDao.isNeedSubmit(localUser, paramString2);
		return i;
	}

	public String getBlogEnabledate() {
		BlogDao localBlogDao = new BlogDao();
		String str = localBlogDao.getSysSetting("enableDate");
		return str;
	}

	public int getMyAttentionCount(String paramString) {
		BlogDao localBlogDao = new BlogDao();
		int i = localBlogDao.getMyAttentionCount(paramString, "all");
		return i;
	}

	public int getMyAttentionCount(String paramString1, String paramString2,
			Map paramMap) {
		BlogDao localBlogDao = new BlogDao();
		int i = localBlogDao.getMyAttentionCount(paramString1, paramString2,
				paramMap);
		return i;
	}

	public BlogUserInfo[] getAttentionList(String paramString, int paramInt1,
			int paramInt2, int paramInt3) {
		return getAttentionList(paramString, paramInt1, paramInt2, paramInt3,
				"all", null);
	}

	public BlogUserInfo[] getAttentionList(String paramString1, int paramInt1,
			int paramInt2, int paramInt3, String paramString2, Map paramMap) {
		BlogUserInfo[] arrayOfBlogUserInfo = null;
		paramString2 = (paramString2 == null) || (paramString2.equals("")) ? "all"
				: paramString2;
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			DepartmentComInfo localDepartmentComInfo = new DepartmentComInfo();
			JobTitlesComInfo localJobTitlesComInfo = new JobTitlesComInfo();
			SubCompanyComInfo localSubCompanyComInfo = new SubCompanyComInfo();

			BlogDao localBlogDao = new BlogDao();
			List localList = localBlogDao.getAttentionMapList(paramString1,
					paramInt1, paramInt2, paramInt3, paramString2, paramMap);
			Map[] arrayOfMap = new Map[localList.size()];
			arrayOfBlogUserInfo = new BlogUserInfo[localList.size()];
			for (int i = 0; i < localList.size(); i++) {
				Map localMap = (Map) localList.get(i);
				String str1 = (String) localMap.get("attentionid");
				String str2 = (String) localMap.get("username");
				String str3 = (String) localMap.get("isnew");
				String str4 = (String) localMap.get("deptName");
				String str5 = localJobTitlesComInfo
						.getJobTitlesname(localResourceComInfo
								.getJobTitle(str1));
				String str6 = localSubCompanyComInfo
						.getSubCompanyname(localResourceComInfo
								.getSubCompanyID(str1));
				String str7 = localResourceComInfo.getMessagerUrls(str1);

				BlogUserInfo localBlogUserInfo = new BlogUserInfo();
				localBlogUserInfo.setUserid(str1);
				localBlogUserInfo.setDeptName(str4);
				localBlogUserInfo.setIsnew(str3);
				localBlogUserInfo.setJobtitle(str5);
				localBlogUserInfo.setUsername(str2);
				localBlogUserInfo.setSubName(str6);
				localBlogUserInfo.setImageUrl(str7);

				arrayOfBlogUserInfo[i] = localBlogUserInfo;
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}

		return arrayOfBlogUserInfo;
	}

	public int viewRight(String paramString1, String paramString2) {
		BlogShareManager localBlogShareManager = new BlogShareManager();
		int i = localBlogShareManager.viewRight(paramString2, paramString1);
		return i;
	}

	private User getUser(String paramString) {
		User localUser = new User();
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			localUser.setUid(Util.getIntValue(paramString, 0));
			localUser.setLoginid(localResourceComInfo.getLoginID(paramString));
			localUser.setFirstname(localResourceComInfo
					.getFirstname(paramString));
			localUser
					.setLastname(localResourceComInfo.getLastname(paramString));
			localUser.setLogintype("1");
			localUser.setSex(localResourceComInfo.getSexs(paramString));
			localUser.setLanguage(7);
			localUser.setEmail(localResourceComInfo.getEmail(paramString));
			localUser.setLocationid(localResourceComInfo
					.getLocationid(paramString));
			localUser.setResourcetype(localResourceComInfo
					.getResourcetype(paramString));
			localUser
					.setJobtitle(localResourceComInfo.getJobTitle(paramString));
			localUser
					.setJoblevel(localResourceComInfo.getJoblevel(paramString));
			localUser
					.setSeclevel(localResourceComInfo.getSeclevel(paramString));
			localUser.setUserDepartment(Util.getIntValue(
					localResourceComInfo.getDepartmentID(paramString), 0));
			localUser.setManagerid(localResourceComInfo
					.getManagerID(paramString));
			localUser.setAssistantid(localResourceComInfo
					.getAssistantID(paramString));

			RecordSet localRecordSet = new RecordSet();
			String str = "select countryid from hrmresource where id="
					+ paramString;
			localRecordSet.execute(str);
			if (localRecordSet.next())
				localUser.setCountryid(localRecordSet.getString("countryid"));
			localUser.setUserSubCompany1(Util.getIntValue(
					localResourceComInfo.getSubCompanyID(paramString), 0));
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return localUser;
	}

	public void sendSubmitRemind(String paramString1, String paramString2,
			String paramString3) {
		String str = "select id from blog_remind where remindid="
				+ paramString2 + " and relatedid=" + paramString1
				+ " and remindType=7 and remindValue='" + paramString3 + "'";
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);

		if (!localRecordSet.next()) {
			BlogDao localBlogDao = new BlogDao();
			localBlogDao.addRemind(paramString2, paramString1, "7",
					paramString3, "0");
		}
	}

	public BlogDiscessVo[] getCommentList(String paramString) {
		BlogDao localBlogDao = new BlogDao();
		List localList1 = localBlogDao.getCommentList(paramString);

		BlogDiscessVo[] arrayOfBlogDiscessVo = new BlogDiscessVo[localList1
				.size()];

		AppDao localAppDao = new AppDao();
		boolean bool = localAppDao.getAppVoByType("mood").isActive();
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			JobTitlesComInfo localJobTitlesComInfo = new JobTitlesComInfo();
			for (int i = 0; i < localList1.size(); i++) {
				BlogDiscessVo localBlogDiscessVo = (BlogDiscessVo) localList1
						.get(i);

				String str1 = Util.null2String(localBlogDiscessVo.getId());
				String str2 = localBlogDiscessVo.getUserid();
				String str3 = localBlogDiscessVo.getWorkdate();

				List localList2 = localBlogDao.getReplyList(str2, str3,
						paramString);
				BlogReplyVo[] arrayOfBlogReplyVo = new BlogReplyVo[localList2
						.size()];
				for (int j = 0; j < localList2.size(); j++) {
					BlogReplyVo localObject = (BlogReplyVo) localList2.get(j);
					String str4 = ((BlogReplyVo) localObject).getUserid();
					String str5 = localResourceComInfo.getLastname(str4);

					((BlogReplyVo) localObject).setUsername(str5);
					((BlogReplyVo) localObject)
							.setImageurl(localResourceComInfo
									.getMessagerUrls(str4));

					arrayOfBlogReplyVo[j] = localObject;
				}

				List localList3 = localBlogDao
						.getLocationList(localBlogDiscessVo.getId());
				localBlogDiscessVo.setLocationList(localList3);

				localBlogDiscessVo.setReplyVoArray(arrayOfBlogReplyVo);
				localBlogDiscessVo.setUsername(localResourceComInfo
						.getLastname(str2));
				localBlogDiscessVo.setImageurl(localResourceComInfo
						.getMessagerUrls(str2));
				localBlogDiscessVo.setJobtitle(localJobTitlesComInfo
						.getJobTitlesname(localResourceComInfo
								.getJobTitle(str2)));
				localBlogDiscessVo.setManagerid(localResourceComInfo
						.getManagerID(str2));
				localBlogDiscessVo.setIsHasLocation(localBlogDao
						.isHasLocation(localBlogDiscessVo.getId()) ? "1" : "0");
				localBlogDiscessVo
						.setContents(blogHtmlFormate(localBlogDiscessVo
								.getContent()));

				Object localObject = null;
				if ((bool) && (!"".equals(str1))) {
					localObject = localAppDao.getappItemByDiscussId(str1);
					if (localObject != null) {
						((AppItemVo) localObject).setItemName(SystemEnv
								.getHtmlLabelName(Util
										.getIntValue(((AppItemVo) localObject)
												.getItemName()), 7));
					}
				}
				localBlogDiscessVo.setAppItemVo((AppItemVo) localObject);

				arrayOfBlogDiscessVo[i] = localBlogDiscessVo;
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return arrayOfBlogDiscessVo;
	}

	public BlogDiscessVo[] getCommentList(String paramString, int paramInt1,
			int paramInt2, int paramInt3) {
		BlogDao localBlogDao = new BlogDao();
		List localList1 = localBlogDao.getCommentDiscussVOList(paramString,
				paramInt1, paramInt2, paramInt3);

		BlogDiscessVo[] arrayOfBlogDiscessVo = new BlogDiscessVo[localList1
				.size()];

		AppDao localAppDao = new AppDao();
		boolean bool = localAppDao.getAppVoByType("mood").isActive();
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			JobTitlesComInfo localJobTitlesComInfo = new JobTitlesComInfo();
			for (int i = 0; i < localList1.size(); i++) {
				BlogDiscessVo localBlogDiscessVo = (BlogDiscessVo) localList1
						.get(i);

				String str1 = Util.null2String(localBlogDiscessVo.getId());
				String str2 = localBlogDiscessVo.getUserid();
				String str3 = localBlogDiscessVo.getWorkdate();

				List localList2 = localBlogDao.getReplyList(str2, str3,
						paramString);
				BlogReplyVo[] arrayOfBlogReplyVo = new BlogReplyVo[localList2
						.size()];
				for (int j = 0; j < localList2.size(); j++) {
					BlogReplyVo localObject = (BlogReplyVo) localList2.get(j);
					String str4 = ((BlogReplyVo) localObject).getUserid();
					String str5 = localResourceComInfo.getLastname(str4);

					((BlogReplyVo) localObject).setUsername(str5);
					((BlogReplyVo) localObject)
							.setImageurl(localResourceComInfo
									.getMessagerUrls(str4));

					arrayOfBlogReplyVo[j] = localObject;
				}

				List localList3 = localBlogDao
						.getLocationList(localBlogDiscessVo.getId());
				localBlogDiscessVo.setLocationList(localList3);

				localBlogDiscessVo.setReplyVoArray(arrayOfBlogReplyVo);
				localBlogDiscessVo.setUsername(localResourceComInfo
						.getLastname(str2));
				localBlogDiscessVo.setImageurl(localResourceComInfo
						.getMessagerUrls(str2));
				localBlogDiscessVo.setJobtitle(localJobTitlesComInfo
						.getJobTitlesname(localResourceComInfo
								.getJobTitle(str2)));
				localBlogDiscessVo.setManagerid(localResourceComInfo
						.getManagerID(str2));
				localBlogDiscessVo.setIsHasLocation(localBlogDao
						.isHasLocation(localBlogDiscessVo.getId()) ? "1" : "0");
				localBlogDiscessVo
						.setContents(blogHtmlFormate(localBlogDiscessVo
								.getContent()));

				Object localObject = null;
				if ((bool) && (!"".equals(str1))) {
					localObject = localAppDao.getappItemByDiscussId(str1);
					if (localObject != null) {
						((AppItemVo) localObject).setItemName(SystemEnv
								.getHtmlLabelName(Util
										.getIntValue(((AppItemVo) localObject)
												.getItemName()), 7));
					}
				}
				localBlogDiscessVo.setAppItemVo((AppItemVo) localObject);

				arrayOfBlogDiscessVo[i] = localBlogDiscessVo;
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return arrayOfBlogDiscessVo;
	}

	public void addScore(String paramString1, String paramString2) {
		BlogDao localBlogDao = new BlogDao();
		localBlogDao.addScore(paramString1, paramString2);
	}

	public BlogUserInfo[] getAttentionMeList(String paramString, int paramInt1,
			int paramInt2, List paramList) {
		BlogUserInfo[] arrayOfBlogUserInfo = null;
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			DepartmentComInfo localDepartmentComInfo = new DepartmentComInfo();
			JobTitlesComInfo localJobTitlesComInfo = new JobTitlesComInfo();
			SubCompanyComInfo localSubCompanyComInfo = new SubCompanyComInfo();

			if (paramInt1 * paramInt2 > paramList.size()) {
				arrayOfBlogUserInfo = new BlogUserInfo[paramInt2
						- (paramInt1 * paramInt2 - paramList.size())];
			} else {
				arrayOfBlogUserInfo = new BlogUserInfo[paramInt2];
			}
			int i = 0;
			for (int j = (paramInt1 - 1) * paramInt2; (j < paramInt1
					* paramInt2)
					&& (j < paramList.size()); j++) {
				String str1 = (String) paramList.get(j);
				String str2 = localResourceComInfo.getLastname(str1);
				String str3 = "0";
				String str4 = localDepartmentComInfo
						.getDepartmentname(localResourceComInfo
								.getDepartmentID(str1));
				String str5 = localJobTitlesComInfo
						.getJobTitlesname(localResourceComInfo
								.getJobTitle(str1));
				String str6 = localSubCompanyComInfo
						.getSubCompanyname(localResourceComInfo
								.getSubCompanyID(str1));
				String str7 = localResourceComInfo.getMessagerUrls(str1);

				BlogUserInfo localBlogUserInfo = new BlogUserInfo();
				localBlogUserInfo.setUserid(str1);
				localBlogUserInfo.setDeptName(str4);
				localBlogUserInfo.setIsnew(str3);
				localBlogUserInfo.setJobtitle(str5);
				localBlogUserInfo.setUsername(str2);
				localBlogUserInfo.setSubName(str6);
				localBlogUserInfo.setImageUrl(str7);

				arrayOfBlogUserInfo[i] = localBlogUserInfo;
				i++;
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}

		return arrayOfBlogUserInfo;
	}

	public void addAttention(String paramString1, String paramString2) {
		BlogManager localBlogManager = new BlogManager();
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			String str1 = "0";
			String str2 = localResourceComInfo.getManagerID(paramString2);
			if (str2.equals(paramString1))
				str1 = "1";
			localBlogManager.addAttention(paramString1, paramString2, str1);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public void cancelAttention(String paramString1, String paramString2) {
		BlogManager localBlogManager = new BlogManager();
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			String str1 = "0";
			String str2 = localResourceComInfo.getManagerID(paramString2);
			if (str2.equals(paramString1))
				str1 = "1";
			localBlogManager.cancelAttention(paramString1, paramString2, str1);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public void requestAttention(String paramString1, String paramString2) {
		BlogManager localBlogManager = new BlogManager();
		try {
			ResourceComInfo localResourceComInfo = new ResourceComInfo();
			String str1 = "0";
			String str2 = localResourceComInfo.getManagers(paramString2);
			if (str2.indexOf("," + paramString1 + ",") != -1)
				str1 = "1";
			localBlogManager.requestAttention(paramString1, paramString2, str1);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public List getGroupList(String paramString) {
		BlogGroupService localBlogGroupService = new BlogGroupService();
		ArrayList localArrayList1 = localBlogGroupService.getGroupsById(Util
				.getIntValue(paramString));
		BlogDao localBlogDao = new BlogDao();
		ArrayList localArrayList2 = new ArrayList();

		HashMap localHashMap = new HashMap();
		localHashMap.put("id", "all");
		localHashMap.put("groupname", "全部关注");
		localHashMap.put("userid", paramString);
		localHashMap.put("count", Integer.valueOf(localBlogDao
				.getMyAttentionCount(paramString, "all")));
		localArrayList2.add(localHashMap);

		for (int i = 0; i < localArrayList1.size(); i++) {
			Map localMap = (Map) localArrayList1.get(i);
			String str = (String) localMap.get("id");
			localMap.put("count", Integer.valueOf(localBlogDao
					.getMyAttentionCount(paramString, str)));

			localArrayList2.add(localMap);
		}

		return localArrayList2;
	}

	public String addGroup(String paramString1, String paramString2) {
		BlogGroupService localBlogGroupService = new BlogGroupService();
		String str = localBlogGroupService.createGroup(
				Util.getIntValue(paramString1), paramString2);
		return str;
	}

	public void delGroup(String paramString1, String paramString2) {
		BlogGroupService localBlogGroupService = new BlogGroupService();
		localBlogGroupService.deleteGroup(Util.getIntValue(paramString1),
				Util.getIntValue(paramString2));
	}

	public void editGroup(String paramString1, String paramString2,
			String paramString3) {
		BlogGroupService localBlogGroupService = new BlogGroupService();
		localBlogGroupService.editGroupName(paramString3,
				Util.getIntValue(paramString2), Util.getIntValue(paramString1));
	}

	public Map<String, String> getBlogCount(String paramString) {
		BlogDao localBlogDao = new BlogDao();
		String str1 = "" + localBlogDao.getMyBlogCount(paramString);
		String str2 = "" + localBlogDao.getMyAttentionCount(paramString, "all");
		String str3 = "" + localBlogDao.getAttentionMe(paramString).size();

		HashMap localHashMap = new HashMap();
		localHashMap.put("blogCount", str1);
		localHashMap.put("myAttentionCount", str2);
		localHashMap.put("attentionMeCount", str3);

		return localHashMap;
	}

	public static Map blogHtmlFormate(String paramString) {
		if ((paramString == null) || (paramString.equals("")))
			return null;
		StringBuffer localStringBuffer = new StringBuffer();
		ArrayList localArrayList = new ArrayList();
		String str1 = "";
		try {
			BaseBean localBaseBean = new BaseBean();
			Parser localObject = new Parser();
			((Parser) localObject).setInputHTML(paramString);

			NodeIterator localNodeIterator = ((Parser) localObject).elements();
			while (localNodeIterator.hasMoreNodes()) {
				Node localNode = localNodeIterator.nextNode();
				localNode = htmlFilter(localNode, localArrayList);
				if (localNode != null) {
					str1 = str1 + localNode.toHtml();
				}
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}

		String str2 = htmlFilter(paramString);

		Object localObject = "";
		for (int i = 0; i < localArrayList.size(); i++) {
			localObject = (String) localObject + "," + localArrayList.get(i);
		}

		localObject = ((String) localObject).length() > 0 ? ((String) localObject)
				.substring(1) : "";
		HashMap localHashMap = new HashMap();

		localHashMap.put("htmlstr", HtmlUtil.translateMarkup(str1 + "<br/>"));
		localHashMap.put("textstr", str2);
		localHashMap.put("imageids", localObject);

		return localHashMap;
	}

	public static String htmlFilter(String paramString) {
		String str1 = "<script[^>]*?>[\\s\\S]*?<\\/script>";
		String str2 = "<style[^>]*?>[\\s\\S]*?<\\/style>";
		String str3 = "<[^>]+>";

		Pattern localPattern1 = Pattern.compile(str1, 2);
		Matcher localMatcher1 = localPattern1.matcher(paramString);
		paramString = localMatcher1.replaceAll("");

		Pattern localPattern2 = Pattern.compile(str2, 2);
		Matcher localMatcher2 = localPattern2.matcher(paramString);
		paramString = localMatcher2.replaceAll("");

		Pattern localPattern3 = Pattern.compile(str3, 2);
		Matcher localMatcher3 = localPattern3.matcher(paramString);
		paramString = localMatcher3.replaceAll("");

		return paramString;
	}

	private static Node htmlFilter(Node paramNode, List paramList) {
		String str1 = "";
		try {
			if ((paramNode instanceof TagNode)) {
				Tag localTag = (Tag) paramNode;
				String str2 = localTag.getTagName();
				Object localObject1;
				Object localObject2;
				if (str2.toLowerCase().equals("img")) {
					localObject1 = localTag.getAttribute("src");
					if (((String) localObject1)
							.indexOf("weaver/weaver.file.FileDownload?fileid=") > 0) {
						localObject2 = ((String) localObject1)
								.substring(((String) localObject1)
										.indexOf("fileid=") + 7);
						paramList.add(localObject2);
						localTag.setEmptyXmlTag(true);
						return null;
					}
				}

				if (paramNode.getChildren() != null) {
					localObject1 = paramNode.getChildren().elements();
					while (((NodeIterator) localObject1).hasMoreNodes()) {
						localObject2 = ((NodeIterator) localObject1).nextNode();
						if ((localObject2 instanceof TagNode)) {
							Node localNode = htmlFilter((Node) localObject2,
									paramList);
						}
					}
				}
				return paramNode;
			}
			return paramNode;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return paramNode;
	}

	public void removeCommentRemind(String paramString) {
		String str = "update blog_remind set status=1 where remindType=9 and remindid="
				+ paramString;
		RecordSet localRecordSet = new RecordSet();
		localRecordSet.execute(str);
	}
}
