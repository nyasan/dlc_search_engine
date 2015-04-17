/**
 * @author mkyong
 * Visit http://www.mkyong.com
 */
package dlc.hello;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	private static final Logger logger = Logger.getLogger(WelcomeController.class);

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView welcome(@PathVariable("name") String name) {

		logger.debug(String.format("welcome() - name {%s}", name));

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addObject("name", name);
		
		return model;

	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView hello() {

		logger.debug("hello()");

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		
		return model;

	}

}
