import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Button1 extends JPanel{
    PanelManager panelManager;
    String[] columnNames = {"nome","sede"};
    
    public Button1() {
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        panelManager = new PanelManager();
        panelManager.createPanel(
            "nome", PanelManager.getJTextField(),
            "sede", PanelManager.getJComboBox("op1", "op2", "op3")
        );
        
        //Creazione del bottone Submit
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        this.add(panelManager);
        this.add(submitButton, BorderLayout.SOUTH);        
    }
    
    private void handleSubmit() {
        // Esegui l'azione di invio dei dati
        try {
            PreparedStatement query = DBManager.createInsertQuery("scuderia", columnNames);
            query.setObject(1, ((JTextField)panelManager.inputFields.get("nome")).getText());
            query.setObject(2, ((JComboBox)panelManager.inputFields.get("sede")).getSelectedItem());
            
            DBManager.executeUpdate(query);
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
}
