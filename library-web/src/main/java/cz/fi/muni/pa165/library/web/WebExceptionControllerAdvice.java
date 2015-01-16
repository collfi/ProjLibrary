package cz.fi.muni.pa165.library.web;

/**
 * Exception handler in case of some unexpected exception
 *
 * @author xpylypen
 */
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice("cz.fi.muni.pa165.library.web")
public class WebExceptionControllerAdvice {

    private static final Logger log = Logger.getLogger( WebExceptionControllerAdvice.class.getName() );

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e) throws Exception {


        ModelAndView mav = new ModelAndView("exception");
        mav.addObject("name", e.getClass().getSimpleName());
        mav.addObject("message", e.getMessage());

        log.log( Level.SEVERE, e.toString(), e);
        return mav;
    }
}