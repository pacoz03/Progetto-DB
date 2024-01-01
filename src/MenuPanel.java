import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {
    private JButton[] buttons = new JButton[15];
    private JPanel buttonsPanel, outputPanel;
    private JScrollPane buttonsScrollPane;
    
    public MenuPanel() {
        new DBManager();

        //layout del panel principale
        this.setLayout(new BorderLayout());

        /*CREAZIONE SCROLLPANE PER I PULSANTI */
        buttonsPanel = new JPanel(new GridLayout(15,1,5,5));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //aggiunta dei pulsanti al panel
        for(int i = 0; i < 15; i++)
        {
            buttons[i] = new JButton("Button" + (i+1));
            buttons[i].setPreferredSize(new Dimension(100,50));
            buttonsPanel.add(buttons[i]);
        }
        //istanziamento dello scrollpane e aggiunta del panel ad esso
        buttonsScrollPane = new JScrollPane(buttonsPanel);
        buttonsScrollPane.setPreferredSize(new Dimension(200,getHeight()));
        buttonsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(buttonsScrollPane,BorderLayout.WEST);
        /*FINE CREAZIONE SCROLLPANE PER PULSANTI */
        
        /*CREAZIONE PANEL DI OUTPUT */
        outputPanel = new JPanel(new BorderLayout());
        
        outputPanel.setBackground(Color.BLUE);
        outputPanel.setOpaque(true);
        add(outputPanel);
        /*FINE CREAZIONE PANEL DI OUTPUT */
        
        //ButtonN equivale a buttons[n-1]. es. Button1 = buttons[0]

        //Button1
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button1());
            }
        });

        //Button2
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button2());
            }
        });

        //Button6
        buttons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button6SelectGara());
            }
        });

        //Button11
        buttons[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button11());
            }
        });
        
    }

    private void addPanelToOutput(JPanel p)
    {
        //Rimuovi gli altri panel
        if(outputPanel.getComponentCount() > 0)
        {
            outputPanel.removeAll();
        }

        //Aggiungi il nuovo panel
        p.setAlignmentX(LEFT_ALIGNMENT);
        p.setAlignmentY(TOP_ALIGNMENT);
        p.setPreferredSize(new Dimension(outputPanel.getWidth(), outputPanel.getHeight()));
        outputPanel.add(p);
        outputPanel.setVisible(false);
        outputPanel.setVisible(true);
    }
}
