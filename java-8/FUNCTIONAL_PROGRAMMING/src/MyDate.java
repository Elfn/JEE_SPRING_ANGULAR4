import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Elimane on Sep, 2017, at 11:14
 */
public class MyDate {

   private String dayName;
   private Date currentDate;
    private Date tomorrow;

    public MyDate() {
        this.currentDate = new Date();
        this.dayName = getCurrentDayName(currentDate);
        this.tomorrow = getTomorrowDate(currentDate);
    }



    public Date getCurrentDate(Date d)
    {

        Calendar c = Calendar.getInstance();
        c.setTime(d);
        Date cd = c.getTime();
        return cd;
    }

    public Date getTomorrowDate(Date d)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DAY_OF_YEAR,1);
        Date newDate = c.getTime();
        return newDate;
    }

    public String getCurrentDayName(Date d)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String res = sdf.format(d);
        return res;
    }

    public String getCurrentDayName()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        return sdf.format(currentDate);
    }

    public boolean isWeekEndDay(Date d)
    {
        Calendar c = Calendar.getInstance();

        return c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }
}
