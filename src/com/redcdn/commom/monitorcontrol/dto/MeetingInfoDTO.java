package com.redcdn.commom.monitorcontrol.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
/**
 * 会议信息索引数据传输对象
 * @author 吴磊
 *
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class MeetingInfoDTO extends DTO{

//	private String  userId;//用户Id
//	private String  userIp;//用户IP
//	private int meetingId;//会议ID
//	private int relayId;
//	private String  relayIp;
//	private int micId;
//	private int  delay;//时延
//	private int cpuRate;//CPU使用率
//	private int FrameRate;//帧率
//	private float lossPackRatioBeforeFECDTO;//视频丢包率
//	private float lossPackRatioAfterFECDTO;//efc后视频丢包率
//	private float audioLossPackRatioBeforeFECDTO;//音频丢包率
//	private float audioLossPackRatioAfterFECDTO;//efc后音频丢包率
//	
//	private int oneEmpty;//一个空心包出现的次数
//	private int twoEmpty;//连续两个空心包出现的次数
//	private int thrEmpty;//连续三个空心包出现的次数
//	private int fouEmpty;//连续四个到10个空心包出现的次数
//	private int tenEmpty;//连续10个以上的空心包出现的次数
//	private String  timeStamps;//记录时间戳
//	private int directionType;//上下行 0上行 1下行
//	private long timeStamp;//时间戳（13位毫秒格式）
	
	private int    meetingId;//会议号
	private int    micId;    
	private String speakerId;//发言者Id
	private String senderId;//发送者Id
	private String receiverId;//接收者Id
	//private String frameInfo;//帧信息
	private String frameVideoInfo;//视频帧信息
	private String frameAudioInfo;//音频帧信息
	public int getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}
	public int getMicId() {
		return micId;
	}
	public void setMicId(int micId) {
		this.micId = micId;
	}
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
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//	public String getUserIp() {
//		return userIp;
//	}
//	public void setUserIp(String userIp) {
//		this.userIp = userIp;
//	}
//	public int getMeetingId() {
//		return meetingId;
//	}
//	public void setMeetingId(int meetingId) {
//		this.meetingId = meetingId;
//	}
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
//	public int getMicId() {
//		return micId;
//	}
//	public void setMicId(int micId) {
//		this.micId = micId;
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
//	
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
//
//	public int getDirectionType() {
//		return directionType;
//	}
//	public void setDirectionType(int directionType) {
//		this.directionType = directionType;
//	}
//	
//	public float getLossPackRatioBeforeFECDTO() {
//		return lossPackRatioBeforeFECDTO;
//	}
//	public void setLossPackRatioBeforeFECDTO(float lossPackRatioBeforeFECDTO) {
//		this.lossPackRatioBeforeFECDTO = lossPackRatioBeforeFECDTO;
//	}
//	public float getLossPackRatioAfterFECDTO() {
//		return lossPackRatioAfterFECDTO;
//	}
//	public void setLossPackRatioAfterFECDTO(float lossPackRatioAfterFECDTO) {
//		this.lossPackRatioAfterFECDTO = lossPackRatioAfterFECDTO;
//	}
//	public float getAudioLossPackRatioBeforeFECDTO() {
//		return audioLossPackRatioBeforeFECDTO;
//	}
//	public void setAudioLossPackRatioBeforeFECDTO(
//			float audioLossPackRatioBeforeFECDTO) {
//		this.audioLossPackRatioBeforeFECDTO = audioLossPackRatioBeforeFECDTO;
//	}
//	public float getAudioLossPackRatioAfterFECDTO() {
//		return audioLossPackRatioAfterFECDTO;
//	}
//	public void setAudioLossPackRatioAfterFECDTO(float audioLossPackRatioAfterFECDTO) {
//		this.audioLossPackRatioAfterFECDTO = audioLossPackRatioAfterFECDTO;
//	}
//	public String getTimeStamps() {
//		return timeStamps;
//	}
//	public void setTimeStamps(String timeStamps) {
//		this.timeStamps = timeStamps;
//	}
//	public long getTimeStamp() {
//		return timeStamp;
//	}
//	public void setTimeStamp(long timeStamp) {
//		this.timeStamp = timeStamp;
//	}
}
