import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DbConnection {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/campionato";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    private static Connection connection;

    // Costruttore privato per impedire la creazione di istanze esterne
    private DbConnection() {
    }


    // Metodo per ottenere l'istanza condivisa della connessione al database
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            System.out.println("Connected to database");
            // Crea una nuova connessione se non esiste o è chiusa
            connection = java.sql.DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        }
        return connection;
    }


    public static List<Map<String, Object>> executeQuery(String query) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
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
        try (Connection connection = getConnection();
                Statement preparedStatement = connection.createStatement()) {
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

/* 
class DbConnectionException extends SQLException {
    public DbConnectionException(String message) {
        super(message);
    }
}

class DbUpdateException extends DbConnectionException {
    public DbUpdateException(String message) {
        super(message);
    }
} */

