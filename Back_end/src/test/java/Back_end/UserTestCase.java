package Back_end;


import Back_end.DAO.UserDAO;
import Back_end.DTO.Address;
import Back_end.DTO.Cart;
import Back_end.DTO.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;


public class UserTestCase
{
    // Private
    private static UserDAO userDAO;


    // Public
    @BeforeClass
    public static void init()
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.scan("Back_end");
        context.refresh();

        userDAO = (UserDAO) context.getBean("userDAO");
    }

    @Test
    public void testAddUser()
	{
		User user = new User();
        Address billingAddress = new Address();
        Address shippingAddress = new Address();
        Cart cart = new Cart();


        // Add a user
        user.setFirstName("Charlie");
        user.setLastName("Liu");
        user.setPassword("123456");
        user.setRole("USER");
        user.setEmail("Charlie.Liu@gmail.com");
        user.setContactNumber("12345678");
        assertTrue(userDAO.addUserTest(user));

        // Add a billing address
        billingAddress.setUserId(user.getId());
        billingAddress.setAddressLineOne("131 Columbia Street");
        billingAddress.setAddressLineTwo("808");
        billingAddress.setCity("Waterloo");
        billingAddress.setState("Ontario");
        billingAddress.setCountry("Canada");
        billingAddress.setPostalCode("N2J 2Y2");
        billingAddress.setBilling(true);
        assertTrue(userDAO.addAddressTest(billingAddress));

        if (user.getRole().equals("USER"))
        {
            // Add a shipping address
            shippingAddress.setUserId(user.getId());
            shippingAddress.setAddressLineOne("131 Columbia Street");
            shippingAddress.setAddressLineTwo("808");
            shippingAddress.setCity("Waterloo");
            shippingAddress.setState("Ontario");
            shippingAddress.setCountry("Canada");
            shippingAddress.setPostalCode("N2J 2Y2");
            shippingAddress.setShipping(true);
            assertTrue(userDAO.addAddressTest(shippingAddress));

            // Add a cart
            cart.setUserId(user.getId());
            cart.setGrandTotal(0);
            cart.setCartLines(0);
            assertTrue(userDAO.addCartTest(cart));
        }
	}
}