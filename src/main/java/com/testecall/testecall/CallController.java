package com.testecall.testecall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CallController {

    @GetMapping("/call")
	public String call() {
		return "call";
	}

	@GetMapping("/")
	public String home() {
		return "call";
	}

}