package com.redcdn.commom.monitorcontrol.db.entity;

/**
 * 会议QOS信息数据库数据对象
 * @author wulei
 *
 */
public class LossPackInfoEntity extends BaseEntity{
	private String userId;
	private String userIp;//用户IP
	private int meetingId;//会议ID
	private int relayId;
	private String relayIp;
//	private int micId;
//	private int delayTime;//时延
	private int cpuRate;//CPU使用率
	private int FrameRate;//帧率
	private int memRate;//内存使用率
//	private int videolossRateOriginal;//视频丢包率(万分之几)Fec前
//	private int videolossRateFEC;//视频丢包率Fec后
//	private int videolossRateFinal;//视频最终丢包率
//	private int audiolossRateOriginal;//音频丢包率Fec前
//	private int audiolossRateFEC;//音频丢包率Fec后
//	private int audiolossRateFinal;//音频最终丢包率
//	private int videolossRateOriginalCC;//整体视频丢包率(万分之几)Fec前
//	private int videolossRateFECCC;//整体视频丢包率Fec后
//	private int videolossRateFinalCC;//整体视频最终丢包率
//	private int audiolossRateOriginalCC;//整体音频丢包率Fec前
//	private int audiolossRateFECCC;//整体音频丢包率Fec后
//	private int audiolossRateFinalCC;//整体音频最终丢包率
//	private int audioCoderateExp;//期望视频码率
//	private int audioCoderateUse;//实际音频码率
//	private int videoCoderateExp;//期望视频码率
//	private int videoCoderateUse;//实际视频码率
	private int emptyPacketRate;//空音包比率(万分之几)
	private int oneEmpty;//一个空心包出现的次数
	private int twoEmpty;//连续两个空心包出现的次数
	private int thrEmpty;//连续三个空心包出现的次数
	private int fouEmpty;//连续四个到10个空心包出现的次数
	private int tenEmpty;//连续10个以上的空心包出现的次数
	private Long timeStamp;//记录时间戳
	private int directionType;//上下行 0上行 1下行
//	private String fecrateExpVideo;//期望视频FEC比例  
//	private String fecrateUseVideo;//实际视频FEC比例
//	private String fecrateExpAudio;//期望音频FEC比例
//	private String fecrateUseAudio;//实际音频FEC比例
	private int bandwidth;//客观带宽
	private int traffic;//流量
	private int detectBandwidth;//侦测带宽
	private int networkType;//网络类型
//	private int audioWaitTime; //音频等待时间,ms
//	private int vedioWaitTime; //视频等待时间,ms
//	private int audioLaterRate;//音频迟到率,万分比
//	private int vedioLaterRate;//视频迟到率,万分比
	private int networkParam1; //网络参数1，wifi:信号强度
	private int networkParam2; //网络参数2, wifi:网络速率
	private String delayTimeIntArray;  //延迟时间数组
//	private int videoMaxCodeRate;    //最大值
//	private int videoStableCodeRate; //稳定值
//	private int videoCurCodeRate;    //当前值
	private int lossRateOriginal;//视频丢包率(万分之几)Fec前
	private int lossRateFEC;//视频丢包率Fec后
	private int lossRateFinal;//视频最终丢包率
	private int lossRateOriginalCC;//整体视频丢包率(万分之几)Fec前
	private int lossRateFECCC;//整体视频丢包率Fec后
	private int lossRateFinalCC;//整体视频最终丢包率
	private String inValidTimes;//补发无效次数（数据/校验包/数据包重发/校验包重发）1:2|3:4 string 
	private String mediaFormat;//媒体格式640*480 
	private int expCodeRate;//期望码率
	private int curCodeRate;//实际频码率
	private int maxCodeRate;//最大值
	private int stableCodeRate;//稳定值
	private int waitTime;//等待时间,ms
	private int reTranTimes;//重发次数
	private String fecRateExp;//期望视频FEC比例  
	private String fecRateUse;//实际视频FEC比例
	private int systemType; // 会议系统 0 海军会诊系统
	private int deviceType; // 硬件类型,硬件类型-1未知，1 pc，2 手机，3 N8，4 X1先这么定
	private int resourceId;
	private int mediaType; 
	private String speakerId;
	private Long   serverTime;//server时间戳
	private String keyLogId;  //日志key
	public String getDelayTimeIntArray() {
		return delayTimeIntArray;
	}
	public void setDelayTimeIntArray(String delayTimeIntArray) {
		this.delayTimeIntArray = delayTimeIntArray;
	}
//	public int getAudioWaitTime() {
//		return audioWaitTime;
//	}
//	public void setAudioWaitTime(int audioWaitTime) {
//		this.audioWaitTime = audioWaitTime;
//	}
//	public int getVedioWaitTime() {
//		return vedioWaitTime;
//	}
//	public void setVedioWaitTime(int vedioWaitTime) {
//		this.vedioWaitTime = vedioWaitTime;
//	}
//	public int getAudioLaterRate() {
//		return audioLaterRate;
//	}
//	public void setAudioLaterRate(int audioLaterRate) {
//		this.audioLaterRate = audioLaterRate;
//	}
//	public int getVedioLaterRate() {
//		return vedioLaterRate;
//	}
//	public void setVedioLaterRate(int vedioLaterRate) {
//		this.vedioLaterRate = vedioLaterRate;
//	}
	public int getNetworkParam1() {
		return networkParam1;
	}
	public void setNetworkParam1(int networkParam1) {
		this.networkParam1 = networkParam1;
	}
	public int getNetworkParam2() {
		return networkParam2;
	}
	public void setNetworkParam2(int networkParam2) {
		this.networkParam2 = networkParam2;
	}

//	public String getFecrateExpVideo() {
//		return fecrateExpVideo;
//	}
//	public void setFecrateExpVideo(String fecrateExpVideo) {
//		this.fecrateExpVideo = fecrateExpVideo;
//	}
//	public String getFecrateUseVideo() {
//		return fecrateUseVideo;
//	}
//	public void setFecrateUseVideo(String fecrateUseVideo) {
//		this.fecrateUseVideo = fecrateUseVideo;
//	}
//	public String getFecrateExpAudio() {
//		return fecrateExpAudio;
//	}
//	public void setFecrateExpAudio(String fecrateExpAudio) {
//		this.fecrateExpAudio = fecrateExpAudio;
//	}
//	public String getFecrateUseAudio() {
//		return fecrateUseAudio;
//	}
//	public void setFecrateUseAudio(String fecrateUseAudio) {
//		this.fecrateUseAudio = fecrateUseAudio;
//	}
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
//	public int getMicId() {
//		return micId;
//	}
//	public void setMicId(int micId) {
//		this.micId = micId;
//	}
//	public int getDelayTime() {
//		return delayTime;
//	}
//	public void setDelayTime(int delayTime) {
//		this.delayTime = delayTime;
//	}
	public int getCpuRate() {
		return cpuRate;
	}
	public void setCpuRate(int cpuRate) {
		this.cpuRate = cpuRate;
	}
	public int getFrameRate() {
		return FrameRate;
	}
	public void setFrameRate(int frameRate) {
		FrameRate = frameRate;
	}
	public int getMemRate() {
		return memRate;
	}
	public void setMemRate(int memRate) {
		this.memRate = memRate;
	}
//	public int getVideolossRateOriginal() {
//		return videolossRateOriginal;
//	}
//	public void setVideolossRateOriginal(int videolossRateOriginal) {
//		this.videolossRateOriginal = videolossRateOriginal;
//	}
//	public int getVideolossRateFEC() {
//		return videolossRateFEC;
//	}
//	public void setVideolossRateFEC(int videolossRateFEC) {
//		this.videolossRateFEC = videolossRateFEC;
//	}
//	public int getVideolossRateFinal() {
//		return videolossRateFinal;
//	}
//	public void setVideolossRateFinal(int videolossRateFinal) {
//		this.videolossRateFinal = videolossRateFinal;
//	}
//	public int getAudiolossRateOriginal() {
//		return audiolossRateOriginal;
//	}
//	public void setAudiolossRateOriginal(int audiolossRateOriginal) {
//		this.audiolossRateOriginal = audiolossRateOriginal;
//	}
//	public int getAudiolossRateFEC() {
//		return audiolossRateFEC;
//	}
//	public void setAudiolossRateFEC(int audiolossRateFEC) {
//		this.audiolossRateFEC = audiolossRateFEC;
//	}
//	public int getAudiolossRateFinal() {
//		return audiolossRateFinal;
//	}
//	public void setAudiolossRateFinal(int audiolossRateFinal) {
//		this.audiolossRateFinal = audiolossRateFinal;
//	}
//	public int getVideolossRateOriginalCC() {
//		return videolossRateOriginalCC;
//	}
//	public void setVideolossRateOriginalCC(int videolossRateOriginalCC) {
//		this.videolossRateOriginalCC = videolossRateOriginalCC;
//	}
//	public int getVideolossRateFECCC() {
//		return videolossRateFECCC;
//	}
//	public void setVideolossRateFECCC(int videolossRateFECCC) {
//		this.videolossRateFECCC = videolossRateFECCC;
//	}
//	public int getVideolossRateFinalCC() {
//		return videolossRateFinalCC;
//	}
//	public void setVideolossRateFinalCC(int videolossRateFinalCC) {
//		this.videolossRateFinalCC = videolossRateFinalCC;
//	}
//	public int getAudiolossRateOriginalCC() {
//		return audiolossRateOriginalCC;
//	}
//	public void setAudiolossRateOriginalCC(int audiolossRateOriginalCC) {
//		this.audiolossRateOriginalCC = audiolossRateOriginalCC;
//	}
//	public int getAudiolossRateFECCC() {
//		return audiolossRateFECCC;
//	}
//	public void setAudiolossRateFECCC(int audiolossRateFECCC) {
//		this.audiolossRateFECCC = audiolossRateFECCC;
//	}
//	public int getAudiolossRateFinalCC() {
//		return audiolossRateFinalCC;
//	}
//	public void setAudiolossRateFinalCC(int audiolossRateFinalCC) {
//		this.audiolossRateFinalCC = audiolossRateFinalCC;
//	}
//	public int getAudioCoderateExp() {
//		return audioCoderateExp;
//	}
//	public void setAudioCoderateExp(int audioCoderateExp) {
//		this.audioCoderateExp = audioCoderateExp;
//	}
//	public int getAudioCoderateUse() {
//		return audioCoderateUse;
//	}
//	public void setAudioCoderateUse(int audioCoderateUse) {
//		this.audioCoderateUse = audioCoderateUse;
//	}
//	public int getVideoCoderateExp() {
//		return videoCoderateExp;
//	}
//	public void setVideoCoderateExp(int videoCoderateExp) {
//		this.videoCoderateExp = videoCoderateExp;
//	}
//	public int getVideoCoderateUse() {
//		return videoCoderateUse;
//	}
//	public void setVideoCoderateUse(int videoCoderateUse) {
//		this.videoCoderateUse = videoCoderateUse;
//	}
	public int getEmptyPacketRate() {
		return emptyPacketRate;
	}
	public void setEmptyPacketRate(int emptyPacketRate) {
		this.emptyPacketRate = emptyPacketRate;
	}
	public int getOneEmpty() {
		return oneEmpty;
	}
	public void setOneEmpty(int oneEmpty) {
		this.oneEmpty = oneEmpty;
	}
	public int getTwoEmpty() {
		return twoEmpty;
	}
	public void setTwoEmpty(int twoEmpty) {
		this.twoEmpty = twoEmpty;
	}
	public int getThrEmpty() {
		return thrEmpty;
	}
	public void setThrEmpty(int thrEmpty) {
		this.thrEmpty = thrEmpty;
	}
	public int getFouEmpty() {
		return fouEmpty;
	}
	public void setFouEmpty(int fouEmpty) {
		this.fouEmpty = fouEmpty;
	}
	public int getTenEmpty() {
		return tenEmpty;
	}
	public void setTenEmpty(int tenEmpty) {
		this.tenEmpty = tenEmpty;
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
//	public int getDetectBandwith() {
//		return detectBandwith;
//	}
//	public void setDetectBandwith(int detectBandwith) {
//		this.detectBandwith = detectBandwith;
//	}
	public int getNetworkType() {
		return networkType;
	}
	public void setNetworkType(int networkType) {
		this.networkType = networkType;
	}
//	public int getVideoMaxCodeRate() {
//		return videoMaxCodeRate;
//	}
//	public void setVideoMaxCodeRate(int videoMaxCodeRate) {
//		this.videoMaxCodeRate = videoMaxCodeRate;
//	}
//	public int getVideoStableCodeRate() {
//		return videoStableCodeRate;
//	}
//	public void setVideoStableCodeRate(int videoStableCodeRate) {
//		this.videoStableCodeRate = videoStableCodeRate;
//	}
//	public int getVideoCurCodeRate() {
//		return videoCurCodeRate;
//	}
//	public void setVideoCurCodeRate(int videoCurCodeRate) {
//		this.videoCurCodeRate = videoCurCodeRate;
//	}
	public int getDetectBandwidth() {
		return detectBandwidth;
	}
	public void setDetectBandwidth(int detectBandwidth) {
		this.detectBandwidth = detectBandwidth;
	}
	public int getLossRateOriginal() {
		return lossRateOriginal;
	}
	public void setLossRateOriginal(int lossRateOriginal) {
		this.lossRateOriginal = lossRateOriginal;
	}
	public int getLossRateFEC() {
		return lossRateFEC;
	}
	public void setLossRateFEC(int lossRateFEC) {
		this.lossRateFEC = lossRateFEC;
	}
	public int getLossRateFinal() {
		return lossRateFinal;
	}
	public void setLossRateFinal(int lossRateFinal) {
		this.lossRateFinal = lossRateFinal;
	}
	public int getLossRateOriginalCC() {
		return lossRateOriginalCC;
	}
	public void setLossRateOriginalCC(int lossRateOriginalCC) {
		this.lossRateOriginalCC = lossRateOriginalCC;
	}
	public int getLossRateFECCC() {
		return lossRateFECCC;
	}
	public void setLossRateFECCC(int lossRateFECCC) {
		this.lossRateFECCC = lossRateFECCC;
	}
	public int getLossRateFinalCC() {
		return lossRateFinalCC;
	}
	public void setLossRateFinalCC(int lossRateFinalCC) {
		this.lossRateFinalCC = lossRateFinalCC;
	}
	public String getInValidTimes() {
		return inValidTimes;
	}
	public void setInValidTimes(String inValidTimes) {
		this.inValidTimes = inValidTimes;
	}
	public String getMediaFormat() {
		return mediaFormat;
	}
	public void setMediaFormat(String mediaFormat) {
		this.mediaFormat = mediaFormat;
	}
	public int getExpCodeRate() {
		return expCodeRate;
	}
	public void setExpCodeRate(int expCodeRate) {
		this.expCodeRate = expCodeRate;
	}
	public int getCurCodeRate() {
		return curCodeRate;
	}
	public void setCurCodeRate(int curCodeRate) {
		this.curCodeRate = curCodeRate;
	}
	public int getMaxCodeRate() {
		return maxCodeRate;
	}
	public void setMaxCodeRate(int maxCodeRate) {
		this.maxCodeRate = maxCodeRate;
	}
	public int getStableCodeRate() {
		return stableCodeRate;
	}
	public void setStableCodeRate(int stableCodeRate) {
		this.stableCodeRate = stableCodeRate;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	public int getReTranTimes() {
		return reTranTimes;
	}
	public void setReTranTimes(int reTranTimes) {
		this.reTranTimes = reTranTimes;
	}
	public String getFecRateExp() {
		return fecRateExp;
	}
	public void setFecRateExp(String fecRateExp) {
		this.fecRateExp = fecRateExp;
	}
	public String getFecRateUse() {
		return fecRateUse;
	}
	public void setFecRateUse(String fecRateUse) {
		this.fecRateUse = fecRateUse;
	}
	public int getSystemType() {
		return systemType;
	}
	public void setSystemType(int systemType) {
		this.systemType = systemType;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
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
	public Long getServerTime() {
		return serverTime;
	}
	public void setServerTime(Long serverTime) {
		this.serverTime = serverTime;
	}
	public String getKeyLogId() {
		return keyLogId;
	}
	public void setKeyLogId(String keyLogId) {
		this.keyLogId = keyLogId;
	}
	
}
