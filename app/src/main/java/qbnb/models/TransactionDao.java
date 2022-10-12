package qbnb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Uses DAO API to act as a persistence layer for Transaction domain models. */
public class TransactionDao {

  private List<Transaction> transactions = new ArrayList<Transaction>();

  public TransactionDao(Transaction trans) {
    transactions.add(trans);
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

  public void update() {}

  public void delete(Transaction trans) {
    transactions.remove(trans);
  }
}