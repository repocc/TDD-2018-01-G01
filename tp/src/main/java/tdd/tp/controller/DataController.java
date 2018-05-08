package main.java.tdd.tp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import app.RulesValidatorApp;
import main.java.tdd.tp.inputdto.Data;

@Controller
public class DataController {
	
	@RequestMapping(value = "/process-data", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getResponse(@RequestBody Data newData) {
		 RulesValidatorApp rt = RulesValidatorApp.getInstance();
		 return rt.processData(newData.getKey(), newData.getValue());
	}
}
