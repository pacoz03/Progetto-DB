import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Button5 extends JPanel {
    PanelManager panelManager;
    private String[] columnNames = {"gara","vettura"};
    public Button5() {
        panelManager = new PanelManager();
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        panelManager.createInsertPanel(
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
        /* Label per il titolo del panel */
        JLabel title = new JLabel("Iscrizione di una vettura ad una gara");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        panelManager.add(title, BorderLayout.NORTH);
        
        panelManager.add(submitButton, BorderLayout.SOUTH);
        this.add(panelManager);
    }
    
    private void handleSubmit() {
        try{
            //Crea il preparedStatement della query
            PreparedStatement query = DBManager.createInsertQuery("partecipazione", columnNames);
            DBManager.setQueryParameters(query, panelManager.inputFields, columnNames, 1, 2);
            DBManager.executeUpdate(query);

            JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
            panelManager.resetFields();
        }catch(Exception e){
            // TODO: handle exception
        }
    }
}
