package Front_end.Controller;


import Back_end.DTO.CartLine;
import Front_end.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController
{
    // Private
    private final CartService cartService;


    // Public
    @Autowired
    public CartController(CartService cartService)
    {
        this.cartService = cartService;
    }

    @GetMapping("/show")
    public ModelAndView showCart(@RequestParam(name = "result", required = false) String result)
    {
        ModelAndView modelAndView = new ModelAndView("page");
        List<CartLine> cartLines = cartService.listCartLines();

        modelAndView.addObject("title", "Shopping Cart");
        modelAndView.addObject("userClickCart", true);
        modelAndView.addObject("cartLines", cartLines);

        if (result != null)
        {
            if (result.equals("updated"))
            {
                modelAndView.addObject("message", "Cart line updated successfully...");
            }
            else if (result.equals("deleted"))
            {
                modelAndView.addObject("message", "Cart line deleted successfully...");
            }
            else {
                modelAndView.addObject("message", "Something went wrong...");
            }
        }

        return modelAndView;
    }

    @GetMapping("/update/{id}/cartlines")
    public String updateCart(@PathVariable int id, @RequestParam int count)
    {
        String result = cartService.updateCartLine(id, count);

        return "redirect:/cart/show?" + result;
    }

    @GetMapping("/delete/{id}/cartlines")
    public String deleteCart(@PathVariable int id)
    {
        String result = cartService.deleteCartLine(id);

        return "redirect:/cart/show?" + result;
    }
}