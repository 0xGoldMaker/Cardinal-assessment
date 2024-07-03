import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class HolidayUtility {
    private static final Set<Date> holidays = new HashSet<>();

    static {
        holidays.add(new Date(2024 - 1900, Calendar.JULY, 4)); // Independence Day
        holidays.add(new Date(2024 - 1900, Calendar.SEPTEMBER, 1)); // Labor Day
    }

    public static boolean isHoliday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        
        if (dayOfWeek == Calendar.SATURDAY) {
            calendar.add(Calendar.DATE, -1);
        } else if (dayOfWeek == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, 1);
        }
        
        return holidays.contains(calendar.getTime());
    }

    public static boolean isWeekendOrHoliday(Date checkoutDate, Date dueDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);

        while (calendar.getTime().before(dueDate) || calendar.getTime().equals(dueDate)) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY || isHoliday(calendar.getTime())) {
                return true;
            }
            calendar.add(Calendar.DATE, 1);
        }
        return false;
    }
}
