package com.redcdn.commom.monitorcontrol.dto;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
/**
 * 会议概要信息索引数据传输对象
 * @author 吴磊
 *
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class MeetingInfoSummaryDTO extends DTO{
	private int meetingId;//会议Id
	private String timeStamps;//会议开始时间
	private int userCount;//参会人数
	private List<String> userIdList;//userId
	private List<String> relayIdList;//relayId
//	private float upLossVideoMic1DTO;
//	private float downLossVideoMic1DTO;	
//	private float upLossVideoMic2DTO;	
//	private float downLossVideoMic2DTO;
//	private float upLossVideoEFCMic1DTO;
//	private float downLossVideoEFCMic1DTO;
//	private float upLossVideoEFCMic2DTO;
//	private float downLossVideoEFCMic2DTO;
//	private float upLossAudioMic1DTO;
//	private float downLossAudioMic1DTO;
//	private float upLossAudioMic2DTO;
//	private float downLossAudioMic2DTO;
//	private float upLossAudioEFCMic1DTO;
//	private float downLossAudioEFCMic1DTO;
//	private float upLossAudioEFCMic2DTO;
//	private float downLossAudioEFCMic2DTO;
//	private String durations;
	
	private long duration;
	
	//7.24新增
//	private List<String> speakerIdListMic1;//mic1的发言者队列
//	private List<String> speakerIdListMic2;//mic2的发言者队列
	
	//8.25新增
//	private float upLossVideoFinalMic1DTO;//上行mic1视频最终丢包率
//	private float downLossVideoFinalMic1DTO;//下行mic1视频最终丢包率
//	private float upLossVideoFinalMic2DTO;//上行mic2视频最终丢包率
//	private float downLossVideoFinalMic2DTO;//下行mic2视频最终丢包率
//	private float upLossAudioFinalMic1DTO;//上行mic1音频最终丢包率
//	private float downLossAudioFinalMic1DTO;//下行mic1音频最终丢包率
//	private float upLossAudioFinalMic2DTO;//上行mic2音频最终丢包率
//	private float downLossAudioFinalMic2DTO;//下行mic2音频最终丢包率
//	private float lossVideoMic1CCDTO;//下行mic1视频整体原始丢包率
//	private float lossVideoEFCMic1CCDTO;//下行mic1视频整体FEC后丢包率
//	private float lossVideoMic1FinalCCDTO;//下行mic1视频整体最终丢包率
//	private float lossVideoMic2CCDTO;//下行mic2视频整体原始丢包率
//	private float lossVideoEFCMic2CCDTO;//下行mic2视频整体FEC后丢包率
//	private float lossVideoMic2FinalCCDTO;//下行mic2视频整体最终丢包率
//	private float lossAudioMic1CCDTO;//下行mic1音频整体原始丢包率
//	private float lossAudioEFCMic1CCDTO;//下行mic1音频整体FEC后丢包率
//	private float lossAudioMic1FinalCCDTO;//下行mic1音频整体最终丢包率
//	private float lossAudioMic2CCDTO;//下行mic2音频整体原始丢包率
//	private float lossAudioEFCMic2CCDTO;//下行mic2音频整体FEC后丢包率
//	private float lossAudioMic2FinalCCDTO;//下行mic2音频整体最终丢包率
	
	
	//2016.4.22新增
	private List<String> speakerIdList;//发言者ID队列
	private String endTimes;
	// 2016 12-29 加上端到端合格率，以及概要表中的详情表的表名
	private String c2cquality;
	private String qosTableName;
//	public float getUpLossVideoFinalMic1DTO() {
//		return upLossVideoFinalMic1DTO;
//	}
//	public void setUpLossVideoFinalMic1DTO(float upLossVideoFinalMic1DTO) {
//		this.upLossVideoFinalMic1DTO = upLossVideoFinalMic1DTO;
//	}
//	public float getDownLossVideoFinalMic1DTO() {
//		return downLossVideoFinalMic1DTO;
//	}
//	public void setDownLossVideoFinalMic1DTO(float downLossVideoFinalMic1DTO) {
//		this.downLossVideoFinalMic1DTO = downLossVideoFinalMic1DTO;
//	}
//	public float getUpLossVideoFinalMic2DTO() {
//		return upLossVideoFinalMic2DTO;
//	}
//	public void setUpLossVideoFinalMic2DTO(float upLossVideoFinalMic2DTO) {
//		this.upLossVideoFinalMic2DTO = upLossVideoFinalMic2DTO;
//	}
//	public float getDownLossVideoFinalMic2DTO() {
//		return downLossVideoFinalMic2DTO;
//	}
//	public void setDownLossVideoFinalMic2DTO(float downLossVideoFinalMic2DTO) {
//		this.downLossVideoFinalMic2DTO = downLossVideoFinalMic2DTO;
//	}
//	public float getUpLossAudioFinalMic1DTO() {
//		return upLossAudioFinalMic1DTO;
//	}
//	public void setUpLossAudioFinalMic1DTO(float upLossAudioFinalMic1DTO) {
//		this.upLossAudioFinalMic1DTO = upLossAudioFinalMic1DTO;
//	}
//	public float getDownLossAudioFinalMic1DTO() {
//		return downLossAudioFinalMic1DTO;
//	}
//	public void setDownLossAudioFinalMic1DTO(float downLossAudioFinalMic1DTO) {
//		this.downLossAudioFinalMic1DTO = downLossAudioFinalMic1DTO;
//	}
//	public float getUpLossAudioFinalMic2DTO() {
//		return upLossAudioFinalMic2DTO;
//	}
//	public void setUpLossAudioFinalMic2DTO(float upLossAudioFinalMic2DTO) {
//		this.upLossAudioFinalMic2DTO = upLossAudioFinalMic2DTO;
//	}
//	public float getDownLossAudioFinalMic2DTO() {
//		return downLossAudioFinalMic2DTO;
//	}
//	public void setDownLossAudioFinalMic2DTO(float downLossAudioFinalMic2DTO) {
//		this.downLossAudioFinalMic2DTO = downLossAudioFinalMic2DTO;
//	}
//	public float getLossVideoMic1CCDTO() {
//		return lossVideoMic1CCDTO;
//	}
//	public void setLossVideoMic1CCDTO(float lossVideoMic1CCDTO) {
//		this.lossVideoMic1CCDTO = lossVideoMic1CCDTO;
//	}
//	public float getLossVideoEFCMic1CCDTO() {
//		return lossVideoEFCMic1CCDTO;
//	}
//	public void setLossVideoEFCMic1CCDTO(float lossVideoEFCMic1CCDTO) {
//		this.lossVideoEFCMic1CCDTO = lossVideoEFCMic1CCDTO;
//	}
//	public float getLossVideoMic1FinalCCDTO() {
//		return lossVideoMic1FinalCCDTO;
//	}
//	public void setLossVideoMic1FinalCCDTO(float lossVideoMic1FinalCCDTO) {
//		this.lossVideoMic1FinalCCDTO = lossVideoMic1FinalCCDTO;
//	}
//	public float getLossVideoMic2CCDTO() {
//		return lossVideoMic2CCDTO;
//	}
//	public void setLossVideoMic2CCDTO(float lossVideoMic2CCDTO) {
//		this.lossVideoMic2CCDTO = lossVideoMic2CCDTO;
//	}
//	public float getLossVideoEFCMic2CCDTO() {
//		return lossVideoEFCMic2CCDTO;
//	}
//	public void setLossVideoEFCMic2CCDTO(float lossVideoEFCMic2CCDTO) {
//		this.lossVideoEFCMic2CCDTO = lossVideoEFCMic2CCDTO;
//	}
//	public float getLossVideoMic2FinalCCDTO() {
//		return lossVideoMic2FinalCCDTO;
//	}
//	public void setLossVideoMic2FinalCCDTO(float lossVideoMic2FinalCCDTO) {
//		this.lossVideoMic2FinalCCDTO = lossVideoMic2FinalCCDTO;
//	}
//	public float getLossAudioMic1CCDTO() {
//		return lossAudioMic1CCDTO;
//	}
//	public void setLossAudioMic1CCDTO(float lossAudioMic1CCDTO) {
//		this.lossAudioMic1CCDTO = lossAudioMic1CCDTO;
//	}
//	public float getLossAudioEFCMic1CCDTO() {
//		return lossAudioEFCMic1CCDTO;
//	}
//	public void setLossAudioEFCMic1CCDTO(float lossAudioEFCMic1CCDTO) {
//		this.lossAudioEFCMic1CCDTO = lossAudioEFCMic1CCDTO;
//	}
//	public float getLossAudioMic1FinalCCDTO() {
//		return lossAudioMic1FinalCCDTO;
//	}
//	public void setLossAudioMic1FinalCCDTO(float lossAudioMic1FinalCCDTO) {
//		this.lossAudioMic1FinalCCDTO = lossAudioMic1FinalCCDTO;
//	}
//	public float getLossAudioMic2CCDTO() {
//		return lossAudioMic2CCDTO;
//	}
//	public void setLossAudioMic2CCDTO(float lossAudioMic2CCDTO) {
//		this.lossAudioMic2CCDTO = lossAudioMic2CCDTO;
//	}
//	public float getLossAudioEFCMic2CCDTO() {
//		return lossAudioEFCMic2CCDTO;
//	}
//	public void setLossAudioEFCMic2CCDTO(float lossAudioEFCMic2CCDTO) {
//		this.lossAudioEFCMic2CCDTO = lossAudioEFCMic2CCDTO;
//	}
//	public float getLossAudioMic2FinalCCDTO() {
//		return lossAudioMic2FinalCCDTO;
//	}
//	public void setLossAudioMic2FinalCCDTO(float lossAudioMic2FinalCCDTO) {
//		this.lossAudioMic2FinalCCDTO = lossAudioMic2FinalCCDTO;
//	}
//	public List<String> getSpeakerIdListMic1() {
//		return speakerIdListMic1;
//	}
//	public void setSpeakerIdListMic1(List<String> speakerIdListMic1) {
//		this.speakerIdListMic1 = speakerIdListMic1;
//	}
//	public List<String> getSpeakerIdListMic2() {
//		return speakerIdListMic2;
//	}
//	public void setSpeakerIdListMic2(List<String> speakerIdListMic2) {
//		this.speakerIdListMic2 = speakerIdListMic2;
//	}
	public int getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}

	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public List<String> getUserIdList() {
		return userIdList;
	}
	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}
	public List<String> getRelayIdList() {
		return relayIdList;
	}
	public void setRelayIdList(List<String> relayIdList) {
		this.relayIdList = relayIdList;
	}
	
//	public float getUpLossVideoMic1DTO() {
//		return upLossVideoMic1DTO;
//	}
//	public void setUpLossVideoMic1DTO(float upLossVideoMic1DTO) {
//		this.upLossVideoMic1DTO = upLossVideoMic1DTO;
//	}
//	public float getDownLossVideoMic1DTO() {
//		return downLossVideoMic1DTO;
//	}
//	public void setDownLossVideoMic1DTO(float downLossVideoMic1DTO) {
//		this.downLossVideoMic1DTO = downLossVideoMic1DTO;
//	}
//	public float getUpLossVideoMic2DTO() {
//		return upLossVideoMic2DTO;
//	}
//	public void setUpLossVideoMic2DTO(float upLossVideoMic2DTO) {
//		this.upLossVideoMic2DTO = upLossVideoMic2DTO;
//	}
//	public float getDownLossVideoMic2DTO() {
//		return downLossVideoMic2DTO;
//	}
//	public void setDownLossVideoMic2DTO(float downLossVideoMic2DTO) {
//		this.downLossVideoMic2DTO = downLossVideoMic2DTO;
//	}
//	public float getUpLossVideoEFCMic1DTO() {
//		return upLossVideoEFCMic1DTO;
//	}
//	public void setUpLossVideoEFCMic1DTO(float upLossVideoEFCMic1DTO) {
//		this.upLossVideoEFCMic1DTO = upLossVideoEFCMic1DTO;
//	}
//	public float getDownLossVideoEFCMic1DTO() {
//		return downLossVideoEFCMic1DTO;
//	}
//	public void setDownLossVideoEFCMic1DTO(float downLossVideoEFCMic1DTO) {
//		this.downLossVideoEFCMic1DTO = downLossVideoEFCMic1DTO;
//	}
//	public float getUpLossVideoEFCMic2DTO() {
//		return upLossVideoEFCMic2DTO;
//	}
//	public void setUpLossVideoEFCMic2DTO(float upLossVideoEFCMic2DTO) {
//		this.upLossVideoEFCMic2DTO = upLossVideoEFCMic2DTO;
//	}
//	public float getDownLossVideoEFCMic2DTO() {
//		return downLossVideoEFCMic2DTO;
//	}
//	public void setDownLossVideoEFCMic2DTO(float downLossVideoEFCMic2DTO) {
//		this.downLossVideoEFCMic2DTO = downLossVideoEFCMic2DTO;
//	}
//	public float getUpLossAudioMic1DTO() {
//		return upLossAudioMic1DTO;
//	}
//	public void setUpLossAudioMic1DTO(float upLossAudioMic1DTO) {
//		this.upLossAudioMic1DTO = upLossAudioMic1DTO;
//	}
//	public float getDownLossAudioMic1DTO() {
//		return downLossAudioMic1DTO;
//	}
//	public void setDownLossAudioMic1DTO(float downLossAudioMic1DTO) {
//		this.downLossAudioMic1DTO = downLossAudioMic1DTO;
//	}
//	public float getUpLossAudioMic2DTO() {
//		return upLossAudioMic2DTO;
//	}
//	public void setUpLossAudioMic2DTO(float upLossAudioMic2DTO) {
//		this.upLossAudioMic2DTO = upLossAudioMic2DTO;
//	}
//	public float getDownLossAudioMic2DTO() {
//		return downLossAudioMic2DTO;
//	}
//	public void setDownLossAudioMic2DTO(float downLossAudioMic2DTO) {
//		this.downLossAudioMic2DTO = downLossAudioMic2DTO;
//	}
//	public float getUpLossAudioEFCMic1DTO() {
//		return upLossAudioEFCMic1DTO;
//	}
//	public void setUpLossAudioEFCMic1DTO(float upLossAudioEFCMic1DTO) {
//		this.upLossAudioEFCMic1DTO = upLossAudioEFCMic1DTO;
//	}
//	public float getDownLossAudioEFCMic1DTO() {
//		return downLossAudioEFCMic1DTO;
//	}
//	public void setDownLossAudioEFCMic1DTO(float downLossAudioEFCMic1DTO) {
//		this.downLossAudioEFCMic1DTO = downLossAudioEFCMic1DTO;
//	}
//	public float getUpLossAudioEFCMic2DTO() {
//		return upLossAudioEFCMic2DTO;
//	}
//	public void setUpLossAudioEFCMic2DTO(float upLossAudioEFCMic2DTO) {
//		this.upLossAudioEFCMic2DTO = upLossAudioEFCMic2DTO;
//	}
//	public float getDownLossAudioEFCMic2DTO() {
//		return downLossAudioEFCMic2DTO;
//	}
//	public void setDownLossAudioEFCMic2DTO(float downLossAudioEFCMic2DTO) {
//		this.downLossAudioEFCMic2DTO = downLossAudioEFCMic2DTO;
//	}
	public String getTimeStamps() {
		return timeStamps;
	}
	public void setTimeStamps(String timeStamps) {
		this.timeStamps = timeStamps;
	}
//	public String getDurations() {
//		return durations;
//	}
//	public void setDurations(String durations) {
//		this.durations = durations;
//	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public List<String> getSpeakerIdList() {
		return speakerIdList;
	}
	public void setSpeakerIdList(List<String> speakerIdList) {
		this.speakerIdList = speakerIdList;
	}
	public String getEndTimes() {
		return endTimes;
	}
	public void setEndTimes(String endTimes) {
		this.endTimes = endTimes;
	}
	public String getC2cquality() {
		return c2cquality;
	}
	public void setC2cquality(String c2cquality) {
		this.c2cquality = c2cquality;
	}
	public String getQosTableName() {
		return qosTableName;
	}
	public void setQosTableName(String qosTableName) {
		this.qosTableName = qosTableName;
	}
	
}
