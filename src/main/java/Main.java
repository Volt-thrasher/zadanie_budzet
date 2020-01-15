import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.transaction();
        transactionDao.create(new Transaction("przychod", "zakupy Lidl Zmigrodzka", 64, "2020-01-04"));
        transactionDao.create(new Transaction("wydatek", "zakupy Aldi", 37, "2020-01-08"));
        transactionDao.create(new Transaction("przychod", "totolotek", 800, "2020-01-05"));
        transactionDao.update(new Transaction(1, "wydatek", "zakupy Lidl Zmigrodzka", 64, "2020-01-04"));
        transactionDao.read("wydatek");
        transactionDao.read("przychod");

    }
}
