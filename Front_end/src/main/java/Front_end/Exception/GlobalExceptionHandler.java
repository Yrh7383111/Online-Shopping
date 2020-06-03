package Front_end.Exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;


@ControllerAdvice
public class GlobalExceptionHandler
{
    // Handle URL that is not defined
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFoundException() 
    {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorTitle", "The page is not constructed...");
        modelAndView.addObject("errorDescription", "The page you are looking for is not available at the moment...");
        modelAndView.addObject("title", "404 Error");

        return modelAndView;
    }

    // Handle product id that is not found
    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handlerProductNotFoundException() 
    {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorTitle", "Product not available...");
        modelAndView.addObject("errorDescription", "The product you are looking for is not available at the moment...");
        modelAndView.addObject("title", "Product Unavailable");

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(Exception e) 
    {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorTitle", "Please contact the author...");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        e.printStackTrace(printWriter);

        modelAndView.addObject("errorDescription", stringWriter.toString());
        modelAndView.addObject("title", "Error");

        return modelAndView;
    }
}