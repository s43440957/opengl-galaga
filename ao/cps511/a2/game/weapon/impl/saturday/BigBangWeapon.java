package ao.cps511.a2.game.weapon.impl.saturday;

import ao.cps511.a2.game.Bullet;
import ao.cps511.a2.game.weapon.Weapon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BigBangWeapon implements Weapon
{
    public List<Bullet> shoot(double x,          double y,
                              double xDirection, double yDirection,
                              boolean shotByPlayer,
                              Color colour)
    {
        List<Bullet> bullets = new ArrayList<Bullet>();

        bullets.add( new Bullet(x - 20, y,
                                xDirection+1, yDirection,
                                shotByPlayer, colour) );
        bullets.add( new Bullet(x + 20, y,
                                xDirection-0.5, yDirection,
                                shotByPlayer, colour) );
        bullets.add( new Bullet(x + 0, y,
                               xDirection, yDirection,
                               shotByPlayer, colour) );
        bullets.add( new Bullet(x + 25, y,
                                       xDirection, yDirection,
                                       shotByPlayer, colour) );
        bullets.add( new Bullet(x - 25, y,
                                       xDirection, yDirection,
                                       shotByPlayer, colour) );

        return bullets;
    }
}