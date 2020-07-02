package Back_end.DAO;


import Back_end.DTO.Address;
import Back_end.DTO.Cart;
import Back_end.DTO.User;

import java.util.List;



public interface UserDAO
{
    // Retrieve a single user based on userId
    User getUser(int userId);

    // Retrieve a single user based on email
    User getUserByEmail(String email);

    // Add a new user - Junit test
    boolean addUserTest(User user);

    // Add a new user - Implementation
    void addUser(User user);

    // Update a new user - Junit test
    boolean updateUserTest(User user);

    // Update a new user - Implementation
    void updateUser(User user);

    // Delete a new user - Junit test
    boolean deleteUserTest(User user);

    // Delete a new user - Implementation
    void deleteUser(User user);

    // Retrieve a single address based on addressId
    Address getAddress(int addressId);

    // Add a new address - Junit test
    boolean addAddressTest(Address address);

    // Add a new address - Implementation
    void addAddress(Address address);

    // Update a new address - Junit test
    boolean updateAddressTest(Address address);

    // Update a new address - Implementation
    void updateAddress(Address address);

    // Delete a new address - Junit test
    boolean deleteAddressTest(Address address);

    // Delete a new address - Implementation
    void deleteAddress(Address address);

    // Retrieve a list of addresses based on specific user
    List<Address> listAddresses(User user);

    // Retrieve a list of billing addresses based on specific user
    List<Address> listBillingAddresses(User user);

    // Retrieve a list of shipping addresses based on specific user
    List<Address> listShippingAddresses(User user);

    // Retrieve a single cart based on cartId
    Cart getCart(int cartId);

    // Update an existing cart - Junit test
    boolean updateCartTest(Cart cart);

    // Update an existing cart - Implementation
    void updateCart(Cart cart);

    // Delete an existing cart - Junit test
    boolean deleteCartTest(Cart cart);

    // Delete an existing cart - Implementation
    void deleteCart(Cart cart);
}