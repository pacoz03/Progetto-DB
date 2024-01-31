import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Map;
import java.util.List;

public class InsertComponente extends JPanel {
    JButton submitButton;
    PanelManager panelManager, panelMotore, panelCambio, panelTelaio;
    //Rappresenta il nome delle colonne da inserire nel database
    String[] columnNames = {"vettura", "costruttore", "dataCreazione","tipocomponente", "tipomotore", "cilindrata", "ncilindri", "nmarce", "materiale", "peso"};
    
    public InsertComponente(){
        constructor(-1);
    }

    public InsertComponente(int vettura) {
        constructor(vettura);
    }

    private void constructor(int vettura)
    {
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        /* Creazione di panelManager per l'inserimento dei dati comuni a tutti i componenti */
        panelManager = new PanelManager();
        panelManager.createInsertPanel(
            "vettura", PanelManager.getJTextField(),
            "dataCreazione", PanelManager.getJTextField(),
            "costruttore", PanelManager.getJTextField(),
            "tipocomponente", PanelManager.getJComboBox("","MOTORE","TELAIO","CAMBIO")
        );
        //Se si accede a questa pagina dopo l'inserimento di una vettura nel database, utilizza quella vettura per l'aggiunta dei componenti
        if(vettura != -1)((JTextField)panelManager.inputFields.get("vettura")).setText(String.valueOf(vettura));

        /* Creazione di panelMotore per l'inserimento dei dati del motore */
        panelMotore = new PanelManager();
        panelMotore.createInsertPanel(
            "tipomotore", PanelManager.getJComboBox("","ASPIRATO","TURBO"),
            "ncilindri", PanelManager.getJTextField(),
            "cilindrata", PanelManager.getJTextField()
        );
        /* ------------------ */

        /* Creazione di panelCambio per l'inserimento dei dati del cambio */
        panelCambio = new PanelManager();
        panelCambio.createInsertPanel(
            "nmarce", PanelManager.getJComboBox("","7", "8")
        );
        /* ------------------ */

        /* Creazione di panelTelaio per l'inserimento dei dati del telaio */
        panelTelaio = new PanelManager();
        panelTelaio.createInsertPanel(
            "materiale", PanelManager.getJTextField(),
            "peso", PanelManager.getJTextField()
        );
        /* ------------------ */
        
        /* Creazione del bottone Submit */
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        /* ------------------ */
        
        /* Creazione di totalPanel, il quale conterrà tutti i pannelli */
        JPanel totalPanel = new JPanel(new BorderLayout());

        /* Creazione Listener sul JCombobox tipocomponente */
        ((JComboBox<?>)panelManager.inputFields.get("tipocomponente")).addItemListener(new ItemListener() {
            
            //Quando il tipo del componente cambia, deve cambiare il panel per l'inserimento dei giusti dati.
            //I valori inseriti negli altri pannelli verranno resettati per evitare errori
            @Override
            public void itemStateChanged(ItemEvent e) {
                String x = (String)e.getItem();
                totalPanel.removeAll();
                totalPanel.add(panelManager,BorderLayout.NORTH);
                if(x.equals("MOTORE")){
                    totalPanel.add(panelMotore, BorderLayout.CENTER);
                    panelTelaio.resetFields();
                    panelCambio.resetFields();
                }else if(x.equals("TELAIO")){
                    totalPanel.add(panelTelaio, BorderLayout.CENTER);
                    panelMotore.resetFields();
                    panelCambio.resetFields();
                }else if(x.equals("CAMBIO")){
                    totalPanel.add(panelCambio, BorderLayout.CENTER);
                    panelMotore.resetFields();
                    panelTelaio.resetFields();
                }

                totalPanel.add(submitButton, BorderLayout.SOUTH);
                totalPanel.setVisible(false);
                totalPanel.setVisible(true);
            }
        });
        /* ------------------ */

        /* Label per il titolo del panel */
        JLabel title = new JLabel("Inserimento componente");
        title.setFont(new Font("", Font.BOLD, 24));
        /* ------------------ */
        
        /* Aggiunta del titolo e dei vari pannelli a totalPanel */
        panelManager.add(title, BorderLayout.NORTH);
        totalPanel.add(panelManager, BorderLayout.CENTER);
        totalPanel.add(submitButton, BorderLayout.SOUTH);
        /* ------------------ */

        this.add(totalPanel);
    }
   
    
    private void handleSubmit() {
        List<Map<String,Object>> selectResult = null;
        try{
            String query = "SELECT ngara\r\n" + //
                    "FROM vettura JOIN componente ON vettura.ngara = componente.vettura\r\n" + //
                    "WHERE vettura.ngara = ? AND componente.tipocomponente = ?;";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setObject(1, ((JTextField)(panelManager.inputFields.get("vettura"))).getText());
            preparedStatement.setObject(2, ((JComboBox<?>)(panelManager.inputFields.get("tipocomponente"))).getSelectedItem());
            selectResult = DBManager.executeQuery(preparedStatement);
        }catch(Exception e){
            // Visualizza un messaggio di errore
           JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }

        if(selectResult.size() > 0){
            JOptionPane.showMessageDialog(null, "Componente già presente", "ERRORE", JOptionPane.ERROR_MESSAGE);
            return;
        }
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
        } catch (SQLException e) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}