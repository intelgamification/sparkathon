package controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import test.City;

@RestController  
public class TestController {
	
	/**
	 * This method is returning the Json array as a response. 
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody List<City> test(){
		
		List<City> cities = new ArrayList<City>();
		
		City c1 = new City();
		c1.setId(1);
		c1.setName("Pune");
		
		cities.add(c1);
		
		City c2 = new City();
		c2.setId(1);
		c2.setName("Pune2");
		
		cities.add(c2);
		
		return cities;
	}
	
}
