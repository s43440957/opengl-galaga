package ao.cps511.a1.game.weapon.impl.max;

import ao.cps511.a1.game.weapon.Weapon;
import ao.cps511.a1.game.Bullet;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: iscott
 * Date: May 29, 2008
 * Time: 6:21:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class RailGun implements Weapon
{
    public java.util.List<Bullet> shoot(
            double x,          double y,
            double xDirection, double yDirection,
            boolean shotByPlayer,
            Color colour)
    {
        return Arrays.asList(
                    new Bullet(x, y, xDirection, yDirection*10,
                               shotByPlayer, RandomColor()),
                    new Bullet(x, y+1, xDirection, yDirection*10,
                               shotByPlayer, RandomColor()),
                    new Bullet(x, y+2, xDirection, yDirection*10,
                               shotByPlayer, RandomColor()),
                    new Bullet(x, y+3, xDirection, yDirection*10,
                               shotByPlayer, RandomColor()),
                    new Bullet(x, y+4, xDirection, yDirection*10,
                               shotByPlayer, RandomColor()),
                    new Bullet(x, y+5, xDirection, yDirection*10,
                               shotByPlayer, RandomColor()),
                    new Bullet(x, y+6, xDirection, yDirection*10,
                               shotByPlayer, RandomColor()),
                    new Bullet(x, y+7, xDirection, yDirection*10,
                               shotByPlayer, RandomColor()),
                    new Bullet(x, y+8, xDirection, yDirection*10,
                               shotByPlayer, RandomColor()),
                    new Bullet(x, y+9, xDirection, yDirection*10,
                               shotByPlayer, RandomColor()),
                    new Bullet(x, y+10, xDirection, yDirection*10,
                               shotByPlayer, RandomColor())
                    );
    }
    public static int ColorComponent()
    {return(int)(Math.random()*256);


    }
    public static Color RandomColor()
    {return new Color(ColorComponent(), ColorComponent(), ColorComponent());}

}
