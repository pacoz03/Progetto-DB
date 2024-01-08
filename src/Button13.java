import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;
import javax.swing.*;


public class Button13 extends JPanel {
    public Button13() {
        super();
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            String query = "SELECT ngara, punteggiototale FROM vettura;";
            
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        PanelManager panel = new PanelManager();
        /* Label per il titolo del panel */
        JLabel title = new JLabel("Stampa della classifica finale dei punti conseguiti da tutte le vetture");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */

        panel.add(title, BorderLayout.NORTH);
        panel.createOutputPanel(selectResult, new String[]{"ngara", "punteggio totale"});
        this.add(panel);
    }
}