import java.awt.event.*;
import javax.swing.*;
public class Button3ActionListener implements ActionListener{
    
    public Button3ActionListener() {
        super();
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        JOptionPane test = new JOptionPane(new Button3SelectGara());
        test.createDialog("Selezione gara a cui apportare modifiche").setVisible(true);
    }
}
