package ao.cps511.a1.game.weapon.impl.max;

import ao.cps511.a1.game.Bullet;
import ao.cps511.a1.game.weapon.Weapon;

import java.awt.*;
import java.util.*;

/**
 *
 */
public class Pistol  implements Weapon
{
    public java.util.List<Bullet> shoot(
            double x,          double y,
            double xDirection, double yDirection,
            boolean shotByPlayer,
            Color colour)
    {
        return Arrays.asList(
                    new Bullet(x+5, y, xDirection-0.5, yDirection+0.5,
                               shotByPlayer, new Color(255, 0, 255)),
                    new Bullet(x-5, y, xDirection+0.5, yDirection+0.5,
                               shotByPlayer, new Color(255, 0, 255)));
    }
}
