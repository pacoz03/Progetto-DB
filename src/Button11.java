import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;
import javax.swing.*;



public class Button11 extends JPanel {
    public Button11() {
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            String query = "SELECT scuderia.nome, COUNT(gentleman.codice) * 100 / COUNT(pilota.codice) AS percentualeGentleman\r\n" + //
                    "FROM scuderia \tJOIN vettura ON scuderia.nome = vettura.scuderia\r\n" + //
                    "\t\t\t\tJOIN pilota ON vettura.ngara = pilota.vettura\r\n" + //
                    "                LEFT JOIN gentleman ON pilota.codice = gentleman.codice\r\n" + //
                    "GROUP BY scuderia.nome;";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        PanelManager panel = new PanelManager();
        /* Label per il titolo del panel */
        JLabel title = new JLabel("Per ciascuna scuderia, visualizzare la percentuale di gentleman driver di cui si compone l'equipaggio");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        panel.add(title, BorderLayout.NORTH);
        panel.createOutputPanel(selectResult, new String[]{"scuderia", "Percentuale di gentleman (%)"});
        this.add(panel);
    }
}