package com.redcdn.commom.monitorcontrol.db.entity;

public class ResendReqConmandEntity extends BaseEntity{
	private String _id;
	private int meetingid;//会议号
	private int micid;
	private String speakerid;//发言者Id
	private int packetrtt;	
	private int packetrto;	
	private int packetmaxrto;	
	private int packetmaxtimes;//补发最大次数
	private int framertt;	
	private int framerto;	
	private int framemaxtimes;	
	private int frameinnerrto;//帧内补发最大时间
	private String dataPacketList;//补发数据包
	private String checkPacketList;//补发校验包
	private String frameList;//补发的帧序号
	private long recvtime;//帧接收时间
	private String senderid;//发送者编号
	private String receiverid;//接收者编号
	private int direction;//0接收，1发送
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
	public int getPacketrtt() {
		return packetrtt;
	}
	public void setPacketrtt(int packetrtt) {
		this.packetrtt = packetrtt;
	}
	public int getPacketrto() {
		return packetrto;
	}
	public void setPacketrto(int packetrto) {
		this.packetrto = packetrto;
	}
	public int getPacketmaxrto() {
		return packetmaxrto;
	}
	public void setPacketmaxrto(int packetmaxrto) {
		this.packetmaxrto = packetmaxrto;
	}
	public int getPacketmaxtimes() {
		return packetmaxtimes;
	}
	public void setPacketmaxtimes(int packetmaxtimes) {
		this.packetmaxtimes = packetmaxtimes;
	}
	public int getFramertt() {
		return framertt;
	}
	public void setFramertt(int framertt) {
		this.framertt = framertt;
	}
	public int getFramerto() {
		return framerto;
	}
	public void setFramerto(int framerto) {
		this.framerto = framerto;
	}
	public int getFramemaxtimes() {
		return framemaxtimes;
	}
	public void setFramemaxtimes(int framemaxtimes) {
		this.framemaxtimes = framemaxtimes;
	}
	public int getFrameinnerrto() {
		return frameinnerrto;
	}
	public void setFrameinnerrto(int frameinnerrto) {
		this.frameinnerrto = frameinnerrto;
	}
	public String getDataPacketList() {
		return dataPacketList;
	}
	public void setDataPacketList(String dataPacketList) {
		this.dataPacketList = dataPacketList;
	}
	public String getCheckPacketList() {
		return checkPacketList;
	}
	public void setCheckPacketList(String checkPacketList) {
		this.checkPacketList = checkPacketList;
	}
	public String getFrameList() {
		return frameList;
	}
	public void setFrameList(String frameList) {
		this.frameList = frameList;
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
}
