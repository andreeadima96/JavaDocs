package ro.teamnet.zth.appl;

        import org.junit.Test;
        import ro.teamnet.zth.appl.dao.DepartmentDao;
        import ro.teamnet.zth.appl.domain.Department;

        import java.sql.SQLException;


        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertNotNull;
        import static org.junit.Assert.assertNull;

/**
 * Created by Andreea.Dima on 7/14/2017.
 */
public class DepartmentDaoTest {
    DepartmentDao dd = new DepartmentDao();
    long index = 20;
    long maxIndex = dd.entman.getNextIdVal("departments", "department_id");

    public DepartmentDaoTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    @Test
    public void findByIdTest() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        assertNotNull(dd.entman.findById(Department.class, index));
    }

    @Test
    public void getNextIdValTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        assertEquals(maxIndex, dd.entman.getNextIdVal("departments", "department_id"));
    }

    @Test
    public void insertTest() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Department newd = new Department();
        newd.setDepartmentName("HR");
        newd.setLocation((long) 1800);
        assertNotNull(dd.entman.insert(newd));

    }

    @Test
    public void findAllTest() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Department newd = new Department();
        assertNotNull(dd.entman.findAll(newd.getClass()));
    }

    @Test
    public void deleteTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Department del = new Department();
        del.setId((long) 296);
        del.setDepartmentName("IT");
        del.setLocation((long) 1700);
        dd.delete(del);
        assertNull(dd.entman.findById(del.getClass(), del.getId()));
    }
}
