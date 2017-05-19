package com.redcdn.commom.monitorcontrol.db.entity.view;

import java.util.List;

import com.redcdn.commom.monitorcontrol.db.entity.MeetingInfoSummaryEntity;
import com.redcdn.commom.monitorcontrol.dto.MeetingInfoSummaryDTO;

public class MeetingInfoSummaryResult {
	private List<MeetingInfoSummaryDTO> listMeetingInfoSummaryDtos;
	private List<MeetingInfoSummaryEntity> listMeetingInfoSummaryEntities;
	private int count;//
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<MeetingInfoSummaryDTO> getListMeetingInfoSummaryDtos() {
		return listMeetingInfoSummaryDtos;
	}
	public void setListMeetingInfoSummaryDtos(
			List<MeetingInfoSummaryDTO> listMeetingInfoSummaryDtos) {
		this.listMeetingInfoSummaryDtos = listMeetingInfoSummaryDtos;
	}
	public List<MeetingInfoSummaryEntity> getListMeetingInfoSummaryEntities() {
		return listMeetingInfoSummaryEntities;
	}
	public void setListMeetingInfoSummaryEntities(
			List<MeetingInfoSummaryEntity> listMeetingInfoSummaryEntities) {
		this.listMeetingInfoSummaryEntities = listMeetingInfoSummaryEntities;
	}
}
