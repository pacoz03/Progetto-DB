import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class InsertComponente extends JPanel {
    PanelManager panelManager, panelMotore, panelCambio, panelTelaio;
    String[] columnNames = {"vettura", "costruttore", "dataCreazione","tipocomponente", "tipomotore", "cilindrata", "ncilindri", "nmarce", "materiale", "peso"};
    
    public InsertComponente(int vettura) {
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        //Creazione del panel di insert
        panelManager = new PanelManager();
        panelManager.createInsertPanel(
            "vettura", PanelManager.getJTextField(),
            "dataCreazione", PanelManager.getJTextField(),
            "costruttore", PanelManager.getJTextField(),
            "tipocomponente", PanelManager.getJComboBox("MOTORE","TELAIO","CAMBIO")
        );
        ((JTextField)panelManager.inputFields.get("vettura")).setText(String.valueOf(vettura));

        //Panel di insert per il motore
        panelMotore = new PanelManager();
        panelMotore.createInsertPanel(
            "tipomotore", PanelManager.getJComboBox("ASPIRATO","TURBO"),
            "ncilindri", PanelManager.getJTextField(),
            "cilindrata", PanelManager.getJTextField()
        );

        //Panel di insert per il cambio
        panelCambio = new PanelManager();
        panelCambio.createInsertPanel(
            "nmarce", PanelManager.getJComboBox("7", "8")
        );

        //Panel di insert per il telaio
        panelTelaio = new PanelManager();
        panelTelaio.createInsertPanel(
            "materiale", PanelManager.getJTextField(),
            "peso", PanelManager.getJTextField()
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
        //Creazione Listener sul Combobox
        ((JComboBox)panelManager.inputFields.get("tipocomponente")).addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String x = (String)e.getItem();
                if(x.equals("MOTORE")){
                    totalPanel.removeAll();
                    totalPanel.add(panelManager,BorderLayout.NORTH);
                    totalPanel.add(panelMotore, BorderLayout.CENTER);
                    totalPanel.add(submitButton, BorderLayout.SOUTH);
                    totalPanel.setVisible(false);
                    totalPanel.setVisible(true);
                }else if(x.equals("TELAIO")){
                    totalPanel.removeAll();
                    totalPanel.add(panelManager,BorderLayout.NORTH);
                    totalPanel.add(panelTelaio, BorderLayout.CENTER);
                    totalPanel.add(submitButton, BorderLayout.SOUTH);
                    totalPanel.setVisible(false);
                    totalPanel.setVisible(true);
                }else{
                    totalPanel.removeAll();
                    totalPanel.add(panelManager,BorderLayout.NORTH);
                    totalPanel.add(panelCambio, BorderLayout.CENTER);
                    totalPanel.add(submitButton, BorderLayout.SOUTH);
                    totalPanel.setVisible(false);
                    totalPanel.setVisible(true);
                }
            }
        });

        
        totalPanel.add(panelManager, BorderLayout.NORTH);
        totalPanel.add(panelMotore, BorderLayout.CENTER);
        totalPanel.add(submitButton, BorderLayout.SOUTH);
        this.add(totalPanel);
    }
    
    private void handleSubmit() {
        try {
            PreparedStatement query = DBManager.createInsertQuery("componente", columnNames);
            query.setObject(1, ((JTextField)panelManager.inputFields.get("ngara")).getText());
            query.setObject(2, ((JTextField)panelManager.inputFields.get("costruttore")).getText());
            query.setDate(3, java.sql.Date.valueOf(((JTextField)panelManager.inputFields.get("dataCreazione")).getText()));
            query.setObject(4, ((JComboBox)panelManager.inputFields.get("tipocomponente")).getSelectedItem());
            query.setObject(5, ((JTextField)panelMotore.inputFields.get("tipomotore")).getText());
            query.setObject(6, ((JTextField)panelMotore.inputFields.get("cilindrata")).getText());
            query.setObject(7, ((JTextField)panelMotore.inputFields.get("ncilindri")).getText());
            query.setObject(8, ((JComboBox)panelCambio.inputFields.get("nmarce")).getSelectedItem());
            query.setObject(9, ((JTextField)panelTelaio.inputFields.get("materiale")).getText());
            query.setObject(10, ((JTextField)panelTelaio.inputFields.get("peso")).getText());
            
            DBManager.executeUpdate(query);
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
}