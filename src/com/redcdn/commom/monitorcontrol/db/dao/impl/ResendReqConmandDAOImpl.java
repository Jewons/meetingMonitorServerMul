package com.redcdn.commom.monitorcontrol.db.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.redcdn.commom.monitorcontrol.db.dao.ResendReqConmandDAO;
import com.redcdn.commom.monitorcontrol.db.entity.ResendReqConmandEntity;
import com.redcdn.commom.monitorcontrol.db.entity.view.ResendReqConmandResult;

public class ResendReqConmandDAOImpl extends BaseMeetingInfoDAO implements ResendReqConmandDAO {

	private Logger logger = Logger.getLogger(this.getClass());
	private String RESENDREQCONMAND_TAB = "ResendReqConmand _Table";
	@Override
	public ResendReqConmandResult findByTimeStamp(int meetingId, int micId,
			String speakerId, long startTime, long endTime, String senderId,
			String receiverId) {
		ResendReqConmandResult result = new ResendReqConmandResult();
		try {
			DBCollection employee = db.getCollection(RESENDREQCONMAND_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("recvtime", new BasicDBObject("$gte", startTime).append("$lt", endTime));
			
			if (!(speakerId == null||"".equals(speakerId))) {
				dbo.put("speakerid", speakerId);
			}
			if (meetingId > 0) {
				dbo.put("meetingid", meetingId);
			}
			
			if (micId == 1 || micId == 2) {
				dbo.put("micid", micId);
			}
			dbo.put("senderid", senderId);

			dbo.put("$or", new BasicDBObject[]{new BasicDBObject("receiverid",receiverId),new BasicDBObject("receiverid","0")});
			DBObject orderBy = new BasicDBObject("recvtime", -1);
			cur = employee.find(dbo).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListResendReqConmandEntities(dbCurToList(cur));
			result.setCount(count);
			cur.close();
			return result;
		} catch (Exception e) {
			logger.error("检索 findByTimeStamp：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}
	
	private List<ResendReqConmandEntity> dbCurToList(DBCursor cursor) {
		List<ResendReqConmandEntity> list = new ArrayList<ResendReqConmandEntity>();
		ResendReqConmandEntity entity = null;
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			System.out.println("entity:"+ dbObject.toString());
			if (dbObject != null) {
				try {
					entity = getResendReqConmandEntity(dbObject);
				} catch (Exception e) {
					logger.error(" dbCurToList：",e);
				}
				if (null != entity) {
					list.add(entity);
				}
				
			}	
		}
		cursor.close();
		return list;
	}

	private ResendReqConmandEntity getResendReqConmandEntity(DBObject dbObject) {
		ResendReqConmandEntity responseEntity = new ResendReqConmandEntity();
		responseEntity.setCheckPacketList(dbObject.get("checkPacketList")==null?"0":dbObject.get("checkPacketList").toString());
		responseEntity.setDataPacketList(dbObject.get("dataPacketList")==null?"0":dbObject.get("dataPacketList").toString());
		responseEntity.setDirection(Integer.parseInt(dbObject.get("direction")==null?"0":dbObject.get("direction").toString()));
		responseEntity.setFrameinnerrto(Integer.parseInt(dbObject.get("frameinnerrto")==null?"0":dbObject.get("frameinnerrto").toString()));
		responseEntity.setFrameList(dbObject.get("frameList")==null?"0":dbObject.get("frameList").toString());
		responseEntity.setFramemaxtimes(Integer.parseInt(dbObject.get("framemaxtimes")==null?"0":dbObject.get("framemaxtimes").toString()));
		responseEntity.setFramerto(Integer.parseInt(dbObject.get("framerto")==null?"0":dbObject.get("framerto").toString()));
		responseEntity.setFramertt(Integer.parseInt(dbObject.get("framertt")==null?"0":dbObject.get("framertt").toString()));
		responseEntity.setMeetingid(Integer.parseInt(dbObject.get("meetingid")==null?"0":dbObject.get("meetingid").toString()));	
		responseEntity.setMicid(Integer.parseInt(dbObject.get("micid")==null?"0":dbObject.get("micid").toString()));
		responseEntity.setPacketmaxrto(Integer.parseInt(dbObject.get("packetmaxrto")==null?"0":dbObject.get("packetmaxrto").toString()));
		responseEntity.setPacketmaxtimes(Integer.parseInt(dbObject.get("packetmaxtimes")==null?"0":dbObject.get("packetmaxtimes").toString()));
		responseEntity.setPacketrto(Integer.parseInt(dbObject.get("packetrto")==null?"0":dbObject.get("packetrto").toString()));
		responseEntity.setPacketrtt(Integer.parseInt(dbObject.get("packetrtt")==null?"0":dbObject.get("packetrtt").toString()));
		responseEntity.setReceiverid(dbObject.get("receiverid")==null?"0":dbObject.get("receiverid").toString());
		responseEntity.setRecvtime(Long.parseLong(dbObject.get("recvtime")==null?"0":dbObject.get("recvtime").toString()));
		responseEntity.setSenderid(dbObject.get("senderid")==null?"0":dbObject.get("senderid").toString());
		responseEntity.setSpeakerid(dbObject.get("speakerid")==null?"0":dbObject.get("speakerid").toString());
		return responseEntity;
	}
}
