import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Button2 extends JPanel {
    PanelManager panelManager;
    String[] columnNames = {"ngara", "modello", "scuderia"};
    
    public Button2() {
        panelManager = new PanelManager();
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        panelManager.createPanel(
            "ngara", PanelManager.getJTextField(),
                       "modello", PanelManager.getJTextField(),
                       "scuderia", PanelManager.getJTextField()
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
        this.setVisible(false);
                this.removeAll();
                this.add(new InsertComponente(Integer.valueOf(((JTextField)panelManager.inputFields.get("ngara")).getText())));
                this.setVisible(true);
                try {
            PreparedStatement query = DBManager.createInsertQuery("vettura", columnNames);
            query.setObject(1, ((JTextField)panelManager.inputFields.get("ngara")).getText());
            query.setObject(2, ((JTextField)panelManager.inputFields.get("modello")).getText());
            query.setObject(3, ((JTextField)panelManager.inputFields.get("scuderia")).getText());
            
            DBManager.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
}
