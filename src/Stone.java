import javax.swing.*;
import java.awt.*;

public class Stone {
    Point point;
    int color = -1;

    public Stone(Point point, int color)
    {
        this.point = point;
        this.color = color;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
