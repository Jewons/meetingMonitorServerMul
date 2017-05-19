package com.redcdn.commom.monitorcontrol.dto;

import java.util.ArrayList;
import java.util.List;

import com.redcdn.commom.monitorcontrol.db.entity.FormDataExcelEntity;

public class FormDataExcelListDTO extends DTO{

	private int currPage;
	
	private int pageSize;
	
	private int totalPage;
	
	List<FormDataExcelDTO> dtos;
	
	List<FormDataExcelEntity> entities;
	
	public FormDataExcelListDTO(){
		entities = new ArrayList<FormDataExcelEntity>();
		dtos = new ArrayList<FormDataExcelDTO>();
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<FormDataExcelDTO> getDtos() {
		return dtos;
	}

	public void setDtos(List<FormDataExcelDTO> dtos) {
		this.dtos = dtos;
	}

	public List<FormDataExcelEntity> getEntities() {
		return entities;
	}

	public void setEntities(List<FormDataExcelEntity> entities) {
		this.entities = entities;
	}
	
	
}
