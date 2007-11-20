package ao.cps511.a1.game;

import ao.cps511.a1.input.PlayerControl;
import ao.cps511.a1.game.common.Collidable;
import ao.cps511.a1.game.common.Visual;

import javax.media.opengl.GL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
public class State implements Visual
{
    //--------------------------------------------------------------------
    private final double                score;
    private final Player                player;
    private final Collection<Enemy>     enemies;
    private final Collection<Bullet>    bullets;
    private final Collection<Particle>  particles;
    private final Collection<Explotion> explotions;
    

    //--------------------------------------------------------------------
    public State()
    {
        score      = 0;
        player     = new Player();
        enemies    = new ArrayList<Enemy>();
        bullets    = new ArrayList<Bullet>();
        particles  = new ArrayList<Particle>();
        explotions = new ArrayList<Explotion>();

        for (int i = 1; i <= 5; i++)
        {
            enemies.add(spawnEnemy());
        }
    }

    private State(Player                copyPlayer,
                  Collection<Enemy>     copyEnemies,
                  Collection<Bullet>    copyBullets,
                  double                copyScore,
                  Collection<Particle>  copyParticles,
                  Collection<Explotion> copyExplotions)
    {
        score      = copyScore;
        player     = copyPlayer;
        enemies    = copyEnemies;
        bullets    = copyBullets;
        particles  = copyParticles;
        explotions = copyExplotions;
    }


    //--------------------------------------------------------------------
    public State advance(PlayerControl control)
    {
        if (control.isPaused()) return this;

        List<Bullet> nextBullets = moveBulletsForeward();
        Player       nextPlayer  = nextPlayer(control, nextBullets);
        List<Enemy>  nextEnemies = nextEnemies(nextBullets);
        
        advanceAttackWave(nextEnemies);

        publishScore(nextPlayer, nextEnemies);
        playerShot(control, nextBullets, nextPlayer);
        enemyShots(nextEnemies, nextBullets);

        List<Explotion> nextExplotions = nextExplotions();
        double nextScore = Math.max(0, score +
                collectBullectHits(nextBullets, nextExplotions));
        collectBullectLostEnemies(nextEnemies);

        return new State(nextPlayer, nextEnemies, nextBullets, nextScore,
                         nextParticles(nextBullets, nextEnemies),
                         nextExplotions);
    }

    //--------------------------------------------------------------------
    private void publishScore(Player       nextPlayer,
                              List<Enemy>  nextEnemies)
    {
        nextPlayer.currentScore( score );
        for (Enemy enemy : nextEnemies)
        {
            enemy.currentScore( score );
        }
    }

    //--------------------------------------------------------------------
    private List<Bullet> moveBulletsForeward()
    {
        List<Bullet> nextBullets = new ArrayList<Bullet>();
        for (Bullet bullet : bullets)
        {
            if (bullet.inBounds())
            {
                nextBullets.add( bullet.advance() );
            }
        }
        return nextBullets;
    }

    //--------------------------------------------------------------------
    private Player nextPlayer(
            PlayerControl control,
            List<Bullet>  nextBullets)
    {
        return player.advance(
                        control,
                        hit(nextBullets, player));
    }

    private List<Enemy> nextEnemies(
            List<Bullet> nextBullets)
    {
        List<Enemy> nextEnemies = new ArrayList<Enemy>();

        for (Enemy enemy : enemies)
        {
            if (hit(nextBullets, enemy)) continue;

            Enemy nextEnemy =
                    enemy.advance(Math.random() < 0.005);
            nextEnemies.add( nextEnemy );
        }

        return nextEnemies;
    }

    //--------------------------------------------------------------------
    private void advanceAttackWave(Collection<Enemy> nextEnemies)
    {
        if (Math.random() < ((.5 + score / 50)//0.09
                                /(nextEnemies.size() + 1)))
        {
            nextEnemies.add( spawnEnemy() );
        }
    }
    private Enemy spawnEnemy()
    {
        return new Enemy(Math.random() * 95 + 5, 100);
    }


    //--------------------------------------------------------------------
    private void playerShot(
            PlayerControl      control,
            Collection<Bullet> nextBullets,
            Player             nextPlayer)
    {
        if (control.shotPending())
        {
            nextBullets.addAll( nextPlayer.shoot() );
        }
    }

    private void enemyShots(
            List<Enemy>  nextEnemies,
            List<Bullet> nextBullets)
    {
        for (Enemy enemy : nextEnemies)
        {
            if (Math.random() < 0.012)
            {
                nextBullets.addAll( enemy.shoot() );
            }
        }
    }

    //--------------------------------------------------------------------
    private double collectBullectHits(
            List<Bullet>    nextBullets,
            List<Explotion> nextExplotions)
    {
        double prevScoreDelta = 0;
        double scoreDelta     = 0;
        for (Bullet bullet :
                new ArrayList<Bullet>(nextBullets))
        {
            if      (bullet.hits(enemies)) scoreDelta += 1;
            else if (bullet.hits(player))  scoreDelta -= 10;
            else if (bullet.inBounds())    continue;

            nextBullets.remove( bullet );

            if (prevScoreDelta != scoreDelta)
            {
                nextExplotions.add( bullet.explode() );
                prevScoreDelta = scoreDelta;
            }
        }
        return scoreDelta;
    }

    private void collectBullectLostEnemies(
            List<Enemy> nextEnemies)
    {
        for (Enemy enemy :
                new ArrayList<Enemy>(nextEnemies))
        {
            if (enemy.isLost())
            {
                nextEnemies.remove( enemy );
            }
        }
    }

    //--------------------------------------------------------------------
    private Collection<Particle> nextParticles(
            List<Bullet> nextBullets,
            List<Enemy>  nextEnemies)
    {
        Collection<Particle> nextParticles = new ArrayList<Particle>();
        for (Particle particle : particles)
        {
            if (! particle.disapated())
            {
                nextParticles.add( particle.advance() );
            }
        }

//        nextParticles.add( nextPlayer.leaveTrail() );
        for (Enemy enemy : nextEnemies)
        {
            nextParticles.add( enemy.leaveTrail() );
        }
        for (Bullet bullet : nextBullets)
        {
            nextParticles.add( bullet.leaveTrail() );
        }

        return nextParticles;
    }

    //--------------------------------------------------------------------
    private List<Explotion> nextExplotions()
    {
        List<Explotion> nextExplotions = new ArrayList<Explotion>();
        for (Explotion explotion : explotions)
        {
            if (explotion.isBurning())
            {
                nextExplotions.add( explotion.advance() );
            }
        }
        return nextExplotions;
    }

    //--------------------------------------------------------------------
    private boolean hit(
            List<Bullet> testBullets,
            Collidable   target)
    {
        for (Bullet bullet : testBullets)
        {
            if (bullet.hits( target )) return true;
        }
        return false;
    }


    //--------------------------------------------------------------------
    public void display(final GL gl)
    {
        player.display( gl );

        for (Enemy enemy : enemies)
        {
            enemy.display( gl );
        }

        for (Bullet bullet : bullets)
        {
            bullet.display( gl );
        }

        for (Particle particle : particles)
        {
            particle.display( gl );
        }

        for (Explotion explotion : explotions)
        {
            explotion.display( gl );
        }
    }

    
    //--------------------------------------------------------------------
    public double score()
    {
        return score;
    }
}
