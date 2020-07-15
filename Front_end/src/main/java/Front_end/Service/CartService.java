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
    public List<CartLine> listAvailableCartLines()
    {
        UserModel userModel = (UserModel)httpSession.getAttribute("userModel");
        Cart cart = userModel.getCart();
        List<CartLine> cartLines = cartLineDAO.listAvailableCartLines(cart.getId());

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

    public String validateCartLine()
    {
        String result = "result=empty";
        UserModel userModel = (UserModel)httpSession.getAttribute("userModel");
        Cart cart = userModel.getCart();
        List<CartLine> cartLines = cartLineDAO.list(cart.getId());

        int cartLinesCount = 0;
        double grandTotal = 0.0;
        boolean isChanged = false;


        for (CartLine cartLine : cartLines)
        {
            Product product = cartLine.getProduct();

            // If the product's unit price is changed
            if (cartLine.getBuyingPrice() != product.getUnitPrice())
            {
                cartLine.setBuyingPrice(product.getUnitPrice());
                cartLine.setTotal(cartLine.getBuyingPrice() * cartLine.getProductCount());
                isChanged = true;
            }

            // If the product's quantity is changed
            if (cartLine.getProductCount() > product.getQuantity())
            {
                cartLine.setProductCount(product.getQuantity());
                cartLine.setTotal(cartLine.getBuyingPrice() * cartLine.getProductCount());
                isChanged = true;
            }

            // If the product is deactivated, but the cart line is available
            if (!product.isActive() && cartLine.isAvailable())
            {
                cartLine.setAvailable(false);
                isChanged = true;
            }

            // If the product line is available, but the cart line is deactivated
            if (product.isActive() && !cartLine.isAvailable())
            {
                if (product.getQuantity() > 0)
                {
                    cartLine.setAvailable(true);
                    isChanged = true;
                }
            }

            if (isChanged)
                cartLineDAO.update(cartLine);

            if (product.isActive() && cartLine.isAvailable())
                cartLinesCount++;

            if (product.isActive() && cartLine.isAvailable())
                grandTotal += cartLine.getTotal();
        }

        // If at least one cart line was modified
        if (isChanged)
            result = "result=modified";

        // Update cart
        cart.setCartLines(cartLinesCount);
        cart.setGrandTotal(grandTotal);
        cartLineDAO.updateCart(cart);

        return result;
    }
}