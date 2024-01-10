import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Font;

public class Button6 extends JPanel{
    JButton submitButton;
    List<Map<String,Object>> result;
    PanelManager panelManager;
    JTable outTable;            //reference alla tabella di output contenuta restituita da panelManager
    public Button6(){
        setLayout(new BorderLayout());

        result = getPartecipazioni();
        /* Creazione di panelManager per l'output dei dati in tabella */
        panelManager = new PanelManager();
        outTable = panelManager.createOutputPanel(result,new String[]{"gara", "vettura", "posizione", "esito", "punteggio"});
        /* ------------------ */
        
        /* Creazione del pulsante di submit */
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = outTable.getRowCount();
                Object[] dataRow = new Object[outTable.getColumnCount()];
                //Per ogni riga della tabella
                for(int i = 0; i < n; i++)
                {
                    //Per ogni campo della riga
                    for(int j = 0; j < dataRow.length; j++)
                    {
                        //Prendi il valore della cella
                        dataRow[j] = outTable.getValueAt(i, j);
                    }
                    //(Riga completata) Esegui l'update con i dati nella riga
                    updatePartecipazione(dataRow, i);
                }
            }
        });
        /* ------------------ */

        /* Label per il titolo del panel */
        JLabel title = new JLabel("Registrazione del risultato di ogni vettura iscritta ad una gara");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        /* Aggiunta di titolo e del pulsante di submit a panelManager */
        panelManager.add(title, BorderLayout.NORTH);
        panelManager.add(submitButton, BorderLayout.SOUTH);
        /* ------------------ */

        this.add(panelManager);
    }

    private void updatePartecipazione(Object[] data, int row)
    {
        String[] col = {"posizione", "esito", "punteggio"};
        try{
            PreparedStatement query = DBManager.createUpdateQuery("partecipazione",col,"gara = ? AND vettura = ?");
            query.setObject(4, data[0]);
            query.setObject(5, data[1]);
            query.setObject(1, data[2]);
            query.setObject(2, data[3]);
            query.setObject(3, data[4]);

            DBManager.executeUpdate(query);
        }catch(Exception e){
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "Errore update riga: " + row, JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<Map<String,Object>> getPartecipazioni(){
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        try {
            String query = "SELECT * FROM partecipazione WHERE esito = 'ISCRITTA';";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
        return selectResult;
    }
}
