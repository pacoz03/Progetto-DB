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

        
        totalPanel.add(panelManager, BorderLayout.NORTH);
        totalPanel.add(submitButton, BorderLayout.SOUTH);
        this.add(totalPanel);
    }

    public void setQueryParameters(PreparedStatement query, Map<String, Component> inputFields, int startParamIndex, int endParamIndex) throws SQLException {
        for (int i = startParamIndex; i <= endParamIndex; i++) {
            Component field = inputFields.get(columnNames[i-1]); // Cambia "param" con il nome effettivo dei campi
            if (field instanceof JTextField) {
                query.setObject(i, ((JTextField) field).getText().equals("")? null : ((JTextField) field).getText());
            } else if (field instanceof JComboBox) {
                Object selectedItem = ((JComboBox) field).getSelectedItem();
                String valueToSet = (selectedItem != null && !String.valueOf(selectedItem).equals("")) ? selectedItem.toString() : null;
                query.setObject(i, valueToSet);
            }
            // Aggiungi altri controlli per gli altri tipi di componenti (es. date, ecc.) se necessario
        }
    }
    
    
    private void handleSubmit() {
        try {
            PreparedStatement query = DBManager.createInsertQuery("componente", columnNames);
            setQueryParameters(query, panelManager.inputFields, 1, 4);
            setQueryParameters(query, panelMotore.inputFields, 5, 7);
            setQueryParameters(query, panelCambio.inputFields, 8, 8);
            setQueryParameters(query, panelTelaio.inputFields, 9, 10);

            
            DBManager.executeUpdate(query);
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
}