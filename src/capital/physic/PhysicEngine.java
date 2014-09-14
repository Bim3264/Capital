package capital.physic;

/**
 * Created by Biw on 14/9/2557.
 */
public class PhysicEngine
{
    public float GRAVITY = 2.0f;

    public PhysicEngine(float gravity)
    {
        this.setGravity(gravity);
    }

    public void setGravity(float gravity)
    {
        this.GRAVITY = gravity;
    }
}
