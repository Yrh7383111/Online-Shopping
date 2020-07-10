package Front_end.Service;


import Back_end.DAO.CartLineDAO;
import Back_end.DTO.Cart;
import Back_end.DTO.CartLine;
import Front_end.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service("cartService")
public class CartService
{
    // Private
    @Autowired
    private HttpSession session;

    @Autowired
    private CartLineDAO cartLineDAO;


    // Public
    // Retrieve the cart
    public Cart getCart()
    {
        UserModel userModel = (UserModel)session.getAttribute("userModel");
        Cart cart = userModel.getCart();

        return cart;
    }

    // Retrieve a list of cart lines
    public List<CartLine> listCartLines()
    {
        UserModel userModel = (UserModel)session.getAttribute("userModel");
        Cart cart = userModel.getCart();
        List<CartLine> cartLines = cartLineDAO.list(cart.getId());

        return cartLines;
    }
}