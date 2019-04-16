/*
 *  
 * 
 *  
 *  weaver.conn.ConnectionMysql
 *  weaver.conn.RecordSet
 *  weaver.general.BaseBean
 *  weaver.general.GCONST
 */
package weaver.bbs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import weaver.conn.ConnectionMysql;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.GCONST;

public class UserOAToBBS
extends BaseBean {
    private Connection conns;
    private Statement statement;
    private ResultSet resultSet;
    private String usertalbe = "";

    public UserOAToBBS() {
        this.usertalbe = this.getPropValue(GCONST.getConfigFile(), "ecologybbs.tables") + "users";
    }

    public void changBBSUser(String string, String string2) {
        block13 : {
            String string3 = "";
            int n = 0;
            string2 = string2.toLowerCase();
            try {
                ConnectionMysql connectionMysql = ConnectionMysql.getInstance();
                this.conns = connectionMysql.getConnection();
                this.statement = this.conns.createStatement();
                string3 = "select * from " + this.usertalbe + " where username='" + string + "' ";
                this.resultSet = this.statement.executeQuery(string3);
                if (this.resultSet.next()) {
                    this.statement.executeUpdate("update " + this.usertalbe + " set user_password='" + string2 + "' where username='" + string + "' ");
                    break block13;
                }
                this.resultSet = this.statement.executeQuery("select max(user_id) from " + this.usertalbe);
                if (this.resultSet.next()) {
                    n = this.resultSet.getInt(1) + 1;
                }
                this.statement.executeUpdate("insert into " + this.usertalbe + " (user_id,username,user_password,user_regdate,user_timezone,user_style,user_dateformat) SELECT " + n + ",'" + string + "','" + string2 + "',user_regdate,8,user_style,user_dateformat FROM " + this.usertalbe + " WHERE user_level=1 ");
            }
            catch (Exception var5_7) {
                this.writeLog((Object)var5_7);
            }
            finally {
                try {
                    this.resultSet.close();
                    this.statement.close();
                    this.conns.close();
                }
                catch (Exception var5_8) {}
            }
        }
    }

    public boolean initBBSUser() throws Exception {
        boolean bl;
        int n = 10;
        bl = true;
        try {
            RecordSet recordSet = new RecordSet();
            recordSet.execute("select lastname,password from hrmresource where (loginid is not null) and status in (0,1,2,3,5) ");
            ConnectionMysql connectionMysql = ConnectionMysql.getInstance();
            this.conns = connectionMysql.getConnection();
            this.statement = this.conns.createStatement();
            this.statement.executeUpdate("delete from " + this.usertalbe + " where user_level!=1 and user_id!=-1");
            while (recordSet.next()) {
                String string = recordSet.getString(1);
                String string2 = recordSet.getString(2);
                string2 = string2.toLowerCase();
                String string3 = "insert into " + this.usertalbe + " (user_id,username,user_password,user_regdate,user_timezone,user_style,user_dateformat) SELECT " + n + ",'" + string + "','" + string2 + "',user_regdate,8,user_style,user_dateformat FROM " + this.usertalbe + " WHERE user_level=1";
                this.statement = this.conns.createStatement();
                this.statement.executeUpdate(string3);
                ++n;
            }
        }
        catch (Exception var3_5) {
            this.writeLog((Object)var3_5);
            bl = false;
        }
        finally {
            try {
                this.statement.close();
                this.conns.close();
            }
            catch (Exception var3_6) {}
        }
        return bl;
    }
}