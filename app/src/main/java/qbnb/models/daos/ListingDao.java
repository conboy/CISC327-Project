package qbnb.models.daos;

import java.util.HashMap;
import java.util.Optional;
import qbnb.models.*;

/** Uses DAO API to act as a persistence layer for Listing domain models. */
public final class ListingDao implements Dao<Listing> {

  /* The list of all Listings present in the DAO. Will need to load from database at some point! */
  private static HashMap<Long, Listing> listings = new HashMap<Long, Listing>();

  /* Gets a Listing at the specified point of the DAO list. */
  @Override
  public Optional get(long id) {
    return Optional.ofNullable(listings.get(id));
  }

  /* Return all Listings present in the DAO. Lists! */
  @Override
  public HashMap<Long, Listing> getAll() {
    return listings;
  }

  /* Append a listing to the end of listings. */
  @Override
  public void save(Listing listing) {
    listings.put(listing.getListingID(), listing);
  }

  /* Search through the DAO list, and update the specified listing if it is present.
   *  If the listing is not present, a warning is output to console. */
  @Override
  public void update(Listing listing, String[] params) {
    // listings.replace(listing.getListingID(), listing, new Listing(Long.parseLong(params[0]),
    // params[1], Double.parseDouble(params[2]), LocalDate.parse(params[3]),
    // Long.parseLong(params[4])));
  }

  /* Delete a listing from the DAO. */
  @Override
  public void delete(Listing l) {
    listings.remove(l.getListingID());
  }

  /* Delete ALL listings from the DAO. TESTING ONLY */
  public static void deleteAll() {
    listings = new HashMap<Long, Listing>();
  }
}
