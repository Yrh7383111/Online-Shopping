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
    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;
    private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);


    // Public
    @Autowired
    public ManagementController(HttpServletRequest httpServletRequest, CategoryDAO categoryDAO, ProductDAO productDAO)
    {
        this.httpServletRequest = httpServletRequest;
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    // Return a list categories
    @ModelAttribute("categories")
    public List<Category> getCategories()
    {
        return categoryDAO.list();
    }

    // Show the Product Management page
    @GetMapping(value = "/products")
    public ModelAndView showProducts(@RequestParam(name="operation", required=false) String operation)
    {
        ModelAndView modelAndView = new ModelAndView("page");
        Product product = new Product();
        Category category = new Category();


        product.setSupplierId(1);                                       // Set the supplier to admin
        product.setActive(true);                                        // Activate the product

        modelAndView.addObject("title", "Management");
        modelAndView.addObject("userClickManageProducts", true);
        modelAndView.addObject("product", product);
        modelAndView.addObject("category", category);

        // Display the message if the product is submitted successfully
        if (operation != null)
        {
            if (operation.equals("productAdded"))                       // If the operation is to add a new product
            {
                modelAndView.addObject("message", "Product added successfully...");
            }
            else if (operation.equals("productUpdated"))                // If the operation is to update a new product
            {
                modelAndView.addObject("message", "Product updated successfully...");
            }
            else if (operation.equals("productDeleted"))                // If the operation is to delete a new product
            {
                modelAndView.addObject("message", "Product deleted successfully...");
            }
            else if (operation.equals("category"))                      // If the operation is to add a new category
            {
                modelAndView.addObject("message", "Category submitted successfully...");
            }
        }

        return modelAndView;
    }

    // Show the page to edit an existing product
    @GetMapping("products/{id}/edit")
    public ModelAndView editProduct(@PathVariable int id)
    {
        ModelAndView modelAndView = new ModelAndView("page");
        Product product = productDAO.get(id);
        Category category = new Category();


        modelAndView.addObject("title","Management");
        modelAndView.addObject("userClickManageProducts",true);
        modelAndView.addObject("product", product);
        modelAndView.addObject("category", category);

        return modelAndView;
    }

    // Add a new product
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
            model.addAttribute("title", "Management");
            model.addAttribute("userClickManageProducts",true);
            model.addAttribute("message", "Error occurred when adding the product...");

            return "page";
        }
        // Else
        // Add a new product (except the image filed)
        productDAO.add(product);                                        // If the product does not exist in the database

        logger.info(product.toString());                                // Log the info the the console

        return "redirect:/manage/products?operation=productAdded";      // Redirect to to the Product Management page with product operation
    }

    // Update a product
    @PostMapping(value = "/products/{id}")
    public String updateProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult, Model model)
    {
        ProductValidator productValidator = new ProductValidator();


        // Call the user defined validate method
        if (!Objects.equals(product.getFile().getOriginalFilename(), ""))
        {
            productValidator.validate(product, bindingResult);
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
        productDAO.update(product);

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

        return "redirect:/manage/products?operation=productUpdated";    // Redirect to to the Product Management page with product operation
    }

    // Activate an existing product
    @PostMapping(value = "/activate/products/{id}")
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
    @PostMapping(value = "/categories")
    public String addCategory(@ModelAttribute("category") Category category)
    {
        categoryDAO.add(category);

        return "redirect:/manage/products?operation=category";      // Redirect to to the Product Management page with category operation
    }

    // Delete a product
    @GetMapping(value = "/products/{id}")
    public String deleteProduct(@PathVariable int id)
    {
        Product product = productDAO.get(id);

        productDAO.delete(product);

        return "redirect:/manage/products?operation=productDeleted";    // Redirect to to the Product Management page with product operation
    }
}