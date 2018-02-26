package com.ohhoonim.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ohhoonim.common.util.Utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

@ControllerAdvice
@RestController
public class CommonExceptionHandler {
	private static final Logger LOGGER = Logger.getLogger(CommonExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = Exception.class)
	public Map<String, Object> handleException(Exception e, HttpServletRequest req) {
		e.printStackTrace();
		Map<String, Object> errorMap = new HashMap<String, Object>();
		errorMap.put("error", e.getMessage());
		errorMap.put("req", req.getParameterMap());
		return errorMap;
	}
	/*
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException2(Exception e, HttpServletRequest req) {
		ModelAndView model = new ModelAndView();
		model.addObject("errorMsg", e.getMessage());
		model.addObject("req", req.getParameterMap());
		model.setViewName("common/error");
		return model;
	}
	*/

}
