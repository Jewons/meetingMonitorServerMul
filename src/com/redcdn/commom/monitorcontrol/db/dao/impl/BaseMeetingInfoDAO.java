package com.redcdn.commom.monitorcontrol.db.dao.impl;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class BaseMeetingInfoDAO {

	protected ConnMeetingInfo conn = ConnMeetingInfo.getInstace();
	protected Mongo mongo = null;
	protected DB db = null;
//	protected String queryQosStaticTabname = "";
	public BaseMeetingInfoDAO(){
		mongo = conn.getMongo();
		db = conn.getDB(mongo);
	}
}
