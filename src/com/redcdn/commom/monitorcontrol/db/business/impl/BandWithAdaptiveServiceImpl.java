package com.redcdn.commom.monitorcontrol.db.business.impl;

import java.util.List;

import com.redcdn.commom.monitorcontrol.db.business.BandWithAdaptiveService;
import com.redcdn.commom.monitorcontrol.db.dao.BandWithAdaptiveDAO;
import com.redcdn.commom.monitorcontrol.db.dao.impl.BandWithAdaptiveDAOImpl;
import com.redcdn.commom.monitorcontrol.db.entity.BandWithAdaptiveEntity;

public class BandWithAdaptiveServiceImpl implements BandWithAdaptiveService{
	private BandWithAdaptiveDAO bandWithAdaptiveDAO= new BandWithAdaptiveDAOImpl();
	@Override
	public List<BandWithAdaptiveEntity> findByUserId(int meetingId, int micId,
			String userId, int pageSize, int currPage) {
		// TODO Auto-generated method stub
		return bandWithAdaptiveDAO.findByUserId(meetingId, micId, userId, pageSize, currPage);
	}
	
	@Override
	public List<BandWithAdaptiveEntity> findByRelayIp(int meetingId, int micId,
			String relayIp, int pageSize, int currPage) {
		// TODO Auto-generated method stub
		return bandWithAdaptiveDAO.findByRelayIp(meetingId, micId, relayIp, pageSize, currPage);
	}

}
