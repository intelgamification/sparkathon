package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("")
	public String loadWelcomepage(){
		//added a comment to check if git push is working - Shantanu
		boolean b =Boolean.TRUE;
		
		if(b) {
			throw new NullPointerException("Null encountered");
		} else {
			return "index";
		}
	}

}
