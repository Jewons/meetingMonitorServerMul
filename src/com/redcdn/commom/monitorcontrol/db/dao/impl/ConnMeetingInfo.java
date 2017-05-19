package com.redcdn.commom.monitorcontrol.db.dao.impl;


import java.util.List;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.redcdn.commom.CommStats;

public class ConnMeetingInfo {
	static  List<ServerAddress> reportDBUrlList = CommStats.serverListCall(CommStats.meetingMonitorMongoHost);
	private static ConnMeetingInfo conn =null;
	private ConnMeetingInfo(){}
//	private QueryQosStatic
	public static ConnMeetingInfo getInstace(){
		if(conn == null){
			conn =  new ConnMeetingInfo();
		}
		return conn;
	}
	private static Mongo mongo = null;
	public  Mongo getMongo() {
		try {
			if(mongo == null){
				mongo = new Mongo(reportDBUrlList);
			}
			return mongo;
		} catch (Exception e) {
			if(mongo != null){
				mongo.close();
			}
			e.printStackTrace();
		}
		return null;
	}

	public  DB getDB(Mongo mongo) {
		try {
		DB db = mongo.getDB(CommStats.meetingMonitorMongoDataBase);
			/*数据库用户名密码，因为mongo集群，用密码影响性能，所以暂时去掉*/
			// db.authenticate(CommStats.username,
			// CommStats.password.toCharArray());
			return db;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void destory(Mongo mongo,DB db){
		/*if(mongo != null){
			mongo.close();
		}*/
		if (db != null) {
			db.requestDone();
		}
		db = null;
	}
}
