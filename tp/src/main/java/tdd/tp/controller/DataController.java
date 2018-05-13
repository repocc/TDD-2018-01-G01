package main.java.tdd.tp.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import app.RulesValidatorApp;

@Controller
public class DataController {
	
	@RequestMapping(value = "/process-data", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getResponse(@RequestBody Map<String, String> newData) {
		 RulesValidatorApp rt = RulesValidatorApp.getInstance();
		 return rt.processData(newData);
	}
}
