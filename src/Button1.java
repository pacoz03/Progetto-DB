import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Button1 extends JPanel{
    private Map<String, JTextField> inputFields;
    String[] columnNames = {"nome","sede"};
    
    public Button1() {
        inputFields = new HashMap<>();
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
         //Creazione e riempimento Panel per le colonne
        JPanel namesPanel = new JPanel(new GridLayout(columnNames.length+1, 1));
        JPanel textPanel = new JPanel(new GridLayout(columnNames.length+1, 1));
        for (String columnName : columnNames) {
            JLabel label = new JLabel(columnName+": ",JLabel.RIGHT);
            namesPanel.add(label);
            
            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(300,30));
            textPanel.add(textField);

            inputFields.put(columnName,textField);
        }
     
        //Creazione di un box panel dove inserire le colonne
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.X_AXIS));
        boxPanel.add(namesPanel);
        boxPanel.add(textPanel);
        
        //Creazione mainPanel per utilizzare il border Layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boxPanel, BorderLayout.CENTER);
        
        //Creazione del bottone Submit
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        
        mainPanel.add(submitButton, BorderLayout.SOUTH);        
        
        this.add(mainPanel);
    }
    
    private void handleSubmit() {
        // Esegui l'azione di invio dei dati
        // Recupera i valori inseriti nei campi di input
        Map<String, Object> inputData = new LinkedHashMap<String,Object>();
        for (Map.Entry<String, JTextField> entry : inputFields.entrySet()) {
            String columnName = entry.getKey();
            Object value = entry.getValue().getText();
            inputData.put(columnName, value);
        }
        try {
            PreparedStatement query = DBManager.createInsertQuery("scuderia", columnNames);
            query.setObject(1, inputData.get("nome"));
            query.setObject(2, inputData.get("sede"));
            
            DBManager.executeUpdate(query);
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
        System.out.println("Dati inseriti: " + inputData);
    }
}
