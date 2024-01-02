import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Button8 extends JPanel {
    public Button8() {
        super();
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            selectResult = DBManager.executeQuery("SELECT DISTINCT scuderia.nome, SUM(quota) AS totalefinanziamenti\r\n" + 
                    "FROM scuderia JOIN gentleman ON scuderia.nome = gentleman.scuderia\r\n" + 
                    "GROUP BY nome;");
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        Object[][] data = DBManager.convertToObjectMatrix(selectResult);
        String[] col = new String[]{"Scuderia", "Totale Finanziamentisrc/Button8.java"};
        JTable table = new JTable(data, col);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
    }
}