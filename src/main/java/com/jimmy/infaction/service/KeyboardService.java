package com.jimmy.infaction.service;

import com.jimmy.infaction.pojo.Keyboard;
import org.springframework.stereotype.Service;

/**
 * @author jimmy on 2019/9/25 21:16
 */
@Service
public interface KeyboardService {
	/**
	 * @Author jimmy on 21:17 2019/9/25
	 * @Description //TODO
	 * @Param [keyboard]
	 * @return void
	 **/
	void insert(Keyboard keyboard);
}
