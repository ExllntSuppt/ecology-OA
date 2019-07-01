package com.weaver.integration.util;

import com.weaver.upgrade.FunctionUpgradeUtil;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.system.License;


public class SapAuthorityUtil
  extends BaseBean
{
  public SapAuthorityUtil() {}
  
  public int isOpenSapFun()
  {
    int i = 0;
    int j = Util.getIntValue(new License().getCId());
    
    int k = FunctionUpgradeUtil.getMenuidkey();
    
    int m = FunctionUpgradeUtil.getMenustatuskey();
    RecordSet localRecordSet = new RecordSet();
    String str = "select count(*) as countNum from menucontrollist where type = 'top'   and menuid = '" + (10122 + j + k) + "'" + "   and isopen = '" + (10122 + j + m + 1) + "' ";
    


    localRecordSet.executeSql(str);
    writeLog("isOpenSql--------" + str);
    if (localRecordSet.next()) {
      i = Util.getIntValue(localRecordSet.getString("countNum"), 0);
    }
    return i;
  }
}