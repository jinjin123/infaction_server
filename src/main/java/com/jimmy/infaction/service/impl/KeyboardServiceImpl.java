package com.jimmy.infaction.service.impl;

import com.jimmy.infaction.mapper.KeyboardMapper;
import com.jimmy.infaction.pojo.Keyboard;
import com.jimmy.infaction.service.KeyboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jimmy on 2019/9/25 21:16
 */
@Service
public class KeyboardServiceImpl implements KeyboardService {
	@Autowired
	KeyboardMapper keyboardMapper;
	/**
	 * @Author jimmy on 21:17 2019/9/25
	 * @Description //TODO
	 * @Param [keyboard]
	 * @return void
	 **/
	@Override
	public void insert(Keyboard keyboard) {
		keyboardMapper.insert(keyboard);
	}
}
