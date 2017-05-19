package com.redcdn.commom.monitorcontrol.db.entity;

public class MultLossPackInfoEntity extends BaseEntity{

	private String    userId;
	private String    userIp;//用户IP
	private int       meetingId;//会议ID
	private String    relayIp;
	private int       relayId;
	private String    speakerId;
	private Long      timeStamp;             //记录时间戳
	private int       systemType;            // 会议系统 0 海军会诊系统
	private int       deviceType;            // 硬件类型,硬件类型-1未知，1 pc，2 手机，3 N8，4 X1先这么定
	private int       resourceId;
	private int       mediaType;
	private int       cpuRate;               //CPU使用率
	private int       frameRate;             //帧率
	private int       memRate;               //内存使用率
	private int       lossRateOriginal;      //下行丢包率(万分之几)Fec前
	private int       lossRateFEC;           //下行丢包率Fec后
	private int       lossRateFinal;         //下行最终丢包率
	private int       lossRateOriginalCC;    //整体丢包率(万分之几)Fec前
	private int       lossRateFECCC;         //整体丢包率Fec后
	private int       lossRateFinalCC;       //整体最终丢包率
	private int       expCodeRate;           //期望视频码率
	private int       curCodeRate;           //实际视频码率
	private int       maxCodeRate;           //最大值
	private int       stableCodeRate;        //稳定值
	private int       emptyPacketRate;       //空音包比率(万分之几)
	private int       oneEmpty;              //一个空心包出现的次数
	private int       twoEmpty;              //连续两个空心包出现的次数
	private int       thrEmpty;              //连续三个空心包出现的次数
	private int       fouEmpty;              //连续四个到10个空心包出现的次数
	private int       tenEmpty;              //连续10个以上的空心包出现的次数
	private int       waitTime;              //等待时间,ms
	private int       reTranTimes;           //重发次数
	private int       directionType;         //上下行 0上行,1下行
	private String    fecRateExp;            //期望视频FEC比例  
	private String    fecRateUse;            //实际视频FEC比例
	private int       bandwidth;             //客观带宽
	private int       traffic;               //流量
	private int       detectBandwidth;        //侦测带宽
	private String    inValidTimes;          //补发无效次数（数据/校验包/数据包重发/校验包重发）1:2|3:4 string 
	private String    mediaFormat;           //媒体格式640*480 
	private int       networkType;           //网络类型
	private int       networkParam1;         //网络参数1，wifi:信号强度
	private int       networkParam2;         //网络参数2, wifi:网络速率
	private String    delayTimeIntArray;     //延迟时间数组
	
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
	public String getRelayIp() {
		return relayIp;
	}
	public void setRelayIp(String relayIp) {
		this.relayIp = relayIp;
	}
	public int getRelayId() {
		return relayId;
	}
	public void setRelayId(int relayId) {
		this.relayId = relayId;
	}
	public String getSpeakerId() {
		return speakerId;
	}
	public void setSpeakerId(String speakerId) {
		this.speakerId = speakerId;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
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
	public int getCpuRate() {
		return cpuRate;
	}
	public void setCpuRate(int cpuRate) {
		this.cpuRate = cpuRate;
	}
	public int getFrameRate() {
		return frameRate;
	}
	public void setFrameRate(int frameRate) {
		this.frameRate = frameRate;
	}
	public int getMemRate() {
		return memRate;
	}
	public void setMemRate(int memRate) {
		this.memRate = memRate;
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
	public int getDirectionType() {
		return directionType;
	}
	public void setDirectionType(int directionType) {
		this.directionType = directionType;
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
	public int getDetectBandwidth() {
		return detectBandwidth;
	}
	public void setDetectBandwidth(int detectBandwidth) {
		this.detectBandwidth = detectBandwidth;
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
	public int getNetworkType() {
		return networkType;
	}
	public void setNetworkType(int networkType) {
		this.networkType = networkType;
	}
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
	public String getDelayTimeIntArray() {
		return delayTimeIntArray;
	}
	public void setDelayTimeIntArray(String delayTimeIntArray) {
		this.delayTimeIntArray = delayTimeIntArray;
	}
	
	
}
