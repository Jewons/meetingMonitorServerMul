package com.redcdn.commom.monitorcontrol.facade;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.redcdn.commom.GZipUtils;
import com.redcdn.commom.monitorcontrol.db.business.BandWithAdaptiveService;
import com.redcdn.commom.monitorcontrol.db.business.LossPackInfoService;
import com.redcdn.commom.monitorcontrol.db.business.MeetingInfoService;
import com.redcdn.commom.monitorcontrol.db.business.MeetingInfoSummaryService;
import com.redcdn.commom.monitorcontrol.db.business.impl.BandWithAdaptiveServiceImpl;
import com.redcdn.commom.monitorcontrol.db.business.impl.LossPackInfoServiceImpl;
import com.redcdn.commom.monitorcontrol.db.business.impl.MeetingInfoServiceImpl;
import com.redcdn.commom.monitorcontrol.db.business.impl.MeetingInfoSummaryServiceImpl;
import com.redcdn.commom.monitorcontrol.db.entity.BandWithAdaptiveEntity;
import com.redcdn.commom.monitorcontrol.db.entity.view.LossPackInfoResult;
import com.redcdn.commom.monitorcontrol.db.entity.view.MeetingInfoResult;
import com.redcdn.commom.monitorcontrol.db.entity.view.MeetingInfoSummaryResult;
import com.redcdn.commom.monitorcontrol.dto.LossPackInfoDTO;
import com.redcdn.commom.monitorcontrol.dto.MeetingInfoDTO;
import com.redcdn.commom.monitorcontrol.dto.MeetingInfoSummaryDTO;
import com.redcdn.commom.monitorcontrol.result.ItemsResult;


@Service
@Path("/MeetingInfoAnalysis")
@SuppressWarnings("unchecked")
public class MeetingInfoAnalyseServiceFacade {
	protected Logger logger = Logger.getLogger(this.getClass());
	//private MeetingInfoService meetingInfoService = new MeetingInfoServiceImpl();
	private LossPackInfoService lossPackInfoService = new LossPackInfoServiceImpl();
	private BandWithAdaptiveService bandWithAdaptiveService = new BandWithAdaptiveServiceImpl();
	
//	@Path("/aboutMeetingId")
//	@POST
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ItemsResult<MeetingInfoDTO> aboutMeetingId(@FormParam("meetingId") int meetingId, 
//			@FormParam("speakerId") String speakerId, 
//			@FormParam("micId") int micId,
//			@FormParam("pageSize") int pageSize,
//			@FormParam("currPage") int currPage){
//		logger.info("进入server的aboutMeetingId查询");
//		ItemsResult<MeetingInfoDTO> result = new ItemsResult<MeetingInfoDTO>();
//		try {
//			result.setCurrPage(currPage);
//			result.setPageSize(pageSize);
//			
//			MeetingInfoResult meetingInfoResult = meetingInfoService.findByMeetingId(meetingId,micId,speakerId,pageSize,currPage);
//			List<MeetingInfoDTO> list = meetingInfoResult.getListMeetingInfoDtos();
//		
//			if (list == null || list.size() == 0){
//				result.setResult(2);
//				return result;
//			}
//			for (MeetingInfoDTO meetingInfoDTO : list) {
//				System.out.println(meetingInfoDTO.toString());
//			}
//			result.setItems(list);
//			result.setCount(meetingInfoResult.getCount());
//		} catch (Exception e) {
//			String message = e.getMessage() == null ? "" : e.getMessage();
//			logger.error(message, e);
//			result.setResult(-1);
//		}
//		return result;
//	}
	
	
	@Path("/aboutLossPacketInfo")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	//public ItemsResult<LossPackInfoDTO> aboutLossPacketInfo(@FormParam("meetingId") int meetingId,
	public byte[] aboutLossPacketInfo(@FormParam("meetingId") int meetingId, 
			@FormParam("userId") String userId, 
//			@FormParam("micId") int micId,
			@FormParam("speakerId") String speakerId,
			@FormParam("pageSize") int pageSize,
			@FormParam("currPage") int currPage){
		logger.info("进入server的aboutLossPacketInfo查询");
		ItemsResult<LossPackInfoDTO> result = new ItemsResult<LossPackInfoDTO>();
		try {
			result.setCurrPage(currPage);
			result.setPageSize(pageSize);
			
			LossPackInfoResult lossPackInfoResult = lossPackInfoService.findByUserId(meetingId,speakerId,userId,pageSize,currPage);
			List<LossPackInfoDTO> list = lossPackInfoResult.getListLossPackInfoDtos();
		
			if (list == null || list.size() == 0){
				result.setResult(2);
				//return result;
			}
			result.setItems(list);
			result.setCount(lossPackInfoResult.getCount());
			List<Integer> mediaTypes = new ArrayList<Integer>();
			for (Integer mediaType : lossPackInfoResult.getMediaTypeList()) {
				mediaTypes.add(mediaType);
			}
			result.setMediaType(mediaTypes);
		} catch (Exception e) {
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}

		byte[] data = null;
		
		try {
			//byte[] input = net.sf.json.JSONObject.fromObject(result).toString().getBytes("UTF-8");
			Gson gs = new Gson();
			byte[] input = gs.toJson(result).getBytes("UTF-8");
			//System.out.println("input.length:"+input.length);
			data = GZipUtils.compress(input);
			//System.out.println("data.length:"+data.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@Path("/aboutLossPacketInfo2")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	//public ItemsResult<LossPackInfoDTO> aboutLossPacketInfo(@FormParam("meetingId") int meetingId,
	//zhanghy 12-27添加参数 QosTableNames
	public byte[] aboutLossPacketInfo2(@FormParam("meetingId") int meetingId, 
			@FormParam("userId") String userId, 
//			@FormParam("micId") int micId,
			@FormParam("speakerId") String speakerId,
			@FormParam("pageSize") int pageSize,
			@FormParam("currPage") int currPage,
			@FormParam("meetingStartTime") String meetingStartTime,
			@FormParam("meetingEndTime") String meetingEndTime,
			@FormParam("QosTableNames") String QosTableNames){
		logger.info("进入server的aboutLossPacketInfo查询");
		ItemsResult<LossPackInfoDTO> result = new ItemsResult<LossPackInfoDTO>();
		try {
			result.setCurrPage(currPage);
			result.setPageSize(pageSize);
			lossPackInfoService.setMeetingStartTime(meetingStartTime);
			lossPackInfoService.setMeetingEndTime(meetingEndTime);
			LossPackInfoResult lossPackInfoResult = lossPackInfoService.findByUserId(meetingId,speakerId,userId,pageSize,currPage,QosTableNames);
			List<LossPackInfoDTO> list = lossPackInfoResult.getListLossPackInfoDtos();
		
			if (list == null || list.size() == 0){
				result.setResult(2);
				//return result;
			}
			result.setItems(list);
			result.setCount(lossPackInfoResult.getCount());
			List<Integer> mediaTypes = new ArrayList<Integer>();
			for (Integer mediaType : lossPackInfoResult.getMediaTypeList()) {
				mediaTypes.add(mediaType);
			}
			result.setMediaType(mediaTypes);
		} catch (Exception e) {
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}

		byte[] data = null;
		
		try {
			//byte[] input = net.sf.json.JSONObject.fromObject(result).toString().getBytes("UTF-8");
			Gson gs = new Gson();
			byte[] input = gs.toJson(result).getBytes("UTF-8");
			//System.out.println("input.length:"+input.length);
			data = GZipUtils.compress(input);
			//System.out.println("data.length:"+data.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	/**
	 * 导出Qos报告
	 * @param meetingId
	 * @param directionType
	 * @return
	 */
	@Path("/aboutMakeQosReport")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public byte[] aboutMakeQosReport(@FormParam("meetingId") int meetingId,
			@FormParam("speakerId") String speakerId,
			@FormParam("directionType") int directionType){
		logger.info("进入server的aboutMakeQosReport查询");
		ItemsResult<LossPackInfoDTO> result = new ItemsResult<LossPackInfoDTO>();
		try {			
			LossPackInfoResult lossPackInfoResult = lossPackInfoService.findByMeetingId(meetingId,speakerId,directionType);
			List<LossPackInfoDTO> list = lossPackInfoResult.getListLossPackInfoDtos();
		
			if (list == null || list.size() == 0){
				result.setResult(2);
				//return result;
			}
			result.setItems(list);
			result.setCount(lossPackInfoResult.getCount());
		} catch (Exception e) {
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}
		
		System.out.println("count:"+result.getCount());
		byte[] data = null;
		
		try {
			Gson gs = new Gson();
			byte[] input = gs.toJson(result).getBytes("UTF-8");
			System.out.println("input.length:"+input.length);
			data = GZipUtils.compress(input);
			System.out.println("data.length:"+data.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	@Path("/aboutBandAdpInfo")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemsResult<BandWithAdaptiveEntity> aboutBandAdpInfo(@FormParam("meetingId") int meetingId, 
			@FormParam("userId") String userId, 
			@FormParam("speakerId") int micId,
			@FormParam("pageSize") int pageSize,
			@FormParam("currPage") int currPage){
		logger.info("进入server的aboutBandAdpInfo查询");
		ItemsResult<BandWithAdaptiveEntity> result = new ItemsResult<BandWithAdaptiveEntity>();
		try {
			result.setCurrPage(currPage);
			result.setPageSize(pageSize);
			
			List<BandWithAdaptiveEntity> list = bandWithAdaptiveService.findByUserId(meetingId,micId,userId,pageSize,currPage);

		
			if (list == null || list.size() == 0){
				result.setResult(2);
				return result;
			}
			result.setItems(list);
			result.setCount(list.size());
		} catch (Exception e) {
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}
		logger.info("退入server的aboutBandAdpInfo查询");
		return result;
	}
	
	@Path("/aboutBandAdpInfoForRelay")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemsResult<BandWithAdaptiveEntity> aboutBandAdpInfoForRelay(@FormParam("meetingId") int meetingId, 
			@FormParam("relayIp") String relayIp, 
			@FormParam("micId") int micId,
			@FormParam("pageSize") int pageSize,
			@FormParam("currPage") int currPage){
		logger.info("进入server的aboutBandAdpInfoForRelay查询");
		ItemsResult<BandWithAdaptiveEntity> result = new ItemsResult<BandWithAdaptiveEntity>();
		try {
			result.setCurrPage(currPage);
			result.setPageSize(pageSize);
			
			List<BandWithAdaptiveEntity> list = bandWithAdaptiveService.findByRelayIp(meetingId,micId,relayIp,pageSize,currPage);

		
			if (list == null || list.size() == 0){
				result.setResult(2);
				return result;
			}
			result.setItems(list);
			result.setCount(list.size());
		} catch (Exception e) {
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}
		logger.info("退入server的aboutBandAdpInfoForRelay查询");
		return result;
	}

	/**
	 * 关于标识
	 * 
	 * @param startTime
	 * @param endTime
	 * @param meetingId 会议号
	 * @param relayId   relayId
	 * @param userId    用户id
	 * @param directionType 0 上行 1 下行  2全部
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
//	@Path("/aboutID")
//	@POST
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ItemsResult<MeetingInfoDTO> aboutID(@FormParam("startTime") long startTime,
//			@FormParam("endTime") long endTime, 
//			@FormParam("meetingId") int meetingId, 
//			@FormParam("relayId") int relayId, 
//			@FormParam("userId") String userId, 
//			@FormParam("directionType") int directionType, 
//			@FormParam("pageSize") int pageSize, 
//			@FormParam("currPage") int currPage){
//		logger.info("进入server的aboutID查询");
//		ItemsResult<MeetingInfoDTO> result = new ItemsResult<MeetingInfoDTO>();
//		try {
//			result.setCurrPage(currPage);
//			result.setPageSize(pageSize);
//			
//			MeetingInfoResult meetingInfoResult = meetingInfoService.findByid(startTime, endTime, userId, meetingId, relayId, directionType, pageSize, currPage);
//			List<MeetingInfoDTO> list = meetingInfoResult.getListMeetingInfoDtos();
//		
//			if (list == null || list.size() == 0){
//				result.setResult(2);
//				return result;
//			}
//			result.setItems(list);
//			result.setCount(meetingInfoResult.getCount());
//		} catch (Exception e) {
//			String message = e.getMessage() == null ? "" : e.getMessage();
//			logger.error(message, e);
//			result.setResult(-1);
//		}
//		return result;
//	}
	
	
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param videoSignBefore
	 * @param videoLossBeforeMin
	 * @param videoLossBeforeMax
	 * @param videoSignAfter
	 * @param videoLossAfterMin
	 * @param videoLossAfterMax
	 * @param audioSignBefore
	 * @param audioLossBeforeMin
	 * @param audioLossBeforeMax
	 * @param audioSignAfter
	 * @param audioLossAfterMin
	 * @param audioLossAfterMax
	 * @param directionType
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
//	@Path("/aboutStream")
//	@POST
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ItemsResult<MeetingInfoDTO> aboutStream(@FormParam("startTime") long startTime,
//			@FormParam("endTime") long endTime, 
//			@FormParam("videoSignBefore") String videoSignBefore, 
//			@FormParam("videoLossBeforeMin") float videoLossBeforeMin, 
//			@FormParam("videoLossBeforeMax") float videoLossBeforeMax,
//			@FormParam("videoSignAfter") String videoSignAfter, 
//			@FormParam("videoLossAfterMin") float videoLossAfterMin, 
//			@FormParam("videoLossAfterMax") float videoLossAfterMax, 
//			@FormParam("audioSignBefore") String audioSignBefore, 
//			@FormParam("audioLossBeforeMin") float audioLossBeforeMin,
//			@FormParam("audioLossBeforeMax") float audioLossBeforeMax, 
//			@FormParam("audioSignAfter") String audioSignAfter,
//			@FormParam("audioLossAfterMin") float audioLossAfterMin,
//			@FormParam("audioLossAfterMax") float audioLossAfterMax,
//			@FormParam("directionType") Integer directionType, 
//			@FormParam("pageSize") int pageSize, 
//			@FormParam("currPage") int currPage){
//		
//		ItemsResult<MeetingInfoDTO> result = new ItemsResult<MeetingInfoDTO>();
//		try {
//			result.setCurrPage(currPage);
//			result.setPageSize(pageSize);
//			MeetingInfoResult meetingInfoResult = meetingInfoService.findByStream(startTime, endTime, videoSignBefore, videoLossBeforeMin, videoLossBeforeMax, videoSignAfter, videoLossAfterMin, videoLossAfterMax, audioSignBefore, audioLossBeforeMin, audioLossBeforeMax, audioSignAfter, audioLossAfterMin, audioLossAfterMax, directionType, pageSize, currPage);
//			List<MeetingInfoDTO> list = meetingInfoResult.getListMeetingInfoDtos();
//			if (list == null || list.size() == 0){
//				result.setResult(2);
//				return result;
//			}
//			result.setItems(list);
//			result.setCount(meetingInfoResult.getCount());
//		} catch (Exception e) {
//			String message = e.getMessage() == null ? "" : e.getMessage();
//			logger.error(message, e);
//			result.setResult(-1);
//		}
//		return result;
//	}
	
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param cpuRateSign
	 * @param cpuRateMin
	 * @param cpuRateMax
	 * @param frameRateSign
	 * @param frameRateMin
	 * @param frameRaterMax
	 * @param delaySign
	 * @param delayMin
	 * @param delayMax
	 * @param directionType
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
//	@Path("/aboutQuality")
//	@POST
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ItemsResult<MeetingInfoDTO> aboutQuality(@FormParam("startTime") long startTime,
//			@FormParam("endTime") long endTime, 
//			@FormParam("cpuRateSign") String cpuRateSign, 
//			@FormParam("cpuRateMin") int cpuRateMin, 
//			@FormParam("cpuRateMax") int cpuRateMax,
//			@FormParam("frameRateSign") String frameRateSign, 
//			@FormParam("frameRateMin") int frameRateMin, 
//			@FormParam("frameRateMax") int frameRaterMax, 
//			@FormParam("delaySign") String delaySign, 
//			@FormParam("delayMin") int delayMin,
//			@FormParam("delayMax") int delayMax, 
//			@FormParam("directionType") int directionType, 
//			@FormParam("pageSize") int pageSize, 
//			@FormParam("currPage") int currPage) {
//		
//		ItemsResult<MeetingInfoDTO> result = new ItemsResult<MeetingInfoDTO>();
//		try {
//			result.setCurrPage(currPage);
//			result.setPageSize(pageSize);
//			MeetingInfoResult meetingInfoResult = meetingInfoService.findByQuality(startTime, endTime, cpuRateSign, cpuRateMin, cpuRateMax, frameRateSign, frameRateMin, frameRaterMax, delaySign, delayMin, delayMax, directionType, pageSize, currPage);
//			List<MeetingInfoDTO> list = meetingInfoResult.getListMeetingInfoDtos();
//			if (list == null || list.size() == 0){
//				result.setResult(2);
//				return result;
//			}
//			result.setItems(list);
//			result.setCount(meetingInfoResult.getCount());
//		} catch (Exception e) {
//			String message = e.getMessage() == null ? "" : e.getMessage();
//			logger.error(message, e);
//			result.setResult(-1);
//		}
//		return result;
//	}
	
	/**
	 * 
//	 * @param startTime
//	 * @param endTime
//	 * @param oneSign
//	 * @param oneMin
//	 * @param oneMax
//	 * @param twoSign
//	 * @param twoMin
//	 * @param twoMax
//	 * @param thrSign
//	 * @param thrMin
//	 * @param thrMax
//	 * @param fouSign
//	 * @param fouMin
//	 * @param fouMax
//	 * @param tenSign
//	 * @param tenMin
//	 * @param tenMax
//	 * @param directionType
//	 * @param pageSize
//	 * @param currPage
//	 * @return
//	 */
//	@Path("/aboutEmptyPackage")
//	@POST
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ItemsResult<MeetingInfoDTO> aboutEmptyPackage(@FormParam("startTime") long startTime,
//			@FormParam("endTime") long endTime, 
//			@FormParam("oneSign") String oneSign, 
//			@FormParam("oneMin") int oneMin, 
//			@FormParam("oneMax") int oneMax,
//			@FormParam("twoSign") String twoSign, 
//			@FormParam("twoMin") int twoMin, 
//			@FormParam("twoMax") int twoMax, 
//			@FormParam("thrSign") String thrSign, 
//			@FormParam("thrMin") int thrMin,
//			@FormParam("thrMax") int thrMax,
//			@FormParam("fouSign") String fouSign, 
//			@FormParam("fouMin") int fouMin,
//			@FormParam("fouMax") int fouMax,
//			@FormParam("tenSign") String tenSign, 
//			@FormParam("tenMin") int tenMin,
//			@FormParam("tenMax") int tenMax,
//			@FormParam("directionType") int directionType, 
//			@FormParam("pageSize") int pageSize, 
//			@FormParam("currPage") int currPage) {
//		
//		ItemsResult<MeetingInfoDTO> result = new ItemsResult<MeetingInfoDTO>();
//		try {
//			result.setCurrPage(currPage);
//			result.setPageSize(pageSize);
//			MeetingInfoResult meetingInfoResult = meetingInfoService.findByEmptyPackage(startTime, endTime, oneSign, oneMin, 
//					oneMax, twoSign, twoMin, twoMax, thrSign, thrMin, thrMax, fouSign, fouMin, fouMax, tenSign, tenMin, 
//					tenMax, directionType, pageSize, currPage);
//			List<MeetingInfoDTO> list = meetingInfoResult.getListMeetingInfoDtos();
//			if (list == null || list.size() == 0){
//				result.setResult(2);
//				return result;
//			}
//			result.setItems(list);
//			result.setCount(meetingInfoResult.getCount());
//		} catch (Exception e) {
//			String message = e.getMessage() == null ? "" : e.getMessage();
//			logger.error(message, e);
//			result.setResult(-1);
//		}
//		return result;
//	}
	
//	@Path("/aboutAll")
//	@POST
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ItemsResult<MeetingInfoDTO> aboutAll(@FormParam("startTime") long startTime,
//			@FormParam("endTime") long endTime, 
//			@FormParam("meetingId") int meetingId, 
//			@FormParam("relayId") int relayId, 
//			@FormParam("userId") String userId,
//			@FormParam("videoSignBefore") String videoSignBefore, 
//			@FormParam("videoLossBeforeMin") float videoLossBeforeMin, 
//			@FormParam("videoLossBeforeMax") float videoLossBeforeMax,
//			@FormParam("videoSignAfter") String videoSignAfter, 
//			@FormParam("videoLossAfterMin") float videoLossAfterMin, 
//			@FormParam("videoLossAfterMax") float videoLossAfterMax, 
//			@FormParam("audioSignBefore") String audioSignBefore, 
//			@FormParam("audioLossBeforeMin") float audioLossBeforeMin,
//			@FormParam("audioLossBeforeMax") float audioLossBeforeMax, 
//			@FormParam("audioSignAfter") String audioSignAfter,
//			@FormParam("audioLossAfterMin") float audioLossAfterMin,
//			@FormParam("audioLossAfterMax") float audioLossAfterMax,
//			@FormParam("cpuRateSign") String cpuRateSign, 
//			@FormParam("cpuRateMin") int cpuRateMin, 
//			@FormParam("cpuRateMax") int cpuRateMax,
//			@FormParam("frameRateSign") String frameRateSign, 
//			@FormParam("frameRateMin") int frameRateMin, 
//			@FormParam("frameRateMax") int frameRaterMax, 
//			@FormParam("delaySign") String delaySign, 
//			@FormParam("delayMin") int delayMin,
//			@FormParam("delayMax") int delayMax,
//			@FormParam("oneSign") String oneSign, 
//			@FormParam("oneMin") int oneMin, 
//			@FormParam("oneMax") int oneMax,
//			@FormParam("twoSign") String twoSign, 
//			@FormParam("twoMin") int twoMin, 
//			@FormParam("twoMax") int twoMax, 
//			@FormParam("thrSign") String thrSign, 
//			@FormParam("thrMin") int thrMin,
//			@FormParam("thrMax") int thrMax,
//			@FormParam("fouSign") String fouSign, 
//			@FormParam("fouMin") int fouMin,
//			@FormParam("fouMax") int fouMax,
//			@FormParam("tenSign") String tenSign, 
//			@FormParam("tenMin") int tenMin,
//			@FormParam("tenMax") int tenMax,
//			@FormParam("directionType") int directionType, 
//			@FormParam("pageSize") int pageSize, 
//			@FormParam("currPage") int currPage){
//		
//		ItemsResult<MeetingInfoDTO> result = new ItemsResult<MeetingInfoDTO>();
//		try {
//			result.setCurrPage(currPage);
//			result.setPageSize(pageSize);
//			MeetingInfoResult meetingInfoResult = meetingInfoService.findByAll(startTime, endTime, 
//					userId, meetingId, relayId, 
//					videoSignBefore, videoLossBeforeMin, videoLossBeforeMax, videoSignAfter, videoLossAfterMin, videoLossAfterMax, audioSignBefore, audioLossBeforeMin, audioLossBeforeMax, audioSignAfter, audioLossAfterMin, audioLossAfterMax, 
//					cpuRateSign, cpuRateMin, cpuRateMax, frameRateSign, frameRateMin, frameRaterMax, delaySign, delayMin, delayMax,
//					oneSign, oneMin, oneMax, twoSign, twoMin, twoMax, thrSign, thrMin, thrMax, fouSign, fouMin, fouMax, tenSign, tenMin, tenMax,
//					directionType, pageSize, currPage);
//			List<MeetingInfoDTO> list = meetingInfoResult.getListMeetingInfoDtos();
//			if (list == null || list.size() == 0){
//				result.setResult(2);
//				return result;
//			}
//			result.setItems(list);
//			result.setCount(meetingInfoResult.getCount());
//		} catch (Exception e) {
//			String message = e.getMessage() == null ? "" : e.getMessage();
//			logger.error(message, e);
//			result.setResult(-1);
//		}
//		return result;
//
//	}
	
	/**
	 * 导出报告
	 * @param startTime
	 * @param endTime
	 * @param attendCountSign
	 * @param attendCountMin
	 * @param attendCountMax
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	@Path("/aboutMakeReport")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	 public byte[] aboutMakeReport(@FormParam("startTime") long startTime,
				@FormParam("endTime") long endTime, 
				@FormParam("attendCountSign") String attendCountSign, 
				@FormParam("attendCountMin") int attendCountMin,
				@FormParam("attendCountMax") int attendCountMax,
				@FormParam("directionType") int directionType){
			int pageSize = 0;
			int currPage = 0;
			ItemsResult<MeetingInfoSummaryDTO> result1 = new ItemsResult<MeetingInfoSummaryDTO>();
			ItemsResult<LossPackInfoDTO> result = new ItemsResult<LossPackInfoDTO>(); 
			try {
				result1.setCurrPage(currPage);
				result1.setPageSize(pageSize);
				MeetingInfoSummaryService meetingInfoSummaryService = new MeetingInfoSummaryServiceImpl();
				MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByAttendCount(startTime, endTime, attendCountSign, attendCountMin, attendCountMax, pageSize, currPage);
				List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
				//如果查询概要表，没有查询到，即查询结果为空，那么将
				if (list == null || list.size() == 0){
					result.setResult(2);
					result.setItems(null);
					result.setCount(0);
				}else {
					//如果有查询结果，放入会议号列表中，并以此为条件查询详细表
					List<Integer> meetingList = new ArrayList<Integer>();
					for (MeetingInfoSummaryDTO dto : list) {
						meetingList.add(dto.getMeetingId());
					}
					list.clear();
					try{
						//LossPackInfoService lossPackInfoService = new LossPackInfoServiceImpl();
						String speakerId = "";
						LossPackInfoResult lossPackInfoResult = lossPackInfoService.findByMeetingIds(meetingList,speakerId,directionType,endTime,null);
						List<LossPackInfoDTO> list2 = lossPackInfoResult.getListLossPackInfoDtos();
						if (list2 == null || list2.size() == 0){
							result.setResult(2);
							//return result;
						}
						result.setItems(list2);
						list2.clear();
						result.setCount(lossPackInfoResult.getCount());
					}
					catch (Exception e) {
						String message = e.getMessage() == null ? "" : e.getMessage();
						logger.error(message, e);
						result.setResult(-1);
					}
				}
//				result.setItems(list);
//				result.setCount(meetingInfoSummaryResult.getCount());
			} catch (Exception e) {
				String message = e.getMessage() == null ? "" : e.getMessage();
				logger.error(message, e);
				result.setResult(-1);
			}
			byte[] data = null;
			
			try {
				Gson gs = new Gson();
				byte[] input = gs.toJson(result).getBytes("UTF-8");
			    //byte[] input = net.sf.json.JSONObject.fromObject(result).toString().getBytes("UTF-8");
				//System.out.println("input.length:"+input.length);
				data = GZipUtils.compress(input);
				//System.out.println("data.length:"+data.length);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		}
}
