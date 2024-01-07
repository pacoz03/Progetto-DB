import java.awt.BorderLayout;
import java.sql.*;
import java.util.*;
import javax.swing.*;



public class Button9 extends JPanel {
    public Button9() {
        super();
        this.setLayout(new BorderLayout());
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

        PanelManager panel = new PanelManager();
        panel.createOutputPanel(selectResult, new String[]{"nome", "sede", "Numero Gentleman"});
        this.add(panel);
    }
}