package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Andreea.Dima on 7/13/2017.
 */
public class EntityManagerImplTest {

    EntityManagerImpl ent = new EntityManagerImpl();
    long index = 20;
    long maxIndex = ent.getNextIdVal("departments", "department_id");

    public EntityManagerImplTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    @Test
    public void findByIdTest() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        assertNotNull(ent.findById(Department.class, index));
    }

    @Test
    public void getNextIdValTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        assertEquals(maxIndex, ent.getNextIdVal("departments", "department_id"));
    }

    @Test
    public void insertTest() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Department newd = new Department();
        newd.setDepartmentName("HR");
        newd.setLocation((long) 1800);
        assertNotNull(ent.insert(newd));

    }

    @Test
    public void findAllTest() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Department newd = new Department();
        assertNotNull(ent.findAll(newd.getClass()));
    }

    @Test
    public void updateTest() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Department newd = new Department();
        newd.setDepartmentName("IT");
        newd.setLocation((long) 2000);
        newd.setId((long) 250);
        // assertNotNull(ent.insert(newd));
        assertEquals(newd, ent.update(newd));
    }

    @Test
    public void deleteTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Department del = new Department();
        del.setId((long) 296);
        del.setDepartmentName("IT");
        del.setLocation((long) 1700);
        ent.delete(del);
        assertNull(ent.findById(del.getClass(), del.getId()));
    }

    @Test
    public void findByParamsTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Department dep = new Department();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("location_id", 2000);

        dep.setId((long) 250);
        dep.setLocation((long) 2000);
        dep.setDepartmentName("IT");
        //assertNotNull(ent.findByParams(dep.getClass(),params));
        ArrayList<Department> list = new ArrayList<Department>();
        list.add(dep);
        assertEquals(list, ent.findByParams(dep.getClass(), params));

    }
}
