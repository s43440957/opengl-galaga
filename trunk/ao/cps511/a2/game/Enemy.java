package ao.cps511.a2.game;

import ao.cps511.a2.base.Translator;
import ao.cps511.a2.game.common.Collidable;
import ao.cps511.a2.game.common.Visual;
import ao.cps511.a2.game.weapon.MetaWeapon;
import ao.cps511.a2.game.weapon.ScoreListener;

import javax.media.opengl.GL;
import java.awt.*;
import java.util.List;

/**
 *
 */
public class Enemy
        implements Visual,
                   Collidable,
                   ScoreListener
{
    //--------------------------------------------------------------------
    private static final double halfWidth  = 3;
    private static final double halfHeight = 1;

    private static final int MIN_COLOUR_COMPONENT = 60;
    private static final int MAX_COLOUR_COMPONENT = 120; // 255


    //--------------------------------------------------------------------
    private final double x;
    private final double y;

    private final double  pivotX;
    private final double  pivotY;
    private final double  pivotPercentLeft;
    private final boolean clockwise;

    private final double yDelta;
    private final Color  colour;
    private final Color  frontColour;

    private MetaWeapon weapon = new MetaWeapon();


    //--------------------------------------------------------------------
    public Enemy(double initialX, double initialY)
    {
        this(initialX, initialY, -1, -1, 0,
             Math.random() < 0.5,
             -(0.05 + Math.random()/6),
             randomColour());
    }
    private Enemy(double centerX, double centerY,
                  double pivotX,  double pivotY,
                  double pivotPercentLeft,
                  boolean clockwise,
                  double  yDelta,
                  Color   colour)
    {
        this.x = centerX;
        this.y = centerY;

        this.pivotX = pivotX;
        this.pivotY = pivotY;

        this.pivotPercentLeft = pivotPercentLeft;

        this.clockwise   = clockwise;
        this.yDelta      = yDelta;
        this.colour      = colour;
        this.frontColour = colour.darker().darker().darker();
    }

    public static Color randomColour()
    {
        return new Color(randomColourComponent(),
                         randomColourComponent(),
                         randomColourComponent());
    }

    private static int randomColourComponent()
    {
        return (int)(MIN_COLOUR_COMPONENT +
                     Math.random() * (
                            MAX_COLOUR_COMPONENT -
                            MIN_COLOUR_COMPONENT + 1));
    }


    //--------------------------------------------------------------------
    public boolean collidesWith(double pointX, double pointY)
    {
        return (x - halfWidth  <= pointX && pointX <= x + halfWidth) &&
               (y - halfHeight <= pointY && pointY <= y + halfHeight);
    }

    public boolean isLost()
    {
        return x < -50 || x > 150 ||
               y < -10 || y > 110;
    }


    //--------------------------------------------------------------------
    public List<Bullet> shoot()
    {
        return weapon.shoot(x, y - halfHeight * 2 - 1, 0, -1,
                            false, Color.RED);
    }

    public Particle leaveTrail()
    {
        return new Particle(x, y + halfHeight);
    }


    //--------------------------------------------------------------------
    public void currentScore(double score)
    {
        weapon.currentScore(Math.max(0, score - 50));
    }


    //--------------------------------------------------------------------
    public Enemy advance(boolean startPivot)
    {
        double nextPivotPercentLeft =
                (startPivot)
                ? (pivotPercentLeft) > 0
                   ? Math.max(pivotPercentLeft, Math.random() * 100)
                   : 50 + Math.random() * 100
                : pivotPercentLeft - 0.5;

        double nextPivotX =
                (startPivot) ? x + (Math.random() - 1)*15 : pivotX;
        double nextPivotY =
                (startPivot) ? y + (Math.random() - 1)*15 : pivotY;

        nextPivotX = Math.min(100, Math.max(0, nextPivotX));
        nextPivotY = Math.min(100, Math.max(0, nextPivotY));

        double nextX = x;
        double nextY = y + yDelta;
        if (nextPivotPercentLeft > 0)
        {
            double pivotDeltaX = x - nextPivotX;
            double pivotDeltaY = y - nextPivotY;

            double angle = (Math.PI / 180) * 5
                            *(1.01 - nextPivotPercentLeft / 100);

            double rotateX, rotateY;
            if (clockwise)
            {
                rotateX = (pivotDeltaX * Math.cos(angle) -
                            pivotDeltaY * Math.sin(angle));
                rotateY = (pivotDeltaX * Math.sin(angle) +
                            pivotDeltaY * Math.cos(angle));
            }
            else
            {
                rotateX =  (pivotDeltaX * Math.cos(angle) +
                             pivotDeltaY * Math.sin(angle));
                rotateY = (-pivotDeltaX * Math.sin(angle) +
                             pivotDeltaY * Math.cos(angle));
            }

            nextX += (rotateX - pivotDeltaX);
            nextY += (rotateY - pivotDeltaY);
        }
        else
        {
            nextX += (Math.random() - 0.5)/10;
        }

        boolean nextClockwise = (nextPivotPercentLeft > 0)
                                 ? clockwise
                                 : (Math.random() < 0.5);
        return new Enemy(nextX, nextY,
                         nextPivotX, nextPivotY,
                         nextPivotPercentLeft,
                         nextClockwise,
                         yDelta, colour);
    }


    //--------------------------------------------------------------------
    public void display(final GL gl)
    {
        Translator t = new Translator(gl);
        t.translated(x, y, new Runnable() {
            public void run() {
                gl.glBegin(GL.GL_QUADS);

                gl.glColor3d(colour.getRed()   / 255.0,
                             colour.getGreen() / 255.0,
                             colour.getBlue()  / 255.0);
                gl.glVertex2d(-halfWidth,  halfHeight);
                gl.glVertex2d( halfWidth,  halfHeight);

                gl.glColor3d(frontColour.getRed()   / 255.0,
                             frontColour.getGreen() / 255.0,
                             frontColour.getBlue()  / 255.0);
                gl.glVertex2d( halfWidth, -halfHeight);
                gl.glVertex2d(-halfWidth, -halfHeight);

                gl.glEnd();
            }
        });
    }
}
