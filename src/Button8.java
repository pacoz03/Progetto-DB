import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Button8 extends JPanel {
    public Button8() {
        super();
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            String query = "SELECT DISTINCT scuderia.nome as scuderia, SUM(quota) AS totalefinanziamenti\r\n" + 
                    "FROM scuderia JOIN gentleman ON scuderia.nome = gentleman.scuderia\r\n" + 
                    "GROUP BY nome;";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        PanelManager panel = new PanelManager();
        /* Label per il titolo del panel */
        JLabel title = new JLabel("Per ciascuna scuderia stampare la somma totale dei finanziamenti ricevuti");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        panel.add(title, BorderLayout.NORTH);
        panel.createOutputPanel(selectResult, new String[]{"scuderia", "totale finanziamenti"});


        this.add(panel);
    }
}