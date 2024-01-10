import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Button12 extends JPanel {
    public Button12() {
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        try {
            String query = "SELECT nome, ncomponenti FROM costruttore;";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
        }

        /* Creazione di panelManager per l'output dei dati in tabella */
        PanelManager panel = new PanelManager();
        panel.createOutputPanel(selectResult, new String[]{"nome", "numero componenti"});
        /* ------------------ */
        
        /* Label per il titolo del panel */
        JLabel title = new JLabel("Stampa mensile dei costruttori compreso il numero di componenti che ha fornito");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        panel.add(title, BorderLayout.NORTH);
        this.add(panel);
    }
}