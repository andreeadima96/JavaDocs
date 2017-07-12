package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andreea.Dima on 7/12/2017.
 */
public class QueryBuilder {
    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private List<Condition> conditions;
    private QueryType queryType;

    public QueryBuilder(){
        this.queryColumns = new ArrayList<ColumnInfo>();
        this.conditions = new ArrayList<Condition>();
    }
    public String getValueForQuery(Object value){
        if(value.getClass().equals("java.lang.String"))
            return "value";

       if(value.getClass().equals("java.lang.Date")){
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE(" + dateFormat.format((Date)value) + " mm-dd-YYYY ";
        }
        return value.toString();
    }

    public QueryBuilder addCondition(Condition condition){
        this.conditions.add(condition);
        return this;
    }

    public QueryBuilder setTabelName(Object tableName){
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns){
        this.queryColumns = queryColumns;
        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType){
        this.queryType = queryType;
        return this;
    }

    private String createSelectQuery(){
        StringBuilder st = new StringBuilder();
        if(this.queryType.equals("SELECT")){
            st.append("SELECT ");

            for(int i=0; i< this.conditions.size() -1; i++){
                st.append(this.getValueForQuery(this.conditions.get(i).getColumnName()));
                st.append(',');
            }
            st.append(this.conditions.get(this.conditions.size()));
            st.append(" FROM ");
            st.append(this.tableName.toString());
        }
        return st.toString();
    }

    private String createDeleteQuery(){
        StringBuilder s = new StringBuilder();
        if(this.queryType.equals("DELETE")){
            s.append("DELETE ");

            for(int i=0; i< this.conditions.size() -1; i++){
                s.append(this.getValueForQuery(this.conditions.get(i).getColumnName()));
                s.append(',');
            }
            s.append(this.conditions.get(this.conditions.size()));
            s.append(" FROM ");
            s.append(this.tableName.toString());
        }
        return s.toString();
    }


}
