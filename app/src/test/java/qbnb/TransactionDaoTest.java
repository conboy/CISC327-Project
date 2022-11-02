package qbnb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qbnb.models.daos.TransactionDao;

public class TransactionDaoTest {
  TransactionDao dao = new TransactionDao();

  @Test
  public void toDB() {
    Assertions.assertTrue(dao.serialize(AppConf.TEST_PATH));
  }

  /**
   * TODO: MAKE THESE WORK ! @Test public void fromDB() {
   * Assertions.assertNotNull(TransactionDao.deserialize(AppTest.TEST_PATH)); } @Test public void
   * toAndFromDB() { dao.serialize(AppTest.TEST_PATH); TransactionDao dao1 =
   * TransactionDao.deserialize(AppTest.TEST_PATH);
   *
   * <p>TransactionDao newDao = new TransactionDao(); newDao.serialize(AppTest.TEST_PATH);
   * TransactionDao dao2 = TransactionDao.deserialize(AppTest.TEST_PATH);
   *
   * <p>Assertions.assertTrue(dao1.equals(dao2)); }
   */
}
