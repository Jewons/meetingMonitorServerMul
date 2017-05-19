package com.redcdn.commom.monitorcontrol.convertor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.redcdn.commom.BeanConvertorImpl;
import com.redcdn.commom.BeanWrapperUtils;
import com.redcdn.commom.monitorcontrol.db.entity.LossPackInfoEntity;
import com.redcdn.commom.monitorcontrol.dto.LossPackInfoDTO;

public class LossPackInfoConvert extends BeanConvertorImpl<LossPackInfoEntity,LossPackInfoDTO>{
	  @Override
	  public void convertBean(LossPackInfoEntity entity, LossPackInfoDTO dto, Object... param)
	  {
	    	BeanWrapperUtils.copyProperties(entity, dto);
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        dto.setTimeStamps(df.format(entity.getTimeStamp()));
	        dto.setServerTimes(df.format(entity.getServerTime()));
//	        dto.setAudiolossRateFECCCs(((float)entity.getAudiolossRateFECCC())/100);
//	        dto.setAudiolossRateFECs((float)(entity.getAudiolossRateFEC())/100);
//	        dto.setAudiolossRateFinalCCs(((float)entity.getAudiolossRateFinalCC())/100);
//	        dto.setAudiolossRateFinals(((float)entity.getAudiolossRateFinal())/100);
//	        dto.setAudiolossRateOriginalCCs(((float)entity.getAudiolossRateOriginalCC())/100);
//	        dto.setAudiolossRateOriginals(((float)entity.getAudiolossRateOriginal())/100);
	        dto.setEmptyPacketRates(((float)entity.getEmptyPacketRate())/100);
//	        dto.setVideolossRateFECCCs(((float)entity.getVideolossRateFECCC())/100);
//	        dto.setVideolossRateFECs(((float)entity.getVideolossRateFEC())/100);
//	        dto.setVideolossRateFinalCCs(((float)entity.getVideolossRateFinalCC())/100);
//	        dto.setVideolossRateFinals(((float)entity.getVideolossRateFinal())/100);
//	        dto.setVideolossRateOriginalCCs(((float)entity.getVideolossRateOriginalCC())/100);
//	        dto.setVideolossRateOriginals(((float)entity.getVideolossRateOriginal())/100);
//	        dto.setAudioLaterRate(((float)entity.getAudioLaterRate())/100);
//	        dto.setVedioLaterRate(((float)entity.getVedioLaterRate())/100);
	        dto.setLossRateFECs((float)entity.getLossRateFEC()/100);
	        dto.setLossRateFECCCs((float)entity.getLossRateFECCC()/100);
	        dto.setLossRateFinals((float)entity.getLossRateFinal()/100);
	        dto.setLossRateFinalCCs((float)entity.getLossRateFinalCC()/100);
	        dto.setLossRateOriginals((float)entity.getLossRateOriginal()/100);
	        dto.setLossRateOriginalCCs((float)entity.getLossRateOriginalCC()/100);
	    }
	    @Override
	    public void convertBean(LossPackInfoEntity entity, LossPackInfoDTO dto, String[] ignoreProperties, Object... param)
	    {
	    	 if (ignoreProperties == null) {
	    	      convertBean(entity, dto, param);
	    	    } else {
	    	    	BeanWrapperUtils.copyProperties(entity, dto);
	    	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	    	        dto.setTimeStamps(df.format(entity.getTimeStamp()));
//	    	        dto.setAudiolossRateFECCCs(((float)entity.getAudiolossRateFECCC())/100);
//	    	        dto.setAudiolossRateFECs((float)(entity.getAudiolossRateFEC())/100);
//	    	        dto.setAudiolossRateFinalCCs(((float)entity.getAudiolossRateFinalCC())/100);
//	    	        dto.setAudiolossRateFinals(((float)entity.getAudiolossRateFinal())/100);
//	    	        dto.setAudiolossRateOriginalCCs(((float)entity.getAudiolossRateOriginalCC())/100);
//	    	        dto.setAudiolossRateOriginals(((float)entity.getAudiolossRateOriginal())/100);
	    	        dto.setEmptyPacketRates(((float)entity.getEmptyPacketRate())/100);
//	    	        dto.setVideolossRateFECCCs(((float)entity.getVideolossRateFECCC())/100);
//	    	        dto.setVideolossRateFECs(((float)entity.getVideolossRateFEC())/100);
//	    	        dto.setVideolossRateFinalCCs(((float)entity.getVideolossRateFinalCC())/100);
//	    	        dto.setVideolossRateFinals(((float)entity.getVideolossRateFinal())/100);
//	    	        dto.setVideolossRateOriginalCCs(((float)entity.getVideolossRateOriginalCC())/100);
//	    	        dto.setVideolossRateOriginals(((float)entity.getVideolossRateOriginal())/100);
	    	        dto.setLossRateFECs((float)entity.getLossRateFEC()/100);
	    	        dto.setLossRateFECCCs((float)entity.getLossRateFECCC()/100);
	    	        dto.setLossRateFinals((float)entity.getLossRateFinal()/100);
	    	        dto.setLossRateFinalCCs((float)entity.getLossRateFinalCC()/100);
	    	        dto.setLossRateOriginals((float)entity.getLossRateOriginal()/100);
	    	        dto.setLossRateOriginalCCs((float)entity.getLossRateOriginalCC()/100);
	    	    }
	    }
	    
	    private  String timeformat(long time) {
			String times = time+"毫秒";
			if (time >= 1000) {
				int timef = Math.round(time / 1000);// 转换到秒
				if (timef > 60) {
					int second = Math.round(timef % 60);
					int minite = Math.round(timef / 60);
					if (minite > 60) {
						int mminite = Math.round(minite % 60);
						int hour = Math.round(minite / 60);
						if (hour > 0) {
							times = hour + "小时" + mminite + "分" + second + "秒";
						} else {
							times = mminite + "分" + second + "秒";
						}
					} else {
						times = minite + "分" + second + "秒";
					}
				} else {
					times = timef + "秒";
				}

			}
			return times;
		}
}
