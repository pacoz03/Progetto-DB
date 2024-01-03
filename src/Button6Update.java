import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Button6Update extends JPanel {
    private Map<String, JTextField> inputFields;
    String[] columnNames = {"esito","posizione","punteggio"};
    JButton submitButton;
    
    public Button6Update(String gara, String vettura){
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
                handleSubmit(gara, vettura);
            }
        });
        
        mainPanel.add(submitButton, BorderLayout.SOUTH);        
        
        this.add(mainPanel);
    }

    
    private void handleSubmit(String gara,String veicolo) {
        Map<String, Object> inputData = new LinkedHashMap<String,Object>();
        for (Map.Entry<String, JTextField> entry : inputFields.entrySet()) {
            String columnName = entry.getKey();
            Object value = entry.getValue().getText();
            inputData.put(columnName, value);
        } 

        try {
            // Utilizza i valori recuperati per eseguire l'inserimento nel database
            String query = "UPDATE partecipazione\r\n" + //
                    "SET esito = ?, posizione = ?, punteggio =?" + //
                    "WHERE gara = ? AND vettura = ?;";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setObject(1, inputData.get("esito"));
            preparedStatement.setObject(2, inputData.get("posizione"));
            preparedStatement.setObject(3, inputData.get("punteggio"));
            preparedStatement.setObject(4, inputData.get(gara));
            preparedStatement.setObject(5, inputData.get(veicolo));
            DBManager.executeUpdate(preparedStatement);
            JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
}
