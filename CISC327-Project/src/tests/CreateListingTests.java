package tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
<<<<<<< HEAD
<<<<<<< HEAD
import models.Listing;

import java.time.LocalDate;
=======
>>>>>>> Test Skeletons for R$ implemented.
=======
import models.Listing;

import java.time.LocalDate;
>>>>>>> alphanumericTitleTest implemented.

public class CreateListingTests {

    /**
     * Tests requirement R4-1: The title of the product has to be alphanumeric-only.
     * Additionally, spaces are allowed only if it is not as prefix and suffix.
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Implemented Tests R2 through to R-7A
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-1' TO FUNCTION.
     */
    @Test
    public void alphanumericTitleTest () {
        //checking non-numeric characters - error with message R4-1 should be thrown
        String message1 = "";
<<<<<<< HEAD
        try {
            Listing x = new Listing(1,
                    "Quantum guy's £$@$!@£$$@! factory",
                    "A super fun factory of nonsense anc whimsy innit!",
                    100, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message1 = e.getMessage();
        }
        Assert.assertEquals("R4-1", message1);

        //checking space characters at the beginning of title - error with message R4-1 should be thrown
        String message2 = "";
        try {
            Listing x = new Listing(1,
                    "     Front space land",
                    "A super fun land of nonsense anc whimsy innit!",
                    100, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message2 = e.getMessage();
        }
        Assert.assertEquals("R4-1", message2);

        //checking space characters at the end of title - error with message R4-1 should be thrown
        String message3 = "";
        try {
            Listing x = new Listing(1,
                    "End space land    ",
                    "A super fun land of nonsense anc whimsy innit!",
                    100, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message3 = e.getMessage();
        }
        Assert.assertEquals("R4-1", message3);
=======
     */
    @Test
    public void alphanumericTitleTest () {
<<<<<<< HEAD
        Assert.fail();
>>>>>>> Test Skeletons for R$ implemented.
=======
=======
>>>>>>> alphanumericTitleTest implemented.
        try {
            Listing x = new Listing(1,
                    "Quantum guy's £$@$!@£$$@! factory",
                    "A super fun factory of nonsense anc whimsy innit!",
                    100, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message1 = e.getMessage();
        }
<<<<<<< HEAD
>>>>>>> alphanumericTitleTest implemented.
=======
        Assert.assertEquals("R4-1", message1);

        //checking space characters at the beginning of title - error with message R4-1 should be thrown
        String message2 = "";
        try {
            Listing x = new Listing(1,
                    "     Front space land",
                    "A super fun land of nonsense anc whimsy innit!",
                    100, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message2 = e.getMessage();
        }
        Assert.assertEquals("R4-1", message2);

        //checking space characters at the end of title - error with message R4-1 should be thrown
        String message3 = "";
        try {
            Listing x = new Listing(1,
                    "End space land    ",
                    "A super fun land of nonsense anc whimsy innit!",
                    100, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message3 = e.getMessage();
        }
        Assert.assertEquals("R4-1", message3);
>>>>>>> alphanumericTitleTest implemented.
    }

    /**
     * Tests requirement R4-2: The title of the product is no longer than 80 characters.
<<<<<<< HEAD
<<<<<<< HEAD
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-2' TO FUNCTION.
     */
    @Test
    public void titleLengthTest () {
        String message = "";
        try {
            String desc = "a".repeat(200); //simple way of writing an arbitrarily long description
            Listing x = new Listing(1,
                    "The Really Really Long Named Place Of Absolute Joy And Wonder And Merriment And Amusement which is better known as The Ultimate Funkodome of the 31st Century",
                    desc,
                    100, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("R4-2", message);
=======
     */
    @Test
    public void titleLengthTest () {
        Assert.fail();
>>>>>>> Test Skeletons for R$ implemented.
=======
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-2' TO FUNCTION.
     */
    @Test
    public void titleLengthTest () {
        String message = "";
        try {
            String desc = "a".repeat(200); //simple way of writing an arbitrarily long description
            Listing x = new Listing(1,
                    "The Really Really Long Named Place Of Absolute Joy And Wonder And Merriment And Amusement which is better known as The Ultimate Funkodome of the 31st Century",
                    desc,
                    100, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("R4-2", message);
>>>>>>> Implemented Tests R2 through to R-7A
    }

    /**
     * Tests requirement R4-3: The description is minimum length of 20 characters and a maximum of 2000 characters.
<<<<<<< HEAD
<<<<<<< HEAD
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-3' TO FUNCTION.
     */
    @Test
    public void descriptionLengthTest () {
        //testing if error is thrown for the title length < 20 case - 'R4-3' is expected error message.
        String message1 = "";
        try {
            Listing x = new Listing(1,
                    "Funland",
                    "It's funland!",
                    100, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message1 = e.getMessage();
        }
        Assert.assertEquals("R4-3", message1);

        //testing if error is thrown for the title length > 2000 case.
        String message2 = "";
        try {
            String desc = "bee".repeat(1000); //simple way of writing an arbitrarily long description
            Listing x = new Listing(1,
                    "The entire bee movie script",
                    desc,
                    10, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message2 = e.getMessage();
        }
        Assert.assertEquals("R4-3", message2);
=======
     */
    @Test
    public void descriptionLengthTest () {
        Assert.fail();
>>>>>>> Test Skeletons for R$ implemented.
=======
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-3' TO FUNCTION.
     */
    @Test
    public void descriptionLengthTest () {
        //testing if error is thrown for the title length < 20 case - 'R4-3' is expected error message.
        String message1 = "";
        try {
            Listing x = new Listing(1,
                    "Funland",
                    "It's funland!",
                    100, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message1 = e.getMessage();
        }
        Assert.assertEquals("R4-3", message1);

        //testing if error is thrown for the title length > 2000 case.
        String message2 = "";
        try {
            String desc = "bee".repeat(1000); //simple way of writing an arbitrarily long description
            Listing x = new Listing(1,
                    "The entire bee movie script",
                    desc,
                    10, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message2 = e.getMessage();
        }
        Assert.assertEquals("R4-3", message2);
>>>>>>> Implemented Tests R2 through to R-7A
    }

    /**
     * Tests requirement R4-4: Description has to be longer than the product's title.
<<<<<<< HEAD
<<<<<<< HEAD
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-4' TO FUNCTION.
     */
    @Test
    public void descriptionLongerThanTitleTest () {
        String message = "";
        try {
            Listing x = new Listing(1,
                    "Ultimate bogland",
                    "it's a bog",
                    100, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("R4-4", message);
=======
     */
    @Test
    public void descriptionLongerThanTitleTest () {
        Assert.fail();
>>>>>>> Test Skeletons for R$ implemented.
=======
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-4' TO FUNCTION.
     */
    @Test
    public void descriptionLongerThanTitleTest () {
        String message = "";
        try {
            Listing x = new Listing(1,
                    "Ultimate bogland",
                    "it's a bog",
                    100, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("R4-4", message);
>>>>>>> Implemented Tests R2 through to R-7A
    }

    /**
     * Tests requirement R4-5: Price has to be within the range [10, 10000].
<<<<<<< HEAD
<<<<<<< HEAD
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-5' TO FUNCTION.
     */
    @Test
    public void priceWithinRangeTest () {
        //testing if correct error is thrown for the price < 10 case.
        String message1 = "";
        try {
            Listing x = new Listing(1,
                    "Funland",
                    "It's funland!",
                    1, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message1 = e.getMessage();
        }
        Assert.assertEquals("R4-5", message1);

        //testing if error is thrown for the price > 10000 case.
        String message2 = "";
        try {
            Listing x = new Listing(1,
                    "Bunland",
                    "It's fun on the bun! - Futurama robot man",
                    10000000, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message2 = e.getMessage();
        }
        Assert.assertEquals("R4-5", message2);
=======
     */
    @Test
    public void priceWithinRangeTest () {
        Assert.fail();
>>>>>>> Test Skeletons for R$ implemented.
=======
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-5' TO FUNCTION.
     */
    @Test
    public void priceWithinRangeTest () {
        //testing if correct error is thrown for the price < 10 case.
        String message1 = "";
        try {
            Listing x = new Listing(1,
                    "Funland",
                    "It's funland!",
                    1, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message1 = e.getMessage();
        }
        Assert.assertEquals("R4-5", message1);

        //testing if error is thrown for the price > 10000 case.
        String message2 = "";
        try {
            Listing x = new Listing(1,
                    "Bunland",
                    "It's fun on the bun! - Futurama robot man",
                    10000000, LocalDate.now(), 1);
        }
        catch (IllegalArgumentException e) {
            message2 = e.getMessage();
        }
        Assert.assertEquals("R4-5", message2);
>>>>>>> Implemented Tests R2 through to R-7A
    }

    /**
     * Tests requirement R4-6: modified_date must be after 2021-01-02 and before 2025-01-02.
<<<<<<< HEAD
<<<<<<< HEAD
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-6' TO FUNCTION.
     */
    @Test
    public void dateWithinRangeTest () {
        //testing if correct error is thrown for the Date < 2021-01-02 case.
        String message1 = "";
        try {
            Listing x = new Listing(1,
                    "Funland",
                    "It's funland!",
                    100, LocalDate.parse("2021-01-01"), 1);
        }
        catch (IllegalArgumentException e) {
            message1 = e.getMessage();
        }
        Assert.assertEquals("R4-6", message1);

        //testing if correct error is thrown for the Date > 2025-01-02 case.
        String message2 = "";
        try {
            Listing x = new Listing(1,
                    "Funland",
                    "It's funland!",
                    10, LocalDate.parse("2025-01-03"), 1);
        }
        catch (IllegalArgumentException e) {
            message2 = e.getMessage();
        }
        Assert.assertEquals("R4-6", message2);
=======
     */
    @Test
    public void dateWithinRangeTest () {
        Assert.fail();
>>>>>>> Test Skeletons for R$ implemented.
=======
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-6' TO FUNCTION.
     */
    @Test
    public void dateWithinRangeTest () {
        //testing if correct error is thrown for the Date < 2021-01-02 case.
        String message1 = "";
        try {
            Listing x = new Listing(1,
                    "Funland",
                    "It's funland!",
                    100, LocalDate.parse("2021-01-01"), 1);
        }
        catch (IllegalArgumentException e) {
            message1 = e.getMessage();
        }
        Assert.assertEquals("R4-6", message1);

        //testing if correct error is thrown for the Date > 2025-01-02 case.
        String message2 = "";
        try {
            Listing x = new Listing(1,
                    "Funland",
                    "It's funland!",
                    10, LocalDate.parse("2025-01-03"), 1);
        }
        catch (IllegalArgumentException e) {
            message2 = e.getMessage();
        }
        Assert.assertEquals("R4-6", message2);
>>>>>>> Implemented Tests R2 through to R-7A
    }

    /**
     * Tests requirement R4-7A: ownerID cannot be empty.
<<<<<<< HEAD
<<<<<<< HEAD
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-7' TO FUNCTION.
     */
    @Test
    public void ownerNonEmptyTest () {
        String message = "";
        try {
            Listing x = new Listing(1,
                    "Ultimate bogland",
                    "it's a bog",
                    100, LocalDate.now(), -1);
        }
        catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("R4-7", message);
=======
     */
    @Test
    public void ownerNonEmptyTest () {
        Assert.fail();
>>>>>>> Test Skeletons for R$ implemented.
=======
     * REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-7' TO FUNCTION.
     */
    @Test
    public void ownerNonEmptyTest () {
        String message = "";
        try {
            Listing x = new Listing(1,
                    "Ultimate bogland",
                    "it's a bog",
                    100, LocalDate.now(), -1);
        }
        catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("R4-7", message);
>>>>>>> Implemented Tests R2 through to R-7A
    }

    /**
     * Tests requirement R4-7B: ownerID has to match an ID within the database.
     */
    @Test
    public void ownerExistsTest () {
        //TODO: cannot be validated without database integration!
        Assert.fail();
    }

    /**
     * Tests requirement R4-8: A user cannot create a listing with a title that is already in-use.
     */
    @Test
    public void sharedTitleTest () {
        //TODO: cannot be validated without database integration!
        Assert.fail();
    }
}
