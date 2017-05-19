package com.redcdn.commom.monitorcontrol.db.entity;

import java.util.List;

public class MeetingInfoSummaryEntity extends BaseEntity{

	private int meetingId;//会议Id
	private long timeStamp;//会议结束时间
	private int userCount;//参会人数
	private List<String> userIdList;//userId
	private List<String> relayIdList;//relayId
	private long duration;//会议持续时间(毫秒级)
//	private int upLossVideoMic1;//上行mic1视频平均丢包率
//	private int downLossVideoMic1;//下行mic1视频平均丢包率	
//	private int upLossVideoMic2;//上行mic2视频平均丢包率
//	private int downLossVideoMic2;//下行mic2视频平均丢包率
//	private int upLossVideoEFCMic1;//上行mic1视频解FEC后平均丢包率
//	private int downLossVideoEFCMic1;//下行mic1视频解FEC后平均丢包率
//	private int upLossVideoEFCMic2;//上行mic2视频解FEC后平均丢包率
//	private int downLossVideoEFCMic2;//下行mic2视频解FEC后平均丢包率
//	private int upLossVideoFinalMic1;//上行mic1视频最终丢包率
//	private int downLossVideoFinalMic1;//下行mic1视频最终丢包率
//	private int upLossVideoFinalMic2;//上行mic2视频最终丢包率
//	private int downLossVideoFinalMic2;//下行mic2视频最终丢包率
//
//	private int upLossAudioMic1;//上行mic1音频平均丢包率
//	private int downLossAudioMic1;//下行mic2音频平均丢包率
//	private int upLossAudioMic2;//上行mic2音频平均丢包率
//	private int downLossAudioMic2;//下行mic2音频平均丢包率
//	private int upLossAudioEFCMic1;//上行mic1音频解FEC后平均丢包率
//	private int downLossAudioEFCMic1;//下行mic1音频解FEC后平均丢包率
//	private int upLossAudioEFCMic2;//上行mic2音频解FEC后平均丢包率
//	private int downLossAudioEFCMic2;//下行mic2音频解FEC后平均丢包率
//	private int upLossAudioFinalMic1;//上行mic1音频最终丢包率
//	private int downLossAudioFinalMic1;//下行mic1音频最终丢包率
//	private int upLossAudioFinalMic2;//上行mic2音频最终丢包率
//	private int downLossAudioFinalMic2;//下行mic2音频最终丢包率
//	
//	//8.5新增
//	private int lossVideoMic1CC;//下行mic1视频整体原始丢包率
//	private int lossVideoEFCMic1CC;//下行mic1视频整体FEC后丢包率
//	private int lossVideoMic1FinalCC;//下行mic1视频整体最终丢包率
//	private int lossVideoMic2CC;//下行mic2视频整体原始丢包率
//	private int lossVideoEFCMic2CC;//下行mic2视频整体FEC后丢包率
//	private int lossVideoMic2FinalCC;//下行mic2视频整体最终丢包率
//	private int lossAudioMic1CC;//下行mic1音频整体原始丢包率
//	private int lossAudioEFCMic1CC;//下行mic1音频整体FEC后丢包率
//	private int lossAudioMic1FinalCC;//下行mic1音频整体最终丢包率
//	private int lossAudioMic2CC;//下行mic2音频整体原始丢包率
//	private int lossAudioEFCMic2CC;//下行mic2音频整体FEC后丢包率
//	private int lossAudioMic2FinalCC;//下行mic2音频整体最终丢包率

	//2016.4.22 新增
	private List<String> speakerIdList;//userId
	private long         endTime;
	//2016 12-29 zhanghy添加从数据库查询端到端质量监测以及概要表中的详情表的表名
	private String c2cquality;
	private String qosTableName;
//	public int getUpLossVideoFinalMic1() {
//		return upLossVideoFinalMic1;
//	}
//	public void setUpLossVideoFinalMic1(int upLossVideoFinalMic1) {
//		this.upLossVideoFinalMic1 = upLossVideoFinalMic1;
//	}
//	public int getDownLossVideoFinalMic1() {
//		return downLossVideoFinalMic1;
//	}
//	public void setDownLossVideoFinalMic1(int downLossVideoFinalMic1) {
//		this.downLossVideoFinalMic1 = downLossVideoFinalMic1;
//	}
//	public int getUpLossVideoFinalMic2() {
//		return upLossVideoFinalMic2;
//	}
//	public void setUpLossVideoFinalMic2(int upLossVideoFinalMic2) {
//		this.upLossVideoFinalMic2 = upLossVideoFinalMic2;
//	}
//	public int getDownLossVideoFinalMic2() {
//		return downLossVideoFinalMic2;
//	}
//	public void setDownLossVideoFinalMic2(int downLossVideoFinalMic2) {
//		this.downLossVideoFinalMic2 = downLossVideoFinalMic2;
//	}
//	public int getUpLossAudioFinalMic1() {
//		return upLossAudioFinalMic1;
//	}
//	public void setUpLossAudioFinalMic1(int upLossAudioFinalMic1) {
//		this.upLossAudioFinalMic1 = upLossAudioFinalMic1;
//	}
//	public int getDownLossAudioFinalMic1() {
//		return downLossAudioFinalMic1;
//	}
//	public void setDownLossAudioFinalMic1(int downLossAudioFinalMic1) {
//		this.downLossAudioFinalMic1 = downLossAudioFinalMic1;
//	}
//	public int getUpLossAudioFinalMic2() {
//		return upLossAudioFinalMic2;
//	}
//	public void setUpLossAudioFinalMic2(int upLossAudioFinalMic2) {
//		this.upLossAudioFinalMic2 = upLossAudioFinalMic2;
//	}
//	public int getDownLossAudioFinalMic2() {
//		return downLossAudioFinalMic2;
//	}
//	public void setDownLossAudioFinalMic2(int downLossAudioFinalMic2) {
//		this.downLossAudioFinalMic2 = downLossAudioFinalMic2;
//	}
//	public int getLossVideoMic1CC() {
//		return lossVideoMic1CC;
//	}
//	public void setLossVideoMic1CC(int lossVideoMic1CC) {
//		this.lossVideoMic1CC = lossVideoMic1CC;
//	}
//	public int getLossVideoEFCMic1CC() {
//		return lossVideoEFCMic1CC;
//	}
//	public void setLossVideoEFCMic1CC(int lossVideoEFCMic1CC) {
//		this.lossVideoEFCMic1CC = lossVideoEFCMic1CC;
//	}
//	public int getLossVideoMic1FinalCC() {
//		return lossVideoMic1FinalCC;
//	}
//	public void setLossVideoMic1FinalCC(int lossVideoMic1FinalCC) {
//		this.lossVideoMic1FinalCC = lossVideoMic1FinalCC;
//	}
//	public int getLossVideoMic2CC() {
//		return lossVideoMic2CC;
//	}
//	public void setLossVideoMic2CC(int lossVideoMic2CC) {
//		this.lossVideoMic2CC = lossVideoMic2CC;
//	}
//	public int getLossVideoEFCMic2CC() {
//		return lossVideoEFCMic2CC;
//	}
//	public void setLossVideoEFCMic2CC(int lossVideoEFCMic2CC) {
//		this.lossVideoEFCMic2CC = lossVideoEFCMic2CC;
//	}
//	public int getLossVideoMic2FinalCC() {
//		return lossVideoMic2FinalCC;
//	}
//	public void setLossVideoMic2FinalCC(int lossVideoMic2FinalCC) {
//		this.lossVideoMic2FinalCC = lossVideoMic2FinalCC;
//	}
//	public int getLossAudioMic1CC() {
//		return lossAudioMic1CC;
//	}
//	public void setLossAudioMic1CC(int lossAudioMic1CC) {
//		this.lossAudioMic1CC = lossAudioMic1CC;
//	}
//	public int getLossAudioEFCMic1CC() {
//		return lossAudioEFCMic1CC;
//	}
//	public void setLossAudioEFCMic1CC(int lossAudioEFCMic1CC) {
//		this.lossAudioEFCMic1CC = lossAudioEFCMic1CC;
//	}
//	public int getLossAudioMic1FinalCC() {
//		return lossAudioMic1FinalCC;
//	}
//	public void setLossAudioMic1FinalCC(int lossAudioMic1FinalCC) {
//		this.lossAudioMic1FinalCC = lossAudioMic1FinalCC;
//	}
//	public int getLossAudioMic2CC() {
//		return lossAudioMic2CC;
//	}
//	public void setLossAudioMic2CC(int lossAudioMic2CC) {
//		this.lossAudioMic2CC = lossAudioMic2CC;
//	}
//	public int getLossAudioEFCMic2CC() {
//		return lossAudioEFCMic2CC;
//	}
//	public void setLossAudioEFCMic2CC(int lossAudioEFCMic2CC) {
//		this.lossAudioEFCMic2CC = lossAudioEFCMic2CC;
//	}
//	public int getLossAudioMic2FinalCC() {
//		return lossAudioMic2FinalCC;
//	}
//	public void setLossAudioMic2FinalCC(int lossAudioMic2FinalCC) {
//		this.lossAudioMic2FinalCC = lossAudioMic2FinalCC;
//	}
	//7.24新增
//	private List<String> speakerIdListMic1;//mic1的发言者队列
//	private List<String> speakerIdListMic2;//mic2的发言者队列
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
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

//	public int getUpLossVideoMic1() {
//		return upLossVideoMic1;
//	}
//	public void setUpLossVideoMic1(int upLossVideoMic1) {
//		this.upLossVideoMic1 = upLossVideoMic1;
//	}
//	public int getDownLossVideoMic1() {
//		return downLossVideoMic1;
//	}
//	public void setDownLossVideoMic1(int downLossVideoMic1) {
//		this.downLossVideoMic1 = downLossVideoMic1;
//	}
//	public int getUpLossVideoMic2() {
//		return upLossVideoMic2;
//	}
//	public void setUpLossVideoMic2(int upLossVideoMic2) {
//		this.upLossVideoMic2 = upLossVideoMic2;
//	}
//	public int getDownLossVideoMic2() {
//		return downLossVideoMic2;
//	}
//	public void setDownLossVideoMic2(int downLossVideoMic2) {
//		this.downLossVideoMic2 = downLossVideoMic2;
//	}
//	public int getUpLossVideoEFCMic1() {
//		return upLossVideoEFCMic1;
//	}
//	public void setUpLossVideoEFCMic1(int upLossVideoEFCMic1) {
//		this.upLossVideoEFCMic1 = upLossVideoEFCMic1;
//	}
//	public int getDownLossVideoEFCMic1() {
//		return downLossVideoEFCMic1;
//	}
//	public void setDownLossVideoEFCMic1(int downLossVideoEFCMic1) {
//		this.downLossVideoEFCMic1 = downLossVideoEFCMic1;
//	}
//	public int getUpLossVideoEFCMic2() {
//		return upLossVideoEFCMic2;
//	}
//	public void setUpLossVideoEFCMic2(int upLossVideoEFCMic2) {
//		this.upLossVideoEFCMic2 = upLossVideoEFCMic2;
//	}
//	public int getDownLossVideoEFCMic2() {
//		return downLossVideoEFCMic2;
//	}
//	public void setDownLossVideoEFCMic2(int downLossVideoEFCMic2) {
//		this.downLossVideoEFCMic2 = downLossVideoEFCMic2;
//	}
//	public int getUpLossAudioMic1() {
//		return upLossAudioMic1;
//	}
//	public void setUpLossAudioMic1(int upLossAudioMic1) {
//		this.upLossAudioMic1 = upLossAudioMic1;
//	}
//	public int getDownLossAudioMic1() {
//		return downLossAudioMic1;
//	}
//	public void setDownLossAudioMic1(int downLossAudioMic1) {
//		this.downLossAudioMic1 = downLossAudioMic1;
//	}
//	public int getUpLossAudioMic2() {
//		return upLossAudioMic2;
//	}
//	public void setUpLossAudioMic2(int upLossAudioMic2) {
//		this.upLossAudioMic2 = upLossAudioMic2;
//	}
//	public int getDownLossAudioMic2() {
//		return downLossAudioMic2;
//	}
//	public void setDownLossAudioMic2(int downLossAudioMic2) {
//		this.downLossAudioMic2 = downLossAudioMic2;
//	}
//	public int getUpLossAudioEFCMic1() {
//		return upLossAudioEFCMic1;
//	}
//	public void setUpLossAudioEFCMic1(int upLossAudioEFCMic1) {
//		this.upLossAudioEFCMic1 = upLossAudioEFCMic1;
//	}
//	public int getDownLossAudioEFCMic1() {
//		return downLossAudioEFCMic1;
//	}
//	public void setDownLossAudioEFCMic1(int downLossAudioEFCMic1) {
//		this.downLossAudioEFCMic1 = downLossAudioEFCMic1;
//	}
//	public int getUpLossAudioEFCMic2() {
//		return upLossAudioEFCMic2;
//	}
//	public void setUpLossAudioEFCMic2(int upLossAudioEFCMic2) {
//		this.upLossAudioEFCMic2 = upLossAudioEFCMic2;
//	}
//	public int getDownLossAudioEFCMic2() {
//		return downLossAudioEFCMic2;
//	}
//	public void setDownLossAudioEFCMic2(int downLossAudioEFCMic2) {
//		this.downLossAudioEFCMic2 = downLossAudioEFCMic2;
//	}
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
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
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
