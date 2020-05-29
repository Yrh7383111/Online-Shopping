package Back_end.DAO;

import Back_end.DTO.Product;

import java.util.List;


public interface ProductDAO
{
	// Retrieve a single product based on id
	Product get(int productId);

	// Retrieve a list of products
	List<Product> list();

	// Add a new  - Junit test
	boolean addTest(Product product);

	// Add a new product - Implementation
	void add(Product product);

	// Update an existing product - Junit test
	boolean updateTest(Product product);
	
	// Update an existing product - Implementation
	void update(Product product);

	// Delete an existing product - Junit test
	boolean deleteTest(Product product);

	// Delete an existing product - Implementation
	void delete(Product product);

	// Retrieve only the active products
	List<Product> listActiveProducts();

	// List products based on categoryId
	List<Product> listActiveProductsByCategory(int categoryId);

	// Retrieve the latest products
	List<Product> listLatestActiveProducts(int count);
}