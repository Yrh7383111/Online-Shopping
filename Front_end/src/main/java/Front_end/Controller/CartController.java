package Front_end.Controller;


import Back_end.DTO.CartLine;
import Front_end.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController
{
    // Private
    @Autowired
    private CartService cartService;


    // Public
    @GetMapping("/show")
    public ModelAndView showCart()
    {
        ModelAndView modelAndView = new ModelAndView("page");
        List<CartLine> cartLines = cartService.listCartLines();

        modelAndView.addObject("title", "Shopping Cart");
        modelAndView.addObject("userClickCart", true);
        modelAndView.addObject("cartLines", cartLines);

        return modelAndView;
    }
}