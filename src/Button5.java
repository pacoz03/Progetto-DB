import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Button5 extends JPanel {
    private Map<String, JTextField> inputFields;
    private String[] columnNames = {"gara","vettura"};
    public Button5() {
        super();
        inputFields = new HashMap<>();
        
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
            System.out.println(columnName + " " + value);
        }
        try{
            //Crea il preparedStatement della query
            PreparedStatement query = DBManager.getConnection().prepareStatement(DBManager.createInsertQuery("partecipazione", columnNames));
            
            //Inserisci i valori
            query.setObject(1, inputData.get("gara"));
            query.setObject(2, inputData.get("vettura"));

            //Esegui l'update
            DBManager.executeUpdate(query);
        }catch(Exception e){
            // TODO: handle exception
        }
    }
}
