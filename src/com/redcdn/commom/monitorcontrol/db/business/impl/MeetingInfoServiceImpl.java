package com.redcdn.commom.monitorcontrol.db.business.impl;

import com.redcdn.commom.BeanWrapperUtils;
import com.redcdn.commom.monitorcontrol.convertor.MeetingInfoConvert;
import com.redcdn.commom.monitorcontrol.db.business.MeetingInfoService;
import com.redcdn.commom.monitorcontrol.db.dao.MeetingInfoDAO;
import com.redcdn.commom.monitorcontrol.db.dao.impl.MeetingInfoDAOImpl;
import com.redcdn.commom.monitorcontrol.db.entity.view.MeetingInfoResult;
import com.redcdn.commom.monitorcontrol.dto.MeetingInfoDTO;


public class MeetingInfoServiceImpl implements MeetingInfoService{

	private MeetingInfoDAO meetingInfoDAO = new MeetingInfoDAOImpl();
	@Override
	public MeetingInfoResult findByid(long startTime, long endTime,
			String userId, int meetingId, int relayId,
			int directionType, int pageSize, int currPage) {
		// TODO Auto-generated method stub
		MeetingInfoResult result = meetingInfoDAO.findByid(startTime, endTime, userId, meetingId, relayId,
				directionType, pageSize, currPage);
		result.setListMeetingInfoDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoEntities(), MeetingInfoDTO.class, new MeetingInfoConvert()));
		return result;
	}

	@Override
	public MeetingInfoResult findByStream(long startTime, long endTime,
			String videoSignBefore, float videoLossBeforeMin,
			float videoLossBeforeMax, String videoSignAfter,
			float videoLossAfterMin, float videoLossAfterMax,
			String audioSignBefore, float audioLossBeforeMin,
			float audioLossBeforeMax, String audioSignAfter,
			float audioLossAfterMin, float audioLossAfterMax,
			int directionType, int pageSize, int currPage) {
		// TODO Auto-generated method stub
		MeetingInfoResult result = meetingInfoDAO.findBySteam(startTime, endTime, videoSignBefore, videoLossBeforeMin, 
				videoLossBeforeMax, videoSignAfter, videoLossAfterMin, videoLossAfterMax, audioSignBefore, audioLossBeforeMin,
				audioLossBeforeMax, audioSignAfter, audioLossAfterMin, audioLossAfterMax, directionType, pageSize, currPage);
		result.setListMeetingInfoDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoEntities(), MeetingInfoDTO.class, new MeetingInfoConvert()));
		return result;
	}

	@Override
	public MeetingInfoResult findByQuality(long startTime, long endTime,
			String cpuRateSign, int cpuRateMin, int cpuRateMax,
			String frameRateSign, int frameRateMin, int frameRaterMax,
			String delaySign, int delayMin, int delayMax, int directionType,
			int pageSize, int currPage) {
		// TODO Auto-generated method stub
		MeetingInfoResult result = meetingInfoDAO.findByQuality(startTime, endTime, cpuRateSign, cpuRateMin,
				cpuRateMax, frameRateSign, frameRateMin, frameRaterMax, delaySign, delayMin, delayMax, 
				directionType, pageSize, currPage);
		result.setListMeetingInfoDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoEntities(), MeetingInfoDTO.class, new MeetingInfoConvert()));
		return result;
	}

	@Override
	public MeetingInfoResult findByEmptyPackage(long startTime, long endTime,
			String oneSign, int oneMin, int oneMax, String twoSign, int twoMin,
			int twoMax, String thrSign, int thrMin, int thrMax, String fouSign,
			int fouMin, int fouMax, String tenSign, int tenMin, int tenMax, int directionType,
			int pageSize, int currPage) {
		// TODO Auto-generated method stub
		MeetingInfoResult result = meetingInfoDAO.findByEmptyPackage(startTime, endTime, oneSign, oneMin, oneMax, twoSign, 
				twoMin, twoMax, thrSign, thrMin, thrMax, fouSign, fouMin, fouMax, tenSign, tenMin, tenMax, directionType,
				pageSize, currPage);
		result.setListMeetingInfoDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoEntities(), MeetingInfoDTO.class, new MeetingInfoConvert()));
		return result;
	}
	
	public MeetingInfoResult findByAll(long startTime,long endTime,
			String userId, int meetingId,int relayId,
			String videoSignBefore, float videoLossBeforeMin,float videoLossBeforeMax, String videoSignAfter, float videoLossAfterMin, float videoLossAfterMax, String audioSignBefore, float audioLossBeforeMin, float audioLossBeforeMax, String audioSignAfter, float audioLossAfterMin, float audioLossAfterMax, 
			String cpuRateSign, int cpuRateMin,int cpuRateMax, String frameRateSign, int frameRateMin, int frameRaterMax, String delaySign, int delayMin, int delayMax,
			String oneSign, int oneMin,int oneMax, String twoSign, int twoMin, int twoMax, String thrSign, int thrMin, int thrMax,String fouSign, int fouMin, int fouMax, String tenSign, int tenMin, int tenMax,
			int directionType,int pageSize, int currPage){
		MeetingInfoResult result = meetingInfoDAO.findByAll(startTime, endTime, userId, meetingId, relayId, 
				videoSignBefore, videoLossBeforeMin, videoLossBeforeMax, videoSignAfter, videoLossAfterMin, videoLossAfterMax, audioSignBefore, audioLossBeforeMin, audioLossBeforeMax, audioSignAfter, audioLossAfterMin, audioLossAfterMax,
				cpuRateSign, cpuRateMin, cpuRateMax, frameRateSign, frameRateMin, frameRaterMax, delaySign, delayMin, delayMax, 
				oneSign, oneMin, oneMax, twoSign, twoMin, twoMax, thrSign, thrMin, thrMax, fouSign, fouMin, fouMax, tenSign, tenMin, tenMax, 
				directionType, pageSize, currPage);
		result.setListMeetingInfoDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoEntities(), MeetingInfoDTO.class,new MeetingInfoConvert()));
		return result;
	}

//	@Override
//	public MeetingInfoResult findByMeetingId(int meetingId,int micId, String userId) {
//		// TODO Auto-generated method stub
//		MeetingInfoResult result = meetingInfoDAO.findByMeetingId(meetingId,micId,userId);
//		result.setListMeetingInfoDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoEntities(), MeetingInfoDTO.class, new MeetingInfoConvert()));
//		return result;
//	}

	@Override
	public MeetingInfoResult findByMeetingId(int meetingId,int micId, String speakerId,int pageSize, int currPage) {
		// TODO Auto-generated method stub
		MeetingInfoResult result = meetingInfoDAO.findByMeetingId(meetingId,micId,speakerId,pageSize,currPage);
		result.setListMeetingInfoDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoEntities(), MeetingInfoDTO.class, new MeetingInfoConvert()));
		return result;
	}
	
}
