import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;


public class Button7 extends JPanel {
    PanelManager panelManager;
    public Button7() {
        super();
        //Set Layout della classe
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        panelManager = new PanelManager();
        panelManager.createInsertPanel(
            "vettura", PanelManager.getJTextField(),
            "componente", PanelManager.getJComboBox("MOTORE", "TELAIO", "CAMBIO")
        );
        
        //Creazione del bottone Submit
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        panelManager.add(submitButton, BorderLayout.SOUTH);
        this.add(panelManager);
        
    }

    private void handleSubmit()
    {
        this.setLayout(new BorderLayout());
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        //Inserisci il risultato in selectResult
        try {
            String query = "SELECT ngara\r\n" + //
                    "FROM vettura JOIN componente ON vettura.ngara = componente.vettura\r\n" + //
                    "WHERE vettura.ngara = ? AND componente.tipocomponente = ?;";
            
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setObject(1, ((JTextField)panelManager.inputFields.get("vettura")).getText());
            preparedStatement.setObject(2, ((JComboBox)panelManager.inputFields.get("componente")).getSelectedItem());
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e1) {
            // TODO: handle exception
            System.out.println(e1.getMessage());
        }
        if(selectResult.size() > 0){
            JOptionPane.showMessageDialog(null, "Componente già presente", "ERRORE", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "È possibile montare il componente", "RISULTATO", JOptionPane.DEFAULT_OPTION);
        }
    }
}