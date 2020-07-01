package Front_end.Handler;


import Back_end.DAO.UserDAO;
import Back_end.DTO.Address;
import Back_end.DTO.Cart;
import Back_end.DTO.User;
import Front_end.Model.SignupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class SignupHandler
{
    // Private
    @Autowired
    private UserDAO userDAO;


    // Public
    public SignupHandler() {}

    public SignupModel init()
    {
        SignupModel signupModel = new SignupModel();

        return signupModel;
    }

    public void addUser(SignupModel signupModel, User user)
    {
        signupModel.setUser(user);
    }

    public void addBillingAddress(SignupModel signupModel, Address billingAddress)
    {
        signupModel.setBillingAddress(billingAddress);
    }

    public String save(SignupModel signupModel)
    {
        User user = signupModel.getUser();
        Address billingAddress = signupModel.getBillingAddress();


        // If the role of the is USER, then create a cart
        if (user.getRole().equals("USER"))
        {
            Cart cart = new Cart();

            cart.setUser(user);
            user.setCart(cart);
        }

        billingAddress.setUser(user);
        billingAddress.setBilling(true);

        userDAO.addUser(user);
        userDAO.addAddress(billingAddress);

        return "success";
    }
}