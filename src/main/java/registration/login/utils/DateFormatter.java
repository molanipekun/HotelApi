package registration.login.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {


    static SimpleDateFormat datePickerFormatter = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
    static SimpleDateFormat switchDateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static String format(Date date){
        return dateFormatter.format(date);
    }

    public static String pickerFormatDate(Date date){
        return datePickerFormatter.format(date);
    }

    public static Date  pickerFormatString(String date){
        Logger logger = LoggerFactory.getLogger(DateFormatter.class);

        Date date1 = null;
        try {
            date1 = datePickerFormatter.parse(date);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return date1;
    }

    public static String switchFormat(Date date){
        return switchDateFormatter.format(date);
    }

}




