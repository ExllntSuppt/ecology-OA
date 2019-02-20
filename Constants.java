package weaver;

public class Constants
{
  public static final int Words_Short = 15;
  public static final int Page_Size = 20;
  public static final int Hrm_All_Member = 1;
  public static final int Hrm_SubCompany = 2;
  public static final int Hrm_Department = 3;
  public static final int Hrm_Role = 4;
  public static final int Hrm_Station = 5;
  public static final int Hrm_Resource = 6;
  public static final String WorkPlan_Type_All = "";
  public static final String WorkPlan_Type_Plan = "0";
  public static final String WorkPlan_Type_ConferenceCalendar = "1";
  public static final String WorkPlan_Type_ProjectCalendar = "2";
  public static final String WorkPlan_Type_CustomerContact = "3";
  public static final String WorkPlan_Type_PersonalScratchpad = "4";
  public static final String WorkPlan_Type_PurposePlan = "6";
  public static final String WorkPlan_Status_All = "";
  public static final String WorkPlan_Status_Unfinished = "0";
  public static final String WorkPlan_Status_Finished = "1";
  public static final String WorkPlan_Status_Archived = "2";
  public static final String WorkPlan_StartTime = "09:00";
  public static final String WorkPlan_EndTime = "17:00";
  public static final String WorkPlan_Urgent_Normal = "1";
  public static final String WorkPlan_Urgent_Important = "2";
  public static final String WorkPlan_Urgent_Urgent = "3";
  public static final String WorkPlan_Remind_No = "1";
  public static final String WorkPlan_Remind_Message = "2";
  public static final String WorkPlan_Remind_Mail = "3";
  public static final String WorkPlan_Personal_Share_By_User = "0";
  public static final String WorkPlan_Personal_Share_By_Manager = "1";
  public static final String WorkPlan_Type_Query_By_Menu = " WHERE (workPlanTypeId = 0 OR workPlanTypeId >= 7) AND available = '1' ORDER BY displayOrder ASC";
  
  public Constants() {}
}