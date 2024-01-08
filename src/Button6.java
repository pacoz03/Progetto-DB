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
    JTable outTable;
    public Button6(){
        super();
        setLayout(new BorderLayout());

        result = getPartecipazioni();
        panelManager = new PanelManager();
        outTable = panelManager.createOutputPanel(result,new String[]{"gara", "vettura", "posizione", "esito", "punteggio"});
        submitButton = new JButton("Submit");

        /* Verificare se la selezione dei valori nella combo box può essere effetuato usando lindice dello stesso in corrispondenza con la entry della lista di partecipazioni */
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = outTable.getRowCount();
                Object[] dataRow = new Object[outTable.getColumnCount()];
                for(int i = 0; i < n; i++)
                {
                    for(int j = 0; j < dataRow.length; j++)
                    {
                        dataRow[j] = outTable.getValueAt(i, j);
                    }
                    updatePartecipazione(dataRow);
                }
            }
        });

        /* Label per il titolo del panel */
        JLabel title = new JLabel("Registrazione del risultato di ogni vettura iscritta ad una gara");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        panelManager.add(title, BorderLayout.NORTH);
        
        panelManager.add(submitButton, BorderLayout.SOUTH);
        this.add(panelManager);
    }

    private int updatePartecipazione(Object[] data)
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
            System.out.println("Query fallita!");
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    private List<Map<String,Object>> getPartecipazioni(){
        List<Map<String, Object>> selectResult = null; // Inizializza selectResult a null
        try {
            String query = "SELECT * FROM partecipazione WHERE esito = 'ISCRITTA';";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            selectResult = DBManager.executeQuery(preparedStatement);
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }
        return selectResult;
    }

}
