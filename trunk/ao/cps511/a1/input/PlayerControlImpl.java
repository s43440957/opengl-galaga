package ao.cps511.a1.input;

import java.awt.*;
import java.awt.event.*;

/**
 *
 */
public class PlayerControlImpl
        implements PlayerControl,
                   KeyListener,
                   MouseListener,
                   MouseMotionListener
{
    //--------------------------------------------------------------------
    private volatile boolean shotPending  = false;
    private volatile double  percentX     = 0;

    private volatile boolean isPaused = false;

//    private volatile int prevX = 0;

    //private final Component binding;


    //--------------------------------------------------------------------
    public PlayerControlImpl(Component listenTo)
    {
//        binding = listenTo;
//        binding.addKeyListener(         this );
//        binding.addMouseListener(       this );
//        binding.addMouseMotionListener( this );

        listenTo.addKeyListener(         this );
        listenTo.addMouseListener(       this );
        listenTo.addMouseMotionListener( this );
    }


    //--------------------------------------------------------------------
    public boolean shotPending()
    {
        boolean ret = shotPending;
        shotPending = false;
        return ret;
    }

    public double percentX()
    {
//        prevPercentX = percentX;
        return percentX;
    }

    public boolean isPaused()
    {
        return isPaused;
    }


    //--------------------------------------------------------------------
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_SPACE:
                shotPending = true;
                break;

            case KeyEvent.VK_LEFT:
                percentX -= 0.05;
                break;

            case KeyEvent.VK_RIGHT:
                percentX += 0.05;
                break;
        }
        percentX = Math.max(0, Math.min(1, percentX));
    }
    public void keyTyped(KeyEvent e)    {}
    public void keyReleased(KeyEvent e) {}


    //--------------------------------------------------------------------
    public void mouseClicked(MouseEvent e)
    {
        //System.out.println("mouse clicked");
//        shotPending = true;
    }

    public void mousePressed(MouseEvent e)
    {
        shotPending = true;
    }
    public void mouseReleased(MouseEvent e) {}


    //--------------------------------------------------------------------
    public void mouseEntered(MouseEvent e)
    {
        isPaused = false;
    }
    public void mouseExited(MouseEvent e)
    {
        isPaused = true;
    }

    public void mouseDragged(MouseEvent e)
    {
        mouseMoved(e);
    }
    public void mouseMoved(MouseEvent e)
    {
        int width  = e.getComponent().getWidth();
        percentX = ((double) e.getX()) / width;
    }
}
