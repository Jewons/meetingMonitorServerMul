package com.redcdn.commom.monitorcontrol.db.entity;

public class FormDataExcelEntity extends BaseEntity{

	private String userId;
	private String userIp;//用户IP
	private int meetingId;//会议ID
	private int relayId;
	private String relayIp;
	private int micId;
	private Long timeStamp;//记录时间戳
	private int directionType;//上下行 0上行 1下行
	private int bandwidth;//客观带宽
	private int traffic;//流量
	private int networkType;//网络类型
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
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getDirectionType() {
		return directionType;
	}
	public void setDirectionType(int directionType) {
		this.directionType = directionType;
	}
	public int getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}
	public int getTraffic() {
		return traffic;
	}
	public void setTraffic(int traffic) {
		this.traffic = traffic;
	}
	public int getNetworkType() {
		return networkType;
	}
	public void setNetworkType(int networkType) {
		this.networkType = networkType;
	}
	
}
