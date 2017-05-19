package com.redcdn.commom.monitorcontrol.db.entity;

public class MeetingInfoEntity extends BaseEntity{

	private String _id;
//	private String userId;
//	private String  userIp;//用户IP
//	private int meetingId;//会议ID
//	private int relayId;
//	private String  relayIp;
//	private int micId;
//	private int delay;//时延
//	private int cpuRate;//CPU使用率
//	private int FrameRate;//帧率
//	private int lossPackRatioBeforeFEC;//视频丢包率
//	private int lossPackRatioAfterFEC;//efc后视频丢包率
//	private int audioLossPackRatioBeforeFEC;//音频丢包率
//	private int audioLossPackRatioAfterFEC;//efc后音频丢包率
//	private int oneEmpty;//一个空心包出现的次数
//	private int twoEmpty;//连续两个空心包出现的次数
//	private int thrEmpty;//连续三个空心包出现的次数
//	private int fouEmpty;//连续四个到10个空心包出现的次数
//	private int tenEmpty;//连续10个以上的空心包出现的次数
//	private Long timeStamp;//记录时间戳
//	private int directionType;//上下行 0上行 1下行
	
	private int    meetingId;//会议号
	private int    micId;    
	private String speakerId;//发言者Id
	private String senderId;//发送者Id
	private String receiverId;//接收者Id
	//private String frameInfo;//帧信息
	private String frameVideoInfo;//视频帧信息
	private String frameAudioInfo;//音频帧信息
	
	public String getSpeakerId() {
		return speakerId;
	}
	public void setSpeakerId(String speakerId) {
		this.speakerId = speakerId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
//	public String getFrameInfo() {
//		return frameInfo;
//	}
//	public void setFrameInfo(String frameInfo) {
//		this.frameInfo = frameInfo;
//	}
	public int getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public int getMicId() {
		return micId;
	}
	public void setMicId(int micId) {
		this.micId = micId;
	}
	public String getFrameVideoInfo() {
		return frameVideoInfo;
	}
	public void setFrameVideoInfo(String frameVideoInfo) {
		this.frameVideoInfo = frameVideoInfo;
	}
	public String getFrameAudioInfo() {
		return frameAudioInfo;
	}
	public void setFrameAudioInfo(String frameAudioInfo) {
		this.frameAudioInfo = frameAudioInfo;
	}
	
	public String toString(){
		String message ="meetingId:"+meetingId+"micId:"+micId+"speakerId:"+speakerId+"senderId:"+senderId+"receiverId:"+receiverId+"frameVideoInfo:"+frameVideoInfo+"frameAudioInfo:"+frameAudioInfo;
		return message;
	}
//	public String getUserIp() {
//		return userIp;
//	}
//	public void setUserIp(String userIp) {
//		this.userIp = userIp;
//	}
//	
//	public int getRelayId() {
//		return relayId;
//	}
//	public void setRelayId(int relayId) {
//		this.relayId = relayId;
//	}
//	public String getRelayIp() {
//		return relayIp;
//	}
//	public void setRelayIp(String relayIp) {
//		this.relayIp = relayIp;
//	}
//	public int getDelay() {
//		return delay;
//	}
//	public void setDelay(int delay) {
//		this.delay = delay;
//	}
//	public int getCpuRate() {
//		return cpuRate;
//	}
//	public void setCpuRate(int cpuRate) {
//		this.cpuRate = cpuRate;
//	}
//	public int getFrameRate() {
//		return FrameRate;
//	}
//	public void setFrameRate(int frameRate) {
//		FrameRate = frameRate;
//	}
//	public int getLossPackRatioBeforeFEC() {
//		return lossPackRatioBeforeFEC;
//	}
//	public void setLossPackRatioBeforeFEC(int lossPackRatioBeforeFEC) {
//		this.lossPackRatioBeforeFEC = lossPackRatioBeforeFEC;
//	}
//	public int getLossPackRatioAfterFEC() {
//		return lossPackRatioAfterFEC;
//	}
//	public void setLossPackRatioAfterFEC(int lossPackRatioAfterFEC) {
//		this.lossPackRatioAfterFEC = lossPackRatioAfterFEC;
//	}
//	public int getAudioLossPackRatioBeforeFEC() {
//		return audioLossPackRatioBeforeFEC;
//	}
//	public void setAudioLossPackRatioBeforeFEC(int audioLossPackRatioBeforeFEC) {
//		this.audioLossPackRatioBeforeFEC = audioLossPackRatioBeforeFEC;
//	}
//	public int getAudioLossPackRatioAfterFEC() {
//		return audioLossPackRatioAfterFEC;
//	}
//	public void setAudioLossPackRatioAfterFEC(int audioLossPackRatioAfterFEC) {
//		this.audioLossPackRatioAfterFEC = audioLossPackRatioAfterFEC;
//	}
//	public int getOneEmpty() {
//		return oneEmpty;
//	}
//	public void setOneEmpty(int oneEmpty) {
//		this.oneEmpty = oneEmpty;
//	}
//	public int getTwoEmpty() {
//		return twoEmpty;
//	}
//	public void setTwoEmpty(int twoEmpty) {
//		this.twoEmpty = twoEmpty;
//	}
//	public int getThrEmpty() {
//		return thrEmpty;
//	}
//	public void setThrEmpty(int thrEmpty) {
//		this.thrEmpty = thrEmpty;
//	}
//	public int getFouEmpty() {
//		return fouEmpty;
//	}
//	public void setFouEmpty(int fouEmpty) {
//		this.fouEmpty = fouEmpty;
//	}
//	public int getTenEmpty() {
//		return tenEmpty;
//	}
//	public void setTenEmpty(int tenEmpty) {
//		this.tenEmpty = tenEmpty;
//	}
//	public Long getTimeStamp() {
//		return timeStamp;
//	}
//	public void setTimeStamp(Long timeStamp) {
//		this.timeStamp = timeStamp;
//	}
//	public int getDirectionType() {
//		return directionType;
//	}
//	public void setDirectionType(int directionType) {
//		this.directionType = directionType;
//	}
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
}
