package Front_end.Model;


import Back_end.DTO.Address;
import Back_end.DTO.User;

import java.io.Serializable;



public class SignupModel implements Serializable
{
    // Private
    private static final long serialVersionUID = 1L;

    private User user;
    private Address billingAddress;


    // Public
    public SignupModel() {}

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }


    public Address getBillingAddress()
    {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
    }
}