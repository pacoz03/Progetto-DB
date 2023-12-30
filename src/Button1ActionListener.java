import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class Button1ActionListener implements ActionListener {
    int result2;
    public Button1ActionListener(JButton button) {
        super();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        JOptionPane test = new JOptionPane(new Button1Insert());
        test.createDialog("Inserimento di un componente").setVisible(true);
    }
}
