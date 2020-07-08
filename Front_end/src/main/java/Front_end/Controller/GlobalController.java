package Front_end.Controller;


import Back_end.DAO.UserDAO;
import Back_end.DTO.User;
import Front_end.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;



@ControllerAdvice
public class GlobalController
{
    // Private
    @Autowired
    private HttpSession session;

    @Autowired
    private UserDAO userDAO;


    // Public
    @ModelAttribute("userModel")
    public UserModel getUserModel()
    {
        UserModel userModel = (UserModel) session.getAttribute("userModel");


        if (userModel == null)
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userDAO.getUserByEmail(authentication.getName());

            if (user != null)
            {
                UserModel newUserModel = new UserModel();

                newUserModel.setId(user.getId());
                newUserModel.setFullName(user.getFirstName() + " " + user.getLastName());
                newUserModel.setRole(user.getRole());

                if (user.getRole().equals("USER"))
                    newUserModel.setCart(user.getCart());

                session.setAttribute("userModel", newUserModel);

                return newUserModel;
            }
        }

        return userModel;
    }
}