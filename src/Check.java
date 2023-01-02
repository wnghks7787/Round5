public class Check {

    int x, y;
    int color;

    public Check(int x, int y, int color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public boolean checking()
    {
        boolean check = false;
        boolean[] checkTmp = new boolean[8];

        checkTmp[0] = check_x_left();
        checkTmp[1] = check_x_right();
        checkTmp[2] = check_y_up();
        checkTmp[3] = check_y_down();
        checkTmp[4] = check_xy_upLeft();
        checkTmp[5] = check_xy_upRight();
        checkTmp[6] = check_xy_downLeft();
        checkTmp[7] = check_xy_downRight();

        for(int i = 0 ; i < 8 ; i++)
        {
            if(checkTmp[i])
                check = true;
        }

        if(checkTmp[0])
            reverse_x_left();
        if(checkTmp[1])
            reverse_x_right();
        if(checkTmp[2])
            reverse_y_up();
        if(checkTmp[3])
            reverse_y_down();
        if(checkTmp[4])
            reverse_xy_upLeft();
        if(checkTmp[5])
            reverse_xy_upRight();
        if(checkTmp[6])
            reverse_xy_downLeft();
        if(checkTmp[7])
            reverse_xy_downRight();

        return check;
    }

    public boolean check_x_left()
    {
        for(int i = x - 1 ; i >= 0 ; i--)
        {
            if(GamePanel.stones[y][i] == 0)
                return false;

            if(GamePanel.stones[y][i] == color)
            {
                if(i == x - 1)
                    return false;
                else
                {
                    System.out.println("x_left : true");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check_x_right()
    {
        for(int i = x + 1 ; i < 8 ; i++)
        {
            if(GamePanel.stones[y][i] == 0)
                return false;

            if(GamePanel.stones[y][i] == color)
            {
                if(i == x + 1)
                    return false;
                else
                {
                    System.out.println("x_right : true");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check_y_up()
    {
        for(int i = y - 1 ; i >= 0 ; i--)
        {
            if(GamePanel.stones[i][x] == 0)
                return false;

            if(GamePanel.stones[i][x] == color)
            {
                if(i == y - 1)
                    return false;
                else
                {
                    System.out.println("y_up : true");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check_y_down()
    {
        for(int i = y + 1 ; i < 8 ; i++)
        {
            if (GamePanel.stones[i][x] == 0)
                return false;

            if (GamePanel.stones[i][x] == color) {
                if (i == y + 1)
                    return false;
                else {
                    System.out.println("y_down : true");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check_xy_upLeft()
    {
        int i, j;

        for(i = y - 1, j = x - 1 ; i >= 0 && j >= 0 ; i--, j--)
        {
            if(GamePanel.stones[i][j] == 0)
                return false;

            if(GamePanel.stones[i][j] == color) {
                if(i == y - 1 || j == x - 1)
                    return false;
                else
                {
                    System.out.println("xy_upLeft : true");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check_xy_upRight()
    {
        int i, j;

        for(i = y - 1, j = x + 1 ; i >= 0 && j < 8 ; i--, j++)
        {
            if(GamePanel.stones[i][j] == 0)
                return false;

            if(GamePanel.stones[i][j] == color) {
                if(i == y - 1 || j == x + 1)
                    return false;
                else
                {
                    System.out.println("xy_upRight : true");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check_xy_downLeft()
    {
        int i, j;

        for(i = y + 1, j = x - 1 ; i < 8 && j >= 0; i++, j--)
        {
            if(GamePanel.stones[i][j] == 0)
                return false;

            if(GamePanel.stones[i][j] == color)
            {
                if(i == y + 1 || y == x - 1)
                    return false;
                else
                {
                    System.out.println("xy_downLeft : true");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check_xy_downRight()
    {
        int i, j;

        for(i = y + 1, j = x + 1 ; i < 8 && j < 8 ; i++, j++)
        {
            if(GamePanel.stones[i][j] == 0)
                return false;

            if(GamePanel.stones[i][j] == color)
            {
                if(i == y + 1 || j == x + 1)
                    return false;
                else
                {
                    System.out.println("xy_downRight : true");
                    return true;
                }
            }
        }
        return false;
    }

    public void reverse_x_left()
    {
        for(int i = x - 1 ; i >= 0 ; i--)
        {
            if(GamePanel.stones[y][i] != color)
                GamePanel.stones[y][i] *= (-1);
            else
                return;
        }
    }

    public void reverse_x_right()
    {
        for(int i = x + 1 ; i < 8 ; i++)
        {
            if(GamePanel.stones[y][i] != color)
                GamePanel.stones[y][i] *= (-1);
            else
                return;
        }
    }

    public void reverse_y_up()
    {
        for(int i = y - 1 ; i >= 0 ; i--)
        {
            if(GamePanel.stones[i][x] != color)
                GamePanel.stones[i][x] *= (-1);
            else
                return;
        }
    }

    public void reverse_y_down()
    {
        for(int i = y + 1 ; i < 8 ; i++)
        {
            if(GamePanel.stones[i][x] != color)
                GamePanel.stones[i][x] *= (-1);
            else
                return;
        }
    }

    public void reverse_xy_upLeft()
    {
        int i, j;

        for(i = y - 1, j = x - 1 ; i >= 0 && j >= 0 ; i--, j--)
        {
            if(GamePanel.stones[i][j] != color)
                GamePanel.stones[i][j] *= (-1);
            else
                return;
        }
    }

    public void reverse_xy_upRight()
    {
        int i, j;

        for(i = y - 1, j = x + 1 ; i >= 0 && j < 8 ; i--, j++)
        {
            if(GamePanel.stones[i][j] != color)
                GamePanel.stones[i][j] *= (-1);
            else
                return;
        }
    }

    public void reverse_xy_downLeft()
    {
        int i, j;

        for(i = y + 1, j = x - 1 ; i < 8 && j >= 0 ; i++, j--)
        {
            if(GamePanel.stones[i][j] != color)
                GamePanel.stones[i][j] *= (-1);
            else
                return;
        }
    }

    public void reverse_xy_downRight()
    {
        int i, j;

        for(i = y + 1, j = x + 1 ; i < 8 && j < 8 ; i++, j++)
        {
            if(GamePanel.stones[i][j] != color)
                GamePanel.stones[i][j] *= (-1);
            else
                return;
        }
    }
}
