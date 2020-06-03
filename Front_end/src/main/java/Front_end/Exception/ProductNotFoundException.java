package Front_end.Exception;


import java.io.Serializable;



public class ProductNotFoundException extends Exception implements Serializable
{
    // Private
    private static final long serialVersionUID = 1L;
    private String message;


    // Public
    public ProductNotFoundException()
    {
        this.message = "Product is not available...";
    }

    public ProductNotFoundException(String message)
    {
        this.message = System.currentTimeMillis() + ": " + message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
