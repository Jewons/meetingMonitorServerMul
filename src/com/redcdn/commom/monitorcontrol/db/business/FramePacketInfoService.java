package com.redcdn.commom.monitorcontrol.db.business;

import com.redcdn.commom.monitorcontrol.db.entity.view.FramePacketInfoResult;



public interface FramePacketInfoService {
	
	public FramePacketInfoResult findByTimeStamp(int meetingId,int micId, String speakerId,long startTime, long endTime,String senderId,String receiverId);
}
