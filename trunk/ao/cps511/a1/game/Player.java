package ao.cps511.a1.game;

import ao.cps511.a1.base.Translator;
import ao.cps511.a1.game.common.Collidable;
import ao.cps511.a1.game.common.Visual;
import ao.cps511.a1.game.weapon.MetaWeapon;
import ao.cps511.a1.game.weapon.ScoreListener;
import ao.cps511.a1.input.PlayerControl;

import javax.media.opengl.GL;
import java.util.List;
import java.awt.*;

/**
 *
 */
public class Player
        implements Visual,
                   Collidable,
                   ScoreListener
{
    //--------------------------------------------------------------------
    private static final float halfWidth  = 5;
    private static final float halfHeight = 2;

    private static final float yGap = 3;


    //--------------------------------------------------------------------
    private final double  centerX;
    private final boolean isHit;

    private MetaWeapon weapon = new MetaWeapon();


    //--------------------------------------------------------------------
    public Player()
    {
        centerX = 50;
        isHit   = false;
    }

    private Player(double centerAt, boolean isHit)
    {
        this.centerX = centerAt;
        this.isHit   = isHit;
    }


    //--------------------------------------------------------------------
    public Player advance(PlayerControl control, boolean isHit)
    {
        return new Player( control.percentX()*100, isHit);
    }

    public List<Bullet> shoot()
    {
        return weapon.shoot(centerX, yGap + 2 * halfHeight + 1, 0, 1,
                            true, Color.DARK_GRAY);
    }

    public Particle leaveTrail()
    {
        return new Particle(centerX, yGap - 1);
    }


    //--------------------------------------------------------------------
    public void display(final GL gl)
    {
        Translator t = new Translator(gl);
        t.translated(centerX, yGap, new Runnable() {
            public void run() {
                gl.glBegin(GL.GL_QUADS);

                if (isHit) gl.glColor3f(1, 0, 0);
                else       gl.glColor3f(1, 0, 0);
                gl.glVertex2f(-halfWidth, halfHeight); // Top Left

                if (isHit) gl.glColor3f(1, 0, 0);
                else       gl.glColor3f(0, 1, 0);
                gl.glVertex2f(halfWidth, halfHeight); // Top Right

                if (isHit) gl.glColor3f(1, 0, 0);
                else       gl.glColor3d(0, 0, .2);
                gl.glVertex2f(halfWidth, -halfHeight); // Bottom Right

                if (isHit) gl.glColor3f(1, 0, 0);
                else       gl.glColor3d(.2, .2, 0);
                gl.glVertex2f(-halfWidth, -halfHeight); // Bottom Left

                gl.glEnd();
            }
        });
    }

    public boolean collidesWith(double pointX, double pointY)
    {
        double x = centerX;
        double y = yGap + halfHeight;

        return (x - halfWidth  <= pointX && pointX <= x + halfWidth) &&
               (y - halfHeight <= pointY && pointY <= y + halfHeight);
    }


    //--------------------------------------------------------------------
    public void currentScore(double score)
    {
        weapon.currentScore( score );
    }
}
