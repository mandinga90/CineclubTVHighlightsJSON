package example.cineclubtvhighlightsjson.functional;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by mlu on 06.05.2017.
 */

public class DateTimeHelper {
    public static Date parseJSONDateTime(String dateString) {
        if (dateString == null) return null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        if (dateString.contains("T")) dateString = dateString.replace('T', ' ');
        if (dateString.contains("Z")) dateString = dateString.replace(".000", "");
        if (dateString.contains("Z")) dateString = dateString.replace("Z", "+0000");
        else
            dateString = dateString.substring(0, dateString.lastIndexOf(':')) + dateString.substring(dateString.lastIndexOf(':')+1);
        try {
            return fmt.parse(dateString);
        }
        catch (ParseException e) {
            Log.e(DateTimeHelper.class.getSimpleName(), "Could not parse JSON datetime: " + dateString);
            return null;
        }
    }
    public static boolean isToday(Calendar date){
        Calendar today = GregorianCalendar.getInstance();
        return (    date.get( Calendar.YEAR ) == today.get( Calendar.YEAR )
                 && date.get( Calendar.MONTH ) == today.get( Calendar.MONTH )
                 && date.get( Calendar.DAY_OF_MONTH ) == today.get( Calendar.DAY_OF_MONTH ) );
    }
}
