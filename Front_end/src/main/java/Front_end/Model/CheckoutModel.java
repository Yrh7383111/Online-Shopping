package Front_end.Model;


import Back_end.DTO.*;

import java.io.Serializable;
import java.util.List;



public class CheckoutModel implements Serializable
{
    // Private
    private static final long serialVersionUID = 1L;

    private User user;
    private Address shippingAddress;
    private Cart cart;
    private List<CartLine> cartLines;
    private OrderDetail orderDetail;
    private double checkoutTotal;


    // Public
    public CheckoutModel() {}

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Address getShippingAddress()
    {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }

    public List<CartLine> getCartLines()
    {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines)
    {
        this.cartLines = cartLines;
    }

    public OrderDetail getOrderDetail()
    {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail)
    {
        this.orderDetail = orderDetail;
    }

    public double getCheckoutTotal()
    {
        return checkoutTotal;
    }

    public void setCheckoutTotal(double checkoutTotal)
    {
        this.checkoutTotal = checkoutTotal;
    }

    @Override
    public String toString()
    {
        return "CheckoutModel{" +
                "user=" + user +
                ", shippingAddress=" + shippingAddress +
                ", cart=" + cart +
                ", cartLines=" + cartLines +
                ", orderDetail=" + orderDetail +
                ", checkoutTotal=" + checkoutTotal +
                '}';
    }
}