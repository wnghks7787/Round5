import javax.swing.*;

public class MainFrame extends JFrame {
    JPanel menuPanel;
    JPanel gamePanel;
    public MainFrame()
    {
        setSize(900, 700);
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();
        add(menuPanel);
        add(gamePanel);

        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
