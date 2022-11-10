package qbnb.models.daos;

import com.google.gson.*;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;
import qbnb.models.*;

/** Uses DAO API to act as a persistence layer for Listing domain models. */
public final class ListingDao implements Dao<Listing> {

  // making notes bc this is a convoluted job
  // basically the dao doesn't need to invoke its own loads anymore. we just load the dao from json
  // instead of invoking a new instance.
  // pretty sure the json should save to the json each time a save, update or delete is made tho!

  // Helper class - correctly formats LocalDates into JSON
  static class LocalDateSerializer implements JsonSerializer<LocalDate> {
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
      return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
    }
  }

  // Another helper class - deserializes dates from JSON back into LocalDates
  static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
    public LocalDate deserialize(
        JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext)
        throws JsonParseException {
      return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
    }
  }

  /* The GSON objects that will help to serialize and deserialize objects respectively */
  // private Gson gsonSerial; -> this is now created at serialization, otherwise errors occur!
  private static final Gson gsonDeserial =
      new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).create();

  /* The list of all Listings present in the DAO. Will need to load from database at some point! */
  public HashMap<Long, Listing> listings = new HashMap<Long, Listing>();

  /* The location of the listings json file within the project */
  private static final String listingPath = "/db/listings.json";

  /* Serialize ListingDao into a JSON file so that they may be saved permanently! */
  public void serialize() {
    Gson gsonSerial =
        new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).create();
    boolean x = write(listingPath, gsonSerial);
  }

  /* Deserialize the listings JSON file into an instance of ListingDao, loading saved listings! */
  public static ListingDao deserialize() {
    String result = Dao.read(listingPath);
    ListingDao dao = gsonDeserial.fromJson(result, ListingDao.class);
    if (dao == null || dao.getAll().values().size() == 0) return new ListingDao();
    else return dao;
  }

  /* Gets a Listing at the specified point of the DAO list. */
  @Override
  public Optional<Listing> get(long id) {
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
    serialize();
  }

  /* Search through the DAO list, and update the specified listing if it is present.
   *  If the listing is not present, a warning is output to console.
   *  Honestly pretty useless for listing. If you want to change it just run UpdateListing. */
  @Override
  public void update(Listing listing, String[] params) {
    Optional<Listing> check = get(listing.getListingID());
    if (check.isPresent()) {
      // if the listing of the same ID is present, overwrite it!
      save(listing);
    } else {
      System.out.println("Listing was not found! No object in the DAO was updated.");
      // TODO: save the listing to the DAO if it is not already present!
    }
  }

  /* An alternate update method that bases updates on title.
   *  This works because titles are required to be unique!
   * TODO: test to see if this works with the socket implementation. */
  public void update(String title, String[] params) {
    boolean found = false;
    for (Listing listing : listings.values()) {
      if (listing.getTitle().equals(title)) {
        // If listing is found, update it through it's inbuilt update procedure.
        listing.UpdateListing(params[0], params[1], Double.parseDouble(params[2]));
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
    serialize();
  }

  /* Gets and returns a listing by ID. Provides the same functionality as get() but makes writing tests easier.
   *  TODO: Remove all instances of this in favour of the slightly more complicated get() method */
  public Listing getByID(long id) {
    for (Listing l : listings.values()) {
      if (l.getListingID() == id) {
        return l;
      }
    }
    return null;
  }

  /* Clear the JSON file completely. Might be necessary to prevent tests from leaking.
   *  Needs updating to the reworked DAO implementation, but is also unused so. lol */
  public void clearJSON() {
    try {
      listings = new HashMap<Long, Listing>();
      serialize();
    } catch (Exception e) {
      System.out.println("Writer Unable to find db/listings.json!");
    }
  }
}
