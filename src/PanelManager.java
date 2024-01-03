import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class PanelManager{
    /*Restituisce un JPanel, con layout "Border", per l'inserimento dei dati dei campi contenuri in columnNames.
     * inputFields e textFields sono parametri di output in cui vengono lasciati i valori inseriti dall'utente
     */
    public static JPanel createInsertPanel(Map<String,JTextField> inputFields, String[] columnNames, JTextField[] textFields)
    {
        //Creazione e riempimento Panel per le colonne
        JPanel namesPanel = new JPanel(new GridLayout(columnNames.length+1, 1));
        JPanel textPanel = new JPanel(new GridLayout(columnNames.length+1, 1));
        textFields = new JTextField[columnNames.length];
        int i = 0;
        for (String columnName : columnNames) {
            JLabel label = new JLabel(columnName+": ",JLabel.RIGHT);
            namesPanel.add(label);
            
            textFields[i] = new JTextField();
            textFields[i].setPreferredSize(new Dimension(300,30));
            textPanel.add(textFields[i]);

            inputFields.put(columnName,textFields[i]);
            i++;
        }

        //Creazione di un box panel dove inserire le colonne
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.X_AXIS));
        boxPanel.add(namesPanel);
        boxPanel.add(textPanel);
        
        //Creazione mainPanel per utilizzare il border Layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boxPanel, BorderLayout.CENTER);

        return mainPanel;
    }
}