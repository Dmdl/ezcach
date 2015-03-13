package com.rcapl.ezcash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ResponseController {

	@RequestMapping(value = "/response", method = RequestMethod.POST)
	public String response() {
		return "pay";
	}
}
