package capital.rendering;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Biw on 14/9/2557.
 */
public class Vertex
{
    public static final int SIZE = 3;

    private Vector3f pos;

    public Vertex(Vector3f pos)
    {
        this.pos = pos;
    }

    public Vector3f getPos()
    {
        return pos;
    }

    public void setPos(Vector3f pos)
    {
        this.pos = pos;
    }
}
