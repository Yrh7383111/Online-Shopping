package Front_end.Service;


import Back_end.DAO.CartLineDAO;
import Back_end.DAO.ProductDAO;
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
    private final HttpSession httpSession;
    private final ProductDAO productDAO;
    private final CartLineDAO cartLineDAO;


    // Public
    @Autowired
    public CartService(HttpSession httpSession, ProductDAO productDAO, CartLineDAO cartLineDAO)
    {
        this.httpSession = httpSession;
        this.productDAO = productDAO;
        this.cartLineDAO = cartLineDAO;
    }

    // Retrieve the cart
    public Cart getCart()
    {
        UserModel userModel = (UserModel)httpSession.getAttribute("userModel");
        Cart cart = userModel.getCart();

        return cart;
    }

    // Retrieve a list of cart lines
    public List<CartLine> listCartLines()
    {
        UserModel userModel = (UserModel)httpSession.getAttribute("userModel");
        Cart cart = userModel.getCart();
        List<CartLine> cartLines = cartLineDAO.list(cart.getId());

        return cartLines;
    }

    public String addCartLine(int productId)
    {
        String result = "";
        UserModel userModel = (UserModel)httpSession.getAttribute("userModel");
        Cart cart = userModel.getCart();
        int cartId = cart.getId();
        CartLine cartLine = cartLineDAO.getCartLineByCartAndProduct(cartId, productId);


        if (cartLine != null)
        {
            result = updateCartLine(cartLine.getId(), cartLine.getProductCount() + 1);
        }
        else {
            cartLine = new CartLine();
            Product product = productDAO.get(productId);

            cartLine.setCartId(cartId);
            cartLine.setTotal(product.getUnitPrice());
            cartLine.setProduct(product);
            cartLine.setProductCount(1);
            cartLine.setBuyingPrice(product.getUnitPrice());
            cartLineDAO.add(cartLine);

            cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
            cart.setCartLines(cart.getCartLines() + 1);
            cartLineDAO.updateCart(cart);

            result = "result=added";
        }

        return result;
    }

    // Update one cart line
    public String updateCartLine(int cartLineId, int count)
    {
        String result = "";
        CartLine cartLine = cartLineDAO.get(cartLineId);
        Product product = cartLine.getProduct();
        double oldTotal = cartLine.getTotal();

        if (count > product.getQuantity())
            result = "result=unavailable";
        else {
            cartLine.setProductCount(count);
            cartLine.setBuyingPrice(product.getUnitPrice());

            double newTotal = product.getUnitPrice() * count;
            cartLine.setTotal(newTotal);

            cartLineDAO.update(cartLine);


            UserModel userModel = (UserModel)httpSession.getAttribute("userModel");
            Cart cart = userModel.getCart();
            double oldGrandTotal = cart.getGrandTotal();
            double newGrandTotal = oldGrandTotal + (newTotal - oldTotal);

            cart.setGrandTotal(newGrandTotal);
            cartLineDAO.updateCart(cart);

            result = "result=updated";
        }

        return result;
    }

    public String deleteCartLine(int cartLineId)
    {
        CartLine cartLine = cartLineDAO.get(cartLineId);
        cartLineDAO.delete(cartLine);


        UserModel userModel = (UserModel)httpSession.getAttribute("userModel");
        Cart cart = userModel.getCart();
        double newGrandTotal = cart.getGrandTotal() - cartLine.getTotal();
        int newCartLines = cart.getCartLines() - 1;

        cart.setGrandTotal(newGrandTotal);
        cart.setCartLines(newCartLines);
        cartLineDAO.updateCart(cart);

        return "result=deleted";
    }
}