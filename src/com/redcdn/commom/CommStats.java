package com.redcdn.commom;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mongodb.ServerAddress;
/**
 * 配置文件读取
 * @author Belie
 */
public class CommStats {
//	  public static final String flashmongohost;//数据库地址
//	  public static final String flashmongodatabase;//数据库名称
	  
	
//	  public static final String reportmongohost;//数据库地址
//	  public static final String reportmongodatabase;//数据库名称
	  
	  public static final String username;//数据库用户名
	  public static final String password;//数据库密码
	  
	  public static final Integer callnumdurtime;//最近通话用户数的最近时间定义(分钟)
	  
	  public static final Integer topnlastTime;//opn允许的最大时间，既top的数据最大范围是当前时间--往前如下配置时间之内（分钟）
	   
	  public static final String meetingMonitorMongoHost;
	  public static final String meetingMonitorMongoDataBase;
	  
	  public static final Integer pageSize;
	  
	  public static final String[] userList;
	  static
	    { 
		   
		   meetingMonitorMongoHost = ResourceBundle.getBundle("c3p0").getString("meetingMonitorMongoHost");
		   meetingMonitorMongoDataBase = ResourceBundle.getBundle("c3p0").getString("meetingMonitorMongoDataBase");
		   
		   username = ResourceBundle.getBundle("c3p0").getString("username");
		   password = ResourceBundle.getBundle("c3p0").getString("password");
		   callnumdurtime = Integer.parseInt(ResourceBundle.getBundle("config").getString("callnumdurtime"));
		   topnlastTime = Integer.parseInt(ResourceBundle.getBundle("config").getString("topnlastTime"));
		   pageSize = Integer.parseInt(ResourceBundle.getBundle("config").getString("pageSize"));
		   //String users = ResourceBundle.getBundle("config").getString("userList");
		   String users = AboutReadExcel.ReadExcelFile("user.xls");
		   userList = users.split(",");
	    }
	   public static List<ServerAddress> serverListCall(String mongohost){
		  List<ServerAddress> serverList = new ArrayList<ServerAddress>(); 
		  String []mogonurl =  mongohost.split(",");
		  if (mogonurl.length == 0) {
			ServerAddress serverAddress = null;
			String[] h = mongohost.split(":");
			try {
				serverAddress = new ServerAddress(h[0], Integer.parseInt(h[1]));
				serverList.add(serverAddress);
				return serverList;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}else {
			 for (String host : mogonurl) {
				  String [] h = host.split(":");
				  ServerAddress serverAddress = null;
				try {
					serverAddress = new ServerAddress(h[0],Integer.parseInt(h[1]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				  serverList.add(serverAddress);
			}
		}
		return serverList;
	   }
}
