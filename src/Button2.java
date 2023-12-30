import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.*;


public class Button2 extends JPanel {
    public Button2() {
        super();
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
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

        Object[][] data = DbConnection.convertToObjectMatrix(selectResult);
        String[] col = new String[]{"Scuderia", "Percentuale di Gentleman"};
        JTable table = new JTable(data, col);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
    }
}