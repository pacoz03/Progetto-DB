import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Button2 extends JPanel {
    PanelManager panelManager;
    String[] columnNames = {"ngara", "modello", "scuderia"};    //Rappresenta il nome delle colonne da inserire nel database
    
    public Button2() {
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        /* Creazione del panelManager per l'inserimento dei dati */
        panelManager = new PanelManager();
        panelManager.createInsertPanel(
            "ngara", PanelManager.getJTextField(),
                       "modello", PanelManager.getJTextField(),
                       "scuderia", PanelManager.getJTextField()
        );
        /* ------------------ */
        
        /* Creazione del bottone Submit */
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        /* ------------------ */

        /* Label per il titolo del panel */
        JLabel title = new JLabel("Inserimento dei dati di una vettura");
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
            PreparedStatement query = DBManager.createInsertQuery("vettura", columnNames);
            DBManager.setQueryParameters(query, panelManager.inputFields,columnNames, 1, 3);
            DBManager.executeUpdate(query);
            
            JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
            // Se l'inserimento è andato a buonfine, cancella i componenti nel label e crea il label per l'aggiunta di un componente 
            this.setVisible(false);
            this.removeAll();
            this.add(new InsertComponente(Integer.valueOf(((JTextField)panelManager.inputFields.get("ngara")).getText())));
            this.setVisible(true);
            panelManager.resetFields();
        } catch (SQLException e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
