import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame implements MouseListener {
    JPanel menuPanel;
    GamePanel gamePanel;
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

        gamePanel.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        gamePanel.clickPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX() / GamePanel.CHECK_SIZE;
        int y = e.getY() / GamePanel.CHECK_SIZE;

        int pressX = e.getX() / GamePanel.CHECK_SIZE;
        int pressY = e.getY() / GamePanel.CHECK_SIZE;
        if(x == pressX && y == pressY)
        {
            System.out.println("x좌표 : " + e.getX() / GamePanel.CHECK_SIZE + " y좌표 : " + e.getY() / GamePanel.CHECK_SIZE);

            int color;

            if(GamePanel.myTern)
                color = GamePanel.WHITE;
            else
                color = GamePanel.BLACK;

            Check check = new Check(x, y, color);

            if(GamePanel.stones[y][x] == 0 && check.checking())
                GamePanel.stones[y][x] = color;
            else
                return;

            GamePanel.myTern = !GamePanel.myTern;
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
