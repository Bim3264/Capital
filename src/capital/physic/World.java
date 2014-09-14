package capital.physic;

/**
 *
 *
 *@author Bim
 */
public class World
{
    private float GRAVITY = 2.0f;
    public boolean isEffectedByGravity = false;

    public World(float gravity)
    {
        this.GRAVITY = gravity;
    }
}
