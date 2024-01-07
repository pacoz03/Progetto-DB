import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Button3 extends JPanel {
    PanelManager panelManager, panelAM, panelPRO;
    String[] columnNames = {"nome","cognome","datanascita","nazionalita","vettura","tipopilota","dataprimalicenza","nlicenze"};
    public Button3() {
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        panelManager = new PanelManager();
        panelManager.createInsertPanel(
            "nome", PanelManager.getJTextField(),
            "cognome", PanelManager.getJTextField(),
            "datanascita", PanelManager.getJTextField(),
            "nazionalita", PanelManager.getJTextField(),
            "vettura", PanelManager.getJTextField(),
            "tipopilota", PanelManager.getJComboBox("AM", "PRO")
        );

        //Panel per AM
        panelAM = new PanelManager();
        panelAM.createInsertPanel(
            "dataprimalicenza", PanelManager.getJTextField()
        );

        //Panel per PRO
        panelPRO = new PanelManager();
        panelPRO.createInsertPanel(
            "nlicenze", PanelManager.getJTextField()
        );
        
        //Creazione del bottone Submit
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        JPanel totalPanel = new JPanel(new BorderLayout());
        ((JComboBox)panelManager.inputFields.get("tipopilota")).addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String x = (String)e.getItem();
                totalPanel.removeAll();
                totalPanel.add(panelManager,BorderLayout.NORTH);
                if(x.equals("PRO")){
                    totalPanel.add(panelPRO, BorderLayout.CENTER);
                }else{
                    
                    totalPanel.add(panelAM, BorderLayout.CENTER);
                }
                totalPanel.add(submitButton, BorderLayout.SOUTH);
                totalPanel.setVisible(false);
                totalPanel.setVisible(true);
            }
        });
        totalPanel.add(panelManager,BorderLayout.NORTH);
        totalPanel.add(panelAM, BorderLayout.CENTER);
        totalPanel.add(submitButton, BorderLayout.SOUTH);
        this.add(totalPanel);
    }

    
    private void handleSubmit() {
        try {
            PreparedStatement query = DBManager.createInsertQuery("pilota", columnNames);
            DBManager.setQueryParameters(query, panelManager.inputFields,columnNames, 1, 6);
            DBManager.setQueryParameters(query, panelAM.inputFields,columnNames, 7, 7);
            DBManager.setQueryParameters(query, panelPRO.inputFields,columnNames, 8, 8);
            
            int result = DBManager.executeUpdate(query);
            if (result == 1) {
                // Visualizza un messaggio di successo
                JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
}