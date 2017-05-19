package com.redcdn.commom.monitorcontrol.db.entity;

public class FramePacketInfoEntity extends BaseEntity{
	private String _id;
	private int meetingid;
	private int micid;
	private String speakerid;
	private int frameid;//帧号
	private long recvtime;//帧接收时间
	private String senderid;//发送者编号
	private String receiverid;//接收者编号
	private int direction;//0接收，1发送
	private String packettype;//data 或 check
	private int fecgroupid;//组编号
	private String fecrateFEC;//比例
	private int fecgroupinnerid;//FEC组内编号
	private long time;//包接收时间
	private int frameInnerId;//帧内编号
	private int index;//数据包序号
	private int mideatype;//0视频，1音频
	public int getMeetingid() {
		return meetingid;
	}
	public void setMeetingid(int meetingid) {
		this.meetingid = meetingid;
	}
	public int getMicid() {
		return micid;
	}
	public void setMicid(int micid) {
		this.micid = micid;
	}
	public String getSpeakerid() {
		return speakerid;
	}
	public void setSpeakerid(String speakerid) {
		this.speakerid = speakerid;
	}
	
	public int getFrameid() {
		return frameid;
	}
	public void setFrameid(int frameid) {
		this.frameid = frameid;
	}
	public long getRecvtime() {
		return recvtime;
	}
	public void setRecvtime(long recvtime) {
		this.recvtime = recvtime;
	}
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}
	public String getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(String receiverid) {
		this.receiverid = receiverid;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public String getPackettype() {
		return packettype;
	}
	public void setPackettype(String packettype) {
		this.packettype = packettype;
	}
	public int getFecgroupid() {
		return fecgroupid;
	}
	public void setFecgroupid(int fecgroupid) {
		this.fecgroupid = fecgroupid;
	}
	public String getFecrateFEC() {
		return fecrateFEC;
	}
	public void setFecrateFEC(String fecrateFEC) {
		this.fecrateFEC = fecrateFEC;
	}
	public int getFecgroupinnerid() {
		return fecgroupinnerid;
	}
	public void setFecgroupinnerid(int fecgroupinnerid) {
		this.fecgroupinnerid = fecgroupinnerid;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getFrameInnerId() {
		return frameInnerId;
	}
	public void setFrameInnerId(int frameInnerId) {
		this.frameInnerId = frameInnerId;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getMideatype() {
		return mideatype;
	}
	public void setMideatype(int mideatype) {
		this.mideatype = mideatype;
	}

}
