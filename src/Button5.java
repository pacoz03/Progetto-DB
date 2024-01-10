import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Button5 extends JPanel {
    PanelManager panelManager;
    private String[] columnNames = {"gara","vettura"};  //Rappresenta il nome delle colonne da inserire nel database

    public Button5() {
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        /* Creazione di panelManager per l'inserimento dei dati */
        panelManager = new PanelManager();
        panelManager.createInsertPanel(
            "gara", PanelManager.getJTextField(),
                       "vettura", PanelManager.getJTextField()
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
        JLabel title = new JLabel("Iscrizione di una vettura ad una gara");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        /* Aggiunta di titolo e del pulsante di submit a panelManager */
        panelManager.add(title, BorderLayout.NORTH);
        panelManager.add(submitButton, BorderLayout.SOUTH);
        /* ------------------ */
        this.add(panelManager);
    }
    
    private void handleSubmit() {
        try{
            PreparedStatement query = DBManager.createInsertQuery("partecipazione", columnNames);
            DBManager.setQueryParameters(query, panelManager.inputFields, columnNames, 1, 2);
            DBManager.executeUpdate(query);

            JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
            panelManager.resetFields();
        }catch(Exception e){
           // Visualizza un messaggio di errore
           JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
