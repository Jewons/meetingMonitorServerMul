package com.redcdn.commom;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import com.redcdn.commom.monitorcontrol.dto.FormDataExcelDTO;


import org.apache.log4j.Logger;

public class ExportFile {
	protected Logger logger = Logger.getLogger(ExportFile.class);
	
	public String mulMeetingExportExcel(int result, List<FormDataExcelDTO> list,long time,HttpServletRequest request) throws IOException{
		
		HSSFWorkbook wb = new HSSFWorkbook(); // 新建工作簿
		HSSFSheet sheet;// 新建工作表
		String userId;
		String excelHead = "时间,网络类型,传输方向,MicId,用户IP,RelayIp,客观带宽,使用带宽";
		String[] head = excelHead.split(",");
		int num = 0;
		if (result == 0) {
			if (list.size() > 0) {
				int length = list.size();
				userId = list.get(0).getUserId()+"_"+list.get(0).getMeetingId();
				sheet = wb.createSheet(userId);
				sheet.setDefaultColumnWidth(17);
				//添加表头
				HSSFRow nRow = sheet.createRow(0);
				for (int i = 0; i<head.length;i++) {
					HSSFCell nCell = nRow.createCell(i);
					nCell.setCellValue(head[i]);
				}
				//添加数据
				for(int i = 0; i<length;i++){
					String tempUserId = list.get(i).getUserId()+"_"+list.get(i).getMeetingId();
					if(tempUserId.equals(userId)){
						//加限制条件
						if (i+1-num <= 65535) {
							nRow = sheet.createRow(i+1-num);
							for(int j = 0; j<head.length; j++){
								HSSFCell nCell = nRow.createCell(j);
								switch (j) {
								case 0:
									nCell.setCellValue(list.get(i).getTimeStamps());
									break;
								case 1:
									nCell.setCellValue(list.get(i).getNetworkType());
									break;
								case 2:
									nCell.setCellValue(list.get(i).getDirectionType());
									break;
								case 3:
									nCell.setCellValue(list.get(i).getMicId());
									break;
								case 4:
									nCell.setCellValue(list.get(i).getUserIp());
									break;
								case 5:
									nCell.setCellValue(list.get(i).getRelayIp());
									break;
								case 6:
									nCell.setCellValue(list.get(i).getBandwidth());
									break;
								case 7:
									nCell.setCellValue(list.get(i).getTraffic());
									break;
								default:
									break;
								}
							}
						}
						
					}else {
						userId = tempUserId;
						num = i;
						sheet = wb.createSheet(userId);
						sheet.setDefaultColumnWidth(17);
						//添加表头
						nRow = sheet.createRow(0);
						for (int k = 0; k<head.length;k++) {
							HSSFCell nCell = nRow.createCell(k);
							nCell.setCellValue(head[k]);
						}
						if (i+1-num <= 65535) {
							nRow = sheet.createRow(i+1-num);
							for(int j = 0; j<head.length; j++){
								HSSFCell nCell = nRow.createCell(j);
								switch (j) {
								case 0:
									nCell.setCellValue(list.get(i).getTimeStamps());
									break;
								case 1:
									nCell.setCellValue(list.get(i).getNetworkType());
									break;
								case 2:
									nCell.setCellValue(list.get(i).getDirectionType());
									break;
								case 3:
									nCell.setCellValue(list.get(i).getMicId());
									break;
								case 4:
									nCell.setCellValue(list.get(i).getUserIp());
									break;
								case 5:
									nCell.setCellValue(list.get(i).getRelayIp());
									break;
								case 6:
									nCell.setCellValue(list.get(i).getBandwidth());
									break;
								case 7:
									nCell.setCellValue(list.get(i).getTraffic());
									break;
								default:
									break;
								}
							}
						}
					}
				}
			}
		}else {
			sheet = wb.createSheet();
			HSSFRow nRow = sheet.createRow(0);
			HSSFCell nCell = nRow.createCell(0);
			nCell.setCellValue("所查询会议无相关数据！");
		}
	
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"); 
		String formatTime = df.format(time*1000);
		//formatTime = formatTime.replace(':', '_');
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName = sf.format(Calendar.getInstance().getTime()) + formatTime.split(" ")[0] + "-"+ formatTime.split(" ")[1];
		String path = request.getServletContext().getRealPath("/");
		path = getFilePath(path);
		
		FileOutputStream fileOut = null;
		
		try {
			if (path != null) {
				fileOut = new FileOutputStream(path + fileName + ".xls");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (fileOut != null) {
			wb.write(fileOut);
			//JsonObject jsonObject = new JsonObject();
			//jsonObject.addProperty("url", df.format(time)+ ".xls");
			fileOut.close();
			return fileName + ".xls";
		}else {
			//fileOut.close();
			return null;
		}
		
		
//		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 生成流对象
//		wb.write(byteArrayOutputStream); // 将excel写入流
//
//		String fileName = formatTime.split(" ")[0] + "_"+ formatTime.split(" ")[1];
//		
//		// 工具类，封装弹出下载框：
//		String outFile = fileName + ".xls";
//
//		download(byteArrayOutputStream, response, outFile);
//		
//		return null;
	}
	
	private void download(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response, String returnName) throws IOException{
		response.setContentType("application/octet-stream;charset=utf-8");
		returnName = response.encodeURL(new String(returnName.getBytes(),"iso8859-1"));			//保存的文件名,必须和页面编码一致,否则乱码
		response.addHeader("Content-Disposition",   "attachment;filename=" + returnName);  
		response.setContentLength(byteArrayOutputStream.size());
		
		ServletOutputStream outputstream = response.getOutputStream();	//取得输出流
		byteArrayOutputStream.writeTo(outputstream);					//写到输出流
		byteArrayOutputStream.close();									//关闭
		outputstream.flush();											//刷数据
	}
	
	private String getFilePath(String webPath){
		if (!webPath.equals("") && webPath != null) {
			int index = webPath.lastIndexOf("meetingMonitorServer");
			webPath = webPath.substring(0, index);
			webPath += "webShow\\";
			return webPath;
		}
		return null;
	}
}
