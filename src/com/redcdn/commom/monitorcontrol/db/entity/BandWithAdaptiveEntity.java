package com.redcdn.commom.monitorcontrol.db.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.redcdn.commom.monitorcontrol.dto.DTO;
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class BandWithAdaptiveEntity extends DTO{
	private String userId;
	private String userIp;//用户IP
	private int meetingId;//会议ID
	private int relayId;
	private String relayIp;
	private int micId;
	private int directionType;//上下行 0上行 1下行
	private  String timeStamp;
	private  String bandInfo;
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getBandInfo() {
		return bandInfo;
	}
	public void setBandInfo(String bandInfo) {
		this.bandInfo = bandInfo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public int getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}
	public int getRelayId() {
		return relayId;
	}
	public void setRelayId(int relayId) {
		this.relayId = relayId;
	}
	public String getRelayIp() {
		return relayIp;
	}
	public void setRelayIp(String relayIp) {
		this.relayIp = relayIp;
	}
	public int getMicId() {
		return micId;
	}
	public void setMicId(int micId) {
		this.micId = micId;
	}
	public int getDirectionType() {
		return directionType;
	}
	public void setDirectionType(int directionType) {
		this.directionType = directionType;
	}
	
}
