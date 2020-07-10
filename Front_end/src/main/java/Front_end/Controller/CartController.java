package Front_end.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/cart")
public class CartController
{
    @GetMapping("/show")
    public ModelAndView showCart()
    {
        ModelAndView modelAndView = new ModelAndView("page");
        
        modelAndView.addObject("title", "Shopping Cart");
        modelAndView.addObject("userClickCart", true);
        modelAndView.addObject("cartLines", null);

        return modelAndView;
    }
}