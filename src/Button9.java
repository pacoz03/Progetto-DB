import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Button9 extends JPanel {
    public Button9() {
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        try {
            String query = "SELECT nome, sede, COUNT(gentleman.codice)\r\n" + //
                    "FROM scuderia JOIN gentleman ON scuderia.nome = gentleman.scuderia\r\n" + //
                    "GROUP BY nome, sede;";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
        }

        /* Creazione di panelManager per l'output dei dati in tabella */
        PanelManager panelManager = new PanelManager();
        panelManager.createOutputPanel(selectResult, new String[]{"nome", "sede", "Numero Gentleman"});
        /* ------------------ */
        
        /* Label per il titolo del panel */
        JLabel title = new JLabel("Stampa annuale delle scuderie che hanno partecipato al campionato compreso il numero di finanziamenti");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        panelManager.add(title, BorderLayout.NORTH);
        this.add(panelManager);
    }
}