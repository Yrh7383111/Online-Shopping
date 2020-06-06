package Front_end.Controller;


import Back_end.DAO.CategoryDAO;
import Back_end.DAO.ProductDAO;
import Back_end.DTO.Category;
import Back_end.DTO.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/manage")
public class ManagementController
{
    // Private
    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ProductDAO productDAO;

    private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);


    // Public
    // Show the Product Management page
    @GetMapping(value = "/products")
    public ModelAndView showProducts(@RequestParam(name="operation", required=false) String operation)
    {
        ModelAndView modelAndView = new ModelAndView("page");
        Product product = new Product();

        product.setSupplierId(1);
        product.setActive(true);

        modelAndView.addObject("title", "Manage Products");
        modelAndView.addObject("userClickManageProducts", true);
        modelAndView.addObject("product", product);

        // Display the message if the product is submitted successfully
        if (operation != null)
        {
            if (operation.equals("product"))
            {
                modelAndView.addObject("message", "Product submitted successfully!");
            }
        }

        return modelAndView;
    }

    @PostMapping(value = "/products")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model)
    {
        // If there is an error occur when adding a product
        if (bindingResult.hasErrors())
        {
            model.addAttribute("title", "Manage Products");
            model.addAttribute("userClickManageProducts",true);
            model.addAttribute("message", "Error occurred when adding the product");

            return "page";
        }
        // Else
        productDAO.add(product);

        logger.info(product.toString());

        return "redirect:/manage/products?operation=product";
    }

    // Return a list categories
    @ModelAttribute("categories")
    public List<Category> getCategories()
    {
        return categoryDAO.list();
    }
}