package Back_end.DAOImpl;


import Back_end.DAO.OrderDAO;
import Back_end.DTO.OrderDetail;
import Back_end.DTO.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository("orderDAO")
@Transactional
public class OrderDAOImpl implements OrderDAO
{
    // Private
    private final SessionFactory sessionFactory;


    // Public
    @Autowired
    public OrderDAOImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    // Retrieve a order item based on orderItemId
    @Override
    public OrderItem getOrderItem(int orderItemId)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            OrderItem orderItem = session.get(OrderItem.class, orderItemId);

            return orderItem;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    // Add a new order item - Junit test
    @Override
    public boolean addOrderItemTest(OrderItem orderItem)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.persist(orderItem);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Add a new order item - Implementation
    @Override
    public void addOrderItem(OrderItem orderItem)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.persist(orderItem);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Update an existing order item - Junit test
    @Override
    public boolean updateOrderItemTest(OrderItem orderItem)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(orderItem);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Update an existing order item - Implementation
    @Override
    public void updateOrderItem(OrderItem orderItem)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(orderItem);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Delete an existing order item - Junit test
    @Override
    public boolean deleteOrderItemTest(OrderItem orderItem)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(orderItem);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Delete an existing order item - Implementation
    @Override
    public void deleteOrderItem(OrderItem orderItem)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(orderItem);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Retrieve a order detail based on orderDetailId
    @Override
    public OrderDetail getOrderDetail(int orderDetailId)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            OrderDetail orderDetail = session.get(OrderDetail.class, orderDetailId);

            return orderDetail;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    // Add a new order detail - Junit test
    @Override
    public boolean addOrderDetailTest(OrderDetail orderDetail)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.persist(orderDetail);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Add a new order detail - Implementation
    @Override
    public void addOrderDetail(OrderDetail orderDetail)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.persist(orderDetail);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Update an existing order detail - Junit test
    @Override
    public boolean updateOrderDetailTest(OrderDetail orderDetail)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(orderDetail);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Update an existing order detail - Implementation
    @Override
    public void updateOrderDetail(OrderDetail orderDetail)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.update(orderDetail);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Delete an existing order detail - Junit test
    @Override
    public boolean deleteOrderDetailTest(OrderDetail orderDetail)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(orderDetail);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Delete an existing order detail - Implementation
    @Override
    public void deleteOrderDetail(OrderDetail orderDetail)
    {
        try
        {
            Session session = sessionFactory.getCurrentSession();
            session.delete(orderDetail);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}