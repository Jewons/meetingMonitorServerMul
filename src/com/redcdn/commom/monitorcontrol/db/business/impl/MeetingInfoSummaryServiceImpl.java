package com.redcdn.commom.monitorcontrol.db.business.impl;

import java.util.List;

import com.redcdn.commom.BeanWrapperUtils;
import com.redcdn.commom.monitorcontrol.convertor.MeetingInfoSummaryConvert;
import com.redcdn.commom.monitorcontrol.db.business.MeetingInfoSummaryService;
import com.redcdn.commom.monitorcontrol.db.dao.MeetingInfoSummaryDAO;
import com.redcdn.commom.monitorcontrol.db.dao.impl.MeetingInfoSummaryDAOImpl;
import com.redcdn.commom.monitorcontrol.db.entity.view.MeetingInfoSummaryResult;
import com.redcdn.commom.monitorcontrol.dto.MeetingInfoSummaryDTO;

public class MeetingInfoSummaryServiceImpl implements MeetingInfoSummaryService {

	private MeetingInfoSummaryDAO meetingInfoSummaryDAO = new MeetingInfoSummaryDAOImpl();
	@Override
	public MeetingInfoSummaryResult findByid(long startTime, long endTime,
			String userId, int meetingId, String relayId, int pageSize,
			int currPage) {
		// TODO Auto-generated method stub
		MeetingInfoSummaryResult result = meetingInfoSummaryDAO.findByid(startTime, endTime, userId, meetingId, relayId,
				pageSize, currPage);
		result.setListMeetingInfoSummaryDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoSummaryEntities(), MeetingInfoSummaryDTO.class, new MeetingInfoSummaryConvert()));
		return result;
	}
	@Override
	public MeetingInfoSummaryResult findByid(long startTime, long endTime,
			String userId, int meetingId, String relayId, int pageSize,
			int currPage, List<String> userList) {
		// TODO Auto-generated method stub
		MeetingInfoSummaryResult result = meetingInfoSummaryDAO.findByid(startTime, endTime, userId, meetingId, relayId,
				pageSize, currPage,userList);
		result.setListMeetingInfoSummaryDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoSummaryEntities(), MeetingInfoSummaryDTO.class, new MeetingInfoSummaryConvert()));
		return result;
	}
	@Override
	public MeetingInfoSummaryResult findByAttendCount(long startTime,
			long endTime, String attendCountSign, int attendCountMin,
			int attendCountMax, int pageSize, int currPage) {
		// TODO Auto-generated method stub
		MeetingInfoSummaryResult result = meetingInfoSummaryDAO.findByAttendCount(startTime, endTime, attendCountSign, attendCountMin, attendCountMax, pageSize, currPage);
		result.setListMeetingInfoSummaryDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoSummaryEntities(), MeetingInfoSummaryDTO.class, new MeetingInfoSummaryConvert()));
		return result;
	}
	@Override
	public MeetingInfoSummaryResult findByStream(long startTime, long endTime,
			String upVideoSignBefore, float upVideoLossBeforeMin,
			float upVideoLossBeforeMax, String upVideoSignAfter,
			float upVideoLossAfterMin, float upVideoLossAfterMax,
			String downVideoSignBefore, float downVideoLossBeforeMin,
			float downVideoLossBeforeMax, String downVideoSignAfter,
			float downVideoLossAfterMin, float downVideoLossAfterMax,
			String upAudioSignBefore, float upAudioLossBeforeMin,
			float upAudioLossBeforeMax, String upAudioSignAfter,
			float upAudioLossAfterMin, float upAudioLossAfterMax,
			String downAudioSignBefore, float downAudioLossBeforeMin,
			float downAudioLossBeforeMax, String downAudioSignAfter,
			float downAudioLossAfterMin, float downAudioLossAfterMax,
			int pageSize, int currPage) {
		// TODO Auto-generated method stub
		MeetingInfoSummaryResult result = meetingInfoSummaryDAO.findByStream(startTime, endTime, upVideoSignBefore, upVideoLossBeforeMin, upVideoLossBeforeMax, upVideoSignAfter, upVideoLossAfterMin, upVideoLossAfterMax
				,downVideoSignBefore, downVideoLossBeforeMin, downVideoLossBeforeMax, downVideoSignAfter, downVideoLossAfterMin, downVideoLossAfterMax, upAudioSignBefore, upAudioLossBeforeMin, upAudioLossBeforeMax, upAudioSignAfter, upAudioLossAfterMin, upAudioLossAfterMax
				,downAudioSignBefore, downAudioLossBeforeMin, downAudioLossBeforeMax, downAudioSignAfter, downAudioLossAfterMin, downAudioLossAfterMax, pageSize, currPage);;
		result.setListMeetingInfoSummaryDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoSummaryEntities(), MeetingInfoSummaryDTO.class, new MeetingInfoSummaryConvert()));
		return result;
	}
	@Override
	public MeetingInfoSummaryResult findByTime(long startTime, long endTime,
			String ducationsign, long ducationMin, long ducationMax,
			int pageSize, int currPage) {
		MeetingInfoSummaryResult result = meetingInfoSummaryDAO.findByTime(startTime, endTime, ducationsign ,ducationMin,ducationMax,pageSize, currPage);
		result.setListMeetingInfoSummaryDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoSummaryEntities(), MeetingInfoSummaryDTO.class, new MeetingInfoSummaryConvert()));
		return result;
	}

	@Override
	public MeetingInfoSummaryResult findByVideoStream(long startTime,
			long endTime, String upVideoSignBefore, float upVideoLossBeforeMin,
			float upVideoLossBeforeMax, String upVideoSignAfter,
			float upVideoLossAfterMin, float upVideoLossAfterMax,
			String upVideoSignFinal, float upVideoLossFinalMin,
			float upVideoLossFinalMax, String downVideoSignBefore,
			float downVideoLossBeforeMin, float downVideoLossBeforeMax,
			String downVideoSignAfter, float downVideoLossAfterMin,
			float downVideoLossAfterMax, String downVideoSignFinal,
			float downVideoLossFinalMin, float downVideoLossFinalMax,
			String videoSignBefore, float videoLossBeforeMin,
			float videoLossBeforeMax, String videoSignAfter,
			float videoLossAfterMin, float videoLossAfterMax,
			String videoSignFinal, float videoLossFinalMin,
			float videoLossFinalMax, int pageSize, int currPage) {
		// TODO Auto-generated method stub
		MeetingInfoSummaryResult result = meetingInfoSummaryDAO.findByVideoStream(startTime, endTime, 
				upVideoSignBefore, upVideoLossBeforeMin, upVideoLossBeforeMax, upVideoSignAfter, upVideoLossAfterMin, upVideoLossAfterMax ,upVideoSignFinal, upVideoLossFinalMin, upVideoLossFinalMax
				,downVideoSignBefore, downVideoLossBeforeMin, downVideoLossBeforeMax, downVideoSignAfter, downVideoLossAfterMin, downVideoLossAfterMax,downVideoSignFinal, downVideoLossFinalMin, downVideoLossFinalMax
				,videoSignBefore, videoLossBeforeMin, videoLossBeforeMax, videoSignAfter, videoLossAfterMin, videoLossAfterMax,videoSignFinal, videoLossFinalMin, videoLossFinalMax
				, pageSize, currPage);
		result.setListMeetingInfoSummaryDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoSummaryEntities(), MeetingInfoSummaryDTO.class, new MeetingInfoSummaryConvert()));
		return result;
	}
	@Override
	public MeetingInfoSummaryResult findByAudioStream(long startTime,
			long endTime, String upAudioSignBefore, float upAudioLossBeforeMin,
			float upAudioLossBeforeMax, String upAudioSignAfter,
			float upAudioLossAfterMin, float upAudioLossAfterMax,
			String upAudioSignFinal, float upAudioLossFinalMin,
			float upAudioLossFinalMax, String downAudioSignBefore,
			float downAudioLossBeforeMin, float downAudioLossBeforeMax,
			String downAudioSignAfter, float downAudioLossAfterMin,
			float downAudioLossAfterMax, String downAudioSignFinal,
			float downAudioLossFinalMin, float downAudioLossFinalMax,
			String audioSignBefore, float audioLossBeforeMin,
			float audioLossBeforeMax, String audioSignAfter,
			float audioLossAfterMin, float audioLossAfterMax,
			String audioSignFinal, float audioLossFinalMin,
			float audioLossFinalMax, int pageSize, int currPage) {
		// TODO Auto-generated method stub
		MeetingInfoSummaryResult result = meetingInfoSummaryDAO.findByAudioStream(startTime, endTime, 
				upAudioSignBefore, upAudioLossBeforeMin, upAudioLossBeforeMax, upAudioSignAfter, upAudioLossAfterMin, upAudioLossAfterMax ,upAudioSignFinal, upAudioLossFinalMin, upAudioLossFinalMax
				,downAudioSignBefore, downAudioLossBeforeMin, downAudioLossBeforeMax, downAudioSignAfter, downAudioLossAfterMin, downAudioLossAfterMax,downAudioSignFinal, downAudioLossFinalMin, downAudioLossFinalMax
				,audioSignBefore, audioLossBeforeMin, audioLossBeforeMax, audioSignAfter, audioLossAfterMin, audioLossAfterMax,audioSignFinal, audioLossFinalMin, audioLossFinalMax
				, pageSize, currPage);
		result.setListMeetingInfoSummaryDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoSummaryEntities(), MeetingInfoSummaryDTO.class, new MeetingInfoSummaryConvert()));
		return result;
	}
	@Override
	public MeetingInfoSummaryResult findByAll(long startTime, long endTime,
			String userId, int meetingId, String relayId,
			String attendCountSign, int attendCountMin, int attendCountMax,
			String ducationsign, long ducationMin, long ducationMax,
			String upVideoSignBefore, float upVideoLossBeforeMin,
			float upVideoLossBeforeMax, String upVideoSignAfter,
			float upVideoLossAfterMin, float upVideoLossAfterMax,
			String upVideoSignFinal, float upVideoLossFinalMin,
			float upVideoLossFinalMax, String downVideoSignBefore,
			float downVideoLossBeforeMin, float downVideoLossBeforeMax,
			String downVideoSignAfter, float downVideoLossAfterMin,
			float downVideoLossAfterMax, String downVideoSignFinal,
			float downVideoLossFinalMin, float downVideoLossFinalMax,
			String videoSignBefore, float videoLossBeforeMin,
			float videoLossBeforeMax, String videoSignAfter,
			float videoLossAfterMin, float videoLossAfterMax,
			String videoSignFinal, float videoLossFinalMin,
			float videoLossFinalMax, String upAudioSignBefore,
			float upAudioLossBeforeMin, float upAudioLossBeforeMax,
			String upAudioSignAfter, float upAudioLossAfterMin,
			float upAudioLossAfterMax, String upAudioSignFinal,
			float upAudioLossFinalMin, float upAudioLossFinalMax,
			String downAudioSignBefore, float downAudioLossBeforeMin,
			float downAudioLossBeforeMax, String downAudioSignAfter,
			float downAudioLossAfterMin, float downAudioLossAfterMax,
			String downAudioSignFinal, float downAudioLossFinalMin,
			float downAudioLossFinalMax, String audioSignBefore,
			float audioLossBeforeMin, float audioLossBeforeMax,
			String audioSignAfter, float audioLossAfterMin,
			float audioLossAfterMax, String audioSignFinal,
			float audioLossFinalMin, float audioLossFinalMax, int pageSize,
			int currPage) {
		// TODO Auto-generated method stub
		MeetingInfoSummaryResult result = meetingInfoSummaryDAO.findByAll(startTime, endTime
				,userId, meetingId, relayId
				,attendCountSign, attendCountMin,attendCountMax
				,ducationsign ,ducationMin,ducationMax
				,upVideoSignBefore, upVideoLossBeforeMin, upVideoLossBeforeMax, upVideoSignAfter, upVideoLossAfterMin, upVideoLossAfterMax ,upVideoSignFinal, upVideoLossFinalMin, upVideoLossFinalMax
				,downVideoSignBefore, downVideoLossBeforeMin, downVideoLossBeforeMax, downVideoSignAfter, downVideoLossAfterMin, downVideoLossAfterMax,downVideoSignFinal, downVideoLossFinalMin, downVideoLossFinalMax
				,videoSignBefore, videoLossBeforeMin, videoLossBeforeMax, videoSignAfter, videoLossAfterMin, videoLossAfterMax,videoSignFinal, videoLossFinalMin, videoLossFinalMax
				,upAudioSignBefore, upAudioLossBeforeMin, upAudioLossBeforeMax, upAudioSignAfter, upAudioLossAfterMin, upAudioLossAfterMax ,upAudioSignFinal, upAudioLossFinalMin, upAudioLossFinalMax
				,downAudioSignBefore, downAudioLossBeforeMin, downAudioLossBeforeMax, downAudioSignAfter, downAudioLossAfterMin, downAudioLossAfterMax,downAudioSignFinal, downAudioLossFinalMin, downAudioLossFinalMax
				,audioSignBefore, audioLossBeforeMin, audioLossBeforeMax, audioSignAfter, audioLossAfterMin, audioLossAfterMax,audioSignFinal, audioLossFinalMin, audioLossFinalMax
				,pageSize, currPage);
		result.setListMeetingInfoSummaryDtos(BeanWrapperUtils.convertList(result.getListMeetingInfoSummaryEntities(), MeetingInfoSummaryDTO.class, new MeetingInfoSummaryConvert()));
		return result;
	}
}
