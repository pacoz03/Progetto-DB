import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Button3 extends JPanel {
    PanelManager panelManager, panelAM, panelPRO;
    String[] columnNames = {"nome","cognome","datanascita","nazionalita","tipopilota","dataprimalicenza","nlicenze","vettura"};
    public Button3() {
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        panelManager = new PanelManager();
        panelManager.createPanel(
            "nome", PanelManager.getJTextField(),
            "cognome", PanelManager.getJTextField(),
            "datanascita", PanelManager.getJTextField(),
            "nazionalita", PanelManager.getJTextField(),
            "vettura", PanelManager.getJTextField(),
            "tipopilota", PanelManager.getJComboBox("AM", "PRO")
        );

        //Panel per AM
        panelAM = new PanelManager();
        panelAM.createPanel(
            "dataprimalicenza", PanelManager.getJTextField()
        );

        //Panel per PRO
        panelPRO = new PanelManager();
        panelPRO.createPanel(
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
                if(x.equals("PRO")){
                    totalPanel.removeAll();
                    totalPanel.add(panelManager,BorderLayout.NORTH);
                    totalPanel.add(panelPRO, BorderLayout.CENTER);
                    totalPanel.add(submitButton, BorderLayout.SOUTH);
                    totalPanel.setVisible(false);
                    totalPanel.setVisible(true);
                }else{
                    totalPanel.removeAll();
                    totalPanel.add(panelManager,BorderLayout.NORTH);
                    totalPanel.add(panelAM, BorderLayout.CENTER);
                    totalPanel.add(submitButton, BorderLayout.SOUTH);
                    totalPanel.setVisible(false);
                    totalPanel.setVisible(true);
                }
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
            //"nome","cognome","datanascita","nazionalita","tipopilota","dataprimalicenza","nlicenze","vettura"
            query.setObject(1, ((JTextField)panelManager.inputFields.get("nome")).getText());
            query.setObject(1, ((JTextField)panelManager.inputFields.get("cognome")).getText());
            query.setDate(3, java.sql.Date.valueOf(((JTextField)panelManager.inputFields.get("datanascita")).getText()));
            query.setObject(4, ((JTextField)panelManager.inputFields.get("nazionalita")).getText());
            query.setObject(5, ((JComboBox<String>)panelManager.inputFields.get("tipopilota")).getSelectedItem());
            query.setObject(6,java.sql.Date.valueOf(((JTextField)panelManager.inputFields.get("dataprimalicenza")).getText()));
            query.setObject(7, ((JTextField)panelManager.inputFields.get("nlicenze")).getText());
            query.setObject(8, ((JTextField)panelManager.inputFields.get("vettura")).getText());
            
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
