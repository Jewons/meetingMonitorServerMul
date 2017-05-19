package com.redcdn.commom.monitorcontrol.convertor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.redcdn.commom.BeanConvertorImpl;
import com.redcdn.commom.BeanWrapperUtils;
import com.redcdn.commom.monitorcontrol.db.entity.FramePacketInfoEntity;
import com.redcdn.commom.monitorcontrol.dto.FramePacketInfoDTO;

public class FramePacketInfoConvert extends BeanConvertorImpl<FramePacketInfoEntity,FramePacketInfoDTO>{
	    @Override
	    public void convertBean(FramePacketInfoEntity entity, FramePacketInfoDTO dto, Object... param)
	    {
	    	BeanWrapperUtils.copyProperties(entity, dto);
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	        dto.setRecvtimes(df.format(entity.getRecvtime()));
	        dto.setTimes(df.format(entity.getTime()));
	  
	    }
	    @Override
	    public void convertBean(FramePacketInfoEntity entity, FramePacketInfoDTO dto, String[] ignoreProperties, Object... param)
	    {
	    	 if (ignoreProperties == null) {
	    	      convertBean(entity, dto, param);
	    	    } else {
	    	      BeanWrapperUtils.copyProperties(entity, dto, ignoreProperties);
	    	      DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	  	          dto.setRecvtimes(df.format(entity.getRecvtime()));
	  	          dto.setTimes(df.format(entity.getTime()));
	  	  
	    	    }
	    }
	    
}
