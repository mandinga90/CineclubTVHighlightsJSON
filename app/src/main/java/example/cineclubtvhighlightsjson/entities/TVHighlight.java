package example.cineclubtvhighlightsjson.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import example.cineclubtvhighlightsjson.functional.DateTimeHelper;

/**
 * Created by mlu on 05.05.2017.
 */

public class TVHighlight {
    private String dateTime;
    private String tvChannel;
    private String title;
    private String originalTitle;
    private String startDate;
    private String startTyp;
    private int advertisingInMinutes;
    private String link;
    private String imageLink;
    private String genre1;
    private String genre2;
    private String ratingInPercentage;
    private String description;

    public Calendar getDateTime() {
        Date date = DateTimeHelper.parseJSONDateTime(dateTime);
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public String getTvChannel() {
        return tvChannel;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartTyp() {
        return startTyp;
    }

    public int getAdvertisingInMinutes() {
        return advertisingInMinutes;
    }

    public String getLink() {
        return link;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getGenre1() {
        return genre1;
    }

    public String getGenre2() {
        return genre2;
    }

    public String getRatingInPercentage() {
        return ratingInPercentage;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "TVHighlight{" +
                "dateTime='" + dateTime + '\'' +
                ", tvChannel='" + tvChannel + '\'' +
                ", title='" + title + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTyp='" + startTyp + '\'' +
                ", advertisingInMinutes=" + advertisingInMinutes +
                ", link='" + link + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", genre1='" + genre1 + '\'' +
                ", genre2='" + genre2 + '\'' +
                ", ratingInPercentage='" + ratingInPercentage + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
