package cz.fi.muni.pa165.library.rest;

/**
 * Exception handler in case of some unexpected exception
 *
 * @author xpylypen
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice("cz.fi.muni.pa165.library.rest")
public class RestExceptionControllerAdvice {

    private static final Logger log = Logger.getLogger( RestExceptionControllerAdvice.class.getName() );

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String exception(Exception e, WebRequest request) {
        log.log(Level.SEVERE, e.toString(), e);
        return e.getClass().getSimpleName() + ": " + e.getMessage();
    }

}