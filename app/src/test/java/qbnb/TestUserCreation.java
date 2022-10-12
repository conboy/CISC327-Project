package qbnb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import qbnb.models.User;

public class TestUserCreation {
    User testUser1 = new User("testUser1@gmail.com", "testUser1", "Password1!", true);

    /**
     * Test if user is given $100.00 when they are created
     */
    @Test
    public void testStartingBalance() {
        Assertions.assertTrue(testUser1.getGuestAccount().getBalance() == 100.00);
    }

    /**
     * Test if user has empty postal code at account opening
     */
    @Test
    public void testStartingPOstalCode() {
        Assertions.assertTrue(testUser1.getAddress().getPostalZip() == null);
    }

    /**
     * Test if user has empty address at account opening
     */
    @Test
    public void testStartingAddress() {
        Assertions.assertTrue(testUser1.getAddress().getStreetNumber() == -1
                && testUser1.getAddress().getUnitNumber() == null && testUser1.getAddress().getStreetName() == null);
    }
}
