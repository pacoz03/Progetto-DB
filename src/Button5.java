import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Button5 extends JPanel {
    PanelManager panelManager;
    private String[] columnNames = {"gara","vettura"};
    public Button5() {
        panelManager = new PanelManager();
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        panelManager.createPanel(
            "gara", PanelManager.getJTextField(),
                       "vettura", PanelManager.getJTextField()
        );
        
        //Creazione del bottone Submit
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        
        panelManager.add(submitButton, BorderLayout.SOUTH);
        this.add(panelManager);
    }
    
    private void handleSubmit() {
        try{
            //Crea il preparedStatement della query
            PreparedStatement query = DBManager.createInsertQuery("partecipazione", columnNames);

            //Inserisci i valori
            query.setObject(1, ((JTextField)panelManager.inputFields.get("gara")).getText());
            query.setObject(2, ((JTextField)panelManager.inputFields.get("vettura")).getText());

            //Esegui l'update
            DBManager.executeUpdate(query);
        }catch(Exception e){
            // TODO: handle exception
        }
    }
}
