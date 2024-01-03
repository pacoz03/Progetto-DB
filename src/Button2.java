import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Button2 extends JPanel {
    private Map<String, JTextField> inputFields;
    String[] columnNames = {"ngara", "modello", "scuderia"};
    
    public Button2() {
        inputFields = new LinkedHashMap<>();
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        //Creazione del panel di insert
        JPanel panel = PanelManager.createInsertPanel(inputFields, columnNames);
        
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
            PreparedStatement query = DBManager.getConnection().prepareStatement(DBManager.createInsertQuery("vettura", columnNames));
            query.setObject(1, inputData.get("nome"));
            query.setObject(2, inputData.get("sede"));
            
            int result = DBManager.executeUpdate(query);
            if (result == 1) {
                // Visualizza un messaggio di successo
                JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
                // Cambia l'inserimento da Vettura a Componente
                this.setVisible(false);
                this.removeAll();
                this.add(new InsertComponente(Integer.valueOf((String)inputData.get("ngara"))));
                this.setVisible(true);
            }
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
        System.out.println("Dati inseriti: " + inputData);
    }
}
