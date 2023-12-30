import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Button6Update extends JPanel {
    private Map<String, JTextField> inputFields;
    JButton submitButton;
    
    public Button6Update(String gara, String veicolo){
        super();
        System.out.println(gara+veicolo);
        inputFields = new HashMap<String, JTextField>();

        setLayout(new GridLayout(4, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Definisci la struttura della query SQL
        String[] columnNames = {"esito","posizione","punteggio"};

        for (String columnName : columnNames) {
            JLabel label = new JLabel(columnName + ":");
            JTextField textField = new JTextField();
            inputFields.put(columnName, textField);

            add(label);
            add(textField);
        }

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit(gara,veicolo);
            }
        });

        add(new JLabel()); // Empty label as a filler
        add(submitButton);

    }

    
    private void handleSubmit(String gara,String veicolo) {
        Map<String, Object> inputData = new HashMap<>();
        for (Map.Entry<String, JTextField> entry : inputFields.entrySet()) {
            String columnName = entry.getKey();
            Object value = entry.getValue().getText();
            inputData.put(columnName, value);
        } 

        try {
            // Utilizza i valori recuperati per eseguire l'inserimento nel database
            int result = DBManager.executeUpdate("UPDATE partecipazione\r\n" + //
                    "SET esito = '"+ inputData.get("esito") +"', posizione = "+inputData.get("posizione")+", punteggio ="+ inputData.get("punteggio")+"\r\n" + //
                    "WHERE gara = "+ gara+" AND vettura = "+ veicolo+";");

                if (result == 1) {
                    // Visualizza un messaggio di successo
                    JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
                }
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
/*     public HashMap<String,Object> getUpdatableData(String gara,String veicolo){

    } */
}
