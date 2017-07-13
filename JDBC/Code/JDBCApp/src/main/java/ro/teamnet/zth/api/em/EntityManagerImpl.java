package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.database.DBManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreea.Dima on 7/13/2017.
 */
public class EntityManagerImpl<T> implements EntityManager<T>{
    Class<T> clazz;

    @Override
    public Object findById(Class entityClass, long id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection conn = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);

        Condition cond = new Condition();
        for(ColumnInfo c: columns){
            if(c.isId()){
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
        try{
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(q);
            T instance = null;

            if(result.next()){
                instance= (T) entityClass.newInstance();

                for(ColumnInfo c: columns) {
                    Field f = instance.getClass().getDeclaredField(c.getColumnName());
                    f.setAccessible(true);
                    f.set(instance,EntityUtils.castFromSqlType(result.getObject(c.getDbColumnName()), f.getType()));
                }
            }else{
                System.out.println("result hasn't more elements");
            }

            return instance;
        }
        catch (SQLException e) {
            System.out.println("Error at connection");
            return null;
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }

    }


    @Override
    public long getNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection conn = DBManager.getConnection();
        String query = "SELECT MAX( " + columnIdName + ") FROM " + tableName;
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            //long r = result.getInt(columnIdName);
            return result.getInt(columnIdName) + 1;
        }
        catch (SQLException e) {
            System.out.println("Error at connection");
            return -1;
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }

    @Override
    public Object insert(Object entity) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection conn = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entity.getClass(), Column.class);
        long id = -1;
        //Condition cond = new Condition();
        for(ColumnInfo c: columns){
            if(c.isId()){
                //cond.setColumnName(c.getColumnName());
                id = getNextIdVal(tableName,c.getColumnName());
                c.setValue(id);

            }else{
                Field f = entity.getClass().getDeclaredField(c.getColumnName());
                f.setAccessible(true);
                c.setValue(f.get(entity.getClass()));
            }
        }

        QueryBuilder query = new QueryBuilder();
        query.setTableName(tableName);
        query.addQueryColumns(columns);
        query.setQueryType(QueryType.INSERT);
        String q = query.createQuery();
        Statement stmt = null;
        try {
            stmt = conn.createStatement( );
            ResultSet result = stmt.executeQuery(q);

            return findById(entity.getClass(),id);
        }
        catch (SQLException e) {
            System.out.println("Error at connection");
            return null;
        }
        finally {
            if (stmt != null) { stmt.close(); }
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
            stmt = conn.createStatement( );
            ResultSet result = stmt.executeQuery(q);
            ArrayList<T> list = new ArrayList<T>();
            T instance;
            while(result.next()){
                instance= clazz.newInstance();

                for(ColumnInfo c: columns) {
                    Field f = instance.getClass().getDeclaredField(c.getColumnName());
                    f.setAccessible(true);
                    f.set(instance,EntityUtils.castFromSqlType(result.getObject(c.getDbColumnName()), f.getType()));
                    list.add(instance);
                }
            }

            return list;
        }
        catch (SQLException e) {
            System.out.println("Error at connection");
            return null;
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
}
