import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;
import javax.swing.*;


public class Button15 extends JPanel {
    public Button15() {
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        try {
            String query = "SELECT scuderia.nome, AVG(rapporto.puntiAlMinuto) as puntialminuto\r\n" + 
                    "FROM scuderia JOIN (SELECT vettura.scuderia, vettura.ngara, vettura.punteggiototale / COALESCE( (SUM(gara.durata) * 60) , 1) AS puntiAlMinuto\r\n" + 
                    "FROM  vettura LEFT JOIN partecipazione ON vettura.ngara = partecipazione.vettura\r\n" + 
                    "LEFT JOIN gara ON partecipazione.gara = gara.codice\r\n" + 
                    "GROUP BY vettura.ngara, vettura.scuderia) \r\n" + 
                    "AS rapporto ON scuderia.nome = rapporto.scuderia\r\n" + 
                    "GROUP BY scuderia.nome;";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
        }

        /* Creazione di panelManager per l'output dei dati in tabella */
        PanelManager panel = new PanelManager();
        panel.createOutputPanel(selectResult, new String[]{"scuderia", "media punti al minuto"});
        /* ------------------ */

        /* Label per il titolo del panel */
        JLabel title = new JLabel("Stampa della classifica finale dei punti conseguiti da tutte le vetture");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */

        panel.add(title, BorderLayout.NORTH);
        this.add(panel);
    }
}