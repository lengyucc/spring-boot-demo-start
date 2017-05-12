package com.antbean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antbean.configuration.NeoProperties;

@RestController
public class TestController {
	@Autowired
	private NeoProperties prop;

	@RequestMapping("/prop")
	public Object index() {
		return prop;
	}
}