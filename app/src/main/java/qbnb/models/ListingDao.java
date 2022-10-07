package qbnb.models;

import models.Listing;
import models.Dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Uses DAO API to act as a persistence layer for Listing domain models. */
public class ListingDao implements Dao<Listing> {

    /* The list of all Listings present in the DAO. Will need to load from database at some point! */
    private List<Listing> listings = new ArrayList<Listing>();

    /* Gets a Listing at the specified point of the DAO list. */
    @Override
    public Optional get(long id) {
        return Optional.ofNullable(listings.get((int) id));
    }

    /* Return all Listings present in the DAO. Lists! */
    @Override
    public List getAll() {
        return listings;
    }

    /* Append a listing to the end of listings. */
    @Override
    public void save(Listing listing) {
        listings.add(listing);
    }

    /* Search through the DAO list, and update the specified listing if it is present.
    *  If the listing is not present, a warning is output to console. */
    @Override
    public void update(Listing listing, String[] params) {
        boolean found = false;
        for (int i = 0; i < listings.size(); i++) {
            if (listings.get(i).equals(listing)) {
                listings.set(i, new Listing(Integer.parseInt(params[0]), params[1], params[2], Double.parseDouble(params[3]), LocalDate.parse(params[4]), Integer.parseInt(params[5])));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Listing was not found! No object in the DAO was updated.");
        }
    }

    /* Delete a listing from the DAO. */
    @Override
    public void delete(Listing l) {
        listings.remove(l);
    }
}
