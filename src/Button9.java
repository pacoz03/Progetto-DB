import java.sql.*;
import java.util.*;
import javax.swing.*;



public class Button9 extends JPanel {
    public Button9() {
        super();
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            String query = "SELECT nome, sede, COUNT(gentleman.codice)\r\n" + //
                    "FROM scuderia JOIN gentleman ON scuderia.nome = gentleman.scuderia\r\n" + //
                    "GROUP BY nome, sede;";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        Object[][] data = DBManager.convertToObjectMatrix(selectResult);
        String[] col = new String[]{"Scuderia", "Numero di Finanziamenti","Numero Gentleman"};
        JTable table = new JTable(data, col);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
    }
}