package capital.component.block;

import capital.component.Drawable;
import capital.rendering.RenderUtil;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Biw on 11/9/2557.
 */
public class Block extends Drawable
{
    protected static int d_size = 1;
    protected Vector3f startpos = new Vector3f(0,0,0);
    protected boolean moveable = false;
    protected Texture texture;

    public Block(int id, int d_size)
    {
        super(d_size);
        this.d_size = d_size;
    }

    public Block(int id, int d_size, boolean moveable)
    {
        super(d_size);
        this.moveable = true;
    }

    //TODO: Add the texture to the quad correctly. (Teams)
    public Block addTexture(String format, String filename)
    {
        //RenderUtil have the util :D

        return this;
    }

    public static void drawBlockArray(Block block, int north, int east)
    {
        int vboID = RenderUtil.createID();

        RenderUtil.createCube(vboID, block.startpos, north, east, block.d_size);
    }
}
