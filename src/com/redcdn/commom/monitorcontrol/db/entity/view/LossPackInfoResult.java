package com.redcdn.commom.monitorcontrol.db.entity.view;

import java.util.List;
import java.util.Set;

import com.redcdn.commom.monitorcontrol.db.entity.LossPackInfoEntity;
import com.redcdn.commom.monitorcontrol.dto.LossPackInfoDTO;

public class LossPackInfoResult {
	
	private List<LossPackInfoDTO> listLossPackInfoDtos;
	private List<LossPackInfoEntity> listLossPackInfoEntities;
	private int count;
	private Set<Integer> mediaTypeList;
	private int errorCode;
	public List<LossPackInfoDTO> getListLossPackInfoDtos() {
		return listLossPackInfoDtos;
	}
	public void setListLossPackInfoDtos(List<LossPackInfoDTO> listLossPackInfoDtos) {
		this.listLossPackInfoDtos = listLossPackInfoDtos;
	}
	public List<LossPackInfoEntity> getListLossPackInfoEntities() {
		return listLossPackInfoEntities;
	}
	public void setListLossPackInfoEntities(
			List<LossPackInfoEntity> listLossPackInfoEntities) {
		this.listLossPackInfoEntities = listLossPackInfoEntities;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Set<Integer> getMediaTypeList() {
		return mediaTypeList;
	}
	public void setMediaTypeList(Set<Integer> mediaTypeList) {
		this.mediaTypeList = mediaTypeList;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
