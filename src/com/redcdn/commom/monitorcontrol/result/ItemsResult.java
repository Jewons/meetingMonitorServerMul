package com.redcdn.commom.monitorcontrol.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import net.sf.json.JSONObject;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.mongodb.util.JSON;
import com.redcdn.commom.monitorcontrol.db.entity.BandWithAdaptiveEntity;
import com.redcdn.commom.monitorcontrol.db.entity.CallDisconnectEntity;
import com.redcdn.commom.monitorcontrol.db.entity.DataPacketQualityEntity;
import com.redcdn.commom.monitorcontrol.dto.DTO;
import com.redcdn.commom.monitorcontrol.dto.FormDataExcelDTO;
import com.redcdn.commom.monitorcontrol.dto.FramePacketInfoDTO;
import com.redcdn.commom.monitorcontrol.dto.LossPackInfoDTO;
import com.redcdn.commom.monitorcontrol.dto.MeetingInfoDTO;
import com.redcdn.commom.monitorcontrol.dto.MeetingInfoSummaryDTO;
import com.redcdn.commom.monitorcontrol.dto.ResendReqConmandDTO;
import com.redcdn.commom.monitorcontrol.dto.ShortLinkInfoDTO;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "itemsResult")
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class ItemsResult<T extends DTO> extends Result<Object>
{

    public ItemsResult()
    {

    }


    private Integer pageSize;
    private Integer currPage;
    private Integer count;
    
    private String url;
    private int index;
    private Map<Integer, JSONObject> meetingInfoMap;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private List<Integer> mediaType = new ArrayList<Integer>();
    
    public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public List<Integer> getMediaType() {
		return mediaType;
	}

	public void setMediaType(List<Integer> mediaType) {
		this.mediaType = mediaType;
	}
	
	@JsonSerialize(include = Inclusion.NON_NULL)
	private T data; 
    
    private Object object;
    
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    @XmlElements(value =
    {@XmlElement(type = MeetingInfoDTO.class),
    		@XmlElement(type = ShortLinkInfoDTO.class),
    		@XmlElement(type = ResendReqConmandDTO.class),
    		@XmlElement(type = FramePacketInfoDTO.class),
    		@XmlElement(type = CallDisconnectEntity.class),
    		@XmlElement(type = MeetingInfoSummaryDTO.class),
    		@XmlElement(type = LossPackInfoDTO.class),
    		@XmlElement(type = BandWithAdaptiveEntity.class),
    		@XmlElement(type = FormDataExcelDTO.class),
    		@XmlElement(type = DataPacketQualityEntity.class)})
    private List<T> items=new ArrayList<T>();
    
    public List<T> getItems()
    {
        return items;
    }

    public void setItems(List<T> items)
    {
      if(items != null)
        this.items = items;
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Map<Integer, JSONObject> getMeetingInfoMap() {
		return meetingInfoMap;
	}

	public void setMeetingInfoMap(Map<Integer, JSONObject> meetingInfoMap) {
		this.meetingInfoMap = meetingInfoMap;
	}
	    
}