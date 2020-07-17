package Back_end.DTO;


import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable
{
    // Private
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private OrderDetail orderDetail;

    @Column(name = "total")
    private double total;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column (name = "product_count")
    private int productCount;

    @Column (name = "buying_price")
    private double buyingPrice;


    // Public
    public OrderItem() {}

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public OrderDetail getOrderDetail()
    {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail)
    {
        this.orderDetail = orderDetail;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public int getProductCount()
    {
        return productCount;
    }

    public void setProductCount(int productCount)
    {
        this.productCount = productCount;
    }

    public double getBuyingPrice()
    {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice)
    {
        this.buyingPrice = buyingPrice;
    }

    @Override
    public String toString()
    {
        return "OrderItem{" +
                "id=" + id +
                ", orderDetail=" + orderDetail +
                ", total=" + total +
                ", product=" + product +
                ", productCount=" + productCount +
                ", buyingPrice=" + buyingPrice +
                '}';
    }
}