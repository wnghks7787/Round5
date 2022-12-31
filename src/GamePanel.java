import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel()
    {
        setBounds(50, 50, 600, 600);
        setBackground(Color.white);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 게임 판 그리기
        for(int i = 0 ; i <= 600 ; i += 75)
        {
            g.drawLine(0, i, 600, i);
            g.drawLine(i, 0, i, 600);
        }
    }
}
