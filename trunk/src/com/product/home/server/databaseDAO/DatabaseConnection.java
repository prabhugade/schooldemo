package com.product.home.server.databaseDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DatabaseConnection 
{
	public TimeZone tz=null;	
	public DatabaseConnection()
	{
		tz=TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+5:30"));
	}
	/**
	 * this method returns the database connection
	 * 
	 * @param driver
	 * @param url
	 * @param user
	 * @param pwd
	 * @return
	 */
	public static Connection getConnection(String driver,String url,String user,String pwd)
	{
		Connection con=null;
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
		}catch (Exception e) 
		{
			
		}
		return con;
	}
	/**
	 * 
	 * @param driver
	 * @param url
	 * @param user
	 * @param pwd
	 * @param loginurl
	 * @return
	 */
	public String getDBUrl(String driver,String url,String user,String pwd,String loginurl)
	{
		String resulturl=null;
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			con=getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				String sql=DatabaseQueries.getUrl;
				ps=con.prepareStatement(sql+" WHERE Status='1'");
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					if(loginurl.contains(rs.getString("Url")))
						resulturl=rs.getString("Database");
				}
			}else
			{
				System.out.println("connection failed"+url);
			}
			
		}catch (Exception e) 
		{
		}finally
		{
			
			try 
			{
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				}
			con=null;
		}
		return resulturl;
	}
	public String storeErrorLog(String driver,String url,String user,String pwd,String empid,String action,String desc,String status,String ipaddress)
	{
		String result="failed";
		Connection con=null;
		Statement st=null;
		String today=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance(tz).getTime());
		try
		{
			con=getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				st=con.createStatement();
				int i=st.executeUpdate("INSERT INTO `ERRORLOG`(`LOGINID`, `ACTION`, `DATETIME`, `DESC`, `STATUS`,`IPADDRESS`) VALUES ('"+empid.replaceAll("'", "''")+"','"+action.replaceAll("'", "''")+"','"+today.replaceAll("'", "''")+"','"+desc.replaceAll("'", "''")+"','"+status.replaceAll("'", "''")+"','"+ipaddress+"')");
				if(i>0)
					result="success";
				else
					result="failed";
			}else
			{
				//System.out.println(bid+" "+action+" "+desc);
			}
		}catch(Exception e)
		{
			System.out.println(action+"\nfailed in errorlog"+e);
		}finally
		{
			try
			{
				st.close();
				con.close();
			}catch(Exception e)
			{
				System.out.println(action+"failed in errorlog"+e);
			}
		}
		return result;
	}
}
