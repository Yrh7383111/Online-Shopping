package Back_end.DAO;


import Back_end.DTO.OrderDetail;
import Back_end.DTO.OrderItem;



public interface OrderDAO
{
    // Retrieve a order item based on orderItemId
    OrderItem getOrderItem(int orderItemId);

    // Add a new order item - Junit test
    boolean addOrderItemTest(OrderItem orderItem);

    // Add a new order item - Implementation
    void addOrderItem(OrderItem orderItem);

    // Update an existing order item - Junit test
    boolean updateOrderItemTest(OrderItem orderItem);

    // Update an existing order item - Implementation
    void updateOrderItem(OrderItem orderItem);

    // Delete an existing order item - Junit test
    boolean deleteOrderItemTest(OrderItem orderItem);

    // Delete an existing order item - Implementation
    void deleteOrderItem(OrderItem orderItem);

    // Retrieve a order detail based on orderDetailId
    OrderDetail getOrderDetail(int orderDetailId);

    // Add a new order detail - Junit test
    boolean addOrderDetailTest(OrderDetail orderDetail);

    // Add a new order detail - Implementation
    void addOrderDetail(OrderDetail orderDetail);

    // Update an existing order detail - Junit test
    boolean updateOrderDetailTest(OrderDetail orderDetail);

    // Update an existing order detail - Implementation
    void updateOrderDetail(OrderDetail orderDetail);

    // Delete an existing order detail - Junit test
    boolean deleteOrderDetailTest(OrderDetail orderDetail);

    // Delete an existing order detail - Implementation
    void deleteOrderDetail(OrderDetail orderDetail);
}