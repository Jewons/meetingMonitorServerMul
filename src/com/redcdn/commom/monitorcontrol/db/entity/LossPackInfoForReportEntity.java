package com.redcdn.commom.monitorcontrol.db.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 汇报查询实体类
 * @author wulei
 * 
 */
public class LossPackInfoForReportEntity {
	private int meetingId; 
	private String userId;
	private int    mediaType;
	private String speakerId;
	public List<LossPackInfoEntity> list;
	
	public LossPackInfoForReportEntity(int meetingId,String userId){
		this.meetingId = meetingId;
		this.userId    = userId;
		list = new ArrayList<LossPackInfoEntity>();
	}


//	public List<LossPackInfoEntity> getList() {
//		return list;
//	}
//
//	public void setList(List<LossPackInfoEntity> list) {
//		this.list = list;
//	}


	public int getMeetingId() {
		return meetingId;
	}


	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getMediaType() {
		return mediaType;
	}


	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}


	public String getSpeakerId() {
		return speakerId;
	}


	public void setSpeakerId(String speakerId) {
		this.speakerId = speakerId;
	}
	
	
}
