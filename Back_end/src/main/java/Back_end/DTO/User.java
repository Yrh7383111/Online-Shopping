package Back_end.DTO;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "user_detail")
public class User implements Serializable
{
    // Private
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "Please enter first name...")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Please enter last name...")
    private String lastName;

    @Column(name = "role")
    private String role;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "password")
    @NotBlank(message = "Please enter password...")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "email")
    @NotBlank(message = "Please enter email address...")
    private String email;

    @Column(name = "contact_number")
    @NotBlank(message = "Please enter contact number...")
    private String contactNumber;

    // mappedBy - Specify that User is the owner of Cart,
    //            and therefore avoid adding a cart_id filed in User table
    // cascade - Automatically add a Cart when a User is created
    @OneToOne(mappedBy="user",
              fetch = FetchType.EAGER,
              cascade = CascadeType.ALL)
    private Cart cart;

    // mappedBy - Specify that User is the owner of Address,
    //            and therefore avoid adding a address_id filed in User table
    // cascade - Automatically remove all the Address when a User is deleted
    @OneToMany(mappedBy="user",
               fetch = FetchType.LAZY,
               cascade= {CascadeType.MERGE, CascadeType.DETACH,
                         CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Address> addresses;


    // Public
    public User() {}

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getConfirmPassword()
    {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }

    public List<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(List<Address> addresses)
    {
        this.addresses = addresses;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", cart=" + cart +
                ", addresses=" + addresses +
                '}';
    }
}