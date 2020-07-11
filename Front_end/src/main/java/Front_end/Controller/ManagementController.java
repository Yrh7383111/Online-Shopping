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
import java.util.Objects;


@Controller
@RequestMapping("/manage")
public class ManagementController
{
    // Private
    private final HttpServletRequest httpServletRequest;
    private final CategoryDAO categoryDAO;
    private final ProductDAO productDAO;
    private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);


    // Public
    @Autowired
    public ManagementController(HttpServletRequest httpServletRequest, CategoryDAO categoryDAO, ProductDAO productDAO)
    {
        this.httpServletRequest = httpServletRequest;
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
    }

    // Show the Product Management page
    @GetMapping(value = "/products")
    public ModelAndView showProducts(@RequestParam(name="operation", required=false) String operation)
    {
        ModelAndView modelAndView = new ModelAndView("page");
        Product product = new Product();


        product.setSupplierId(1);                                       // Set the supplier to admin
        product.setActive(true);                                        // Activate the product

        modelAndView.addObject("title", "Management");
        modelAndView.addObject("userClickManageProducts", true);
        modelAndView.addObject("product", product);

        // Display the message if the product is submitted successfully
        if (operation != null)
        {
            if (operation.equals("product"))                            // If the operation is to add a new product
            {
                modelAndView.addObject("message", "Product submitted successfully...");
            }
            else if (operation.equals("category"))                      // If the operation is to add a new category
            {
                modelAndView.addObject("message", "Category submitted successfully...");
            }
        }

        return modelAndView;
    }

    // Show the page to edit an existing product
    @GetMapping("/{id}/products")
    public ModelAndView editProduct(@PathVariable int id)
    {
        ModelAndView modelAndView = new ModelAndView("page");
        Product product = productDAO.get(id);


        modelAndView.addObject("title","Management");
        modelAndView.addObject("userClickManageProducts",true);
        modelAndView.addObject("product", product);

        return modelAndView;
    }

    // Add/Update a new product
    @PostMapping(value = "/products")
    public String addProduct(@Valid @ModelAttribute("product") Product product,
                             BindingResult bindingResult, Model model)
    {
        ProductValidator productValidator = new ProductValidator();


        // Call the user defined validate method
        if (product.getId() == 0)                                       // If we are adding a new product, then we need to validate
        {
            productValidator.validate(product, bindingResult);
        }
        else {
            if (!Objects.equals(product.getFile().getOriginalFilename(), ""))
            {
                productValidator.validate(product, bindingResult);
            }
        }

        // If there is an error occur when adding a product
        if (bindingResult.hasErrors())
        {
            model.addAttribute("title", "Management");
            model.addAttribute("userClickManageProducts",true);
            model.addAttribute("message", "Error occurred when adding the product...");

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

    // Activate an existing product
    @PostMapping(value = "/activation/{id}/products")
    @ResponseBody
    public String activateProduct(@PathVariable int id)
    {
        Product product = productDAO.get(id);
        String name = product.getName();
        boolean isActive = product.isActive();


        product.setActive(!isActive);
        productDAO.update(product);

        return (isActive)? name + " has been deactivated successfully..." : name + " has been activated successfully...";
    }

    // Add a new category
    @PostMapping(value = "/category")
    public String addCategory(@ModelAttribute("category") Category category)
    {
        categoryDAO.add(category);

        return "redirect:/manage/products?operation=category";      // Redirect to to the Product Management page with category operation
    }

    // Return a list categories
    @ModelAttribute("categories")
    public List<Category> getCategories()
    {
        return categoryDAO.list();
    }

    // Return an empty category to fill out
    @ModelAttribute("category")
    public Category addCategory()
    {
        Category category = new Category();

        return category;
    }
}