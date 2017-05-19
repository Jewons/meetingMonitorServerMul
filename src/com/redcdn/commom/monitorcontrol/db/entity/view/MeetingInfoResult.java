package com.redcdn.commom.monitorcontrol.db.entity.view;
import java.util.List;

import com.redcdn.commom.monitorcontrol.db.entity.MeetingInfoEntity;
import com.redcdn.commom.monitorcontrol.dto.MeetingInfoDTO;

public class MeetingInfoResult {

	private List<MeetingInfoDTO> listMeetingInfoDtos;
	private List<MeetingInfoEntity> listMeetingInfoEntities;
	private int count;
	public List<MeetingInfoDTO> getListMeetingInfoDtos() {
		return listMeetingInfoDtos;
	}
	public void setListMeetingInfoDtos(List<MeetingInfoDTO> listMeetingInfoDtos) {
		this.listMeetingInfoDtos = listMeetingInfoDtos;
	}
	public List<MeetingInfoEntity> getListMeetingInfoEntities() {
		return listMeetingInfoEntities;
	}
	public void setListMeetingInfoEntities(
			List<MeetingInfoEntity> listMeetingInfoEntities) {
		this.listMeetingInfoEntities = listMeetingInfoEntities;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}	
}
