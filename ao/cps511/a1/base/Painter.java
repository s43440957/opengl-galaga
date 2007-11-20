package ao.cps511.a1.base;

import javax.media.opengl.GL;
import java.awt.*;

/**
 * work with applying colours.
 */
public class Painter
{
    //--------------------------------------------------------------------
    private Painter() {}


    //--------------------------------------------------------------------
    public static void apply(GL gl, Color colour)
    {
        apply(gl, colour, 1.0);
    }

    public static void apply(GL gl, Color colour, double opacity)
    {
        gl.glColor4d(colour.getRed()   / 255.0,
                     colour.getGreen() / 255.0,
                     colour.getBlue()  / 255.0,
                     opacity);
    }

}
