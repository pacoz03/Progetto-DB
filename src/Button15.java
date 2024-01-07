import java.awt.BorderLayout;
import java.sql.*;
import java.util.*;
import javax.swing.*;


public class Button15 extends JPanel {
    public Button15() {
        super();
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
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
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        PanelManager panel = new PanelManager();
        panel.createOutputPanel(selectResult, new String[]{"scuderia", "media punti al minuto"});
        this.add(panel);
    }
}