import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame implements MouseListener {
    MenuPanel menuPanel;
    GamePanel gamePanel;
    ResultFrame resultFrame;
    JButton resetBtn;

    public static int whiteCnt = 0;
    public static int blackCnt = 0;
    public MainFrame()
    {
        getContentPane().setBackground(new Color(103, 92, 92));
        setSize(900, 700);
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();
        resultFrame = new ResultFrame(this);
        add(menuPanel);
        add(gamePanel);


        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addResetButton();

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
            int color;

            if(GamePanel.myTurn)
                color = GamePanel.WHITE;
            else
                color = GamePanel.BLACK;

            Check check = new Check(x, y, color);

            if(GamePanel.stones[y][x] == 0 && check.checking())
                GamePanel.stones[y][x] = color;
            else
                return;

            repaint();

            blackCnt = 0;
            whiteCnt = 0;
            for(int i = 0 ; i < 8 ; i++)
            {
                for(int j = 0 ; j < 8 ; j++)
                {
                    if(GamePanel.stones[i][j] == 1)
                        blackCnt++;
                    else if(GamePanel.stones[i][j] == -1)
                        whiteCnt++;
                }
            }

            menuPanel.blackInt.setText(String.valueOf(blackCnt));
            menuPanel.whiteInt.setText(String.valueOf(whiteCnt));

            if(MainFrame.whiteCnt > MainFrame.blackCnt)
                resultFrame.textLabel.setText("White WIN!!");
            else if(MainFrame.whiteCnt < MainFrame.blackCnt)
                resultFrame.textLabel.setText("Black WIN!!");
            else
                resultFrame.textLabel.setText("DRAW!!");

            GamePanel.myTurn = !GamePanel.myTurn;
            if(GamePanel.myTurn)
                color = GamePanel.WHITE;
            else
                color = GamePanel.BLACK;

//            boolean empty = check.emptyCheck();
            if(!check.emptyCheck(color)) {
                System.out.println("PASS");
                GamePanel.myTurn = !GamePanel.myTurn;

                color *= -1;

                passChecking(check, color);
            }

            if(GamePanel.myTurn)
            {
                menuPanel.blackTurn.setVisible(false);
                menuPanel.whiteTurn.setVisible(true);
            }
            else
            {
                menuPanel.blackTurn.setVisible(true);
                menuPanel.whiteTurn.setVisible(false);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void reset()
    {
        for(int i = 0 ; i < 8 ; i++)
        {
            for(int j = 0 ; j < 8 ; j++)
            {
                if((i == 3 && j == 3) || (i == 4 && j == 4))
                    GamePanel.stones[i][j] = GamePanel.WHITE;
                else if((i == 4 && j == 3) || (i == 3 && j == 4))
                    GamePanel.stones[i][j] = GamePanel.BLACK;
                else
                    GamePanel.stones[i][j] = 0;
            }
        }

        menuPanel.blackTurn.setVisible(true);
        menuPanel.whiteTurn.setVisible(false);
        GamePanel.myTurn = false;
        menuPanel.blackInt.setText("2");
        menuPanel.whiteInt.setText("2");
        gamePanel.repaint();
    }

    void addResetButton()
    {
        resetBtn = new JButton("Reset");

        menuPanel.add(resetBtn);


        resetBtn.setBounds(30, 400, 140, 70);

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultFrame.textLabel.setText("Are you want to restart?");
                resultFrame.setVisible(true);
            }
        });
    }

    public void passChecking(Check check, int color)
    {
        if(!check.emptyCheck(color))
            resultFrame.setVisible(true);
        else
        {
            if(color == -1)
                JOptionPane.showMessageDialog(null, "Black can't move!", "Pass", JOptionPane.WARNING_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "White can't move!", "Pass", JOptionPane.WARNING_MESSAGE);
        }
    }
}
