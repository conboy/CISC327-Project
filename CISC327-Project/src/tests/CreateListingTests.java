package tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreateListingTests {

    /**
     * Tests requirement R4-1: The title of the product has to be alphanumeric-only.
     * Additionally, spaces are allowed only if it is not as prefix and suffix.
     */
    @Test
    public void alphanumericTitleTest () {
        Assert.fail();
    }

    /**
     * Tests requirement R4-2: The title of the product is no longer than 80 characters.
     */
    @Test
    public void titleLengthTest () {
        Assert.fail();
    }

    /**
     * Tests requirement R4-3: The description is minimum length of 20 characters and a maximum of 2000 characters.
     */
    @Test
    public void descriptionLengthTest () {
        Assert.fail();
    }

    /**
     * Tests requirement R4-4: Description has to be longer than the product's title.
     */
    @Test
    public void descriptionLongerThanTitleTest () {
        Assert.fail();
    }

    /**
     * Tests requirement R4-5: Price has to be within the range [10, 10000].
     */
    @Test
    public void priceWithinRangeTest () {
        Assert.fail();
    }

    /**
     * Tests requirement R4-6: modified_date must be after 2021-01-02 and before 2025-01-02.
     */
    @Test
    public void dateWithinRangeTest () {
        Assert.fail();
    }

    /**
     * Tests requirement R4-7A: ownerID cannot be empty.
     */
    @Test
    public void ownerNonEmptyTest () {
        Assert.fail();
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
