import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

public class PanelManager extends JPanel{
    public Map<String, Component> inputFields = new HashMap<String,Component>();

    public static JTextField getJTextField()
    {
        JTextField t = new JTextField();
        t.setPreferredSize(new Dimension(300,30));
        return t;
    }

    public static JScrollPane getJScrollPane()
    {
        JScrollPane t = new JScrollPane();
        return t;
    }

    public static JComboBox<String> getJComboBox(String... options)
    {
        JComboBox<String> t = new JComboBox<String>();
        for (String s : options) {
            t.addItem(s);
        }
        return t;
    }

    /*Restituisce un JPanel, con layout "Border", per l'inserimento dei dati dei campi contenuri in columnNames.
     * inputFields e textFields sono parametri di output in cui vengono lasciati i valori inseriti dall'utente
     */
    public void createPanel(Object... objects)
    {
        if(objects.length % 2 != 0)
        {
            System.out.println("Il numero di ogetti deve essere pari");
        }
        //Creazione e riempimento Panel per le colonne
        JPanel namesPanel = new JPanel(new GridLayout(objects.length/2, 1));
        JPanel objectPanel = new JPanel(new GridLayout(objects.length/2, 1));

        for(int i = 0; i < objects.length; i++)
        {
            JLabel label = new JLabel((String)objects[i] + ":");
            namesPanel.add(label);

            objectPanel.add((Component)objects[++i]);
            inputFields.put((String)objects[i-1],(Component)objects[i]);
        }
    
        //Creazione di un box panel dove inserire le colonne
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.X_AXIS));
        boxPanel.add(namesPanel);
        boxPanel.add(objectPanel);
        
        //Creazione mainPanel per utilizzare il border Layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boxPanel, BorderLayout.CENTER);

        this.add(mainPanel);
    }
}