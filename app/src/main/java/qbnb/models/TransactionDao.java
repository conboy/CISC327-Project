package qbnb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import com.google.gson.*;

/** Uses DAO API to act as a persistence layer for Transaction domain models. */
public class TransactionDao implements Dao<Transaction> {

  private List<Transaction> transactions = new ArrayList<Transaction>();

 /* private Gson gson = new Gson();
           new GsonBuilder()
                .registerTypeHierarchyAdapter(Transaction.class, new TransactionAdapter())
                .registerTypeHierarchyAdapter(LocalDate.class, new LocalDateAdapter())
                .create(); */

  public TransactionDao(Transaction trans) {
    transactions.add(trans);
  }

  /*
  public String serialize() {
        return gson.toJson(transactions);
  }
  */

  public static TransactionDao deserialize(String json) {
        return gson.fromJson(json, TransactionDao.class);
  }

  public Optional<Transaction> get(long id) {
    return Optional.ofNullable(transactions.get((int) id));
  }

  public List<Transaction> getAll() {
    return transactions;
  }

  public void save(Transaction trans) {
    transactions.add(trans);
  }

  public void update(Transaction trans, String[] params) {
        int x = 1+1;
  }

  public void delete(Transaction trans) {
    transactions.remove(trans);
  }
}
