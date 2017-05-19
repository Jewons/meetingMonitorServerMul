package com.redcdn.commom.monitorcontrol.db.dao;

import java.util.List;
import java.util.Map;

import com.redcdn.commom.monitorcontrol.db.entity.FormDataExcelEntity;
import com.redcdn.commom.monitorcontrol.db.entity.LossPackInfoForReportEntity;
import com.redcdn.commom.monitorcontrol.db.entity.view.LossPackInfoResult;
import com.redcdn.commom.monitorcontrol.dto.FormDataExcelListDTO;

public interface LossPackInfoDAO {
	public LossPackInfoResult findByUserId(int meetingId,String speakerId, String userId,int pageSize, int currPage);
	
	public LossPackInfoResult findByMeetingId(int meetingId, String speakerId, int directionType);
	
//	public LossPackInfoResult findByMeetingIds(List<Integer> list, String speakerId, int directionType, long endTime);
	
	public List<FormDataExcelEntity> findByMeetingIds(List<Integer> list);
	
	public FormDataExcelListDTO findByTime(long startTime, long endTime, int currPage, int pageSize, boolean isCount);

	public FormDataExcelListDTO findByMeetingIds(List<Integer> list, int currPage,
			int pageSize, boolean isCount);
	
	public FormDataExcelListDTO findByMeetingIds(List<Integer> list, int currPage,
			int pageSize, boolean isCount, int index);
	
//	public LossPackInfoResult findByMeetingIds(List<Integer> list, String speakerId, int directionType, long endTime,String[] userList);
	
	public void setCollectionName(String tabName);
	
	public Map<String, LossPackInfoForReportEntity> findByMeetingIds(List<Integer> list, String speakerId,
			int directionType, long endTime, String[] userList);
}
