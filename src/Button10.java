import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;
import javax.swing.*;



public class Button10 extends JPanel {
    public Button10() {
        super();
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
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        PanelManager panel = new PanelManager();
        /* Label per il titolo del panel */
        JLabel title = new JLabel("Visualizzare i piloti che hanno vinto in un circuito di casa");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        panel.add(title, BorderLayout.NORTH);
        panel.createOutputPanel(selectResult, new String[]{"nome", "cognome", "nazionalità", "vettura", "gara", "sede circuito"});
        this.add(panel);
    }
}