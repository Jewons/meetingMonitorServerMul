package com.redcdn.commom.monitorcontrol.db.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.redcdn.commom.monitorcontrol.db.dao.BandWithAdaptiveDAO;
import com.redcdn.commom.monitorcontrol.db.entity.BandWithAdaptiveEntity;

public class BandWithAdaptiveDAOImpl extends BaseMeetingInfoDAO implements BandWithAdaptiveDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	//private String BANDWITHADAPTIVE_TAB = "MeetingKeyEventInfo_Table";
	private String BANDWITHADAPTIVE_TAB = "MeetingKeyEventInfo2016_Table";
	@Override
	public List<BandWithAdaptiveEntity> findByUserId(int meetingId, int micId,
			String userId, int pageSize, int currPage) {
		// TODO Auto-generated method stub
		//pageSize *= 2;
		List<BandWithAdaptiveEntity> list = new ArrayList<BandWithAdaptiveEntity>();
		try {
			DBCollection employee = db.getCollection(BANDWITHADAPTIVE_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("writer", 0);
			
			if(micId == 1 || micId ==2){
				dbo.put("micId", micId);
			}
			if (meetingId > 0) {
				dbo.put("meetingId", meetingId);
			}
			if (!"".equals(userId)) {
				if (Integer.parseInt(userId) > 0) {
					dbo.put("userId", userId);	
				}
			}
			
			DBObject orderBy = new BasicDBObject("reporttime", 1);
			//int skip = (currPage-1)*pageSize;
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
			//cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			//cur = employee.find();
			cur = employee.find(dbo).sort(orderBy);
			DBObject dbObject = cur.explain();
			System.out.println("explainB:"+dbObject);
			//int count = employee.find(dbo).count();
			list = dbCurToList(cur);
			cur.close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("检索 findByUserId：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return list;
	}
	
	@Override
	public List<BandWithAdaptiveEntity> findByRelayIp(int meetingId, int micId,
			String relayIp, int pageSize, int currPage) {
		// TODO Auto-generated method stub
		List<BandWithAdaptiveEntity> list = new ArrayList<BandWithAdaptiveEntity>();
		try {
			DBCollection employee = db.getCollection(BANDWITHADAPTIVE_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("writer", 1);
			
			if(micId == 1 || micId ==2){
				dbo.put("micId", micId);
			}
			if (meetingId > 0) {
				dbo.put("meetingId", meetingId);
			}
			if (!"".equals(relayIp)) {
				dbo.put("relayIp", relayIp);	
			}
			
			DBObject orderBy = new BasicDBObject("reporttime", 1);
			//int skip = (currPage-1)*pageSize;
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
			//cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			//cur = employee.find();
			cur = employee.find(dbo).sort(orderBy);
			DBObject dbObject = cur.explain();
			System.out.println("explainB:"+dbObject);
			//int count = employee.find(dbo).count();
			list = dbCurToList(cur);
			cur.close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("检索 findByUserId：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return list;
	}
	
	
	private List<BandWithAdaptiveEntity> dbCurToList(DBCursor cursor){
		List<BandWithAdaptiveEntity> list = new ArrayList<BandWithAdaptiveEntity>();
		BandWithAdaptiveEntity entity = null;
		while (cursor.hasNext()) {
			//test
			DBObject dbObject = cursor.next();
			//System.out.println("entity:"+ dbObject.toString());
			if (dbObject != null){
				try {
					entity = getBandWithAdaptiveEntity(dbObject);
				} catch (Exception e) {
					// TODO: handle exception
					logger.error(" dbCurToList：",e);
				}
				if(null != entity){
					list.add(entity);
				}
			}
	    }
		cursor.close();
		return list;
	}
	
	private BandWithAdaptiveEntity getBandWithAdaptiveEntity(DBObject dbObject) {
		BandWithAdaptiveEntity responseEntity = new BandWithAdaptiveEntity();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		responseEntity.setTimeStamp(df.format((Long.parseLong(dbObject.get("reporttime")==null?"0":dbObject.get("reporttime").toString()))*1000));
		responseEntity.setUserIp(dbObject.get("userIp")==null?"0":dbObject.get("userIp").toString());
		responseEntity.setUserId(dbObject.get("userId")==null?"0":dbObject.get("userId").toString());
		responseEntity.setMeetingId(Integer.parseInt(dbObject.get("meetingid")==null?"0":dbObject.get("meetingid").toString()));
		responseEntity.setMicId(Integer.parseInt(dbObject.get("micid")==null?"0":dbObject.get("micid").toString()));
		responseEntity.setDirectionType(Integer.parseInt(dbObject.get("directionType")==null?"0":dbObject.get("directionType").toString()));
		responseEntity.setRelayId(Integer.parseInt(dbObject.get("relayId")==null?"0":dbObject.get("relayId").toString()));
		responseEntity.setRelayIp(dbObject.get("relayIp")==null?"0":dbObject.get("relayIp").toString());
		responseEntity.setBandInfo(dbObject.get("keyevent")==null?"0":dbObject.get("keyevent").toString());
		return responseEntity;
	}
	
		

}
