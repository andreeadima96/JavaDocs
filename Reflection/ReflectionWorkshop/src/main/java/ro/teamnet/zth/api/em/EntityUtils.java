package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Andreea.Dima on 7/12/2017.
 */
public class EntityUtils {
    private EntityUtils(){
        new UnsupportedOperationException();
    }

    public static String getTableName(Class entity){

        if(entity.isAnnotationPresent(Table.class)) {
            Table t = (Table) entity.getAnnotation(Table.class);
            return t.name();
        }

        return entity.getName();
    }

    public static ArrayList<ColumnInfo> getColumns(Class entity){
        Field fields[] = entity.getDeclaredFields();
        ArrayList<ColumnInfo> info = new ArrayList<ColumnInfo>();

        for(int i =0; i < fields.length; i++){
            if(fields[i].isAnnotationPresent(Column.class)){
                    ColumnInfo c = new ColumnInfo();

                    c.setColumnName(fields[i].getName());
                    c.setColumnType(fields[i].getClass());
                Column columnName = fields[i].getAnnotation(Column.class);
                c.setDbColumnName(columnName.name());
                    c.setId(false);
                    info.add(c);
            }

            if( fields[i].isAnnotationPresent(Id.class)){
                ColumnInfo d = new ColumnInfo();

                d.setColumnName(fields[i].getName());
                d.setColumnType(fields[i].getClass());
                Id columnName = fields[i].getAnnotation(Id.class);
                d.setDbColumnName(columnName.name());
                d.setId(true);
                info.add(d);
            }
        }

        return  info;
    }

    public static Object castFromSqlType(Object value, Class wantedType){
        if(value instanceof BigDecimal){
            if(wantedType.getName().equals("java.lang.Integer"))
                return (Integer) value;

            if(wantedType.getName().equals("java.lang.Long"))
                return (Long) value;

            if(wantedType.getName().equals("java.lang.Float"))
                return (Float) value;

            if(wantedType.getName().equals("java.lang.Double"))
                return (Double) value;

        }
        return value;
    }

    public static ArrayList<Field> getFieldsByAnnotations(Class clazz, Class annotation){
        Field [] fields = clazz.getDeclaredFields();
        ArrayList<Field> result = new ArrayList<Field>();

        for(int i =0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(annotation)) {
                result.add(fields[i]);
            }
        }
        return result;
    }

    public static Object getSqlValue(Object object) throws NoSuchFieldException {
        if(object.getClass().isAnnotationPresent(Table.class)){
            Field id = object.getClass().getField("id");
            id.setAccessible(true);
            return id;
        }
        return object;
    }
}
