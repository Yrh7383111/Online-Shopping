package Back_end.DTO;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable
{
    // Private
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_total")
    private double orderTotal;

    @Column(name = "order_count")
    private int orderCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shipping_id")
    private Address shipping;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billing_id")
    private Address billing;

    @Column(name="order_date")
    private Date orderDate;

    @OneToMany(mappedBy="orderDetail",
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;


    // Public
    public OrderDetail() {}

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public double getOrderTotal()
    {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal)
    {
        this.orderTotal = orderTotal;
    }

    public int getOrderCount()
    {
        return orderCount;
    }

    public void setOrderCount(int orderCount)
    {
        this.orderCount = orderCount;
    }

    public Address getShipping()
    {
        return shipping;
    }

    public void setShipping(Address shipping)
    {
        this.shipping = shipping;
    }

    public Address getBilling()
    {
        return billing;
    }

    public void setBilling(Address billing)
    {
        this.billing = billing;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getOrderItems()
    {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems)
    {
        this.orderItems = orderItems;
    }

    @Override
    public String toString()
    {
        return "OrderDetail{" +
                "id=" + id +
                ", user=" + user +
                ", orderTotal=" + orderTotal +
                ", orderCount=" + orderCount +
                ", shipping=" + shipping +
                ", billing=" + billing +
                ", orderDate=" + orderDate +
                ", orderItems=" + orderItems +
                '}';
    }
}