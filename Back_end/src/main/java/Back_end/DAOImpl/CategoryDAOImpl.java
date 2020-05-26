package Back_end.DAOImpl;

import Back_end.DAO.CategoryDAO;
import Back_end.DTO.Category;

import java.util.ArrayList;
import java.util.List;



public class CategoryDAOImpl implements CategoryDAO
{
	// Private
	private static List<Category> categories = new ArrayList<>();
	
	
	static
	{
		Category category = new Category();
		
		// First category
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is some description for television!");
		category.setImageURL("CAT_1.png");
		
		categories.add(category);


		// Second category
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is some description for mobile!");
		category.setImageURL("CAT_2.png");

		categories.add(category);
		
		
		// Third category
		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is some description for laptop!");
		category.setImageURL("CAT_3.png");
		
		categories.add(category);
	}
	
	
	
	@Override
	public List<Category> list()
	{
		return categories;
	}
}