import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;


public class Button7 extends JPanel {
    PanelManager panelManager;
    public Button7() {
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        /* Creazione di panelManager per l'input dei dati */
        panelManager = new PanelManager();
        panelManager.createInsertPanel(
            "vettura", PanelManager.getJTextField(),
            "componente", PanelManager.getJComboBox("MOTORE", "TELAIO", "CAMBIO")
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
        JLabel title = new JLabel("Verifica della possibilità di montare un componente su una vettura");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        /* Aggiunta del titolo e del pulsante di submit a panelManager */
        panelManager.add(title, BorderLayout.NORTH);
        panelManager.add(submitButton, BorderLayout.SOUTH);
        /* ------------------ */

        this.add(panelManager);
        
    }

    private void handleSubmit()
    {
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        String[] columsNames = {"vettura", "componente"};
        try {
            String query = "SELECT ngara\r\n" + //
                    "FROM vettura JOIN componente ON vettura.ngara = componente.vettura\r\n" + //
                    "WHERE vettura.ngara = ? AND componente.tipocomponente = ?;";
            
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            DBManager.setQueryParameters(preparedStatement, panelManager.inputFields, columsNames, 1, 2);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
        }

        if(selectResult.size() > 0){
            JOptionPane.showMessageDialog(null, "Componente già presente", "ERRORE", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "È possibile montare il componente", "RISULTATO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}