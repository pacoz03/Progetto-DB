import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Button5 extends JPanel {
    private Map<String, JTextField> inputFields;
    public Button5() {
        super();
        inputFields = new HashMap<>();

        setLayout(new GridLayout(11, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Definisci la struttura della query SQL
        String[] columnNames = {"gara","vettura"};

        for (String columnName : columnNames) {
            JLabel label = new JLabel(columnName + ":");
            JTextField textField = new JTextField();
            inputFields.put(columnName, textField);

            add(label);
            add(textField);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        add(new JLabel()); // Empty label as a filler
        add(submitButton);
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
            int result = DBManager.executeUpdate("INSERT INTO partecipazione (gara,vettura))\r\n" + //
                    "VALUES ('"+
                            inputData.get("gara")+"', '"+
                            inputData.get("vettura")+"')");
                if (result == 1) {
                    // Visualizza un messaggio di successo
                    JOptionPane.showMessageDialog(this, "Inserimento riuscito", "Successo", JOptionPane.INFORMATION_MESSAGE);
                }
        } catch (SQLException e1) {
            // Visualizza un messaggio di errore
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
        System.out.println("Dati inseriti: " + inputData);
    }
}
