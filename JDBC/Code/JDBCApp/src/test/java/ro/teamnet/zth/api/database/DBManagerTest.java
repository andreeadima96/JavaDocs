package ro.teamnet.zth.api.database;

import org.junit.Test;
import ro.teamnet.zth.database.DBManager;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Andreea.Dima on 7/13/2017.
 */
public class DBManagerTest {
    @Test
    public void getConnectionTest() throws SQLException, ClassNotFoundException {
        assertNotNull("getConnectionTest: ", DBManager.getConnection());
    }

    @Test
    public  void checkConnectionTest() throws SQLException, ClassNotFoundException {
        assertTrue(DBManager.checkConnection(DBManager.getConnection()));
    }

}
