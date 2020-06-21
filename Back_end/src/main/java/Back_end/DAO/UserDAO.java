package Back_end.DAO;


import Back_end.DTO.Address;
import Back_end.DTO.Cart;
import Back_end.DTO.User;



public interface UserDAO
{
    // Add a new user - Junit test
    boolean addUserTest(User user);

    // Add a new user - Implementation
    void addUser(User user);

    // Add a new address - Junit test
    boolean addAddressTest(Address address);

    // Add a new address - Implementation
    void addAddress(Address address);

    // Add a new cart - Junit test
    boolean addCartTest(Cart cart);

    // Add a new cart - Implementation
    void addCart(Cart cart);
}