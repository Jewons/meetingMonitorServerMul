package com.redcdn.commom.monitorcontrol.convertor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.redcdn.commom.BeanConvertorImpl;
import com.redcdn.commom.BeanWrapperUtils;
import com.redcdn.commom.monitorcontrol.db.entity.FormDataExcelEntity;
import com.redcdn.commom.monitorcontrol.db.entity.FramePacketInfoEntity;
import com.redcdn.commom.monitorcontrol.dto.FormDataExcelDTO;
import com.redcdn.commom.monitorcontrol.dto.FramePacketInfoDTO;


public class FormDataExcelConvert extends BeanConvertorImpl<FormDataExcelEntity,FormDataExcelDTO>{
	 @Override
	    public void convertBean(FormDataExcelEntity entity, FormDataExcelDTO dto, Object... param)
	    {
	    	BeanWrapperUtils.copyProperties(entity, dto);
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        dto.setTimeStamps(df.format(entity.getTimeStamp()));
	  
	    }
	    @Override
	    public void convertBean(FormDataExcelEntity entity, FormDataExcelDTO dto, String[] ignoreProperties, Object... param)
	    {
	    	 if (ignoreProperties == null) {
	    	      convertBean(entity, dto, param);
	    	    } else {
	    	      BeanWrapperUtils.copyProperties(entity, dto, ignoreProperties);
	    	      DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  	          dto.setTimeStamps(df.format(entity.getTimeStamp()));
	    	    }
	    }
}
