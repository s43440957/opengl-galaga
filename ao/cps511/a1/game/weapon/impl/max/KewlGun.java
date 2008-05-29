package ao.cps511.a1.game.weapon.impl.max;

import ao.cps511.a1.game.Bullet;
import ao.cps511.a1.game.weapon.Weapon;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class KewlGun implements Weapon
{
    public List<Bullet> shoot(
            double x, double y, double XDirection, double yDirection, boolean shotByPlayer, Color colour)
    {
        List<Weapon> weapons = Arrays.asList(
                new RailGun(),
                new Shotgun());
        int index = (int)( Math.random() * weapons.size() );
        return weapons.get( index ).shoot(
                x, y, XDirection, yDirection, shotByPlayer, colour);
    }
}
