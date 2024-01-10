import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Button8 extends JPanel {
    public Button8() {
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        try {
            String query = "SELECT DISTINCT scuderia.nome as scuderia, SUM(quota) AS totalefinanziamenti\r\n" + 
                    "FROM scuderia JOIN gentleman ON scuderia.nome = gentleman.scuderia\r\n" + 
                    "GROUP BY nome;";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
        }

        /* Creazione di panelManager per l'output dei dati in tabella */
        PanelManager panelManager = new PanelManager();
        panelManager.createOutputPanel(selectResult, new String[]{"scuderia", "totale finanziamenti"});
        /* ------------------ */

        /* Label per il titolo del panel */
        JLabel title = new JLabel("Per ciascuna scuderia stampare la somma totale dei finanziamenti ricevuti");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        panelManager.add(title, BorderLayout.NORTH);
        this.add(panelManager);
    }
}