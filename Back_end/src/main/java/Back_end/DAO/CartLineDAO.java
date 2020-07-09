package Back_end.DAO;


import Back_end.DTO.Cart;
import Back_end.DTO.CartLine;

import java.util.List;



public interface CartLineDAO
{
    // Retrieve a single cart line based on id
    public CartLine get(int cartLineId);

    // Retrieve a list of cart lines based on cartId
    public List<CartLine> list(int cartId);

    // Add a new cart line - Junit test
    public boolean addTest(CartLine cartLine);

    // Add a new cart line - Implementation
    public void add(CartLine cartLine);

    // Update an existing cart line - Junit test
    public boolean updateTest(CartLine cartLine);

    // Update an existing cart line - Implementation
    public void update(CartLine cartLine);

    // Delete an existing cart line - Junit test
    public boolean deleteTest(CartLine cartLine);

    // Delete an existing cart line - Implementation
    public void delete(CartLine cartLine);

    // Retrieve the cart line based on cartId and productId
    public CartLine getCartLineByCartAndProduct(int cartId, int productId);

    // Retrieve only the available cart line
    public List<CartLine> listAvailableCartLines(int cartId);

    // Update an existing cart - Junit test
    boolean updateCartTest(Cart cart);

    // Update an existing cart - Implementation
    void updateCart(Cart cart);
}