package com.redcdn.commom.monitorcontrol.db.business;

import java.util.List;

import com.redcdn.commom.monitorcontrol.db.entity.BandWithAdaptiveEntity;

public interface BandWithAdaptiveService {
	public List<BandWithAdaptiveEntity> findByUserId(int meetingId,int micId, String userId,int pageSize, int currPage);
	
	public List<BandWithAdaptiveEntity> findByRelayIp(int meetingId,int micId, String relayIp,int pageSize, int currPage);
}	
