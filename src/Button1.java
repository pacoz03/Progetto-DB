import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Button1 extends JPanel{
    private Map<String, JTextField> inputFields;
    private JTextField textFields[];
    
    public Button1() {
        inputFields = new HashMap<>();
        // Definisci la struttura della query SQL
        String[] columnNames = {"nome","sede"};
        
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        //Creazione e riempimento Panel per le colonne
        JPanel namesPanel = new JPanel(new GridLayout(columnNames.length+1, 1));
        JPanel textPanel = new JPanel(new GridLayout(columnNames.length+1, 1));
        textFields = new JTextField[columnNames.length];
        int i = 0;
        for (String columnName : columnNames) {
            JLabel label = new JLabel(columnName+": ",JLabel.RIGHT);
            namesPanel.add(label);
            
            textFields[i] = new JTextField();
            textFields[i].setPreferredSize(new Dimension(300,30));
            textPanel.add(textFields[i]);

            inputFields.put(columnName,textFields[i]);
            i++;
        }

        //Creazione di un box panel dove inserire le colonne
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.X_AXIS));
        boxPanel.add(namesPanel);
        boxPanel.add(textPanel);
        
        //Creazione del bottone Submit
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        
        //Creazione mainPanel per utilizzare il border Layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boxPanel, BorderLayout.CENTER);
        mainPanel.add(submitButton,BorderLayout.SOUTH);

        //Aggiungi al panel della classe il mainPanel
        this.add(mainPanel);
    }
    
    private void handleSubmit() {
        // Esegui l'azione di invio dei dati
        // Recupera i valori inseriti nei campi di input
        Map<String, Object> inputData = new LinkedHashMap<String,Object>();
        for (Map.Entry<String, JTextField> entry : inputFields.entrySet()) {
            String columnName = entry.getKey();
            Object value = entry.getValue().getText();
            System.out.println(value);
            inputData.put(columnName, value);
        }
        try {
            // Utilizza i valori recuperati per eseguire l'inserimento nel database
            int result = DBManager.executeUpdate("INSERT INTO scuderia (nome,sede)" + 
                    "VALUES ('" +
                    inputData.get("nome") + "', '" + 
                    inputData.get("sede") + "');"); 

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
