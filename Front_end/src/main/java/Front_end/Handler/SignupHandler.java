package Front_end.Handler;


import Back_end.DAO.UserDAO;
import Back_end.DTO.Address;
import Back_end.DTO.Cart;
import Back_end.DTO.User;
import Front_end.Model.SignupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.message.MessageResolver;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class SignupHandler
{
    // Private
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;


    // Public
    @Autowired
    public SignupHandler(UserDAO userDAO, PasswordEncoder passwordEncoder)
    {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public SignupModel init()
    {
        SignupModel signupModel = new SignupModel();

        return signupModel;
    }

    public void addUser(SignupModel signupModel, User user)
    {
        signupModel.setUser(user);
    }

    public String validateUser(User user, MessageContext messageContext)
    {
        String transitionValue = new String("success");

        // Password matches confirm password
        if (!user.getPassword().equals(user.getConfirmPassword()))
        {
            MessageBuilder messageBuilder = new MessageBuilder();
            MessageResolver messageResolver = messageBuilder.error().source("confirmPassword")
                                                            .defaultText("Password doesn't match confirm password...").build();

            messageContext.addMessage(messageResolver);

            transitionValue = "failure";
        }

        // Unique email address
        if (userDAO.getUserByEmail(user.getEmail()) != null)
        {
            MessageBuilder messageBuilder = new MessageBuilder();
            MessageResolver messageResolver = messageBuilder.error().source("email")
                                                            .defaultText("Email address is already taken...").build();

            messageContext.addMessage(messageResolver);

            transitionValue = "failure";
        }

        return transitionValue;
    }

    public void addBillingAddress(SignupModel signupModel, Address billingAddress)
    {
        signupModel.setBillingAddress(billingAddress);
    }

    public String save(SignupModel signupModel)
    {
        User user = signupModel.getUser();
        Address billingAddress = signupModel.getBillingAddress();

        // Encrypt password using B encryption
        user.setPassword(passwordEncoder.encode(user.getPassword()));

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