package Front_end.Handler;


import Back_end.DAO.CartLineDAO;
import Back_end.DAO.OrderDAO;
import Back_end.DAO.ProductDAO;
import Back_end.DAO.UserDAO;
import Back_end.DTO.*;
import Front_end.Model.CheckoutModel;
import Front_end.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Component("checkoutHandler")
public class CheckoutHandler
{
    // Private
    private final UserDAO userDAO;
    private final ProductDAO productDAO;
    private final CartLineDAO cartLineDAO;
    private final OrderDAO orderDAO;
    private final HttpSession httpSession;


    // Public
    @Autowired
    public CheckoutHandler(UserDAO userDAO, ProductDAO productDAO,
                           CartLineDAO cartLineDAO, OrderDAO orderDAO,
                           HttpSession httpSession)
    {
        this.userDAO = userDAO;
        this.productDAO = productDAO;
        this.cartLineDAO = cartLineDAO;
        this.orderDAO = orderDAO;
        this.httpSession = httpSession;
    }

    public CheckoutModel init(String email)
    {
        User user = userDAO.getUserByEmail(email);
        CheckoutModel checkoutModel = new CheckoutModel();

        if (user != null)
        {
            checkoutModel.setUser(user);
            checkoutModel.setCart(user.getCart());

            List<CartLine> cartLines = cartLineDAO.listAvailableCartLines(user.getCart().getId());
            checkoutModel.setCartLines(cartLines);

            double checkoutTotal = 0.0;
            for (CartLine cartLine : cartLines)
                checkoutTotal += cartLine.getTotal();
            checkoutModel.setCheckoutTotal(checkoutTotal);
        }

        return checkoutModel;
    }

    public List<Address> getShippingAddresses(CheckoutModel checkoutModel)
    {
        int userId = checkoutModel.getUser().getId();
        List<Address> shippingAddresses = userDAO.listShippingAddresses(userId);
        List<Address> billingAddresses = userDAO.listBillingAddresses(userId);

        if (shippingAddresses == null)
            shippingAddresses = new ArrayList<Address>();

        shippingAddresses.addAll(billingAddresses);

        return shippingAddresses;
    }

    public OrderDetail getOrderDetail(CheckoutModel checkoutModel)
    {
        OrderDetail orderDetail = checkoutModel.getOrderDetail();

        return orderDetail;
    }

    public String selectListAddress(CheckoutModel checkoutModel, int shippingId)
    {
        Address shippingAddress = userDAO.getAddress(shippingId);

        checkoutModel.setShippingAddress(shippingAddress);

        return "success";
    }

    public String addNewAddress(CheckoutModel checkoutModel, Address shippingAddress)
    {
        shippingAddress.setUser(checkoutModel.getUser());
        shippingAddress.setShipping(true);

        checkoutModel.setShippingAddress(shippingAddress);

        userDAO.addAddress(shippingAddress);

        return "success";
    }

    public String addOrder(CheckoutModel checkoutModel)
    {
        List<CartLine> cartLines = checkoutModel.getCartLines();
        OrderDetail orderDetail = new OrderDetail();
        Date date = new Date();
        double orderTotal = 0.0;
        int orderCount = 0;


        for (CartLine cartLine : cartLines)
        {
            List<OrderItem> orderItems = orderDetail.getOrderItems();
            Product product = cartLine.getProduct();
            OrderItem orderItem = new OrderItem();

            // Set entities of orderItem
            orderItem.setOrderDetail(orderDetail);
            orderItem.setTotal(cartLine.getTotal());
            orderItem.setProduct(cartLine.getProduct());
            orderItem.setProductCount(cartLine.getProductCount());
            orderItem.setBuyingPrice(cartLine.getBuyingPrice());
            // Add orderItem to orderDetail
            orderItems.add(orderItem);

            // Update two entities for orderDetail
            orderTotal += orderItem.getTotal();
            orderCount++;

            // Update product
            // 1. quantity
            // 2. purchases
            product.setQuantity(product.getQuantity() - cartLine.getProductCount());
            product.setPurchases(product.getPurchases() + cartLine.getProductCount());
            // Update product in database
            productDAO.update(product);

            // Delete cartLine from cart
            cartLineDAO.delete(cartLine);
        }

        // Set entities of orderDetail
        orderDetail.setUser(checkoutModel.getUser());
        orderDetail.setOrderTotal(orderTotal);
        orderDetail.setOrderCount(orderCount);
        orderDetail.setShipping(checkoutModel.getShippingAddress());
        orderDetail.setBilling((Address) userDAO.listBillingAddresses(checkoutModel.getUser().getId()));
        orderDetail.setOrderDate(date);

        // Add orderDetail in database
        orderDAO.addOrderDetail(orderDetail);

        // Set orderDetail of checkoutModel
        checkoutModel.setOrderDetail(orderDetail);

        // Update cart
        Cart cart = checkoutModel.getCart();
        cart.setGrandTotal(cart.getGrandTotal() - orderTotal);
        cart.setCartLines(cart.getCartLines() - orderCount);
        cartLineDAO.updateCart(cart);

        // Update cart if it belongs a user
        UserModel userModel = (UserModel) httpSession.getAttribute("userModel");
        if (userModel != null)
            userModel.setCart(cart);


        return "success";
    }
}