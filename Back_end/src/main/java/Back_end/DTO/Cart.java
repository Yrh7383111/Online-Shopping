package Back_end.DTO;


import javax.persistence.*;



@Entity
@Table(name = "cart")
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "grand_total")
    private double grandTotal;

    @Column(name = "cart_lines")
    private int cartLines;


    // Public
    public Cart() {}

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

    public double getGrandTotal()
    {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal)
    {
        this.grandTotal = grandTotal;
    }

    public int getCartLines()
    {
        return cartLines;
    }

    public void setCartLines(int cartLines)
    {
        this.cartLines = cartLines;
    }

    @Override
    public String toString()
    {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", grandTotal=" + grandTotal +
                ", cartLines=" + cartLines +
                '}';
    }
}