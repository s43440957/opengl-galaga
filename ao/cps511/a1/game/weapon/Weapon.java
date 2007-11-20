package ao.cps511.a1.game.weapon;

import ao.cps511.a1.game.Bullet;

import java.awt.*;
import java.util.List;

/**
 *
 */
public interface Weapon
{
    public List<Bullet> shoot(double x,          double y,
                              double XDirection, double yDirection,
                              boolean shotByPlayer,
                              Color   colour);

//    public List<Bullet> shoot(double x,          double y,
//                              double XDirection, double yDirection);
}
