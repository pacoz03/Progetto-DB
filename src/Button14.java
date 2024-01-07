import java.awt.BorderLayout;
import java.sql.*;
import java.util.*;
import javax.swing.*;


public class Button14 extends JPanel {
    public Button14() {
        super();
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            String query = "SELECT ngara, punteggiototale, tipomotore\r\n" + //
                    "FROM vettura JOIN componente ON vettura.ngara = componente.vettura\r\n" + //
                    "WHERE tipomotore = 'TURBO'\r\n" + //
                    "ORDER BY punteggiototale DESC;";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }
        PanelManager out1 = new PanelManager();
        out1.createOutputPanel(selectResult, new String[]{"vettura", "punteggio totale", "tipo motore"});

        try {
            String query = "SELECT ngara, punteggiototale, tipomotore\r\n" + //
                    "FROM vettura JOIN componente ON vettura.ngara = componente.vettura\r\n" + //
                    "WHERE tipomotore = 'ASPIRATO'\r\n" + //
                    "ORDER BY punteggiototale DESC;";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        PanelManager out2 = new PanelManager();
        out2.createOutputPanel(selectResult, new String[]{"vettura", "punteggio totale", "tipo motore"});
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(out1);
        panel.add(out2);
        JScrollPane scrollPane = new JScrollPane(panel);
        this.add(scrollPane);
    }
}