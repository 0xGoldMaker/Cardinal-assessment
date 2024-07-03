import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ToolRentalService {
    private Map<String, Tool> tools = new HashMap<>();

    public ToolRentalService() {
        tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, false));
        tools.put("LADW", new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false));
        tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));
        tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false));
    }

    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, Date checkoutDate) throws IllegalArgumentException {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        }

        Tool tool = tools.get(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Invalid tool code");
        }

        Date dueDate = new Date(checkoutDate.getTime() + (rentalDays * 24 * 60 * 60 * 1000L));
        int chargeDays = calculateChargeDays(checkoutDate, dueDate, tool);
        double preDiscountCharge = chargeDays * tool.getDailyCharge();
        double discountAmount = preDiscountCharge * (discountPercent / 100.0);
        double finalCharge = preDiscountCharge - discountAmount;

        return new RentalAgreement(tool, rentalDays, discountPercent, checkoutDate, dueDate, preDiscountCharge, discountAmount, finalCharge);
    }

    private int calculateChargeDays(Date checkoutDate, Date dueDate, Tool tool) {
        int chargeDays = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);

        while (!calendar.getTime().after(dueDate)) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            boolean isHoliday = HolidayUtility.isHoliday(calendar.getTime());

            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                if (dayOfWeek == Calendar.SATURDAY && tool.isWeekendCharge()) {
                    chargeDays++;
                }
                if (dayOfWeek == Calendar.SUNDAY && tool.isWeekendCharge()) {
                    chargeDays++;
                }
            } else {
                if (!isHoliday) {
                    if (tool.isWeekdayCharge()) {
                        chargeDays++;
                    }
                } else {
                    if (tool.isHolidayCharge()) {
                        chargeDays++;
                    }
                }
            }
            calendar.add(Calendar.DATE, 1);
        }
        return chargeDays;
    }
}
