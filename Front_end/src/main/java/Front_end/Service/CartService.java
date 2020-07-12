package Front_end.Service;


import Back_end.DAO.CartLineDAO;
import Back_end.DTO.Cart;
import Back_end.DTO.CartLine;
import Back_end.DTO.Product;
import Front_end.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service("cartService")
public class CartService
{
    // Private
    private final HttpSession session;
    private final CartLineDAO cartLineDAO;


    // Public
    @Autowired
    public CartService(HttpSession session, CartLineDAO cartLineDAO)
    {
        this.session = session;
        this.cartLineDAO = cartLineDAO;
    }

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

    // Update one cart line
    public String updateCartLine(int id, int count)
    {
        CartLine cartLine = cartLineDAO.get(id);

        Product product = cartLine.getProduct();
        double oldTotal = cartLine.getTotal();

        if (count > product.getQuantity())
            count = product.getQuantity();
        // Else
        cartLine.setProductCount(count);
        cartLine.setBuyingPrice(product.getUnitPrice());

        double newTotal = product.getUnitPrice() * count;
        cartLine.setTotal(newTotal);

        cartLineDAO.update(cartLine);


        UserModel userModel = (UserModel)session.getAttribute("userModel");
        Cart cart = userModel.getCart();
        double oldGrandTotal = cart.getGrandTotal();
        double newGrandTotal = oldGrandTotal + (newTotal - oldTotal);

        cart.setGrandTotal(newGrandTotal);
        cartLineDAO.updateCart(cart);

        return "result=updated";
    }
}