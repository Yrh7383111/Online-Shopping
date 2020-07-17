package Back_end.DAOImpl;


import Back_end.DAO.UserDAO;
import Back_end.DTO.Address;
import Back_end.DTO.Cart;
import Back_end.DTO.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO
{
    // Private
    private final SessionFactory sessionFactory;


    // Public
    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    // Retrieve a single user based on userId
    @Override
    public User getUser(int userId)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            User user = session.get(User.class, userId);

            return user;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByEmail(String email)
    {
        String selectUserByEmail = "FROM User WHERE email =:email";

       try
       {
           Session session = sessionFactory.getCurrentSession();
           Query<User> query = session.createQuery(selectUserByEmail);

           query.setParameter("email", email);
           User user = query.getSingleResult();

           return user;
       }
       catch (Exception e)
       {
//           e.printStackTrace();
       }

       return null;
    }

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

    // Update an existing user - Junit test
    @Override
    public boolean updateUserTest(User user)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(user);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Update an existing user - Implementation
    @Override
    public void updateUser(User user)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Delete an existing user - Junit test
    @Override
    public boolean deleteUserTest(User user)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(user);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Delete an existing user - Implementation
    @Override
    public void deleteUser(User user)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Retrieve a single address based on addressId
    @Override
    public Address getAddress(int addressId)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Address address = session.get(Address.class, addressId);

            return address;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
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

    // Update an existing address - Junit test
    @Override
    public boolean updateAddressTest(Address address)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(address);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Update an existing address - Implementation
    @Override
    public void updateAddress(Address address)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(address);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Delete an existing address - Junit test
    @Override
    public boolean deleteAddressTest(Address address)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(address);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Delete an existing address - Implementation
    @Override
    public void deleteAddress(Address address)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(address);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Retrieve a list of addresses based on specific user
    @Override
    public List<Address> listAddresses(int userId)
    {
        Session session = sessionFactory.getCurrentSession();
        String selectAddresses = "FROM Address WHERE user.id = :userId";
        Query<Address> query = session.createQuery(selectAddresses);

        query.setParameter("userId", userId);

        List<Address> addresses = query.getResultList();

        return addresses;
    }

    @Override
    public List<Address> listBillingAddresses(int userId)
    {
        Session session = sessionFactory.getCurrentSession();
        String selectBillingAddresses = "FROM Address WHERE user.id = :userId AND billing = :billing";
        Query<Address> query = session.createQuery(selectBillingAddresses);

        query.setParameter("userId", userId);
        query.setParameter("billing", true);

        List<Address> addresses = query.getResultList();

        return addresses;
    }

    @Override
    public List<Address> listShippingAddresses(int userId)
    {
        Session session = sessionFactory.getCurrentSession();
        String selectShippingAddresses = "FROM Address WHERE user.id = :userId AND shipping = :shipping ORDER BY id DESC";
        Query<Address> query = session.createQuery(selectShippingAddresses);

        query.setParameter("userId", userId);
        query.setParameter("shipping", true);

        List<Address> addresses = query.getResultList();

        return addresses;
    }

    // Retrieve a single cart based on cartId
    @Override
    public Cart getCart(int cartId)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Cart cart = session.get(Cart.class, cartId);

            return cart;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    // Update an existing cart - Junit test
    @Override
    public boolean updateCartTest(Cart cart)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(cart);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Update an existing cart - Implementation
    @Override
    public void updateCart(Cart cart)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(cart);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Delete an existing cart - Junit test
    @Override
    public boolean deleteCartTest(Cart cart)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(cart);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Delete an existing cart - Implementation
    @Override
    public void deleteCart(Cart cart)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(cart);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}