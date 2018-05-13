package main.java.tdd.tp.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import app.RulesValidatorApp;

@Controller
public class DataController {
	
	RulesValidatorApp rt;
	
	@RequestMapping(value = "/process-data", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getResponse(@RequestBody Map<String, String> newData) {
		 rt = RulesValidatorApp.getInstance();
		 return rt.processData(newData);
	}
	
	@RequestMapping(value = "/get-state", method = RequestMethod.GET)
	@ResponseBody
	public String getState() {
		 rt = RulesValidatorApp.getInstance();
		 return rt.getState();
	}
}
