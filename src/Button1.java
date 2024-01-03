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
        panelManager.createInsertPanel(
            "nome", PanelManager.getJTextField(),
            "sede", PanelManager.getJTextField()
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
        // Esegui l'azione di invio dei dati
        try {
            PreparedStatement query = DBManager.createInsertQuery("scuderia", columnNames);
            query.setObject(1, ((JTextField)panelManager.inputFields.get("nome")).getText());
            query.setObject(2, ((JTextField)panelManager.inputFields.get("sede")).getText());
            
            DBManager.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
}
