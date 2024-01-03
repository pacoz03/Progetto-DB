import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Button3 extends JPanel {
    private Map<String, JTextField> inputFields;
    private JTextField textFields[];
    public Button3() {
        inputFields = new HashMap<>();
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));

        // Definisci la struttura della query SQL
        String[] columnNames = {"nome","cognome","datanascita","nazionalita","tipopilota","dataprimalicenza","nlicenze","vettura"};

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
            inputData.put(columnName, value);
        }
        try {
            // Utilizza i valori recuperati per eseguire l'inserimento nel database
            int result = DBManager.executeUpdate("INSERT INTO pilota (nome,cognome,datanascita,nazionalita,tipopilota,dataprimalicenza,nlicenze,vettura)\r\n" + //
                    "VALUES ('"+
                            inputData.get("nome")+"', '"+
                            inputData.get("cognome")+"', '"+
                            inputData.get("datanascita")+"', '"+
                            inputData.get("datanascita")+"', '"+
                            inputData.get("tipopilota")+"', '"+
                            inputData.get("dataprimalicenza")+"', '"+
                            inputData.get("nlicenze")+"', '"+
                            inputData.get("vettura")+"')");
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
