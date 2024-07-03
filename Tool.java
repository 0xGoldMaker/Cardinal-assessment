public class Tool {
    private String toolCode;
    private String toolType;
    private String brand;
    private double dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    public Tool(String toolCode, String toolType, String brand, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getToolCode() { return toolCode; }
    public String getToolType() { return toolType; }
    public String getBrand() { return brand; }
    public double getDailyCharge() { return dailyCharge; }
    public boolean isWeekdayCharge() { return weekdayCharge; }
    public boolean isWeekendCharge() { return weekendCharge; }
    public boolean isHolidayCharge() { return holidayCharge; }

    @Override
    public String toString() {
        return String.format("Tool [code=%s, type=%s, brand=%s]", toolCode, toolType, brand);
    }
}
