import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.*;


public class Button13 extends JPanel {
    public Button13() {
        super();
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            selectResult = DBManager.executeQuery("SELECT ngara, punteggiototale FROM vettura;");
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