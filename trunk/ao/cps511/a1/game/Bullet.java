package ao.cps511.a1.game;

import ao.cps511.a1.base.Translator;
import ao.cps511.a1.base.Painter;
import ao.cps511.a1.game.common.Collidable;
import ao.cps511.a1.game.common.Visual;

import javax.media.opengl.GL;
import java.util.Collection;
import java.awt.*;

/**
 *
 */
public class Bullet implements Visual
{
    //--------------------------------------------------------------------
    private static final double halfWidth  = 0.5;
    private static final double halfHeight = 0.5;


    //--------------------------------------------------------------------
    private final double xPosition;
    private final double yPosition;
    private final double ySpeed;
    private final double xSpeed;

    private final boolean isPlayers;
    private final Color   colour;


    //--------------------------------------------------------------------
    public Bullet(double x,     double y,
                  double xStep, double yStep,
                  boolean shotByPlayer,
                  Color   shotColour)
    {
        xPosition = x;
        yPosition = y;
        xSpeed    = xStep;
        ySpeed    = yStep;
        isPlayers = shotByPlayer;
        colour    = shotColour;

    }

//    public Bullet(double x,     double y,
//                  double xStep, double yStep,
//                  boolean shotByPlayer)
//    {
//        this
//    }


    //--------------------------------------------------------------------
    public Particle leaveTrail()
    {
        return new Particle(xPosition, yPosition, 0.35, Color.RED); 
    }


    //--------------------------------------------------------------------
    public Bullet advance()
    {
        return new Bullet(xPosition + xSpeed,
                          yPosition + ySpeed,
                          xSpeed, ySpeed,
                          isPlayers,
                          colour);
    }


    //--------------------------------------------------------------------
    public boolean hits(Collidable target)
    {
        return !(target instanceof Enemy && !isPlayers) &&
               target.collidesWith(xPosition, yPosition);
    }
    public boolean hits(Collection<? extends Collidable> targets)
    {
        return (hitsTarget(targets) != null);
    }
    public <T extends Collidable> T hitsTarget(
            Collection<T> targets)
    {
        for (T target : targets)
        {
            if (hits(target)) return target;
        }
        return null;
    }

    public boolean inBounds()
    {
        return 0 <= yPosition && yPosition <= 100;
    }


    //--------------------------------------------------------------------
    public void display(final GL gl)
    {
        Translator t = new Translator(gl);
        t.translated(xPosition, yPosition, new Runnable() {
            public void run() {
                gl.glBegin(GL.GL_QUADS);

                //colour
                Painter.apply(gl, colour, 0.8);
                gl.glVertex2d(-halfWidth, halfHeight);
                gl.glVertex2d(halfWidth, halfHeight);
                gl.glVertex2d(halfWidth, -halfHeight);
                gl.glVertex2d(-halfWidth, -halfHeight);

                gl.glEnd();
            }
        });
    }

    public Explotion explode()
    {
        return new Explotion(xPosition, yPosition);
    }
}
