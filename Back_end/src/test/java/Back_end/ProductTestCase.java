package Back_end;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Back_end.DAO.ProductDAO;
import Back_end.DTO.Product;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class ProductTestCase
{
	// Private
	private static ProductDAO productDAO;


	// Public
	@BeforeClass
	public static void init()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.scan("Back_end");
		context.refresh();

		productDAO = (ProductDAO) context.getBean("productDAO");
	}
	
//	@Test
//	public void testCRUDProduct()
//	{
//		Product product = new Product();
//
//		// Add
//		product.setName("Huawei P30");
//		product.setBrand("huawei");
//		product.setDescription("Huawei");
//		product.setUnitPrice(25000);
//		product.setActive(true);
//		product.setCategoryId(3);
//		product.setSupplierId(1);
//
//		assertTrue(productDAO.addTest(product));
//
//
//		// Update
//		product = productDAO.get(5);
//		product.setName("Dell Latitude");
//		assertTrue(productDAO.updateTest(product));
//		assertEquals("Dell Latitude", product.getName());
//
//
//		// Delete
//		assertTrue(productDAO.deleteTest(product));
//
//
//		// List
//		assertEquals(6, productDAO.list().size());
//	}

	@Test
	public void testListActiveProducts()
	{
		assertEquals(5, productDAO.listActiveProducts().size());
	}

	@Test
	public void testListActiveProductsByCategory()
	{
		assertEquals(1, productDAO.listActiveProductsByCategory(1).size());

		assertEquals(4, productDAO.listActiveProductsByCategory(3).size());
	}

	@Test
	public void testListLatestActiveProduct()
	{
		assertEquals(5, productDAO.listLatestActiveProducts(5).size());
	}
}