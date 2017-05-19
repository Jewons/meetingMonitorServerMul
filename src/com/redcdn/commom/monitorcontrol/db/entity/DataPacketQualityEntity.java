package com.redcdn.commom.monitorcontrol.db.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import com.redcdn.commom.monitorcontrol.dto.DTO;

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class DataPacketQualityEntity extends DTO{
	
	private String id;
	
	private int    index;
	
	private String data;
	
	private int    role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	
}
