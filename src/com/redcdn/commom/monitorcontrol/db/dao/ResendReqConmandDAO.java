package com.redcdn.commom.monitorcontrol.db.dao;

import com.redcdn.commom.monitorcontrol.db.entity.view.ResendReqConmandResult;

public interface ResendReqConmandDAO {
	
	public ResendReqConmandResult findByTimeStamp(int meetingId,int micId, String speakerId,long startTime, long endTime,String senderId,String receiverId);
}
