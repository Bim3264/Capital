package capital.graphics;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by Biw on 23/9/2557.
 */
public class Material
{
    public Texture texture;
    public int id;
    public String name;
    public Vector3f ambient, diffuse, specular;
    public float shininess;
    public int shader;
}
