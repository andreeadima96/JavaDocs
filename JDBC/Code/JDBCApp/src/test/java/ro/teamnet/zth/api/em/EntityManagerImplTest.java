package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Andreea.Dima on 7/13/2017.
 */
public class EntityManagerImplTest {
    EntityManagerImpl ent = new EntityManagerImpl();
    long index =20;
    @Test
    public void findByIdTest() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        assertNotNull(ent.findById(Department.class,index));
    }
    @Test
    public void getNextIdValTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        assertEquals(271,ent.getNextIdVal("departments","department_id"));
    }


}
