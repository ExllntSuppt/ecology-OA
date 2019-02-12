/*
 *  
 */
package DBstep;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class iDBManager2000 {
    public String ClassString = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    public String ConnectionString = "jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=DBDemo;User=dbdemo;Password=dbdemo";
    public String UserName = "dbdemo";
    public String PassWord = "dbdemo";
    public Connection Conn;
    public Statement Stmt;

    public boolean OpenConnection() {
        boolean mResult = true;
        try {
            Class.forName(this.ClassString);
            this.Conn = this.UserName == null && this.PassWord == null ? DriverManager.getConnection(this.ConnectionString) : DriverManager.getConnection(this.ConnectionString, this.UserName, this.PassWord);
            this.Stmt = this.Conn.createStatement();
            mResult = true;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            mResult = false;
        }
        return mResult;
    }

    public void CloseConnection() {
        try {
            this.Stmt.close();
            this.Conn.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public String GetDateTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(cal.getTime());
        return mDateTime;
    }

    public java.sql.Date GetDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String mDateTime = formatter.format(cal.getTime());
        return java.sql.Date.valueOf(mDateTime);
    }

    public int GetMaxID(String vTableName, String vFieldName) {
        int mResult = 0;
        String mSql = new String();
        mSql = String.valueOf(String.valueOf(new StringBuffer("select max(").append(vFieldName).append(")+1 as MaxID from ").append(vTableName)));
        if (this.OpenConnection()) {
            try {
                ResultSet result = this.ExecuteQuery(mSql);
                if (result.next()) {
                    mResult = result.getInt("MaxID");
                }
                result.close();
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
            this.CloseConnection();
        }
        return mResult;
    }

    public ResultSet ExecuteQuery(String SqlString) {
        ResultSet result = null;
        try {
            result = this.Stmt.executeQuery(SqlString);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }

    public int ExecuteUpdate(String SqlString) {
        int result = 0;
        try {
            result = this.Stmt.executeUpdate(SqlString);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }
}