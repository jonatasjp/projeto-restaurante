package proj.unipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LayoutController {
	
	@RequestMapping(method = RequestMethod.GET, value ="menu")
	public String menu(ModelMap map) {
		return "/menu";
	}
	@RequestMapping(method = RequestMethod.GET, value ="rodape")
	public String rodape(ModelMap map) {
		return "/rodape";
	}
}
