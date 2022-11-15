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

  /**
   * From a json file, deserializes and builds the TransactionDao
   *
   * @param json - data to be deserialized
   * @return TransactionDao - Built TransactionDao
   */
  public static TransactionDao deserialize(String json) {
    return gson.fromJson(json, TransactionDao.class);
  }

  /**
   * Returns a transaction based on a transaction id
   *
   * @param id - id of the transaction to be retrieved
   * @return Optional<Transaction> - the transaction if found
   */
  public Optional<Transaction> get(long id) {
    return Optional.ofNullable(transactions.get((int) id));
  }

  /**
   * Returns a list of all transactions in the Dao
   *
   * @return List<Transaction> - list of transactions
   */
  public List<Transaction> getAll() {
    return transactions;
  }

  /**
   * Adds a transaction to the list of transactions
   *
   * @param trans - transaction to be added
   */
  public void save(Transaction trans) {
    transactions.add(trans);
  }

  /**
   * Update a given transaction from the list of transactions
   *
   * @param trans - transaction to be updated
   * @param params - parameters with
   */
  public void update(Transaction trans, String[] params) {
    int x = 1 + 1;
  }

  /**
   * Remove a transaction
   *
   * @param trans - transaction to be deleted
   */
  public void delete(Transaction trans) {
    transactions.remove(trans);
  }
}
