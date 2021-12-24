package Model;

public class DBtables {
    public DBtables(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private String tableName;

    @Override
    public String toString() {
        return "DBtables{" +
                "tableName='" + tableName + '\'' +
                '}';
    }
}
