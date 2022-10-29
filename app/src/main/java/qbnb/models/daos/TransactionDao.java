package qbnb.models;

import com.google.gson.*;
import java.util.HashMap;
import java.util.Optional;

/** Uses DAO API to act as a persistence layer for Transaction domain models. */
public class TransactionDao implements Dao<Transaction> {

  private HashMap<Long, Transaction> transactions = new HashMap<Long, Transaction>();

  public TransactionDao(Transaction trans) {
    this.save(trans);
  }

  public TransactionDao() {
    this.save(new Transaction());
  }

  public boolean serialize(String transactionPath) {
    return write(transactionPath);
  }

  public static TransactionDao deserialize(String transactionPath) {
    String result = Dao.read(transactionPath);
    return gson.fromJson(result, TransactionDao.class);
  }

  public Optional<Transaction> get(long id) {
    return Optional.ofNullable(transactions.get(id));
  }

  public HashMap<Long, Transaction> getAll() {
    return transactions;
  }

  public void save(Transaction trans) {
    transactions.put(trans.getId(), trans);
  }

  public void update(Transaction trans, String[] params) {
    // transactions.replace(trans, new Transaction((Long) params[0], (Long) params[1], (Long)
    // params[2], (float) params[3], LocalDate.parse(params[4]), LocalDate.parse(params[5])));
  }

  public void delete(Transaction trans) {
    transactions.remove(trans.getId());
  }
}
