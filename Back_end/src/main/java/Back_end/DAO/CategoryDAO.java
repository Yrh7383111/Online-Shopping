package Back_end.DAO;


import Back_end.DTO.Category;

import java.util.List;



public interface CategoryDAO
{
    // Retrieve a list of categories
    List<Category> list();

    // Retrieve a single category based on id
    Category get(int id);
}