package com.redcdn.commom.monitorcontrol.db.business.impl;

import java.util.List;

import com.redcdn.commom.monitorcontrol.db.business.DataPacketQualityService;
import com.redcdn.commom.monitorcontrol.db.dao.DataPacketQualityDAO;
import com.redcdn.commom.monitorcontrol.db.dao.impl.DataPacketQualityDAOImpl;
import com.redcdn.commom.monitorcontrol.db.entity.DataPacketQualityEntity;
import com.sun.istack.internal.logging.Logger;

public class DataPacketQualityServiceImpl implements DataPacketQualityService {

	private DataPacketQualityDAO dataPacketQualityDAO = new DataPacketQualityDAOImpl();
	private Logger logger = Logger.getLogger(this.getClass());
	@Override
	public List<DataPacketQualityEntity> findBykey(String key, int pageSize,
			int currPage) {
		logger.info("进入DataPacketQualityDAOImpl::findBykey");
		System.out.println("key:"+key);
		if (!key.equals("") && key != null) {
			String[] strings = key.split("-");
			if (strings.length > 0) {
				dataPacketQualityDAO.setCollectionName(strings[0]);
				logger.info("退出DataPacketQualityDAOImpl::findBykey");
				return dataPacketQualityDAO.findBykey(key, pageSize, currPage);
			}
		}
		logger.info("退出DataPacketQualityDAOImpl::findBykey");
		return null;
	}
}
