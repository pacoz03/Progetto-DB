import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Button1 extends JPanel{
    PanelManager panelManager;
    String[] columnNames = {"nome","sede"}; //Rappresenta il nome delle colonne da inserire nel database

    //Creazione del panel Button1
    public Button1() {
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        /*Creazione del panel manager per l'inserimento dei dati*/
        panelManager = new PanelManager();
        panelManager.createInsertPanel(
            "nome", PanelManager.getJTextField(),
            "sede", PanelManager.getJTextField()
        );
        /* ------------------ */

        //Creazione del bottone Submit
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        /* ------------------ */

        /* Label per il titolo del panel */
        JLabel title = new JLabel("Inserimento scuderia");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */

        /* Aggiunta a panelManager di titolo e pulsante di submit */
        panelManager.add(title, BorderLayout.NORTH);
        panelManager.add(submitButton, BorderLayout.SOUTH);
        /* ------------------ */
        this.add(panelManager);        
    }
    
    private void handleSubmit() {
        try {
            PreparedStatement query = DBManager.createInsertQuery("scuderia", columnNames);
            DBManager.setQueryParameters(query,panelManager.inputFields,columnNames, 1,2);
            DBManager.executeUpdate(query);
            
            JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
            panelManager.resetFields();
        } catch (Exception e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
