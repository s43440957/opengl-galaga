package ao.cps511.a1.game.weapon.impl;

import ao.cps511.a1.game.Bullet;
import ao.cps511.a1.game.weapon.Weapon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class XrossAction implements Weapon
{
    public List<Bullet> shoot(double x,          double y,
                              double xDirection, double yDirection,
                              boolean shotByPlayer,
                              Color   colour)
    {
        List<Bullet> bullets = new ArrayList<Bullet>();

        bullets.add( new Bullet(x, y,
                                xDirection, yDirection,
                                shotByPlayer,
                                colour.brighter().brighter().brighter()));

        bullets.add( new Bullet(x - 2, y,
                                xDirection + 0.2, yDirection,
                                shotByPlayer,
                                colour.darker().darker().darker()));
        bullets.add( new Bullet(x + 2, y,
                                xDirection - 0.2, yDirection,
                                shotByPlayer,
                                colour.darker().darker().darker()) );

        return bullets;
    }
}

