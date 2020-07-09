package Back_end.DTO;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "cart_line")
public class CartLine implements Serializable
{
    // Private
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "total")
    private double total;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_count")
    private int productCount;

    @Column(name = "buying_price")
    private double buyingPrice;

    @Column(name = "is_available")
    private boolean available = true;



    // Public
    public CartLine() {}

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCartId()
    {
        return cartId;
    }

    public void setCartId(int cartId)
    {
        this.cartId = cartId;
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

    public boolean isAvailable()
    {
        return available;
    }

    public void setAvailable(boolean available)
    {
        this.available = available;
    }

    @Override
    public String toString()
    {
        return "CartLine{" +
                "id=" + id +
                ", cartId=" + cartId +
                ", total=" + total +
                ", product=" + product +
                ", productCount=" + productCount +
                ", buyingPrice=" + buyingPrice +
                ", available=" + available +
                '}';
    }
}