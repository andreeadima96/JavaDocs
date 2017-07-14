package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andreea.Dima on 7/14/2017.
 */
public class LocationDao {
    public EntityManager entman = new EntityManager() {
        EntityManagerImpl emi = new EntityManagerImpl();

        @Override
        public Object findById(Class entityClass, long id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
            return emi.findById(Department.class, id);
        }

        @Override
        public long getNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
            return emi.getNextIdVal("locations", "location_id");
        }

        @Override
        public Object insert(Object entity) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
            return emi.insert((Location) entity);
        }

        @Override
        public List findAll(Class entityClass) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
            return emi.findAll(Location.class);
        }

    };

    public void delete(Object entity) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        EntityManagerImpl entmani = new EntityManagerImpl();
        entmani.delete((Location) entity);
    }
}
