import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.*;


public class Button12 extends JPanel {
    public Button12() {
        super();
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            selectResult = DBManager.executeQuery("SELECT nome, ncomponenti FROM costruttore;");
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        Object[][] data = DBManager.convertToObjectMatrix(selectResult);
        String[] col = new String[]{"Costruttore", "Numero di Componenti"};
        JTable table = new JTable(data, col);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
    }
}