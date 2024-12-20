import java.sql.SQLException;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = new DatabaseConnection().connect();
        new Hospital(connection);
    }
}