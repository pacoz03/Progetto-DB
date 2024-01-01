import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.*;


public class Button15 extends JPanel {
    public Button15() {
        super();
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            selectResult = DBManager.executeQuery("SELECT scuderia.nome, AVG(rapporto.puntiAlMinuto) as puntialminuto\r\n" + 
                    "FROM scuderia JOIN (SELECT vettura.scuderia, vettura.ngara, vettura.punteggiototale / COALESCE( (SUM(gara.durata) * 60) , 1) AS puntiAlMinuto\r\n" + 
                    "FROM  vettura LEFT JOIN partecipazione ON vettura.ngara = partecipazione.vettura\r\n" + 
                    "LEFT JOIN gara ON partecipazione.gara = gara.codice\r\n" + 
                    "GROUP BY vettura.ngara, vettura.scuderia) \r\n" + 
                    "AS rapporto ON scuderia.nome = rapporto.scuderia\r\n" + 
                    "GROUP BY scuderia.nome;");
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        // Stampa la lista di mappe
        for (Map<String, Object> resultRow : selectResult) {
            for (Map.Entry<String, Object> entry : resultRow.entrySet()) {
                String columnName = entry.getKey();
                Object value = entry.getValue();
                System.out.println(columnName + ": " + value);
            }
            System.out.println("----------"); // Separatore tra le righe
        }

        Object[][] data = DBManager.convertToObjectMatrix(selectResult);
        String[] col = new String[]{"Scuderia", "Punteggio Al Minuto"};
        JTable table = new JTable(data, col);
        JScrollPane scrollPane = new JScrollPane(table);
    
        this.add(scrollPane);
    }
}