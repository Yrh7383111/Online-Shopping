package Front_end.Controller;


import Back_end.DAO.CategoryDAO;
import Back_end.DAO.ProductDAO;
import Back_end.DTO.Category;
import Back_end.DTO.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation)
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
    public String submitProduct(@ModelAttribute("product") Product product)
    {
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