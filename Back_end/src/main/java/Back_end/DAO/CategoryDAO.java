package Back_end.DAO;

import Back_end.DTO.Category;

import java.util.List;


public interface CategoryDAO
{
	// Retrieve a single category based on categoryId
	Category get(int categoryId);

	// Retrieve a list of categories
	List<Category> list();

	// Add a new category - Junit test
	boolean addTest(Category category);

	// Add a new category - Implementation
	void add(Category category);

	// Update an existing category - Junit test
	boolean updateTest(Category category);

	// Update an existing category - Implementation
	void update(Category category);

	// Delete an existing category - Junit test
	boolean deleteTest(Category category);

	// Delete an existing category - Implementation
	void delete(Category category);
}