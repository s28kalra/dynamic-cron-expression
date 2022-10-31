package com.example.demo.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@Autowired
	private EventJobScheduling jobScheduling;
	
	@PostMapping("/addAnEvent")
	public String addAnEvent(@RequestBody Event event) {
		return jobScheduling.addAnEvent(event);
	}
	
	@GetMapping("/deleteAnEvent")
	public boolean deleteAnEvent(@RequestParam String jobId) {
		return jobScheduling.deleteAnEvent(jobId);
	}

}
