package com.redcdn.commom.monitorcontrol.db.business.impl;

import com.redcdn.commom.BeanWrapperUtils;
import com.redcdn.commom.monitorcontrol.convertor.ResendReqConmandConvert;
import com.redcdn.commom.monitorcontrol.db.business.ResendReqConmandService;
import com.redcdn.commom.monitorcontrol.db.dao.ResendReqConmandDAO;
import com.redcdn.commom.monitorcontrol.db.dao.impl.ResendReqConmandDAOImpl;
import com.redcdn.commom.monitorcontrol.db.entity.view.ResendReqConmandResult;
import com.redcdn.commom.monitorcontrol.dto.ResendReqConmandDTO;

public class ResendReqConmandServiceImpl implements ResendReqConmandService {
	private ResendReqConmandDAO resendReqConmandDAO = new ResendReqConmandDAOImpl();
	@Override
	public ResendReqConmandResult findByTimeStamp(int meetingId, int micId,
			String speakerId, long startTime, long endTime, String senderId,
			String receiverId) {
		// TODO Auto-generated method stub
		ResendReqConmandResult result = resendReqConmandDAO.findByTimeStamp(meetingId, micId, speakerId, startTime, endTime, senderId, receiverId);
		result.setListResendReqConmandDtos(BeanWrapperUtils.convertList(result.getListResendReqConmandEntities(), ResendReqConmandDTO.class, new ResendReqConmandConvert()));
		return result;
	}

}
