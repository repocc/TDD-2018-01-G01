package main.java.tdd.tp.controller;

import java.util.Map;

import clojure.lang.PersistentArrayMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import app.RulesValidatorApp;

@Controller
public class DataController {
	
	RulesValidatorApp rt;
	
	@RequestMapping(value = "/process-data", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getResponse(@RequestBody String newData) {
		 rt = RulesValidatorApp.getInstance();
		 return rt.processData(newData);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/get-state", method = RequestMethod.GET)
	@ResponseBody
	public String getState() {
		 rt = RulesValidatorApp.getInstance();
		 return rt.getState();
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/add-rules", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String defineRules(@RequestBody String newData) {
        rt = RulesValidatorApp.getInstance();
        System.out.println(newData);
        return rt.addRules(newData);
    }


}
