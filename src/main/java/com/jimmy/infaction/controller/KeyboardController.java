package com.jimmy.infaction.controller;

import com.jimmy.infaction.pojo.Keyboard;
import com.jimmy.infaction.service.KeyboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jimmy on 2019/9/25 20:38
 */
@Service
@RequestMapping("/keyboard")
public class KeyboardController {
	private static Logger log = LoggerFactory.getLogger(KeyboardController.class);
	@Autowired
	private KeyboardService keyboardService;
	@RequestMapping(value = "/record", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map>KeyboardInfo (Keyboard keyboard){
		Map<String, Object> res = new HashMap<String, Object>();
		try{
			keyboardService.insert(keyboard);
			res.put("succeed",false);
			return new ResponseEntity<Map>(res,HttpStatus.OK);
		}catch (Throwable  t) {
			log.error(t.getMessage(),t);
			return new ResponseEntity<Map>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
