import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class Button1ActionListener implements ActionListener {
    private Map<String, JTextField> inputFields;

    @Override
    public void actionPerformed(ActionEvent e){
        createOptionPanel();
    }

    public void createOptionPanel() {
        JPanel panel = new JPanel();
        JOptionPane test = new JOptionPane(panel);
        inputFields = new HashMap<>();

        panel.setLayout(new GridLayout(11, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Definisci la struttura della query SQL
        String[] columnNames = {"vettura", "costruttore", "dataCreazione", "cilindrata", "tipomotore", "ncilindri", "materiale", "nmarce", "peso", "tipocomponente"};

        for (String columnName : columnNames) {
            JLabel label = new JLabel(columnName + ":");
            JTextField textField = new JTextField();
            inputFields.put(columnName, textField);

            panel.add(label);
            panel.add(textField);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        panel.add(new JLabel()); // Empty label as a filler
        panel.add(submitButton); 
        test.createDialog("Inserimento dati").setVisible(true);
    }

    private void handleSubmit() {
        // Esegui l'azione di invio dei dati
        // Recupera i valori inseriti nei campi di input
        Map<String, Object> inputData = new HashMap<>();
        for (Map.Entry<String, JTextField> entry : inputFields.entrySet()) {
            String columnName = entry.getKey();
            Object value = entry.getValue().getText();
            inputData.put(columnName, value);
        }
        try {
            // Utilizza i valori recuperati per eseguire l'inserimento nel database
            int result = DbConnection.executeUpdate("INSERT INTO componente (vettura, costruttore, dataCreazione, cilindrata, tipomotore, ncilindri, materiale, nmarce, peso, tipocomponente)" +
                    "VALUES (" +
                    inputData.get("vettura") + ", '" + 
                    inputData.get("costruttore") + "', '" + 
                    inputData.get("dataCreazione") + "', " + 
                    inputData.get("cilindrata") + ", '" + 
                    inputData.get("tipomotore") + "', " + 
                    inputData.get("ncilindri") + ", '" + 
                    inputData.get("materiale") + "', " + 
                    inputData.get("nmarce") + ", " + 
                    inputData.get("peso") + ", '" + 
                    inputData.get("tipocomponente") + "');");

                if (result == 1) {
                    // Visualizza un messaggio di successo
                    JOptionPane.showMessageDialog(null, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
                }
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
        System.out.println("Dati inseriti: " + inputData);
    }
}
