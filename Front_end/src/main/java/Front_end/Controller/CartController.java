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

    @GetMapping("/show/cartlines")
    public ModelAndView showCart(@RequestParam(name = "result", required = false) String result)
    {
        ModelAndView modelAndView = new ModelAndView("page");

        modelAndView.addObject("title", "Shopping Cart");
        modelAndView.addObject("userClickCart", true);

        if (result != null)
        {
            if (result.equals("added"))
            {
                modelAndView.addObject("message", "Cart line added successfully...");
                validateCartLine();
            }
            else if (result.equals("updated"))
            {
                modelAndView.addObject("message", "Cart line updated successfully...");
                validateCartLine();
            }
            else if (result.equals("deleted"))
            {
                modelAndView.addObject("message", "Cart line deleted successfully...");
                validateCartLine();
            }
            else if (result.equals("unavailable"))
            {
                modelAndView.addObject("message", "Product quantity is unavailable...");
                validateCartLine();
            }
            else if (result.equals("modified"))
            {
                modelAndView.addObject("message", "One or more cart lines modified successfully...");
            }
            else {
                modelAndView.addObject("message", "Something went wrong...");
            }
        }
        else {
            if (cartService.validateCartLine().equals("result=modified"))
                modelAndView.addObject("message", "One or more cart lines modified successfully...");
        }

        List<CartLine> cartLines = cartService.getAvailableCartLines();
        modelAndView.addObject("cartLines", cartLines);

        return modelAndView;
    }

    @GetMapping("/add/cartlines/{productId}")
    public String addCartLine(@PathVariable int productId)
    {
        String result = cartService.addCartLine(productId);

        return "redirect:/cart/show/cartlines?" + result;
    }

    @GetMapping("/update/cartlines/{cartLineId}")
    public String updateCartLine(@PathVariable int cartLineId, @RequestParam int count)
    {
        String result = cartService.updateCartLine(cartLineId, count);

        return "redirect:/cart/show/cartlines?" + result;
    }

    @GetMapping("/delete/cartlines/{cartLineId}")
    public String deleteCartLine(@PathVariable int cartLineId)
    {
        String result = cartService.deleteCartLine(cartLineId);

        return "redirect:/cart/show/cartlines?" + result;
    }

    @GetMapping("/validate/cartlines")
    public String validateCartLine()
    {
        String result = cartService.validateCartLine();
        
        
        if (result.equals("result=modified"))
        {
            return "redirect:/cart/show/cartlines?" + result;
        }
        else {
            return "redirect:/cart/checkout";
        }
    }
}