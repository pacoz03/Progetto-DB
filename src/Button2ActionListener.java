import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import java.awt.event.ActionEvent;

public class Button2ActionListener implements ActionListener {
    ArrayList<ArrayList<String>> result;
    public Button2ActionListener(JButton button) {
        super();
        result = new ArrayList<ArrayList<String>>();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        /* try {
            // Esempio di query di selezione
            selectResult = DbConnection.executeQuery("select componente.tipomotore,sum(vettura.punteggiototale) as punteggiototale from componente join vettura on componente.vettura = vettura.ngara where tipocomponente='MOTORE' group by tipomotore order by punteggiototale ASC;", 
            (resultSet) -> {
                List<Map<String, Object>> rows = new ArrayList<>();
                try {
                    while (resultSet.next()) {
                        Map<String, Object> row = DbConnection.createRow(resultSet);
                        rows.add(row);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                return rows;
            });
        } catch (SQLException e1) {
            System.out.println("Errore durante l'esecuzione della query di selezione");
        } */

        try {
            selectResult = DbConnection.executeQuery("SELECT scuderia.nome, COUNT(gentleman.codice) * 100 / COUNT(pilota.codice) AS percentualeGentleman\r\n" + //
                    "FROM scuderia \tJOIN vettura ON scuderia.nome = vettura.scuderia\r\n" + //
                    "\t\t\t\tJOIN pilota ON vettura.ngara = pilota.vettura\r\n" + //
                    "                LEFT JOIN gentleman ON pilota.codice = gentleman.codice\r\n" + //
                    "GROUP BY scuderia.nome;");
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }
        for (Map<String, Object> row : selectResult) {
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String columnName = entry.getKey();
                Object columnValue = entry.getValue();
                System.out.print(columnName + ": " + columnValue + " | ");
            }
            System.out.println(); // Vai a capo dopo ogni riga
        }
    }
}