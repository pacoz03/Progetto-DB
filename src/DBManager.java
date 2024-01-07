import java.sql.*;
import java.util.*;


public class DBManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/campionato";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public DBManager()
    {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Connected to database");   
        } catch (Exception e) {
            System.err.println("Error while connecting to database.");
        }
    }

    public static Connection getConnection()
    {
        return connection;
    }

    public static Object[][] convertToObjectMatrix(List<Map<String, Object>> in,String... colNames){
        ArrayList<ArrayList<Object>> tempData = new ArrayList<ArrayList<Object>>();
        //Per ogni nodo della lista (tupla)
        for (Map<String, Object> row : in) {
            //Crea un ArrayList dove inserire tutti i valori della tupla
            ArrayList<Object> temp = new ArrayList<>();
            //Per ogni entry della mappa (coppia {nome colonna, valore})
            if(colNames.length == 0)
                for(Map.Entry<String, Object> entry : row.entrySet()){                
                        //Aggiungi il valore alla lista
                        System.out.println(entry.getKey() + " " + entry.getValue());
                        temp.add(entry.getValue());
                }
            else
                for(String colName : colNames){
                    temp.add(row.get(colName));
                }
            tempData.add(temp);
        }
        //Converti in una matrice di Object
        Object[][] out = new Object[tempData.size()][];
        int i = 0;
        for(ArrayList<Object> x : tempData) {
            out[i] = x.toArray();
            i++;
        }
        return out;
    }

    public static PreparedStatement createInsertQuery(String tableName, String[] columsNames) throws SQLException{
        String query = new String();
        //INSERT INTO tableName (name1,name2,.....)
        query = "INSERT INTO " + tableName + "(";
        for(int i = 0; i < columsNames.length-1; i++){
            query += columsNames[i] + ",";
        }
        query += columsNames[columsNames.length-1] + ")";
        //VALUES (?,?,...);
        query += "VALUES (";
        for(int i = 0; i < columsNames.length-1; i++){
            query += "?,";
        }
        query += "?);";
        
        return connection.prepareStatement(query);
    }

    public static List<Map<String, Object>> executeQuery(PreparedStatement preparedStatement) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Ottenere i metadati del risultato
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Popolare la lista con i risultati della query
            while (resultSet.next()) {
                // Creare una mappa per la riga corrente
                Map<String, Object> resultRow = new LinkedHashMap<>();

                // Popolare la mappa con i risultati della riga corrente
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    int columnType = metaData.getColumnType(i);
                    //System.out.println("Column name: " + columnName + " | Value: " + value + " | Type: " + columnType);
                    // Converti il valore in base al tipo di dati della colonna
                    Object convertedValue = convertValue(value, columnType);

                    // Aggiungere la coppia (nome colonna, valore) alla mappa dei risultati
                    resultRow.put(columnName, convertedValue);
                }

                // Aggiungi la mappa alla lista
                resultList.add(resultRow);
            }
        }

        return resultList;
    }

    private static Object convertValue(Object value, int columnType) {
        // Aggiungi logicamente più conversioni in base ai tipi di dati necessari
        switch (columnType) {
            case java.sql.Types.INTEGER:
                return (value != null) ? ((Number) value).intValue() : null;
            case java.sql.Types.VARCHAR:
                return (value != null) ? value.toString() : null;
            case java.sql.Types.DECIMAL:
                return ((java.math.BigDecimal) value).doubleValue();
            case java.sql.Types.DATE:
                return new java.util.Date(((java.sql.Date) value).getTime());
            default:
                return value;
        }
    }


    // Metodo per eseguire un'operazione di insert, update o delete
    public static int executeUpdate(PreparedStatement preparedStatement) throws SQLException {
        System.out.println("Executing update: " + preparedStatement);
        try {
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Query eseguita correttamente, righe modificate: " + rowsAffected);
            return rowsAffected;
        } catch (Exception e) {
            System.out.println("Query fallita: " + e.getMessage());
        }
    
        return 0;
    }
    
    // Metodo per chiudere la connessione
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            System.out.println("Disconnected from database");
            connection.close();
        }
    }

}