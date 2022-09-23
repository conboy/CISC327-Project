package models;

import java.util.List;
import java.util.Optional;

/** Uses DAO API to act as a persistence layer for Transaction domain models. */
public class TransactionDao implements Dao<Transaction> {

  private List<Transaction> transactions = new ArrayList<>();

  public TransactionDao(Transaction trans) {
    transactions.add(trans);
  }

  @Override
  public Optional<Transaction> get(long id) {
    return Optional.ofNullable(transactions.get((int) id));
  }

  @Override
  public List<Transaction> getAll() {
    return transactions;
  }

  @Override
  public void save(Transaction trans) {
    transactions.add(trans);
  }

  public void update() {}

  @Override
  public void delete(Transaction trans) {
    transactions.remove(trans);
  }
}
