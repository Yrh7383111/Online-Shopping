package Back_end.DAOImpl;


import Back_end.DAO.UserDAO;
import Back_end.DTO.Address;
import Back_end.DTO.Cart;
import Back_end.DTO.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO
{
    // Private
    @Autowired
    private SessionFactory sessionFactory;


    // Public
    // Add a new user - Junit test
    @Override
    public boolean addUserTest(User user)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.persist(user);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Add a new user - Implementation
    @Override
    public void addUser(User user)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.persist(user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Add a new address - Junit test
    @Override
    public boolean addAddressTest(Address address)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.persist(address);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Add a new address - Implementation
    @Override
    public void addAddress(Address address)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.persist(address);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Add a new cart - Junit test
    @Override
    public boolean addCartTest(Cart cart)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.persist(cart);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Add a new cart - Implementation
    @Override
    public void addCart(Cart cart)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.persist(cart);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}