package com.group1.Georgies_Eats;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloWorldController {

	
	// From appliciation.properties
	@Value("${helloworld.message}")
	
	private String message;
	
	
	@GetMapping("/helloworld")
	public String HelloController(Model m) {
		m.addAttribute("message", message);
		return "helloworld";
	}
	
	@GetMapping("hello")
	public String HelloWithParam(@RequestParam(name = "name", required = true, 
		defaultValue = "springboot")String name, Model m) {
	
		m.addAttribute("message", name);
		return "helloworld";
	}
	
}
