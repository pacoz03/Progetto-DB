import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Button2 extends JPanel {
    private Map<String, JTextField> inputFields;
    private JTextField textFields[];
    
    public Button2() {
        inputFields = new LinkedHashMap<>();
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        // Definisci la struttura della query SQL
        String[] columnNames = {"vettura", "costruttore", "dataCreazione", "cilindrata", "tipomotore", "ncilindri", "materiale", "nmarce", "peso", "tipocomponente"};
        
        //Creazione del panel di insert
        JPanel panel = PanelManager.createInsertPanel(inputFields, columnNames, textFields);
        
        //Creazione del bottone Submit
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        
        panel.add(submitButton, BorderLayout.SOUTH);        
        
        this.add(panel);
    }
    
    private void handleSubmit() {
        // Esegui l'azione di invio dei dati
        // Recupera i valori inseriti nei campi di input
        Map<String, Object> inputData = new HashMap<>();
        for (Map.Entry<String, JTextField> entry : inputFields.entrySet()) {
            String columnName = entry.getKey();
            Object value = entry.getValue().getText();
            System.out.println(value);
            inputData.put(columnName, value);
        }
        try {
            // Utilizza i valori recuperati per eseguire l'inserimento nel database
            int result = DBManager.executeUpdate("INSERT INTO componente (vettura, costruttore, dataCreazione, cilindrata, tipomotore, ncilindri, materiale, nmarce, peso, tipocomponente)" +
                    "VALUES (" +
                    inputData.get("vettura") + ", '" + 
                    inputData.get("costruttore") + "', '" + 
                    inputData.get("dataCreazione") + "', " + 
                    inputData.get("cilindrata") + ", '" + 
                    inputData.get("tipomotore") + "', " + 
                    inputData.get("ncilindri") + ", '" + 
                    inputData.get("materiale") + "', " + 
                    inputData.get("nmarce") + ", " + 
                    inputData.get("peso") + ", '" + 
                    inputData.get("tipocomponente") + "');");

                if (result == 1) {
                    // Visualizza un messaggio di successo
                    JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
                }
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
        System.out.println("Dati inseriti: " + inputData);
    }
}
