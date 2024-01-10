import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;
import javax.swing.*;


public class Button14 extends JPanel {
    String[] tipiMotore = {"ASPIRATO", "TURBO"};
    public Button14() {
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
            //Per ogni tipo di motore crea una classifica separata
            for (String tipo : tipiMotore) {
                preparedStatement.setString(1, tipo);
                selectResult = DBManager.executeQuery(preparedStatement);

                /* Creazione di panelManager per l'output dei dati in tabella */
                PanelManager panelManager = new PanelManager();
                panelManager.createOutputPanel(selectResult, new String[]{"vettura", "punteggio totale", "tipo motore"});
                /* ------------------ */

                panel.add(panelManager);
            }
        } catch (SQLException e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
        }
        JLabel title = new JLabel("Stampa delle classifiche finali di punti per tipo di motore");
        title.setFont(new Font("", Font.BOLD, 24));

        JScrollPane scrollPane = new JScrollPane(panel);
        this.add(title, BorderLayout.NORTH);
        this.add(scrollPane);
    }
}