import java.awt.BorderLayout;
import java.sql.*;
import java.util.*;
import javax.swing.*;



public class Button11 extends JPanel {
    public Button11() {
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        /*try {
            selectResult = DBManager.executeQuery("SELECT scuderia.nome, COUNT(gentleman.codice) * 100 / COUNT(pilota.codice) AS percentualeGentleman\r\n" + //
                    "FROM scuderia \tJOIN vettura ON scuderia.nome = vettura.scuderia\r\n" + //
                    "\t\t\t\tJOIN pilota ON vettura.ngara = pilota.vettura\r\n" + //
                    "                LEFT JOIN gentleman ON pilota.codice = gentleman.codice\r\n" + //
                    "GROUP BY scuderia.nome;");
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }*/

        Object[][] data = DBManager.convertToObjectMatrix(selectResult);
        String[] col = new String[]{"Scuderia", "Percentuale di Gentleman"};
        JTable table = new JTable(data, col);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
    }
}