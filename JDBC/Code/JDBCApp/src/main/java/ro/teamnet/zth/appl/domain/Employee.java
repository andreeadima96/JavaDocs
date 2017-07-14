package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.em.ColumnInfo;
import ro.teamnet.zth.api.em.Condition;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.database.DBManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreea.Dima on 7/14/2017.
 */
public class Employee {
    static ArrayList<Employee> searchDepartments(String s) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Connection conn = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(Employee.class);
        List<ColumnInfo> columns = EntityUtils.getColumns(Employee.class);
        ArrayList<Employee> list = new ArrayList<Employee>();
        String query = "SELECT * FROM " + tableName + " INNER JOIN " + EntityUtils.getTableName(Department.class) + " ON " +
                "employees.department_id = " + EntityUtils.getTableName(Department.class) + ".department_id" + " WHERE " +
                EntityUtils.getTableName(Department.class) + ".department_name like '%" + s + "%'";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            Employee instance = null;

            if (result.next()) {
                instance = new Employee();

                for (ColumnInfo c : columns) {
                    Field f = instance.getClass().getDeclaredField(c.getColumnName());
                    f.setAccessible(true);
                    f.set(instance, EntityUtils.castFromSqlType(result.getObject(c.getDbColumnName()), f.getType()));
                }
                list.add(instance);
            }
        } catch (SQLException e) {
            System.out.println("Error at connection");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
        return list;
    }

    public static void main(String[] args) throws NoSuchFieldException, SQLException, ClassNotFoundException, IllegalAccessException {
        ArrayList<Employee> emp = searchDepartments("ion");
    }
}
