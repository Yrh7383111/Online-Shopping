package Front_end.Validator;


import Back_end.DTO.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;


public class ProductValidator implements Validator
{

    @Override
    public boolean supports(Class<?> clazz)
    {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        Product product = (Product) target;


        // If there is no file uploaded
        if (product.getFile() == null || Objects.equals(product.getFile().getOriginalFilename(), ""))
        {
            errors.rejectValue("file", null, "Please select a file to upload!");
            return;
        }

        // If the image extension does not meet the requirement
        if (!(Objects.equals(product.getFile().getContentType(), "image/jpg") ||
              Objects.equals(product.getFile().getContentType(), "image/jpeg") ||
              Objects.equals(product.getFile().getContentType(), "image/png") ||
              Objects.equals(product.getFile().getContentType(), "image/gif")))
        {
            errors.rejectValue("file", null, "Please select an image file to upload!");
        }
    }
}