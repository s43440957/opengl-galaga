package ao.cps511.a1.game.weapon;

import ao.cps511.a1.game.Bullet;
import ao.cps511.a1.game.weapon.impl.RandomFire;
import ao.cps511.a1.game.weapon.impl.SinglePhoton;
import ao.cps511.a1.game.weapon.impl.ivan.CrazyGun;
import ao.cps511.a1.game.weapon.impl.ivan.InsaneGun;
import ao.cps511.a1.game.weapon.impl.saturday.BigBangWeapon;

import java.awt.*;
import java.util.EnumMap;
import java.util.List;

/**
 *
 */
public class MetaWeapon
        implements Weapon, ScoreListener
{
    //--------------------------------------------------------------------
    private static EnumMap<Level, Weapon> weaponsByLevel =
            new EnumMap<Level, Weapon>(Level.class) {{
                put(Level.NEWBIE, new SinglePhoton());
                put(Level.BEGINNER, new BigBangWeapon());
                put(Level.AVERAGE, new CrazyGun());
                put(Level.STRONG, new InsaneGun());
                put(Level.PRO, new RandomFire());

//                put(Level.NEWBIE, new SinglePhoton());
//                put(Level.BEGINNER, new SinglePhoton());
//                put(Level.AVERAGE, new DoublePhoton());
//                put(Level.STRONG, new XrossAction());
//                put(Level.PRO, new RandomFire());
            }};


    //--------------------------------------------------------------------
    private double score;


    //--------------------------------------------------------------------
    public List<Bullet> shoot(double x,          double y,
                              double XDirection, double yDirection,
                              boolean shotByPlayer,
                              Color colour)
    {
        return weaponsByLevel.get( Level.levelOf(score) ).shoot(
                                    x, y, XDirection, yDirection,
                                    shotByPlayer,
                                    colour);
    }


    //--------------------------------------------------------------------
    public void currentScore(double score)
    {
        this.score = score;
    }
}
