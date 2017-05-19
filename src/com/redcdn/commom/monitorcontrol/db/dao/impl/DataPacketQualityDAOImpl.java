package com.redcdn.commom.monitorcontrol.db.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.redcdn.commom.monitorcontrol.db.dao.DataPacketQualityDAO;
import com.redcdn.commom.monitorcontrol.db.entity.DataPacketQualityEntity;


public class DataPacketQualityDAOImpl extends BaseMeetingInfoDAO implements DataPacketQualityDAO {

	private Logger logger = Logger.getLogger(this.getClass());
	private String DATAPACKETQUALITY_TAB;
	@Override
	public List<DataPacketQualityEntity> findBykey(String key, int pageSize,
			int currPage) {
		List<DataPacketQualityEntity> list = new ArrayList<DataPacketQualityEntity>();
		try {
			DBCollection employee = db.getCollection(DATAPACKETQUALITY_TAB);
			DBCursor cur = null;
			BasicDBObject dbo=new BasicDBObject();//新建查询基类对象 dbo
			

			if (!"".equals(key)) {
				dbo.put("id", key);	
			}
			
			DBObject orderBy = new BasicDBObject("index", 1);

			cur = employee.find(dbo).sort(orderBy);
			DBObject dbObject = cur.explain();
			System.out.println("explainB:"+dbObject);
			list = dbCurToList(cur);
			cur.close();
			return list;
		} catch (Exception e) {
			logger.error("检索 findByUserId：",e);
		}
		finally{
			conn.destory(mongo, db);
		}
		return list;
	}

	@Override
	public void setCollectionName(String tabName) {
		this.DATAPACKETQUALITY_TAB = tabName;
	}
	
	private List<DataPacketQualityEntity> dbCurToList(DBCursor cursor){
		List<DataPacketQualityEntity> list = new ArrayList<DataPacketQualityEntity>();
		DataPacketQualityEntity entity = null;
		while (cursor.hasNext()) {
			//test
			DBObject dbObject = cursor.next();
			//System.out.println("entity:"+ dbObject.toString());
			if (dbObject != null){
				try {
					entity = getDataPacketQualityEntity(dbObject);
				} catch (Exception e) {
					logger.error(" dbCurToList：",e);
				}
				if(null != entity){
					list.add(entity);
				}
			}
	    }
		cursor.close();
		return list;
	}
	
	private DataPacketQualityEntity getDataPacketQualityEntity(DBObject dbObject) {
		DataPacketQualityEntity responseEntity = new DataPacketQualityEntity();
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		responseEntity.setData(dbObject.get("data")==null?"0":dbObject.get("data").toString());
		responseEntity.setId(dbObject.get("id")==null?"0":dbObject.get("id").toString());
		responseEntity.setIndex(Integer.parseInt(dbObject.get("index")==null?"0":dbObject.get("index").toString()));
		responseEntity.setRole(Integer.parseInt(dbObject.get("role")==null?"0":dbObject.get("role").toString()));
		return responseEntity;
	}
}
