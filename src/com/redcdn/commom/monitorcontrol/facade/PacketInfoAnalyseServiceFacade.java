package com.redcdn.commom.monitorcontrol.facade;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.redcdn.commom.monitorcontrol.db.business.FramePacketInfoService;
import com.redcdn.commom.monitorcontrol.db.business.MeetingInfoService;
import com.redcdn.commom.monitorcontrol.db.business.ResendReqConmandService;
import com.redcdn.commom.monitorcontrol.db.business.impl.FramePacketInfoServiceImpl;
import com.redcdn.commom.monitorcontrol.db.business.impl.MeetingInfoServiceImpl;
import com.redcdn.commom.monitorcontrol.db.business.impl.ResendReqConmandServiceImpl;
import com.redcdn.commom.monitorcontrol.db.entity.view.FramePacketInfoResult;
import com.redcdn.commom.monitorcontrol.db.entity.view.MeetingInfoResult;
import com.redcdn.commom.monitorcontrol.db.entity.view.ResendReqConmandResult;
import com.redcdn.commom.monitorcontrol.dto.FramePacketInfoDTO;
import com.redcdn.commom.monitorcontrol.dto.MeetingInfoDTO;
import com.redcdn.commom.monitorcontrol.dto.ResendReqConmandDTO;
import com.redcdn.commom.monitorcontrol.result.ItemsResult;

@Service
@Path("/PacketInfoAnalyse")
@SuppressWarnings("unchecked")
public class PacketInfoAnalyseServiceFacade {
	protected Logger logger = Logger.getLogger(this.getClass());
	private FramePacketInfoService framePacketInfoService = new FramePacketInfoServiceImpl();
	private ResendReqConmandService reqConmandService = new ResendReqConmandServiceImpl();
	@Path("/aboutTimeStamp")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemsResult<FramePacketInfoDTO> aboutTimeStamp(@FormParam("meetingId") int meetingId, 
			@FormParam("speakerId") String speakerId, 
			@FormParam("micId") int micId,
			@FormParam("startTime") long startTime,
			@FormParam("endTime") long endTime,
			@FormParam("senderId") String senderId,
			@FormParam("receiverId") String receiverId){
		ItemsResult<FramePacketInfoDTO> result = new ItemsResult<FramePacketInfoDTO>();
		try {			
			FramePacketInfoResult framePacketInfoResult = framePacketInfoService.findByTimeStamp(meetingId, micId,speakerId,startTime,endTime,senderId,receiverId);
			List<FramePacketInfoDTO> list = framePacketInfoResult.getListFramePacketInfoDtos();
		
			if (list == null || list.size() == 0){
				result.setResult(2);
				return result;
			}
			result.setItems(list);
			result.setCount(framePacketInfoResult.getCount());
		} catch (Exception e) {
			// TODO: handle exception
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}
		return result;
	}
	
	@Path("/aboutResendReqConmandTimeStamp")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemsResult<ResendReqConmandDTO> aboutResendReqConmandTimeStamp(@FormParam("meetingId") int meetingId, 
			@FormParam("speakerId") String speakerId, 
			@FormParam("micId") int micId,
			@FormParam("startTime") long startTime,
			@FormParam("endTime") long endTime,
			@FormParam("senderId") String senderId,
			@FormParam("receiverId") String receiverId){
		ItemsResult<ResendReqConmandDTO> result = new ItemsResult<ResendReqConmandDTO>();
		try {			
			ResendReqConmandResult resendReqConmandResult = reqConmandService.findByTimeStamp(meetingId, micId,speakerId,startTime,endTime,senderId,receiverId);
			List<ResendReqConmandDTO> list = resendReqConmandResult.getListResendReqConmandDtos();
		
			if (list == null || list.size() == 0){
				result.setResult(2);
				return result;
			}
			result.setItems(list);
			result.setCount(resendReqConmandResult.getCount());
		} catch (Exception e) {
			// TODO: handle exception
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}
		return result;
	}
}
