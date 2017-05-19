package com.redcdn.commom.monitorcontrol.convertor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.redcdn.commom.BeanConvertorImpl;
import com.redcdn.commom.BeanWrapperUtils;
import com.redcdn.commom.monitorcontrol.db.entity.ResendReqConmandEntity;
import com.redcdn.commom.monitorcontrol.dto.ResendReqConmandDTO;

public class ResendReqConmandConvert extends BeanConvertorImpl<ResendReqConmandEntity,ResendReqConmandDTO>{
	 @Override
	    public void convertBean(ResendReqConmandEntity entity, ResendReqConmandDTO dto, Object... param)
	    {
	    	BeanWrapperUtils.copyProperties(entity, dto);
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	        dto.setRecvtimes(df.format(entity.getRecvtime()));
	  
	    }
	    @Override
	    public void convertBean(ResendReqConmandEntity entity, ResendReqConmandDTO dto, String[] ignoreProperties, Object... param)
	    {
	    	 if (ignoreProperties == null) {
	    	      convertBean(entity, dto, param);
	    	    } else {
	    	      BeanWrapperUtils.copyProperties(entity, dto, ignoreProperties);
	    	      DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	  	          dto.setRecvtimes(df.format(entity.getRecvtime()));
	    	    }
	    }
}
