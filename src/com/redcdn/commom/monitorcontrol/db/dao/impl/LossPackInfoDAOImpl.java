package com.redcdn.commom.monitorcontrol.db.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.redcdn.commom.monitorcontrol.db.dao.LossPackInfoDAO;
import com.redcdn.commom.monitorcontrol.db.entity.FormDataExcelEntity;
import com.redcdn.commom.monitorcontrol.db.entity.LossPackInfoEntity;
import com.redcdn.commom.monitorcontrol.db.entity.LossPackInfoForReportEntity;
import com.redcdn.commom.monitorcontrol.db.entity.view.LossPackInfoResult;
import com.redcdn.commom.monitorcontrol.dto.FormDataExcelListDTO;

public class LossPackInfoDAOImpl extends BaseMeetingInfoDAO implements LossPackInfoDAO{
	private Logger logger = Logger.getLogger(this.getClass());
	//private String LOSSPACKINFO_TAB = "MeetingStatisticInfo_Table";
	//private String LOSSPACKINFO_TAB = "MeetingStatisticInfoNew_Table";
	private String lOSSPACKINFO_OLD_TAB = "MeetingStatisticInfo_Table";
	private String LOSSPACKINFO_TAB     = "MeetingStatisticInfo_FourTable";
	//private String LOSSPACKINFO_TAB     = "MeetingStatisticInfo_FourTable_test";
	@Override
	public LossPackInfoResult findByUserId(int meetingId, String speakerId,
			String userId, int pageSize, int currPage) {
	    pageSize *= 2;
		LossPackInfoResult result = new LossPackInfoResult();
		try {
			DBCollection employee = db.getCollection(LOSSPACKINFO_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			//建立索引
//			BasicDBObject indexDbo = new BasicDBObject();
//			indexDbo.put("meetingId", 1);
//			indexDbo.put("userId", 1);
//
//			employee.createIndex(indexDbo);
			
//			if(micId == 1 || micId ==2){
//				dbo.put("micId", micId);
//			}
			if (meetingId > 0) {
				dbo.put("meetingId", meetingId);
			}
			if (!"".equals(userId)) {
				if (Integer.parseInt(userId) > 0) {
					dbo.put("userId", userId);	
				}
			}
			
			if (!"".equals(speakerId)) {
				if (Integer.parseInt(speakerId) > 0) {
					dbo.put("speakerId", speakerId);	
				}
			}
			
			//DBObject orderBy = new BasicDBObject("reporttime", 1);
			DBObject orderBy = new BasicDBObject("timeStamp", 1);
			//int skip = (currPage-1)*pageSize;
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
			//cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			cur = employee.find(dbo).sort(orderBy);
			//int count = employee.find(dbo).count();
			System.out.println("表名:"+LOSSPACKINFO_TAB);
//			int count = cur.count();
			DBObject dbObject = cur.explain();
			System.out.println("explain:"+dbObject);
			Set<Integer> mediaTypes = new HashSet<Integer>();
			result.setListLossPackInfoEntities(dbCurToList(cur,mediaTypes));
//			result.setCount(count);
			System.out.println("resourIds.size:"+mediaTypes.size());
			result.setMediaTypeList(mediaTypes);
			//employee.dropIndex(indexDbo);
			cur.close();
			return result;
		} catch (Exception e) {
			logger.error("检索 findByUserId：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}
	
	private List<LossPackInfoEntity> dbCurToList(DBCursor cursor, Set<Integer> mediaTypes) {
		logger.info("进入dbCurToList");
		List<LossPackInfoEntity> list = new ArrayList<LossPackInfoEntity>();
		LossPackInfoEntity entity = null;
		while (cursor.hasNext()) {
			//test
			DBObject dbObject = cursor.next();
			//System.out.println("entity:"+ dbObject.toString());
			if (dbObject != null) {
				try {
					entity = getLossPackInfoEntity(dbObject);
				} catch (Exception e) {
					logger.error(" dbCurToList：",e);
				}
				if(null != entity && entity.getTimeStamp() != 0){
					list.add(entity);
					mediaTypes.add(entity.getMediaType());
				}	
			}
			
			
			//正式版
			//LossPackInfoEntity entity = getLossPackInfoEntity(cursor.next());
			//list.add(entity);
		}
		cursor.close();
		logger.info("退出dbCurToList");
		return list;
	}
	
	private List<LossPackInfoEntity> dbCurToList(DBCursor cursor) {
		logger.info("进入dbCurToList");
		List<LossPackInfoEntity> list = new ArrayList<LossPackInfoEntity>();
		LossPackInfoEntity entity = null;
		while (cursor.hasNext()) {
			//test
			DBObject dbObject = cursor.next();
			//System.out.println("entity:"+ dbObject.toString());
			if (dbObject != null) {
				try {
					entity = getLossPackInfoEntity(dbObject);
				} catch (Exception e) {
					logger.error(" dbCurToList：",e);
				}
				if(null != entity && entity.getTimeStamp() != 0){
					list.add(entity);
				}	
			}
			
			
			//正式版
			//LossPackInfoEntity entity = getLossPackInfoEntity(cursor.next());
			//list.add(entity);
		}
		cursor.close();
		logger.info("退出dbCurToList");
		return list;
	}
	
	private Map<String, LossPackInfoForReportEntity> dbCurToMap(DBCursor cursor,int Type){
		logger.info("进入dbCurToList");
		Map<String, LossPackInfoForReportEntity> map = new HashMap<String, LossPackInfoForReportEntity>();
		LossPackInfoEntity entity = null;
		while (cursor.hasNext()) {
			//test
			
			DBObject dbObject = cursor.next();
			//System.out.println("entity:"+ dbObject.toString());
			if (dbObject != null) {
				try {
					entity = getLossPackInfoEntity(dbObject);
				} catch (Exception e) {
					logger.error(" dbCurToList：",e);
				}
				if(null != entity && entity.getTimeStamp() != 0){
					if (Type == 1) {
						//只要user汇报的数据
						if (Integer.parseInt(entity.getUserId()) > 9999999) {
							String key = entity.getUserId() + "_" + entity.getMeetingId();
							if (map.containsKey(key)) {
								map.get(key).list.add(entity);
//								List<LossPackInfoEntity> list =  map.get(key).list;
//								System.out.println("list.size:"+list.size());
							}else {
								LossPackInfoForReportEntity entity2 = new LossPackInfoForReportEntity(entity.getMeetingId(), entity.getUserId());
								entity2.list.add(entity);
								map.put(key, entity2);
							}
						}
					}else {
						String key = entity.getUserId() + "_" + entity.getMeetingId();
						if (map.containsKey(key)) {
							map.get(key).list.add(entity);
						}else {
							LossPackInfoForReportEntity entity2 = new LossPackInfoForReportEntity(entity.getMeetingId(), entity.getUserId());
							entity2.list.add(entity);
							map.put(key, entity2);
						}
					}
				}	
			}
			//正式版
			//LossPackInfoEntity entity = getLossPackInfoEntity(cursor.next());
			//list.add(entity);
		}
		cursor.close();
		logger.info("退出dbCurToList");
		return map;
	}
	
	
	/**
	 * 从db获取相应数据
	 * @param cursor 
	 * @param Type 筛选数据条件
	 * @return
	 */
	private List<LossPackInfoEntity> dbCurToList(DBCursor cursor,int Type) {
		logger.info("进入dbCurToList");
		List<LossPackInfoEntity> list = new ArrayList<LossPackInfoEntity>();
		LossPackInfoEntity entity = null;
		while (cursor.hasNext()) {
			//test
			DBObject dbObject = cursor.next();
			//System.out.println("entity:"+ dbObject.toString());
			if (dbObject != null) {
				try {
					entity = getLossPackInfoEntity(dbObject);
				} catch (Exception e) {
					logger.error(" dbCurToList：",e);
				}
				if(null != entity && entity.getTimeStamp() != 0){
					if (Type == 1) {
						if (Integer.parseInt(entity.getUserId()) > 9999999) {
							list.add(entity);
						}
					}else {
						list.add(entity);
					}
				}	
			}
			//正式版
			//LossPackInfoEntity entity = getLossPackInfoEntity(cursor.next());
			//list.add(entity);
		}
		cursor.close();
		logger.info("退出dbCurToList");
		return list;
	}
	private List<FormDataExcelEntity> dbCurToListForReport(DBCursor cursor) {
		List<FormDataExcelEntity> list = new ArrayList<FormDataExcelEntity>();
		FormDataExcelEntity entity = null;
		
		while (cursor.hasNext()) {
			//test
			DBObject dbObject = cursor.next();
			if (dbObject != null) {
				try {
					entity = getFormDataExcelEntity(dbObject);
				} catch (Exception e) {
					logger.error(" dbCurToList：",e);
				}
				if(null != entity && entity.getTimeStamp() != 0){
					list.add(entity);
				}	
			}		
		}
		cursor.close();
		return list;
	}
	
	private void dbCurToListForReport(DBCursor cursor,FormDataExcelListDTO dto) {
		logger.info("进入dbCurToListForReport");
		FormDataExcelEntity entity = null;
		while (cursor.hasNext()) {
			try {
				entity = getFormDataExcelEntity(cursor.next());
			} catch (Exception e) {
				logger.error(" dbCurToList：",e);
			}
			if(null != entity && entity.getTimeStamp() != 0 && (entity.getMicId()==1 || entity.getMicId() == 2)){
				dto.getEntities().add(entity);
			}
		}
		cursor.close();
		System.out.println("conut:"+ dto.getEntities().size());
		logger.info("退出dbCurToListForReport");
		//return dto;
	}
	private LossPackInfoEntity getLossPackInfoEntity(DBObject dbObject) {
		LossPackInfoEntity responseEntity = new LossPackInfoEntity();
//		responseEntity.setAudioCoderateExp(Integer.parseInt(dbObject.get("audioCoderateExp")==null?"0":dbObject.get("audioCoderateExp").toString()));
//		responseEntity.setAudioCoderateUse(Integer.parseInt(dbObject.get("audioCoderateUse")==null?"0":dbObject.get("audioCoderateUse").toString()));
//		responseEntity.setAudiolossRateFEC(Integer.parseInt(dbObject.get("audiolossRateFEC")==null?"0":dbObject.get("audiolossRateFEC").toString()));
//		responseEntity.setAudiolossRateFECCC(Integer.parseInt(dbObject.get("audiolossRateFECCC")==null?"0":dbObject.get("audiolossRateFECCC").toString()));
//		responseEntity.setAudiolossRateFinal(Integer.parseInt(dbObject.get("audiolossRateFinal")==null?"0":dbObject.get("audiolossRateFinal").toString()));
//		responseEntity.setAudiolossRateFinalCC(Integer.parseInt(dbObject.get("audiolossRateFinalCC")==null?"0":dbObject.get("audiolossRateFinalCC").toString()));
//		responseEntity.setAudiolossRateOriginal(Integer.parseInt(dbObject.get("audiolossRateOriginal")==null?"0":dbObject.get("audiolossRateOriginal").toString()));
//		responseEntity.setAudiolossRateOriginalCC(Integer.parseInt(dbObject.get("audiolossRateOriginalCC")==null?"0":dbObject.get("audiolossRateOriginalCC").toString()));
		try {
			responseEntity.setBandwidth(Integer.parseInt(dbObject.get("bandwidth")==null?"0":dbObject.get("bandwidth").toString()));
		} catch (Exception e) {
			logger.error(" getLossPackInfoEntity：",e);
			
		}
		
		responseEntity.setEmptyPacketRate(Integer.parseInt(dbObject.get("emptyPacketRate")==null?"0":dbObject.get("emptyPacketRate").toString()));
//		responseEntity.setFecrateExpAudio(dbObject.get("fecrateExpAudio")==null?"0":dbObject.get("fecrateExpAudio").toString());
//		responseEntity.setFecrateExpVideo(dbObject.get("fecrateExpVideo")==null?"0":dbObject.get("fecrateExpVideo").toString());
//		responseEntity.setFecrateUseAudio(dbObject.get("fecrateUseAudio")==null?"0":dbObject.get("fecrateUseAudio").toString());
//		responseEntity.setFecrateUseVideo(dbObject.get("fecrateUseVideo")==null?"0":dbObject.get("fecrateUseVideo").toString());
		responseEntity.setCpuRate(Integer.parseInt(dbObject.get("cpuRate")==null?"0":dbObject.get("cpuRate").toString()));
//		responseEntity.setDelayTime(Integer.parseInt(dbObject.get("delayTime")==null?"0":dbObject.get("delayTime").toString()));
		responseEntity.setDirectionType(Integer.parseInt(dbObject.get("directionType")==null?"0":dbObject.get("directionType").toString()));
		responseEntity.setFouEmpty(Integer.parseInt(dbObject.get("fouEmpty")==null?"0":dbObject.get("fouEmpty").toString()));
		responseEntity.setFrameRate(Integer.parseInt(dbObject.get("frameRate")==null?"0":dbObject.get("frameRate").toString()));
		responseEntity.setMemRate(Integer.parseInt(dbObject.get("memRate")==null?"0":dbObject.get("memRate").toString()));
		responseEntity.setTraffic(Integer.parseInt(dbObject.get("traffic")==null?"0":dbObject.get("traffic").toString()));
//		responseEntity.setVideoCoderateExp(Integer.parseInt(dbObject.get("videoCoderateExp")==null?"0":dbObject.get("videoCoderateExp").toString()));
//		responseEntity.setVideoCoderateUse(Integer.parseInt(dbObject.get("videoCoderateUse")==null?"0":dbObject.get("videoCoderateUse").toString()));
//		responseEntity.setVideolossRateFEC(Integer.parseInt(dbObject.get("videolossRateFEC")==null?"0":dbObject.get("videolossRateFEC").toString()));
//		responseEntity.setVideolossRateFECCC(Integer.parseInt(dbObject.get("videolossRateFECCC")==null?"0":dbObject.get("videolossRateFECCC").toString()));
//		responseEntity.setVideolossRateFinal(Integer.parseInt(dbObject.get("videolossRateFinal")==null?"0":dbObject.get("videolossRateFinal").toString()));
//		responseEntity.setVideolossRateFinalCC(Integer.parseInt(dbObject.get("videolossRateFinalCC")==null?"0":dbObject.get("videolossRateFinalCC").toString()));
//		responseEntity.setVideolossRateOriginal(Integer.parseInt(dbObject.get("videolossRateOriginal")==null?"0":dbObject.get("videolossRateOriginal").toString()));
//		responseEntity.setVideolossRateOriginalCC(Integer.parseInt(dbObject.get("videolossRateOriginalCC")==null?"0":dbObject.get("videolossRateOriginalCC").toString()));
		responseEntity.setMeetingId(Integer.parseInt(dbObject.get("meetingid")==null?"0":dbObject.get("meetingid").toString()));
//		responseEntity.setMicId(Integer.parseInt(dbObject.get("micId")==null?"0":dbObject.get("micId").toString()));
		responseEntity.setOneEmpty(Integer.parseInt(dbObject.get("oneEmpty")==null?"0":dbObject.get("oneEmpty").toString()));
		responseEntity.setRelayId(Integer.parseInt(dbObject.get("relayId")==null?"0":dbObject.get("relayId").toString()));
		responseEntity.setRelayIp(dbObject.get("relayIp")==null?"0":dbObject.get("relayIp").toString());
		responseEntity.setTenEmpty(Integer.parseInt(dbObject.get("tenEmpty")==null?"0":dbObject.get("tenEmpty").toString()));
		responseEntity.setThrEmpty(Integer.parseInt(dbObject.get("thrEmpty")==null?"0":dbObject.get("thrEmpty").toString()));
		responseEntity.setTimeStamp((Long.parseLong(dbObject.get("timeStamp")==null?"0":dbObject.get("timeStamp").toString()))*1000);
		responseEntity.setTwoEmpty(Integer.parseInt(dbObject.get("twoEmpty")==null?"0":dbObject.get("twoEmpty").toString()));
		responseEntity.setUserIp(dbObject.get("userIp")==null?"0":dbObject.get("userIp").toString());
		responseEntity.setUserId(dbObject.get("userId")==null?"0":dbObject.get("userId").toString());
		responseEntity.setNetworkType(Integer.parseInt(dbObject.get("networkType")==null?"0":dbObject.get("networkType").toString()));
//		responseEntity.setDetectBandwith(Integer.parseInt(dbObject.get("detectBandwith")==null?"0":dbObject.get("detectBandwith").toString()));
		responseEntity.setDetectBandwidth(Integer.parseInt(dbObject.get("detectBandwidth")==null?"0":dbObject.get("detectBandwidth").toString()));
		responseEntity.setMeetingId(Integer.parseInt(dbObject.get("meetingId")==null?"0":dbObject.get("meetingId").toString()));
//		responseEntity.setAudioWaitTime(Integer.parseInt(dbObject.get("audioWaitTime")==null?"0":dbObject.get("audioWaitTime").toString()));
//		responseEntity.setVedioWaitTime(Integer.parseInt(dbObject.get("vedioWaitTime")==null?"0":dbObject.get("vedioWaitTime").toString()));
//		responseEntity.setAudioLaterRate(Integer.parseInt(dbObject.get("audioLaterRate")==null?"0":dbObject.get("audioLaterRate").toString()));
//		responseEntity.setVedioLaterRate(Integer.parseInt(dbObject.get("vedioLaterRate")==null?"0":dbObject.get("vedioLaterRate").toString()));
		responseEntity.setNetworkParam1(Integer.parseInt(dbObject.get("networkParam1")==null?"0":dbObject.get("networkParam1").toString()));
		responseEntity.setNetworkParam2(Integer.parseInt(dbObject.get("networkParam2")==null?"0":dbObject.get("networkParam2").toString()));
		responseEntity.setDelayTimeIntArray(dbObject.get("delayTimeIntArray")==null?"0":dbObject.get("delayTimeIntArray").toString());
//		responseEntity.setVideoMaxCodeRate(Integer.parseInt(dbObject.get("videoMaxCodeRate")==null?"0":dbObject.get("videoMaxCodeRate").toString()));
//		responseEntity.setVideoStableCodeRate(Integer.parseInt(dbObject.get("videoStableCodeRate")==null?"0":dbObject.get("videoStableCodeRate").toString()));
//		responseEntity.setVideoCurCodeRate(Integer.parseInt(dbObject.get("videoCurCodeRate")==null?"0":dbObject.get("videoCurCodeRate").toString()));
		responseEntity.setLossRateFEC(Integer.parseInt(dbObject.get("lossRateFEC")==null?"0":dbObject.get("lossRateFEC").toString()));
		responseEntity.setLossRateFECCC(Integer.parseInt(dbObject.get("lossRateFECCC")==null?"0":dbObject.get("lossRateFECCC").toString()));
		responseEntity.setLossRateFinal(Integer.parseInt(dbObject.get("lossRateFinal")==null?"0":dbObject.get("lossRateFinal").toString()));
		responseEntity.setLossRateFinalCC(Integer.parseInt(dbObject.get("lossRateFinalCC")==null?"0":dbObject.get("lossRateFinalCC").toString()));
		responseEntity.setLossRateOriginal(Integer.parseInt(dbObject.get("lossRateOriginal")==null?"0":dbObject.get("lossRateOriginal").toString()));
		responseEntity.setLossRateOriginalCC(Integer.parseInt(dbObject.get("lossRateOriginalCC")==null?"0":dbObject.get("lossRateOriginalCC").toString()));
		responseEntity.setInValidTimes(dbObject.get("inValidTimes")==null?"0":dbObject.get("inValidTimes").toString());
		responseEntity.setMediaFormat(dbObject.get("mediaFormat")==null?"0":dbObject.get("mediaFormat").toString());
		responseEntity.setExpCodeRate(Integer.parseInt(dbObject.get("expCodeRate")==null?"0":dbObject.get("expCodeRate").toString()));
		responseEntity.setCurCodeRate(Integer.parseInt(dbObject.get("curCodeRate")==null?"0":dbObject.get("curCodeRate").toString()));
		responseEntity.setMaxCodeRate(Integer.parseInt(dbObject.get("maxCodeRate")==null?"0":dbObject.get("maxCodeRate").toString()));
		responseEntity.setStableCodeRate(Integer.parseInt(dbObject.get("stableCodeRate")==null?"0":dbObject.get("stableCodeRate").toString()));
		responseEntity.setWaitTime(Integer.parseInt(dbObject.get("waitTime")==null?"0":dbObject.get("waitTime").toString()));
		responseEntity.setReTranTimes(Integer.parseInt(dbObject.get("reTranTimes")==null?"0":dbObject.get("reTranTimes").toString()));
		responseEntity.setFecRateExp(dbObject.get("fecRateExp")==null?"0":dbObject.get("fecRateExp").toString());
		responseEntity.setFecRateUse(dbObject.get("fecRateUse")==null?"0":dbObject.get("fecRateUse").toString());
		responseEntity.setSpeakerId(dbObject.get("speakerId")==null?"0":dbObject.get("speakerId").toString());
		responseEntity.setSystemType(Integer.parseInt(dbObject.get("systemType")==null?"0":dbObject.get("systemType").toString()));
		responseEntity.setDeviceType(Integer.parseInt(dbObject.get("deviceType")==null?"0":dbObject.get("deviceType").toString()));
		responseEntity.setResourceId(Integer.parseInt(dbObject.get("resourceId")==null?"0":dbObject.get("resourceId").toString()));
		responseEntity.setMediaType(Integer.parseInt(dbObject.get("mediaType")==null?"0":dbObject.get("mediaType").toString()));
		responseEntity.setServerTime((Long.parseLong(dbObject.get("serverTime")==null?"0":dbObject.get("serverTime").toString()))*1000);
		responseEntity.setKeyLogId(dbObject.get("keyLogId")==null?"0":dbObject.get("keyLogId").toString());
		return responseEntity;
	}

	private FormDataExcelEntity getFormDataExcelEntity(DBObject dbObject) {
		FormDataExcelEntity responseEntity = new FormDataExcelEntity();
		responseEntity.setBandwidth(Integer.parseInt(dbObject.get("bandwidth")==null?"0":dbObject.get("bandwidth").toString()));
		responseEntity.setDirectionType(Integer.parseInt(dbObject.get("directionType")==null?"0":dbObject.get("directionType").toString()));
		responseEntity.setTraffic(Integer.parseInt(dbObject.get("traffic")==null?"0":dbObject.get("traffic").toString()));
		responseEntity.setMeetingId(Integer.parseInt(dbObject.get("meetingid")==null?"0":dbObject.get("meetingid").toString()));
		responseEntity.setMicId(Integer.parseInt(dbObject.get("micId")==null?"0":dbObject.get("micId").toString()));
		responseEntity.setRelayId(Integer.parseInt(dbObject.get("relayId")==null?"0":dbObject.get("relayId").toString()));
		responseEntity.setRelayIp(dbObject.get("relayIp")==null?"0":dbObject.get("relayIp").toString());
		responseEntity.setTimeStamp((Long.parseLong(dbObject.get("reporttime")==null?"0":dbObject.get("reporttime").toString()))*1000);
		responseEntity.setUserIp(dbObject.get("userIp")==null?"0":dbObject.get("userIp").toString());
		responseEntity.setUserId(dbObject.get("userId")==null?"0":dbObject.get("userId").toString());
		responseEntity.setNetworkType(Integer.parseInt(dbObject.get("networkType")==null?"0":dbObject.get("networkType").toString()));
		responseEntity.setMeetingId(Integer.parseInt(dbObject.get("meetingId")==null?"0":dbObject.get("meetingId").toString()));
		return responseEntity;
	}
		
	@Override
	public LossPackInfoResult findByMeetingId(int meetingId, String speakerId, int directionType) {
		LossPackInfoResult result = new LossPackInfoResult();
		try {
			DBCollection employee = db.getCollection(LOSSPACKINFO_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
//			List<DBObject> listIndexs = new ArrayList<DBObject>();
//			listIndexs = employee.getIndexInfo();
//			for (DBObject dbObject : listIndexs) {
//				System.out.println("IndexInfo:"+dbObject);
//			}
			
			
			if (meetingId > 0) {
				dbo.put("meetingId", meetingId);
			}
			if(directionType == 1){
				dbo.put("directionType", directionType);
			}else{
				dbo.put("directionType", 1);
			}
//			if(micId == 1 || micId == 2){
//				dbo.put("micId", micId);
//			}
			
			if (!"".equals(speakerId)) {
				if (Integer.parseInt(speakerId) > 0) {
					dbo.put("speakerId", speakerId);	
				}
			}
			
			DBObject orderBy = new BasicDBObject("userId", 1);
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
			//cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
//			List<LossPackInfoEntity> list = new ArrayList<LossPackInfoEntity>();
			cur = employee.find(dbo).sort(orderBy);
			DBObject dbObject = cur.explain();
			System.out.println("explainB:"+dbObject);
//			int count = cur.count();
			result.setListLossPackInfoEntities(dbCurToList(cur));
			//result.setCount(count);
			cur.close();
			return result;
		} catch (Exception e) {
			
			logger.error("检索 findByMeetingId：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}

//	@Override
//	public LossPackInfoResult findByMeetingIds(List<Integer> list, String speakerId,
//			int directionType,long endTime)  {
//		
//		LossPackInfoResult result = new LossPackInfoResult();
//		try {
//			DBCollection employee = db.getCollection(LOSSPACKINFO_TAB);
//			DBCursor cur = null;
//			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
//			
//			//用in来代替or实行SQL中的or查询
//			if (list.size()>0) {
//				BasicDBList values = new BasicDBList();
//				for (int meetingId :list) {
//					//BasicDBObject dbo1 = new BasicDBObject();
//					values.add(meetingId);
//				}
//				dbo.put("meetingId", new BasicDBObject("$in",values));
//			}
//			
//			if(directionType == 1){
//				dbo.put("directionType", directionType);
//			}else{
//				dbo.put("directionType", 1);
//			}
//			if (!"".equals(speakerId)) {
//				if (Integer.parseInt(speakerId) > 0) {
//					dbo.put("speakerId", speakerId);	
//				}
//			}
//			dbo.put("reporttime", new BasicDBObject("$lte", endTime));
//			DBObject orderBy = new BasicDBObject("meetingId", 1).append("userId", 1);
//
//			cur = employee.find(dbo).sort(orderBy);
//			DBObject dbObject = cur.explain();
//			System.out.println("explainB:"+dbObject);
//			int count = cur.count();
//			result.setListLossPackInfoEntities(dbCurToList(cur));
//			result.setCount(count);
//			return result;
//		} catch (Exception e) {
//			logger.error("检索 findByMeetingId：",e);
//		}
//		finally{
//			conn.destory(mongo, db);
//		}
//		return result;
//	}

	@Override
	public List<FormDataExcelEntity> findByMeetingIds(List<Integer> list) {
		List<FormDataExcelEntity> entities = new ArrayList<FormDataExcelEntity>();
		try {
			DBCollection employee = db.getCollection(LOSSPACKINFO_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
//			if (list.size()>0) {
//				BasicDBObject[] dbos = new BasicDBObject[list.size()]; 
//				for(int meetingId,i = 0;i<list.size();i++){
//					meetingId = list.get(i);
//					dbos[i] = new BasicDBObject("meetingId", meetingId);
//				}
//				dbo.put("$or", dbos);
//				//dbo.put("$in", dbos);
//			}
			//用in来代替or实行SQL中的or查询
			if (list.size()>0) {
				BasicDBList values = new BasicDBList();
				for (int meetingId :list) {
					//BasicDBObject dbo1 = new BasicDBObject();
					values.add(meetingId);
				}
				dbo.put("meetingId", new BasicDBObject("$in",values));
			}
			DBObject orderBy = new BasicDBObject("meetingId", 1);
			

			cur = employee.find(dbo).sort(orderBy);
			DBObject dbObject = cur.explain();
			System.out.println("explainB:"+dbObject);
			//int count = cur.count();
			entities = dbCurToListForReport(cur);
			cur.close();
			return entities;
		} catch (Exception e) {
			logger.error("检索 findByMeetingId：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return entities;
	}

	@Override
	public FormDataExcelListDTO findByTime(long startTime, long endTime,
			int currPage, int pageSize, boolean isCount) {
		//List<FormDataExcelEntity> entities = new ArrayList<FormDataExcelEntity>();
		logger.info("进入findBytime");
		FormDataExcelListDTO dto = new FormDataExcelListDTO();
		try {
			DBCollection employee = db.getCollection(LOSSPACKINFO_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("reporttime", new BasicDBObject("$gte", startTime).append("$lte", endTime));
			int skip = (currPage - 1) * pageSize;
			DBObject orderBy = new BasicDBObject("meetingId", 1).append("userId", 1);
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			System.out.println("explain:"+cur.explain());
			if (isCount) {
				int count = employee.find(dbo).count();
				int totalPage = count % pageSize == 0 ? count / pageSize
						: (count / pageSize) + 1;
				dto.setCurrPage(currPage);
				dto.setPageSize(pageSize);
				dto.setTotalPage(totalPage);
			}
			dbCurToListForReport(cur,dto);
			cur.close();
		} catch (Exception e) {
			logger.error("检索 findByTime：", e);
		}finally{
			conn.destory(mongo, db);
		}
		logger.info("退出findBytime");
		return dto;
	}
	
	@Override
	public FormDataExcelListDTO findByMeetingIds(List<Integer> list,int currPage, int pageSize, boolean isCount,int index) {
		FormDataExcelListDTO dto = new FormDataExcelListDTO();
		try {
			DBCollection employee =null;
			if (index == 0) {
				employee  = db.getCollection(lOSSPACKINFO_OLD_TAB);
			}
			if (index == 1) {
				employee = db.getCollection(LOSSPACKINFO_TAB);
			}
			
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
//			if (list.size()>0) {
//				BasicDBObject[] dbos = new BasicDBObject[list.size()]; 
//				for(int meetingId,i = 0;i<list.size();i++){
//					meetingId = list.get(i);
//					dbos[i] = new BasicDBObject("meetingId", meetingId);
//				}
//				dbo.put("$or", dbos);
//				//dbo.put("$in", dbos);
//			}
			//用in来代替or实现SQL中的or查询
			if (list.size()>0) {
				BasicDBList values = new BasicDBList();
				for (int meetingId :list) {
					//BasicDBObject dbo1 = new BasicDBObject();
					values.add(meetingId);
				}
				dbo.put("meetingId", new BasicDBObject("$in",values));
			}
			DBObject orderBy = new BasicDBObject("meetingId", 1).append("userId", 1);
			//DBObject orderBy = new BasicDBObject("meetingId", 1).append("userId", 1).append("directionType", 1);
			//DBObject orderBy = new BasicDBObject("meetingId", 1);

			int skip = (currPage - 1) * pageSize;
			//DBObject orderBy = new BasicDBObject("meetingId", 1).append("userId", 1);
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			System.out.println("explain:"+cur.explain());
			if (isCount) {
				int count = employee.find(dbo).count();
				System.out.println("count:"+count);
//				if (count == 0) {
//					dto = findByMeetingIdsForNewTable(list,currPage,pageSize,isCount);
//					return dto;
//				}
				int totalPage = count % pageSize == 0 ? count / pageSize
						: (count / pageSize) + 1;
				dto.setCurrPage(currPage);
				dto.setPageSize(pageSize);
				dto.setTotalPage(totalPage);
			}
			dbCurToListForReport(cur,dto);
			cur.close();
		} catch (Exception e) {
			logger.error("检索 findByTime：", e);
		}finally{
			conn.destory(mongo, db);
		}
		logger.info("退出findBytime");
		return dto;
	}

	@Override
	public FormDataExcelListDTO findByMeetingIds(List<Integer> list,
			int currPage, int pageSize, boolean isCount) {
		FormDataExcelListDTO dto = new FormDataExcelListDTO();
		try {
			DBCollection employee =db.getCollection(LOSSPACKINFO_TAB);			
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
//			if (list.size()>0) {
//				BasicDBObject[] dbos = new BasicDBObject[list.size()]; 
//				for(int meetingId,i = 0;i<list.size();i++){
//					meetingId = list.get(i);
//					dbos[i] = new BasicDBObject("meetingId", meetingId);
//				}
//				dbo.put("$or", dbos);
//				//dbo.put("$in", dbos);
//			}
			//用in来代替or实行SQL中的or查询
			if (list.size()>0) {
				BasicDBList values = new BasicDBList();
				for (int meetingId :list) {
					//BasicDBObject dbo1 = new BasicDBObject();
					values.add(meetingId);
				}
				dbo.put("meetingId", new BasicDBObject("$in",values));
			}
			//DBObject orderBy = new BasicDBObject("meetingId", 1).append("userId", 1);
			//DBObject orderBy = new BasicDBObject("meetingId", 1).append("userId", 1).append("directionType", 1);
			DBObject orderBy = new BasicDBObject("meetingId", 1);

			int skip = (currPage - 1) * pageSize;
			//DBObject orderBy = new BasicDBObject("meetingId", 1).append("userId", 1);
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			System.out.println("explain:"+cur.explain());
			if (isCount) {
				int count = employee.find(dbo).count();
				System.out.println("count:"+count);
//				if (count == 0) {
//					dto = findByMeetingIdsForNewTable(list,currPage,pageSize,isCount);
//					return dto;
//				}
				int totalPage = count % pageSize == 0 ? count / pageSize
						: (count / pageSize) + 1;
				dto.setCurrPage(currPage);
				dto.setPageSize(pageSize);
				dto.setTotalPage(totalPage);
			}
			dbCurToListForReport(cur,dto);
			cur.close();
		} catch (Exception e) {
			logger.error("检索 findByTime：", e);
		}finally{
			conn.destory(mongo, db);
		}
		logger.info("退出findBytime");
		return dto;
	}

	
//	@Override
//	public LossPackInfoResult findByMeetingIds(List<Integer> list, String speakerId,
//			int directionType, long endTime, String[] userList) {
//		LossPackInfoResult result = new LossPackInfoResult();
//		try {
//			DBCollection employee = db.getCollection(LOSSPACKINFO_TAB);
//			DBCursor cur = null;
//			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
//			
//			//用in来代替or实行SQL中的or查询
//			if (list.size()>0) {
//				BasicDBList values = new BasicDBList();
//				for (int meetingId :list) {
//					//BasicDBObject dbo1 = new BasicDBObject();
//					values.add(meetingId);
//				}
//				dbo.put("meetingId", new BasicDBObject("$in",values));
//			}
//			if (userList != null && userList.length>0) {
//				BasicDBList values = new BasicDBList();
//				for (String user : userList) {
//					values.add(user);
//				}
//				dbo.put("userId", new BasicDBObject("$in",values));
//			}else {
//				
//			}
////			if(directionType == 1){
////				dbo.put("directionType", directionType);
////			}else{
////				dbo.put("directionType", 1);
////			}
//			dbo.put("directionType", 1);
////			if(micId == 1 || micId == 2){
////				dbo.put("micId", micId);
////			}
//			if (!"".equals(speakerId)) {
//				if (Integer.parseInt(speakerId) > 0) {
//					dbo.put("speakerId", speakerId);	
//				}
//			}
//			dbo.put("timeStamp", new BasicDBObject("$lte", endTime));
//			DBObject orderBy = new BasicDBObject("meetingId", 1).append("userId", 1)/*.append("mediaType", 1)*/;
//
//			cur = employee.find(dbo).sort(orderBy);
//			DBObject dbObject = cur.explain();
//			System.out.println("explainB:"+dbObject);
//			int count = cur.count();
//			result.setListLossPackInfoEntities(dbCurToList(cur,1));
//			result.setCount(count);
//			return result;
//		} catch (Exception e) {
//			logger.error("检索 findByMeetingId：",e);
//		}
//		finally{
//			conn.destory(mongo, db);
//		}
//		return result;
//	}
	
	
	@Override
	public Map<String, LossPackInfoForReportEntity> findByMeetingIds(List<Integer> list, String speakerId,
			int directionType, long endTime, String[] userList) {
		//zhanghy 2017-01-18 修改表名查询方式，service层提供不完全表名（MeetingQosStatic+日期）,在DAO层循环
		//判断程序里组合的表名，如果组合表名在db中存在，则开始查询，不存在，则返回已查询到的数据。
		Map<String, LossPackInfoForReportEntity> queryMap = new HashMap<String, LossPackInfoForReportEntity>();
		try {
			//LOSSPACKINFO_TAB = "MeetingQosStatic_" +"2016_05_16";
			int index = 0;
			//判断该数据库中是否存在这个表名
			while(isCollectionExist(LOSSPACKINFO_TAB +"_"+index)){
				System.out.println("findByMeetingIds查询表名："+LOSSPACKINFO_TAB +"_"+index);
				//由于数据库现在有分表查询，所以用一个子表储存数据，循环后汇总到总表。
				Map<String, LossPackInfoForReportEntity> childqueryMap = new HashMap<String, LossPackInfoForReportEntity>();
				DBCollection employee = db.getCollection(LOSSPACKINFO_TAB +"_"+index);
				DBCursor cur = null;
				BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
				
				//用in来代替or实行SQL中的or查询
				if (list.size()>0) {
					BasicDBList values = new BasicDBList();
					for (int meetingId :list) {
						//BasicDBObject dbo1 = new BasicDBObject();
						values.add(meetingId);
					}
					dbo.put("meetingId", new BasicDBObject("$in",values));
				}
				if (userList != null && userList.length>0) {
					BasicDBList values = new BasicDBList();
					for (String user : userList) {
						values.add(user);
					}
					dbo.put("userId", new BasicDBObject("$in",values));
				}else {
					
				}
//				if(directionType == 1){
//					dbo.put("directionType", directionType);
//				}else{
//					dbo.put("directionType", 1);
//				}
				dbo.put("directionType", 1);

				if (!"".equals(speakerId)) {
					if (Integer.parseInt(speakerId) > 0) {
						dbo.put("speakerId", speakerId);	
					}
				}
				//dbo.put("timeStamp", new BasicDBObject("$lte", endTime));
				DBObject orderBy = new BasicDBObject("meetingId", 1).append("userId", 1)/*.append("mediaType", 1)*/;

				cur = employee.find(dbo).sort(orderBy);
				DBObject dbObject = cur.explain();
				System.out.println("explainB:"+dbObject);
				//int count = cur.count();
				childqueryMap =dbCurToMap(cur,1);
				//result.setCount(count);
				cur.close();
				//将子表收集到的数据汇总到总表
				queryMap.putAll(childqueryMap);
				index++;
			}
		
			return queryMap;
		} catch (Exception e) {
			logger.error("检索 findByMeetingId：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return queryMap;
	}
/*	@Override
	public Map<String, LossPackInfoForReportEntity> findByMeetingIds(List<Integer> list, String speakerId,
			int directionType, long endTime, String[] userList,Map<Integer, String> meetingInfo) {
		
		Map<String, LossPackInfoForReportEntity> queryMap = new HashMap<String, LossPackInfoForReportEntity>();
		try {
			//LOSSPACKINFO_TAB = "MeetingQosStatic_" +"2016_05_16";
			DBCollection employee = db.getCollection(LOSSPACKINFO_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			//用in来代替or实行SQL中的or查询
			if (list.size()>0) {
				BasicDBList values = new BasicDBList();
				for (int meetingId :list) {
					//BasicDBObject dbo1 = new BasicDBObject();
					values.add(meetingId);
				}
				dbo.put("meetingId", new BasicDBObject("$in",values));
			}
			if (userList != null && userList.length>0) {
				BasicDBList values = new BasicDBList();
				for (String user : userList) {
					values.add(user);
				}
				dbo.put("userId", new BasicDBObject("$in",values));
			}else {
				
			}
//			if(directionType == 1){
//				dbo.put("directionType", directionType);
//			}else{
//				dbo.put("directionType", 1);
//			}
			dbo.put("directionType", 1);

			if (!"".equals(speakerId)) {
				if (Integer.parseInt(speakerId) > 0) {
					dbo.put("speakerId", speakerId);	
				}
			}
			//dbo.put("timeStamp", new BasicDBObject("$lte", endTime));
			DBObject orderBy = new BasicDBObject("meetingId", 1).append("userId", 1).append("mediaType", 1);

			cur = employee.find(dbo).sort(orderBy);
			DBObject dbObject = cur.explain();
			System.out.println("explainB:"+dbObject);
			//int count = cur.count();
			queryMap =dbCurToMap(cur,1);
			//result.setCount(count);
			cur.close();
			return queryMap;
		} catch (Exception e) {
			logger.error("检索 findByMeetingId：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return queryMap;
	}*/
	@Override
	public void setCollectionName(String tabName) {
		this.LOSSPACKINFO_TAB =  "MeetingQosStatic" + tabName;
		
	}
	private boolean isCollectionExist(String tabName){
		Set<String> dbTableNames = db.getCollectionNames();
		if(dbTableNames.contains(tabName)){
			return true;
		}else{
			return false;
		}
	}
}
