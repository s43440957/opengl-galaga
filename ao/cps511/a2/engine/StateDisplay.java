package ao.cps511.a2.engine;

import ao.cps511.a2.base.OrthoRenderer;
import ao.cps511.a2.game.State;
import ao.cps511.a2.game.weapon.Level;
import ao.cps511.a2.input.PlayerControl;
import com.sun.opengl.util.j2d.TextRenderer;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.awt.*;

/**
 * Renders the running state of the game.
 */
public class StateDisplay extends OrthoRenderer
{
    //--------------------------------------------------------------------
    private final static int WIDTH  = 100;
    private final static int HEIGHT = 100;


    //--------------------------------------------------------------------
    private PlayerControl controller;
    private State         state;

    private TextRenderer text;

    private long timeMillis       = 0;
    private long prevDisplayStart = 0;


    //--------------------------------------------------------------------
    public StateDisplay(PlayerControl control)
    {
        super(WIDTH, HEIGHT);

        state      = new State();
        controller = control;

        text = new TextRenderer(new Font("SansSerif", Font.BOLD, 18));
    }


    //--------------------------------------------------------------------
    protected void display(final GL gl, final GLU glu)
    {
        try
        {
            if (controller == null) return;
            if (prevDisplayStart != 0 && !controller.isPaused())
            {
                timeMillis +=
                        System.currentTimeMillis() - prevDisplayStart;
            }
        }
        finally
        {
            prevDisplayStart = System.currentTimeMillis();
        }

        state.score();
        state = state.advance( controller );
        int score = (int)state.score();

        state.display( gl );

        long deltaSeconds = timeMillis/1000;

        text.beginRendering(WIDTH * 4, HEIGHT * 4);
        text.setColor(1.0f, 1.0f, 1.0f, 0.5f);
        text.draw("score " + score        + "    " +
                  "time "  + deltaSeconds + "    " +
                  "score/second " +
                     (double) Math.round(
                                (double)score / deltaSeconds * 100) / 100,
                  0, (int)(HEIGHT * 3.75));
        text.draw("level " + Level.levelOf(score),
                  0, (int)(HEIGHT * 3.50));
        text.endRendering();
    }


    //--------------------------------------------------------------------
//    public void controlWith(PlayerControl control)
//    {
//        controller = control;
//    }
}
