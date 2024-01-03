import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Button5 extends JPanel {
    private Map<String, JTextField> inputFields;
    private JTextField textFields[];
    private String[] columnNames = {"gara","vettura"};
    public Button5() {
        super();
        inputFields = new HashMap<>();
        
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));

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
        try{
            PreparedStatement query = DBManager.getConnection().prepareStatement(DBManager.createInsertQuery("partecipazione", columnNames));
            query.setInt(0, (int)inputData.get("gara"));
            query.setString(1, (String)inputData.get("vettura"));
            DBManager.executeQuery(query);
        }catch(Exception e){
            // TODO: handle exception
        }
    }
}
