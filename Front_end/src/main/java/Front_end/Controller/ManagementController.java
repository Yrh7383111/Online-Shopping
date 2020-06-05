package Front_end.Controller;


import Back_end.DAO.CategoryDAO;
import Back_end.DTO.Category;
import Back_end.DTO.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/manage")
public class ManagementController
{
    // Private
    @Autowired
    private CategoryDAO categoryDAO;


    // Public
    @GetMapping(value = "/products")
    public ModelAndView showManageProducts()
    {
        ModelAndView modelAndView = new ModelAndView("page");
        Product product = new Product();

        product.setSupplierId(1);
        product.setActive(true);

        modelAndView.addObject("title", "Manage Products");
        modelAndView.addObject("userClickManageProducts", true);
        modelAndView.addObject("product", product);

        return modelAndView;
    }

    @ModelAttribute("categories")
    public List<Category> getCategories()
    {
        return categoryDAO.list();
    }
}