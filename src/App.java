import javax.swing.*;

public class App extends JFrame{
    private MenuPanel login;

    public App() {
        super();
        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(500, 900);
        setVisible(true);
        setLocation(this.getToolkit().getScreenSize().width / 2 - this.getWidth() / 2,
                this.getToolkit().getScreenSize().height / 2 - this.getHeight() / 2);


        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    System.out.println("Closing connection...");
                    DbConnection.closeConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public static void main(String[] args) throws Exception {
        DbConnection.getConnection();
        App app = new App();
        app.setVisible(true);
    }
}

