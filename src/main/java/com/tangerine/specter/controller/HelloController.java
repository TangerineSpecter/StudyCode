package com.tangerine.specter.controller;

import com.tangerine.specter.param.HelloParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping("test")
	public String test(HelloParam param) {
		return "welcome~" + param.getName();
	}
}
