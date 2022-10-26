package qbnb.models;

import com.google.gson.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Uses DAO API to act as a persistence layer for Transaction domain models. */
public class TransactionDao implements Dao<Transaction> {

  private List<Transaction> transactions = new ArrayList<Transaction>();

  public TransactionDao(Transaction trans) {
    transactions.add(trans);
  }

  public TransactionDao() {
    transactions.add(new Transaction());
  }

  public boolean serialize(String transactionPath) {
    return write(transactionPath);
  }

  public static TransactionDao deserialize(String transactionPath) {
    String result = Dao.read(transactionPath);
    return gson.fromJson(result, TransactionDao.class);
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
    int x = 1 + 1;
  }

  public void delete(Transaction trans) {
    transactions.remove(trans);
  }
}
