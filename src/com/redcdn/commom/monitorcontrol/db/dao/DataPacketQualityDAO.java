package com.redcdn.commom.monitorcontrol.db.dao;

import java.util.List;

import com.redcdn.commom.monitorcontrol.db.entity.DataPacketQualityEntity;

public interface DataPacketQualityDAO {
	public List<DataPacketQualityEntity> findBykey(String key,int pageSize, int currPage);
	
	public void setCollectionName(String tabName); 
}
