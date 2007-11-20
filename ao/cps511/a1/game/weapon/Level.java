package ao.cps511.a1.game.weapon;

/**
 *
 */
public enum Level
{
    //--------------------------------------------------------------------
    NEWBIE(0),
    BEGINNER(1), // 10
    AVERAGE(100), // 10
    STRONG(110),  // 30
    PRO(120);    //50


    //--------------------------------------------------------------------
    private static Level reverseValues[] = new Level[values().length];
    static
    {
        for (int i = 0, j = reverseValues.length - 1;
                 i <        reverseValues.length;
                 i++,   j--)
        {
            reverseValues[ i ] = values()[ j ];
        }
    }


    //--------------------------------------------------------------------
    public static Level levelOf(double score)
    {
        for (Level level : reverseValues)
        {
            if (level.scoreRequired <= score)
            {
                return level;
            }
        }
        return BEGINNER;
    }


    //--------------------------------------------------------------------
    private final double scoreRequired;


    //--------------------------------------------------------------------
    private Level(double score)
    {
        scoreRequired = score;
    }
}
