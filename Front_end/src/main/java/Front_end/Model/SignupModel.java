package Front_end.Model;


import Back_end.DTO.Address;
import Back_end.DTO.User;

import java.io.Serializable;



public class SignupModel implements Serializable
{
    // Private
    private static final long serialVersionUID = 1L;

    private User user;
    private Address billing;


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

    public Address getBilling()
    {
        return billing;
    }

    public void setBilling(Address billing)
    {
        this.billing = billing;
    }
}