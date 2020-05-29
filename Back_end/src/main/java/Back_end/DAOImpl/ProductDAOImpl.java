package Back_end.DAOImpl;

import java.util.List;

import Back_end.DAO.ProductDAO;
import Back_end.DTO.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO
{
	// Private
	@Autowired
	private SessionFactory sessionFactory;


	// Public
	// Retrieve a single product based on id
	@Override
	public Product get(int productId)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Product product = session.get(Product.class, productId);

			return product;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	// Retrieve a list of products
	@Override
	public List<Product> list()
	{
		Session session = sessionFactory.getCurrentSession();
		String queryString = "FROM Product";
		Query<Product> query = session.createQuery(queryString, Product.class);
		List<Product> products = query.getResultList();

		return products;
	}

	// Add a new  - Junit test
	@Override
	public boolean addTest(Product product)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.persist(product);

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return false;
	}

	// Add a new product - Implementation
	@Override
	public void add(Product product)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.persist(product);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// Update an existing product - Junit test
	@Override
	public boolean updateTest(Product product)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.update(product);

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return false;
	}

	// Update an existing product - Implementation
	@Override
	public void update(Product product)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.update(product);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// Delete an existing product - Junit test
	@Override
	public boolean deleteTest(Product product)
	{
		try
		{
			// Deactivate the category
			product.setActive(false);

			Session session = sessionFactory.getCurrentSession();
			session.update(product);

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return false;
	}

	// Delete an existing product - Implementation
	@Override
	public void delete(Product product)
	{
		try
		{
			// Deactivate the category
			product.setActive(false);

			Session session = sessionFactory.getCurrentSession();
			session.update(product);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// Retrieve only the active products
	@Override
	public List<Product> listActiveProducts()
	{
		return null;
	}

	// List products based on categoryId
	@Override
	public List<Product> listActiveProductsByCategory(int categoryId)
	{
		return null;
	}

	// Retrieve the latest products
	@Override
	public List<Product> listLatestActiveProducts(int count)
	{
		return null;
	}
}