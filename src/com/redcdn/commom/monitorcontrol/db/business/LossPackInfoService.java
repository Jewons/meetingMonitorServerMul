package com.redcdn.commom.monitorcontrol.db.business;

import java.util.List;
import java.util.Map;

import org.springframework.oxm.jibx.JibxMarshaller;

import net.sf.json.JSONObject;

import com.redcdn.commom.monitorcontrol.db.entity.view.LossPackInfoResult;
import com.redcdn.commom.monitorcontrol.dto.FormDataExcelDTO;

public interface LossPackInfoService {
	public LossPackInfoResult findByUserId(int meetingId,String speakerId, String userId,int pageSize, int currPage);
	
	public LossPackInfoResult findByUserId(int meetingId,String speakerId, String userId,int pageSize, int currPage,String qosTableNames);
	
	public LossPackInfoResult findByMeetingId(int meetingId, String speakerId, int directionType);
	
//	public LossPackInfoResult findByMeetingIds(List<Integer> list, String speakerId, int directionType,long endTime);
	
	public List<FormDataExcelDTO> findByMeetingIds(List<Integer> list);
	
	public List<FormDataExcelDTO> findByTime(long startTime, long endTime, int pageSize);
	
	public List<FormDataExcelDTO> findByMeetingIds(List<Integer> meetingIds, int pageSize);
	
	public List<FormDataExcelDTO> findByMeetingIdsForTempVersion(List<Integer> meetingIds, int pageSize, int index);
	
	public LossPackInfoResult findByMeetingIds(List<Integer> list, String speakerId, int directionType,long endTime,String[] userList);
	
	//public LossPackInfoResult findByMeetingIds(List<Integer> list, String speakerId, int directionType,long endTime,String[] userList,Map<Integer, String> meetingInfo);
	
	//public LossPackInfoResult findByMeetingIds(List<Integer> list, String speakerId,int directionType, long endTime, String[] userList,String qosTableNames);
	
	public void setMeetingStartTime(String meetingStartTime);
	
	public void setMeetingEndTime(String meetingEndTime);

	//public LossPackInfoResult findByMeetingIds(List<JSONObject> meetingList,String speakerId, int directionType, long endTime, String[] userlist);
}
