import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener {

    // 각종 상수들
    static final int CHECK_SIZE = 75;
    static final int STONE_SIZE = 55;
    static final int BLACK = 1;
    static final int WHITE = 2;

    // 돌 저장하는 배열
    static int[][] stones = new int[8][8];     // [y][x]

    // 흑백 플레이 순서
    static boolean myTern = false;      // black == false, white == true

    // 이미지 이쁘게 출력하기
    ImageIcon whiteIcon = new ImageIcon("assets/white.png");
    ImageIcon blackIcon = new ImageIcon("assets/black.png");
    Image whiteImageResize, blackImageResize;

    public GamePanel()
    {
        // 이미지 불러오기
        getImage();

        setBounds(50, 50, 600, 600);
        setBackground(new Color(133, 69, 49));

        JLabel myLabel = new JLabel();
        add(myLabel);

        ImageIcon icon = new ImageIcon("assets/white.png");
        Image image = icon.getImage();
        Image image2 = image.getScaledInstance(STONE_SIZE, STONE_SIZE, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image2);

        myLabel.setIcon(icon);
        myLabel.setBounds(CHECK_SIZE + 10, CHECK_SIZE + 10, STONE_SIZE, STONE_SIZE);

        setLayout(null);
        addMouseListener(this);

        for(int y = 0 ; y < 8 ; y++)
        {
            for(int x = 0 ; x < 8 ; x++)
            {
                if((x == 3 && y == 3) || (x == 4 && y == 4))
                    stones[y][x] = WHITE;
                else if((x == 4 && y == 3) || (x == 3 && y == 4))
                    stones[y][x] = BLACK;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 게임 판 그리기
        for(int i = 0 ; i < 8 ; i++)
        {
            for(int j = 0 ; j < 8 ; j++)
            {
                g.setColor(new Color(176, 176, 176));
                if(i % 2 == 0 && j % 2 != 0)
                    g.fillRect(i * CHECK_SIZE, j * CHECK_SIZE, CHECK_SIZE, CHECK_SIZE);
                else if(i % 2 != 0 && j % 2 == 0)
                    g.fillRect(i * CHECK_SIZE, j * CHECK_SIZE, CHECK_SIZE, CHECK_SIZE);
            }
        }

        // 돌 그리기
        for(int y = 0 ; y < 8 ; y++)
        {
            for(int x = 0 ; x < 8 ; x++)
            {
                switch (stones[y][x]) {
                    case 1 -> g.drawImage(blackImageResize, x * CHECK_SIZE + 10, y * CHECK_SIZE + 10, this);
                    case 2 -> g.drawImage(whiteImageResize, x * CHECK_SIZE + 10, y * CHECK_SIZE + 10, this);
                    default -> {
                    }
                }
            }
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("x좌표 : " + e.getX() / CHECK_SIZE + " y좌표 : " + e.getY() / CHECK_SIZE);

        int x = e.getX() / CHECK_SIZE;
        int y = e.getY() / CHECK_SIZE;
        System.out.println(Check.check_x_left(x, y));

        if(!myTern && stones[y][x] == 0 && Check.check_x_left(x, y))
            stones[y][x] = 1;
        else if(stones[y][x] == 0 && Check.check_x_left(x, y))
            stones[y][x] = 2;
        else
            return;

        myTern = !myTern;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    void getImage()
    {
        Image whiteImage = whiteIcon.getImage();
        Image blackImage = blackIcon.getImage();

        whiteImageResize = whiteImage.getScaledInstance(STONE_SIZE, STONE_SIZE, Image.SCALE_SMOOTH);
        blackImageResize = blackImage.getScaledInstance(STONE_SIZE, STONE_SIZE, Image.SCALE_SMOOTH);

        whiteIcon = new ImageIcon(whiteImageResize);
        blackIcon = new ImageIcon(blackImageResize);
    }
}
