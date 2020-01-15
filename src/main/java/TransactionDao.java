import java.sql.*;
import java.util.Optional;

public class TransactionDao {
    private static final String USER = "root";
    private static final String PASS = "admin+123";
    private static final String URL = "jdbc:mysql://localhost:3306/budget?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
    private Connection connection;

    public void transaction() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    //CREATE

    public void create(Transaction transaction) {
        final String sql = "insert into transactions(typ, opis, kwota, data_transakcji) values(?,?,?,?)";
        try {
            PreparedStatement prepStmnt = connection.prepareStatement(sql);
            prepStmnt.setString(1, transaction.getType());
            prepStmnt.setString(2, transaction.getDescription());
            prepStmnt.setDouble(3, transaction.getAmount());
            prepStmnt.setString(4, transaction.getDate());
            prepStmnt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not get record");
            e.printStackTrace();
        }
    }

    //READ
    public Optional<Transaction> read(String type) {
        final String sql = "select id, typ, opis, kwota, data_transakcji from transactions where typ = ?";
        try {
            PreparedStatement prepStmnt = connection.prepareStatement(sql);
            prepStmnt.setString(1, type);
            ResultSet result = prepStmnt.executeQuery();
            if (result.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(result.getLong("id"));
                transaction.setType(result.getString("typ"));
                transaction.setDescription(result.getString("opis"));
                transaction.setAmount(result.getDouble("kwota"));
                transaction.setDate(result.getString("data_transakcji"));
                return Optional.of(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Transaction transaction) {
        final String sql = "update transactions set typ=?, opis=?, kwota=?, data_transakcji=? where id=?";
        try {
            PreparedStatement prepStmnt = connection.prepareStatement(sql);
            prepStmnt.setString(1, transaction.getType());
            prepStmnt.setString(2, transaction.getDescription());
            prepStmnt.setDouble(3, transaction.getAmount());
            prepStmnt.setString(4, transaction.getDate());
            prepStmnt.setLong(5, transaction.getId());
            prepStmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
