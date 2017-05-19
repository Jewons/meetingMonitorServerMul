package com.redcdn.commom.monitorcontrol.dto;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
/**
 * 短链信息索引数据传输对象
 * @author 吴磊
 *
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class ShortLinkInfoDTO extends DTO{

}
