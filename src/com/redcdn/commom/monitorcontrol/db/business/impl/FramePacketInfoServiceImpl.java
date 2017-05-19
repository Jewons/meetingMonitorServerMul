package com.redcdn.commom.monitorcontrol.db.business.impl;

import com.redcdn.commom.BeanWrapperUtils;
import com.redcdn.commom.monitorcontrol.convertor.FramePacketInfoConvert;
import com.redcdn.commom.monitorcontrol.db.business.FramePacketInfoService;
import com.redcdn.commom.monitorcontrol.db.dao.FramePacketInfoDAO;
import com.redcdn.commom.monitorcontrol.db.dao.impl.FramePacketInfoDAOImpl;
import com.redcdn.commom.monitorcontrol.db.entity.view.FramePacketInfoResult;
import com.redcdn.commom.monitorcontrol.dto.FramePacketInfoDTO;

public class FramePacketInfoServiceImpl implements FramePacketInfoService{

	private FramePacketInfoDAO framePacketInfoDAO = new FramePacketInfoDAOImpl();
	@Override
	public FramePacketInfoResult findByTimeStamp(int meetingId, int micId,
			String speakerId, long startTime, long endTime, String senderId,
			String receiverId) {
		// TODO Auto-generated method stub
		FramePacketInfoResult result = framePacketInfoDAO.findByTimeStamp(meetingId, micId, speakerId, startTime, endTime, senderId, receiverId);
		result.setListFramePacketInfoDtos(BeanWrapperUtils.convertList(result.getListFramePacketInfoEntities(), FramePacketInfoDTO.class, new FramePacketInfoConvert()));
		return result;
	}

}
