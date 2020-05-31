package Front_end;


import Back_end.DAO.CategoryDAO;
import Back_end.DTO.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class PageController
{
    // Private
    @Autowired
    private CategoryDAO categoryDAO;


    // Public
    @GetMapping(value = {"/", "/home", "/index"})
    public ModelAndView index()
    {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("title","Home");
        modelAndView.addObject("userClickHome",true);

        modelAndView.addObject("categories", categoryDAO.list());

        return modelAndView;
    }

    @GetMapping(value = "/about")
    public ModelAndView about()
    {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("title","About Us");
        modelAndView.addObject("userClickAbout",true);

        return modelAndView;
    }

    @GetMapping(value = "/contact")
    public ModelAndView contact()
    {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("title","Contact Us");
        modelAndView.addObject("userClickContact",true);

        return modelAndView;
    }

    @GetMapping(value = "/show/all/products")
    public ModelAndView showAllProducts()
    {
        ModelAndView modelAndView = new ModelAndView("page");


        modelAndView.addObject("title","All Products");
        modelAndView.addObject("userClickAllProducts",true);
        modelAndView.addObject("categories", categoryDAO.list());

        return modelAndView;
    }

    @GetMapping(value = "/show/category/{id}/products")
    public ModelAndView showCategoryProducts(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView("page");
        Category category = new Category();


        category = categoryDAO.get(id);

        modelAndView.addObject("title",category.getName());
        modelAndView.addObject("userClickCategoryProducts",true);
        modelAndView.addObject("categories", categoryDAO.list());
        modelAndView.addObject("category", category);

        return modelAndView;
    }
}