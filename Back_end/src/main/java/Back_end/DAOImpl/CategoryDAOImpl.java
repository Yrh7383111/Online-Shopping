package Back_end.DAOImpl;

import java.util.List;

import Back_end.DAO.CategoryDAO;
import Back_end.DTO.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO
{
	// Private
	@Autowired
	private SessionFactory sessionFactory;


	// Retrieve a single category based on id
	@Override
	public Category get(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		Category category = session.get(Category.class, id);

		return category;
	}

	// Retrieve a list of categories
	@Override
	public List<Category> list()
	{
		Session session = sessionFactory.getCurrentSession();
		String selectActiveCategory = "FROM Category WHERE active = :active";
		Query<Category> query = session.createQuery(selectActiveCategory);
				
		query.setParameter("active", true);

		List<Category> categories = query.getResultList();

		return categories;
	}

	// Add a new category - Junit test
	@Override
	public boolean addTest(Category category)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.persist(category);

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();

			return false;
		}
	}

	// Add a new category - Implementation
	@Override
	public void add(Category category)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.persist(category);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// Update an existing category - Junit test
	@Override
	public boolean updateTest(Category category)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.update(category);

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();

			return false;
		}
	}

	// Update an existing category - Implementation
	@Override
	public void update(Category category)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.update(category);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	@Override
	public boolean deleteTest(Category category)
	{
		// Deactivate the category
		category.setActive(false);

		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.update(category);

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public void delete(Category category)
	{
		// Deactivate the category
		category.setActive(false);

		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.update(category);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}