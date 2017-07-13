package ro.teamnet.zth.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Andreea.Dima on 7/13/2017.
 */

public class DBManager {
    static String CONNECTION_STRING= "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":xe";

    private DBManager(){
        throw new UnsupportedOperationException();
    }

    private static void registerDriver() throws ClassNotFoundException {
        Class.forName(DBProperties.DRIVER_CLASS);
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        registerDriver();

       return DriverManager.getConnection(CONNECTION_STRING,DBProperties.USER,DBProperties.PASS);
    }

    public static boolean checkConnection(Connection connection) throws SQLException, ClassNotFoundException {
        try (Statement stmt = connection.createStatement( )){
            String query = "SELECT 1 FROM DUAL";

            return stmt.execute(query);
        }
        catch (SQLException e) {
            System.out.println("Error at connection");
            return false;
        }
    }
}
