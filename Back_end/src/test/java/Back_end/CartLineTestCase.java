package Back_end;


import Back_end.DAO.CartLineDAO;
import Back_end.DAO.ProductDAO;
import Back_end.DAO.UserDAO;
import Back_end.DTO.Cart;
import Back_end.DTO.CartLine;
import Back_end.DTO.Product;
import Back_end.DTO.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CartLineTestCase
{
    // Private
    private static UserDAO userDAO;
    private static ProductDAO productDAO;
    private static CartLineDAO cartLineDAO;


    // Public
    @BeforeClass
    public static void init()
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.scan("Back_end");
        context.refresh();

        userDAO = (UserDAO)context.getBean("userDAO");
        productDAO = (ProductDAO)context.getBean("productDAO");
        cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
    }

//    @Test
//    public void testCRUDCartLine()
//    {
//        User user = userDAO.getUser(6);
//        Cart cart = user.getCart();
//        Product product = productDAO.get(2);
//        CartLine cartLine = new CartLine();
//
//
//        cartLine.setCartId(cart.getId());
//        cartLine.setProduct(product);
//        cartLine.setProductCount(cartLine.getProductCount() + 1);
//        cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
//        cartLine.setBuyingPrice(product.getUnitPrice());
//        cartLine.setAvailable(true);
//
//        // Create
//        assertTrue(cartLineDAO.addTest(cartLine));
//
//        // Read
//        cartLine = cartLineDAO.get(1);
//        assertEquals(1599.00, cartLine.getTotal(), 0);
//
//        // Update
//        cartLine = cartLineDAO.get(1);
//        cartLine.setProductCount(cartLine.getProductCount() + 1);
//        assertTrue(cartLineDAO.updateTest(cartLine));
//        assertEquals(2, cartLine.getProductCount());
//
//        // Delete
//        assertTrue(cartLineDAO.deleteTest(cartLine));
//    }

    @Test
    public void testUpdateCart()
    {
        User user = userDAO.getUser(6);
        Cart cart = user.getCart();
        Product product = productDAO.get(2);
        CartLine cartLine = new CartLine();


        cartLine.setCartId(cart.getId());
        cartLine.setProduct(product);
        cartLine.setProductCount(cartLine.getProductCount() + 10);
        cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
        cartLine.setBuyingPrice(product.getUnitPrice());
        cartLine.setAvailable(true);

        // Create
        assertTrue(cartLineDAO.addTest(cartLine));

        // Update cart
        cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
        cart.setCartLines(cart.getCartLines() + 1);
        assertTrue(cartLineDAO.updateCartTest(cart));
    }
}