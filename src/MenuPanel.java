import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;

public class MenuPanel extends JPanel {
    private JButton[] buttons = new JButton[15];
    private JPanel panel;
    
    public MenuPanel() {
        super();

        setLayout(new GridLayout(2, 1));
        panel = new JPanel(new GridLayout(4, 4,5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for(int i = 0; i < 15; i++)
        {
            buttons[i] = new JButton("Button" + (i+1));
            panel.add(buttons[i]);
        }

        add(panel);

        //L'indice del pulsante n equivale a n-1. es. Button1 = buttons[0]

        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button1());
                jop.createDialog("Inserimeto dati").setVisible(true);
            }
        });

        //Button2
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button2());
                jop.createDialog("Inserimeto dati").setVisible(true);
            }
        });

        //Button6
        buttons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button6SelectGara());
                jop.createDialog("Inserimeto dati").setVisible(true);
            }
        });

        //Button11
        buttons[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button11());
                jop.createDialog("Inserimeto dati").setVisible(true);
            }
        });
        
    }
}
