import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuPanel extends JPanel {
    private JButton button1[];
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JPanel buttons;
    
    public MenuPanel() {
        super();

        setLayout(new GridLayout(2, 1));
        buttons = new JPanel(new GridLayout(4, 4,5,5));
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        button1 = new JButton("button1");
        button2 = new JButton("button2");
        button3 = new JButton("button3");
        button4 = new JButton("button4");
        button5 = new JButton("button5");
        button6 = new JButton("button6");
        button7 = new JButton("button7");
        button8 = new JButton("button8");
        button9 = new JButton("button9");
        button10 = new JButton("button10");
        button11 = new JButton("button11");
        button12 = new JButton("button12");
        button13 = new JButton("button13");
        button14 = new JButton("button14");
        button15 = new JButton("button15");

       
        this.buttons.add(button1);
        this.buttons.add(button2);
        this.buttons.add(button3);
        this.buttons.add(button4);
        this.buttons.add(button5);
        this.buttons.add(button6);
        this.buttons.add(button7);
        this.buttons.add(button8);
        this.buttons.add(button9);
        this.buttons.add(button10);
        this.buttons.add(button11);
        this.buttons.add(button12);
        this.buttons.add(button13);
        this.buttons.add(button14);
        this.buttons.add(button15);

        add(buttons);

        button1.addActionListener(new Button1ActionListener(button1));
        button2.addActionListener(new Button2ActionListener(button2));
        button3.addActionListener(new Button3ActionListener());
        
    }
}
