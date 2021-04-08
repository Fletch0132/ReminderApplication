package apps.nixonblkmnd.reminderapplication.Formats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//FORMAT DATES BETWEEN APPLICATION, UI, AND DATABASE
public class FormatDate {

    //CHANGE DATE FORMAT - YYYY-MM-DD
    public static String DateFormatYear(String dateIn) {
        //OUTPUT DATE
        Date out;
        //OUTPUT STRING TO BE RETURNED
        String dateOut = "";
        //DATE FORMATS - ORIGINAL FORMAT AND CHANGED FORMAT
        SimpleDateFormat dInput = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dOutput = new SimpleDateFormat("yyyy/MM/dd");

        //CHANGE ORIGINAL DATE'S FORMAT TO NEW FORMAT
        try{
            out = dInput.parse(dateIn);
            dateOut = dOutput.format(out);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return dateOut;
    }



    //CHANGE DATE FORMAT - DD-MM-YYYY
    public static String DateFormatDay(String dateIn){
        //OUTPUT DATE
        Date out;
        //OUTPUT STRING TO BE RETURNED
        String dateOut = "";
        //DATE FORMATS - ORIGINAL FORMAT AND CHANGED FORMAT
        SimpleDateFormat dInput = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat dOutput = new SimpleDateFormat("dd/MM/yyyy");

        //CHANGE ORIGINAL DATE'S FORMAT TO NEW FORMAT
        try{
            out = dInput.parse(dateIn);
            dateOut = dOutput.format(out);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return dateOut;
    }
}
