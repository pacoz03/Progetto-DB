import java.awt.GridLayout;
import java.util.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.regex.*;
import javax.swing.*;

public class Button3SelectGara extends JPanel{
    JButton submitButton;
    JComboBox<String> partecipazioni;
    List<Map<String,Object>> partecip;

    public Button3SelectGara(){
        super();
        
        setLayout(new GridLayout(4, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        partecipazioni = new JComboBox<>();
        partecip = getPartecipazioni();

        partecipazioni.addItem("gara " + "vettura");
        for (Map<String, Object> row : partecip) {
            String info= new String();
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String columnName = entry.getKey();
                Object columnValue = entry.getValue();
                System.out.print(columnName + ": " + columnValue + " | ");

                info += columnName + ": " + columnValue + " | ";
            }
            partecipazioni.addItem(info);
            System.out.println(); // Vai a capo dopo ogni riga
        }
        
        partecipazioni.setSelectedIndex(0);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Pattern pattern = Pattern.compile("vettura:\\s*(\\d+)\\s*\\|\\s*gara:\\s*(\\d+)");
                Matcher matcher = pattern.matcher(partecipazioni.getSelectedItem().toString());
        
                // Verifica se la regex ha trovato corrispondenze e ottieni i numeri
                if (matcher.find()) {
                    String vetturaNumber = matcher.group(1);
                    String garaNumber = matcher.group(2);
        
                    // Stampa i numeri
                    System.out.println("Vettura: " + vetturaNumber);
                    System.out.println("Gara: " + garaNumber);
                    JOptionPane test = new JOptionPane(new Button3Update(garaNumber,vetturaNumber));
                    test.createDialog("Inserimento di un risultato").setVisible(true);
                }
            }
        });

        add(partecipazioni);
        add(submitButton);
    }

    private List<Map<String,Object>> getPartecipazioni(){
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        try {
            selectResult = DbConnection.executeQuery("SELECT gara, vettura FROM partecipazione;");
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }
        return selectResult;
    }

}
