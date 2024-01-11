import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
import java.util.Map;
public class Button4 extends JPanel {
    PanelManager panelManager;
    //Rappresenta il nome delle colonne da inserire nel database
    private String[] columnNames = {"codice","quota","scuderia"};
    public Button4() {
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        /* Creazione di panelManager per l'inserimento dei dati */
        panelManager = new PanelManager();
        panelManager.createInsertPanel(
            "codice", PanelManager.getJTextField(),
                       "quota", PanelManager.getJTextField(),
                       "scuderia", PanelManager.getJTextField()
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
        JLabel title = new JLabel("Registrazione di un finanziamento per una scuderia");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        /* Aggiunta di titolo e del pulsante di submit a panelManager */
        panelManager.add(title, BorderLayout.NORTH);
        panelManager.add(submitButton, BorderLayout.SOUTH);
        /* ------------------ */
        
        this.add(panelManager);
    }
    


    private void handleSubmit() {
        
        try {
            List<Map<String, Object>> selectResult = null;
            int totaleEquipaggio = 0, totaleGentleman = 0;
            String query = "SELECT  COUNT(pilota.codice) AS totaleEquipaggio, COUNT(gentleman.codice) AS totaleGentleman\r\n" + //
                            "FROM vettura JOIN pilota on pilota.vettura = vettura.ngara\n"+
                            "LEFT JOIN gentleman on pilota.codice = gentleman.codice\n" +
                            "WHERE vettura.ngara IN (SELECT ngara FROM pilota join vettura on pilota.vettura = vettura.ngara WHERE pilota.codice = ?)\n"+
                            "GROUP BY ngara";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            
            preparedStatement.setInt(1, Integer.parseInt(((JTextField)(panelManager.inputFields.get("codice"))).getText()));
            selectResult = DBManager.executeQuery(preparedStatement);
            //Se la select restituisce qualcosa (c'è almeno un pilota)
            if(selectResult.size() > 0){
                totaleEquipaggio = Integer.valueOf(String.valueOf(selectResult.get(0).get("totaleEquipaggio")));
                //Se i campi della hashmap sono almeno 2 (esiste un gentleman nell'equipaggio)
                if(selectResult.get(0).size() > 1){
                    totaleGentleman = Integer.valueOf(String.valueOf(selectResult.get(0).get("totaleGentleman")));
                }else{
                    totaleGentleman = 0;
                }
            }else{
                totaleEquipaggio = 0;
                totaleGentleman = 0;
            }

            if(totaleEquipaggio - totaleGentleman <= 1){
                JOptionPane.showMessageDialog(this, "Un equipaggio non può essere formato da soli Gentleman Driver", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
        } catch (Exception e) {
            //Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }

        try {
            PreparedStatement query = DBManager.createInsertQuery("gentleman", columnNames);
            DBManager.setQueryParameters(query,panelManager.inputFields, columnNames, 1,3);
            DBManager.executeUpdate(query);
            
            JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
            panelManager.resetFields();
        } catch (SQLException e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
