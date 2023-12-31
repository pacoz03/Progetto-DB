import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Button2 extends JPanel {
    private Map<String, Object> inputData;
    
    public Button2() {
        inputData = new HashMap<>();
        // Definisci la struttura della query SQL
        String[] columnNames = {"vettura", "costruttore", "dataCreazione", "cilindrata", "tipomotore", "ncilindri", "materiale", "nmarce", "peso", "tipocomponente"};
        
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
