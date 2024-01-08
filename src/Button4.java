import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Button4 extends JPanel {
    PanelManager panelManager;
    private String[] columnNames = {"codice","quota","scuderia"};
    public Button4() {
        panelManager = new PanelManager();
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        panelManager.createInsertPanel(
            "codice", PanelManager.getJTextField(),
                       "quota", PanelManager.getJTextField(),
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

        /* Label per il titolo del panel */
        JLabel title = new JLabel("Registrazione di un finanziamento per una scuderia");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        panelManager.add(title, BorderLayout.NORTH);
        
        panelManager.add(submitButton, BorderLayout.SOUTH);
        this.add(panelManager);
    }
    


    private void handleSubmit() {
        try {
            PreparedStatement query = DBManager.createInsertQuery("gentleman", columnNames);
            // "codice","quota","scuderia"
            DBManager.setQueryParameters(query,panelManager.inputFields, columnNames, 1,3);

            
            DBManager.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
}
