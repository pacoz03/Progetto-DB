import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;
import javax.swing.*;



public class Button10 extends JPanel {
    public Button10() {
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; 
        try {
            String query = "SELECT pilota.nome, pilota.cognome, pilota.nazionalita, vettura.ngara, gara.codice, circuito.sede\r\n" + //
                    "FROM pilota JOIN vettura ON vettura.ngara = pilota.vettura\r\n" + //
                    "\t\t\tJOIN partecipazione ON vettura.ngara = partecipazione.vettura\r\n" + //
                    "\t\t\tJOIN gara ON partecipazione.gara = gara.codice\r\n" + //
                    "\t\t\tJOIN circuito ON gara.circuito = circuito.nome\r\n" + //
                    "WHERE partecipazione.posizione = 1 AND pilota.nazionalita = circuito.sede;";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
        }

        /* Creazione di panelManager per l'output dei dati in tabella */
        PanelManager panelManager = new PanelManager();
        panelManager.createOutputPanel(selectResult, new String[]{"nome", "cognome", "nazionalità", "vettura", "gara", "sede circuito"});
        /* ------------------ */
        
        /* Label per il titolo del panel */
        JLabel title = new JLabel("Visualizzare i piloti che hanno vinto in un circuito di casa");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        panelManager.add(title, BorderLayout.NORTH);
        this.add(panelManager);
    }
}