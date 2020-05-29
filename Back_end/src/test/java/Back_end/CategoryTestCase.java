package Back_end;

import Back_end.DAO.CategoryDAO;
import Back_end.DTO.Category;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;


public class CategoryTestCase
{
	// Private
	private static CategoryDAO categoryDAO;


	// Public
	@BeforeClass
	public static void init()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.scan("Back_end");
		context.refresh();

		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}
	
	
//	@Test
//	public void testAddCategory()
//	{
//		Category category = new Category();
//
//		category.setName("Laptop");
//		category.setDescription("Laptop");
//		category.setImageURL("Laptop.png");
//
//		assertTrue(categoryDAO.addTest(category));
//	}
	
//	@Test
//	public void testGetCategory()
//	{
//		Category category = new Category();
//
//		category = categoryDAO.get(1);
//
//		assertEquals("TV",category.getName());
//	}
	
//	@Test
//	public void testUpdateCategory()
//	{
//		Category category = new Category();
//
//		category = categoryDAO.get(4);
//
//		category.setName("Laptop");
//
//		assertTrue(categoryDAO.updateTest(category));
//		assertEquals("Laptop", category.getName());
//	}

//	@Test
//	public void testDeleteCategory()
//	{
//		Category category = new Category();
//
//		category = categoryDAO.get(2);
//
//		assertTrue(categoryDAO.deleteTest(category));
//	}

//	@Test
//	public void testListCategory()
//	{
//		assertEquals(6,categoryDAO.list().size());
//	}

	
	@Test
	public void testCRUDCategory()
	{
		Category category = new Category();

		// Add
		category.setName("Laptop");
		category.setDescription("Laptop");
		category.setImageURL("Laptop.png");

		assertTrue(categoryDAO.addTest(category));


		// Get
		category = categoryDAO.get(1);

		assertEquals("TV",category.getName());


		// Update
		category = categoryDAO.get(4);

		category.setName("Laptop");

		assertTrue(categoryDAO.updateTest(category));
		assertEquals("Laptop", category.getName());
		
		
		// Delete
		category = categoryDAO.get(2);

		assertTrue(categoryDAO.deleteTest(category));
		
		
		// List of categories
		assertEquals(6,categoryDAO.list().size());
	}
}