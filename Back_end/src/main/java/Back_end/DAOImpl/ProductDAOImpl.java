package Back_end.DAOImpl;

import java.util.List;

import Back_end.DAO.ProductDAO;
import Back_end.DTO.Category;
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
		Query<Product> query = session.createQuery(queryString);
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
		Session session = sessionFactory.getCurrentSession();
		String selectActiveProducts = "FROM Product WHERE active = :active";
		Query<Product> query = session.createQuery(selectActiveProducts);

		query.setParameter("active", true);

		List<Product> products = query.getResultList();

		return products;
	}

	// List products based on categoryId
	@Override
	public List<Product> listActiveProductsByCategory(int categoryId)
	{
		Session session = sessionFactory.getCurrentSession();
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		Query<Product> query = session.createQuery(selectActiveProductsByCategory);

		query.setParameter("active", true);
		query.setParameter("categoryId", categoryId);

		List<Product> products = query.getResultList();

		return products;
	}

	// Retrieve the latest products
	@Override
	public List<Product> listLatestActiveProducts(int count)
	{
		Session session = sessionFactory.getCurrentSession();
		String selectLatestActiveProducts = "FROM Product WHERE active = :active ORDER BY id";
		Query<Product> query = session.createQuery(selectLatestActiveProducts);

		query.setParameter("active", true);
		query.setFirstResult(0);
		query.setMaxResults(count);

		List<Product> products = query.getResultList();

		return products;
	}
}