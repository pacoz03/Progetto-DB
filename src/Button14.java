import java.awt.BorderLayout;
import java.awt.Panel;
import java.sql.*;
import java.util.*;
import javax.swing.*;


public class Button14 extends JPanel {
    String[] tipiMotore = {"ASPIRATO", "TURBO"};
    public Button14() {
        super();
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //Inserisci il risultato in selectResult
        try {
            String query = "SELECT ngara, punteggiototale, tipomotore\r\n" + //
                    "FROM vettura JOIN componente ON vettura.ngara = componente.vettura\r\n" + //
                    "WHERE tipomotore = ?\r\n" + //
                    "ORDER BY punteggiototale DESC;";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            for (String tipo : tipiMotore) {
                preparedStatement.setString(1, tipo);
                selectResult = DBManager.executeQuery(preparedStatement);
                PanelManager panelManager = new PanelManager();
                panelManager.createOutputPanel(selectResult, new String[]{"vettura", "punteggio totale", "tipo motore"});
                panel.add(panelManager);
            }
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }
        
        JScrollPane scrollPane = new JScrollPane(panel);
        this.add(scrollPane);
    }
}