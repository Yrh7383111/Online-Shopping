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
            if (result.equals("added"))
            {
                modelAndView.addObject("message", "Cart line added successfully...");
            }
            else if (result.equals("updated"))
            {
                modelAndView.addObject("message", "Cart line updated successfully...");
            }
            else if (result.equals("deleted"))
            {
                modelAndView.addObject("message", "Cart line deleted successfully...");
            }
            else if (result.equals("unavailable"))
            {
                modelAndView.addObject("message", "Product quantity is  unavailable...");
            }
            else {
                modelAndView.addObject("message", "Something went wrong...");
            }
        }

        return modelAndView;
    }

    @GetMapping("/add/{productId}/cartlines")
    public String addCart(@PathVariable int productId)
    {
        String result = cartService.addCartLine(productId);

        return "redirect:/cart/show?" + result;
    }

    @GetMapping("/update/{cartLineId}/cartlines")
    public String updateCart(@PathVariable int cartLineId, @RequestParam int count)
    {
        String result = cartService.updateCartLine(cartLineId, count);

        return "redirect:/cart/show?" + result;
    }

    @GetMapping("/delete/{cartLineId}/cartlines")
    public String deleteCart(@PathVariable int cartLineId)
    {
        String result = cartService.deleteCartLine(cartLineId);

        return "redirect:/cart/show?" + result;
    }
}