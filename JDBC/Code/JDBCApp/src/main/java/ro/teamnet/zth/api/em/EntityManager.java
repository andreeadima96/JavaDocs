package ro.teamnet.zth.api.em;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andreea.Dima on 7/13/2017.
 */
public interface EntityManager<T> {
    T findById(Class<T> entityClass, long id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException;
   long getNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;
    T insert(T entity) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException;
    List<T> findAll(Class<T> entityClass) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException;

}
