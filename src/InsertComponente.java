import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class InsertComponente extends JPanel {
    JButton submitButton;
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
            "tipocomponente", PanelManager.getJComboBox("","MOTORE","TELAIO","CAMBIO")
        );
        ((JTextField)panelManager.inputFields.get("vettura")).setText(String.valueOf(vettura));

        //Panel di insert per il motore
        panelMotore = new PanelManager();
        panelMotore.createInsertPanel(
            "tipomotore", PanelManager.getJComboBox("","ASPIRATO","TURBO"),
            "ncilindri", PanelManager.getJTextField(),
            "cilindrata", PanelManager.getJTextField()
        );

        //Panel di insert per il cambio
        panelCambio = new PanelManager();
        panelCambio.createInsertPanel(
            "nmarce", PanelManager.getJComboBox("","7", "8")
        );

        //Panel di insert per il telaio
        panelTelaio = new PanelManager();
        panelTelaio.createInsertPanel(
            "materiale", PanelManager.getJTextField(),
            "peso", PanelManager.getJTextField()
        );
        
        //Creazione del bottone Submit
        submitButton = new JButton("Submit");
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
                totalPanel.removeAll();
                totalPanel.add(panelManager,BorderLayout.NORTH);
                if(x.equals("MOTORE")){
                    totalPanel.add(panelMotore, BorderLayout.CENTER);
                    
                }else if(x.equals("TELAIO")){
                    totalPanel.add(panelTelaio, BorderLayout.CENTER);
                }else if(x.equals("CAMBIO")){
                    totalPanel.add(panelCambio, BorderLayout.CENTER);
                }

                totalPanel.add(submitButton, BorderLayout.SOUTH);
                totalPanel.setVisible(false);
                totalPanel.setVisible(true);
            }
        });

        /* Label per il titolo del panel */
        JLabel title = new JLabel("Inserimento componente");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        totalPanel.add(title, BorderLayout.NORTH);
        totalPanel.add(panelManager, BorderLayout.CENTER);
        totalPanel.add(submitButton, BorderLayout.SOUTH);
        this.add(totalPanel);
    }
   
    
    private void handleSubmit() {
        try {
            PreparedStatement query = DBManager.createInsertQuery("componente", columnNames);
            DBManager.setQueryParameters(query, panelManager.inputFields,columnNames, 1, 4);
            DBManager.setQueryParameters(query, panelMotore.inputFields,columnNames, 5, 7);
            DBManager.setQueryParameters(query, panelCambio.inputFields,columnNames, 8, 8);
            DBManager.setQueryParameters(query, panelTelaio.inputFields,columnNames, 9, 10);
            DBManager.executeUpdate(query);

            JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
            panelManager.resetFields();
            panelMotore.resetFields();
            panelTelaio.resetFields();
            panelCambio.resetFields();
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
}