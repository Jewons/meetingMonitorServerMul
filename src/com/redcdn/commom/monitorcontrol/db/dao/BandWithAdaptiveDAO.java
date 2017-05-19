package com.redcdn.commom.monitorcontrol.db.dao;

import java.util.List;

import com.redcdn.commom.monitorcontrol.db.entity.BandWithAdaptiveEntity;

public interface BandWithAdaptiveDAO {
	public List<BandWithAdaptiveEntity> findByUserId(int meetingId,int micId, String userId,int pageSize, int currPage);
	
	public List<BandWithAdaptiveEntity> findByRelayIp(int meetingId,int micId, String relayIp,int pageSize, int currPage);
}
