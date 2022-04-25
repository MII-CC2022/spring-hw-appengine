package edu.cc.paas.holamundo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HolaController {
    
    @GetMapping("/")
	public String index() {
	    
		return "index";
	}
    
    
    
}