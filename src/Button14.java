import java.sql.*;
import java.util.*;
import javax.swing.*;


public class Button14 extends JPanel {
    public Button14() {
        super();
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            selectResult = DBManager.executeQuery("SELECT ngara, punteggiototale, tipomotore\r\n" + //
                    "FROM vettura JOIN componente ON vettura.ngara = componente.vettura\r\n" + //
                    "WHERE tipomotore = 'TURBO'\r\n" + //
                    "ORDER BY punteggiototale DESC;");
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        Object[][] data = DBManager.convertToObjectMatrix(selectResult);
        String[] col = new String[]{"Vettura", "Punteggio Totale, Tipo Motore"};
        JTable table = new JTable(data, col);
        JScrollPane scrollPane = new JScrollPane(table);
    
        this.add(scrollPane);


        try {
            selectResult = DBManager.executeQuery("SELECT ngara, punteggiototale, tipomotore\r\n" + //
                    "FROM vettura JOIN componente ON vettura.ngara = componente.vettura\r\n" + //
                    "WHERE tipomotore = 'ASPIRATO'\r\n" + //
                    "ORDER BY punteggiototale DESC;");
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }

        data = DBManager.convertToObjectMatrix(selectResult);
        col = new String[]{"Vettura", "Punteggio Totale, Tipo Motore"};
        table = new JTable(data, col);
        JScrollPane scrollPane1 = new JScrollPane(table);

        this.add(scrollPane1);

    }
}