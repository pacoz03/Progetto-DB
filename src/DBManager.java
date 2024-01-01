import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DBManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/campionato";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
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

    public static Object[][] convertToObjectMatrix(List<Map<String, Object>> in)
    {
        ArrayList<ArrayList<Object>> tempData = new ArrayList<ArrayList<Object>>();
        //Per ogni nodo della lista (tupla)
        for (Map<String, Object> row : in) {
            //Crea un ArrayList dove inserire tutti i valori della tupla
            ArrayList<Object> temp = new ArrayList<>();
            //Per ogni entry della mappa (coppia {nome colonna, valore})
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                //Prendi il valore ed inseriscilo nell'ArrayList
                Object columnValue = entry.getValue();
                temp.add(0, columnValue);
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


    public static List<Map<String, Object>> executeQuery(String query) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Ottenere i metadati del risultato
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Popolare la lista con i risultati della query
            while (resultSet.next()) {
                // Creare una mappa per la riga corrente
                Map<String, Object> resultRow = new HashMap<>();

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
    public static int executeUpdate(String query) throws SQLException {
        System.out.println("Executing update: " + query);
        try (Statement preparedStatement = connection.createStatement()) 
            {
            // Esegue l'operazione e restituisce il numero di righe interessate
            return preparedStatement.executeUpdate(query);
        }
    }
    
    // Metodo per chiudere la connessione
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            System.out.println("Disconnected from database");
            connection.close();
        }
    }

}