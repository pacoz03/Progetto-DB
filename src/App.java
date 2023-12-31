import javax.swing.*;

public class App extends JFrame{
    private MenuPanel menuPanel;

    public App() {
        super();
        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(900, 600);
        setVisible(true);
        setLocation(this.getToolkit().getScreenSize().width / 2 - this.getWidth() / 2,
                this.getToolkit().getScreenSize().height / 2 - this.getHeight() / 2);

        menuPanel = new MenuPanel();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    System.out.println("Closing connection...");
                    DBManager.closeConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.add(menuPanel);

    }
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.setVisible(true);
    }
}
