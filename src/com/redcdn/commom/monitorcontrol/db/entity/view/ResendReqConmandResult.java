package com.redcdn.commom.monitorcontrol.db.entity.view;

import java.util.List;

import com.redcdn.commom.monitorcontrol.db.entity.ResendReqConmandEntity;
import com.redcdn.commom.monitorcontrol.dto.ResendReqConmandDTO;

public class ResendReqConmandResult {
	private List<ResendReqConmandDTO> listResendReqConmandDtos;
	private List<ResendReqConmandEntity> listResendReqConmandEntities;
	private int count;
	public List<ResendReqConmandDTO> getListResendReqConmandDtos() {
		return listResendReqConmandDtos;
	}
	public void setListResendReqConmandDtos(
			List<ResendReqConmandDTO> listResendReqConmandDtos) {
		this.listResendReqConmandDtos = listResendReqConmandDtos;
	}
	public List<ResendReqConmandEntity> getListResendReqConmandEntities() {
		return listResendReqConmandEntities;
	}
	public void setListResendReqConmandEntities(
			List<ResendReqConmandEntity> listResendReqConmandEntities) {
		this.listResendReqConmandEntities = listResendReqConmandEntities;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
