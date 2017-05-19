package com.redcdn.commom.monitorcontrol.db.entity.view;

import java.util.List;

import com.redcdn.commom.monitorcontrol.db.entity.FramePacketInfoEntity;
import com.redcdn.commom.monitorcontrol.dto.FramePacketInfoDTO;

public class FramePacketInfoResult {
	private List<FramePacketInfoDTO> listFramePacketInfoDtos;
	private List<FramePacketInfoEntity> listFramePacketInfoEntities;
	private int count;
	public List<FramePacketInfoDTO> getListFramePacketInfoDtos() {
		return listFramePacketInfoDtos;
	}
	public void setListFramePacketInfoDtos(
			List<FramePacketInfoDTO> listFramePacketInfoDtos) {
		this.listFramePacketInfoDtos = listFramePacketInfoDtos;
	}
	public List<FramePacketInfoEntity> getListFramePacketInfoEntities() {
		return listFramePacketInfoEntities;
	}
	public void setListFramePacketInfoEntities(
			List<FramePacketInfoEntity> listFramePacketInfoEntities) {
		this.listFramePacketInfoEntities = listFramePacketInfoEntities;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
