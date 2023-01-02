import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    JLabel blackScore;
    JLabel blackInt;
    JLabel whiteScore;
    JLabel whiteInt;
    JLabel tern;

    public MenuPanel()
    {
        setBounds(700, 0, 200, 700);
        setBackground(Color.white);
        setLayout(null);

        addBlackScoreLabel();
        addWhiteScoreLabel();
    }

    void addBlackScoreLabel()
    {
        blackScore = new JLabel("Black Score");
        blackInt = new JLabel("2");

        add(blackScore);
        add(blackInt);

        blackScore.setBounds(30, 30, 100, 50);
        blackInt.setBounds(30, 80, 100, 50);
    }

    void addWhiteScoreLabel()
    {
        whiteScore = new JLabel("White Score");
        whiteInt = new JLabel("2");

        add(whiteScore);
        add(whiteInt);

        whiteScore.setBounds(30, 130, 100, 50);
        whiteInt.setBounds(30, 180, 100, 50);
    }
}
