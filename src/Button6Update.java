import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Button6Update extends JPanel {
    private Map<String, JTextField> inputFields;
    String[] columnNames = {"esito","posizione","punteggio"};
    JButton submitButton;
    
    public Button6Update(String gara, String veicolo){
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
                handleSubmit(inputFields.get(e));
            }
        });
        
        panel.add(submitButton, BorderLayout.SOUTH);        
        
        this.add(panel);
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
