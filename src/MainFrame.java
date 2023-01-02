import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame implements MouseListener {
    MenuPanel menuPanel;
    GamePanel gamePanel;

    boolean passCheck = false;

    JButton resetBtn;
    public MainFrame()
    {
        getContentPane().setBackground(new Color(103, 92, 92));
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
//            System.out.println("x좌표 : " + e.getX() / GamePanel.CHECK_SIZE + " y좌표 : " + e.getY() / GamePanel.CHECK_SIZE);



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

            int whiteStone = 0, blackStone = 0;
            for(int i = 0 ; i < 8 ; i++)
            {
                for(int j = 0 ; j < 8 ; j++)
                {
                    if(GamePanel.stones[i][j] == 1)
                        blackStone++;
                    else if(GamePanel.stones[i][j] == -1)
                        whiteStone++;
                }
            }

            menuPanel.blackInt.setText(String.valueOf(blackStone));
            menuPanel.whiteInt.setText(String.valueOf(whiteStone));

            GamePanel.myTurn = !GamePanel.myTurn;

//            boolean empty = check.emptyCheck();
            if(!check.emptyCheck()) {
                if(passCheck) {
                    passCheck = false;
                    System.out.println("게임 종료");
                }
                else {
                    System.out.println("둘 수 있는 곳이 없습니다.");
//                    GamePanel.myTurn = !GamePanel.myTurn;
                    passCheck = true;
                    return;
                }
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


        resetBtn.setBounds(30, 400, 70, 70);

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

    }

//    public boolean passChecking(Check check)
//    {
//        if(!check.emptyCheck()) {
//            if(passCheck) {
//                passCheck = false;
//                System.out.println("게임 종료");
//            }
//            else {
//                System.out.println("둘 수 있는 곳이 없습니다.");
////                    GamePanel.myTurn = !GamePanel.myTurn;
//                passCheck = true;
//            }
//        }
//    }
}
