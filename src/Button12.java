import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Button12 extends JPanel {
    public Button12() {
        super();
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            String query = "SELECT nome, ncomponenti FROM costruttore;";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        PanelManager panel = new PanelManager();
        /* Label per il titolo del panel */
        JLabel title = new JLabel("Stampa mensile dei costruttori compreso il numero di componenti che ha fornito");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        panel.add(title, BorderLayout.NORTH);
        panel.createOutputPanel(selectResult, new String[]{"nome", "numero componenti"});
        this.add(panel);
    }
}