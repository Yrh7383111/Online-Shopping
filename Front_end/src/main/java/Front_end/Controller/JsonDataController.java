package Front_end.Controller;

import java.util.List;

import Back_end.DAO.ProductDAO;
import Back_end.DTO.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/json/data")
public class JsonDataController
{
	// Private
	@Autowired
	private ProductDAO productDAO;
	

	// Public
	@GetMapping("/all/products")
	@ResponseBody
	public List<Product> getAllProducts()
	{
		List<Product> products = productDAO.listActiveProducts();

		return products;
	}
	
	@GetMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable int id)
	{
		List<Product> products = productDAO.listActiveProductsByCategory(id);

		return products;
	}
}