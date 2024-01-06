import java.sql.*;
import java.util.*;
import javax.swing.*;



public class Button10 extends JPanel {
    public Button10() {
        super();
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

        Object[][] data = DBManager.convertToObjectMatrix(selectResult);
        String[] col = new String[]{"Nome", "Cognome","Nazionalità","Vettura","Codice Gara","Sede Circuito"};
        JTable table = new JTable(data, col);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
    }
}