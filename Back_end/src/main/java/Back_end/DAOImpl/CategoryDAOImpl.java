package Back_end.DAOImpl;

import Back_end.DAO.CategoryDAO;
import Back_end.DTO.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;



@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO
{
	// Private
	private List<Category> categories;


	// Public
	public CategoryDAOImpl()
	{
		categories = new ArrayList<Category>();


		Category categoryOne = new Category(1, "Phone", "Phone", "phone.png", true);
		Category categoryTwo = new Category(2, "Tablet", "Tablet", "Tablet.png", true);
		Category categoryThree = new Category(3, "Computer", "Computer", "Computer.png", true);
		Category categoryFour = new Category(4, "Television", "Television", "Television.png", true);
		Category categoryFive = new Category(5, "Printer", "Printer", "Printer.png", true);
		Category categorySix = new Category(6, "Camera", "Camera", "Camera.png", true);

		categories.add(categoryOne);
		categories.add(categoryTwo);
		categories.add(categoryThree);
		categories.add(categoryFour);
		categories.add(categoryFive);
		categories.add(categorySix);
	}

	// Retrieve a list of categories
	@Override
	public List<Category> list()
	{
		return categories;
	}

	// Retrieve a single category based on id
	@Override
	public Category get(int id)
	{
		for(Category category : categories)
		{
			if (category.getId() == id)
				return category;
		}
		return null;
	}
}