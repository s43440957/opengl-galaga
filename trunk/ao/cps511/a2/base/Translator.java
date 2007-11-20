package ao.cps511.a2.base;

import javax.media.opengl.GL;

/**
 *
 */
public class Translator
{
    //--------------------------------------------------------------------
    private final GL gl;


    //--------------------------------------------------------------------
    public Translator(GL gl)
    {
        this.gl = gl;
    }


    //--------------------------------------------------------------------
    public void translated(double x, double y, Runnable r)
    {
        gl.glTranslated(x, y, 0);

        r.run();

        gl.glTranslated(-x, -y, 0);
    }
}
