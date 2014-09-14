package capital.component.block;

import capital.component.DrawableComponent;
import capital.rendering.RenderUtil;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by Biw on 11/9/2557.
 */
public class Block extends DrawableComponent
{
    protected static int d_size = 1;
    protected Vector3f startpos = new Vector3f(0,0,0);
    protected boolean moveable = false;
    protected Texture texture;
    protected int id;

    public Block(int id)
    {
        super(d_size);
        this.id = id;
        this.d_size = 1;
        this.moveable = false;
    }

    public Block(int id, int d_size)
    {
        super(d_size);
        this.id = id;
        this.d_size = d_size;
        this.moveable = false;
    }

    public Block(int id, int d_size, boolean moveable)
    {
        super(d_size);
        this.id = id;
        this.d_size = d_size;
        this.moveable = true;
    }

    //TODO: Add the texture to the quad correctly. (Teams)
    public Block addTexture(String format, String filename)
    {
        //RenderUtil have the util :D

        return this;
    }

    public static void drawBlock(Block block)
    {
        int vboID = RenderUtil.createID();

        RenderUtil.createCube(vboID, block.startpos, block.d_size, block.d_size, block.d_size);
    }

    public static void drawBlockArray(Block block, int north, int east)
    {
        int vboID = RenderUtil.createID();

        RenderUtil.createCube(vboID, block.startpos, north, east, block.d_size);
    }
}
