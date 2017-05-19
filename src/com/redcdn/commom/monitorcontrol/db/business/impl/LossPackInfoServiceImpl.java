package com.redcdn.commom.monitorcontrol.db.business.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import com.mongodb.DB;
import com.redcdn.commom.BeanWrapperUtils;
import com.redcdn.commom.monitorcontrol.convertor.FormDataExcelConvert;
import com.redcdn.commom.monitorcontrol.convertor.LossPackInfoConvert;
import com.redcdn.commom.monitorcontrol.db.business.LossPackInfoService;
import com.redcdn.commom.monitorcontrol.db.dao.LossPackInfoDAO;
import com.redcdn.commom.monitorcontrol.db.dao.impl.LossPackInfoDAOImpl;
import com.redcdn.commom.monitorcontrol.db.entity.LossPackInfoEntity;
import com.redcdn.commom.monitorcontrol.db.entity.LossPackInfoForReportEntity;
import com.redcdn.commom.monitorcontrol.db.entity.view.LossPackInfoResult;
import com.redcdn.commom.monitorcontrol.dto.FormDataExcelDTO;
import com.redcdn.commom.monitorcontrol.dto.FormDataExcelListDTO;
import com.redcdn.commom.monitorcontrol.dto.LossPackInfoDTO;
import com.sun.istack.internal.logging.Logger;


public class LossPackInfoServiceImpl implements LossPackInfoService{
	
	private String meetingStartTime;
	private String meetingEndTime;
	private LossPackInfoDAO lossPackInfoDAO = new LossPackInfoDAOImpl();
	
	
	public String getMeetingStartTime() {
		return meetingStartTime;
	}
	@Override
	public void setMeetingStartTime(String meetingStartTime) {
		this.meetingStartTime = meetingStartTime;
	}
	public String getMeetingEndTime() {
		return meetingEndTime;
	}
	
	@Override
	public void setMeetingEndTime(String meetingEndTime) {
		this.meetingEndTime = meetingEndTime;
	}
	
	
	@Override
	public LossPackInfoResult findByUserId(int meetingId, String speakerId,
			String userId, int pageSize, int currPage) {

		LossPackInfoResult result = new LossPackInfoResult();
		List<LossPackInfoEntity> entities = new ArrayList<LossPackInfoEntity>();
		Set<Integer> mediaTypeList = new HashSet<Integer>();
		List<String> tabs = getQueryTabs(meetingStartTime, meetingEndTime);
		//List<String> tabs = getTableNames(userId);
		// result = checkTabName(tabs);
		/*if(result.getErrorCode() != 0){
			return result;
		}*/
		if (tabs.size() == 0) {
			return result;
		}
		for (String tab : tabs) {
			if (tab != null && !tab.equals("")) {
				lossPackInfoDAO.setCollectionName(tab);
				LossPackInfoResult result1 = lossPackInfoDAO.findByUserId(meetingId, speakerId, userId,pageSize,currPage);
				entities.addAll(result1.getListLossPackInfoEntities());
				mediaTypeList.addAll(result1.getMediaTypeList());
			}
		}
		result.setListLossPackInfoEntities(entities);
		result.setMediaTypeList(mediaTypeList);
		result.setListLossPackInfoDtos(BeanWrapperUtils.convertList(result.getListLossPackInfoEntities(), LossPackInfoDTO.class, new LossPackInfoConvert()));
		return result;
	}
	
	@Override
	public LossPackInfoResult findByUserId(int meetingId, String speakerId,
			String userId, int pageSize, int currPage, String qosTableNames) {
		// TODO Auto-generated method stub
		LossPackInfoResult result = new LossPackInfoResult();
		List<LossPackInfoEntity> entities = new ArrayList<LossPackInfoEntity>();
		Set<Integer> mediaTypeList = new HashSet<Integer>();
		//List<String> tabs = getQueryTabs(meetingStartTime, meetingEndTime);
		List<String> tabs = getTableNames(qosTableNames);
		// result = checkTabName(tabs);
		/*if(result.getErrorCode() != 0){
			return result;
		}*/
		if (tabs.size() == 0) {
			return result;
		}
		for (String tab : tabs) {
			if (tab != null && !tab.equals("")) {
				System.out.println("测试static表名："+tab);
				lossPackInfoDAO.setCollectionName(tab);
				LossPackInfoResult result1 = lossPackInfoDAO.findByUserId(meetingId, speakerId, userId,pageSize,currPage);
				entities.addAll(result1.getListLossPackInfoEntities());
				mediaTypeList.addAll(result1.getMediaTypeList());
			}
		}
		result.setListLossPackInfoEntities(entities);
		result.setMediaTypeList(mediaTypeList);
		result.setListLossPackInfoDtos(BeanWrapperUtils.convertList(result.getListLossPackInfoEntities(), LossPackInfoDTO.class, new LossPackInfoConvert()));
		return result;
	}
	@Override
	public LossPackInfoResult findByMeetingId(int meetingId, String speakerId, int directionType) {
		LossPackInfoResult result = lossPackInfoDAO.findByMeetingId(meetingId,speakerId,directionType);
		result.setListLossPackInfoDtos(BeanWrapperUtils.convertList(result.getListLossPackInfoEntities(), LossPackInfoDTO.class, new LossPackInfoConvert()));
		return result;
	}
//	@Override
//	public LossPackInfoResult findByMeetingIds(List<Integer> list, String speakerId,
//			int directionType,long endTime) {
//		LossPackInfoResult result = lossPackInfoDAO.findByMeetingIds(list,speakerId,directionType,endTime);
//		result.setListLossPackInfoDtos(BeanWrapperUtils.convertList(result.getListLossPackInfoEntities(), LossPackInfoDTO.class, new LossPackInfoConvert()));
//		return result;
//	}
	@Override
	public List<FormDataExcelDTO> findByMeetingIds(List<Integer> list) {
		List<FormDataExcelDTO> dtos = BeanWrapperUtils.convertList(lossPackInfoDAO.findByMeetingIds(list), FormDataExcelDTO.class,new FormDataExcelConvert());
		return dtos;
	}
	@Override
	public List<FormDataExcelDTO> findByTime(long startTime, long endTime,
			int pageSize) {
		List<FormDataExcelDTO> list = new ArrayList<FormDataExcelDTO>();
		FormDataExcelListDTO dto = lossPackInfoDAO.findByTime(startTime, endTime, 1, pageSize, true);
		int total = dto.getTotalPage();
		if (total > 0) {
			list = BeanWrapperUtils.convertList(dto.getEntities(), FormDataExcelDTO.class,new FormDataExcelConvert());
			if (total > 1) {
				for (int i = 2; i <= total; i++){
					FormDataExcelListDTO dto2 = lossPackInfoDAO.findByTime(startTime, endTime, i, pageSize, false);
					list.addAll(BeanWrapperUtils.convertList(dto2.getEntities(), FormDataExcelDTO.class,new FormDataExcelConvert()));
				}
			}
			return list;
		}else {
			return list;
		}
		
	}
	@Override
	public List<FormDataExcelDTO> findByMeetingIds(List<Integer> meetingIds,
			int pageSize) {
		List<FormDataExcelDTO> list = new ArrayList<FormDataExcelDTO>();
		FormDataExcelListDTO dto = lossPackInfoDAO.findByMeetingIds(meetingIds, 1, pageSize, true);
		//FormDataExcelListDTO dto = lossPackInfoDAO.findByMeetingIds(meetingIds, 1, pageSize, true, index);
		int total = dto.getTotalPage();
		if (total > 0) {
			list = BeanWrapperUtils.convertList(dto.getEntities(), FormDataExcelDTO.class,new FormDataExcelConvert());
			if (total > 1) {
				for (int i = 2; i <= total; i++){
					FormDataExcelListDTO dto2 = lossPackInfoDAO.findByMeetingIds(meetingIds, i, pageSize, false);
					//FormDataExcelListDTO dto2 = lossPackInfoDAO.findByMeetingIds(meetingIds, i, pageSize, false,index);
					list.addAll(BeanWrapperUtils.convertList(dto2.getEntities(), FormDataExcelDTO.class,new FormDataExcelConvert()));
				}
			}
			return list;
		}else {
			return list;
		}
	}
	@Override
	public List<FormDataExcelDTO> findByMeetingIdsForTempVersion(
			List<Integer> meetingIds, int pageSize, int index) {
		List<FormDataExcelDTO> list = new ArrayList<FormDataExcelDTO>();
		//FormDataExcelListDTO dto = lossPackInfoDAO.findByMeetingIds(meetingIds, 1, pageSize, true);
		FormDataExcelListDTO dto = lossPackInfoDAO.findByMeetingIds(meetingIds, 1, pageSize, true, index);
		int total = dto.getTotalPage();
		if (total > 0) {
			list = BeanWrapperUtils.convertList(dto.getEntities(), FormDataExcelDTO.class,new FormDataExcelConvert());
			if (total > 1) {
				for (int i = 2; i <= total; i++){
					//FormDataExcelListDTO dto2 = lossPackInfoDAO.findByMeetingIds(meetingIds, i, pageSize, false);
					FormDataExcelListDTO dto2 = lossPackInfoDAO.findByMeetingIds(meetingIds, i, pageSize, false,index);
					list.addAll(BeanWrapperUtils.convertList(dto2.getEntities(), FormDataExcelDTO.class,new FormDataExcelConvert()));
				}
			}
			return list;
		}else {
			return list;
		}
	}
/*	@Override
	public LossPackInfoResult findByMeetingIds(List<Integer> list, String speakerId,
			int directionType, long endTime, String[] userList) {
		LossPackInfoResult result = new LossPackInfoResult();
		List<String> tabs = getQueryTabs(meetingStartTime, meetingEndTime);
		if (tabs.size() == 0) {
			return result;
		}
		Map<String, LossPackInfoForReportEntity> map = new HashMap<String, LossPackInfoForReportEntity>();
		Map<String, LossPackInfoForReportEntity> map1;
		for (String tab : tabs) {
			if (tab != null && !tab.equals("")) {
				lossPackInfoDAO.setCollectionName(tab);
				//LossPackInfoResult result1 = lossPackInfoDAO.findByUserId(meetingId, speakerId, userId,pageSize,currPage);
				map1 = lossPackInfoDAO.findByMeetingIds(list,speakerId,directionType,endTime,userList);
				for (Map.Entry<String, LossPackInfoForReportEntity> entry : map1.entrySet()) {
					if(map.containsKey(entry.getKey()))
						map.get(entry.getKey()).list.addAll(entry.getValue().list);
					else 
						map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		int i = 0;
		for(LossPackInfoForReportEntity entity : map.values()){
			if(i == 0){
				result.setListLossPackInfoEntities(entity.list);
				++i;
			}else {
				result.getListLossPackInfoEntities().addAll(entity.list);
			}
			//result.getListLossPackInfoEntities().addAll(entity.list);
		}
		map  = null;
		map1 = null;
		result.setCount(result.getListLossPackInfoEntities().size());
		result.setListLossPackInfoDtos(BeanWrapperUtils.convertList(result.getListLossPackInfoEntities(), LossPackInfoDTO.class, new LossPackInfoConvert()));
		return result;
	}*/
	/**
	 * 2017-01-08 修改查询机制（在DAO层中）
	 */
	@Override
	public LossPackInfoResult findByMeetingIds(List<Integer> list, String speakerId,
			int directionType, long endTime, String[] userList) {
		LossPackInfoResult result = new LossPackInfoResult();
		List<String> tabs = getQueryTabs(meetingStartTime, meetingEndTime);
		if (tabs.size() == 0) {
			return result;
		}
		Map<String, LossPackInfoForReportEntity> map = new HashMap<String, LossPackInfoForReportEntity>();
		Map<String, LossPackInfoForReportEntity> map1;
		for (String tab : tabs) {
			if (tab != null && !tab.equals("")) {
				lossPackInfoDAO.setCollectionName(tab);
				//LossPackInfoResult result1 = lossPackInfoDAO.findByUserId(meetingId, speakerId, userId,pageSize,currPage);
				map1 = lossPackInfoDAO.findByMeetingIds(list,speakerId,directionType,endTime,userList);
				for (Map.Entry<String, LossPackInfoForReportEntity> entry : map1.entrySet()) {
					if(map.containsKey(entry.getKey()))
						map.get(entry.getKey()).list.addAll(entry.getValue().list);
					else 
						map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		int i = 0;
		for(LossPackInfoForReportEntity entity : map.values()){
			if(i == 0){
				result.setListLossPackInfoEntities(entity.list);
				++i;
			}else {
				result.getListLossPackInfoEntities().addAll(entity.list);
			}
			//result.getListLossPackInfoEntities().addAll(entity.list);
		}
		map  = null;
		map1 = null;
		result.setCount(result.getListLossPackInfoEntities().size());
		result.setListLossPackInfoDtos(BeanWrapperUtils.convertList(result.getListLossPackInfoEntities(), LossPackInfoDTO.class, new LossPackInfoConvert()));
		return result;
	}
	/*@Override
	public LossPackInfoResult findByMeetingIds(List<JSONObject> list, String speakerId,
			int directionType, long endTime, String[] userList) {
		LossPackInfoResult result = new LossPackInfoResult();
		List<String> tabs = getQueryTabs(meetingStartTime, meetingEndTime);
		if (tabs.size() == 0) {
			return result;
		}
		List<Integer> meetingList = new ArrayList<Integer>();
		for(JSONObject jsonObject : list){
			meetingList.add(jsonObject.getInt("meetingId"));
		}
		Map<String, LossPackInfoForReportEntity> map = new HashMap<String, LossPackInfoForReportEntity>();
		Map<String, LossPackInfoForReportEntity> map1;
		for (String tab : tabs) {
			if (tab != null && !tab.equals("")) {
				lossPackInfoDAO.setCollectionName(tab);
				//LossPackInfoResult result1 = lossPackInfoDAO.findByUserId(meetingId, speakerId, userId,pageSize,currPage);
				map1 = lossPackInfoDAO.findByMeetingIds(meetingList,speakerId,directionType,endTime,userList);
				for (Map.Entry<String, LossPackInfoForReportEntity> entry : map1.entrySet()) {
					if(map.containsKey(entry.getKey()))
						map.get(entry.getKey()).list.addAll(entry.getValue().list);
					else 
						map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		int i = 0;
		for(LossPackInfoForReportEntity entity : map.values()){
			if(i == 0){
				result.setListLossPackInfoEntities(entity.list);
				++i;
			}else {
				result.getListLossPackInfoEntities().addAll(entity.list);
			}
			//result.getListLossPackInfoEntities().addAll(entity.list);
		}
		map  = null;
		map1 = null;
		result.setCount(result.getListLossPackInfoEntities().size());
		result.setListLossPackInfoDtos(BeanWrapperUtils.convertList(result.getListLossPackInfoEntities(), LossPackInfoDTO.class, new LossPackInfoConvert()));
		return result;
	}*/
/*	@Override
	public LossPackInfoResult findByMeetingIds(List<Integer> list, String speakerId,
			int directionType, long endTime, String[] userList,String qosTableNames) {
		LossPackInfoResult result = new LossPackInfoResult();
		List<String> tabs = getQueryTabs(meetingStartTime, meetingEndTime);
		if (tabs.size() == 0) {
			return result;
		}
		Map<String, LossPackInfoForReportEntity> map = new HashMap<String, LossPackInfoForReportEntity>();
		Map<String, LossPackInfoForReportEntity> map1;
		for (String tab : tabs) {
			if (tab != null && !tab.equals("")) {
				lossPackInfoDAO.setCollectionName(tab);
				//LossPackInfoResult result1 = lossPackInfoDAO.findByUserId(meetingId, speakerId, userId,pageSize,currPage);
				map1 = lossPackInfoDAO.findByMeetingIds(list,speakerId,directionType,endTime,userList);
				for (Map.Entry<String, LossPackInfoForReportEntity> entry : map1.entrySet()) {
					if(map.containsKey(entry.getKey()))
						map.get(entry.getKey()).list.addAll(entry.getValue().list);
					else 
						map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		int i = 0;
		for(LossPackInfoForReportEntity entity : map.values()){
			if(i == 0){
				result.setListLossPackInfoEntities(entity.list);
				++i;
			}else {
				result.getListLossPackInfoEntities().addAll(entity.list);
			}
			//result.getListLossPackInfoEntities().addAll(entity.list);
		}
		map  = null;
		map1 = null;
		result.setCount(result.getListLossPackInfoEntities().size());
		result.setListLossPackInfoDtos(BeanWrapperUtils.convertList(result.getListLossPackInfoEntities(), LossPackInfoDTO.class, new LossPackInfoConvert()));
		return result;
	}*/
	private List<String> getQueryTabs(String startTime,String endTime) {
		List<String> tabs = new ArrayList<String>();
		if (startTime == null || startTime == "" || endTime == null || endTime == "") {
			return tabs;
		}else {
			String tab ="";
			if (startTime.equals(endTime)) {
				tab = tabNameFormat(startTime);
				tabs.add(tab);
				return tabs;
			}else {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
				for(long longStartTime = timeToLong(startTime),longEndTime = timeToLong(endTime);longStartTime <= longEndTime;){
					if (longStartTime == 0L || longEndTime ==0L) {
						break;
					}
					tab = tabNameFormat(df.format(longStartTime));
					longStartTime += 24*3600*1000;
					tabs.add(tab);
				}
				return tabs;
			}
		}
	}
	private List<String> getTableNames(String qosTables){
		String[] dailyTable = qosTables.split("\\|");
		List<String> tableList = new ArrayList<String>();
		int StartTableNum = 0;
		int EndTableNum = 0;
		for(int i = 0; i < dailyTable.length; i++){
			String[] timeTable = dailyTable[i].split("/");
			String[] startTable = timeTable[0].split("_");
			String[] endTable = timeTable[1].split("_");
			/*if(Integer.parseInt(startTable[0]) < Integer.parseInt(meetingStartTime)){
				System.out.println("传入表名不合法，开始时间在会议开始时间之前");
				tableList.add("Illegal(-1)");
				return tableList;
			}*/
			/*if(Integer.parseInt(endTable[0]) > Integer.parseInt(meetingEndTime)){
				System.out.println("传入表名不合法，结束时间在会议结束时间之后");
				tableList.add("Illegal(-2)");
				return tableList;
			}*/
 			StartTableNum = Integer.parseInt(startTable[1]);
 			EndTableNum = Integer.parseInt(endTable[1]);
 			for(int j = StartTableNum; j <= EndTableNum ;j++){
 				tableList.add(startTable[0]+"_"+j);
 			}
		}
		return tableList;
	}
	private LossPackInfoResult checkTabName(List<String> tableList){
		//zhanghy 12-28 在result中添加一个错误码， -1 代表开始表名不合法，-2表示结束表名不合法
		LossPackInfoResult result = new LossPackInfoResult();
		if("Illegal(-1)".equals(tableList.get(0))){
			result.setErrorCode(-1);
			return result;
		}
		else if("Illegal(-2)".equals(tableList.get(0))){
			result.setErrorCode(-2);
			return result;
		}
		else{
			result.setErrorCode(0);
		}
		return result;
	}
	
	private String tabNameFormat(String time){
		String tab = "";
		if (time == null && time.equals("")) {
			return tab;
		}else {
			//tab = time.replace('-','');
			tab = time.replace("-","");
			return tab;
		}
	}
	
	private Long timeToLong(String time){
		try {
			Calendar c = Calendar.getInstance();
			//c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
			c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(time));
			return c.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}
}
