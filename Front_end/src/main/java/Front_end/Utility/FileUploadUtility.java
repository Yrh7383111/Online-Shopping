package Front_end.Utility;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;



public class FileUploadUtility
{
    // Private
    private static final String ABSOLUTE_PATH = "/Users/JacksonYin/Desktop/Java/Online Shopping Cart/Online Shopping/Front_end/src/main/webapp/assets/images/";

    private static String REAL_PATH;

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);


    // Public
    public static void uploadFile(HttpServletRequest httpServletRequest, MultipartFile file, String code) throws IOException
    {
        String REAL_PATH = httpServletRequest.getSession().getServletContext().getRealPath("/assets/images/");
        logger.info(REAL_PATH);


        File realPathFile = new File(REAL_PATH);
        File absolutePathFile = new File(ABSOLUTE_PATH);
        File realPathImageFile = new File(REAL_PATH + code + ".jpg");
        File absolutePathImageFile = new File(ABSOLUTE_PATH + code + ".jpg");

//        // Create the directory if it does not exist
//        if (!realPathFile.exists())
//        {
//            realPathFile.mkdirs();
//        }
//        if (!absolutePathFile.exists())
//        {
//            absolutePathFile.mkdir();
//        }

        try
        {
            file.transferTo(realPathImageFile);
            file.transferTo(absolutePathImageFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}