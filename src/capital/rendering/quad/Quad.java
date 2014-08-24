package capital.rendering.quad;

import capital.rendering.RenderUtil;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Biw on 24/8/2557.
 */
public class Quad
{
    public Quad(int vboID, Vector3f startPos, int size)
    {
        RenderUtil.createQuad(vboID, startPos, size);
    }
}
