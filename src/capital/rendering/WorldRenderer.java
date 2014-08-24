package capital.rendering;

import capital.rendering.quad.Quad;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class WorldRenderer
{
    int size = 32;
    boolean[][] coordinate;

    FloatBuffer pos;

    /**
     * Under work
     *
     * @param resourceImage
     */
    public void renderFromBitmap(FileInputStream resourceImage)
    {
        try
        {
            BufferedImage bitmap = ImageIO.read(resourceImage);

            pos = new BufferUtils().createFloatBuffer(bitmap.getWidth() * bitmap.getHeight());
            coordinate = new boolean[bitmap.getWidth()][bitmap.getHeight()];

            for (int i = 0; i < bitmap.getWidth(); i++)
            {
                for (int j = 0; j < bitmap.getHeight(); j++)
                {
                    if (bitmap.getRGB(i,j) == Color.BLACK.getRGB())
                    {
                        coordinate[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < bitmap.getWidth(); i++)
            {
                for (int j = 0; j < bitmap.getHeight(); j++)
                {
                    if (coordinate[i][j] == true)
                    {
                        //TODO: Finish this thing
                        pos.put(i).put(0).put(j);
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
    }

    public void render()
    {
        /**
         * The more the size the faster is run. (Must be power-of-two and not larger than the current world size)
         */
//        Quad quad = new Quad(vboID, new Vector3f(0,0,0), 50);

        for (int i = 0; i < 256; i += size)
        {
            for (int j = 0; j < 256; j += size)
            {
                int vboID = RenderUtil.createID();
                Quad quad = new Quad(vboID, new Vector3f(i,0,j), size);
            }
        }
    }
}
