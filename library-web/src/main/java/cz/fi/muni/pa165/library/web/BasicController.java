package cz.fi.muni.pa165.library.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BasicController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {

        //model.addAttribute("message", "Dream team library!");
        return "index";

    }
}
