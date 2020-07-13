package Back_end.DAOImpl;


import Back_end.DAO.CartLineDAO;
import Back_end.DTO.Cart;
import Back_end.DTO.CartLine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO
{
    // Private
    @Autowired
    private SessionFactory sessionFactory;


    // Public
    // Retrieve a single cart line based on id
    @Override
    public CartLine get(int cartLineId)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            CartLine cartLine = session.get(CartLine.class, cartLineId);

            return cartLine;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    // Retrieve a list of cart lines based on cartId
    @Override
    public List<CartLine> list(int cartId)
    {
        Session session = sessionFactory.getCurrentSession();
        String select = "FROM CartLine WHERE cartId = :cartId";
        Query<CartLine> query = session.createQuery(select);

        query.setParameter("cartId", cartId);

        List<CartLine> cartLines = query.getResultList();

        return cartLines;
    }

    // Add a new cart line - Junit test
    @Override
    public boolean addTest(CartLine cartLine)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.persist(cartLine);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Add a new cart line - Implementation
    @Override
    public void add(CartLine cartLine)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.persist(cartLine);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Update an existing cart line - Junit test
    @Override
    public boolean updateTest(CartLine cartLine)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(cartLine);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Update an existing cart line - Implementation
    @Override
    public void update(CartLine cartLine)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(cartLine);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Delete an existing cart line - Junit test
    @Override
    public boolean deleteTest(CartLine cartLine)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(cartLine);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Delete an existing cart line - Implementation
    @Override
    public void delete(CartLine cartLine)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(cartLine);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Retrieve the cart line based on cartId and productId
    @Override
    public CartLine getCartLineByCartAndProduct(int cartId, int productId)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String selectCartLineByCartAndProduct = "FROM CartLine WHERE cartId = :cartId AND product.id = :productId";
            Query<CartLine> query = session.createQuery(selectCartLineByCartAndProduct);

            query.setParameter("cartId", cartId);
            query.setParameter("productId", productId);

            CartLine cartLine = query.getSingleResult();

            return cartLine;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    // Retrieve only the available cart line
    @Override
    public List<CartLine> listAvailableCartLines(int cartId)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            String selectAvailableCartLines = "FROM CartLine WHERE cartId = :cartId AND available = :available";
            Query<CartLine> query = session.createQuery(selectAvailableCartLines);

            query.setParameter("cartId", cartId);
            query.setParameter("available", true);

            List<CartLine> cartLines = query.getResultList();

            return cartLines;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    // Update a new cart - Junit test
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

    // Update a new cart - Implementation
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
}