package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingle {

    private static Connection connection;

    private ConnectionSingle(){

    }

    public static Connection getConnection() throws SQLException {
        if(connection == null){
            connection = initConnection();
        } else if (connection.isClosed() && connection != null){
            connection = initConnection();
        }
        return connection;
    }

    private static Connection initConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/projeto_3", "postgres", "zkdPOSTGRESQL00121521");
    }


}
