package com.redcdn.commom.monitorcontrol.convertor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.redcdn.commom.BeanConvertorImpl;
import com.redcdn.commom.BeanWrapperUtils;
import com.redcdn.commom.monitorcontrol.db.entity.MeetingInfoSummaryEntity;
import com.redcdn.commom.monitorcontrol.dto.MeetingInfoSummaryDTO;

public class MeetingInfoSummaryConvert extends BeanConvertorImpl<MeetingInfoSummaryEntity,MeetingInfoSummaryDTO>{
	  @Override
	  public void convertBean(MeetingInfoSummaryEntity entity, MeetingInfoSummaryDTO dto, Object... param)
	  {
	    	BeanWrapperUtils.copyProperties(entity, dto);
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        dto.setTimeStamps(df.format(entity.getTimeStamp()));
	        if (entity.getEndTime()!= 0) {
	        	dto.setEndTimes(df.format(entity.getEndTime()));
			}
	        
//	        dto.setDownLossAudioEFCMic1DTO(((float)entity.getDownLossAudioEFCMic1())/100);
//	        dto.setDownLossAudioEFCMic2DTO(((float)entity.getDownLossAudioEFCMic2())/100);
//	        dto.setDownLossAudioMic1DTO(((float)entity.getDownLossAudioMic1())/100);
//	        dto.setDownLossAudioMic2DTO(((float)entity.getDownLossAudioMic2())/100);
//	        dto.setDownLossVideoEFCMic1DTO(((float)entity.getDownLossVideoEFCMic1())/100);
//	        dto.setDownLossVideoEFCMic2DTO(((float)entity.getDownLossVideoEFCMic2())/100);
//	        dto.setDownLossVideoMic1DTO(((float)entity.getDownLossVideoMic1())/100);
//	        dto.setDownLossVideoMic2DTO(((float)entity.getDownLossVideoMic2())/100);
//	        dto.setUpLossAudioEFCMic1DTO(((float)entity.getUpLossAudioEFCMic1())/100);
//	        dto.setUpLossAudioEFCMic2DTO(((float)entity.getUpLossAudioEFCMic2())/100);
//	        dto.setUpLossAudioMic1DTO(((float)entity.getUpLossAudioMic1())/100);
//	        dto.setUpLossAudioMic2DTO(((float)entity.getUpLossAudioMic2())/100);
//	        dto.setUpLossVideoEFCMic1DTO(((float)entity.getUpLossVideoEFCMic1())/100);
//	        dto.setUpLossVideoEFCMic2DTO(((float)entity.getUpLossVideoEFCMic2())/100);
//	        dto.setUpLossVideoMic1DTO(((float)entity.getUpLossVideoMic1())/100);
//	        dto.setUpLossVideoMic2DTO(((float)entity.getUpLossVideoMic2())/100);
	        //dto.setDurations(timeformat(entity.getDuration()));
//	        dto.setUpLossVideoFinalMic1DTO(((float)entity.getUpLossVideoFinalMic1())/100);
//	        dto.setDownLossVideoFinalMic1DTO(((float)entity.getDownLossVideoFinalMic1())/100);
//	        dto.setUpLossVideoFinalMic2DTO(((float)entity.getUpLossVideoFinalMic2())/100);
//	        dto.setDownLossVideoFinalMic2DTO(((float)entity.getDownLossVideoFinalMic2())/100);
//	        dto.setUpLossAudioFinalMic1DTO(((float)entity.getUpLossAudioFinalMic1())/100);
//	        dto.setDownLossAudioFinalMic1DTO(((float)entity.getDownLossAudioFinalMic1())/100);
//	        dto.setUpLossAudioFinalMic2DTO(((float)entity.getUpLossAudioFinalMic2())/100);
//	        dto.setDownLossAudioFinalMic2DTO(((float)entity.getDownLossAudioFinalMic2())/100);
//	        dto.setLossVideoMic1CCDTO(((float)entity.getLossVideoMic1CC())/100);
//	        dto.setLossVideoEFCMic1CCDTO(((float)entity.getLossVideoEFCMic1CC())/100);
//	        dto.setLossVideoMic1FinalCCDTO(((float)entity.getLossVideoMic1FinalCC())/100);
//	    	dto.setLossVideoMic2CCDTO(((float)entity.getLossVideoMic2CC())/100);
//	    	dto.setLossVideoEFCMic2CCDTO(((float)entity.getLossVideoEFCMic2CC())/100);
//	    	dto.setLossVideoMic2FinalCCDTO(((float)entity.getLossVideoMic2FinalCC())/100);
//	    	dto.setLossAudioMic1CCDTO(((float)entity.getLossAudioMic1CC())/100);
//	    	dto.setLossAudioEFCMic1CCDTO(((float)entity.getLossAudioEFCMic1CC())/100);
//	    	dto.setLossAudioMic1FinalCCDTO(((float)entity.getLossAudioMic1FinalCC())/100);
//	    	dto.setLossAudioMic2CCDTO(((float)entity.getLossAudioMic2CC())/100);
//	    	dto.setLossAudioEFCMic2CCDTO(((float)entity.getLossAudioEFCMic2CC())/100);
//	    	dto.setLossAudioMic2FinalCCDTO(((float)entity.getLossAudioMic2FinalCC())/100);
	    }  
	    @Override
	    public void convertBean(MeetingInfoSummaryEntity entity, MeetingInfoSummaryDTO dto, String[] ignoreProperties, Object... param)
	    {
	    	 if (ignoreProperties == null) {
	    	      convertBean(entity, dto, param);
	    	    } else {
	    	      BeanWrapperUtils.copyProperties(entity, dto, ignoreProperties);
	    	      DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	      dto.setTimeStamps(df.format(entity.getTimeStamp()));
	    	      if (entity.getEndTime()!= 0) {
	  	        	dto.setEndTimes(df.format(entity.getEndTime()));
	  			}
//	    	      dto.setDownLossAudioEFCMic1DTO(((float)entity.getDownLossAudioEFCMic1())/100);
//	    	      dto.setDownLossAudioEFCMic2DTO(((float)entity.getDownLossAudioEFCMic2())/100);
//	    	      dto.setDownLossAudioMic1DTO(((float)entity.getDownLossAudioMic1())/100);
//	    	      dto.setDownLossAudioMic2DTO(((float)entity.getDownLossAudioMic2())/100);
//	    	      dto.setDownLossVideoEFCMic1DTO(((float)entity.getDownLossVideoEFCMic1())/100);
//		  	      dto.setDownLossVideoEFCMic2DTO(((float)entity.getDownLossVideoEFCMic2())/100);
//		  	      dto.setDownLossVideoMic1DTO(((float)entity.getDownLossVideoMic1())/100);
//		  	      dto.setDownLossVideoMic2DTO(((float)entity.getDownLossVideoMic2())/100);
//		  	      dto.setUpLossAudioEFCMic1DTO(((float)entity.getUpLossAudioEFCMic1())/100);
//		  	      dto.setUpLossAudioEFCMic2DTO(((float)entity.getUpLossAudioEFCMic2())/100);
//		  	      dto.setUpLossAudioMic1DTO(((float)entity.getUpLossAudioMic1())/100);
//		  	      dto.setUpLossAudioMic2DTO(((float)entity.getUpLossAudioMic2())/100);
//		  	      dto.setUpLossVideoEFCMic1DTO(((float)entity.getUpLossVideoEFCMic1())/100);
//		  	      dto.setUpLossVideoEFCMic2DTO(((float)entity.getUpLossVideoEFCMic2())/100);
//		  	      dto.setUpLossVideoMic1DTO(((float)entity.getUpLossVideoMic1())/100);
//		  	      dto.setUpLossVideoMic2DTO(((float)entity.getUpLossVideoMic2())/100);
//		  	      dto.setUpLossVideoFinalMic1DTO(((float)entity.getUpLossVideoFinalMic1())/100);
//			      dto.setDownLossVideoFinalMic1DTO(((float)entity.getDownLossVideoFinalMic1())/100);
//			      dto.setUpLossVideoFinalMic2DTO(((float)entity.getUpLossVideoFinalMic2())/100);
//			      dto.setDownLossVideoFinalMic2DTO(((float)entity.getDownLossVideoFinalMic2())/100);
//			      dto.setUpLossAudioFinalMic1DTO(((float)entity.getUpLossAudioFinalMic1())/100);
//			      dto.setDownLossAudioFinalMic1DTO(((float)entity.getDownLossAudioFinalMic1())/100);
//			      dto.setUpLossAudioFinalMic2DTO(((float)entity.getUpLossAudioFinalMic2())/100);
//			      dto.setDownLossAudioFinalMic2DTO(((float)entity.getDownLossAudioFinalMic2())/100);
//			      dto.setLossVideoMic1CCDTO(((float)entity.getLossVideoMic1CC())/100);
//			      dto.setLossVideoEFCMic1CCDTO(((float)entity.getLossVideoEFCMic1CC())/100);
//			      dto.setLossVideoMic1FinalCCDTO(((float)entity.getLossVideoMic1FinalCC())/100);
//			      dto.setLossVideoMic2CCDTO(((float)entity.getLossVideoMic2CC())/100);
//			      dto.setLossVideoEFCMic2CCDTO(((float)entity.getLossVideoEFCMic2CC())/100);
//			      dto.setLossVideoMic2FinalCCDTO(((float)entity.getLossVideoMic2FinalCC())/100);
//			      dto.setLossAudioMic1CCDTO(((float)entity.getLossAudioMic1CC())/100);
//			      dto.setLossAudioEFCMic1CCDTO(((float)entity.getLossAudioEFCMic1CC())/100);
//			      dto.setLossAudioMic1FinalCCDTO(((float)entity.getLossAudioMic1FinalCC())/100);
//			      dto.setLossAudioMic2CCDTO(((float)entity.getLossAudioMic2CC())/100);
//			      dto.setLossAudioEFCMic2CCDTO(((float)entity.getLossAudioEFCMic2CC())/100);
//			      dto.setLossAudioMic2FinalCCDTO(((float)entity.getLossAudioMic2FinalCC())/100);
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
