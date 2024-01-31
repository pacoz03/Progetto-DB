import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Button3 extends JPanel {
    PanelManager panelManager, panelAM, panelPRO;
    //Rappresenta il nome delle colonne da inserire nel database
    String[] columnNames = {"nome","cognome","datanascita","nazionalita","vettura","tipopilota","dataprimalicenza","nlicenze"};
    public Button3() {
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        /* Creazione di panelManager per l'inserimento dei dati comuni a tutti i piloti */
        panelManager = new PanelManager();
        panelManager.createInsertPanel(
            "nome", PanelManager.getJTextField(),
            "cognome", PanelManager.getJTextField(),
            "datanascita", PanelManager.getJTextField(),
            "nazionalita", PanelManager.getJTextField(),
            "vettura", PanelManager.getJTextField(),
            "tipopilota", PanelManager.getJComboBox("AM", "PRO")
        );
        /* ------------------ */

        /* Creazione di panelAM per l'inserimento dei dati dei piloti AM */
        panelAM = new PanelManager();
        panelAM.createInsertPanel(
            "dataprimalicenza", PanelManager.getJTextField()
        );
        /* ------------------ */

        /* Creazione di panelPRO per l'inserimento dei dati dei piloti PRO */
        panelPRO = new PanelManager();
        panelPRO.createInsertPanel(
            "nlicenze", PanelManager.getJTextField()
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

        /* Creazione di un totalPanel che conterrà tutti gli altri pannelli  */
        JPanel totalPanel = new JPanel(new BorderLayout());
        
        /* Creazione di un listener sul JComboBox tipopilota  */
        ((JComboBox<?>)panelManager.inputFields.get("tipopilota")).addItemListener(new ItemListener() {

            //Quando il tipo del pilota cambia, deve cambiare il panel per l'inserimento dei giusti dati.
            //I valori inseriti negli altri pannelli verranno resettati per evitare errori
            @Override
            public void itemStateChanged(ItemEvent e) {
                String x = (String)e.getItem();
                totalPanel.removeAll();
                totalPanel.add(panelManager,BorderLayout.NORTH);
                if(x.equals("PRO")){
                    totalPanel.add(panelPRO, BorderLayout.CENTER);
                    panelAM.resetFields();
                }else{
                    totalPanel.add(panelAM, BorderLayout.CENTER);
                    panelPRO.resetFields();
                }
                totalPanel.add(submitButton, BorderLayout.SOUTH);
                totalPanel.setVisible(false);
                totalPanel.setVisible(true);
            }
        });
        /* ------------------ */

        /* Label per il titolo del panel */
        JLabel title = new JLabel("Aggiunta di un nuovo pilota all'equipaggio");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        /* Aggiunta del titolo e dei vari pannelli a totalPanel */
        panelManager.add(title, BorderLayout.NORTH);
        totalPanel.add(panelManager,BorderLayout.NORTH);
        totalPanel.add(panelAM, BorderLayout.CENTER);
        totalPanel.add(submitButton, BorderLayout.SOUTH);
        /* ------------------ */

        this.add(totalPanel);
    }

    
    private void handleSubmit() {
        try {
            PreparedStatement query = DBManager.createInsertQuery("pilota", columnNames);
            DBManager.setQueryParameters(query, panelManager.inputFields,columnNames, 1, 6);
            DBManager.setQueryParameters(query, panelAM.inputFields,columnNames, 7, 7);
            DBManager.setQueryParameters(query, panelPRO.inputFields,columnNames, 8, 8);
            DBManager.executeUpdate(query);

            JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
            panelManager.resetFields();
            panelAM.resetFields();
            panelPRO.resetFields();
        } catch (SQLException e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}