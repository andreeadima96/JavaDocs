package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Andreea.Dima on 7/14/2017.
 */
public class LocationDaoTest {
    LocationDao ll = new LocationDao();
    long index = 20;
    long maxIndex = ll.entman.getNextIdVal("locations", "location_id");

    public LocationDaoTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    @Test
    public void findByIdTest() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        assertNotNull(ll.entman.findById(Location.class, index));
    }

    @Test
    public void getNextIdValTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        assertEquals(maxIndex, ll.entman.getNextIdVal("locations", "location_id"));
    }

    @Test
    public void insertTest() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Location newl = new Location();
        newl.setCity("Bucharest");
        newl.setId((long) 1800);
        newl.setPostalCode("06210256");
        newl.setStateProvince("RO");
        newl.setStreetAddress("Splaiul Independentei");
        assertNotNull(ll.entman.insert(newl));

    }

    @Test
    public void findAllTest() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Location newl = new Location();
        assertNotNull(ll.entman.findAll(newl.getClass()));
    }

    @Test
    public void deleteTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Location del = new Location();
       del.setId((long) 1700);
        del.setStreetAddress("2004 Charade Rd");
        del.setStateProvince("Washington");
        del.setPostalCode("98199");
        del.setCity("Seattle");
        ll.delete(del);

        assertNull(ll.entman.findById(del.getClass(), del.getId()));
    }
}
