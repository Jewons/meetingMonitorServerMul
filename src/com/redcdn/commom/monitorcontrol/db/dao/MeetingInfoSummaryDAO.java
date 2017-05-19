package com.redcdn.commom.monitorcontrol.db.dao;

import java.util.List;

import com.redcdn.commom.monitorcontrol.db.entity.view.MeetingInfoSummaryResult;
public interface MeetingInfoSummaryDAO {
	

	/**
	 * 
	 * @param startTime 起始时间
	 * @param endTime   结束时间
	 * @param userId    用户Id
	 * @param meetingId 会议号
	 * @param relayId   relayId
	 * @param directionType 传输方向 0上行 1下行 2全部
	 * @param pageSize      
	 * @param currPage 当前页
	 * @return
	 */
	public MeetingInfoSummaryResult findByid(long startTime,long endTime,String userId, int meetingId,
			String relayId, int pageSize, int currPage);
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param userId
	 * @param meetingId
	 * @param relayId
	 * @param pageSize
	 * @param currPage
	 * @param userList 企业视讯号列表
	 * @return
	 */
	public MeetingInfoSummaryResult findByid(long startTime,long endTime,String userId, int meetingId,
			String relayId, int pageSize, int currPage,List<String> userList);
	
	public MeetingInfoSummaryResult findByAttendCount(long startTime,long endTime, String attendCountSign, int attendCountMin,
			int attendCountMax, int pageSize, int currPage);
	
	public MeetingInfoSummaryResult findByStream(long startTime, long endTime, String upVideoSignBefore, float upVideoLossBeforeMin, float upVideoLossBeforeMax, String upVideoSignAfter, float upVideoLossAfterMin,float upVideoLossAfterMax
			,String downVideoSignBefore, float downVideoLossBeforeMin, float downVideoLossBeforeMax, String downVideoSignAfter, float downVideoLossAfterMin, float downVideoLossAfterMax, String upAudioSignBefore, float upAudioLossBeforeMin, float upAudioLossBeforeMax, String upAudioSignAfter, float upAudioLossAfterMin, float upAudioLossAfterMax
			,String downAudioSignBefore, float downAudioLossBeforeMin, float downAudioLossBeforeMax, String downAudioSignAfter, float downAudioLossAfterMin, float downAudioLossAfterMax, int pageSize, int currPage);
	
	
	public MeetingInfoSummaryResult findByTime(long startTime,long endTime,String ducationsign, long ducationMin,
			long ducationMax, int pageSize, int currPage);
	

	
	public MeetingInfoSummaryResult findByVideoStream(long startTime, long endTime
			,String upVideoSignBefore, float upVideoLossBeforeMin, float upVideoLossBeforeMax, String upVideoSignAfter, float upVideoLossAfterMin,float upVideoLossAfterMax,String upVideoSignFinal, float upVideoLossFinalMin,float upVideoLossFinalMax
			,String downVideoSignBefore, float downVideoLossBeforeMin, float downVideoLossBeforeMax, String downVideoSignAfter, float downVideoLossAfterMin, float downVideoLossAfterMax, String downVideoSignFinal, float downVideoLossFinalMin, float downVideoLossFinalMax
			,String videoSignBefore, float videoLossBeforeMin, float videoLossBeforeMax, String videoSignAfter, float videoLossAfterMin, float videoLossAfterMax, String videoSignFinal, float videoLossFinalMin, float videoLossFinalMax
			,int pageSize, int currPage);
	
	public MeetingInfoSummaryResult findByAudioStream(long startTime, long endTime
			,String upAudioSignBefore, float upAudioLossBeforeMin, float upAudioLossBeforeMax, String upAudioSignAfter, float upAudioLossAfterMin,float upAudioLossAfterMax,String upAudioSignFinal, float upAudioLossFinalMin,float upAudioLossFinalMax
			,String downAudioSignBefore, float downAudioLossBeforeMin, float downAudioLossBeforeMax, String downAudioSignAfter, float downAudioLossAfterMin, float downAudioLossAfterMax, String downAudioSignFinal, float downAudioLossFinalMin, float downAudioLossFinalMax
			,String audioSignBefore, float audioLossBeforeMin, float audioLossBeforeMax, String audioSignAfter, float audioLossAfterMin, float audioLossAfterMax, String audioSignFinal, float audioLossFinalMin, float audioLossFinalMax
			,int pageSize, int currPage);
	
	public MeetingInfoSummaryResult findByAll(long startTime,long endTime,
			String userId, int meetingId,String relayId, 
			String attendCountSign, int attendCountMin,int attendCountMax,
			String ducationsign, long ducationMin,long ducationMax
			,String upVideoSignBefore, float upVideoLossBeforeMin, float upVideoLossBeforeMax, String upVideoSignAfter, float upVideoLossAfterMin,float upVideoLossAfterMax,String upVideoSignFinal, float upVideoLossFinalMin,float upVideoLossFinalMax
			,String downVideoSignBefore, float downVideoLossBeforeMin, float downVideoLossBeforeMax, String downVideoSignAfter, float downVideoLossAfterMin, float downVideoLossAfterMax, String downVideoSignFinal, float downVideoLossFinalMin, float downVideoLossFinalMax
			,String videoSignBefore, float videoLossBeforeMin, float videoLossBeforeMax, String videoSignAfter, float videoLossAfterMin, float videoLossAfterMax, String videoSignFinal, float videoLossFinalMin, float videoLossFinalMax
			,String upAudioSignBefore, float upAudioLossBeforeMin, float upAudioLossBeforeMax, String upAudioSignAfter, float upAudioLossAfterMin,float upAudioLossAfterMax,String upAudioSignFinal, float upAudioLossFinalMin,float upAudioLossFinalMax
			,String downAudioSignBefore, float downAudioLossBeforeMin, float downAudioLossBeforeMax, String downAudioSignAfter, float downAudioLossAfterMin, float downAudioLossAfterMax, String downAudioSignFinal, float downAudioLossFinalMin, float downAudioLossFinalMax
			,String audioSignBefore, float audioLossBeforeMin, float audioLossBeforeMax, String audioSignAfter, float audioLossAfterMin, float audioLossAfterMax, String audioSignFinal, float audioLossFinalMin, float audioLossFinalMax
			,int pageSize, int currPage);
}

