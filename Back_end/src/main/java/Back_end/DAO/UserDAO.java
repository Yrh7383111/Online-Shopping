package Back_end.DAO;


import Back_end.DTO.Address;
import Back_end.DTO.Cart;
import Back_end.DTO.User;



public interface UserDAO
{
    // Retrieve a single user based on userId
    User get(int userId);

    // Add a new user - Junit test
    boolean addUserTest(User user);

    // Add a new user - Implementation
    void addUser(User user);

    // Add a new address - Junit test
    boolean addAddressTest(Address address);

    // Add a new address - Implementation
    void addAddress(Address address);

    // Update an existing cart - Junit test
    boolean updateCartTest(Cart cart);

    // Update an existing cart - Implementation
    void updateCart(Cart cart);
}