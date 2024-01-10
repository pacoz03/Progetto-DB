import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;

public class MenuPanel extends JPanel {
    private JButton[] buttons = new JButton[15];
    private JButton componentButton;
    private JPanel buttonsPanel, outputPanel;
    private JScrollPane buttonsScrollPane;
    
    public MenuPanel() {
        new DBManager();

        //layout del panel principale
        this.setLayout(new BorderLayout());

        /*CREAZIONE SCROLLPANE PER I PULSANTI */
        buttonsPanel = new JPanel(new GridLayout(16,1,5,5));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //aggiunta dei pulsanti al panel
        for(int i = 0; i < 15; i++)
        {
            buttons[i] = new JButton("Button" + (i+1));
            buttons[i].setPreferredSize(new Dimension(100,50));
            buttonsPanel.add(buttons[i]);
        }
        componentButton = new JButton("InsertComponente");
        componentButton.setPreferredSize(new Dimension(100,50));
        buttonsPanel.add(componentButton);
        //istanziamento dello scrollpane e aggiunta del panel ad esso
        buttonsScrollPane = new JScrollPane(buttonsPanel);
        buttonsScrollPane.setPreferredSize(new Dimension(200,getHeight()));
        buttonsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(buttonsScrollPane,BorderLayout.WEST);
        /*FINE CREAZIONE SCROLLPANE PER PULSANTI */
        
        /*CREAZIONE PANEL DI OUTPUT */
        outputPanel = new JPanel(new BorderLayout());
        add(outputPanel);
        /*FINE CREAZIONE PANEL DI OUTPUT */
        
        //ButtonN equivale a buttons[N-1]. es. Button1 = buttons[0]

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

        //Button3
        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button3());
            }
        });

        //Button4
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button4());
            }
        });

        //Button5
        buttons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button5());
            }
        });

        //Button6
        buttons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button6());
            }
        });

        //Button7
        buttons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button7());
            }
        });

        //Button8
        buttons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button8());
            }
        });

        //Button9
        buttons[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button9());
            }
        });

        //Button10
        buttons[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button10());
            }
        });

        //Button11
        buttons[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button11());
            }
        });

        //Button12
        buttons[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button12());
            }
        });

        //Button13
        buttons[12].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button13());
            }
        });

        //Button14
        buttons[13].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button14());
            }
        });

        //Button15
        buttons[14].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new Button15());
            }
        });

        //InsertComponent button
        componentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanelToOutput(new InsertComponente());
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
