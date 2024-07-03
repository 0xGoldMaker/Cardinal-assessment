import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolRentalServiceTest {
    private ToolRentalService service;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

    @BeforeEach
    public void setUp() {
        service = new ToolRentalService();
    }

    /**
     * Test case to verify that an exception is thrown when the rental day count is less than 1.
     */
    @Test
    public void testInvalidRentalDays() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.checkout("JAKR", 0, 10, new Date());
        });
    }

    /**
     * Test case to verify that an exception is thrown when the discount percent is greater than 100.
     */
    @Test
    public void testInvalidDiscountPercent() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.checkout("JAKR", 5, 101, new Date());
        });
    }

    /**
     * Tool code "JAKR", checkout date "09/03/15", rental days 5, discount percent 101%.
     * This should throw an exception due to invalid discount percent.
     */
    @Test
    public void testScenario1() throws ParseException {
        Date checkoutDate = dateFormat.parse("09/03/15");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.checkout("JAKR", 5, 101, checkoutDate);
        });
    }

    /**
     * Tool code "LADW", checkout date "07/02/20", rental days 3, discount percent 10%.
     */
    @Test
    public void testScenario2() throws ParseException {
        Date checkoutDate = dateFormat.parse("07/02/20");
        RentalAgreement agreement = service.checkout("LADW", 3, 10, checkoutDate);
        agreement.printAgreement();
    }

    /**
     * Tool code "CHNS", checkout date "07/02/15", rental days 5, discount percent 25%.
     */
    @Test
    public void testScenario3() throws ParseException {
        Date checkoutDate = dateFormat.parse("07/02/15");
        RentalAgreement agreement = service.checkout("CHNS", 5, 25, checkoutDate);
        agreement.printAgreement();
    }

    /**
     * Tool code "JAKD", checkout date "09/03/15", rental days 6, discount percent 0%.
     */
    @Test
    public void testScenario4() throws ParseException {
        Date checkoutDate = dateFormat.parse("09/03/15");
        RentalAgreement agreement = service.checkout("JAKD", 6, 0, checkoutDate);
        agreement.printAgreement();
    }

    /**
     * Tool code "JAKR", checkout date "07/02/15", rental days 9, discount percent 0%.
     */
    @Test
    public void testScenario5() throws ParseException {
        Date checkoutDate = dateFormat.parse("07/02/15");
        RentalAgreement agreement = service.checkout("JAKR", 9, 0, checkoutDate);
        agreement.printAgreement();
    }

    /**
     * Tool code "JAKR", checkout date "07/02/20", rental days 4, discount percent 50%.
     */
    @Test
    public void testScenario6() throws ParseException {
        Date checkoutDate = dateFormat.parse("07/02/20");
        RentalAgreement agreement = service.checkout("JAKR", 4, 50, checkoutDate);
        agreement.printAgreement();
    }

}
