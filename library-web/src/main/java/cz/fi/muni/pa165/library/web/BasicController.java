package cz.fi.muni.pa165.library.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Controller
@RequestMapping("/")
public class BasicController {

    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public String index()
    {
        return "index";
    }

    @RequestMapping( value = "/login", method = RequestMethod.GET )
    public String login()
    {
        return "login";
    }

}
