package com.redcdn.commom.monitorcontrol.facade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mongodb.util.JSON;
import com.redcdn.commom.CommStats;
import com.redcdn.commom.ExportFile;
import com.redcdn.commom.GZipUtils;
import com.redcdn.commom.monitorcontrol.db.business.LossPackInfoService;
import com.redcdn.commom.monitorcontrol.db.business.MeetingInfoSummaryService;
import com.redcdn.commom.monitorcontrol.db.business.impl.LossPackInfoServiceImpl;
import com.redcdn.commom.monitorcontrol.db.business.impl.MeetingInfoSummaryServiceImpl;
import com.redcdn.commom.monitorcontrol.db.entity.MeetingInfoSummaryEntity;
import com.redcdn.commom.monitorcontrol.db.entity.view.LossPackInfoResult;
import com.redcdn.commom.monitorcontrol.db.entity.view.MeetingInfoSummaryResult;
import com.redcdn.commom.monitorcontrol.dto.DTO;
import com.redcdn.commom.monitorcontrol.dto.FormDataExcelDTO;
import com.redcdn.commom.monitorcontrol.dto.LossPackInfoDTO;
import com.redcdn.commom.monitorcontrol.dto.MeetingInfoSummaryDTO;
import com.redcdn.commom.monitorcontrol.result.ItemsResult;
import com.sun.org.apache.xml.internal.security.keys.content.KeyValue;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

@Service
@Path("/MeetingInfoSummaryAnalysis")
@SuppressWarnings("unchecked")
public class MeetingInfoSummaryAnalyseServiceFacade {

	@Context
	HttpServletResponse response;
	
	@Context
	HttpServletRequest request;
	
	protected Logger logger = Logger.getLogger(this.getClass());
	private MeetingInfoSummaryService meetingInfoSummaryService = new MeetingInfoSummaryServiceImpl();
	//private LossPackInfoService lossPackInfoService = new LossPackInfoServiceImpl();
	/**
	 * 关于标识
	 * 
	 * @param startTime
	 * @param endTime
	 * @param meetingId 会议号
	 * @param relayId   relayId
	 * @param userId    用户id
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	@Path("/aboutID")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	 public ItemsResult<MeetingInfoSummaryDTO> aboutID(@FormParam("startTime") long startTime,
				@FormParam("endTime") long endTime, 
				@FormParam("meetingId") int meetingId, 
				@FormParam("relayId") String relayId, 
				@FormParam("userId") String userId, 
				@FormParam("pageSize") int pageSize, 
				@FormParam("currPage") int currPage,
				@FormParam("UserId_List_for_Company") String userList){
		//System.out.println(System.getProperty("user.dir"));
		//System.out.println(this.getClass().getClassLoader().getResource("").getPath());
		ItemsResult<MeetingInfoSummaryDTO> result = new ItemsResult<MeetingInfoSummaryDTO>();
		try {
			result.setCurrPage(currPage);
			result.setPageSize(pageSize);
			MeetingInfoSummaryResult meetingInfoSummaryResult = new MeetingInfoSummaryResult();
			if(userList==null){
				//如果公司名称上没有输入信息，那么走原来的逻辑，不加入公司的视讯号列表过滤
				meetingInfoSummaryResult = meetingInfoSummaryService.findByid(startTime, endTime, userId, meetingId, relayId, pageSize, currPage);
			}else{
				JSONObject usersJson = JSONObject.fromObject(userList);
				JSONArray userJsonArray = (JSONArray) usersJson.get("userIdList");
				List<String> userListStrings = new ArrayList<String>();
				for(int i = 0;i < userJsonArray.size();i++){
					userListStrings.add(userJsonArray.getString(i));
				}
				
				System.out.println("userlist:"+userListStrings);
				//如果公司名称输入信息，那么通过该公司的视讯号列表进行对查询结果的过滤，最后显示出有该公司的视讯号概要表
				meetingInfoSummaryResult = meetingInfoSummaryService.findByid(startTime, endTime, userId, meetingId, relayId, pageSize, currPage,userListStrings);
			}
			List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
		
			if (list == null || list.size() == 0){
				result.setResult(2);
				return result;
			}
			result.setItems(list);
			result.setCount(meetingInfoSummaryResult.getCount());
			meetingInfoSummaryResult = null;
		} catch (Exception e) {
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}
		return result;
	}
	/*********************************************************************************/
	@Path("/aboutIDtest")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	 public ItemsResult<MeetingInfoSummaryDTO> aboutIDtest(@FormParam("param") String jsonParam){
		JSONObject param = JSONObject.fromObject(jsonParam);
			 return null;
	}
	/*******************************************************************************/
	/**
	 * 关于会议人数
	 * @param startTime
	 * @param endTime
	 * @param attendCountSign
	 * @param attendCountMin
	 * @param attendCountMax
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	@Path("/aboutAttendCount")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	 public ItemsResult<MeetingInfoSummaryDTO> aboutAttendCount(@FormParam("startTime") long startTime,
				@FormParam("endTime") long endTime, 
				@FormParam("attendCountSign") String attendCountSign, 
				@FormParam("attendCountMin") int attendCountMin,
				@FormParam("attendCountMax") int attendCountMax,
				@FormParam("pageSize") int pageSize, 
				@FormParam("currPage") int currPage){
			ItemsResult<MeetingInfoSummaryDTO> result = new ItemsResult<MeetingInfoSummaryDTO>();
			try {
				result.setCurrPage(currPage);
				result.setPageSize(pageSize);
				
				MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByAttendCount(startTime, endTime, attendCountSign, attendCountMin, attendCountMax, pageSize, currPage);
				List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
			
				if (list == null || list.size() == 0){
					result.setResult(2);
					return result;
				}
				result.setItems(list);
				result.setCount(meetingInfoSummaryResult.getCount());
			} catch (Exception e) {
				String message = e.getMessage() == null ? "" : e.getMessage();
				logger.error(message, e);
				result.setResult(-1);
			}
			return result;
		}
	

	/**
	 * 按流质量查询 已废弃 音视频分开查询
	 * @param startTime
	 * @param endTime
	 * @param upVideoSignBefore
	 * @param upVideoLossBeforeMin
	 * @param upVideoLossBeforeMax
	 * @param upVideoSignAfter
	 * @param upVideoLossAfterMin
	 * @param upVideoLossAfterMax
	 * @param downVideoSignBefore
	 * @param downVideoLossBeforeMin
	 * @param downVideoLossBeforeMax
	 * @param downVideoSignAfter
	 * @param downVideoLossAfterMin
	 * @param downVideoLossAfterMax
	 * @param upAudioSignBefore
	 * @param upAudioLossBeforeMin
	 * @param upAudioLossBeforeMax
	 * @param upAudioSignAfter
	 * @param upAudioLossAfterMin
	 * @param upAudioLossAfterMax
	 * @param downAudioSignBefore
	 * @param downAudioLossBeforeMin
	 * @param downAudioLossBeforeMax
	 * @param downAudioSignAfter
	 * @param downAudioLossAfterMin
	 * @param downAudioLossAfterMax
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	@Path("/aboutStream")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	 public ItemsResult<MeetingInfoSummaryDTO> aboutStream(@FormParam("startTime") long startTime,
				@FormParam("endTime") long endTime, 
				@FormParam("upVideoSignBefore") String upVideoSignBefore, 
				@FormParam("upVideoLossBeforeMin") float upVideoLossBeforeMin,
				@FormParam("upVideoLossBeforeMax") float upVideoLossBeforeMax,
				@FormParam("upVideoSignAfter") String upVideoSignAfter, 
				@FormParam("upVideoLossAfterMin") float upVideoLossAfterMin, 
				@FormParam("upVideoLossAfterMax") float upVideoLossAfterMax,
				@FormParam("downVideoSignBefore") String downVideoSignBefore, 
				@FormParam("downVideoLossBeforeMin") float downVideoLossBeforeMin, 
				@FormParam("downVideoLossBeforeMax") float downVideoLossBeforeMax, 
				@FormParam("downVideoSignAfter") String downVideoSignAfter, 
				@FormParam("downVideoLossAfterMin") float downVideoLossAfterMin,
				@FormParam("downVideoLossAfterMax") float downVideoLossAfterMax,
				@FormParam("upAudioSignBefore") String upAudioSignBefore, 
				@FormParam("upAudioLossBeforeMin") int upAudioLossBeforeMin,
				@FormParam("upAudioLossBeforeMax") int upAudioLossBeforeMax,
				@FormParam("upAudioSignAfter") String upAudioSignAfter, 
				@FormParam("upAudioLossAfterMin") float upAudioLossAfterMin, 
				@FormParam("upAudioLossAfterMax") float upAudioLossAfterMax,
				@FormParam("downAudioSignBefore") String downAudioSignBefore, 
				@FormParam("downAudioLossBeforeMin") float downAudioLossBeforeMin, 
				@FormParam("downAudioLossBeforeMax") float downAudioLossBeforeMax, 
				@FormParam("downAudioSignAfter") String downAudioSignAfter, 
				@FormParam("downAudioLossAfterMin") float downAudioLossAfterMin,
				@FormParam("downAudioLossAfterMax") float downAudioLossAfterMax,
				@FormParam("pageSize") int pageSize, 
				@FormParam("currPage") int currPage){
			ItemsResult<MeetingInfoSummaryDTO> result = new ItemsResult<MeetingInfoSummaryDTO>();
			try {
				result.setCurrPage(currPage);
				result.setPageSize(pageSize);
				
				MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByStream(startTime, endTime, upVideoSignBefore, upVideoLossBeforeMin, upVideoLossBeforeMax, upVideoSignAfter, upVideoLossAfterMin, upVideoLossAfterMax
						,downVideoSignBefore, downVideoLossBeforeMin, downVideoLossBeforeMax, downVideoSignAfter, downVideoLossAfterMin, downVideoLossAfterMax, upAudioSignBefore, upAudioLossBeforeMin, upAudioLossBeforeMax, upAudioSignAfter, upAudioLossAfterMin, upAudioLossAfterMax
						,downAudioSignBefore, downAudioLossBeforeMin, downAudioLossBeforeMax, downAudioSignAfter, downAudioLossAfterMin, downAudioLossAfterMax, pageSize, currPage);
				List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
			
				if (list == null || list.size() == 0){
					result.setResult(2);
					return result;
				}
				result.setItems(list);
				result.setCount(meetingInfoSummaryResult.getCount());
			} catch (Exception e) {
				String message = e.getMessage() == null ? "" : e.getMessage();
				logger.error(message, e);
				result.setResult(-1);
			}
			return result;
		}
	
	
	/**
	 * 按时间查询
	 * @param startTime
	 * @param endTime
	 * @param ducationsign
	 * @param ducationMin
	 * @param ducationMax
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	@Path("/aboutTime")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	 public ItemsResult<MeetingInfoSummaryDTO> aboutTime(@FormParam("startTime") long startTime,
				@FormParam("endTime") long endTime,
				@FormParam("ducationsign") String ducationsign,
				@FormParam("ducationMin") long ducationMin,
				@FormParam("ducationMax") long ducationMax,
				@FormParam("pageSize") int pageSize, 
				@FormParam("currPage") int currPage){
		ItemsResult<MeetingInfoSummaryDTO> result = new ItemsResult<MeetingInfoSummaryDTO>();
		try {
			result.setCurrPage(currPage);
			result.setPageSize(pageSize);
			
			MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByTime(startTime, endTime, ducationsign ,ducationMin,ducationMax,pageSize, currPage);
			List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
		
			if (list == null || list.size() == 0){
				result.setResult(2);
				return result;
			}
			result.setItems(list);
			result.setCount(meetingInfoSummaryResult.getCount());
		} catch (Exception e) {
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}
		return result;
	}
	
	
	

	
	/**
	 * 按视频流质量查询
	 * @param startTime
	 * @param endTime
	 * @param upVideoSignBefore
	 * @param upVideoLossBeforeMin
	 * @param upVideoLossBeforeMax
	 * @param upVideoSignAfter
	 * @param upVideoLossAfterMin
	 * @param upVideoLossAfterMax
	 * @param downVideoSignBefore
	 * @param downVideoLossBeforeMin
	 * @param downVideoLossBeforeMax
	 * @param downVideoSignAfter
	 * @param downVideoLossAfterMin
	 * @param downVideoLossAfterMax
	 * @param upAudioSignBefore
	 * @param upAudioLossBeforeMin
	 * @param upAudioLossBeforeMax
	 * @param upAudioSignAfter
	 * @param upAudioLossAfterMin
	 * @param upAudioLossAfterMax
	 * @param downAudioSignBefore
	 * @param downAudioLossBeforeMin
	 * @param downAudioLossBeforeMax
	 * @param downAudioSignAfter
	 * @param downAudioLossAfterMin
	 * @param downAudioLossAfterMax
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	@Path("/aboutVideoStream")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemsResult<MeetingInfoSummaryDTO> aboutVideoStream(@FormParam("startTime") long startTime,
			@FormParam("endTime") long endTime, 
			@FormParam("upVideoSignBefore") String upVideoSignBefore, 
			@FormParam("upVideoLossBeforeMin") float upVideoLossBeforeMin,
			@FormParam("upVideoLossBeforeMax") float upVideoLossBeforeMax,
			@FormParam("upVideoSignAfter") String upVideoSignAfter, 
			@FormParam("upVideoLossAfterMin") float upVideoLossAfterMin, 
			@FormParam("upVideoLossAfterMax") float upVideoLossAfterMax,
			@FormParam("upVideoSignFinal") String upVideoSignFinal,
	        @FormParam("upVideoLossFinalMin") float upVideoLossFinalMin,
	        @FormParam("upVideoLossFinalMax") float upVideoLossFinalMax,
			@FormParam("downVideoSignBefore") String downVideoSignBefore, 
			@FormParam("downVideoLossBeforeMin") float downVideoLossBeforeMin, 
			@FormParam("downVideoLossBeforeMax") float downVideoLossBeforeMax, 
			@FormParam("downVideoSignAfter") String downVideoSignAfter, 
			@FormParam("downVideoLossAfterMin") float downVideoLossAfterMin,
			@FormParam("downVideoLossAfterMax") float downVideoLossAfterMax,
			@FormParam("downVideoSignFinal") String downVideoSignFinal,
	        @FormParam("downVideoLossFinalMin") float downVideoLossFinalMin,
	        @FormParam("downVideoLossFinalMax") float downVideoLossFinalMax,
	        @FormParam("videoSignBefore") String videoSignBefore, 
			@FormParam("videoLossBeforeMin") float videoLossBeforeMin, 
			@FormParam("videoLossBeforeMax") float videoLossBeforeMax, 
			@FormParam("videoSignAfter") String videoSignAfter, 
			@FormParam("videoLossAfterMin") float videoLossAfterMin,
			@FormParam("videoLossAfterMax") float videoLossAfterMax,
			@FormParam("videoSignFinal") String videoSignFinal,
	        @FormParam("videoLossFinalMin") float videoLossFinalMin,
	        @FormParam("videoLossFinalMax") float videoLossFinalMax,
			@FormParam("pageSize") int pageSize, 
			@FormParam("currPage") int currPage){
		logger.info("进入server.aboutVideoStream查询");
		ItemsResult<MeetingInfoSummaryDTO> result = new ItemsResult<MeetingInfoSummaryDTO>();
		try {
			result.setCurrPage(currPage);
			result.setPageSize(pageSize);
			MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByVideoStream(startTime, endTime, 
					upVideoSignBefore, upVideoLossBeforeMin, upVideoLossBeforeMax, upVideoSignAfter, upVideoLossAfterMin, upVideoLossAfterMax ,upVideoSignFinal, upVideoLossFinalMin, upVideoLossFinalMax
					,downVideoSignBefore, downVideoLossBeforeMin, downVideoLossBeforeMax, downVideoSignAfter, downVideoLossAfterMin, downVideoLossAfterMax,downVideoSignFinal, downVideoLossFinalMin, downVideoLossFinalMax
					,videoSignBefore, videoLossBeforeMin, videoLossBeforeMax, videoSignAfter, videoLossAfterMin, videoLossAfterMax,videoSignFinal, videoLossFinalMin, videoLossFinalMax
					, pageSize, currPage);
			List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
		
			if (list == null || list.size() == 0){
				result.setResult(2);
				return result;
			}
			result.setItems(list);
			result.setCount(meetingInfoSummaryResult.getCount());
		} catch (Exception e) {
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}
		return result;
	}
	
	/**
	 * 按音频流质量查询
	 * @param startTime
	 * @param endTime
	 * @param upVideoSignBefore
	 * @param upVideoLossBeforeMin
	 * @param upVideoLossBeforeMax
	 * @param upVideoSignAfter
	 * @param upVideoLossAfterMin
	 * @param upVideoLossAfterMax
	 * @param downVideoSignBefore
	 * @param downVideoLossBeforeMin
	 * @param downVideoLossBeforeMax
	 * @param downVideoSignAfter
	 * @param downVideoLossAfterMin
	 * @param downVideoLossAfterMax
	 * @param upAudioSignBefore
	 * @param upAudioLossBeforeMin
	 * @param upAudioLossBeforeMax
	 * @param upAudioSignAfter
	 * @param upAudioLossAfterMin
	 * @param upAudioLossAfterMax
	 * @param downAudioSignBefore
	 * @param downAudioLossBeforeMin
	 * @param downAudioLossBeforeMax
	 * @param downAudioSignAfter
	 * @param downAudioLossAfterMin
	 * @param downAudioLossAfterMax
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	@Path("/aboutAudioStream")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemsResult<MeetingInfoSummaryDTO> aboutAudioStream(@FormParam("startTime") long startTime,
			@FormParam("endTime") long endTime, 
			@FormParam("upAudioSignBefore") String upAudioSignBefore, 
			@FormParam("upAudioLossBeforeMin") float upAudioLossBeforeMin,
			@FormParam("upAudioLossBeforeMax") float upAudioLossBeforeMax,
			@FormParam("upAudioSignAfter") String upAudioSignAfter, 
			@FormParam("upAudioLossAfterMin") float upAudioLossAfterMin, 
			@FormParam("upAudioLossAfterMax") float upAudioLossAfterMax,
			@FormParam("upAudioSignFinal") String upAudioSignFinal,
	        @FormParam("upAudioLossFinalMin") float upAudioLossFinalMin,
	        @FormParam("upAudioLossFinalMax") float upAudioLossFinalMax,
			@FormParam("downAudioSignBefore") String downAudioSignBefore, 
			@FormParam("downAudioLossBeforeMin") float downAudioLossBeforeMin, 
			@FormParam("downAudioLossBeforeMax") float downAudioLossBeforeMax, 
			@FormParam("downAudioSignAfter") String downAudioSignAfter, 
			@FormParam("downAudioLossAfterMin") float downAudioLossAfterMin,
			@FormParam("downAudioLossAfterMax") float downAudioLossAfterMax,
			@FormParam("downAudioSignFinal") String downAudioSignFinal,
	        @FormParam("downAudioLossFinalMin") float downAudioLossFinalMin,
	        @FormParam("downAudioLossFinalMax") float downAudioLossFinalMax,
	        @FormParam("AudioSignBefore") String audioSignBefore, 
			@FormParam("audioLossBeforeMin") float audioLossBeforeMin, 
			@FormParam("audioLossBeforeMax") float audioLossBeforeMax, 
			@FormParam("audioSignAfter") String audioSignAfter, 
			@FormParam("audioLossAfterMin") float audioLossAfterMin,
			@FormParam("audioLossAfterMax") float audioLossAfterMax,
			@FormParam("audioSignFinal") String audioSignFinal,
	        @FormParam("audioLossFinalMin") float audioLossFinalMin,
	        @FormParam("audioLossFinalMax") float audioLossFinalMax,
			@FormParam("pageSize") int pageSize, 
			@FormParam("currPage") int currPage){
		logger.info("进入server.aboutAudioStream查询");
		ItemsResult<MeetingInfoSummaryDTO> result = new ItemsResult<MeetingInfoSummaryDTO>();
		try {
			result.setCurrPage(currPage);
			result.setPageSize(pageSize);
			MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByAudioStream(startTime, endTime
					,upAudioSignBefore, upAudioLossBeforeMin, upAudioLossBeforeMax, upAudioSignAfter, upAudioLossAfterMin, upAudioLossAfterMax ,upAudioSignFinal, upAudioLossFinalMin, upAudioLossFinalMax
					,downAudioSignBefore, downAudioLossBeforeMin, downAudioLossBeforeMax, downAudioSignAfter, downAudioLossAfterMin, downAudioLossAfterMax,downAudioSignFinal, downAudioLossFinalMin, downAudioLossFinalMax
					,audioSignBefore, audioLossBeforeMin, audioLossBeforeMax, audioSignAfter, audioLossAfterMin, audioLossAfterMax,audioSignFinal, audioLossFinalMin, audioLossFinalMax
					, pageSize, currPage);
			List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
		
			if (list == null || list.size() == 0){
				result.setResult(2);
				return result;
			}
			result.setItems(list);
			result.setCount(meetingInfoSummaryResult.getCount());
		} catch (Exception e) {
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}
		return result;
	}
	
	@Path("/aboutAll")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	 public ItemsResult<MeetingInfoSummaryDTO> aboutAll(@FormParam("startTime") long startTime,
				@FormParam("endTime") long endTime,
				@FormParam("meetingId") int meetingId, 
				@FormParam("relayId") String relayId, 
				@FormParam("userId") String userId,
				@FormParam("attendCountSign") String attendCountSign, 
				@FormParam("attendCountMin") int attendCountMin,
				@FormParam("attendCountMax") int attendCountMax,
				@FormParam("upVideoSignBefore") String upVideoSignBefore, 
				@FormParam("upVideoLossBeforeMin") float upVideoLossBeforeMin,
				@FormParam("upVideoLossBeforeMax") float upVideoLossBeforeMax,
				@FormParam("upVideoSignAfter") String upVideoSignAfter, 
				@FormParam("upVideoLossAfterMin") float upVideoLossAfterMin, 
				@FormParam("upVideoLossAfterMax") float upVideoLossAfterMax,
				@FormParam("upVideoSignFinal") String upVideoSignFinal,
		        @FormParam("upVideoLossFinalMin") float upVideoLossFinalMin,
		        @FormParam("upVideoLossFinalMax") float upVideoLossFinalMax,
				@FormParam("downVideoSignBefore") String downVideoSignBefore, 
				@FormParam("downVideoLossBeforeMin") float downVideoLossBeforeMin, 
				@FormParam("downVideoLossBeforeMax") float downVideoLossBeforeMax, 
				@FormParam("downVideoSignAfter") String downVideoSignAfter, 
				@FormParam("downVideoLossAfterMin") float downVideoLossAfterMin,
				@FormParam("downVideoLossAfterMax") float downVideoLossAfterMax,
				@FormParam("downVideoSignFinal") String downVideoSignFinal,
		        @FormParam("downVideoLossFinalMin") float downVideoLossFinalMin,
		        @FormParam("downVideoLossFinalMax") float downVideoLossFinalMax,
		        @FormParam("videoSignBefore") String videoSignBefore, 
				@FormParam("videoLossBeforeMin") float videoLossBeforeMin, 
				@FormParam("videoLossBeforeMax") float videoLossBeforeMax, 
				@FormParam("videoSignAfter") String videoSignAfter, 
				@FormParam("videoLossAfterMin") float videoLossAfterMin,
				@FormParam("videoLossAfterMax") float videoLossAfterMax,
				@FormParam("videoSignFinal") String videoSignFinal,
		        @FormParam("videoLossFinalMin") float videoLossFinalMin,
		        @FormParam("videoLossFinalMax") float videoLossFinalMax,
		        @FormParam("upAudioSignBefore") String upAudioSignBefore, 
				@FormParam("upAudioLossBeforeMin") float upAudioLossBeforeMin,
				@FormParam("upAudioLossBeforeMax") float upAudioLossBeforeMax,
				@FormParam("upAudioSignAfter") String upAudioSignAfter, 
				@FormParam("upAudioLossAfterMin") float upAudioLossAfterMin, 
				@FormParam("upAudioLossAfterMax") float upAudioLossAfterMax,
				@FormParam("upAudioSignFinal") String upAudioSignFinal,
		        @FormParam("upAudioLossFinalMin") float upAudioLossFinalMin,
		        @FormParam("upAudioLossFinalMax") float upAudioLossFinalMax,
				@FormParam("downAudioSignBefore") String downAudioSignBefore, 
				@FormParam("downAudioLossBeforeMin") float downAudioLossBeforeMin, 
				@FormParam("downAudioLossBeforeMax") float downAudioLossBeforeMax, 
				@FormParam("downAudioSignAfter") String downAudioSignAfter, 
				@FormParam("downAudioLossAfterMin") float downAudioLossAfterMin,
				@FormParam("downAudioLossAfterMax") float downAudioLossAfterMax,
				@FormParam("downAudioSignFinal") String downAudioSignFinal,
		        @FormParam("downAudioLossFinalMin") float downAudioLossFinalMin,
		        @FormParam("downAudioLossFinalMax") float downAudioLossFinalMax,
		        @FormParam("AudioSignBefore") String audioSignBefore, 
				@FormParam("audioLossBeforeMin") float audioLossBeforeMin, 
				@FormParam("audioLossBeforeMax") float audioLossBeforeMax, 
				@FormParam("audioSignAfter") String audioSignAfter, 
				@FormParam("audioLossAfterMin") float audioLossAfterMin,
				@FormParam("audioLossAfterMax") float audioLossAfterMax,
				@FormParam("audioSignFinal") String audioSignFinal,
		        @FormParam("audioLossFinalMin") float audioLossFinalMin,
		        @FormParam("audioLossFinalMax") float audioLossFinalMax,
				@FormParam("ducationsign") String ducationsign,
				@FormParam("ducationMin") long ducationMin,
				@FormParam("ducationMax") long ducationMax,
				@FormParam("pageSize") int pageSize, 
				@FormParam("currPage") int currPage){
		ItemsResult<MeetingInfoSummaryDTO> result = new ItemsResult<MeetingInfoSummaryDTO>();
		try {
			result.setCurrPage(currPage);
			result.setPageSize(pageSize);
			
			MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByAll(startTime, endTime
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
			List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
		
			if (list == null || list.size() == 0){
				result.setResult(2);
				return result;
			}
			result.setItems(list);
			result.setCount(meetingInfoSummaryResult.getCount());
		} catch (Exception e) {
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}
		return result;
	}
	
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
				
				MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByAttendCount(startTime, endTime, attendCountSign, attendCountMin, attendCountMax, pageSize, currPage);
				List<MeetingInfoSummaryEntity> list = meetingInfoSummaryResult.getListMeetingInfoSummaryEntities();
				//查询会议概要表，如果没有查询到，即查询结果为空
				//test
				//list = null;
				if (list == null || list.size() == 0){
					result.setResult(2);
					List<LossPackInfoDTO> list2 = null;
					result.setItems(list2);
					result.setCount(0);
				}else {
					//如果有查询结果，放入会议号列表中，并以此为条件查询详细表
					List<Integer> meetingList = new ArrayList<Integer>();
					for (MeetingInfoSummaryEntity entity : list) {
						//if (dto.getMeetingId() != 30066477) {
						meetingList.add(entity.getMeetingId());
						//}
						//会有一些会议太大 导致数据无法传送到webShow（而且一个sheet只能写65535行 数据太多也什么意思） 所以去掉该逻辑 还是
//					endTime = entity.getEndTime()/1000 > endTime ? entity.getEndTime()/1000:endTime;
					}
					try{
						LossPackInfoService lossPackInfoService = new LossPackInfoServiceImpl();
						String speakerId = "";
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						lossPackInfoService.setMeetingStartTime(df.format(startTime*1000));
						lossPackInfoService.setMeetingEndTime(df.format(endTime*1000));
						LossPackInfoResult lossPackInfoResult = lossPackInfoService.findByMeetingIds(meetingList,speakerId,directionType,endTime,null);
						List<LossPackInfoDTO> list2 = lossPackInfoResult.getListLossPackInfoDtos();
						if (list2 == null || list2.size() == 0){
							result.setResult(2);
							//return result;
						}
						result.setItems(list2);
						result.setCount(lossPackInfoResult.getCount());
						lossPackInfoResult = null;
						list2              = null;
						
					}
					catch (Exception e) {
						String message = e.getMessage() == null ? "" : e.getMessage();
						logger.error(message, e);
						result.setResult(-1);
						System.gc();
					}
				}
//				result.setItems(list);
//				result.setCount(meetingInfoSummaryResult.getCount());
				meetingInfoSummaryResult = null;
				list = null;
			} catch (Exception e) {
				String message = e.getMessage() == null ? "" : e.getMessage();
				logger.error(message, e);
				System.gc();
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
				result = null;
			} catch (Exception e) {
				e.printStackTrace();
				System.gc();
			}
			System.out.println("data.length:"+data.length);
			System.gc();
			return data;
	}
	
	@Path("/aboutDetailData")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	 public byte[] aboutDetailData(@FormParam("startTime") long startTime,
				@FormParam("endTime") long endTime){
			int pageSize = 0;
			int currPage = 0;
			ItemsResult<MeetingInfoSummaryDTO> result1 = new ItemsResult<MeetingInfoSummaryDTO>();
			ItemsResult<FormDataExcelDTO> result = new ItemsResult<FormDataExcelDTO>(); 
			try {
				result1.setCurrPage(currPage);
				result1.setPageSize(pageSize);
				
				MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByid(startTime, endTime, null, 0, null, pageSize, currPage);
				List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
				//查询会议概要表，如果没有查询到，即查询结果为空

				if (list == null || list.size() == 0){
					result.setResult(2);
					List<FormDataExcelDTO> list2 = null;
					result.setItems(list2);
					result.setCount(0);
				}else {
					//如果有查询结果，放入会议号列表中，并以此为条件查询详细表
					List<Integer> meetingList = new ArrayList<Integer>();
					for (MeetingInfoSummaryDTO dto : list) {
						meetingList.add(dto.getMeetingId());
					}
					try{
						LossPackInfoService lossPackInfoService = new LossPackInfoServiceImpl();
//						int micId = 0;
						List<FormDataExcelDTO> list2 = lossPackInfoService.findByMeetingIds(meetingList);
						if (list2 == null || list2.size() == 0){
							result.setResult(2);
						}
						result.setItems(list2);
					}
					catch (Exception e) {
						String message = e.getMessage() == null ? "" : e.getMessage();
						logger.error(message, e);
						result.setResult(-1);
					}
				}
			} catch (Exception e) {
				String message = e.getMessage() == null ? "" : e.getMessage();
				logger.error(message, e);
				result.setResult(-1);
			}
			byte[] data = null;
			
			try {
				Gson gs = new Gson();
				byte[] input = gs.toJson(result).getBytes("UTF-8");
				data = GZipUtils.compress(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("data.length:"+data.length);
			return data;
		}
	
//	@Path("/aboutDetailDataForReportNew")
//	@POST
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	 public ItemsResult<FormDataExcelDTO> aboutDetailDataNew(@FormParam("startTime") long startTime,
//				@FormParam("endTime") long endTime){
//			int pageSize = 0;
//			int currPage = 0;
//			ItemsResult<MeetingInfoSummaryDTO> result1 = new ItemsResult<MeetingInfoSummaryDTO>();
//			ItemsResult<FormDataExcelDTO> result = new ItemsResult<FormDataExcelDTO>(); 
//			int resValue = 0 ;
//			ExportFile exportFile = new ExportFile();
//			try {
//				result1.setCurrPage(currPage);
//				result1.setPageSize(pageSize);
//				
//				MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByid(startTime, endTime, null, 0, null, pageSize, currPage);
//				List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
//				//查询会议概要表，如果没有查询到，即查询结果为空
//
//				if (list == null || list.size() == 0){
//					resValue = 2;
//					result.setResult(resValue);
//					List<FormDataExcelDTO> list2 = null;
//					result.setItems(list2);
//					result.setCount(0);
//					result.setUrl(exportFile.mulMeetingExportExcel(resValue, list2, startTime,request));
//					
//				}else {
//					//如果有查询结果，放入会议号列表中，并以此为条件查询详细表
//					List<Integer> meetingList = new ArrayList<Integer>();
//					for (MeetingInfoSummaryDTO dto : list) {
//						meetingList.add(dto.getMeetingId());
//					}
//					try{
//						LossPackInfoService lossPackInfoService = new LossPackInfoServiceImpl();
//						//int micId = 0;
//						List<FormDataExcelDTO> list2 = lossPackInfoService.findByMeetingIds(meetingList);
//						if (list2 == null || list2.size() == 0){
//							resValue = 2;
//							result.setResult(resValue);
//						}
//						//将这些list写入execl文件
//						//ExportFile exportFile = new ExportFile();
//						//String path = request.getServletContext().getRealPath("/");
//						String url = exportFile.mulMeetingExportExcel(0, list2, startTime,request);
//						if (url == null) {
//							result.setResult(2);
//							//return result;
//						}
//						result.setUrl(url);
//						result.setCount(list2.size());
//					}
//					catch (Exception e) {
//						String message = e.getMessage() == null ? "" : e.getMessage();
//						logger.error(message, e);
//						result.setResult(-1);
//					}
//				}
//			} catch (Exception e) {
//				String message = e.getMessage() == null ? "" : e.getMessage();
//				logger.error(message, e);
//				result.setResult(-1);
//				//result.setUrl(null);
//			}
//		return result;	
//	}
	
	
	@Path("/aboutDetailDataForReportNew")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	 public ItemsResult<FormDataExcelDTO> aboutDetailDataNew(@FormParam("startTime") long startTime,
			@FormParam("endTime") long endTime, 
			@FormParam("index") int index){
		int pageSize = 0;
		int currPage = 0;
		ItemsResult<MeetingInfoSummaryDTO> result1 = new ItemsResult<MeetingInfoSummaryDTO>();
		ItemsResult<FormDataExcelDTO> result = new ItemsResult<FormDataExcelDTO>(); 
		result.setIndex(index);
		int resValue = 0 ;
		//long startTime = Long.parseLong(request.getParameter("startTime"));
		//long endTime = Long.parseLong(request.getParameter("endTime"));
		ExportFile exportFile = new ExportFile();
		try {
			result1.setCurrPage(currPage);
			result1.setPageSize(pageSize);
			
			MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByid(startTime, endTime, null, 0, null, pageSize, currPage);
			List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
			//查询会议概要表，如果没有查询到，即查询结果为空

			if (list == null || list.size() == 0){
				resValue = 2;
				result.setResult(resValue);
				List<FormDataExcelDTO> list2 = null;
				result.setItems(list2);
				result.setCount(0);
				result.setUrl(exportFile.mulMeetingExportExcel(resValue, list2, startTime,request));
				
			}else {
				//如果有查询结果，放入会议号列表中，并以此为条件查询详细表
				List<Integer> meetingList = new ArrayList<Integer>();
				for (MeetingInfoSummaryDTO dto : list) {
					//临时版干掉杭州会议
//					if (dto.getMeetingId() != 30066477) {
						meetingList.add(dto.getMeetingId());
//					}
				}
				try{
					LossPackInfoService lossPackInfoService = new LossPackInfoServiceImpl();
					
					//List<FormDataExcelDTO> list2 = lossPackInfoService.findByMeetingIds(meetingList,CommStats.pageSize);
					
					//临时版本
					List<FormDataExcelDTO> list2 = lossPackInfoService.findByMeetingIdsForTempVersion(meetingList,CommStats.pageSize,index);
					if (list2 == null || list2.size() == 0){
						resValue = 2;
						result.setResult(resValue);
					}
					//将这些list写入execl文件
					String url = exportFile.mulMeetingExportExcel(0, list2, startTime,request);
					if (url == null) {
						result.setResult(2);
						//return result;
					}
					result.setUrl(url);
					result.setCount(list2.size());
				}
				catch (Exception e) {
					String message = e.getMessage() == null ? "" : e.getMessage();
					logger.error(message, e);
					result.setResult(-1);
				}
			}
		} catch (Exception e) {
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
			//result.setUrl(null);
		}
	return result;		
	}
	
	@Path("/aboutDetailDataForReportNew")
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	 public ItemsResult<FormDataExcelDTO> aboutDetailDataNew(){
		int pageSize = 0;
		int currPage = 0;
		ItemsResult<MeetingInfoSummaryDTO> result1 = new ItemsResult<MeetingInfoSummaryDTO>();
		ItemsResult<FormDataExcelDTO> result = new ItemsResult<FormDataExcelDTO>(); 
		
		int resValue = 0 ;
		long startTime = Long.parseLong(request.getParameter("startTime"));
		long endTime = Long.parseLong(request.getParameter("endTime"));
		int index = Integer.parseInt(request.getParameter("index"));
		result.setIndex(index);
		
		ExportFile exportFile = new ExportFile();
		try {
			result1.setCurrPage(currPage);
			result1.setPageSize(pageSize);
			
			MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByid(startTime, endTime, null, 0, null, pageSize, currPage);
			List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
			//查询会议概要表，如果没有查询到，即查询结果为空

			if (list == null || list.size() == 0){
				resValue = 2;
				result.setResult(resValue);
				List<FormDataExcelDTO> list2 = null;
				result.setItems(list2);
				result.setCount(0);
				result.setUrl(exportFile.mulMeetingExportExcel(resValue, list2, startTime,request));
				
			}else {
				//如果有查询结果，放入会议号列表中，并以此为条件查询详细表
				List<Integer> meetingList = new ArrayList<Integer>();
				for (MeetingInfoSummaryDTO dto : list) {
					//临时版干掉杭州会议
					if (dto.getMeetingId() != 30066477) {
						meetingList.add(dto.getMeetingId());
					}
				}
				try{
					LossPackInfoService lossPackInfoService = new LossPackInfoServiceImpl();

					//List<FormDataExcelDTO> list2 = lossPackInfoService.findByMeetingIds(meetingList,CommStats.pageSize);
					//临时版本
					List<FormDataExcelDTO> list2 = lossPackInfoService.findByMeetingIdsForTempVersion(meetingList,CommStats.pageSize,index);
					if (list2 == null || list2.size() == 0){
						resValue = 2;
						result.setResult(resValue);
					}
					//将这些list写入execl文件
					String url = exportFile.mulMeetingExportExcel(0, list2, startTime,request);
					if (url == null) {
						result.setResult(2);
						//return result;
					}
					result.setUrl(url);
					result.setCount(list2.size());
				}
				catch (Exception e) {
					String message = e.getMessage() == null ? "" : e.getMessage();
					logger.error(message, e);
					result.setResult(-1);
				}
			}
		} catch (Exception e) {
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
			//result.setUrl(null);
		}
	return result;	
	}
/*	
	*//**
	 * 导出报告
	 * @param startTime
	 * @param endTime
	 * @param attendCountSign
	 * @param attendCountMin
	 * @param attendCountMax
	 * @param pageSize
	 * @param currPage
	 * @return
	 *//*
	@Path("/aboutMakeMarketReport")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public byte[] aboutMakeMarketReport(@FormParam("startTime") long startTime,
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
				
				MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByAttendCount(startTime, endTime, attendCountSign, attendCountMin, attendCountMax, pageSize, currPage);
				List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
				//查询会议概要表，如果没有查询到，即查询结果为空
				//test
				//list = null;
				if (list == null || list.size() == 0){
					result.setResult(2);
					List<LossPackInfoDTO> list2 = null;
					result.setItems(list2);
					result.setCount(0);
				}else {
					//如果有查询结果，放入会议号列表中，并以此为条件查询详细表
					List<Integer> meetingList = new ArrayList<Integer>();
					for (MeetingInfoSummaryDTO dto : list) {
						//if (dto.getMeetingId() != 30066477) {
							meetingList.add(dto.getMeetingId());
						//}
					}
					try{
						LossPackInfoService lossPackInfoService = new LossPackInfoServiceImpl();
						String speakerId = "";
						//zhanghy 1214 加上起止时间
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						lossPackInfoService.setMeetingStartTime(df.format(startTime*1000));
						lossPackInfoService.setMeetingEndTime(df.format(endTime*1000));
						LossPackInfoResult lossPackInfoResult = lossPackInfoService.findByMeetingIds(meetingList,speakerId,directionType,endTime,CommStats.userList);
						List<LossPackInfoDTO> list2 = lossPackInfoResult.getListLossPackInfoDtos();
						if (list2 == null || list2.size() == 0){
							result.setResult(2);
							//return result;
						}
						result.setItems(list2);
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
				//byte[] input = net.sf.json.JSONObject.fromObject(result).toString().getBytes("UTF-8");
				Gson gs = new Gson();
				byte[] input = gs.toJson(result).getBytes("UTF-8");
				//System.out.println("input.length:"+input.length);
				data = GZipUtils.compress(input);
				//System.out.println("data.length:"+data.length);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("data.length:"+data.length);
			return data;
	}*/
	/**
	 * 导出报告 zhanghy 2017-01-18加上会议的开始时间，结束时间
	 * @param startTime
	 * @param endTime
	 * @param attendCountSign
	 * @param attendCountMin
	 * @param attendCountMax
	 * @param pageSize
	 * @param currPage
	 * @return
	 */
	@Path("/aboutMakeMarketReport")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public byte[] aboutMakeMarketReport(@FormParam("startTime") long startTime,
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
				
				MeetingInfoSummaryResult meetingInfoSummaryResult = meetingInfoSummaryService.findByAttendCount(startTime, endTime, attendCountSign, attendCountMin, attendCountMax, pageSize, currPage);
				List<MeetingInfoSummaryDTO> list = meetingInfoSummaryResult.getListMeetingInfoSummaryDtos();
				//查询会议概要表，如果没有查询到，即查询结果为空
				//test
				//list = null;
				if (list == null || list.size() == 0){
					result.setResult(2);
					List<LossPackInfoDTO> list2 = null;
					result.setItems(list2);
					result.setCount(0);
				}else {
					//如果有查询结果，放入会议号列表中，并以此为条件查询详细表
					List<Integer> meetingList = new ArrayList<Integer>();
					Map<Integer, JSONObject> meetingInfoMap = new HashMap<Integer, JSONObject>();
					for (MeetingInfoSummaryDTO dto : list) {
							int tmp_userCount = 0;
						//if (dto.getMeetingId() != 30066477) {
							meetingList.add(dto.getMeetingId());
						//}
							JSONObject meetingInfo = new JSONObject();
							meetingInfo.put("startTime", dto.getTimeStamps());
							meetingInfo.put("endTime", dto.getEndTimes());
							
							for(String userId : dto.getUserIdList()) {
								  if(userId.length() > 4){
									  tmp_userCount++;
								  }
							}
							//meetingInfo.put("userCount", dto.getUserCount());
							meetingInfo.put("userCount", tmp_userCount);
							meetingInfo.put("userList", dto.getUserIdList());
							meetingInfoMap.put(dto.getMeetingId(), meetingInfo);				
					}
					try{
						LossPackInfoService lossPackInfoService = new LossPackInfoServiceImpl();
						String speakerId = "";
						//zhanghy 2016-1214 加上起止时间
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						lossPackInfoService.setMeetingStartTime(df.format(startTime*1000));
						lossPackInfoService.setMeetingEndTime(df.format(endTime*1000));
						LossPackInfoResult lossPackInfoResult = lossPackInfoService.findByMeetingIds(meetingList,speakerId,directionType,endTime,CommStats.userList);
						List<LossPackInfoDTO> list2 = lossPackInfoResult.getListLossPackInfoDtos();
						if (list2 == null || list2.size() == 0){
							result.setResult(2);
							//return result;
						}
						result.setItems(list2);
						result.setCount(lossPackInfoResult.getCount());
						result.setMeetingInfoMap(meetingInfoMap);
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
				//byte[] input = net.sf.json.JSONObject.fromObject(result).toString().getBytes("UTF-8");
				Gson gs = new Gson();
				byte[] input = gs.toJson(result).getBytes("UTF-8");
				//System.out.println("input.length:"+input.length);
				data = GZipUtils.compress(input);
				//System.out.println("data.length:"+data.length);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("data.length:"+data.length);
			return data;
	}
}
