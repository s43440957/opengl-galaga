package ao.cps511.a1.game.weapon.impl;

import ao.cps511.a1.game.Bullet;
import ao.cps511.a1.game.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

/**
 *
 */
public class DoublePhoton implements Weapon
{
    public List<Bullet> shoot(double x,          double y,
                              double xDirection, double yDirection,
                              boolean shotByPlayer,
                              Color   colour)
    {
        List<Bullet> bullets = new ArrayList<Bullet>();

        bullets.add( new Bullet(x - 1, y,
                                xDirection, yDirection,
                                shotByPlayer, colour) );
        bullets.add( new Bullet(x + 1, y,
                                xDirection, yDirection,
                                shotByPlayer, colour) );

        return bullets;
    }
}

