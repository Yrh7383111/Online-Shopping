package Back_end;


import Back_end.DAO.UserDAO;
import Back_end.DTO.Address;
import Back_end.DTO.Cart;
import Back_end.DTO.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

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

//    @Test
//    public void testAddUserCartAddress()
//	{
//		User user = new User();
//        Address billingAddress = new Address();
//        Address shippingAddress = new Address();
//        Cart cart = new Cart();
//
//
//        // Add a user
//        user.setFirstName("Leo");
//        user.setLastName("Li");
//        user.setPassword("123456");
//        user.setRole("USER");
//        user.setEmail("Leo.Li@gmail.com");
//        user.setContactNumber("12345678");
//        assertTrue(userDAO.addUserTest(user));
//
//        // Add a billing address
//        billingAddress.setUserId(user.getId());
//        billingAddress.setAddressLineOne("131 Columbia Street");
//        billingAddress.setAddressLineTwo("808");
//        billingAddress.setCity("Waterloo");
//        billingAddress.setState("Ontario");
//        billingAddress.setCountry("Canada");
//        billingAddress.setPostalCode("N2J 2Y2");
//        billingAddress.setBilling(true);
//        assertTrue(userDAO.addAddressTest(billingAddress));
//
//        if (user.getRole().equals("USER"))
//        {
//            // Add a shipping address
//            shippingAddress.setUserId(user.getId());
//            shippingAddress.setAddressLineOne("131 Columbia Street");
//            shippingAddress.setAddressLineTwo("808");
//            shippingAddress.setCity("Waterloo");
//            shippingAddress.setState("Ontario");
//            shippingAddress.setCountry("Canada");
//            shippingAddress.setPostalCode("N2J 2Y2");
//            shippingAddress.setShipping(true);
//            assertTrue(userDAO.addAddressTest(shippingAddress));
//
//            // Add a cart
//            cart.setUser(user);
//            cart.setGrandTotal(0);
//            cart.setCartLines(0);
//            assertTrue(userDAO.addCartTest(cart));
//        }
//	}

//    @Test
//    public void testAddUserCartAddressNew()
//    {
//        User user = new User();
//        Cart cart = new Cart();
//        Address billingAddress = new Address();
//        Address shippingAddress = new Address();
//
//
//        // Add a user
//        user.setFirstName("Charlie");
//        user.setLastName("Qian");
//        user.setPassword("123456");
//        user.setRole("USER");
//        user.setEmail("Charlie.Qian@gmail.com");
//        user.setContactNumber("12345678");
//        user.setCart(cart);
//        cart.setUser(user);
//        assertTrue(userDAO.addUserTest(user));
//
//        // Add a billing address
//        billingAddress.setAddressLineOne("131 Columbia Street");
//        billingAddress.setAddressLineTwo("808");
//        billingAddress.setCity("Waterloo");
//        billingAddress.setState("Ontario");
//        billingAddress.setCountry("Canada");
//        billingAddress.setPostalCode("N2J 2Y2");
//        billingAddress.setBilling(true);
//        billingAddress.setUser(user);
//        assertTrue(userDAO.addAddressTest(billingAddress));
//
//        // Add a shipping address
//        shippingAddress.setAddressLineOne("131 Columbia Street");
//        shippingAddress.setAddressLineTwo("808");
//        shippingAddress.setCity("Waterloo");
//        shippingAddress.setState("Ontario");
//        shippingAddress.setCountry("Canada");
//        shippingAddress.setPostalCode("N2J 2Y2");
//        shippingAddress.setShipping(true);
//        shippingAddress.setUser(user);
//        assertTrue(userDAO.addAddressTest(shippingAddress));
//    }

//    @Test
//    public void testGetUpdateUserCart()
//    {
//        User user = userDAO.getUser(8);
//        Cart cart = userDAO.getCart(user.getCart().getId());
//
//
//        user.setFirstName("Thomas");
//        user.setLastName("Zhang");
//        user.setEmail("Thomas.Zhang@gmail.com");
//        assertTrue(userDAO.updateUserTest(user));
//
//        user = userDAO.getUser(8);
//        assertEquals("Thomas.Zhang@gmail.com", user.getEmail());
//
//        cart.setGrandTotal(999);
//        cart.setCartLines(1);
//        assertTrue(userDAO.updateCartTest(cart));
//
//        cart = userDAO.getCart(user.getCart().getId());
//        assertEquals(new Double(999), new Double(cart.getGrandTotal()));
//    }

//    @Test
//    public void testGetUpdateAddress()
//    {
//        User user = userDAO.getUser(8);
//        List<Address> billingAddresses = userDAO.listBillingAddresses(user);
//        List<Address> shippingAddresses = userDAO.listShippingAddresses(user);
//
//
//        assertEquals(1, billingAddresses.size());
//        assertEquals(1, shippingAddresses.size());
//
//
//        Address billingAddress = billingAddresses.get(0);
//        Address shippingAddress = shippingAddresses.get(0);
//
//        billingAddress.setAddressLineOne("330 Phillip St");
//        billingAddress.setAddressLineTwo("903");
//        billingAddress.setPostalCode("N2L 3W9");
//        assertTrue(userDAO.updateAddressTest(billingAddress));
//
//        billingAddress = billingAddresses.get(0);
//        assertEquals("330 Phillip St", billingAddress.getAddressLineOne());
//
//        shippingAddress.setAddressLineOne("330 Phillip St");
//        shippingAddress.setAddressLineTwo("903");
//        shippingAddress.setPostalCode("N2L 3W9");
//        assertTrue(userDAO.updateAddressTest(shippingAddress));
//
//        shippingAddress = shippingAddresses.get(0);
//        assertEquals("330 Phillip St", shippingAddress.getAddressLineOne());
//    }


}