package com.redcdn.commom.monitorcontrol.db.business;

import java.util.List;

import com.redcdn.commom.monitorcontrol.db.entity.DataPacketQualityEntity;

public interface DataPacketQualityService {
	public List<DataPacketQualityEntity> findBykey(String key,int pageSize, int currPage);
}
