package ao.cps511.a2.game.weapon.impl;

import ao.cps511.a2.game.Bullet;
import ao.cps511.a2.game.weapon.Weapon;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class SinglePhoton implements Weapon
{
    public List<Bullet> shoot(double x,          double y,
                              double xDirection, double yDirection,
                              boolean shotByPlayer,
                              Color colour)
    {
        return Collections.singletonList(
                    new Bullet(x, y, xDirection, yDirection,
                               shotByPlayer, colour));
    }
}
