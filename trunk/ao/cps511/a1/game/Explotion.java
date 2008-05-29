package ao.cps511.a1.game;

import ao.cps511.a1.base.Translator;
import ao.cps511.a1.game.common.Visual;

import javax.media.opengl.GL;
import java.awt.geom.Point2D;

/**
 *
 */
public class Explotion implements Visual
{
    //--------------------------------------------------------------------
    private static final int    STEPS      = 40;

    private static final double halfWidth  = 1.25;
    private static final double halfHeight = 1.25;


    //--------------------------------------------------------------------
    private final double x;
    private final double y;

    private final int stepsLeft;

    private final Point2D[] flames;
    

    //--------------------------------------------------------------------
    public Explotion(double centerX, double centerY)
    {
        this(centerX, centerY, STEPS,
             randomFlames(centerX, centerY));
    }

    private Explotion(
            double    centerX,
            double    centerY,
            int       countdown,
            Point2D[] nextFlames)
    {
        x = centerX;
        y = centerY;

        stepsLeft = countdown;
        flames    = nextFlames;
    }

    private static Point2D[]
            randomFlames(double centerX, double centerY)
    {
        Point2D[] flames = new Point2D[ (int)(50 + Math.random()*20) ];
        for (int i = 0; i < flames.length; i++)
        {
            flames[i] = new Point2D.Double(centerX + Math.random() - 0.5,
                                           centerY + Math.random() - 0.5);
        }
        return flames;
    }



    //--------------------------------------------------------------------
    public boolean isBurning()
    {
        return stepsLeft > 0;
    }


    //--------------------------------------------------------------------
    public Explotion advance()
    {
        Point2D nextFlames[] = new Point2D[ flames.length ];

        for (int i = 0; i < nextFlames.length; i++)
        {
            double centerDeltaX = flames[i].getX() - x;
            double centerDeltaY = flames[i].getY() - y;

            nextFlames[ i ] =
                    new Point2D.Double(
                            flames[i].getX() + centerDeltaX/10,
                            flames[i].getY() + centerDeltaY/10);
        }

        return new Explotion(x, y, stepsLeft - 1, nextFlames);
    }


    //--------------------------------------------------------------------
    public void display(GL gl)
    {
        for (Point2D flame : flames)
        {
            displayFlame(flame, gl);
        }
    }

    private void displayFlame(final Point2D flame, final GL gl)
    {
        final double scale = Math.pow(0.99, (STEPS-stepsLeft));

        Translator t = new Translator(gl);
        t.translated(flame.getX(), flame.getY(), new Runnable() {
            public void run() {
                gl.glBegin(GL.GL_QUADS);

                gl.glColor4d(
                        Math.random(), Math.random(), Math.random(), 0.5);

                double scaledHalfWidth  = halfWidth  * scale;
                double scaledHalfHeight = halfHeight * scale;

                gl.glVertex2d(-scaledHalfWidth,  scaledHalfHeight);
                gl.glVertex2d( scaledHalfWidth,  scaledHalfHeight);
                gl.glVertex2d( scaledHalfWidth, -scaledHalfHeight);
                gl.glVertex2d(-scaledHalfWidth, -scaledHalfHeight);

                gl.glEnd();
            }
        });
    }
}
