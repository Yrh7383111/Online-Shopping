package Back_end.DTO;


import javax.persistence.*;



@Entity
@Table(name = "address")
public class Address
{
    // Private
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "address_line_one")
    private String addressLineOne;

    @Column(name = "address_line_two")
    private String addressLineTwo;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name ="postal_code")
    private String postalCode;

    @Column(name="is_billing")
    private boolean billing;

    @Column(name="is_shipping")
    private boolean shipping;


    // Public
    public Address() {}

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

    public String getAddressLineOne()
    {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne)
    {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo()
    {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo)
    {
        this.addressLineTwo = addressLineTwo;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public boolean isBilling()
    {
        return billing;
    }

    public void setBilling(boolean billing)
    {
        this.billing = billing;
    }

    public boolean isShipping()
    {
        return shipping;
    }

    public void setShipping(boolean shipping)
    {
        this.shipping = shipping;
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "id=" + id +
                ", user=" + user +
                ", addressLineOne='" + addressLineOne + '\'' +
                ", addressLineTwo='" + addressLineTwo + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", billing=" + billing +
                ", shipping=" + shipping +
                '}';
    }
}