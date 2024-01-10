import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

public class PanelManager extends JPanel{
    public Map<String, Component> inputFields = new HashMap<String,Component>();

    public PanelManager(){
        this.setLayout(new BorderLayout());
    }

    //Metodo per la creazione di un JTextField delle dimensioni standard utilizzate
    public static JTextField getJTextField(){
        JTextField t = new JTextField();
        t.setPreferredSize(new Dimension(300,30));
        return t;
    }

    //Metodo per la creazione di un JComboBox inizializzato con tutti gli options
    public static JComboBox<String> getJComboBox(String... options){
        JComboBox<String> t = new JComboBox<String>();
        for (String s : options) {
            t.addItem(s);
        }
        return t;
    }

    //Metodo per la creazione di un panel per l'inserimento dei dati
    public void createInsertPanel(Object... objects){
        //Gli ogetti devono essere inviati a coppie <nome, Field>
        if(objects.length % 2 != 0)
        {
            System.out.println("Il numero di ogetti deve essere pari");
        }
        //Creazione e riempimento Panel per le colonne
        JPanel namesPanel = new JPanel(new GridLayout(objects.length/2, 1));    //Contiene i nomi dei fields incolonnati
        JPanel objectPanel = new JPanel(new GridLayout(objects.length/2, 1));   //Contiene i field incolonnati

        //Per ogni coppia
        for(int i = 0; i < objects.length; i++)
        {
            //Inserisci nel panel dei nomi una label col nome
            JLabel label = new JLabel((String)objects[i] + ":");
            namesPanel.add(label);

            //Inserisci nel panel degli ogetti il field passato in input
            objectPanel.add((Component)objects[++i]);
            
            //Aggiungi alla mappa il field che avrà come chiave il nome
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

    //Metodo per la creazione di una tabella per visualizzare l'output
    public JTable createOutputPanel(List<Map<String, Object>> result, String[] col)
    {
        Object[][] data = DBManager.convertToObjectMatrix(result);
        JTable table = new JTable(data, col);
        JScrollPane scrollPane = new JScrollPane(table);

        this.add(scrollPane);
        return table;
    }

    //Metodo per il reset dei valori di tutti gli inputField
    public void resetFields()
    {
        for (Map.Entry<String, Component>  field : inputFields.entrySet()) {
            Component comp = field.getValue();
            if(comp instanceof JTextField){
                ((JTextField)comp).setText("");
            } else if(comp instanceof JComboBox){
                ((JComboBox<?>)comp).setSelectedItem("");
            }
        }
    }
}