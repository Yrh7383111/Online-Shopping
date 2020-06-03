package Front_end.Controller;


import Back_end.DAO.CategoryDAO;
import Back_end.DAO.ProductDAO;
import Back_end.DTO.Category;
import Back_end.DTO.Product;
import Front_end.Exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class PageController
{
    // Private
    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ProductDAO productDAO;

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);


    // Public

    // Home page
    @GetMapping(value = {"/", "/home", "/index"})
    public ModelAndView index()
    {
        ModelAndView modelAndView = new ModelAndView("page");
        List<Category> categories = categoryDAO.list();

        modelAndView.addObject("title","Home");
        modelAndView.addObject("userClickHome",true);
        modelAndView.addObject("categories", categories);

        logger.info("Inside PageController index method - INFO");
        logger.debug("Inside PageController index method - DEBUG");

        return modelAndView;
    }

    // About page
    @GetMapping(value = "/about")
    public ModelAndView about()
    {
        ModelAndView modelAndView = new ModelAndView("page");

        modelAndView.addObject("title","About Us");
        modelAndView.addObject("userClickAbout",true);

        return modelAndView;
    }

    // Contact page
    @GetMapping(value = "/contact")
    public ModelAndView contact()
    {
        ModelAndView modelAndView = new ModelAndView("page");

        modelAndView.addObject("title","Contact Us");
        modelAndView.addObject("userClickContact",true);

        return modelAndView;
    }

    // Show all products
    @GetMapping(value = "/show/all/products")
    public ModelAndView showAllProducts()
    {
        ModelAndView modelAndView = new ModelAndView("page");
        List<Category> categories = categoryDAO.list();

        modelAndView.addObject("title","All Products");
        modelAndView.addObject("userClickAllProducts",true);
        modelAndView.addObject("categories", categories);

        return modelAndView;
    }

    // Show products based on id
    @GetMapping(value = "/show/category/{id}/products")
    public ModelAndView showCategoryProducts(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView("page");
        List<Category> categories = categoryDAO.list();
        Category category = categoryDAO.get(id);
        String name = category.getName();

        modelAndView.addObject("title", name);
        modelAndView.addObject("userClickCategoryProducts",true);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("category", category);

        return modelAndView;
    }

    // Show a single product
    @GetMapping(value = "/show/{id}/product")
    public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException
    {
        ModelAndView modelAndView = new ModelAndView("page");
        Product product = productDAO.get(id);
        if (product == null)
            throw new ProductNotFoundException();
        // Else
        String name = product.getName();
        int views = product.getViews();

        product.setViews(views + 1);
        productDAO.update(product);

        modelAndView.addObject("title", name);
        modelAndView.addObject("userClickShowProduct", true);
        modelAndView.addObject("product", product);
        
        return modelAndView;
    }
}