package Front_end.Model;

import Back_end.DTO.Cart;

import java.io.Serializable;



public class UserModel implements Serializable
{
    // Private
    private static final long serialVersionUID = 1L;

    private int id;
    private String fullName;
    private String email;
    private String role;
    private Cart cart;


    // Public
    public UserModel() {}

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }
}