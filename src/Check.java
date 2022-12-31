public class Check {

    public static boolean check_x_left(int x, int y)
    {
        int color;

        if(GamePanel.myTern)
            color = 2;
        else
            color = 1;

        for(int i = x - 1 ; i >= 0 ; i--)
        {
            if(GamePanel.stones[y][i] == 0)
                return false;

            if(GamePanel.stones[y][i] == color)
            {
                if(i == x - 1)
                    return false;

                return true;
            }
        }
        return false;

    }
}
