import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class InsertComponente extends JPanel {
    private Map<String, JTextField> inputFields;
    String[] columnNames = {"vettura", "costruttore", "dataCreazione", "cilindrata", "tipomotore", "ncilindri", "materiale", "peso", "tipocomponente"};
    
    public InsertComponente(int vettura) {
        inputFields = new LinkedHashMap<>();
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        //Creazione del panel di insert
        JPanel panel = PanelManager.createInsertPanel(inputFields, columnNames);
        inputFields.get("vettura").setEditable(false);
        
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
            inputData.put(columnName, value);
        }
        try {
            PreparedStatement query = DBManager.createInsertQuery("componente", columnNames);
            query.setObject(1, inputData.get("vettura"));
            query.setObject(2, inputData.get("costruttore"));
            query.setDate(3, java.sql.Date.valueOf((String)inputData.get("dataCreazione")));
            query.setObject(4, inputData.get("cilindrata"));
            query.setObject(5, inputData.get("tipomotore"));
            query.setObject(6, inputData.get("ncilindri"));
            query.setObject(7, inputData.get("materiale"));
            query.setObject(8, inputData.get("nmarce"));
            query.setObject(9, inputData.get("peso"));
            query.setObject(10, inputData.get("tipocomponente"));
            
            int result = DBManager.executeUpdate(query);
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