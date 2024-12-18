import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection connect() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=HOSPITAL;encrypt=false;integratedSecurity=true";
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}