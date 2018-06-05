package main.java.tdd.tp.controller;

import app.RulesValidatorApp;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
	@RequestMapping(value = "/get-state/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getState(@PathVariable("id") String idDashboard) {
		 rt = RulesValidatorApp.getInstance();
		 return rt.getState(idDashboard);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/get-signals/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getSignals(@PathVariable("id") String idDashboard) {
		rt = RulesValidatorApp.getInstance();
		return rt.getSignals(idDashboard);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/get-dashboards", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Set<String> getDashboardsIds() {
		rt = RulesValidatorApp.getInstance();
		return rt.getDashboardsIds();
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/add-rules/{id}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String defineRules(@RequestBody String newData, @PathVariable("id") String idDashboard) {
        rt = RulesValidatorApp.getInstance();
        return rt.addRules(newData, idDashboard);
    }

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/create-dashboard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void createDashboard(@RequestBody String idDashboard) {
		rt = RulesValidatorApp.getInstance();
		rt.addDashboard(idDashboard);
	}


}
