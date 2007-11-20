package ao.cps511.a2.run;

import ao.cps511.a2.engine.Galaga;

/**
 * Entry point for the galaga game.
 */
public class GalagaStarter
{
    //--------------------------------------------------------------------
    private GalagaStarter() {}


    //--------------------------------------------------------------------
    public static void main(String[] args)
    {
        new Galaga().start();
    }
}
