import java.sql.*;
import java.util.*;
import javax.swing.*;


public class Button13 extends JPanel {
    public Button13() {
        super();
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            String query = "SELECT ngara, punteggiototale FROM vettura;";
            
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        Object[][] data = DBManager.convertToObjectMatrix(selectResult);
        String[] col = new String[]{"Vettura", "Punteggio Totale"};
        JTable table = new JTable(data, col);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
    }
}