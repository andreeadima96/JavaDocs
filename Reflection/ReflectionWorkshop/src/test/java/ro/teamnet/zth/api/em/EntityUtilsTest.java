package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andreea.Dima on 7/12/2017.
 */
public class EntityUtilsTest {
    @Test
    public void testGetTableNameMethod(){
        String tableName = EntityUtils.getTableName(Employee.class);
        assertEquals("Table name should be employees!", "employees",tableName);
    }
    @Test
    public void testGetColumns(){
        int tableSize = EntityUtils.getColumns(Employee.class).size();
        assertEquals("Table size should be 5!",5,tableSize);
    }

   /* public void testCastFromSqlType(){
        ("The result shoul be Integer: 5",EntityUtils.castFromSqlType(new BigDecimal(5), java.lang.Integer));
    }*/
}
