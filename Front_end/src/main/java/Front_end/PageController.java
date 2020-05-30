package Front_end;


import Back_end.DAO.CategoryDAO;
import Back_end.DTO.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title","Home");
        mv.addObject("userClickHome",true);

        mv.addObject("categories", categoryDAO.list());

        return mv;
    }

    @GetMapping(value = "/about")
    public ModelAndView about()
    {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title","About Us");
        mv.addObject("userClickAbout",true);

        return mv;
    }

    @GetMapping(value = "/contact")
    public ModelAndView contact()
    {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title","Contact Us");
        mv.addObject("userClickContact",true);

        return mv;
    }

    @GetMapping(value = "/show/all/products")
    public ModelAndView showAllProducts()
    {
        ModelAndView mv = new ModelAndView("page");


        mv.addObject("title","All Products");
        mv.addObject("userClickAllProducts",true);
        mv.addObject("categories", categoryDAO.list());

        return mv;
    }

    @GetMapping(value = "/show/category/{id}/products")
    public ModelAndView showCategoryProducts(@PathVariable("id") int id)
    {
        ModelAndView mv = new ModelAndView("page");
        Category category = new Category();


        category = categoryDAO.get(id);

        mv.addObject("title",category.getName());
        mv.addObject("userClickCategoryProducts",true);
        mv.addObject("categories", categoryDAO.list());
        mv.addObject("category", category);

        return mv;
    }
}