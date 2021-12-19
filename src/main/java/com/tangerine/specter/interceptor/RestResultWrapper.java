package com.tangerine.specter.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
// 对响应报文统一处理，对bean内容进行加密
@Component
//声明该类要处理的包路径
@ControllerAdvice(annotations = RestController.class)
public class RestResultWrapper implements ResponseBodyAdvice<Object> {

	/**
	 * 选择哪些类，或哪些方法需要走beforeBodyWrite
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		log.info("supports======");
		log.info(returnType.toString());
		log.info(JSON.toJSONString(converterType));
		return true;
	}

	/**
	 * 对response处理的具体方法
	 * 从arg0中可以获取方法名和类名
	 * arg0.getMethod().getDeclaringClass().getName()为获取方法名
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		log.info("beforeBodyWrite======");
		log.info(JSON.toJSONString(body));
		log.info(returnType.toString());
		log.info(JSON.toJSONString(selectedContentType));
		log.info(JSON.toJSONString(selectedConverterType));
		log.info(JSON.toJSONString(request));
		log.info(response.toString());
		return null;
	}
}
