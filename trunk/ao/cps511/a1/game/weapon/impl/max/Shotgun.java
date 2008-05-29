package ao.cps511.a1.game.weapon.impl.max;

import ao.cps511.a1.game.weapon.Weapon;
import ao.cps511.a1.game.Bullet;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: iscott
 * Date: May 22, 2008
 * Time: 6:32:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Shotgun implements Weapon
{
    public java.util.List<Bullet> shoot(
            double x, double y,
            double xDirection, double yDirection,
            boolean shotByPlayer,
            Color colour)
    {
        return Arrays.asList(
                RandomShot(x, y, xDirection, yDirection, shotByPlayer),
                RandomShot(x, y, xDirection, yDirection, shotByPlayer),
                RandomShot(x, y, xDirection, yDirection, shotByPlayer),
                RandomShot(x, y, xDirection, yDirection, shotByPlayer));
    }

    public static int ColorComponent()
    {return(int)(Math.random()*256);


    }
    public static Color RandomColor()
    {return new Color(ColorComponent(), ColorComponent(), ColorComponent());}


    public static Bullet RandomShot(
            double x, double y,
            double xDirection, double yDirection,
            boolean shotByPlayer)
    {
    return new Bullet(x,y,
                           xDirection + (Math.random() -0.5)/2,
                           yDirection + (Math.random())/2,
                           shotByPlayer, RandomColor());
    }


}
