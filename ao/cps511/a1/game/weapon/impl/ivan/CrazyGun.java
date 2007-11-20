package ao.cps511.a1.game.weapon.impl.ivan;

import ao.cps511.a1.game.Bullet;
import ao.cps511.a1.game.weapon.Weapon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CrazyGun implements Weapon
{
    public List<Bullet> shoot(double x,          double y,
                              double xDirection, double yDirection,
                              boolean shotByPlayer,
                              Color colour)
    {
        List<Bullet> bullets = new ArrayList<Bullet>();

        bullets.add(new Bullet(x, y, xDirection, yDirection,
                               shotByPlayer, colour));
        bullets.add(new Bullet(x + 5, y, xDirection, yDirection,
                               shotByPlayer, colour));
        bullets.add(new Bullet(x -5, y, xDirection, yDirection,
                               shotByPlayer, colour));
//        return Collections.singletonList(
//                    new Bullet(x, y, xDirection, yDirection,
//                               shotByPlayer, colour));
        return bullets;
    }
}
