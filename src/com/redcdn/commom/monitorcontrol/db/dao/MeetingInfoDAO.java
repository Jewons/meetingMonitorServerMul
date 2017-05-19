package com.redcdn.commom.monitorcontrol.db.dao;


import com.redcdn.commom.monitorcontrol.db.entity.view.MeetingInfoResult;
public interface MeetingInfoDAO {
	

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
	public MeetingInfoResult findByid(long startTime,long endTime,String userId, int meetingId,
			int relayId, int directionType,int pageSize, int currPage);
	
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param videoSignBefore
	 * @param videoLossBeforeMin
	 * @param videoLossBeforeMax
	 * @param videoSignAfter
	 * @param videoLossAfterMin
	 * @param videoLossAfterMax
	 * @param audioSignBefore
	 * @param audioLossBeforeMin
	 * @param audioLossBeforeMax
	 * @param audioSignAfter
	 * @param audioLossAfterMin
	 * @param audioLossAfterMax
	 * @param directionType
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	public MeetingInfoResult findBySteam(long startTime,long endTime,String videoSignBefore, float videoLossBeforeMin,
			float videoLossBeforeMax, String videoSignAfter, float videoLossAfterMin, float videoLossAfterMax, 
			String audioSignBefore, float audioLossBeforeMin, float audioLossBeforeMax, String audioSignAfter, 
			float audioLossAfterMin, float audioLossAfterMax, 
			int directionType,int pageSize, int currPage);
	
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param cpuRateSign
	 * @param cpuRateMin
	 * @param cpuRateMax
	 * @param frameRateSign
	 * @param frameRateMin
	 * @param frameRaterMax
	 * @param delaySign
	 * @param delayMin
	 * @param delayMax
	 * @param directionType
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	public MeetingInfoResult findByQuality(long startTime,long endTime,String cpuRateSign, int cpuRateMin,
			int cpuRateMax, String frameRateSign, int frameRateMin, int frameRaterMax, 
			String delaySign, int delayMin, int delayMax,
			int directionType,int pageSize, int currPage);
	
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param oneSign
	 * @param oneMin
	 * @param oneMax
	 * @param twoSign
	 * @param twoMin
	 * @param twoMax
	 * @param thrSign
	 * @param thrMin
	 * @param thrMax
	 * @param fouSign
	 * @param fouMin
	 * @param fouMax
	 * @param tenMin
	 * @param tenMax
	 * @param directionType
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	public MeetingInfoResult findByEmptyPackage(long startTime,long endTime,String oneSign, int oneMin,
			int oneMax, String twoSign, int twoMin, int twoMax, String thrSign, int thrMin, int thrMax,
			String fouSign, int fouMin, int fouMax, String tenSign, int tenMin, int tenMax,int directionType,int pageSize, 
			int currPage);
	
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param userId
	 * @param meetingId
	 * @param relayId
	 * @param videoSignBefore
	 * @param videoLossBeforeMin
	 * @param videoLossBeforeMax
	 * @param videoSignAfter
	 * @param videoLossAfterMin
	 * @param videoLossAfterMax
	 * @param audioSignBefore
	 * @param audioLossBeforeMin
	 * @param audioLossBeforeMax
	 * @param audioSignAfter
	 * @param audioLossAfterMin
	 * @param audioLossAfterMax
	 * @param cpuRateSign
	 * @param cpuRateMin
	 * @param cpuRateMax
	 * @param frameRateSign
	 * @param frameRateMin
	 * @param frameRaterMax
	 * @param delaySign
	 * @param delayMin
	 * @param delayMax
	 * @param oneSign
	 * @param oneMin
	 * @param oneMax
	 * @param twoSign
	 * @param twoMin
	 * @param twoMax
	 * @param thrSign
	 * @param thrMin
	 * @param thrMax
	 * @param fouSign
	 * @param fouMin
	 * @param fouMax
	 * @param tenSign
	 * @param tenMin
	 * @param tenMax
	 * @param directionType
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	public MeetingInfoResult findByAll(long startTime,long endTime,
			String userId, int meetingId,int relayId,
			String videoSignBefore, float videoLossBeforeMin,float videoLossBeforeMax, String videoSignAfter, float videoLossAfterMin, float videoLossAfterMax, String audioSignBefore, float audioLossBeforeMin, float audioLossBeforeMax, String audioSignAfter, float audioLossAfterMin, float audioLossAfterMax, 
			String cpuRateSign, int cpuRateMin,int cpuRateMax, String frameRateSign, int frameRateMin, int frameRaterMax, String delaySign, int delayMin, int delayMax,
			String oneSign, int oneMin,int oneMax, String twoSign, int twoMin, int twoMax, String thrSign, int thrMin, int thrMax,String fouSign, int fouMin, int fouMax, String tenSign, int tenMin, int tenMax,
			int directionType,int pageSize, int currPage);
	
	//public MeetingInfoResult findByMeetingId(int meetingId,int micId, String userId);
	public MeetingInfoResult findByMeetingId(int meetingId,int micId, String speakerId,int pageSize, int currPage);
	
}
