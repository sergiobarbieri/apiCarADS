package br.com.drummond.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CarController {
	
	@RequestMapping(value = "/carView", method = RequestMethod.GET)
	public String getCarView() {
		     
		   return "carView";
	}
	
	@RequestMapping(value = "/carView2", method = RequestMethod.GET)
	public String getCarView2() {
		     
		   return "carView2";
	}
	
	@RequestMapping(value = "/carView3", method = RequestMethod.GET)
	public String getCarView3() {
		     
		   return "carView3";
	}

}
