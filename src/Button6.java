import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Font;

public class Button6 extends JPanel{
    Map<Integer, Integer> points = new HashMap<Integer, Integer>(){
        {
            put(1, 25);
            put(2, 20);
            put(3, 16);
            put(4, 13);
            put(5, 11);
            put(6, 10);
            put(7, 9);
            put(8, 8);
            put(9, 7);
            put(10, 6);
            put(11, 5);
            put(12, 4);
            put(13, 3);
            put(14, 2);
            put(15, 1);

        }
    };
    JButton submitButton;
    List<Map<String,Object>> result;
    PanelManager panelManager;
    JTable outTable;            //reference alla tabella di output contenuta restituita da panelManager
    public Button6(){
        setLayout(new BorderLayout());

        result = getPartecipazioni();
        /* Creazione di panelManager per l'output dei dati in tabella */
        panelManager = new PanelManager();
        outTable = panelManager.createOutputPanel(result,new String[]{"gara", "vettura", "posizione", "esito"});
        /* ------------------ */

        /* Label per il titolo del panel */
        JLabel title = new JLabel("Registrazione del risultato di ogni vettura iscritta ad una gara");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        /* Creazione del pulsante di submit */
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = outTable.getRowCount();
                Object[] dataRow = new Object[outTable.getColumnCount()+1];
                //Per ogni riga della tabella
                for(int i = 0; i < n; i++)
                {
                    //Per ogni campo della riga
                    for(int j = 0; j < dataRow.length-1; j++)
                    {
                        //Prendi il valore della cella
                        dataRow[j] = outTable.getValueAt(i, j);
                    }

                    if(points.get(Integer.parseInt(String.valueOf(dataRow[2]))) == null)
                        dataRow[dataRow.length-1] = 0;
                    else
                        dataRow[dataRow.length-1] = points.get(Integer.parseInt(String.valueOf(dataRow[2])));

                    if(String.valueOf(dataRow[3]).equals("ISCRITTA"))
                        continue;
                    //(Riga completata) Esegui l'update con i dati nella riga
                    if(updatePartecipazione(dataRow, i) == 1)
                        JOptionPane.showMessageDialog(panelManager, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
                }
                result = getPartecipazioni();
                panelManager.removeAll();
                outTable = panelManager.createOutputPanel(result,new String[]{"gara", "vettura", "posizione", "esito"});
                panelManager.add(title, BorderLayout.NORTH);
                panelManager.add(submitButton, BorderLayout.SOUTH);
                panelManager.setVisible(false);
                panelManager.setVisible(true);
            }
        });
        /* ------------------ */

        /* Aggiunta di titolo e del pulsante di submit a panelManager */
        panelManager.add(title, BorderLayout.NORTH);
        panelManager.add(submitButton, BorderLayout.SOUTH);
        /* ------------------ */

        this.add(panelManager);
    }

    private int updatePartecipazione(Object[] data, int row)
    {
        String[] col = {"posizione", "esito", "punteggio"};
        try{
            PreparedStatement query = DBManager.createUpdateQuery("partecipazione",col,"gara = ? AND vettura = ?");
            query.setObject(4, data[0]);
            query.setObject(5, data[1]);
            query.setObject(1, data[2]);
            query.setObject(2, String.valueOf(data[3]).toUpperCase());
            query.setObject(3, data[4]);

            DBManager.executeUpdate(query);
            return 1;
        }catch(SQLException e){
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(panelManager, e.getMessage(), "Errore update riga: " + row, JOptionPane.ERROR_MESSAGE);
            return 0;
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
