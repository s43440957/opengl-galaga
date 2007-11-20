package ao.cps511.a1.game;

import ao.cps511.a1.base.Painter;
import ao.cps511.a1.base.Translator;
import ao.cps511.a1.game.common.Visual;

import javax.media.opengl.GL;
import java.awt.*;

/**
 *
 */
public class Particle implements Visual
{
    //--------------------------------------------------------------------
    private static final double halfWidth  = 0.25;
    private static final double halfHeight = 0.25;


    //--------------------------------------------------------------------
    private final double xPosition;
    private final double yPosition;
    private final double scale;

    private final Color colour;


    //--------------------------------------------------------------------
    public Particle(double x, double y)
    {
        this(x, y, 1.0);
    }
    public Particle(double x, double y, double scale)
    {
        this(x, y, scale, Color.GRAY);
    }
    public Particle(double x, double y, double scale, Color colour)
    {
        xPosition  = x;
        yPosition  = y;

        this.scale  = scale;
        this.colour = colour;
    }


    //--------------------------------------------------------------------
    public Particle advance()
    {
        return new Particle(xPosition,
                            yPosition,
                            scale * 0.95,
                            colour);
    }


    //--------------------------------------------------------------------
    public boolean disapated()
    {
        return scale < 0.05;
    }


    //--------------------------------------------------------------------
    public void display(final GL gl)
    {
        Translator t = new Translator(gl);
        t.translated(xPosition, yPosition, new Runnable() {
            public void run() {
                gl.glBegin(GL.GL_QUADS);

                double scaledHalfWidth  = halfWidth  * scale;
                double scaledHalfHeight = halfHeight * scale;

                Painter.apply(gl, colour, 0.2);
                gl.glVertex2d(-scaledHalfWidth,  scaledHalfHeight);
                gl.glVertex2d( scaledHalfWidth,  scaledHalfHeight);
                gl.glVertex2d( scaledHalfWidth, -scaledHalfHeight);
                gl.glVertex2d(-scaledHalfWidth, -scaledHalfHeight);

                gl.glEnd();
            }
        });
    }
}

