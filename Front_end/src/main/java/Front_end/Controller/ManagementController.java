package Front_end.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/manage")
public class ManagementController
{
    @GetMapping(value = "/products")
    public ModelAndView showManageProducts()
    {
        ModelAndView modelAndView = new ModelAndView("page");

        modelAndView.addObject("title", "Manage Products");
        modelAndView.addObject("userClickManageProducts", true);

        return modelAndView;
    }
}