package Front_end.Controller;


import Back_end.DAO.CategoryDAO;
import Back_end.DAO.ProductDAO;
import Back_end.DTO.Category;
import Back_end.DTO.Product;
import Front_end.Utility.FileUploadUtility;
import Front_end.Validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private HttpServletRequest httpServletRequest;

    private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);


    // Public
    // Show the Product Management page
    @GetMapping(value = "/products")
    public ModelAndView showProducts(@RequestParam(name="operation", required=false) String operation)
    {
        ModelAndView modelAndView = new ModelAndView("page");
        Product product = new Product();


        product.setSupplierId(1);                                       // Set the supplier to admin
        product.setActive(true);                                        // Activate the product

        modelAndView.addObject("title", "Manage Products");
        modelAndView.addObject("userClickManageProducts", true);
        modelAndView.addObject("product", product);

        // Display the message if the product is submitted successfully
        if (operation != null)
        {
            if (operation.equals("product"))                            // If the operation is to add a product
            {
                modelAndView.addObject("message", "Product submitted successfully!");
            }
        }

        return modelAndView;
    }

    @PostMapping(value = "/products")
    public String addProduct(@Valid @ModelAttribute("product") Product product,
                             BindingResult bindingResult, Model model)
    {
        ProductValidator productValidator = new ProductValidator();


        // Call the user defined validate method
        productValidator.validate(product, bindingResult);

        // If there is an error occur when adding a product
        if (bindingResult.hasErrors())
        {
            model.addAttribute("title", "Manage Products");
            model.addAttribute("userClickManageProducts",true);
            model.addAttribute("message", "Error occurred when adding the product");

            return "page";
        }
        // Else
        // Add a new product (except the image filed)
        if (product.getId() == 0)
        {
            productDAO.add(product);                                    // If the product does not exist in the database
        }
        else {
            productDAO.update(product);                                 // If the product exists in the database
        }

        // Attach the image file the product afterwards
        if (product.getFile() != null)                                  // If the product does not have a file, then upload it
        {
            try
            {
                FileUploadUtility.uploadFile(httpServletRequest, product.getFile(), product.getCode());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        logger.info(product.toString());                                // Log the info the the console

        return "redirect:/manage/products?operation=product";           // Redirect to to the Product Management page with product operation
    }

    // Return a list categories
    @ModelAttribute("categories")
    public List<Category> getCategories()
    {
        return categoryDAO.list();
    }
}