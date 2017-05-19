package com.redcdn.commom.monitorcontrol.db.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.redcdn.commom.monitorcontrol.db.dao.MeetingInfoSummaryDAO;
import com.redcdn.commom.monitorcontrol.db.entity.MeetingInfoSummaryEntity;
import com.redcdn.commom.monitorcontrol.db.entity.view.MeetingInfoSummaryResult;

public class MeetingInfoSummaryDAOImpl extends BaseMeetingInfoDAO implements MeetingInfoSummaryDAO {

	private Logger logger = Logger.getLogger(this.getClass());
	//private String MEETINGINFO_TAB = "MeetingInfo_tab";
	//private String MEETINGINFOSUMMARY_TAB = "MeetingInfoSummary_Table";
	//private String MEETINGINFOSUMMARY_TAB = "MeetingInfoSummary_FourTable_test";
	private String MEETINGINFOSUMMARY_TAB = "MeetingInfoSummary_FourTable";
	@Override
	public MeetingInfoSummaryResult findByid(long startTime, long endTime,
			String userId, int meetingId, String relayId, int pageSize,
			int currPage) {
		pageSize *= 2;
		MeetingInfoSummaryResult result = new MeetingInfoSummaryResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFOSUMMARY_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("startTime", new BasicDBObject("$gte", startTime).append("$lt", endTime));
			
			if (!(userId == null||"".equals(userId))) {
				Pattern pattern = Pattern.compile("^.*" + userId+ ".*$", Pattern.CASE_INSENSITIVE); 
				dbo.put("userIdList", pattern);
			}			
			if (meetingId > 0) {
				dbo.put("meetingId", meetingId);
			}
//          dbo.put("meetingId", new BasicDBObject("$ne",50026024));
			if (!"".equals(relayId) && null != relayId) {
//				if (Integer.parseInt(relayId) > 0) {
//					Pattern pattern = Pattern.compile("^.*" + relayId+ ".*$", Pattern.CASE_INSENSITIVE); 
//					dbo.put("relayIdList", pattern);	
//				}
				Pattern pattern = Pattern.compile("^.*" + relayId+ ".*$", Pattern.CASE_INSENSITIVE); 
				dbo.put("relayIdList", pattern);
			}
			
			DBObject orderBy = new BasicDBObject("startTime", -1);
			int skip = (currPage-1)*pageSize;
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
//			cur = employee.find().limit(2);
//			int count = employee.find().count();
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoSummaryEntities(dbCurToList(cur));
			result.setCount(count);
			cur.close();
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
	public MeetingInfoSummaryResult findByid(long startTime, long endTime,
			String userId, int meetingId, String relayId, int pageSize,
			int currPage, List<String> userList) {
		// TODO Auto-generated method stub
		pageSize *= 2;
		MeetingInfoSummaryResult result = new MeetingInfoSummaryResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFOSUMMARY_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("startTime", new BasicDBObject("$gte", startTime).append("$lt", endTime));
		
			if (!(userId == null||"".equals(userId))) {
				Pattern pattern = Pattern.compile("^.*" + userId+ ".*$", Pattern.CASE_INSENSITIVE); 
				dbo.put("userIdList", pattern);
			}			
			if (meetingId > 0) {
				dbo.put("meetingId", meetingId);
			}
//          dbo.put("meetingId", new BasicDBObject("$ne",50026024));
			if (!"".equals(relayId) && null != relayId) {
//				if (Integer.parseInt(relayId) > 0) {
//					Pattern pattern = Pattern.compile("^.*" + relayId+ ".*$", Pattern.CASE_INSENSITIVE); 
//					dbo.put("relayIdList", pattern);	
//				}
				Pattern pattern = Pattern.compile("^.*" + relayId+ ".*$", Pattern.CASE_INSENSITIVE); 
				dbo.put("relayIdList", pattern);
			}
			if(userList != null){
				Iterator<String> ite = userList.iterator();
				BasicDBList value = new BasicDBList();
				while(ite.hasNext()){
					String user = ite.next();
					Pattern pattern = Pattern.compile("^.*" + user+ ".*$", Pattern.CASE_INSENSITIVE); 
					value.add(pattern);
				}
				dbo.put("userIdList", new BasicDBObject("$in",value));
			}
			DBObject orderBy = new BasicDBObject("startTime", -1);
			int skip = (currPage-1)*pageSize;
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
//			cur = employee.find().limit(2);
//			int count = employee.find().count();
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoSummaryEntities(dbCurToList(cur));
			result.setCount(count);
			cur.close();
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
	public MeetingInfoSummaryResult findByAttendCount(long startTime,
			long endTime, String attendCountSign, int attendCountMin,
			int attendCountMax, int pageSize, int currPage) {
		pageSize *= 2;
		MeetingInfoSummaryResult result = new MeetingInfoSummaryResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFOSUMMARY_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("startTime", new BasicDBObject("$gte", startTime).append("$lt", endTime));
		
			if(!(attendCountSign == null ||"".equals(attendCountSign))){
				if ("<".equals(attendCountSign)) {
					dbo.put("userCount", new BasicDBObject("$lt",attendCountMin));
				}else if (">".equals(attendCountSign)) {
					dbo.put("userCount", new BasicDBObject("$gt",attendCountMax));
				}else if("between".equals(attendCountSign)){
					dbo.put("userCount", new BasicDBObject("$gte",attendCountMin).append("$lte", attendCountMax));
				}
			}
			
			DBObject orderBy = new BasicDBObject("startTime",-1);
			
			//如果currPage=0且pageSize=0 表示这次查询是为了导出excel报告
			if (currPage == 0 && pageSize == 0) {
				cur = employee.find(dbo).sort(orderBy);
			}else {
				//表示从查询结果list中取出第skip个到skip+pagesize个为止
				//skip(k)表示跳到第k个，即从第k个开始
				//limit(n)表示从list中取出n个元素
				int skip = (currPage-1)*pageSize;
				cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			}
			int count = employee.find(dbo).count();
			result.setListMeetingInfoSummaryEntities(dbCurToList(cur));
			result.setCount(count);
			cur.close();
			return result;
		} catch (Exception e) {
			logger.error("检索 findbyAttendCount：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}
	
	@Override
	public MeetingInfoSummaryResult findByStream(long startTime, long endTime,
			String upVideoSignBefore, float upVideoLossBeforeMin,
			float upVideoLossBeforeMax, String upVideoSignAfter,
			float upVideoLossAfterMin, float upVideoLossAfterMax,
			String downVideoSignBefore, float downVideoLossBeforeMin,
			float downVideoLossBeforeMax, String downVideoSignAfter,
			float downVideoLossAfterMin, float downVideoLossAfterMax,
			String upAudioSignBefore, float upAudioLossBeforeMin,
			float upAudioLossBeforeMax, String upAudioSignAfter,
			float upAudioLossAfterMin, float upAudioLossAfterMax,
			String downAudioSignBefore, float downAudioLossBeforeMin,
			float downAudioLossBeforeMax, String downAudioSignAfter,
			float downAudioLossAfterMin, float downAudioLossAfterMax,
			int pageSize, int currPage) {

		pageSize *= 2;
		MeetingInfoSummaryResult result = new MeetingInfoSummaryResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFOSUMMARY_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("startTime", new BasicDBObject("$gte", startTime).append("$lt", endTime));
			
			if(!(upVideoSignBefore == null ||"".equals(upVideoSignBefore))){
				if ("<".equals(upVideoSignBefore)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("upLossVideoMic1", new BasicDBObject("$lt", upVideoLossBeforeMin)),
							new BasicDBObject("upLossVideoMic2", new BasicDBObject("$lt", upVideoLossBeforeMin))});
				}else if (">".equals(upVideoSignBefore)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("upLossVideoMic1", new BasicDBObject("$gt", upVideoLossBeforeMax)),
							new BasicDBObject("upLossVideoMic2", new BasicDBObject("$gt", upVideoLossBeforeMax))});
				}else if("between".equals(upVideoSignBefore)){
					dbo.put("$or", new BasicDBObject[]{
							new BasicDBObject("upLossVideoMic1", new BasicDBObject("$gte", upVideoLossBeforeMin).append("$lte", upVideoLossBeforeMax)),
							new BasicDBObject("upLossVideoMic2", new BasicDBObject("$gte", upVideoLossBeforeMin).append("$lte", upVideoLossBeforeMax))
					});
				}
			}
			
			if(!(upVideoSignAfter == null ||"".equals(upVideoSignAfter))){
				if ("<".equals(upVideoSignAfter)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("upLossVideoEFCMic1", new BasicDBObject("$lt", upVideoLossAfterMin)),
							new BasicDBObject("upLossVideoEFCMic2", new BasicDBObject("$lt", upVideoLossAfterMin))});
				}else if (">".equals(upVideoSignAfter)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("upLossVideoEFCMic1", new BasicDBObject("$gt", upVideoLossAfterMax)),
							new BasicDBObject("upLossVideoEFCMic2", new BasicDBObject("$gt", upVideoLossAfterMax))});
				}else if("between".equals(upVideoSignAfter)){
					dbo.put("$or", new BasicDBObject[]{
							new BasicDBObject("upLossVideoEFCMic1", new BasicDBObject("$gte", upVideoLossBeforeMin).append("$lte", upVideoLossAfterMax)),
							new BasicDBObject("upLossVideoEFCMic2", new BasicDBObject("$gte", upVideoLossBeforeMin).append("$lte", upVideoLossAfterMax))
					});
				}
			}
			
			if(!(downVideoSignBefore == null ||"".equals(downVideoSignBefore))){
				if ("<".equals(downVideoSignBefore)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("downLossVideoMic1", new BasicDBObject("$lt", downVideoLossBeforeMin)),
							new BasicDBObject("downLossVideoMic2", new BasicDBObject("$lt", downVideoLossBeforeMin))});
				}else if (">".equals(downVideoSignBefore)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("downLossVideoMic1", new BasicDBObject("$gt", downVideoLossBeforeMax)),
							new BasicDBObject("downLossVideoMic2", new BasicDBObject("$gt", downVideoLossBeforeMax))});
				}else if("between".equals(downVideoSignBefore)){
					dbo.put("$or", new BasicDBObject[]{
							new BasicDBObject("downLossVideoMic1", new BasicDBObject("$gte", downVideoLossBeforeMin).append("$lte", downVideoLossBeforeMax)),
							new BasicDBObject("downLossVideoMic2", new BasicDBObject("$gte", downVideoLossBeforeMin).append("$lte", downVideoLossBeforeMax))
					});
				}
			}
			
			if(!(downVideoSignAfter == null ||"".equals(downVideoSignAfter))){
				if ("<".equals(downVideoSignAfter)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("downLossVideoEFCMic1", new BasicDBObject("$lt", downVideoLossAfterMin)),
							new BasicDBObject("downLossVideoEFCMic2", new BasicDBObject("$lt", downVideoLossAfterMin))});
				}else if (">".equals(downVideoSignAfter)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("downLossVideoEFCMic1", new BasicDBObject("$gt", downVideoLossAfterMax)),
							new BasicDBObject("downLossVideoEFCMic2", new BasicDBObject("$gt", downVideoLossAfterMax))});
				}else if("between".equals(downVideoSignAfter)){
					dbo.put("$or", new BasicDBObject[]{
							new BasicDBObject("downLossVideoEFCMic1", new BasicDBObject("$gte", downVideoLossAfterMin).append("$lte", downVideoLossAfterMax)),
							new BasicDBObject("downLossVideoEFCMic2", new BasicDBObject("$gte", downVideoLossAfterMin).append("$lte", downVideoLossAfterMax))
					});
				}
			}
			
			if(!(upAudioSignBefore == null ||"".equals(upAudioSignBefore))){
				if ("<".equals(upAudioSignBefore)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("upLossAudioMic1", new BasicDBObject("$lt", upAudioLossBeforeMin)),
							new BasicDBObject("upLossAudioMic2", new BasicDBObject("$lt", upAudioLossBeforeMin))});
				}else if (">".equals(upAudioSignBefore)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("upLossAudioMic1", new BasicDBObject("$gt", upAudioLossBeforeMax)),
							new BasicDBObject("upLossAudioMic2", new BasicDBObject("$gt", upAudioLossBeforeMax))});
				}else if("between".equals(upAudioSignBefore)){
					dbo.put("$or", new BasicDBObject[]{
							new BasicDBObject("upLossAudioMic1", new BasicDBObject("$gte", upAudioLossBeforeMin).append("$lte", upAudioLossBeforeMax)),
							new BasicDBObject("upLossAudioMic2", new BasicDBObject("$gte", upAudioLossBeforeMin).append("$lte", upAudioLossBeforeMax))
					});
				}
			}
			
			if(!(upAudioSignAfter == null ||"".equals(upAudioSignAfter))){
				if ("<".equals(upAudioSignAfter)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("upLossAudioEFCMic1", new BasicDBObject("$lt", upAudioLossAfterMin)),
							new BasicDBObject("upLossAudioEFCMic2", new BasicDBObject("$lt", upAudioLossAfterMin))});
				}else if (">".equals(upAudioSignAfter)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("upLossAudioEFCMic1", new BasicDBObject("$gt", upAudioLossAfterMax)),
							new BasicDBObject("upLossAudioEFCMic2", new BasicDBObject("$gt", upAudioLossAfterMax))});
				}else if("between".equals(upAudioSignAfter)){
					dbo.put("$or", new BasicDBObject[]{
							new BasicDBObject("upLossAudioEFCMic1", new BasicDBObject("$gte", upAudioLossAfterMin).append("$lte", upAudioLossAfterMax)),
							new BasicDBObject("upLossAudioEFCMic2", new BasicDBObject("$gte", upAudioLossAfterMin).append("$lte", upAudioLossAfterMax))
					});
				}
			}
			
			if(!(downAudioSignBefore == null ||"".equals(downAudioSignBefore))){
				if ("<".equals(downAudioSignBefore)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("downLossAudioMic1", new BasicDBObject("$lt", downAudioLossBeforeMin)),
							new BasicDBObject("downLossAudioMic2", new BasicDBObject("$lt", downAudioLossBeforeMin))});
				}else if (">".equals(downAudioSignBefore)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("downLossAudioMic1", new BasicDBObject("$gt", downAudioLossBeforeMax)),
							new BasicDBObject("downLossAudioMic2", new BasicDBObject("$gt", downAudioLossBeforeMax))});
				}else if("between".equals(downAudioSignBefore)){
					dbo.put("$or", new BasicDBObject[]{
							new BasicDBObject("downLossAudioMic1", new BasicDBObject("$gte", downAudioLossBeforeMin).append("$lte", downAudioLossBeforeMax)),
							new BasicDBObject("downLossAudioMic2", new BasicDBObject("$gte", downAudioLossBeforeMin).append("$lte", downAudioLossBeforeMax))
					});
				}
			}
			
			if(!(downAudioSignAfter == null ||"".equals(downAudioSignAfter))){
				if ("<".equals(downAudioSignAfter)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("downLossAudioEFCMic1", new BasicDBObject("$lt", downAudioLossAfterMin)),
							new BasicDBObject("downLossAudioEFCMic2", new BasicDBObject("$lt", downAudioLossAfterMin))});
				}else if (">".equals(downAudioSignAfter)) {
					dbo.put("$or", new BasicDBObject[]{new BasicDBObject("downLossAudioEFCMic1", new BasicDBObject("$gt", downAudioLossAfterMax)),
							new BasicDBObject("downLossAudioEFCMic2", new BasicDBObject("$gt", downAudioLossAfterMax))});
				}else if("between".equals(downAudioSignAfter)){
					dbo.put("$or", new BasicDBObject[]{
							new BasicDBObject("downLossAudioEFCMic1", new BasicDBObject("$gte", downAudioLossAfterMin).append("$lte", downAudioLossAfterMax)),
							new BasicDBObject("downLossAudioEFCMic2", new BasicDBObject("$gte", downAudioLossAfterMin).append("$lte", downAudioLossAfterMax))
					});
				}
			}
			
			DBObject orderBy = new BasicDBObject("timeStamp", -1);
			int skip = (currPage-1)*pageSize;
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoSummaryEntities(dbCurToList(cur));
			result.setCount(count);
			cur.close();
			return result;
		} catch (Exception e) {
			logger.error("检索 findByStream：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}
	
	@Override
	public MeetingInfoSummaryResult findByTime(long startTime, long endTime,
			String ducationsign, long ducationMin, long ducationMax,
			int pageSize, int currPage) {
		pageSize *= 2;
		MeetingInfoSummaryResult result = new MeetingInfoSummaryResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFOSUMMARY_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("startTime", new BasicDBObject("$gte", startTime).append("$lt", endTime));
		
			if(!(ducationsign == null ||"".equals(ducationsign))){
				if ("<".equals(ducationsign)) {
					dbo.put("duration", new BasicDBObject("$lt",ducationMin));
				}else if (">".equals(ducationsign)) {
					dbo.put("duration", new BasicDBObject("$gt",ducationMax));
				}else if("between".equals(ducationsign)){
					dbo.put("duration", new BasicDBObject("$gte",ducationMin).append("$lte", ducationMax));
				}
			}
			
			DBObject orderBy = new BasicDBObject("startTime", -1);
			int skip = (currPage-1)*pageSize;
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoSummaryEntities(dbCurToList(cur));
			result.setCount(count);
			cur.close();
			return result;
		} catch (Exception e) {
			logger.error("检索 findByTime：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}
	
	private MeetingInfoSummaryEntity getMeetingInfoSummaryEntity(DBObject dbObject){
		MeetingInfoSummaryEntity responseEntity = new MeetingInfoSummaryEntity();
//		responseEntity.setDownLossAudioEFCMic1(Integer.parseInt(dbObject.get("downLossAudioEFCMic1")==null?"0":dbObject.get("downLossAudioEFCMic1").toString()));
//		responseEntity.setDownLossAudioEFCMic1(Integer.parseInt(dbObject.get("downLossAudioEFCMic2")==null?"0":dbObject.get("downLossAudioEFCMic2").toString()));
//		responseEntity.setDownLossAudioMic1(Integer.parseInt(dbObject.get("downLossAudioMic1")==null?"0":dbObject.get("downLossAudioMic1").toString()));
//		responseEntity.setDownLossAudioMic2(Integer.parseInt(dbObject.get("downLossAudioMic2")==null?"0":dbObject.get("downLossAudioMic2").toString()));
//		responseEntity.setDownLossVideoEFCMic1(Integer.parseInt(dbObject.get("downLossVideoEFCMic1")==null?"0":dbObject.get("downLossVideoEFCMic1").toString()));
//		responseEntity.setDownLossVideoEFCMic2(Integer.parseInt(dbObject.get("downLossVideoEFCMic2")==null?"0":dbObject.get("downLossVideoEFCMic2").toString()));
//		responseEntity.setDownLossVideoMic1(Integer.parseInt(dbObject.get("downLossVideoMic1")==null?"0":dbObject.get("downLossVideoMic1").toString()));
//		responseEntity.setDownLossVideoMic2(Integer.parseInt(dbObject.get("downLossVideoMic2")==null?"0":dbObject.get("downLossVideoMic2").toString()));
		responseEntity.setMeetingId(Integer.parseInt(dbObject.get("meetingId")==null?"0":dbObject.get("meetingId").toString()));	
		responseEntity.setTimeStamp((Long.parseLong(dbObject.get("startTime")==null?"0":dbObject.get("startTime").toString()))*1000);
//		responseEntity.setUpLossAudioEFCMic1(Integer.parseInt(dbObject.get("upLossAudioEFCMic1")==null?"0":dbObject.get("upLossAudioEFCMic1").toString()));
//		responseEntity.setUpLossAudioEFCMic2(Integer.parseInt(dbObject.get("upLossAudioEFCMic2")==null?"0":dbObject.get("upLossAudioEFCMic2").toString()));
//		responseEntity.setUpLossAudioMic1(Integer.parseInt(dbObject.get("upLossAudioMic1")==null?"0":dbObject.get("upLossAudioMic1").toString()));
//		responseEntity.setUpLossAudioMic2(Integer.parseInt(dbObject.get("upLossAudioMic2")==null?"0":dbObject.get("upLossAudioMic2").toString()));
//		responseEntity.setUpLossVideoEFCMic1(Integer.parseInt(dbObject.get("upLossVideoEFCMic1")==null?"0":dbObject.get("upLossVideoEFCMic1").toString()));
//		responseEntity.setUpLossVideoEFCMic2(Integer.parseInt(dbObject.get("upLossVideoEFCMic2")==null?"0":dbObject.get("upLossVideoEFCMic2").toString()));
//		responseEntity.setUpLossVideoMic1(Integer.parseInt(dbObject.get("upLossVideoMic1")==null?"0":dbObject.get("upLossVideoMic1").toString()));
//		responseEntity.setUpLossVideoMic2(Integer.parseInt(dbObject.get("upLossVideoMic2")==null?"0":dbObject.get("upLossVideoMic2").toString()));
		responseEntity.setUserCount(Integer.parseInt(dbObject.get("userCount")==null?"0":dbObject.get("userCount").toString()));
		responseEntity.setDuration(Long.parseLong(dbObject.get("duration")==null?"0":dbObject.get("duration").toString()));
//		responseEntity.setUpLossVideoFinalMic1(Integer.parseInt(dbObject.get("upLossVideoFinalMic1")==null?"0":dbObject.get("upLossVideoFinalMic1").toString()));
//		responseEntity.setDownLossVideoFinalMic1(Integer.parseInt(dbObject.get("downLossVideoFinalMic1")==null?"0":dbObject.get("downLossVideoFinalMic1").toString()));
//		responseEntity.setUpLossVideoFinalMic2(Integer.parseInt(dbObject.get("upLossVideoFinalMic2")==null?"0":dbObject.get("upLossVideoFinalMic2").toString()));
//		responseEntity.setDownLossVideoFinalMic2(Integer.parseInt(dbObject.get("downLossVideoFinalMic2")==null?"0":dbObject.get("downLossVideoFinalMic2").toString()));
//		responseEntity.setUpLossAudioFinalMic1(Integer.parseInt(dbObject.get("upLossAudioFinalMic1")==null?"0":dbObject.get("upLossAudioFinalMic1").toString()));
//		responseEntity.setDownLossAudioFinalMic1(Integer.parseInt(dbObject.get("downLossAudioFinalMic1")==null?"0":dbObject.get("downLossAudioFinalMic1").toString()));
//		responseEntity.setUpLossAudioFinalMic2(Integer.parseInt(dbObject.get("upLossAudioFinalMic2")==null?"0":dbObject.get("upLossAudioFinalMic2").toString()));
//		responseEntity.setDownLossAudioFinalMic2(Integer.parseInt(dbObject.get("downLossAudioFinalMic2")==null?"0":dbObject.get("downLossAudioFinalMic2").toString()));
//		responseEntity.setLossVideoMic1CC(Integer.parseInt(dbObject.get("lossVideoMic1CC")==null?"0":dbObject.get("lossVideoMic1CC").toString()));
//		responseEntity.setLossVideoEFCMic1CC(Integer.parseInt(dbObject.get("lossVideoEFCMic1CC")==null?"0":dbObject.get("lossVideoEFCMic1CC").toString()));
//		responseEntity.setLossVideoMic1FinalCC(Integer.parseInt(dbObject.get("lossVideoMic1FinalCC")==null?"0":dbObject.get("lossVideoMic1FinalCC").toString()));
//		responseEntity.setLossVideoMic2CC(Integer.parseInt(dbObject.get("lossVideoMic2CC")==null?"0":dbObject.get("lossVideoMic2CC").toString()));
//		responseEntity.setLossVideoEFCMic2CC(Integer.parseInt(dbObject.get("lossVideoEFCMic2CC")==null?"0":dbObject.get("lossVideoEFCMic2CC").toString()));
//		responseEntity.setLossVideoMic2FinalCC(Integer.parseInt(dbObject.get("lossVideoMic2FinalCC")==null?"0":dbObject.get("lossVideoMic2FinalCC").toString()));
//		responseEntity.setLossAudioMic1CC(Integer.parseInt(dbObject.get("lossAudioMic1CC")==null?"0":dbObject.get("lossAudioMic1CC").toString()));
//		responseEntity.setLossAudioEFCMic1CC(Integer.parseInt(dbObject.get("lossAudioEFCMic1CC")==null?"0":dbObject.get("lossAudioEFCMic1CC").toString()));
//		responseEntity.setLossAudioMic1FinalCC(Integer.parseInt(dbObject.get("lossAudioMic1FinalCC")==null?"0":dbObject.get("lossAudioMic1FinalCC").toString()));
//		responseEntity.setLossAudioMic2CC(Integer.parseInt(dbObject.get("lossAudioMic2CC")==null?"0":dbObject.get("lossAudioMic2CC").toString()));
//		responseEntity.setLossAudioEFCMic2CC(Integer.parseInt(dbObject.get("lossAudioEFCMic2CC")==null?"0":dbObject.get("lossAudioEFCMic2CC").toString()));
//		responseEntity.setLossAudioMic2FinalCC(Integer.parseInt(dbObject.get("lossAudioMic2FinalCC")==null?"0":dbObject.get("lossAudioMic2FinalCC").toString()));
		
		
		String relayIds = dbObject.get("relayIdList")==null?"0":dbObject.get("relayIdList").toString();
		if (!"".equals(relayIds)) {
			List<String> relayIdList = getList(relayIds);
			responseEntity.setRelayIdList(relayIdList);
		}else{
			responseEntity.setRelayIdList(null);
		}
		String userIds = dbObject.get("userIdList")==null?"0":dbObject.get("userIdList").toString();
		if (!"".equals(userIds)) {
			List<String> userIdList = getList(userIds);
			responseEntity.setUserIdList(userIdList);
		}else{
			responseEntity.setUserIdList(null);
		}
		
//		String speakerIdMic1s = dbObject.get("spearkIdListMic1")==null?"0":dbObject.get("spearkIdListMic1").toString();
//		if (!"".equals(speakerIdMic1s)) {
//			List<String> speakerIdListMic1 = getList(speakerIdMic1s);
//			responseEntity.setSpeakerIdListMic1(speakerIdListMic1);
//		}else{
//			responseEntity.setSpeakerIdListMic1(null);
//		}
//		String speakerIdMic2s = dbObject.get("spearkIdListMic2")==null?"0":dbObject.get("spearkIdListMic2").toString();
//		if (!"".equals(speakerIdMic2s)) {
//			List<String> speakerIdListMic2 = getList(speakerIdMic2s);
//			responseEntity.setSpeakerIdListMic2(speakerIdListMic2);
//		}else{
//			responseEntity.setSpeakerIdListMic2(null);
//		}
		String speakerIds = dbObject.get("spearkIdList")==null?"0":dbObject.get("spearkIdList").toString();
		if (!"".equals(userIds)) {
			List<String> speakerIdList = getList(speakerIds);
			responseEntity.setSpeakerIdList(speakerIdList);
		}else{
			responseEntity.setSpeakerIdList(null);
		}
		
		responseEntity.setEndTime((Long.parseLong(dbObject.get("endTime")==null?"0":dbObject.get("endTime").toString()))*1000);
		responseEntity.setC2cquality(dbObject.get("c2cquality")==null?"0":dbObject.get("c2cquality").toString());
		responseEntity.setQosTableName(dbObject.get("qosTableName")==null?"0":dbObject.get("qosTableName").toString());
		return responseEntity;
	}
	
	private List<String> getList(String data){
		List<String> dataList = new ArrayList<String>();
		String[] strings = data.split("_");
		for(String e:strings)
		{
			dataList.add(e);
		}
		return dataList;
	}
	
	private List<MeetingInfoSummaryEntity> dbCurToList(DBCursor cursor) {
		List<MeetingInfoSummaryEntity> list = new ArrayList<MeetingInfoSummaryEntity>();
		MeetingInfoSummaryEntity entity = null;
		while (cursor.hasNext()) {
			//test
			DBObject dbObject = cursor.next();
			System.out.println("entity:"+ dbObject.toString());
			if (dbObject != null) {
				try {
					entity = getMeetingInfoSummaryEntity(dbObject);
				} catch (Exception e) {
					logger.error(" dbCurToList：",e);
				}
				if(null != entity && entity.getUserCount() != 0 /*&& !isAllRelay(entity.getUserIdList())*/){
					list.add(entity);
				}	
			}
			
			
			//正式版
			//MeetingInfoSummaryEntity entity = getMeetingInfoSummaryEntity(cursor.next());
			//list.add(entity);
		}
		cursor.close();
		return list;
	}
	
	private boolean isAllRelay(List<String> userList){
		boolean isAllRelay = false;
		if (userList == null || userList.isEmpty()) {
			return isAllRelay = true;
		}else {
			for (String userId : userList) {
				if(Integer.parseInt(userId) > 9999999){
					return isAllRelay;
				}else {
					continue;
				}
			}
			return isAllRelay = true;
		}
	}
//	private List<MeetingInfoSummaryEntity> dbCurToList(DBCursor cursor) {
//		List<MeetingInfoSummaryEntity> list = new ArrayList<MeetingInfoSummaryEntity>();
//		while (cursor.hasNext()) {
//			//test
//			DBObject dbObject = cursor.next();
//			System.out.println("entity:"+ dbObject.toString());
//			if (dbObject != null) {
//				MeetingInfoSummaryEntity entity = getMeetingInfoSummaryEntity(dbObject);
//				list.add(entity);
//			}
//			
//			
//			//正式版
//			//MeetingInfoSummaryEntity entity = getMeetingInfoSummaryEntity(cursor.next());
//			//list.add(entity);
//		}
//		cursor.close();
//		return list;
//	}
	@Override
	public MeetingInfoSummaryResult findByVideoStream(long startTime,
			long endTime, String upVideoSignBefore, float upVideoLossBeforeMin,
			float upVideoLossBeforeMax, String upVideoSignAfter,
			float upVideoLossAfterMin, float upVideoLossAfterMax,
			String upVideoSignFinal, float upVideoLossFinalMin,
			float upVideoLossFinalMax, String downVideoSignBefore,
			float downVideoLossBeforeMin, float downVideoLossBeforeMax,
			String downVideoSignAfter, float downVideoLossAfterMin,
			float downVideoLossAfterMax, String downVideoSignFinal,
			float downVideoLossFinalMin, float downVideoLossFinalMax,
			String videoSignBefore, float videoLossBeforeMin,
			float videoLossBeforeMax, String videoSignAfter,
			float videoLossAfterMin, float videoLossAfterMax,
			String videoSignFinal, float videoLossFinalMin,
			float videoLossFinalMax, int pageSize, int currPage) {
		pageSize *= 2;
		MeetingInfoSummaryResult result = new MeetingInfoSummaryResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFOSUMMARY_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("startTime", new BasicDBObject("$gte", startTime).append("$lt", endTime));
			
			if(!(upVideoSignBefore == null ||"".equals(upVideoSignBefore))){
				if ("<".equals(upVideoSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossVideoMic1", new BasicDBObject("$lt", upVideoLossBeforeMin)),
							new BasicDBObject("upLossVideoMic2", new BasicDBObject("$lt", upVideoLossBeforeMin))});
				}else if (">".equals(upVideoSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossVideoMic1", new BasicDBObject("$gt", upVideoLossBeforeMax)),
							new BasicDBObject("upLossVideoMic2", new BasicDBObject("$gt", upVideoLossBeforeMax))});
				}else if("between".equals(upVideoSignBefore)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("upLossVideoMic1", new BasicDBObject("$gte", upVideoLossBeforeMin).append("$lte", upVideoLossBeforeMax)),
							new BasicDBObject("upLossVideoMic2", new BasicDBObject("$gte", upVideoLossBeforeMin).append("$lte", upVideoLossBeforeMax))
					});
				}
			}
			
			if(!(upVideoSignAfter == null ||"".equals(upVideoSignAfter))){
				if ("<".equals(upVideoSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossVideoEFCMic1", new BasicDBObject("$lt", upVideoLossAfterMin)),
							new BasicDBObject("upLossVideoEFCMic2", new BasicDBObject("$lt", upVideoLossAfterMin))});
				}else if (">".equals(upVideoSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossVideoEFCMic1", new BasicDBObject("$gt", upVideoLossAfterMax)),
							new BasicDBObject("upLossVideoEFCMic2", new BasicDBObject("$gt", upVideoLossAfterMax))});
				}else if("between".equals(upVideoSignAfter)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("upLossVideoEFCMic1", new BasicDBObject("$gte", upVideoLossBeforeMin).append("$lte", upVideoLossAfterMax)),
							new BasicDBObject("upLossVideoEFCMic2", new BasicDBObject("$gte", upVideoLossBeforeMin).append("$lte", upVideoLossAfterMax))
					});
				}
			}
			
			if(!(upVideoSignFinal == null ||"".equals(upVideoSignFinal))){
				if ("<".equals(upVideoSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossVideoFinalMic1", new BasicDBObject("$lt", upVideoLossFinalMin)),
							new BasicDBObject("upLossVideoFinalMic2", new BasicDBObject("$lt", upVideoLossFinalMin))});
				}else if (">".equals(upVideoSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossVideoFinalMic1", new BasicDBObject("$gt", upVideoLossFinalMax)),
							new BasicDBObject("upLossVideoFinalMic2", new BasicDBObject("$gt", upVideoLossFinalMax))});
				}else if("between".equals(upVideoSignFinal)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("upLossVideoFinalMic1", new BasicDBObject("$gte", upVideoLossFinalMin).append("$lte", upVideoLossFinalMax)),
							new BasicDBObject("upLossVideoFinalMic2", new BasicDBObject("$gte", upVideoLossFinalMin).append("$lte", upVideoLossFinalMax))
					});
				}
			}
			if(!(downVideoSignBefore == null ||"".equals(downVideoSignBefore))){
				if ("<".equals(downVideoSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossVideoMic1", new BasicDBObject("$lt", downVideoLossBeforeMin)),
							new BasicDBObject("downLossVideoMic2", new BasicDBObject("$lt", downVideoLossBeforeMin))});
				}else if (">".equals(downVideoSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossVideoMic1", new BasicDBObject("$gt", downVideoLossBeforeMax)),
							new BasicDBObject("downLossVideoMic2", new BasicDBObject("$gt", downVideoLossBeforeMax))});
				}else if("between".equals(downVideoSignBefore)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("downLossVideoMic1", new BasicDBObject("$gte", downVideoLossBeforeMin).append("$lte", downVideoLossBeforeMax)),
							new BasicDBObject("downLossVideoMic2", new BasicDBObject("$gte", downVideoLossBeforeMin).append("$lte", downVideoLossBeforeMax))
					});
				}
			}
			
			if(!(downVideoSignAfter == null ||"".equals(downVideoSignAfter))){
				if ("<".equals(downVideoSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossVideoEFCMic1", new BasicDBObject("$lt", downVideoLossAfterMin)),
							new BasicDBObject("downLossVideoEFCMic2", new BasicDBObject("$lt", downVideoLossAfterMin))});
				}else if (">".equals(downVideoSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossVideoEFCMic1", new BasicDBObject("$gt", downVideoLossAfterMax)),
							new BasicDBObject("downLossVideoEFCMic2", new BasicDBObject("$gt", downVideoLossAfterMax))});
				}else if("between".equals(downVideoSignAfter)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("downLossVideoEFCMic1", new BasicDBObject("$gte", downVideoLossAfterMin).append("$lte", downVideoLossAfterMax)),
							new BasicDBObject("downLossVideoEFCMic2", new BasicDBObject("$gte", downVideoLossAfterMin).append("$lte", downVideoLossAfterMax))
					});
				}
			}
			
			if(!(downVideoSignFinal == null ||"".equals(downVideoSignFinal))){
				if ("<".equals(downVideoSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossVideoFinalMic1", new BasicDBObject("$lt", downVideoLossFinalMin)),
							new BasicDBObject("downLossVideoFinalMic2", new BasicDBObject("$lt", downVideoLossFinalMin))});
				}else if (">".equals(downVideoSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossVideoFinalMic1", new BasicDBObject("$gt", downVideoLossFinalMax)),
							new BasicDBObject("downLossVideoFinalMic2", new BasicDBObject("$gt", downVideoLossFinalMax))});
				}else if("between".equals(downVideoSignFinal)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("downLossVideoFinalMic1", new BasicDBObject("$gte", downVideoLossFinalMin).append("$lte", downVideoLossFinalMax)),
							new BasicDBObject("downLossVideoFinalMic2", new BasicDBObject("$gte", downVideoLossFinalMin).append("$lte", downVideoLossFinalMax))
					});
				}
			}
			
			if(!(videoSignBefore == null ||"".equals(videoSignBefore))){
				if ("<".equals(videoSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossVideoMic1CC", new BasicDBObject("$lt", videoLossBeforeMin)),
							new BasicDBObject("lossVideoMic2CC", new BasicDBObject("$lt", videoLossBeforeMin))});
				}else if (">".equals(videoSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossVideoMic1CC", new BasicDBObject("$gt", videoLossBeforeMax)),
							new BasicDBObject("lossVideoMic2CC", new BasicDBObject("$gt", videoLossBeforeMax))});
				}else if("between".equals(videoSignBefore)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("lossVideoMic1CC", new BasicDBObject("$gte", videoLossBeforeMin).append("$lte", videoLossBeforeMax)),
							new BasicDBObject("lossVideoMic2CC", new BasicDBObject("$gte", videoLossBeforeMin).append("$lte", videoLossBeforeMax))
					});
				}
			}
			
			if(!(videoSignAfter == null ||"".equals(videoSignAfter))){
				if ("<".equals(videoSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossVideoEFCMic1CC", new BasicDBObject("$lt", videoLossAfterMin)),
							new BasicDBObject("lossVideoEFCMic2CC", new BasicDBObject("$lt", videoLossAfterMin))});
				}else if (">".equals(videoSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossVideoEFCMic1CC", new BasicDBObject("$gt", videoLossAfterMax)),
							new BasicDBObject("lossVideoEFCMic2CC", new BasicDBObject("$gt", videoLossAfterMax))});
				}else if("between".equals(videoSignAfter)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("lossVideoEFCMic1CC", new BasicDBObject("$gte", videoLossAfterMin).append("$lte", videoLossAfterMax)),
							new BasicDBObject("lossVideoEFCMic2CC", new BasicDBObject("$gte", videoLossAfterMin).append("$lte", videoLossAfterMax))
					});
				}
			}
			
			if(!(videoSignFinal == null ||"".equals(videoSignFinal))){
				if ("<".equals(videoSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossVideoMic1FinalCC", new BasicDBObject("$lt", videoLossFinalMin)),
							new BasicDBObject("lossVideoMic2FinalCC", new BasicDBObject("$lt", videoLossFinalMin))});
				}else if (">".equals(videoSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossVideoMic1FinalCC", new BasicDBObject("$gt", videoLossFinalMax)),
							new BasicDBObject("lossVideoMic2FinalCC", new BasicDBObject("$gt", videoLossFinalMax))});
				}else if("between".equals(videoSignFinal)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("lossVideoMic1FinalCC", new BasicDBObject("$gte", videoLossFinalMin).append("$lte", videoLossFinalMax)),
							new BasicDBObject("lossVideoMic2FinalCC", new BasicDBObject("$gte", videoLossFinalMin).append("$lte", videoLossFinalMax))
					});
				}
			}
			DBObject orderBy = new BasicDBObject("startTime", -1);
			int skip = (currPage-1)*pageSize;
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoSummaryEntities(dbCurToList(cur));
			result.setCount(count);
			cur.close();
			return result;
		} catch (Exception e) {
			logger.error("检索 findByVideoStream：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}

	@Override
	public MeetingInfoSummaryResult findByAudioStream(long startTime,
			long endTime, String upAudioSignBefore, float upAudioLossBeforeMin,
			float upAudioLossBeforeMax, String upAudioSignAfter,
			float upAudioLossAfterMin, float upAudioLossAfterMax,
			String upAudioSignFinal, float upAudioLossFinalMin,
			float upAudioLossFinalMax, String downAudioSignBefore,
			float downAudioLossBeforeMin, float downAudioLossBeforeMax,
			String downAudioSignAfter, float downAudioLossAfterMin,
			float downAudioLossAfterMax, String downAudioSignFinal,
			float downAudioLossFinalMin, float downAudioLossFinalMax,
			String audioSignBefore, float audioLossBeforeMin,
			float audioLossBeforeMax, String audioSignAfter,
			float audioLossAfterMin, float audioLossAfterMax,
			String audioSignFinal, float audioLossFinalMin,
			float audioLossFinalMax, int pageSize, int currPage) {
		pageSize *= 2;
		MeetingInfoSummaryResult result = new MeetingInfoSummaryResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFOSUMMARY_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			dbo.put("startTime", new BasicDBObject("$gte", startTime).append("$lt", endTime));
			
			if(!(upAudioSignBefore == null ||"".equals(upAudioSignBefore))){
				if ("<".equals(upAudioSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossAudioMic1", new BasicDBObject("$lt", upAudioLossBeforeMin)),
							new BasicDBObject("upLossAudioMic2", new BasicDBObject("$lt", upAudioLossBeforeMin))});
				}else if (">".equals(upAudioSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossAudioMic1", new BasicDBObject("$gt", upAudioLossBeforeMax)),
							new BasicDBObject("upLossAudioMic2", new BasicDBObject("$gt", upAudioLossBeforeMax))});
				}else if("between".equals(upAudioSignBefore)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("upLossAudioMic1", new BasicDBObject("$gte", upAudioLossBeforeMin).append("$lte", upAudioLossBeforeMax)),
							new BasicDBObject("upLossAudioMic2", new BasicDBObject("$gte", upAudioLossBeforeMin).append("$lte", upAudioLossBeforeMax))
					});
				}
			}
			
			if(!(upAudioSignAfter == null ||"".equals(upAudioSignAfter))){
				if ("<".equals(upAudioSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossAudioEFCMic1", new BasicDBObject("$lt", upAudioLossAfterMin)),
							new BasicDBObject("upLossAudioEFCMic2", new BasicDBObject("$lt", upAudioLossAfterMin))});
				}else if (">".equals(upAudioSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossAudioEFCMic1", new BasicDBObject("$gt", upAudioLossAfterMax)),
							new BasicDBObject("upLossAudioEFCMic2", new BasicDBObject("$gt", upAudioLossAfterMax))});
				}else if("between".equals(upAudioSignAfter)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("upLossAudioEFCMic1", new BasicDBObject("$gte", upAudioLossBeforeMin).append("$lte", upAudioLossAfterMax)),
							new BasicDBObject("upLossAudioEFCMic2", new BasicDBObject("$gte", upAudioLossBeforeMin).append("$lte", upAudioLossAfterMax))
					});
				}
			}
			
			if(!(upAudioSignFinal == null ||"".equals(upAudioSignFinal))){
				if ("<".equals(upAudioSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossAudioFinalMic1", new BasicDBObject("$lt", upAudioLossFinalMin)),
							new BasicDBObject("upLossAudioFinalMic2", new BasicDBObject("$lt", upAudioLossFinalMin))});
				}else if (">".equals(upAudioSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossAudioFinalMic1", new BasicDBObject("$gt", upAudioLossFinalMax)),
							new BasicDBObject("upLossAudioFinalMic2", new BasicDBObject("$gt", upAudioLossFinalMax))});
				}else if("between".equals(upAudioSignFinal)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("upLossAudioFinalMic1", new BasicDBObject("$gte", upAudioLossFinalMin).append("$lte", upAudioLossFinalMax)),
							new BasicDBObject("upLossAudioFinalMic2", new BasicDBObject("$gte", upAudioLossFinalMin).append("$lte", upAudioLossFinalMax))
					});
				}
			}
			if(!(downAudioSignBefore == null ||"".equals(downAudioSignBefore))){
				if ("<".equals(downAudioSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossAudioMic1", new BasicDBObject("$lt", downAudioLossBeforeMin)),
							new BasicDBObject("downLossAudioMic2", new BasicDBObject("$lt", downAudioLossBeforeMin))});
				}else if (">".equals(downAudioSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossAudioMic1", new BasicDBObject("$gt", downAudioLossBeforeMax)),
							new BasicDBObject("downLossAudioMic2", new BasicDBObject("$gt", downAudioLossBeforeMax))});
				}else if("between".equals(downAudioSignBefore)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("downLossAudioMic1", new BasicDBObject("$gte", downAudioLossBeforeMin).append("$lte", downAudioLossBeforeMax)),
							new BasicDBObject("downLossAudioMic2", new BasicDBObject("$gte", downAudioLossBeforeMin).append("$lte", downAudioLossBeforeMax))
					});
				}
			}
			
			if(!(downAudioSignAfter == null ||"".equals(downAudioSignAfter))){
				if ("<".equals(downAudioSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossAudioEFCMic1", new BasicDBObject("$lt", downAudioLossAfterMin)),
							new BasicDBObject("downLossAudioEFCMic2", new BasicDBObject("$lt", downAudioLossAfterMin))});
				}else if (">".equals(downAudioSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossAudioEFCMic1", new BasicDBObject("$gt", downAudioLossAfterMax)),
							new BasicDBObject("downLossAudioEFCMic2", new BasicDBObject("$gt", downAudioLossAfterMax))});
				}else if("between".equals(downAudioSignAfter)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("downLossAudioEFCMic1", new BasicDBObject("$gte", downAudioLossAfterMin).append("$lte", downAudioLossAfterMax)),
							new BasicDBObject("downLossAudioEFCMic2", new BasicDBObject("$gte", downAudioLossAfterMin).append("$lte", downAudioLossAfterMax))
					});
				}
			}
			
			if(!(downAudioSignFinal == null ||"".equals(downAudioSignFinal))){
				if ("<".equals(downAudioSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossAudioFinalMic1", new BasicDBObject("$lt", downAudioLossFinalMin)),
							new BasicDBObject("downLossAudioFinalMic2", new BasicDBObject("$lt", downAudioLossFinalMin))});
				}else if (">".equals(downAudioSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossAudioFinalMic1", new BasicDBObject("$gt", downAudioLossFinalMax)),
							new BasicDBObject("downLossAudioFinalMic2", new BasicDBObject("$gt", downAudioLossFinalMax))});
				}else if("between".equals(downAudioSignFinal)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("downLossAudioFinalMic1", new BasicDBObject("$gte", downAudioLossFinalMin).append("$lte", downAudioLossFinalMax)),
							new BasicDBObject("downLossAudioFinalMic2", new BasicDBObject("$gte", downAudioLossFinalMin).append("$lte", downAudioLossFinalMax))
					});
				}
			}
			
			if(!(audioSignBefore == null ||"".equals(audioSignBefore))){
				if ("<".equals(audioSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossAudioMic1CC", new BasicDBObject("$lt", audioLossBeforeMin)),
							new BasicDBObject("lossAudioMic2CC", new BasicDBObject("$lt", audioLossBeforeMin))});
				}else if (">".equals(audioSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossAudioMic1CC", new BasicDBObject("$gt", audioLossBeforeMax)),
							new BasicDBObject("lossAudioMic2CC", new BasicDBObject("$gt", audioLossBeforeMax))});
				}else if("between".equals(audioSignBefore)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("lossAudioMic1CC", new BasicDBObject("$gte", audioLossBeforeMin).append("$lte", audioLossBeforeMax)),
							new BasicDBObject("lossAudioMic2CC", new BasicDBObject("$gte", audioLossBeforeMin).append("$lte", audioLossBeforeMax))
					});
				}
			}
			
			if(!(audioSignAfter == null ||"".equals(audioSignAfter))){
				if ("<".equals(audioSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossAudioEFCMic1CC", new BasicDBObject("$lt", audioLossAfterMin)),
							new BasicDBObject("lossAudioEFCMic2CC", new BasicDBObject("$lt", audioLossAfterMin))});
				}else if (">".equals(audioSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossAudioEFCMic1CC", new BasicDBObject("$gt", audioLossAfterMax)),
							new BasicDBObject("lossAudioEFCMic2CC", new BasicDBObject("$gt", audioLossAfterMax))});
				}else if("between".equals(audioSignAfter)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("lossAudioEFCMic1CC", new BasicDBObject("$gte", audioLossAfterMin).append("$lte", audioLossAfterMax)),
							new BasicDBObject("lossAudioEFCMic2CC", new BasicDBObject("$gte", audioLossAfterMin).append("$lte", audioLossAfterMax))
					});
				}
			}
			
			if(!(audioSignFinal == null ||"".equals(audioSignFinal))){
				if ("<".equals(audioSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossAudioMic1FinalCC", new BasicDBObject("$lt", audioLossFinalMin)),
							new BasicDBObject("lossAudioMic2FinalCC", new BasicDBObject("$lt", audioLossFinalMin))});
				}else if (">".equals(audioSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossAudioMic1FinalCC", new BasicDBObject("$gt", audioLossFinalMax)),
							new BasicDBObject("lossAudioMic2FinalCC", new BasicDBObject("$gt", audioLossFinalMax))});
				}else if("between".equals(audioSignFinal)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("lossAudioMic1FinalCC", new BasicDBObject("$gte", audioLossFinalMin).append("$lte", audioLossFinalMax)),
							new BasicDBObject("lossAudioMic2FinalCC", new BasicDBObject("$gte", audioLossFinalMin).append("$lte", audioLossFinalMax))
					});
				}
			}
			DBObject orderBy = new BasicDBObject("startTime", -1);
			int skip = (currPage-1)*pageSize;
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoSummaryEntities(dbCurToList(cur));
			result.setCount(count);
			cur.close();
			return result;
		} catch (Exception e) {
			logger.error("检索 findByAudioStream：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}

	@Override
	public MeetingInfoSummaryResult findByAll(long startTime, long endTime,
			String userId, int meetingId, String relayId,
			String attendCountSign, int attendCountMin, int attendCountMax,
			String ducationsign, long ducationMin, long ducationMax,
			String upVideoSignBefore, float upVideoLossBeforeMin,
			float upVideoLossBeforeMax, String upVideoSignAfter,
			float upVideoLossAfterMin, float upVideoLossAfterMax,
			String upVideoSignFinal, float upVideoLossFinalMin,
			float upVideoLossFinalMax, String downVideoSignBefore,
			float downVideoLossBeforeMin, float downVideoLossBeforeMax,
			String downVideoSignAfter, float downVideoLossAfterMin,
			float downVideoLossAfterMax, String downVideoSignFinal,
			float downVideoLossFinalMin, float downVideoLossFinalMax,
			String videoSignBefore, float videoLossBeforeMin,
			float videoLossBeforeMax, String videoSignAfter,
			float videoLossAfterMin, float videoLossAfterMax,
			String videoSignFinal, float videoLossFinalMin,
			float videoLossFinalMax, String upAudioSignBefore,
			float upAudioLossBeforeMin, float upAudioLossBeforeMax,
			String upAudioSignAfter, float upAudioLossAfterMin,
			float upAudioLossAfterMax, String upAudioSignFinal,
			float upAudioLossFinalMin, float upAudioLossFinalMax,
			String downAudioSignBefore, float downAudioLossBeforeMin,
			float downAudioLossBeforeMax, String downAudioSignAfter,
			float downAudioLossAfterMin, float downAudioLossAfterMax,
			String downAudioSignFinal, float downAudioLossFinalMin,
			float downAudioLossFinalMax, String audioSignBefore,
			float audioLossBeforeMin, float audioLossBeforeMax,
			String audioSignAfter, float audioLossAfterMin,
			float audioLossAfterMax, String audioSignFinal,
			float audioLossFinalMin, float audioLossFinalMax, int pageSize,
			int currPage) {
		pageSize *= 2;
		MeetingInfoSummaryResult result = new MeetingInfoSummaryResult();
		try {
			DBCollection employee = db.getCollection(MEETINGINFOSUMMARY_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			
			//固定时间
			dbo.put("startTime", new BasicDBObject("$gte", startTime).append("$lt", endTime));
		
			//ID
			if (!(userId == null||"".equals(userId))) {
				Pattern pattern = Pattern.compile("^.*" + userId+ ".*$", Pattern.CASE_INSENSITIVE); 
				dbo.put("userIdList", pattern);
			}			
			if (meetingId > 0) {
				dbo.put("meetingId", meetingId);
			}
			if (!"".equals(relayId)) {
				Pattern pattern = Pattern.compile("^.*" + relayId+ ".*$", Pattern.CASE_INSENSITIVE); 
				dbo.put("relayIdList", pattern);	
			}
			
			//参会人数
			if(!(attendCountSign == null ||"".equals(attendCountSign))){
				if ("<".equals(attendCountSign)) {
					dbo.put("userCount", new BasicDBObject("$lt",attendCountMin));
				}else if (">".equals(attendCountSign)) {
					dbo.put("userCount", new BasicDBObject("$gt",attendCountMax));
				}else if("between".equals(attendCountSign)){
					dbo.put("userCount", new BasicDBObject("$gte",attendCountMin).append("$lte", attendCountMax));
				}
			}
			
			
			//会议时长
			if(!(ducationsign == null ||"".equals(ducationsign))){
				if ("<".equals(ducationsign)) {
					dbo.put("duration", new BasicDBObject("$lt",ducationMin));
				}else if (">".equals(ducationsign)) {
					dbo.put("duration", new BasicDBObject("$gt",ducationMax));
				}else if("between".equals(ducationsign)){
					dbo.put("duration", new BasicDBObject("$gte",ducationMin).append("$lte", ducationMax));
				}
			}
			
			
			//视频流统计
			if(!(upVideoSignBefore == null ||"".equals(upVideoSignBefore))){
				if ("<".equals(upVideoSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossVideoMic1", new BasicDBObject("$lt", upVideoLossBeforeMin)),
							new BasicDBObject("upLossVideoMic2", new BasicDBObject("$lt", upVideoLossBeforeMin))});
				}else if (">".equals(upVideoSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossVideoMic1", new BasicDBObject("$gt", upVideoLossBeforeMax)),
							new BasicDBObject("upLossVideoMic2", new BasicDBObject("$gt", upVideoLossBeforeMax))});
				}else if("between".equals(upVideoSignBefore)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("upLossVideoMic1", new BasicDBObject("$gte", upVideoLossBeforeMin).append("$lte", upVideoLossBeforeMax)),
							new BasicDBObject("upLossVideoMic2", new BasicDBObject("$gte", upVideoLossBeforeMin).append("$lte", upVideoLossBeforeMax))
					});
				}
			}
			
			if(!(upVideoSignAfter == null ||"".equals(upVideoSignAfter))){
				if ("<".equals(upVideoSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossVideoEFCMic1", new BasicDBObject("$lt", upVideoLossAfterMin)),
							new BasicDBObject("upLossVideoEFCMic2", new BasicDBObject("$lt", upVideoLossAfterMin))});
				}else if (">".equals(upVideoSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossVideoEFCMic1", new BasicDBObject("$gt", upVideoLossAfterMax)),
							new BasicDBObject("upLossVideoEFCMic2", new BasicDBObject("$gt", upVideoLossAfterMax))});
				}else if("between".equals(upVideoSignAfter)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("upLossVideoEFCMic1", new BasicDBObject("$gte", upVideoLossBeforeMin).append("$lte", upVideoLossAfterMax)),
							new BasicDBObject("upLossVideoEFCMic2", new BasicDBObject("$gte", upVideoLossBeforeMin).append("$lte", upVideoLossAfterMax))
					});
				}
			}
			
			if(!(upVideoSignFinal == null ||"".equals(upVideoSignFinal))){
				if ("<".equals(upVideoSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossVideoFinalMic1", new BasicDBObject("$lt", upVideoLossFinalMin)),
							new BasicDBObject("upLossVideoFinalMic2", new BasicDBObject("$lt", upVideoLossFinalMin))});
				}else if (">".equals(upVideoSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossVideoFinalMic1", new BasicDBObject("$gt", upVideoLossFinalMax)),
							new BasicDBObject("upLossVideoFinalMic2", new BasicDBObject("$gt", upVideoLossFinalMax))});
				}else if("between".equals(upVideoSignFinal)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("upLossVideoFinalMic1", new BasicDBObject("$gte", upVideoLossFinalMin).append("$lte", upVideoLossFinalMax)),
							new BasicDBObject("upLossVideoFinalMic2", new BasicDBObject("$gte", upVideoLossFinalMin).append("$lte", upVideoLossFinalMax))
					});
				}
			}
			if(!(downVideoSignBefore == null ||"".equals(downVideoSignBefore))){
				if ("<".equals(downVideoSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossVideoMic1", new BasicDBObject("$lt", downVideoLossBeforeMin)),
							new BasicDBObject("downLossVideoMic2", new BasicDBObject("$lt", downVideoLossBeforeMin))});
				}else if (">".equals(downVideoSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossVideoMic1", new BasicDBObject("$gt", downVideoLossBeforeMax)),
							new BasicDBObject("downLossVideoMic2", new BasicDBObject("$gt", downVideoLossBeforeMax))});
				}else if("between".equals(downVideoSignBefore)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("downLossVideoMic1", new BasicDBObject("$gte", downVideoLossBeforeMin).append("$lte", downVideoLossBeforeMax)),
							new BasicDBObject("downLossVideoMic2", new BasicDBObject("$gte", downVideoLossBeforeMin).append("$lte", downVideoLossBeforeMax))
					});
				}
			}
			
			if(!(downVideoSignAfter == null ||"".equals(downVideoSignAfter))){
				if ("<".equals(downVideoSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossVideoEFCMic1", new BasicDBObject("$lt", downVideoLossAfterMin)),
							new BasicDBObject("downLossVideoEFCMic2", new BasicDBObject("$lt", downVideoLossAfterMin))});
				}else if (">".equals(downVideoSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossVideoEFCMic1", new BasicDBObject("$gt", downVideoLossAfterMax)),
							new BasicDBObject("downLossVideoEFCMic2", new BasicDBObject("$gt", downVideoLossAfterMax))});
				}else if("between".equals(downVideoSignAfter)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("downLossVideoEFCMic1", new BasicDBObject("$gte", downVideoLossAfterMin).append("$lte", downVideoLossAfterMax)),
							new BasicDBObject("downLossVideoEFCMic2", new BasicDBObject("$gte", downVideoLossAfterMin).append("$lte", downVideoLossAfterMax))
					});
				}
			}
			
			if(!(downVideoSignFinal == null ||"".equals(downVideoSignFinal))){
				if ("<".equals(downVideoSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossVideoFinalMic1", new BasicDBObject("$lt", downVideoLossFinalMin)),
							new BasicDBObject("downLossVideoFinalMic2", new BasicDBObject("$lt", downVideoLossFinalMin))});
				}else if (">".equals(downVideoSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossVideoFinalMic1", new BasicDBObject("$gt", downVideoLossFinalMax)),
							new BasicDBObject("downLossVideoFinalMic2", new BasicDBObject("$gt", downVideoLossFinalMax))});
				}else if("between".equals(downVideoSignFinal)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("downLossVideoFinalMic1", new BasicDBObject("$gte", downVideoLossFinalMin).append("$lte", downVideoLossFinalMax)),
							new BasicDBObject("downLossVideoFinalMic2", new BasicDBObject("$gte", downVideoLossFinalMin).append("$lte", downVideoLossFinalMax))
					});
				}
			}
			
			if(!(videoSignBefore == null ||"".equals(videoSignBefore))){
				if ("<".equals(videoSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossVideoMic1CC", new BasicDBObject("$lt", videoLossBeforeMin)),
							new BasicDBObject("lossVideoMic2CC", new BasicDBObject("$lt", videoLossBeforeMin))});
				}else if (">".equals(videoSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossVideoMic1CC", new BasicDBObject("$gt", videoLossBeforeMax)),
							new BasicDBObject("lossVideoMic2CC", new BasicDBObject("$gt", videoLossBeforeMax))});
				}else if("between".equals(videoSignBefore)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("lossVideoMic1CC", new BasicDBObject("$gte", videoLossBeforeMin).append("$lte", videoLossBeforeMax)),
							new BasicDBObject("lossVideoMic2CC", new BasicDBObject("$gte", videoLossBeforeMin).append("$lte", videoLossBeforeMax))
					});
				}
			}
			
			if(!(videoSignAfter == null ||"".equals(videoSignAfter))){
				if ("<".equals(videoSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossVideoEFCMic1CC", new BasicDBObject("$lt", videoLossAfterMin)),
							new BasicDBObject("lossVideoEFCMic2CC", new BasicDBObject("$lt", videoLossAfterMin))});
				}else if (">".equals(videoSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossVideoEFCMic1CC", new BasicDBObject("$gt", videoLossAfterMax)),
							new BasicDBObject("lossVideoEFCMic2CC", new BasicDBObject("$gt", videoLossAfterMax))});
				}else if("between".equals(videoSignAfter)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("lossVideoEFCMic1CC", new BasicDBObject("$gte", videoLossAfterMin).append("$lte", videoLossAfterMax)),
							new BasicDBObject("lossVideoEFCMic2CC", new BasicDBObject("$gte", videoLossAfterMin).append("$lte", videoLossAfterMax))
					});
				}
			}
			
			if(!(videoSignFinal == null ||"".equals(videoSignFinal))){
				if ("<".equals(videoSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossVideoMic1FinalCC", new BasicDBObject("$lt", videoLossFinalMin)),
							new BasicDBObject("lossVideoMic2FinalCC", new BasicDBObject("$lt", videoLossFinalMin))});
				}else if (">".equals(videoSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossVideoMic1FinalCC", new BasicDBObject("$gt", videoLossFinalMax)),
							new BasicDBObject("lossVideoMic2FinalCC", new BasicDBObject("$gt", videoLossFinalMax))});
				}else if("between".equals(videoSignFinal)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("lossVideoMic1FinalCC", new BasicDBObject("$gte", videoLossFinalMin).append("$lte", videoLossFinalMax)),
							new BasicDBObject("lossVideoMic2FinalCC", new BasicDBObject("$gte", videoLossFinalMin).append("$lte", videoLossFinalMax))
					});
				}
			}
			
			//音频流统计
			if(!(upAudioSignBefore == null ||"".equals(upAudioSignBefore))){
				if ("<".equals(upAudioSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossAudioMic1", new BasicDBObject("$lt", upAudioLossBeforeMin)),
							new BasicDBObject("upLossAudioMic2", new BasicDBObject("$lt", upAudioLossBeforeMin))});
				}else if (">".equals(upAudioSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossAudioMic1", new BasicDBObject("$gt", upAudioLossBeforeMax)),
							new BasicDBObject("upLossAudioMic2", new BasicDBObject("$gt", upAudioLossBeforeMax))});
				}else if("between".equals(upAudioSignBefore)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("upLossAudioMic1", new BasicDBObject("$gte", upAudioLossBeforeMin).append("$lte", upAudioLossBeforeMax)),
							new BasicDBObject("upLossAudioMic2", new BasicDBObject("$gte", upAudioLossBeforeMin).append("$lte", upAudioLossBeforeMax))
					});
				}
			}
			
			if(!(upAudioSignAfter == null ||"".equals(upAudioSignAfter))){
				if ("<".equals(upAudioSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossAudioEFCMic1", new BasicDBObject("$lt", upAudioLossAfterMin)),
							new BasicDBObject("upLossAudioEFCMic2", new BasicDBObject("$lt", upAudioLossAfterMin))});
				}else if (">".equals(upAudioSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossAudioEFCMic1", new BasicDBObject("$gt", upAudioLossAfterMax)),
							new BasicDBObject("upLossAudioEFCMic2", new BasicDBObject("$gt", upAudioLossAfterMax))});
				}else if("between".equals(upAudioSignAfter)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("upLossAudioEFCMic1", new BasicDBObject("$gte", upAudioLossBeforeMin).append("$lte", upAudioLossAfterMax)),
							new BasicDBObject("upLossAudioEFCMic2", new BasicDBObject("$gte", upAudioLossBeforeMin).append("$lte", upAudioLossAfterMax))
					});
				}
			}
			
			if(!(upAudioSignFinal == null ||"".equals(upAudioSignFinal))){
				if ("<".equals(upAudioSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossAudioFinalMic1", new BasicDBObject("$lt", upAudioLossFinalMin)),
							new BasicDBObject("upLossAudioFinalMic2", new BasicDBObject("$lt", upAudioLossFinalMin))});
				}else if (">".equals(upAudioSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("upLossAudioFinalMic1", new BasicDBObject("$gt", upAudioLossFinalMax)),
							new BasicDBObject("upLossAudioFinalMic2", new BasicDBObject("$gt", upAudioLossFinalMax))});
				}else if("between".equals(upAudioSignFinal)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("upLossAudioFinalMic1", new BasicDBObject("$gte", upAudioLossFinalMin).append("$lte", upAudioLossFinalMax)),
							new BasicDBObject("upLossAudioFinalMic2", new BasicDBObject("$gte", upAudioLossFinalMin).append("$lte", upAudioLossFinalMax))
					});
				}
			}
			if(!(downAudioSignBefore == null ||"".equals(downAudioSignBefore))){
				if ("<".equals(downAudioSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossAudioMic1", new BasicDBObject("$lt", downAudioLossBeforeMin)),
							new BasicDBObject("downLossAudioMic2", new BasicDBObject("$lt", downAudioLossBeforeMin))});
				}else if (">".equals(downAudioSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossAudioMic1", new BasicDBObject("$gt", downAudioLossBeforeMax)),
							new BasicDBObject("downLossAudioMic2", new BasicDBObject("$gt", downAudioLossBeforeMax))});
				}else if("between".equals(downAudioSignBefore)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("downLossAudioMic1", new BasicDBObject("$gte", downAudioLossBeforeMin).append("$lte", downAudioLossBeforeMax)),
							new BasicDBObject("downLossAudioMic2", new BasicDBObject("$gte", downAudioLossBeforeMin).append("$lte", downAudioLossBeforeMax))
					});
				}
			}
			
			if(!(downAudioSignAfter == null ||"".equals(downAudioSignAfter))){
				if ("<".equals(downAudioSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossAudioEFCMic1", new BasicDBObject("$lt", downAudioLossAfterMin)),
							new BasicDBObject("downLossAudioEFCMic2", new BasicDBObject("$lt", downAudioLossAfterMin))});
				}else if (">".equals(downAudioSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossAudioEFCMic1", new BasicDBObject("$gt", downAudioLossAfterMax)),
							new BasicDBObject("downLossAudioEFCMic2", new BasicDBObject("$gt", downAudioLossAfterMax))});
				}else if("between".equals(downAudioSignAfter)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("downLossAudioEFCMic1", new BasicDBObject("$gte", downAudioLossAfterMin).append("$lte", downAudioLossAfterMax)),
							new BasicDBObject("downLossAudioEFCMic2", new BasicDBObject("$gte", downAudioLossAfterMin).append("$lte", downAudioLossAfterMax))
					});
				}
			}
			
			if(!(downAudioSignFinal == null ||"".equals(downAudioSignFinal))){
				if ("<".equals(downAudioSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossAudioFinalMic1", new BasicDBObject("$lt", downAudioLossFinalMin)),
							new BasicDBObject("downLossAudioFinalMic2", new BasicDBObject("$lt", downAudioLossFinalMin))});
				}else if (">".equals(downAudioSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("downLossAudioFinalMic1", new BasicDBObject("$gt", downAudioLossFinalMax)),
							new BasicDBObject("downLossAudioFinalMic2", new BasicDBObject("$gt", downAudioLossFinalMax))});
				}else if("between".equals(downAudioSignFinal)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("downLossAudioFinalMic1", new BasicDBObject("$gte", downAudioLossFinalMin).append("$lte", downAudioLossFinalMax)),
							new BasicDBObject("downLossAudioFinalMic2", new BasicDBObject("$gte", downAudioLossFinalMin).append("$lte", downAudioLossFinalMax))
					});
				}
			}
			
			if(!(audioSignBefore == null ||"".equals(audioSignBefore))){
				if ("<".equals(audioSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossAudioMic1CC", new BasicDBObject("$lt", audioLossBeforeMin)),
							new BasicDBObject("lossAudioMic2CC", new BasicDBObject("$lt", audioLossBeforeMin))});
				}else if (">".equals(audioSignBefore)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossAudioMic1CC", new BasicDBObject("$gt", audioLossBeforeMax)),
							new BasicDBObject("lossAudioMic2CC", new BasicDBObject("$gt", audioLossBeforeMax))});
				}else if("between".equals(audioSignBefore)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("lossAudioMic1CC", new BasicDBObject("$gte", audioLossBeforeMin).append("$lte", audioLossBeforeMax)),
							new BasicDBObject("lossAudioMic2CC", new BasicDBObject("$gte", audioLossBeforeMin).append("$lte", audioLossBeforeMax))
					});
				}
			}
			
			if(!(audioSignAfter == null ||"".equals(audioSignAfter))){
				if ("<".equals(audioSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossAudioEFCMic1CC", new BasicDBObject("$lt", audioLossAfterMin)),
							new BasicDBObject("lossAudioEFCMic2CC", new BasicDBObject("$lt", audioLossAfterMin))});
				}else if (">".equals(audioSignAfter)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossAudioEFCMic1CC", new BasicDBObject("$gt", audioLossAfterMax)),
							new BasicDBObject("lossAudioEFCMic2CC", new BasicDBObject("$gt", audioLossAfterMax))});
				}else if("between".equals(audioSignAfter)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("lossAudioEFCMic1CC", new BasicDBObject("$gte", audioLossAfterMin).append("$lte", audioLossAfterMax)),
							new BasicDBObject("lossAudioEFCMic2CC", new BasicDBObject("$gte", audioLossAfterMin).append("$lte", audioLossAfterMax))
					});
				}
			}
			
			if(!(audioSignFinal == null ||"".equals(audioSignFinal))){
				if ("<".equals(audioSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossAudioMic1FinalCC", new BasicDBObject("$lt", audioLossFinalMin)),
							new BasicDBObject("lossAudioMic2FinalCC", new BasicDBObject("$lt", audioLossFinalMin))});
				}else if (">".equals(audioSignFinal)) {
					dbo.put("$and", new BasicDBObject[]{new BasicDBObject("lossAudioMic1FinalCC", new BasicDBObject("$gt", audioLossFinalMax)),
							new BasicDBObject("lossAudioMic2FinalCC", new BasicDBObject("$gt", audioLossFinalMax))});
				}else if("between".equals(audioSignFinal)){
					dbo.put("$and", new BasicDBObject[]{
							new BasicDBObject("lossAudioMic1FinalCC", new BasicDBObject("$gte", audioLossFinalMin).append("$lte", audioLossFinalMax)),
							new BasicDBObject("lossAudioMic2FinalCC", new BasicDBObject("$gte", audioLossFinalMin).append("$lte", audioLossFinalMax))
					});
				}
			}
			DBObject orderBy = new BasicDBObject("startTime", -1);
			int skip = (currPage-1)*pageSize;
			
			//表示从查询结果list中取出第skip个到skip+pagesize个为止
			//skip(k)表示跳到第k个，即从第k个开始
			//limit(n)表示从list中取出n个元素
			cur = employee.find(dbo).skip(skip).limit(pageSize).sort(orderBy);
			int count = employee.find(dbo).count();
			result.setListMeetingInfoSummaryEntities(dbCurToList(cur));
			cur.close();
			result.setCount(count);
			return result;
		} catch (Exception e) {
			logger.error("检索 findbyAll：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return result;
	}

}
