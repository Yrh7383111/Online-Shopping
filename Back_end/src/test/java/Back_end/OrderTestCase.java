package Back_end;


import Back_end.DAO.OrderDAO;
import Back_end.DAO.ProductDAO;
import Back_end.DAO.UserDAO;
import Back_end.DTO.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



public class OrderTestCase
{
    // Private
    private static UserDAO userDAO;
    private static ProductDAO productDAO;
    private static OrderDAO orderDAO;


    // Public
    @BeforeClass
    public static void init()
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.scan("Back_end");
        context.refresh();

        userDAO = (UserDAO)context.getBean("userDAO");
        productDAO = (ProductDAO)context.getBean("productDAO");
        orderDAO = (OrderDAO) context.getBean("orderDAO");
    }

//    @Test
//    public void testCRUDOrderDetail()
//    {
//        OrderDetail orderDetail = new OrderDetail();
//        User user  = userDAO.getUser(5);
//        double orderTotal = 1999.0;
//        int orderCount = 1;
//        Address billing = userDAO.getAddress(2);
//        Address shipping = userDAO.getAddress(3);
//        Date orderDate = new Date(202007017);
//
//
//        orderDetail.setUser(user);
//        orderDetail.setOrderTotal(orderTotal);
//        orderDetail.setOrderCount(orderCount);
//        orderDetail.setBilling(billing);
//        orderDetail.setShipping(shipping);
//        orderDetail.setOrderDate(orderDate);
//
//        // Create
//        assertTrue(orderDAO.addOrderDetailTest(orderDetail));
//
//        // Read
//        orderDetail = orderDAO.getOrderDetail(1);
//        assertEquals(1999.00, orderDetail.getOrderTotal(), 0);
//
//        // Update
//        orderDetail = orderDAO.getOrderDetail(1);
//        user  = userDAO.getUser(6);
//
//        orderDetail.setUser(user);
//        orderDetail.setOrderTotal(user.getCart().getGrandTotal());
//        orderDetail.setOrderCount(user.getCart().getCartLines());
//        billing = userDAO.getAddress(4);
//        shipping = userDAO.getAddress(5);
//        orderDetail.setBilling(billing);
//        orderDetail.setShipping(shipping);
//        orderDetail.setOrderDate(orderDate);
//
//        assertTrue(orderDAO.updateOrderDetailTest(orderDetail));
//        assertEquals(5694.00, orderDetail.getOrderTotal(), 0);
//
//        // Delete
//        assertTrue(orderDAO.deleteOrderDetailTest(orderDetail));
//    }

    @Test
    public void testCRUDOrderItem()
    {
        // Create a Order Detail reference
        OrderDetail orderDetail = new OrderDetail();
        User user  = userDAO.getUser(5);
        double orderTotal = 1999.0;
        int orderCount = 1;
        Address billing = userDAO.getAddress(2);
        Address shipping = userDAO.getAddress(3);
        Date orderDate = new Date(202007017);


        orderDetail.setUser(user);
        orderDetail.setOrderTotal(orderTotal);
        orderDetail.setOrderCount(orderCount);
        orderDetail.setBilling(billing);
        orderDetail.setShipping(shipping);
        orderDetail.setOrderDate(orderDate);

        // Create
        assertTrue(orderDAO.addOrderDetailTest(orderDetail));



        // Order Item
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderDetail(orderDetail);
        orderItem.setTotal(1999.00);
        Product product = productDAO.get(6);
        orderItem.setProduct(product);
        orderItem.setProductCount(1);
        orderItem.setBuyingPrice(1999.00);


        // Create
        assertTrue(orderDAO.addOrderItemTest(orderItem));

        // Read
        orderItem = orderDAO.getOrderItem(1);
        assertEquals(1999.00, orderItem.getBuyingPrice(), 0);

        // Update
        orderItem = orderDAO.getOrderItem(1);
        orderItem.setBuyingPrice(1599.00);
        assertTrue(orderDAO.updateOrderItemTest(orderItem));
        assertEquals(1599.00, orderItem.getBuyingPrice(), 0);

        // Delete
        assertTrue(orderDAO.deleteOrderItemTest(orderItem));
    }
}