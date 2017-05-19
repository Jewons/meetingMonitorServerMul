package com.redcdn.commom.monitorcontrol.db.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.redcdn.commom.monitorcontrol.dto.DTO;

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class CallDisconnectEntity extends DTO{
	  private String optime;
	  private String reason;
	  private String sid;
	  
	public String getOptime() {
		return optime;
	}
	public void setOptime(String optime) {
		this.optime = optime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
}
