package ro.teamnet.zth.api.em;

import ro.teamnet.zth.database.DBManager;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andreea.Dima on 7/13/2017.
 */
public class EntityManagerImpl<T> implements EntityManager<T> {

    @Override
    public Object findById(Class entityClass, long id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection conn = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);

        Condition cond = new Condition();
        for (ColumnInfo c : columns) {
            if (c.isId()) {
                cond.setColumnName(c.getDbColumnName());
                cond.setValue(id);
            }
        }

        QueryBuilder query = new QueryBuilder();
        query.setTableName(tableName);
        query.addQueryColumns(columns);
        query.setQueryType(QueryType.SELECT);
        query.addCondition(cond);
        String q = query.createQuery();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(q);
            T instance = null;

            if (result.next()) {
                instance = (T) entityClass.newInstance();

                for (ColumnInfo c : columns) {
                    Field f = instance.getClass().getDeclaredField(c.getColumnName());
                    f.setAccessible(true);
                    f.set(instance, EntityUtils.castFromSqlType(result.getObject(c.getDbColumnName()), f.getType()));
                }
            }
            return instance;
        } catch (SQLException e) {
            System.out.println("Error at connection");
            return null;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

    @Override
    public long getNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection conn = DBManager.getConnection();
        String query = "SELECT MAX( " + columnIdName + ") FROM " + tableName;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            result.next();
            return result.getInt("MAX(" + columnIdName + ")") + 1;

        } catch (SQLException e) {
            System.out.println("Error at connection");
            return -1;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

    @Override
    public Object insert(Object entity) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection conn = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
        // List<Field> fields = EntityUtils.getFieldsByAnnotations(entity.getClass(), Column.class);
        long id = -1;

        for (ColumnInfo c : columns) {
            if (c.isId()) {
                //cond.setColumnName(c.getColumnName());
                id = getNextIdVal(tableName, c.getDbColumnName());
                c.setValue(id);

            } else {
                Field f = entity.getClass().getDeclaredField(c.getColumnName());
                f.setAccessible(true);
                c.setValue(f.get(entity));
            }
        }

        QueryBuilder query = new QueryBuilder();
        query.setTableName(tableName);
        query.addQueryColumns(columns);
        query.setQueryType(QueryType.INSERT);
        String q = query.createQuery();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            int result = stmt.executeUpdate(q);
            if (result <= 0) {
                System.out.println("No rows added");
            }

            return findById(entity.getClass(), id);
        } catch (SQLException e) {
            System.out.println("Error at connection");
            return null;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }

    }

    @Override
    public List findAll(Class entityClass) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Connection conn = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);

        QueryBuilder query = new QueryBuilder();
        query.setTableName(tableName);
        query.addQueryColumns(columns);
        query.setQueryType(QueryType.SELECT);

        String q = query.createQuery();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(q);
            ArrayList<T> list = new ArrayList<T>();
            T instance;
            while (result.next()) {
                instance = (T) entityClass.newInstance();

                for (ColumnInfo c : columns) {
                    Field f = instance.getClass().getDeclaredField(c.getColumnName());
                    f.setAccessible(true);
                    f.set(instance, EntityUtils.castFromSqlType(result.getObject(c.getDbColumnName()), f.getType()));
                    list.add(instance);
                }
            }

            return list;
        } catch (SQLException e) {
            System.out.println("Error at connection");
            return null;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

    Object update(T entity) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Connection conn = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

        Condition cond = new Condition();
        for (ColumnInfo c : columns) {
            Field fo = entity.getClass().getDeclaredField(c.getColumnName());
            fo.setAccessible(true);
            c.setValue(fo.get(entity));

            if (c.isId()) {
                cond.setValue(fo.get(entity));
                cond.setColumnName(c.getDbColumnName());
            }
        }

        QueryBuilder query = new QueryBuilder();
        query.setTableName(tableName);
        query.addQueryColumns(columns);
        query.setQueryType(QueryType.UPDATE);
        query.addCondition(cond);

        String q = query.createQuery();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            int result = stmt.executeUpdate(q);
            if (result <= 0) {
                System.out.println("No rows updated");
            }

            return findById(entity.getClass(), (Long) cond.getValue());
        } catch (SQLException e) {
            System.out.println("Error at connection");
            return null;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

    public void delete(Object entity) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Connection conn = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

        Condition cond = new Condition();
        for (ColumnInfo c : columns) {
            Field f = entity.getClass().getDeclaredField(c.getColumnName());
            f.setAccessible(true);
            c.setValue(f.get(entity));

            if (c.isId()) {
                cond.setValue(f.get(entity));
                cond.setColumnName(c.getDbColumnName());
            }
        }

        QueryBuilder query = new QueryBuilder();
        query.setTableName(tableName);
        query.addQueryColumns(columns);
        query.setQueryType(QueryType.DELETE);
        query.addCondition(cond);

        String q = query.createQuery();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            int result = stmt.executeUpdate(q);
           if (result <= 0) {
                System.out.println("No rows deleted");
            }
        } catch (SQLException e) {
            System.out.println("Error at connection");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

    List<T> findByParams(Class<T> entityClass, Map<String, Object> params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Connection conn = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);

        ArrayList<Condition> conditions = new ArrayList<Condition>();
        for(Map.Entry<String,Object> entry: params.entrySet()){
            Condition cond = new Condition();
            cond.setColumnName(entry.getKey());
            cond.setValue(entry.getValue());
            conditions.add(cond);
        }
        QueryBuilder query = new QueryBuilder();
        query.setTableName(tableName);
        query.addQueryColumns(columns);
        query.setQueryType(QueryType.SELECT);
        for(Condition c: conditions){
            query.addCondition(c);
        }
        ArrayList<T> resultList = new ArrayList<T>();
        String q = query.createQuery();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(q);
            T instance;

            while (result.next()) {
                instance = (T) entityClass.newInstance();

                for (ColumnInfo c : columns) {
                    Field f = instance.getClass().getDeclaredField(c.getColumnName());
                    f.setAccessible(true);
                    f.set(instance, EntityUtils.castFromSqlType(result.getObject(c.getDbColumnName()), f.getType()));

                }
                resultList.add(instance);
            }

            return resultList;
        } catch (SQLException e) {
            System.out.println("Error at connection");
            return null;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }
}
