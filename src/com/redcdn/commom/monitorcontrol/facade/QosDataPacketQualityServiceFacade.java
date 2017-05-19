package com.redcdn.commom.monitorcontrol.facade;

import java.util.List;

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

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.redcdn.commom.GZipUtils;
import com.redcdn.commom.monitorcontrol.db.business.DataPacketQualityService;
import com.redcdn.commom.monitorcontrol.db.business.impl.DataPacketQualityServiceImpl;
import com.redcdn.commom.monitorcontrol.db.entity.DataPacketQualityEntity;
import com.redcdn.commom.monitorcontrol.result.ItemsResult;

@Service
@Path("/QosDataPacketQualityService")
@SuppressWarnings("unchecked")
public class QosDataPacketQualityServiceFacade {

	
	@Context
	HttpServletResponse response;
	
	@Context
	HttpServletRequest request;
	
	protected Logger logger = Logger.getLogger(this.getClass());
	private DataPacketQualityService dataPacketQualityService = new DataPacketQualityServiceImpl();
	
	@Path("/aboutDataPacketQuality")
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemsResult<DataPacketQualityEntity> aboutDataPacketQuality(){
//	       (@FormParam("key") String key, 
//			@FormParam("pageSize") int pageSize,
//			@FormParam("currPage") int currPage){
		logger.info("进入server的aboutDataPacketQuality查询");
		int pageSize = 0;
		int currPage = 0;
		String key = request.getParameter("key");
		ItemsResult<DataPacketQualityEntity> result = new ItemsResult<DataPacketQualityEntity>();
		try {
			result.setCurrPage(currPage);
			result.setPageSize(pageSize);
			
			List<DataPacketQualityEntity> list = dataPacketQualityService.findBykey(key, pageSize, currPage);

		
			if (list == null || list.size() == 0){
				result.setResult(2);
				return result;
			}
			result.setItems(list);
			result.setCount(list.size());
		} catch (Exception e) {
			// TODO: handle exception
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}
		logger.info("退入server的aboutDataPacketQuality查询");
		return result;
	}
	
	@Path("/aboutDataPacketQuality")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemsResult<DataPacketQualityEntity> aboutDataPacketQuality(@FormParam("key") String key, 
			@FormParam("pageSize") int pageSize,
			@FormParam("currPage") int currPage){
		logger.info("进入server的aboutDataPacketQuality查询");
//		int pageSize = 0;
//		int currPage = 0;
//		String key = request.getParameter("key");
		ItemsResult<DataPacketQualityEntity> result = new ItemsResult<DataPacketQualityEntity>();
		try {
			result.setCurrPage(currPage);
			result.setPageSize(pageSize);
			
			List<DataPacketQualityEntity> list = dataPacketQualityService.findBykey(key, pageSize, currPage);

		
			if (list == null || list.size() == 0){
				result.setResult(2);
				return result;
			}
			result.setItems(list);
			result.setCount(list.size());
		} catch (Exception e) {
			// TODO: handle exception
			String message = e.getMessage() == null ? "" : e.getMessage();
			logger.error(message, e);
			result.setResult(-1);
		}
		logger.info("退出server的aboutDataPacketQuality查询");
		return result;
	}
	
	@Path("/aboutDataPacketQualityNew")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public byte[] aboutDataPacketQualityNew(@FormParam("keyLogId") String key, 
			@FormParam("pageSize") int pageSize,
			@FormParam("currPage") int currPage) {
		logger.info("进入server的aboutDataPacketQualityNew查询");
		ItemsResult<DataPacketQualityEntity> result = new ItemsResult<DataPacketQualityEntity>();
		try {
			result.setCurrPage(currPage);
			result.setPageSize(pageSize);
			
			List<DataPacketQualityEntity> list = dataPacketQualityService.findBykey(key, pageSize, currPage);
			
			if (list == null || list.size() == 0){
				result.setResult(2);
//				return result;
			}
			result.setItems(list);
			result.setCount(list.size());
		} catch (Exception e) {
			// TODO: handle exception
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
		logger.info("退入server的aboutDataPacketQualityNew查询");
		return data;
	}
}
