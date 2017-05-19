package com.redcdn.commom.monitorcontrol.db.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.redcdn.commom.monitorcontrol.db.dao.FramePacketInfoDAO;
import com.redcdn.commom.monitorcontrol.db.entity.FramePacketInfoEntity;
import com.redcdn.commom.monitorcontrol.db.entity.view.FramePacketInfoResult;

public class FramePacketInfoDAOImpl extends BaseMeetingInfoDAO implements FramePacketInfoDAO{
	private Logger logger = Logger.getLogger(this.getClass());
	private String FRAMEPACKETINFO_TAB = "FramePacketInfo_Table";
	@Override
	public FramePacketInfoResult findByTimeStamp(int meetingId, int micId,
			String speakerId, long startTime, long endTime, String senderId,
			String receiverId) {
		// TODO Auto-generated method stub
		FramePacketInfoResult result = new FramePacketInfoResult();
		try {
			DBCollection employee = db.getCollection(FRAMEPACKETINFO_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("time", new BasicDBObject("$gte", startTime).append("$lt", endTime));
			//dbo.put("startTime", new BasicDBObject("$gte", startTime).append("$lt", endTime));
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
			DBObject orderBy = new BasicDBObject("time", -1);
//			cur = employee.find();
//			int count = employee.find().count();
			cur = employee.find(dbo).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListFramePacketInfoEntities(dbCurToList(cur));
			cur.close();
			result.setCount(count);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("检索 findByTimeStamp：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}
	
	private List<FramePacketInfoEntity> dbCurToList(DBCursor cursor) {
		List<FramePacketInfoEntity> list = new ArrayList<FramePacketInfoEntity>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			//System.out.println("entity:"+ dbObject.toString());
			if (dbObject != null) {
				FramePacketInfoEntity entity = getFramePacketInfoEntity(dbObject);
				list.add(entity);
			}	
		}
		cursor.close();
		return list;
	}

	private FramePacketInfoEntity getFramePacketInfoEntity(DBObject dbObject) {
		// TODO Auto-generated method stub
		FramePacketInfoEntity responseEntity = new FramePacketInfoEntity();
		responseEntity.setDirection(Integer.parseInt(dbObject.get("direction")==null?"0":dbObject.get("direction").toString()));
		responseEntity.setFecgroupid(Integer.parseInt(dbObject.get("fecgroupid")==null?"0":dbObject.get("fecgroupid").toString()));
		responseEntity.setFecgroupinnerid(Integer.parseInt(dbObject.get("fecgroupinnerid")==null?"0":dbObject.get("fecgroupinnerid").toString()));
		responseEntity.setFecrateFEC(dbObject.get("fecrate")==null?"0":dbObject.get("fecrate").toString());
		responseEntity.setFrameid(Integer.parseInt(dbObject.get("frameid")==null?"0":dbObject.get("frameid").toString()));
		responseEntity.setIndex(Integer.parseInt(dbObject.get("index")==null?"0":dbObject.get("index").toString()));
		responseEntity.setPackettype(dbObject.get("packettype")==null?"0":dbObject.get("packettype").toString());
		responseEntity.setReceiverid(dbObject.get("receiverid")==null?"0":dbObject.get("receiverid").toString());
		responseEntity.setMeetingid(Integer.parseInt(dbObject.get("meetingid")==null?"0":dbObject.get("meetingid").toString()));	
		responseEntity.setMicid(Integer.parseInt(dbObject.get("micid")==null?"0":dbObject.get("micid").toString()));
		responseEntity.setRecvtime(Long.parseLong(dbObject.get("recvtime")==null?"0":dbObject.get("recvtime").toString()));
		responseEntity.setSenderid(dbObject.get("senderid")==null?"0":dbObject.get("senderid").toString());
		responseEntity.setSpeakerid(dbObject.get("speakerid")==null?"0":dbObject.get("speakerid").toString());
		responseEntity.setTime(Long.parseLong(dbObject.get("time")==null?"0":dbObject.get("time").toString()));
		return responseEntity;
	}
	
}
