package com.redcdn.commom.monitorcontrol.convertor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.redcdn.commom.BeanConvertorImpl;
import com.redcdn.commom.BeanWrapperUtils;
import com.redcdn.commom.monitorcontrol.db.entity.MeetingInfoEntity;
import com.redcdn.commom.monitorcontrol.dto.MeetingInfoDTO;;


public class MeetingInfoConvert extends BeanConvertorImpl<MeetingInfoEntity,MeetingInfoDTO>
{

    @Override
    public void convertBean(MeetingInfoEntity entity, MeetingInfoDTO dto, Object... param)
    {
    	BeanWrapperUtils.copyProperties(entity, dto);
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//        dto.setTimeStamps(df.format(entity.getTimeStamp()));
//        dto.setLossPackRatioBeforeFECDTO(((float)entity.getLossPackRatioBeforeFEC())/100);
//        dto.setLossPackRatioAfterFECDTO(((float)entity.getLossPackRatioAfterFEC())/100);
//        dto.setAudioLossPackRatioBeforeFECDTO(((float)entity.getAudioLossPackRatioBeforeFEC())/100);
//        dto.setAudioLossPackRatioAfterFECDTO(((float)entity.getAudioLossPackRatioAfterFEC())/100);
        
        
    }
    @Override
    public void convertBean(MeetingInfoEntity entity, MeetingInfoDTO dto, String[] ignoreProperties, Object... param)
    {
    	 if (ignoreProperties == null) {
    	      convertBean(entity, dto, param);
    	    } else {
    	      BeanWrapperUtils.copyProperties(entity, dto, ignoreProperties);
//    	      DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	      dto.setTimeStamps(df.format(entity.getTimeStamp()));
//    	      dto.setLossPackRatioBeforeFECDTO(entity.getLossPackRatioBeforeFEC()/100);
//    	      dto.setLossPackRatioAfterFECDTO(entity.getLossPackRatioAfterFEC()/100);
//    	      dto.setAudioLossPackRatioBeforeFECDTO(entity.getAudioLossPackRatioBeforeFEC()/100);
//    	      dto.setAudioLossPackRatioAfterFECDTO(entity.getAudioLossPackRatioAfterFEC()/100);
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

