package com.redcdn.commom.monitorcontrol.db.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.redcdn.commom.monitorcontrol.db.dao.MeetingInfoDAO;
import com.redcdn.commom.monitorcontrol.db.entity.MeetingInfoEntity;
import com.redcdn.commom.monitorcontrol.db.entity.view.MeetingInfoResult;


public class MeetingInfoDAOImpl extends BaseMeetingInfoDAO implements MeetingInfoDAO{

	private Logger logger = Logger.getLogger(this.getClass());
	//private String MEETINGINFO_TAB = "MeetingInfo_tab";
	private String MEETINGINFO_TAB = "MeetingInfo_Table";
	/**
	 * 获取查询对象
	 * @param dbObject
	 * @return
	 */
	private MeetingInfoEntity getMeetingInfoEntity(DBObject dbObject){
		MeetingInfoEntity responseEntity = new MeetingInfoEntity();
		responseEntity.set_id(dbObject.get("_id").toString());
//		responseEntity.setAudioLossPackRatioAfterFEC(Integer.parseInt(dbObject.get("audioLossPackRatioAfterFEC")==null?"0":dbObject.get("audioLossPackRatioAfterFEC").toString()));
//		responseEntity.setAudioLossPackRatioBeforeFEC(Integer.parseInt(dbObject.get("audioLossPackRatioBeforeFEC")==null?"0":dbObject.get("audioLossPackRatioBeforeFEC").toString()));
//		responseEntity.setCpuRate(Integer.parseInt(dbObject.get("cpuRate")==null?"0":dbObject.get("cpuRate").toString()));
//		responseEntity.setDelay(Integer.parseInt(dbObject.get("delay")==null?"0":dbObject.get("delay").toString()));
//		responseEntity.setDirectionType(Integer.parseInt(dbObject.get("directionType")==null?"0":dbObject.get("directionType").toString()));
//		responseEntity.setFouEmpty(Integer.parseInt(dbObject.get("fouEmpty")==null?"0":dbObject.get("fouEmpty").toString()));
//		responseEntity.setFrameRate(Integer.parseInt(dbObject.get("frameRate")==null?"0":dbObject.get("frameRate").toString()));
//		responseEntity.setLossPackRatioAfterFEC(Integer.parseInt(dbObject.get("lossPackRatioAfterFEC")==null?"0":dbObject.get("lossPackRatioAfterFEC").toString()));
//		responseEntity.setLossPackRatioBeforeFEC(Integer.parseInt(dbObject.get("lossPackRatioBeforeFEC")==null?"0":dbObject.get("lossPackRatioBeforeFEC").toString()));
		responseEntity.setMeetingId(Integer.parseInt(dbObject.get("meetingid")==null?"0":dbObject.get("meetingid").toString()));
		responseEntity.setMicId(Integer.parseInt(dbObject.get("micid")==null?"0":dbObject.get("micid").toString()));
//		responseEntity.setOneEmpty(Integer.parseInt(dbObject.get("oneEmpty")==null?"0":dbObject.get("oneEmpty").toString()));
//		responseEntity.setRelayId(Integer.parseInt(dbObject.get("relayId")==null?"0":dbObject.get("relayId").toString()));
//		responseEntity.setRelayIp(dbObject.get("relayIp")==null?"0":dbObject.get("relayIp").toString());
//		responseEntity.setTenEmpty(Integer.parseInt(dbObject.get("tenEmpty")==null?"0":dbObject.get("tenEmpty").toString()));
//		responseEntity.setThrEmpty(Integer.parseInt(dbObject.get("thrEmpty")==null?"0":dbObject.get("thrEmpty").toString()));
//		responseEntity.setTimeStamp(Long.parseLong(dbObject.get("timeStamp")==null?"0":dbObject.get("timeStamp").toString()));
//		responseEntity.setTwoEmpty(Integer.parseInt(dbObject.get("twoEmpty")==null?"0":dbObject.get("twoEmpty").toString()));
//		responseEntity.setUserIp(dbObject.get("userIp")==null?"0":dbObject.get("userIp").toString());
//		responseEntity.setUserId(dbObject.get("userId")==null?"0":dbObject.get("userId").toString());
//		responseEntity.setFrameInfo(dbObject.get("frames")==null?"0":dbObject.get("frames").toString());
		responseEntity.setFrameVideoInfo(dbObject.get("framesvideo")==null?"0":dbObject.get("framesvideo").toString());
		responseEntity.setFrameAudioInfo(dbObject.get("framesaudio")==null?"0":dbObject.get("framesaudio").toString());
		//System.out.println("FrameAudioInfo:"+responseEntity.getFrameAudioInfo());
		responseEntity.setSpeakerId(dbObject.get("speakerid")==null?"0":dbObject.get("speakerid").toString());
		responseEntity.setSenderId(dbObject.get("senderid")==null?"0":dbObject.get("senderid").toString());
		responseEntity.setReceiverId(dbObject.get("receiverid")==null?"0":dbObject.get("receiverid").toString());
		return responseEntity;
	}
	
	private List<MeetingInfoEntity> dbCurToList(DBCursor cursor) {
		List<MeetingInfoEntity> list = new ArrayList<MeetingInfoEntity>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			System.out.println("entity:"+ dbObject.toString());
			if (dbObject != null) {
				MeetingInfoEntity entity = getMeetingInfoEntity(dbObject);
				System.out.println("entity1:"+entity.toString());
				list.add(entity);
			}	
		}
		cursor.close();
		return list;
	}
	@Override
	public MeetingInfoResult findByid(long startTime, long endTime,
			String userId, int meetingId, int relayId,
			int directionType, int pageSize, int currPage) {
		pageSize *= 2;
		MeetingInfoResult result = new MeetingInfoResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFO_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("timeStamp", new BasicDBObject("$gte", startTime).append("$lt", endTime));
			if(directionType == 0 || directionType == 1){
				dbo.put("directionType", directionType);
			}
			if (!(userId == null||"".equals(userId))) {
				dbo.put("userId", userId);
			}
			if (meetingId > 0) {
				dbo.put("meetingId", meetingId);
			}
			if (relayId > 0) {
				dbo.put("relayId", relayId);	
			}
			DBObject orderBy = new BasicDBObject("timeStamp", -1);
			int skip = (currPage-1)*pageSize;
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoEntities(dbCurToList(cur));
			cur.close();
			result.setCount(count);
			return result;
		} catch (Exception e) {
			logger.error("检索 findbyid：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}

	@Override
	public MeetingInfoResult findBySteam(long startTime, long endTime,
			String videoSignBefore, float videoLossBeforeMin,
			float videoLossBeforeMax, String videoSignAfter,
			float videoLossAfterMin, float videoLossAfterMax,
			String audioSignBefore, float audioLossBeforeMin,
			float audioLossBeforeMax, String audioSignAfter,
			float audioLossAfterMin, float audioLossAfterMax,
			int directionType, int pageSize, int currPage) {
		pageSize *= 2;
		MeetingInfoResult result = new MeetingInfoResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFO_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			dbo.put("timeStamp", new BasicDBObject("$gte", startTime).append("$lt", endTime));
			if(directionType == 0 || directionType == 1){
				dbo.put("directionType", directionType);
			}
			if(!(videoSignBefore == null ||"".equals(videoSignBefore))){
				if ("<".equals(videoSignBefore)) {
					dbo.put("lossPackRatioBeforeFEC", new BasicDBObject("$lt",videoLossBeforeMin));
				}else if (">".equals(videoSignBefore)) {
					dbo.put("lossPackRatioBeforeFEC", new BasicDBObject("$gt",videoLossBeforeMax));
				}else if("between".equals(videoSignBefore)){
					dbo.put("lossPackRatioBeforeFEC", new BasicDBObject("$gte",videoLossBeforeMin).append("$lte", videoLossBeforeMax));
				}
			}
			if (!(videoSignAfter == null || "".equals(videoSignAfter))) {
				if ("<".equals(videoSignAfter)) {
					dbo.put("lossPackRatioAfterFEC", new BasicDBObject("$lt",videoLossAfterMin));
				}else if (">".equals(videoSignAfter)) {
					dbo.put("lossPackRatioAfterFEC", new BasicDBObject("$gt",videoLossAfterMax));
				}else if("between".equals(videoSignAfter)){
					dbo.put("lossPackRatioAfterFEC", new BasicDBObject("$gte",videoLossAfterMin).append("$lte", videoLossAfterMax));
				}
			}
			if (!(audioSignBefore == null || "".equals(audioSignBefore))) {
				if ("<".equals(audioSignBefore)) {
					dbo.put("audioLossPackRatioBeforeFEC", new BasicDBObject("$lt",audioLossBeforeMin));
				}else if (">".equals(audioSignBefore)) {
					dbo.put("audioLossPackRatioBeforeFEC", new BasicDBObject("$gt",audioLossBeforeMax));
				}else if("between".equals(audioSignBefore)){
					dbo.put("audioLossPackRatioBeforeFEC", new BasicDBObject("$gte",audioLossBeforeMin).append("$lte", audioLossBeforeMax));
				}
			}
			if (!(audioSignAfter == null || "".equals(audioSignAfter))) {
				if ("<".equals(audioSignAfter)) {
					dbo.put("audioLossPackRatioAfterFEC", new BasicDBObject("$lt",audioLossAfterMin));
				}else if (">".equals(audioSignAfter)) {
					dbo.put("audioLossPackRatioAfterFEC", new BasicDBObject("$gt",audioLossAfterMax));
				}else if("between".equals(audioSignAfter)){
					dbo.put("audioLossPackRatioAfterFEC", new BasicDBObject("$gte",audioLossAfterMin).append("$lte", audioLossAfterMax));
				}
			}
			DBObject orderBy = new BasicDBObject("timeStamp", -1);
			int skip = (currPage-1)*pageSize;
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoEntities(dbCurToList(cur));
			cur.close();
			result.setCount(count);
			return result;
		}
			catch (Exception e) {
				logger.error("检索 findbyStream：",e);
			}finally{
				conn.destory(mongo, db);
			}
		return result;
	}

	@Override
	public MeetingInfoResult findByQuality(long startTime, long endTime,
			String cpuRateSign, int cpuRateMin, int cpuRateMax,
			String frameRateSign, int frameRateMin, int frameRaterMax,
			String delaySign, int delayMin, int delayMax, int directionType,
			int pageSize, int currPage) {
		pageSize *= 2;
		MeetingInfoResult result = new MeetingInfoResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFO_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			dbo.put("timeStamp", new BasicDBObject("$gte", startTime).append("$lt", endTime));
			if(directionType == 0 || directionType == 1){
				dbo.put("directionType", directionType);
			}
			
			if(!(cpuRateSign == null ||"".equals(cpuRateSign))){
				if ("<".equals(cpuRateSign)) {
					dbo.put("cpuRate", new BasicDBObject("$lt",cpuRateMin));
				}else if (">".equals(cpuRateSign)) {
					dbo.put("cpuRate", new BasicDBObject("$gt",cpuRateMax));
				}else if("between".equals(cpuRateSign)){
					dbo.put("cpuRate", new BasicDBObject("$gte",cpuRateMin).append("$lte", cpuRateMax));
				}
			}
			if (!(frameRateSign == null || "".equals(frameRateSign))) {
				if ("<".equals(frameRateSign)) {
					dbo.put("frameRate", new BasicDBObject("$lt",frameRateMin));
				}else if (">".equals(frameRateSign)) {
					dbo.put("frameRate", new BasicDBObject("$gt",frameRaterMax));
				}else if("between".equals(frameRateSign)){
					dbo.put("frameRate", new BasicDBObject("$gte",frameRateMin).append("$lte", frameRaterMax));
				}
			}
			if (!(delaySign == null || "".equals(delaySign))) {
				if ("<".equals(delaySign)) {
					dbo.put("delay", new BasicDBObject("$lt",delayMin));
				}else if (">".equals(delaySign)) {
					dbo.put("delay", new BasicDBObject("$gt",delayMax));
				}else if("between".equals(delaySign)){
					dbo.put("delay", new BasicDBObject("$gte",delayMin).append("$lte", delayMax));
				}
			}
			
			DBObject orderBy = new BasicDBObject("timeStamp", -1);
			int skip = (currPage-1)*pageSize;
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoEntities(dbCurToList(cur));
			cur.close();
			result.setCount(count);
			return result;
		}
			catch (Exception e) {
				logger.error("检索 findByQuality：",e);
			}finally{
				conn.destory(mongo, db);
			}
		return result;
	}

	@Override
	public MeetingInfoResult findByEmptyPackage(long startTime, long endTime,
			String oneSign, int oneMin, int oneMax, String twoSign, int twoMin,
			int twoMax, String thrSign, int thrMin, int thrMax, String fouSign,
			int fouMin, int fouMax, String tenSign, int tenMin, int tenMax, int directionType,
			int pageSize, int currPage) {
		pageSize *= 2;
		MeetingInfoResult result = new MeetingInfoResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFO_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			dbo.put("timeStamp", new BasicDBObject("$gte", startTime).append("$lt", endTime));
			if(directionType == 0 || directionType == 1){
				dbo.put("directionType", directionType);
			}
			if(!(oneSign == null ||"".equals(oneSign))){
				if ("<".equals(oneSign)) {
					dbo.put("oneEmpty", new BasicDBObject("$lt",oneMin));
				}else if (">".equals(oneSign)) {
					dbo.put("oneEmpty", new BasicDBObject("$gt",oneMax));
				}else if("between".equals(oneSign)){
					dbo.put("oneEmpty", new BasicDBObject("$gte",oneMin).append("$lte", oneMax));
				}
			}
			if (!(twoSign == null || "".equals(twoSign))) {
				if ("<".equals(twoSign)) {
					dbo.put("twoEmpty", new BasicDBObject("$lt",twoMin));
				}else if (">".equals(twoSign)) {
					dbo.put("twoEmpty", new BasicDBObject("$gt",twoMax));
				}else if("between".equals(twoSign)){
					dbo.put("twoEmpty", new BasicDBObject("$gte",twoMin).append("$lte", twoMax));
				}
			}
			if (!(fouSign == null || "".equals(fouSign))) {
				if ("<".equals(fouSign)) {
					dbo.put("fouEmpty", new BasicDBObject("$lt",fouMin));
				}else if (">".equals(fouSign)) {
					dbo.put("fouEmpty", new BasicDBObject("$gt",fouMax));
				}else if("between".equals(fouSign)){
					dbo.put("fouEmpty", new BasicDBObject("$gte",fouMin).append("$lte", fouMax));
				}
			}
			
			if (!(thrSign == null || "".equals(thrSign))) {
				if ("<".equals(thrSign)) {
					dbo.put("thrEmpty", new BasicDBObject("$lt",thrMin));
				}else if (">".equals(thrSign)) {
					dbo.put("thrEmpty", new BasicDBObject("$gt",thrMax));
				}else if("between".equals(thrSign)){
					dbo.put("thrEmpty", new BasicDBObject("$gte",thrMin).append("$lte", thrMax));
				}
			}
			
			if (!(tenSign == null || "".equals(tenSign))) {
				if ("<".equals(tenSign)) {
					dbo.put("tenEmpty", new BasicDBObject("$lt",tenMin));
				}else if (">".equals(tenSign)) {
					dbo.put("tenEmpty", new BasicDBObject("$gt",tenMax));
				}else if("between".equals(tenSign)){
					dbo.put("tenEmpty", new BasicDBObject("$gte",tenMin).append("$lte", tenMax));
				}
			}
			DBObject orderBy = new BasicDBObject("timeStamp", -1);
			int skip = (currPage-1)*pageSize;
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoEntities(dbCurToList(cur));
			cur.close();
			result.setCount(count);
			return result;
		}
			catch (Exception e) {
				logger.error("检索 findByQuality：",e);
			}finally{
				conn.destory(mongo, db);
			}
		return result;
	}

	@Override
	public MeetingInfoResult findByAll(long startTime, long endTime,
			String userId, int meetingId, int relayId, String videoSignBefore,
			float videoLossBeforeMin, float videoLossBeforeMax,
			String videoSignAfter, float videoLossAfterMin,
			float videoLossAfterMax, String audioSignBefore,
			float audioLossBeforeMin, float audioLossBeforeMax,
			String audioSignAfter, float audioLossAfterMin,
			float audioLossAfterMax, String cpuRateSign, int cpuRateMin,
			int cpuRateMax, String frameRateSign, int frameRateMin,
			int frameRaterMax, String delaySign, int delayMin, int delayMax,
			String oneSign, int oneMin, int oneMax, String twoSign, int twoMin,
			int twoMax, String thrSign, int thrMin, int thrMax, String fouSign,
			int fouMin, int fouMax, String tenSign, int tenMin, int tenMax,
			int directionType, int pageSize, int currPage) {
		pageSize *= 2;
		MeetingInfoResult result = new MeetingInfoResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFO_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			dbo.put("timeStamp", new BasicDBObject("$gte", startTime).append("$lt", endTime));
			if(directionType == 0 || directionType == 1){
				dbo.put("directionType", directionType);
			}
		
			//标识
			if (!(userId == null||"".equals(userId))) {
				dbo.put("userId", userId);
			}
			if (meetingId > 0) {
				dbo.put("meetingId", meetingId);
			}
			if (relayId > 0) {
				dbo.put("relayId", relayId);	
			}
			
			//流统计
			if(!(videoSignBefore == null ||"".equals(videoSignBefore))){
				if ("<".equals(videoSignBefore)) {
					dbo.put("lossPackRatioBeforeFEC", new BasicDBObject("$lt",videoLossBeforeMin));
				}else if (">".equals(videoSignBefore)) {
					dbo.put("lossPackRatioBeforeFEC", new BasicDBObject("$gt",videoLossBeforeMax));
				}else if("between".equals(videoSignBefore)){
					dbo.put("lossPackRatioBeforeFEC", new BasicDBObject("$gte",videoLossBeforeMin).append("$lte", videoLossBeforeMax));
				}
			}
			if (!(videoSignAfter == null || "".equals(videoSignAfter))) {
				if ("<".equals(videoSignAfter)) {
					dbo.put("lossPackRatioAfterFEC", new BasicDBObject("$lt",videoLossAfterMin));
				}else if (">".equals(videoSignAfter)) {
					dbo.put("lossPackRatioAfterFEC", new BasicDBObject("$gt",videoLossAfterMax));
				}else if("between".equals(videoSignAfter)){
					dbo.put("lossPackRatioAfterFEC", new BasicDBObject("$gte",videoLossAfterMin).append("$lte", videoLossAfterMax));
				}
			}
			if (!(audioSignBefore == null || "".equals(audioSignBefore))) {
				if ("<".equals(audioSignBefore)) {
					dbo.put("audioLossPackRatioBeforeFEC", new BasicDBObject("$lt",audioLossBeforeMin));
				}else if (">".equals(audioSignBefore)) {
					dbo.put("audioLossPackRatioBeforeFEC", new BasicDBObject("$gt",audioLossBeforeMax));
				}else if("between".equals(audioSignBefore)){
					dbo.put("audioLossPackRatioBeforeFEC", new BasicDBObject("$gte",audioLossBeforeMin).append("$lte", audioLossBeforeMax));
				}
			}
			if (!(audioSignAfter == null || "".equals(audioSignAfter))) {
				if ("<".equals(audioSignAfter)) {
					dbo.put("audioLossPackRatioAfterFEC", new BasicDBObject("$lt",audioLossAfterMin));
				}else if (">".equals(audioSignAfter)) {
					dbo.put("audioLossPackRatioAfterFEC", new BasicDBObject("$gt",audioLossAfterMax));
				}else if("between".equals(audioSignAfter)){
					dbo.put("audioLossPackRatioAfterFEC", new BasicDBObject("$gte",audioLossAfterMin).append("$lte", audioLossAfterMax));
				}
			}
			
			//质量
			if(!(cpuRateSign == null ||"".equals(cpuRateSign))){
				if ("<".equals(cpuRateSign)) {
					dbo.put("cpuRate", new BasicDBObject("$lt",cpuRateMin));
				}else if (">".equals(cpuRateSign)) {
					dbo.put("cpuRate", new BasicDBObject("$gt",cpuRateMax));
				}else if("between".equals(cpuRateSign)){
					dbo.put("cpuRate", new BasicDBObject("$gte",cpuRateMin).append("$lte", cpuRateMax));
				}
			}
			if (!(frameRateSign == null || "".equals(frameRateSign))) {
				if ("<".equals(frameRateSign)) {
					dbo.put("frameRate", new BasicDBObject("$lt",frameRateMin));
				}else if (">".equals(frameRateSign)) {
					dbo.put("frameRate", new BasicDBObject("$gt",frameRaterMax));
				}else if("between".equals(frameRateSign)){
					dbo.put("frameRate", new BasicDBObject("$gte",frameRateMin).append("$lte", frameRaterMax));
				}
			}
			if (!(delaySign == null || "".equals(delaySign))) {
				if ("<".equals(delaySign)) {
					dbo.put("delay", new BasicDBObject("$lt",delayMin));
				}else if (">".equals(delaySign)) {
					dbo.put("delay", new BasicDBObject("$gt",delayMax));
				}else if("between".equals(delaySign)){
					dbo.put("delay", new BasicDBObject("$gte",delayMin).append("$lte", delayMax));
				}
			}
			
			//空心包
			if(!(oneSign == null ||"".equals(oneSign))){
				if ("<".equals(oneSign)) {
					dbo.put("oneEmpty", new BasicDBObject("$lt",oneMin));
				}else if (">".equals(oneSign)) {
					dbo.put("oneEmpty", new BasicDBObject("$gt",oneMax));
				}else if("between".equals(oneSign)){
					dbo.put("oneEmpty", new BasicDBObject("$gte",oneMin).append("$lte", oneMax));
				}
			}
			if (!(twoSign == null || "".equals(twoSign))) {
				if ("<".equals(twoSign)) {
					dbo.put("twoEmpty", new BasicDBObject("$lt",twoMin));
				}else if (">".equals(twoSign)) {
					dbo.put("twoEmpty", new BasicDBObject("$gt",twoMax));
				}else if("between".equals(twoSign)){
					dbo.put("twoEmpty", new BasicDBObject("$gte",twoMin).append("$lte", twoMax));
				}
			}
			if (!(fouSign == null || "".equals(fouSign))) {
				if ("<".equals(fouSign)) {
					dbo.put("fouEmpty", new BasicDBObject("$lt",fouMin));
				}else if (">".equals(fouSign)) {
					dbo.put("fouEmpty", new BasicDBObject("$gt",fouMax));
				}else if("between".equals(fouSign)){
					dbo.put("fouEmpty", new BasicDBObject("$gte",fouMin).append("$lte", fouMax));
				}
			}
			
			if (!(thrSign == null || "".equals(thrSign))) {
				if ("<".equals(thrSign)) {
					dbo.put("thrEmpty", new BasicDBObject("$lt",thrMin));
				}else if (">".equals(thrSign)) {
					dbo.put("thrEmpty", new BasicDBObject("$gt",thrMax));
				}else if("between".equals(thrSign)){
					dbo.put("thrEmpty", new BasicDBObject("$gte",thrMin).append("$lte", thrMax));
				}
			}
			if (!(tenSign == null || "".equals(tenSign))) {
				if ("<".equals(tenSign)) {
					dbo.put("tenEmpty", new BasicDBObject("$lt",tenMin));
				}else if (">".equals(tenSign)) {
					dbo.put("tenEmpty", new BasicDBObject("$gt",tenMax));
				}else if("between".equals(tenSign)){
					dbo.put("tenEmpty", new BasicDBObject("$gte",tenMin).append("$lte", tenMax));
				}
			}
			
			DBObject orderBy = new BasicDBObject("timeStamp", -1);
			int skip = (currPage-1)*pageSize;
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoEntities(dbCurToList(cur));
			cur.close();
			result.setCount(count);
			return result;
		} catch (Exception e) {
			logger.error("检索 findbysid：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}

//	@Override
//	public MeetingInfoResult findByMeetingId(int meetingId,int micId,String userId) {
//		//pageSize *= 2;
//		MeetingInfoResult result = new MeetingInfoResult();
//		try {
//			DBCollection employee = db.getCollection(MEETINGINFO_TAB);
//			DBCursor cur = null;
//			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
//			
////          dbo.put("timeStamp", new BasicDBObject("$gte", startTime).append("$lt", endTime));
////			if(directionType == 0 || directionType == 1){
////				dbo.put("directionType", directionType);
////			}
//			if (!(userId == null||"".equals(userId))) {
//				dbo.put("userId", userId);
//			}
//			if (meetingId > 0) {
//				dbo.put("meetingId", meetingId);
//			}
//			
//			if (micId == 1 || micId == 2) {
//				dbo.put("micId", micId);
//			}
////			if (relayId > 0) {
////				dbo.put("relayId", relayId);	
////			}
//			DBObject orderBy = new BasicDBObject("timeStamp", -1);
//			//int skip = (currPage-1)*pageSize;
//			
//			//表示从查询结果list中取出第skip个到skip+pagesize个为止
//			//skip(k)表示跳到第k个，即从第k个开始
//			//limit(n)表示从list中取出n个元素
//			//cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
//			cur = employee.find(dbo).sort(orderBy);
//			int count = employee.find(dbo).count();
//			result.setListMeetingInfoEntities(dbCurToList(cur));
//			result.setCount(count);
//			return result;
//		} catch (Exception e) {
//			logger.error("检索 findbyMeetingId：",e);
//		}
//		finally{
//			conn.destory(mongo, db);
//		}
//		return result;
//	}
	
	@Override
	public MeetingInfoResult findByMeetingId(int meetingId,int micId,String speakerId,int pageSize, int currPage) {
		pageSize *= 2;
		MeetingInfoResult result = new MeetingInfoResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFO_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			if (!(speakerId == null||"".equals(speakerId))) {
				dbo.put("speakerid", speakerId);
			}
			if (meetingId > 0) {
				dbo.put("meetingid", meetingId);
			}
			
			if (micId == 1 || micId == 2) {
				dbo.put("micid", micId);
			}
			
			//DBObject orderBy = new BasicDBObject("timeStamp", -1);
			//int skip = (currPage-1)*pageSize;
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
			//cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			//cur = employee.find();
			//int count = employee.find().count();
			//cur = employee.find(dbo).skip(skip).limit(pageSize);
			cur = employee.find(dbo);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoEntities(dbCurToList(cur));
			cur.close();
			result.setCount(count);
			return result;
		} catch (Exception e) {
			logger.error("检索 findbyMeetingId：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}
}
