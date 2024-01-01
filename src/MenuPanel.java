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

        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button3());
                jop.createDialog("Inserimeto dati").setVisible(true);
            }
        });

        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button4());
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

        buttons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button8());
                jop.createDialog("Inserimeto dati").setVisible(true);
            }
        });

        buttons[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button9());
                jop.createDialog("Inserimeto dati").setVisible(true);
            }
        });

        buttons[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button10());
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

        buttons[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button12());
                jop.createDialog("Inserimeto dati").setVisible(true);
            }
        });

        buttons[12].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button13());
                jop.createDialog("Inserimeto dati").setVisible(true);
            }
        });

        buttons[13].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button14());
                jop.createDialog("Inserimeto dati").setVisible(true);
            }
        });

        buttons[14].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane(new Button15());
                jop.createDialog("Inserimeto dati").setVisible(true);
            }
        });

        


        
    }
}
